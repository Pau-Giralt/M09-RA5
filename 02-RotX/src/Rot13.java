import java.util.Scanner;


public class Rot13 {
    
    static char [] minuscules = {
       'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h',
       'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò',
       'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
        };
    static char [] majuscules = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H',
        'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò',
        'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Insereix la frase: ");
            String frase = scanner.nextLine();
            
            System.out.print("1 - Xifrar en Rot13\n2 - Desxifrar en Rot13\n");
            int opcio = scanner.nextInt();
            if (opcio == 1) {
                System.out.println("Frase xifrada: " + xifraRot13(frase));
            } else if (opcio == 2) {
                System.out.println("Frase desxifrada: " + desxifraRot13(frase));
            } else {
                System.out.println("Opció no vàlida.");
            }
            scanner.close();
        }
    
    public static String xifraRotX(String frase) {
        char[] xifrada  = frase.toCharArray();
        for (int i = 0; i < xifrada.length; i++) {
            char c = xifrada[i];
            
            if (Character.isLowerCase(c)) {
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == c) {
                        xifrada[i] = minuscules[(j + 13) % minuscules.length];
                    }
                }
            }else if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == c) {
                        xifrada[i] = majuscules[(j + 13) % majuscules.length];
                    }
                }
            }
        }
        return new String(xifrada);
    
    }
    public static String desxifraRotX(String frase) {
        char[] desxifrada = frase.toCharArray();
        for (int i = 0; i < desxifrada.length; i++) {
            char c = desxifrada[i];
            if (Character.isLowerCase(c)) {
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == c) {
                        desxifrada[i] = minuscules[(j - 13 + minuscules.length) % minuscules.length];
                    }
                }
            }else if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == c) {
                        desxifrada[i] = majuscules[(j - 13 + majuscules.length) % majuscules.length];
                    }
                }
            }
        }
        return new String(desxifrada);
    }

    public static String xifraRotX(String cadenaXifrada) {
}   



