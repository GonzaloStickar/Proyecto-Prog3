import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    Scanner scanner = new Scanner(System.in);
    public void mostrarFelicitaciones(int jugador) {
        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("                       ¡¡¡Felicitaciones!!!");
        System.out.println(" Terminó el juego, el ganador y merecedor del Trono de Hierro es el jugador "+jugador);
        System.out.println("==============================================================================");
        System.out.println();
    }

    public void aniadirTextoFelicitaciones (int jugador) {
        Main.archivo.aniadirTexto("==============================================================================");
        Main.archivo.aniadirTexto("                     ¡¡¡Felicitaciones Jugador "+jugador+"!!!");
        Main.archivo.aniadirTexto("       Terminó el juego. eres el ganador y merecedor del Trono de Hierro");
        Main.archivo.aniadirTexto("                     las fuerzas mágicas del universo luz te abrazan!");
        Main.archivo.aniadirTexto("==============================================================================");
        Main.archivo.aniadirTexto("");
    }

    public void aniadirTextoPersonajesRestantes (ArrayList<Personajes> jugador, int intJugador) {
        Main.archivo.aniadirTexto("");
        if (jugador.size()==1) {
            Main.archivo.aniadirTexto("Gana Jugador "+intJugador+", le quedó vivo el siguiente personaje:");
        }
        else {
            Main.archivo.aniadirTexto("Gana Jugador " + intJugador + ", le quedaron vivos los siguientes personajes:");
        }
        for (Personajes personaje : jugador) {
            Main.archivo.aniadirTexto("-   "+personaje.nombre+" con "+Main.obtenerDosDecimales(personaje.salud)+" de salud.");
        }
        Main.archivo.aniadirTexto("");
    }

    public int ingresarInt (String mensajeTry, double min, double max, String mensajeException) {
        int numero;
        while (true) {
            try {
                System.out.print(mensajeTry);
                int numeroIngresado = scanner.nextInt();
                if (numeroIngresado >= min && numeroIngresado <= max) {
                    scanner.nextLine();
                    numero = numeroIngresado;
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println(mensajeException);
                scanner.nextLine();
            }
        }
        return numero;
    }

    public String ingresarStringRegex (String mensajeTry, String regex,String mensajeException) {
        String string;
        while (true) {
            try {
                System.out.print(mensajeTry);
                String stringIngresado = scanner.nextLine();
                if (stringIngresado.matches(regex)) {
                    string = stringIngresado;
                    break;
                } else {
                    System.out.println(mensajeException);
                }
            } catch (IllegalArgumentException e) {
                scanner.nextLine();
            }
        }
        return string;
    }
}
