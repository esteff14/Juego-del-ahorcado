import java.util.Scanner;
import java.util.Random;

public class Ahorcado {
    static int option;

    public static void main(String[] args) {
        final int num = 10;
        boolean palabraDescubierta;
        int intentosFallidos;
        String palabraCamuflada, letra, letraFallida, palabra, letraBien;
        String[] grupoPalabras = {"termometro", "departamento", "pichincha",
                "esternocleidomastoideo", "elefante", "naranja", "martillo", "clavicembalo", "terremoto", "infinito"};

        Scanner scanner = new Scanner(System.in);

        do {
            palabra = "";
            palabraCamuflada = "";
            letraFallida = "";
            letraBien = "";
            intentosFallidos = 0;
            menu();
            if (option == 2) {
                break;
            }

            while (option > 2 || option < 1) {
                menu();
            }
            Random rand = new Random();
            int i = rand.nextInt(num);
            palabra = grupoPalabras[i];

            System.out.println(palabra);

            for (int j = 0; j < palabra.length(); j++) {
                palabraCamuflada += "-";
            }

            System.out.println("\n\nJugando......");
            System.out.println("La palabra seleccionada en esta partida tiene " + palabraCamuflada.length() +
                    " letras: " + palabraCamuflada);

            while (intentosFallidos < 7 && !palabraCamuflada.equals(palabra)) {  // Cambio aquí a 7 intentos
                System.out.print("Adivine la letra: ");
                letra = scanner.next();
                letra = letra.toLowerCase();

                while (letraBien.contains(letra) || letraFallida.contains(letra)) {
                    System.out.println("Ya escribiste esa letra...");
                    System.out.print("Adivine la letra: ");
                    letra = scanner.next();
                    letra = letra.toLowerCase();
                }

                int posicion = palabra.indexOf(letra);

                if (posicion != -1) {
                    while (posicion != -1) {
                        palabraCamuflada = palabraCamuflada.substring(0, posicion) + letra +
                                palabraCamuflada.substring(posicion + 1);
                        posicion = palabra.indexOf(letra, posicion + 1);
                    }
                    System.out.println("\nPalabra: " + palabraCamuflada);
                    letraBien += letra;
                } else {
                    System.out.println("\nFallaste, la letra no está en la palabra...");
                    intentosFallidos += 1;
                    letraFallida += letra;
                    System.out.println("Letras equivocadas: " + letraFallida);
                    System.out.println("Llevas " + intentosFallidos + " equivocaciones....");
                    System.out.println("\n\nPalabra: " + palabraCamuflada);

                    if (intentosFallidos == 1) {
                        System.out.println("Dibujaré la cabeza...\n\n  O  \n");
                    } else if (intentosFallidos == 2) {
                        System.out.println("Dibujaré el pectoral...\n\n  O  \n  |  \n");
                    } else if (intentosFallidos == 3) {
                        System.out.println("Dibujaré el brazo...\n\n   O  \n / |   \n");
                    } else if (intentosFallidos == 4) {
                        System.out.println("Dibujaré el otro brazo...\n\n   O  \n / | \\  \n");
                    } else if (intentosFallidos == 5) {
                        System.out.println("Dibujaré la pierna...\n\n   O  \n / | \\  \n  /  \n");
                    } else if (intentosFallidos == 6) {
                        System.out.println("Dibujaré la otra pierna...\n\n   O  \n / | \\  \n  / \\ \n");
                        System.out.println("Perdiste, suerte para la próxima......");
                        System.out.println("La palabra era: " + palabra);
                    }
                }
                if (palabraCamuflada.equals(palabra)) {
                    System.out.println("¡Felicidades! Acabas de ganar.");
                }
            }

            letraBien = "";
            letraFallida = "";
            intentosFallidos = 0;

        } while (true);
    }

    static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n-----------Juego ahorcado----------");
        System.out.println("1. Jugar");
        System.out.println("2. Salir");
        System.out.print("Elija una opción: ");
        option = scanner.nextInt();
    }
}