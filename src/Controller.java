import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    Scanner scanner = new Scanner(System.in);
    public void mostrarFelicitaciones(int jugador) {
        System.out.println();
        System.out.println("==============================================================================");
        System.out.println("                          Felicitaciones!!!");
        System.out.println(" Terminó el juego, el ganador y merecedor del Trono de Hierro es el jugador "+jugador);
        System.out.println("==============================================================================");
        System.out.println();
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
