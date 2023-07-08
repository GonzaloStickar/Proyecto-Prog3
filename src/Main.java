import java.text.DecimalFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArchivoManager archivo = new ArchivoManager();
        //archivo.aniadirTexto("hola");

        //Genero NombresApodos
        ArrayList<NombresApodos> nombresRandom = new ArrayList<>(crearNombresApodos());
        ArrayList<NombresApodos> apodosRandom = new ArrayList<>(crearNombresApodos());

        //Genero personajes
        //[0,1,0,2,1,2] (Dependiendo el número, se crea una clase diferente) (Para diferenciar y que sea aleatorio)

        //Prueba
//        System.out.println(personajeHumano.atacar());
//        personajeOrco.setSalud(personajeHumano.atacar());
//        System.out.println(personajeOrco.salud);

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
                        System.out.println();
                        System.out.println("---------------------------------------------");

                        ArrayList<Personajes> jugador1Personajes = new ArrayList<>(crearPersonajes(nombresRandom, apodosRandom));
                        ArrayList<Personajes> jugador2Personajes = new ArrayList<>(crearPersonajes(nombresRandom, apodosRandom));

                        ArrayList<Integer> ordenJuegoJugador1Personajes = new ArrayList<>(crearListaOrdenRandomPersonajesJugadores());
                        ArrayList<Integer> ordenJuegoJugador2Personajes = new ArrayList<>(crearListaOrdenRandomPersonajesJugadores());

                        ArrayList<Personajes> personajesP1Coordinados = new ArrayList<>();
                        ArrayList<Personajes> personajesP2Coordinados = new ArrayList<>();

                        for (int i=0;i<jugador1Personajes.size();i++) {
                            personajesP1Coordinados.add((jugador1Personajes.get(ordenJuegoJugador1Personajes.get(i))));
                            personajesP2Coordinados.add(jugador2Personajes.get(ordenJuegoJugador2Personajes.get(i)));
                        }

                        boolean terminoJuego=false;
                        int ronda=0;

                        while (!terminoJuego) {

                            System.out.println("---------------------------------------------");

                            System.out.println("Cartas Jugador 1: "+personajesP1Coordinados.size());
                            System.out.println("Cartas Jugador 2: "+personajesP2Coordinados.size());

                            ronda+=1;

                            int ataquesP1= 7;
                            int ataquesP2= 7;

                            double ataqueP1;
                            double ataqueP2;

                            System.out.println("Ronda "+ronda);
                            int turno = crearNumeroEntreRangoRandom(0,1);

                            if (turno==0) {
                                System.out.println("Empieza atacando el jugador 1");
                            }
                            else {
                                System.out.println("Empieza atacando el jugador 2");
                            }

                            while (true) {
                                ataqueP1 = ((AtaquePersonaje) personajesP1Coordinados.get(0)).atacar();
                                ataqueP2 = ((AtaquePersonaje) personajesP2Coordinados.get(0)).atacar();

                                if (ataquesP1==0 || ataquesP2 ==0) {
                                    break; //Vuelve a empezar, una nueva ronda.
                                }

                                if (turno==0) {
                                    System.out.println("(Ataques restantes:" + ataquesP1 + ")   J1 ataca al J2 con "+personajesP1Coordinados.get(0).apodo+" con un daño de "+obtenerDosDecimales(ataqueP1));

                                    if (ataqueP1>personajesP2Coordinados.get(0).salud) {
                                        System.out.println("Mano ganado por jugador 2");
                                        if (personajesP1Coordinados.size()==1) {
                                            terminoJuego=true;
                                            System.out.println("Terminó el juego, el ganador y merecedor del Trono de Hierro es el jugador 2");
                                        }
                                        else {
                                            personajesP1Coordinados.remove(0);
                                        }
                                        break;
                                    }
                                    else {
                                        personajesP2Coordinados.get(0).setSalud(ataqueP1);
                                        ataquesP1-=1;
                                    }
                                    turno = 1;
                                }
                                else {
                                    System.out.println("(Ataques restantes:" + ataquesP1 + ")   J2 ataca al J1 con "+personajesP2Coordinados.get(0).apodo+" con un daño de "+obtenerDosDecimales(ataqueP2));

                                    if (ataqueP2>personajesP1Coordinados.get(0).salud) {
                                        System.out.println("Mano ganado por jugador 1");
                                        if (personajesP2Coordinados.size()==1) {
                                            terminoJuego=true;
                                            System.out.println("Terminó el juego, el ganador y merecedor del Trono de Hierro es el jugador 1");
                                        }
                                        else {
                                            personajesP2Coordinados.remove(0);
                                        }
                                        break;
                                    }
                                    else {
                                        personajesP1Coordinados.get(0).setSalud(ataqueP2);
                                        ataquesP2-=1;
                                    }
                                    turno = 0;
                                }
                            }
                            System.out.println("---------------------------------------------");
                        }
                    }

                    case 2-> {
                        //Ingresar datos de los personajes, a mano.
                        System.out.println("Que personaje quiere?");
                        System.out.println("1) Orco");
                        System.out.println("2) Elfo");
                        System.out.println("3) Humano");
                        try {
                            System.out.print("Elija una de las opciones: ");
                            int opcionPersonaje = scanner.nextInt();
                            Personajes personaje = crearPersonaje(opcionPersonaje);
                            System.out.println(personaje.nombre);
                            System.out.println(personaje.apodo);
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Ingresar un número");
                            scanner.next();
                        }
                    }
                    case 3-> archivo.mostrarContenidoLog();
                    case 4 -> archivo.eliminarContenidoLog();
                    case 5 -> archivo.eliminarlog();
                    case 0 -> finalizar = true;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Ingresar solamente un número");
                scanner.next();
            }
        }
    }

    private static String obtenerDosDecimales(double danio) {
        DecimalFormat dosDecimales = new DecimalFormat("0.00");
        return dosDecimales.format(Math.abs(danio));
    }

    public static ArrayList<NombresApodos> crearNombresApodos() {
        NombresApodos[] nombresApodos = NombresApodos.values();
        Random random = new Random();

        ArrayList<NombresApodos> arrayList = new ArrayList<>();
        while (arrayList.size()<6) {
            if (!arrayList.contains(nombresApodos[random.nextInt(nombresApodos.length)])) {
                arrayList.add(nombresApodos[random.nextInt(nombresApodos.length)]);
            }
        }
        return arrayList;
    }
    public static int crearNumeroEntreRangoRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static ArrayList<Personajes> crearPersonajes(ArrayList<NombresApodos> nombresRandom, ArrayList<NombresApodos> apodosRandom) {
        ArrayList<Personajes> personajesJugador = new ArrayList<>();
        for (int i=0;i<3;i++) {
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
        Scanner scanner = new Scanner(System.in);

        //Nombre
        String nombrePersonaje;
        while (true) {
            try {
                System.out.print("Ingrese el nombre del personaje: ");
                String nombrePersonajeIngresado = scanner.nextLine();
                if (nombrePersonajeIngresado.matches("^[a-zA-Z]+$")) {
                    nombrePersonaje = nombrePersonajeIngresado;
                    break;
                } else {
                    System.out.println("Error, ingrese un nombre adecuado.");
                }
            } catch (IllegalArgumentException e) {
                scanner.nextLine();
            }
        }

        //Apodo
        String apodoPersonaje;
        while (true) {
            try {
                System.out.print("Ingrese el apodo del personaje: ");
                String apodoPersonajeIngresado = scanner.nextLine();
                if (apodoPersonajeIngresado.matches("^[a-zA-Z]+$")) {
                    apodoPersonaje = apodoPersonajeIngresado;
                    break;
                } else {
                    System.out.println("Error, ingrese un apodo adecuado.");
                }
            } catch (IllegalArgumentException e) {
                scanner.nextLine();
            }
        }

        //FechaNacimiento
        String fechaNacimientoPersonaje;
        while (true) {
            try {
                System.out.print("Ingrese una fecha de nacimiento del personaje: ");
                String fechaNacimientoPersonajeIngresado = scanner.nextLine();
                if (fechaNacimientoPersonajeIngresado.matches("[a-zA-Z0-9 ]+")) {
                    fechaNacimientoPersonaje = fechaNacimientoPersonajeIngresado;
                    break;
                } else {
                    System.out.println("Error, ingrese una fecha de nacimiento adecuada.");
                }
            } catch (IllegalArgumentException e) {
                scanner.nextLine();
            }
        }

        //Edad
        int edadPersonaje;
        while (true) {
            try {
                System.out.print("Ingrese la edad del personaje: ");
                int edadPersonajeIngresado = scanner.nextInt();
                if (edadPersonajeIngresado >= 0 && edadPersonajeIngresado <= 300) {
                    scanner.nextLine();
                    edadPersonaje = edadPersonajeIngresado;
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese una edad válida.");
                scanner.nextLine();
            }
        }

        //Salud
        double saludPersonaje;
        while (true) {
            try {
                System.out.print("Ingrese la 'salud' del personaje: ");
                int saludPersonajeIngresado = scanner.nextInt();
                if (saludPersonajeIngresado > 0 && saludPersonajeIngresado < 9999) {
                    scanner.nextLine();
                    saludPersonaje = saludPersonajeIngresado;
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese una 'salud' adecuada.");
                scanner.nextLine();
            }
        }

        //Velocidad
        int velocidadPersonaje;
        while (true) {
            try {
                System.out.print("Ingrese la velocidad del personaje: ");
                int velocidadPersonajeIngresado = scanner.nextInt();
                if (velocidadPersonajeIngresado >= 1 && velocidadPersonajeIngresado <= 10) {
                    scanner.nextLine();
                    velocidadPersonaje = velocidadPersonajeIngresado;
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese una velocidad adecuada.");
                scanner.nextLine();
            }
        }

        //Destreza
        int destrezaPersonaje;
        while (true) {
            try {
                System.out.print("Ingrese la destreza del personaje: ");
                int destrezaPersonajeIngresado = scanner.nextInt();
                if (destrezaPersonajeIngresado >= 1 && destrezaPersonajeIngresado <= 5) {
                    scanner.nextLine();
                    destrezaPersonaje = destrezaPersonajeIngresado;
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese una destreza adecuada.");
                scanner.nextLine();
            }
        }

        //Fuerza
        int fuerzaPersonaje;
        while (true) {
            try {
                System.out.print("Ingrese la fuerza del personaje: ");
                int fuerzaPersonajeIngresado = scanner.nextInt();
                if (fuerzaPersonajeIngresado >= 1 && fuerzaPersonajeIngresado <= 10) {
                    scanner.nextLine();
                    fuerzaPersonaje = fuerzaPersonajeIngresado;
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese una fuerza adecuada.");
                scanner.nextLine();
            }
        }

        //Nivel
        int nivelPersonaje;
        while (true) {
            try {
                System.out.print("Ingrese el nivel del personaje: ");
                int nivelPersonajeIngresado = scanner.nextInt();
                if (nivelPersonajeIngresado >= 1 && nivelPersonajeIngresado <= 10) {
                    scanner.nextLine();
                    nivelPersonaje = nivelPersonajeIngresado;
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un nivel adecuada.");
                scanner.nextLine();
            }
        }

        //Armadura
        int armaduraPersonaje;
        while (true) {
            try {
                System.out.print("Ingrese la armadura del personaje: ");
                int armaduraPersonajeIngresado = scanner.nextInt();
                if (armaduraPersonajeIngresado >= 1 && armaduraPersonajeIngresado <= 10) {
                    scanner.nextLine();
                    armaduraPersonaje = armaduraPersonajeIngresado;
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese una armadura adecuada.");
                scanner.nextLine();
            }
        }

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
}