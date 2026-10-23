//definitiu

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
        String[] proves = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String[] xifrades = new String[proves.length];

        System.out.println("Xifrat");
        System.out.println("------");
        for (int i = 0; i < proves.length; i++) {
            xifrades[i] = xifraRot13(proves[i]);
            System.out.printf("%-25s => %s%n", proves[i], xifrades[i]);
        }

        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (int i = 0; i < xifrades.length; i++) {
            System.out.printf("%-25s => %s%n", xifrades[i], desxifraRot13(xifrades[i]));
        }
    }

    public static String xifraRot13(String frase) {
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
    public static String desxifraRot13(String frase) {
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
}   



