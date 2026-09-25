//definitivo

public class Rot13 {

    static String frase = "Hola buenos dias";
    static int desplazament = 10;
    
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
            String resultado = xifraRotX(frase, desplazament);
            System.out.println("Xifrat\n------");
            System.out.printf("(%d)-%-5s => %s%n%n", desplazament, frase, resultado);
            String resultado2 = desxifraRotX(resultado, desplazament);
            System.out.println("Desxifrat\n------");
            System.out.printf("(%d)-%-5s => %s%n%n", desplazament, resultado, resultado2);
            System.out.printf("Missatge xifrat: %s\n-----------------\n", resultado2);
            forsaBrutaRotX(resultado);

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

    public static void forsaBrutaRotX(String cadenaXifrada) {
            
            for (int i = 0; i < majuscules.length; i++) {  
                String resultado = desxifraRotX(cadenaXifrada, i);
                System.out.printf("(%d)->%s%n",i, resultado); 
            } 
      
    }
}   



