import java.io.*;
import java.util.Scanner;

public class ArchivoManager {
    private static final File log = new File("log.txt");

    public void aniadirTexto(String texto) {
        try {
            FileWriter writer = new FileWriter(log, true);
            BufferedWriter mejorRendimiento = new BufferedWriter(writer);
            mejorRendimiento.write(texto);
            //mejorRendimiento.newLine();

            mejorRendimiento.close();
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Ocurrió un error al ESCRIBIR en el archivo.");
        }
    }
    public void eliminarContenidoLog() {
        try {
            if (!log.exists()) {
                FileNoEncontrado();
            }
            else {
                FileWriter log = new FileWriter("log.txt");
                log.close();
            }
        }
        catch (IOException e) {
            System.out.println("Ocurrió un error al ELIMINAR el contenido del archivo.");
        }
    }

    public void mostrarContenidoLog() {
        try {
            if (!log.exists()) {
                FileNoEncontrado();
            }
            else {
                Scanner scannerLog = new Scanner(log);
                while (scannerLog.hasNextLine()) {
                    String linea = scannerLog.nextLine();
                    System.out.println(linea);
                }
            }
        }
        catch (IOException e) {
            System.out.println("Ocurrió un error al MOSTRAR el contenido del archivo.");
        }
    }

    public void eliminarlog() {
        if (log.delete()) { //Si existe el archivo, retorna 'true'.
            System.out.println("Archivo 'log' eliminado.");
        }
        else {
            FileNoEncontrado();
        }
    }

    public static void FileNoEncontrado() {
        System.out.println("No existe el archivo.");
    }
}