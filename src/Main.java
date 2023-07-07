import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArchivoManager archivo = new ArchivoManager();
        //archivo.aniadirTexto("hola");
        //archivo.mostrarContenidoLog();
        //archivo.eliminarContenidoLog();


        //Genero personajes
        //[0,1,0,2,1,2] (Dependiendo el número, se crea una clase diferente) (Para diferenciar y que sea aleatorio)
        PersonajeHumano personajeHumano = new PersonajeHumano("pepe","pepito");
        PersonajeOrco personajeOrco = new PersonajeOrco("orco","shrek");
        PersonajeElfo personajeElfo = new PersonajeElfo("elfo","bosques");



        //Prueba
        double ataque = personajeHumano.atacar();
        if (ataque > 0 && ataque<=100) {
            personajeOrco.setSalud(ataque);
        }



        HashSet<NombresApodos> nombresRandom = new HashSet<>(crearNombresApodos());
        HashSet<NombresApodos> apodosRandom = new HashSet<>(crearNombresApodos());
//        for (NombresApodos nombre : nombresRandom) {
//            System.out.println(nombre);
//        }
    }

    public static HashSet<NombresApodos> crearNombresApodos() {
        NombresApodos[] nombresApodos = NombresApodos.values();
        Random random = new Random();

        HashSet<NombresApodos> hashSet = new HashSet<>();
        while (hashSet.size()<6) {
            hashSet.add(nombresApodos[random.nextInt(nombresApodos.length)]);
        }
        return hashSet;
    }
    public static int crearValorEntreRangoRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}