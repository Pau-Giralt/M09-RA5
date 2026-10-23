//definitivo

public class RotX {

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
        int[] desplacaments = {0, 2, 4, 6};
        String[] xifrades = new String[proves.length];

        System.out.println("Xifrat");
        System.out.println("------");
        for (int i = 0; i < proves.length; i++) {
            xifrades[i] = xifraRotX(proves[i], desplacaments[i]);
            System.out.printf("(%d)-%-25s => %s%n", desplacaments[i], proves[i], xifrades[i]);
        }

        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("---------");
        for (int i = 0; i < xifrades.length; i++) {
            System.out.printf("(%d)-%-25s => %s%n", desplacaments[i], xifrades[i],
                    desxifraRotX(xifrades[i], desplacaments[i]));
        }

        // Força bruta sobre l'últim missatge XIFRAT
        String missatgeXifrat = xifrades[xifrades.length - 1];
        System.out.println();
        System.out.printf("Missatge xifrat: %s%n", missatgeXifrat);
        System.out.println("----------------");
        forcaBrutaRotX(missatgeXifrat);
    }

    public static String xifraRotX(String frase, int desplazament) {
        char[] xifrada  = frase.toCharArray();
        for (int i = 0; i < xifrada.length; i++) {
            char c = xifrada[i];
            
            if (Character.isLowerCase(c)) {
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == c) {
                        xifrada[i] = minuscules[(j + desplazament) % minuscules.length];
                    }
                }
            }else if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == c) {
                        xifrada[i] = majuscules[(j + desplazament) % majuscules.length];
                    }
                }
            }
        }
        return new String(xifrada);
    
    }
    public static String desxifraRotX(String frase, int desplazament) {
        char[] desxifrada = frase.toCharArray();
        for (int i = 0; i < desxifrada.length; i++) {
            char c = desxifrada[i];
            if (Character.isLowerCase(c)) {
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == c) {
                        desxifrada[i] = minuscules[(j - desplazament + minuscules.length) % minuscules.length];
                    }
                }
            }else if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == c) {
                        desxifrada[i] = majuscules[(j - desplazament + majuscules.length) % majuscules.length];
                    }
                }
            }
        }
        return new String(desxifrada);
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {
            
            for (int i = 0; i < majuscules.length; i++) {  
                String resultado = desxifraRotX(cadenaXifrada, i);
                System.out.printf("(%d)->%s%n",i, resultado); 
            } 
      
    }
}   



