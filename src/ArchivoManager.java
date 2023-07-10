import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ArchivoManager {
    private static final File log = new File("log.txt");

    public void aniadirTexto(String texto) {
        try {
            FileWriter writer = new FileWriter(log, true);
            BufferedWriter mejorRendimiento = new BufferedWriter(writer);
            mejorRendimiento.write(texto);
            mejorRendimiento.newLine();
            mejorRendimiento.write(texto);

            mejorRendimiento.close();
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Ocurrió un error al ESCRIBIR en el archivo.");
        }
    }
    public void eliminarContenidoLog() {
        try {
            FileWriter log = new FileWriter("log.txt");
            log.close();
        }
        catch (IOException e) {
            System.out.println("Ocurrió un error al ELIMINAR el contenido del archivo.");
        }
    }

    public void mostrarContenidoLog() {
        try {
            Scanner scannerLog = new Scanner(log);
            while (scannerLog.hasNextLine()) {
                String linea = scannerLog.nextLine();
                System.out.println(linea);
            }
        }
        catch (IOException e) {
            System.out.println("Ocurrió un error al MOSTRAR el contenido del archivo.");
        }
    }

    public void eliminarlog() {
        if (log.delete()) {
            System.out.println("'log' eliminado.");
        }
        else {
            System.out.println("No existe el archivo");
        }
    }

    public void mostrarFelicitaciones(int jugador) {
        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("                          Felicitaciones!!!");
        System.out.println(" Terminó el juego, el ganador y merecedor del Trono de Hierro es el jugador "+jugador);
        System.out.println("==============================================================================");
        System.out.println();
    }
}
