import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArchivoManager archivo = new ArchivoManager();
        //archivo.aniadirTexto("hola");
        //archivo.mostrarContenidoLog();
        //archivo.eliminarContenidoLog();



        //Genero NombresApodos
        ArrayList<NombresApodos> nombresRandom = new ArrayList<>(crearNombresApodos());
        ArrayList<NombresApodos> apodosRandom = new ArrayList<>(crearNombresApodos());

        //Genero personajes
        //[0,1,0,2,1,2] (Dependiendo el número, se crea una clase diferente) (Para diferenciar y que sea aleatorio)

        //Prueba
//        System.out.println(personajeHumano.atacar());
//        personajeOrco.setSalud(personajeHumano.atacar());
//        System.out.println(personajeOrco.salud);

        ArrayList<Personajes> jugador1Personajes = new ArrayList<Personajes>(crearPersonajes(nombresRandom,apodosRandom));
        ArrayList<Personajes> jugador2Personajes = new ArrayList<Personajes>(crearPersonajes(nombresRandom,apodosRandom));

        for (Personajes personaje : jugador1Personajes) {
            System.out.println(personaje);
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
}