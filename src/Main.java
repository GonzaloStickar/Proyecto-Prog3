import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArchivoManager archivo = new ArchivoManager();
        //archivo.aniadirTexto("hola");
        //archivo.mostrarContenidoLog();
        //archivo.eliminarContenidoLog();




        Random random = new Random();
        NombresApodos[] nombresApodos = NombresApodos.values();
        HashSet<NombresApodos> nombresRandom = new HashSet<>();
        while (nombresRandom.size()<6) {
            nombresRandom.add(nombresApodos[random.nextInt(nombresApodos.length)]);
        }
        for (NombresApodos nombre : nombresRandom) {
            System.out.println(nombre);
        }
    }
}