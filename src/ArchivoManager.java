import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArchivoManager {
    private static final File log = new File("log.txt");

    public void aniadirTexto(String texto) {
        try {
            FileWriter writer = new FileWriter(log, true);
            BufferedWriter mejorRendimiento = new BufferedWriter(writer);

            mejorRendimiento.write(texto);
            mejorRendimiento.newLine();

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
                fileNoEncontrado();
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
                fileNoEncontrado();
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
            fileNoEncontrado();
        }
    }

    public static void fileNoEncontrado() {
        System.out.println("No existe el archivo.");
    }

    public int buscarPartidaX() {
        int maxNumeroPartida=0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("log.txt"));
            Pattern pattern = Pattern.compile("Se inicia la partida (\\d+)");
            Matcher matcher;
            if (!log.exists()) {
                fileNoEncontrado();
            }
            else {
                String linea;

                while ((linea = reader.readLine()) != null) {
                    matcher = pattern.matcher(linea);
                    if (matcher.find()) {
                        int numeroPartida = Integer.parseInt(matcher.group(1));
                        if (numeroPartida > maxNumeroPartida) {
                            maxNumeroPartida = numeroPartida;
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println("Ocurrió un error al BUSCAR el número del último juego.");
        }
        return maxNumeroPartida+1;
    }
}