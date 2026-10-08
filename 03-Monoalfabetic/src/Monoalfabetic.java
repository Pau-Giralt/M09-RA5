import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Monoalfabetic {

    private static final String frase = "Hola buenos dias";
    
    private static final String majuscules = ("AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ");
    private static final char[] alfabet = majuscules.toCharArray();
    private static final char[] clave = permutaAlfabet(alfabet);
    public static void main(String[] args) {
        System.out.println(alfabet);
        System.out.println(clave);

        System.out.println("\nXifratge:");
        String t1 = "àrbritre, coixí, Perímetre";
        String t2 = "Taüll, DÍA, año";
        String t3 = "Peça, Òrrius, Bòvila";


        System.out.println("Test 01 " + t1 + " -> " + xifraMonoAlfa(t1));
        System.out.println("Test 02 " + t2 + " -> " + xifraMonoAlfa(t2));
        System.out.println("Test 03 " + t3 + " -> " + xifraMonoAlfa(t3));

        System.out.println("\nDesxifratge:");
        System.out.println(xifraMonoAlfa(t1) + " -> " + desxifraMonoAlfa(xifraMonoAlfa(t1)));
        System.out.println(xifraMonoAlfa(t2) + " -> " + desxifraMonoAlfa(xifraMonoAlfa(t2)));
        System.out.println(xifraMonoAlfa(t3) + " -> " + desxifraMonoAlfa(xifraMonoAlfa(t3)));
    }
    public static char[] permutaAlfabet(char[] alfabet) {
        List<Character> permutado = new ArrayList<>();
        for (char c : alfabet) {
            permutado.add(c);
        }
        Collections.shuffle(permutado);
        char[] resultado = new char[alfabet.length];
        for (int i = 0; i < permutado.size(); i++) {
            resultado[i] = permutado.get(i);
        }
        return resultado;
    }

        public static char xifraCaracter(char c) {
            boolean esMinus = Character.isLowerCase(c);
            char cUp = Character.toUpperCase(c);
            for (int i = 0; i < alfabet.length; i++) {
                if (alfabet[i] == cUp) {
                    char res = clave[i];
                    if (esMinus) {
                        return Character.toLowerCase(res);
                    } else {
                        return res;
                    }
                }
            }
            return c;  
        }
        public static char desxifraCaracter(char c) {
            boolean esMinus = Character.isLowerCase(c);
            char cUp = Character.toUpperCase(c);
            for (int i = 0; i < clave.length; i++) {
                if (clave[i] == cUp) {
                    char res = alfabet[i];
                    if (esMinus) {
                        return Character.toLowerCase(res);
                    } else {
                        return res;
                    }
                }
            }
            return c;  // espacio, coma, número... se devuelve igual
        }
    
    public static String xifraMonoAlfa(String frase) {
       char[] arrayFrase = frase.toCharArray();
       for (int i = 0; i < arrayFrase.length; i++) {
           arrayFrase[i] = xifraCaracter(arrayFrase[i]);
       }
       return new String(arrayFrase);
    }

    public static String desxifraMonoAlfa(String frase) {
        char[] arrayFrase = frase.toCharArray();
        for (int i = 0; i < arrayFrase.length; i++) {
            arrayFrase[i] = desxifraCaracter(arrayFrase[i]);
        }
        return new String(arrayFrase);
    }
}
