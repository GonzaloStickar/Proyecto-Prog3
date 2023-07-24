import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static ArchivoManager archivo = new ArchivoManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean finalizar = false;
        while (!finalizar) {
            System.out.println();
            System.out.println("1) Iniciar partida");
            System.out.println("2) Iniciar partida e ingresar personajes a mano");
            System.out.println("3) Leer todas las partidas jugadas");
            System.out.println("4) Borrar partidas jugadas");
            System.out.println("5) Borrar archivo de log");
            System.out.println("0) Salir");

            try {
                System.out.print("Elija una de las opciones: ");
                int opcion = scanner.nextInt();
                switch (opcion) {
                    case 1 -> {
                        iniciarPartidaLog();

                        ArrayList<NombresApodos> nombresRandom = new ArrayList<>(crearNombresRandom());
                        ArrayList<NombresApodos> apodosRandom = new ArrayList<>(crearApodosRandom(nombresRandom));
                        System.out.println(nombresRandom);
                        System.out.println(apodosRandom);

                        archivo.aniadirTexto("Se generaron 6 personajes");
                        archivo.aniadirTexto("");
                        ArrayList<Personajes> jugador1Personajes = new ArrayList<>(crearPersonajes(nombresRandom, apodosRandom,0));
                        ArrayList<Personajes> jugador2Personajes = new ArrayList<>(crearPersonajes(nombresRandom, apodosRandom,3));
                        archivo.aniadirTexto("");

                        iniciarPartida(jugador1Personajes,jugador2Personajes);
                    }
                    case 2-> {
                        iniciarPartidaLog();

                        Controller controller = new Controller();
                        ArrayList<Personajes> personajesIngresadoAManoP1 = new ArrayList<>();
                        ArrayList<Personajes> personajesIngresadoAManoP2 = new ArrayList<>();

                        int personajeNum=0;
                        for (int i=0;i<6;i++) {
                            personajeNum+=1;

                            System.out.println("---Personaje "+personajeNum+"---");
                            //Ingresar datos de los personajes, a mano.
                            System.out.println("Que personaje quiere?");
                            System.out.println("1) Orco");
                            System.out.println("2) Elfo");
                            System.out.println("3) Humano");
                            int opcionPersonaje = controller.ingresarInt("Elija uno de los personajes: ",1,3,"Ingresar un número correctamente.");

                            if (opcionPersonaje==1 || opcionPersonaje==2 || opcionPersonaje==3) {
                                Personajes personajeNuevo = crearPersonaje(opcionPersonaje);

                                if (personajesIngresadoAManoP1.size() == 3) {
                                    personajesIngresadoAManoP2.add(personajeNuevo);
                                } else {
                                    personajesIngresadoAManoP1.add(personajeNuevo);
                                }
                            }
                        }
                        iniciarPartida(personajesIngresadoAManoP1,personajesIngresadoAManoP2);
                    }
                    case 3-> archivo.mostrarContenidoLog();
                    case 4 -> archivo.eliminarContenidoLog();
                    case 5 -> archivo.eliminarlog();
                    case 0 -> finalizar = true;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Ingresar solamente un número.");
                scanner.next();
            }
        }
    }

    private static String obtenerDosDecimales(double danio) {
        DecimalFormat dosDecimales = new DecimalFormat("0.00");
        return dosDecimales.format(Math.abs(danio));
    }

    public static ArrayList<NombresApodos> crearApodosRandom(ArrayList<NombresApodos> array) {
        ArrayList<NombresApodos> apodos = new ArrayList<>(Arrays.asList(NombresApodos.values()));
        for (NombresApodos nombre : array) {
            apodos.remove(nombre);
        }

        ArrayList<NombresApodos> arrayApodos = new ArrayList<>();

        while (arrayApodos.size()<6) {
            int numeroRandom = (int)(Math.random()*apodos.size());
            if (!arrayApodos.contains(apodos.get(numeroRandom))) {
                arrayApodos.add(apodos.get(numeroRandom));
            }
        }
        return arrayApodos;
    }

    public static ArrayList<NombresApodos> crearNombresRandom() {
        ArrayList<NombresApodos> nombres = new ArrayList<>(Arrays.asList(NombresApodos.values()));
        ArrayList<NombresApodos> arrayNombres = new ArrayList<>();

        while (arrayNombres.size()<6) {
            int numeroRandom = (int)(Math.random()*nombres.size());
            if (!arrayNombres.contains(nombres.get(numeroRandom))) {
                arrayNombres.add(nombres.get(numeroRandom));
            }
        }
        return arrayNombres;
    }

    public static int crearNumeroEntreRangoRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static ArrayList<Personajes> crearPersonajes(ArrayList<NombresApodos> nombresRandom, ArrayList<NombresApodos> apodosRandom,int indicador) {
        ArrayList<Personajes> personajesJugador = new ArrayList<>();
        int j;
        int jugadorInt;

        if (indicador==0) {
            jugadorInt = 1;
            j=3;
        }
        else {
            jugadorInt = 2;
            j=6;
        }

        for (int i=indicador;i<j;i++) {
            if (i==0) {
                archivo.aniadirTexto("--------Personaje " + (i+1) +" Jugador "+jugadorInt+": "+apodosRandom.get(i)+"-------");
            }
            else {
                archivo.aniadirTexto("--------Personaje " + (i+1) +" Jugador "+jugadorInt+": "+apodosRandom.get(i)+"-------");
            }

            int personajeNumero = crearNumeroEntreRangoRandom(0,2);

            if (personajeNumero == 0) {
                PersonajeHumano personajeHumano = new PersonajeHumano(nombresRandom.get(i),apodosRandom.get(i));
                personajesJugador.add(personajeHumano);
            }
            else if (personajeNumero == 1) {
                PersonajeOrco personajeOrco = new PersonajeOrco(nombresRandom.get(i),apodosRandom.get(i));
                personajesJugador.add(personajeOrco);
            }
            else {
                PersonajeElfo personajeElfo = new PersonajeElfo(nombresRandom.get(i),apodosRandom.get(i));
                personajesJugador.add(personajeElfo);
            }
        }
        return personajesJugador;
    }

    public static Personajes crearPersonaje (int opcionSeleccionada) {
        Controller controller = new Controller();

        //Nombre
        String nombrePersonaje = controller.ingresarStringRegex("Ingrese el nombre del personaje: ","^[a-zA-Z]+$","Error, ingrese un nombre adecuado.");

        //Apodo
        String apodoPersonaje = controller.ingresarStringRegex("Ingrese el apodo del personaje: ","^[a-zA-Z]+$","Error, ingrese un apodo adecuado.");

        //FechaNacimiento
        String fechaNacimientoPersonaje = controller.ingresarStringRegex("Ingrese una fecha de nacimiento del personaje: ","[a-zA-Z0-9 ]+","Error, ingrese una fecha de nacimiento adecuada.");

        //Edad
        int edadPersonaje = controller.ingresarInt("Ingrese la edad del personaje: ",0,300,"Ingrese una edad válida.");

        //Salud
        double saludPersonaje = controller.ingresarInt("Ingrese la 'salud' del personaje: ",1, 9999,"Ingrese una 'salud' adecuada.");

        //Velocidad
        int velocidadPersonaje = controller.ingresarInt("Ingrese la velocidad del personaje: ",1,10,"Ingrese una velocidad adecuada.");

        //Destreza
        int destrezaPersonaje = controller.ingresarInt("Ingrese la destreza del personaje: ",1,5,"Ingrese una destreza adecuada.");

        //Fuerza
        int fuerzaPersonaje = controller.ingresarInt("Ingrese la fuerza del personaje: ",1,10,"Ingrese una fuerza adecuada.");

        //Nivel
        int nivelPersonaje = controller.ingresarInt("Ingrese el nivel del personaje: ",1,10,"Ingrese un nivel adecuado.");

        //Armadura
        int armaduraPersonaje = controller.ingresarInt("Ingrese la armadura del personaje: ",1,10,"Ingrese una armadura adecuada.");

        if (opcionSeleccionada == 1) {
            return new PersonajeOrco(nombrePersonaje, apodoPersonaje, fechaNacimientoPersonaje, edadPersonaje, saludPersonaje, velocidadPersonaje, destrezaPersonaje, fuerzaPersonaje, nivelPersonaje, armaduraPersonaje);
        }
        else if (opcionSeleccionada==2) {
            return new PersonajeElfo(nombrePersonaje, apodoPersonaje, fechaNacimientoPersonaje, edadPersonaje, saludPersonaje, velocidadPersonaje, destrezaPersonaje, fuerzaPersonaje, nivelPersonaje, armaduraPersonaje);
        }
        else {
            return new PersonajeHumano(nombrePersonaje, apodoPersonaje, fechaNacimientoPersonaje, edadPersonaje, saludPersonaje, velocidadPersonaje, destrezaPersonaje, fuerzaPersonaje, nivelPersonaje, armaduraPersonaje);
        }
    }

    public static void iniciarPartida (ArrayList<Personajes> j1, ArrayList<Personajes> j2) {
        System.out.println();

        boolean terminoJuego=false;
        int ronda=0;
        Controller controller = new Controller();

        while (!terminoJuego) {
            archivo.aniadirTexto("------------------------------------------------------------------------------");

            System.out.println("------------------------------------------------------------------------------");
            System.out.println("Cartas Jugador 1: "+j1.size());
            System.out.println("Cartas Jugador 2: "+j2.size());
            System.out.println();

            ronda+=1;
            archivo.aniadirTexto("Ronda "+ronda);
            archivo.aniadirTexto("");
            System.out.println("Ronda "+ronda);
            System.out.println();

            int ataquesP1= 7;
            int ataquesP2= 7;

            int iJ1 = crearNumeroEntreRangoRandom(0,j1.size()-1);
            int iJ2 = crearNumeroEntreRangoRandom(0,j2.size()-1);

            int turno = crearNumeroEntreRangoRandom(0,1);

            if (turno==0) {
                archivo.aniadirTexto("El sistema sorteó al Jugador 1 para iniciar la ronda");
                archivo.aniadirTexto("");
                archivo.aniadirTexto("El sistema eligió al personaje "+j1.get(iJ1).nombre+" del jugador 1 y al personaje "+j2.get(iJ2).nombre+" del jugador 2 para que se enfrenten en esta ronda.");
                archivo.aniadirTexto("");
                System.out.println("Empieza atacando el jugador 1");
            }
            else {
                archivo.aniadirTexto("El sistema sorteó al Jugador 2 para iniciar la ronda");
                archivo.aniadirTexto("");
                archivo.aniadirTexto("El sistema eligió al personaje "+j2.get(iJ2).nombre+" del jugador 2 y al personaje "+j1.get(iJ1).nombre+" del jugador 1 para que se enfrenten en esta ronda.");
                archivo.aniadirTexto("");
                System.out.println("Empieza atacando el jugador 2");
            }

            while (true) {
                //Entre un rango (posición 0 y el rango de cada "mazo"), creo un número aleatorio.
                //Este número aleatorio puedo identificar la posición del personaje que se va a elegir
                // para la siguiente ronda.

                double ataqueP1 = Math.abs(((AtaquePersonaje) j1.get(iJ1)).atacar());
                double ataqueP2 = Math.abs(((AtaquePersonaje) j2.get(iJ2)).atacar());

                if (ataquesP1==0 && ataquesP2==0) {
                    archivo.aniadirTexto("");
                    archivo.aniadirTexto("Jugadores 1 y 2, se quedaron sin ataques.");
                    break;
                }

                System.out.println();

                if (turno==0) {
                    System.out.println("Ataca J1");
                    System.out.println("J2 tiene una salud de: "+obtenerDosDecimales(j2.get(iJ2).salud));
                    System.out.println("J1 ataca al J2 con "+j1.get(iJ1).apodo+" con un daño de "+obtenerDosDecimales(ataqueP1));
                    System.out.println("(Ataques restantes:" + (ataquesP1-1) + ")");

                    if (ataqueP1>j2.get(iJ2).salud) {
                        archivo.aniadirTexto(j1.get(iJ1).apodo+" ("+j1.get(iJ1).nombre+") realiza un ataque FINAL a "+j2.get(iJ2).apodo+" ("+j2.get(iJ2).nombre+") y le quita sus  "+obtenerDosDecimales(j2.get(iJ2).salud)+" puntos de salud restantes.");
                        archivo.aniadirTexto("");
                        archivo.aniadirTexto("La ronda es ganada por por jugador 1");
                        archivo.aniadirTexto("");

                        System.out.println();
                        System.out.println("Ronda ganada por jugador 1");

                        if (j2.size()==1) {
                            if (j1.size()==1) {
                                archivo.aniadirTexto("Gana Jugador 1, le quedó vivo el siguiente personaje:");
                            }
                            else {
                                archivo.aniadirTexto("Gana Jugador 1, le quedaron vivos los siguientes personajes:");
                            }

                            for (Personajes personaje : j1) {
                                archivo.aniadirTexto(personaje.nombre+" con "+obtenerDosDecimales(personaje.salud)+" de salud.");
                            }
                            controller.mostrarFelicitaciones(1);
                            controller.aniadirTextoFelicitaciones(1);
                            terminoJuego=true;
                        }
                        else {
                            archivo.aniadirTexto("Muere "+j2.get(iJ2).apodo);
                            archivo.aniadirTexto("");
                            archivo.aniadirTexto("J1 recibe una recompensa de 10 puntos de salud!.");

                            System.out.println("J1 recibe una recompensa de 10 puntos de salud!.");
                            j1.get(iJ1).premioPorGanar();
                            j2.remove(iJ2);
                        }
                        break;
                    }
                    else {
                        j2.get(iJ2).setSalud(ataqueP1);
                        archivo.aniadirTexto(j1.get(iJ1).apodo+" ("+j1.get(iJ1).nombre+") ataca a "+j2.get(iJ2).apodo+" ("+j2.get(iJ2).nombre+") y le quita "+obtenerDosDecimales(ataqueP1)+" de salud. "+j2.get(iJ2).apodo+" queda con "+obtenerDosDecimales(j2.get(iJ2).salud)+" de salud.");
                        System.out.println("J2 tiene una salud restante de: "+obtenerDosDecimales(j2.get(iJ2).salud));
                        ataquesP1-=1;
                    }
                    turno = 1;
                }
                else {
                    System.out.println("Ataca J2");
                    System.out.println("J1 tiene una salud de: "+obtenerDosDecimales(j1.get(iJ1).salud));
                    System.out.println("J2 ataca al J1 con "+j2.get(iJ2).apodo+" con un daño de "+obtenerDosDecimales(ataqueP2));
                    System.out.println("(Ataques restantes:" + (ataquesP2-1) + ")");

                    if (ataqueP2>j1.get(iJ1).salud) {
                        archivo.aniadirTexto(j2.get(iJ2).apodo+" ("+j2.get(iJ2).nombre+") realiza un ataque FINAL a "+j1.get(iJ1).apodo+" ("+j1.get(iJ1).nombre+") y le quita sus "+obtenerDosDecimales(j1.get(iJ1).salud)+" puntos de salud restantes.");
                        archivo.aniadirTexto("");
                        archivo.aniadirTexto("La ronda es ganada por jugador 2");
                        archivo.aniadirTexto("");

                        System.out.println();
                        System.out.println("Ronda ganada por jugador 2");
                        if (j1.size()==1) {
                            if (j2.size()==1) {
                                archivo.aniadirTexto("Gana Jugador 2, le quedó vivo el siguiente personaje:");
                            }
                            else {
                                archivo.aniadirTexto("Gana Jugador 2, le quedaron vivos los siguientes personajes:");
                            }

                            for (Personajes personaje : j2) {
                                archivo.aniadirTexto("-   "+personaje.nombre+" con "+obtenerDosDecimales(personaje.salud)+" de salud.");
                            }
                            controller.mostrarFelicitaciones(2);
                            controller.aniadirTextoFelicitaciones(2);
                            terminoJuego=true;
                        }
                        else {
                            archivo.aniadirTexto("Muere "+j1.get(iJ1).apodo);
                            archivo.aniadirTexto("");
                            archivo.aniadirTexto("J2 recibe una recompensa de 10 puntos de salud!.");

                            System.out.println("J2 recibe una recompensa de 10 puntos de salud!.");
                            j2.get(iJ2).premioPorGanar();
                            j1.remove(iJ1);
                        }
                        break;
                    }
                    else {
                        j1.get(iJ1).setSalud(ataqueP2);
                        archivo.aniadirTexto(j2.get(iJ2).apodo+" ("+j2.get(iJ2).nombre+") ataca a "+j1.get(iJ1).apodo+" ("+j1.get(iJ1).nombre+") y le quita "+obtenerDosDecimales(ataqueP2)+" de salud. "+j1.get(iJ1).apodo+" queda con "+obtenerDosDecimales(j1.get(iJ1).salud)+" de salud.");
                        System.out.println("J1 tiene una salud restante de: "+obtenerDosDecimales(j1.get(iJ1).salud));
                        ataquesP2-=1;
                    }
                    turno = 0;
                }
            }
            archivo.aniadirTexto("------------------------------------------------------------------------------");
            archivo.aniadirTexto("");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println();
        }
    }

    public static void iniciarPartidaLog() {
        archivo.aniadirTexto("Partida "+archivo.buscarPartidaX());
        archivo.aniadirTexto("");
    }
}