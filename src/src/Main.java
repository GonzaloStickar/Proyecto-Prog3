import java.util.*;

public class Main {

    public static void main(String[] args) {

        ArchivoManager archivo = new ArchivoManager();
        //archivo.aniadirTexto("hola");
        //archivo.mostrarContenidoLog();
        //archivo.eliminarContenidoLog();



        HashSet<NombresApodos> nombresRandom = new HashSet<>(crearNombresApodos());
        HashSet<NombresApodos> apodosRandom = new HashSet<>(crearNombresApodos());
        for (NombresApodos nombre : nombresRandom) {
            System.out.println(nombre);
        }
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
}