import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArchivoManager archivo = new ArchivoManager();
        archivo.aniadirTexto("hola");

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
                    case 1:
                        ArrayList<Personajes> jugador1Personajes = new ArrayList<>(crearPersonajes(nombresRandom, apodosRandom));
                        ArrayList<Personajes> jugador2Personajes = new ArrayList<>(crearPersonajes(nombresRandom, apodosRandom));

                        ArrayList<Integer> ordenJuegoJugador1Personajes = new ArrayList<>(crearListaOrdenRandomPersonajesJugadores());
                        ArrayList<Integer> ordenJuegoJugador2Personajes = new ArrayList<>(crearListaOrdenRandomPersonajesJugadores());

                        ArrayList<Personajes> personajesP1Coordinados = new ArrayList<>();
                        ArrayList<Personajes> personajesP2Coordinados = new ArrayList<>();

                        //double ataqueP1 = (((AtaquePersonaje) jugador1Personajes.get(ordenJuegoJugador1Personajes.get(0))).atacar());

                        for (int i=0;i<3;i++) {
                            personajesP1Coordinados.add((jugador1Personajes.get(ordenJuegoJugador1Personajes.get(i))));
                            personajesP2Coordinados.add(jugador2Personajes.get(ordenJuegoJugador2Personajes.get(i)));
                        }

                        for (int i=0;i<3;i++) {
                            int turno = crearNumeroEntreRangoRandom(0,1);
                            while (true) {
                                int ataquesP1 = 7;
                                int ataquesP2 = 7;

                                if (turno==0) {
                                    ataquesP1=-1;
                                    double ataqueP1 = (((AtaquePersonaje) jugador1Personajes.get(ordenJuegoJugador1Personajes.get(i))).atacar());

                                    if ((ataqueP1>personajesP2Coordinados.get(i).salud) && (personajesP2Coordinados.size()==1)) {
                                        System.out.println("Gano el p1");
                                        break;
                                    }

                                    else if (ataqueP1>personajesP2Coordinados.get(i).salud) {
                                        System.out.println("Gano el p1");
                                        break;
                                    }
                                    else {
                                        personajesP2Coordinados.get(i).setSalud(ataqueP1);
                                    }
                                    turno = 1;
                                }
                                else {
                                    ataquesP2=-1;
                                    double ataqueP2 = (((AtaquePersonaje) jugador2Personajes.get(ordenJuegoJugador2Personajes.get(i))).atacar());

                                    if ((ataqueP2>personajesP1Coordinados.get(i).salud) && (personajesP1Coordinados.size()==1)) {
                                        System.out.println("Gano el p2");
                                        break;
                                    }

                                    else if (ataqueP2>personajesP1Coordinados.get(i).salud) {
                                        System.out.println("Gano el p2");
                                        break;
                                    }
                                    else {
                                        personajesP1Coordinados.get(i).setSalud(ataqueP2);
                                    }
                                    turno = 0;
                                }

                            }
                        }
                        break;

                    case 2:


                    case 3:
                        archivo.mostrarContenidoLog();
                        break;
                    case 4:
                        archivo.eliminarContenidoLog();
                        break;
                    case 5:
                        archivo.eliminarlog();
                        break;

                    case 0:
                        finalizar = true;
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Ingresar solamente un número");
                scanner.next();
            }
        }
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
}