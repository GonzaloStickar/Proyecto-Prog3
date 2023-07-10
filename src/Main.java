import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArchivoManager archivo = new ArchivoManager();
        //archivo.aniadirTexto("hola");

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
                        ArrayList<NombresApodos> nombresRandom = new ArrayList<>(crearNombresRandom());
                        ArrayList<NombresApodos> apodosRandom = new ArrayList<>(crearApodosRandom(nombresRandom));

                        ArrayList<Personajes> jugador1Personajes = new ArrayList<>(crearPersonajes(nombresRandom, apodosRandom));
                        ArrayList<Personajes> jugador2Personajes = new ArrayList<>(crearPersonajes(nombresRandom, apodosRandom));

                        ArrayList<Integer> ordenJuegoJugador1Personajes = new ArrayList<>(crearListaOrdenRandomPersonajesJugadores());
                        ArrayList<Integer> ordenJuegoJugador2Personajes = new ArrayList<>(crearListaOrdenRandomPersonajesJugadores());

                        ArrayList<Personajes> personajesP1Coordinados = new ArrayList<>();
                        ArrayList<Personajes> personajesP2Coordinados = new ArrayList<>();

                        for (int i=0;i<3;i++) {
                            personajesP1Coordinados.add((jugador1Personajes.get(ordenJuegoJugador1Personajes.get(i))));
                            personajesP2Coordinados.add(jugador2Personajes.get(ordenJuegoJugador2Personajes.get(i)));
                        }
                        iniciarPartida(personajesP1Coordinados,personajesP2Coordinados);
                    }
                    case 2-> {
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

    public static Set<NombresApodos> crearApodosRandom(ArrayList<NombresApodos> array) {
        Set<NombresApodos> setApodos = new HashSet<>(Set.of(NombresApodos.values()));
        array.forEach(setApodos::remove);
        Set<NombresApodos> setApodosNuevo = new HashSet<>();

        while (setApodosNuevo.size()<6) {
            int numeroRandom = (int)(Math.random()*setApodos.size());
            setApodosNuevo.add(NombresApodos.values()[numeroRandom]);
        }
        return setApodosNuevo;
    }

    public static Set<NombresApodos> crearNombresRandom() {
        NombresApodos[] nombresApodos = NombresApodos.values();
        Set<NombresApodos> setNombres = new HashSet<>();

        while (setNombres.size()<6) {
            int numeroRandom = (int)(Math.random()*nombresApodos.length);
            setNombres.add(nombresApodos[numeroRandom]);
        }
        return setNombres;
    }

    public static int crearNumeroEntreRangoRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static ArrayList<Personajes> crearPersonajes(ArrayList<NombresApodos> nombresRandom, ArrayList<NombresApodos> apodosRandom) {
        ArrayList<Personajes> personajesJugador = new ArrayList<>();

        for (int i=0;i<nombresRandom.size();i++) {
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

    public static ArrayList<Integer> crearListaOrdenRandomPersonajesJugadores() {
        ArrayList<Integer> array = new ArrayList<>();
        while (array.size()<3) {
            int numero = crearNumeroEntreRangoRandom(0,2);
            if (!array.contains(numero)) {
                array.add(numero);
            }
        }
        return array;
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
        System.out.println("---------------------------------------------");

        boolean terminoJuego=false;
        int ronda=0;
        Controller controller = new Controller();

        while (!terminoJuego) {
            System.out.println("---------------------------------------------");
            System.out.println("Cartas Jugador 1: "+j1.size());
            System.out.println("Cartas Jugador 2: "+j2.size());
            System.out.println();

            ronda+=1;

            int ataquesP1= 7;
            int ataquesP2= 7;

            double ataqueP1;
            double ataqueP2;

            int iJ1 = crearNumeroEntreRangoRandom(0,j1.size()-1);
            int iJ2 = crearNumeroEntreRangoRandom(0,j2.size()-1);

            System.out.println("Ronda "+ronda);
            System.out.println();
            int turno = crearNumeroEntreRangoRandom(0,1);

            if (turno==0) {
                System.out.println("Empieza atacando el jugador 1");
            }
            else {
                System.out.println("Empieza atacando el jugador 2");
            }

            while (true) {

                //Entre un rango (posición 0 y el rango de cada "mazo"), creo un número aleatorio.
                //Este número aleatorio puedo identificar la posición del personaje que se va a elegir
                // para la siguiente ronda.

                ataqueP1 = ((AtaquePersonaje) j1.get(iJ1)).atacar();
                ataqueP2 = ((AtaquePersonaje) j2.get(iJ2)).atacar();

                if (ataqueP1<=0) {
                    ataqueP1 = ((AtaquePersonaje) j1.get(iJ1)).atacar();
                }
                if (ataqueP2<=0) {
                    ataqueP2 = ((AtaquePersonaje) j2.get(iJ2)).atacar();
                }

                if (ataquesP1==0 || ataquesP2 ==0) {
                    break; //Vuelve a empezar una nueva ronda independientemente
                    // de quien haya ganado. Si gana la ronda el J1, y el J2
                    // tenía un ataque más, no va a ser posible que lo realice, ya que
                    // el J1 ganó la mano.
                }
                System.out.println();

                if (turno==0) {
                    System.out.println("Ataca J1");
                    System.out.println("J2 tiene una salud de: "+obtenerDosDecimales(j2.get(iJ2).salud));
                    System.out.println("J1 ataca al J2 con "+j1.get(iJ1).apodo+" con un daño de "+obtenerDosDecimales(ataqueP1));
                    System.out.println("(Ataques restantes:" + (ataquesP1-1) + ")");

                    if (ataqueP1>j2.get(iJ2).salud) {
                        System.out.println();
                        System.out.println("Ronda ganada por jugador 1");
                        if (j2.size()==1) {
                            int jugadorCampeon=1;
                            controller.mostrarFelicitaciones(jugadorCampeon);
                            terminoJuego=true;
                        }
                        else {
                            j2.remove(iJ2);
                        }
                        break;
                    }
                    else {
                        j2.get(iJ2).setSalud(ataqueP1);
                        System.out.println("J2 tiene una salud restante de: "+j2.get(iJ2).salud);
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
                        System.out.println();
                        System.out.println("Ronda ganada por jugador 2");
                        if (j1.size()==1) {
                            int jugadorCampeon=2;
                            controller.mostrarFelicitaciones(jugadorCampeon);
                            terminoJuego=true;
                        }
                        else {
                            j1.remove(iJ1);
                        }
                        break;
                    }
                    else {
                        j1.get(iJ1).setSalud(ataqueP2);
                        System.out.println("J1 tiene una salud restante de: "+j1.get(iJ1).salud);
                        ataquesP2-=1;
                    }
                    turno = 0;
                }
            }
            System.out.println("---------------------------------------------");
            System.out.println();
        }
    }
}