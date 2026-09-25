import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Monoalfabetic {

    private static final String frase = "Hola buenos dias";
    private static final int desplazament = 10;
    
    private static final String majuscules = ("AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ");
    private static final char[] alfabet = majuscules.toCharArray();
     
        public static void main(String[] args) {
            permutaAlfabet(alfabet);

        }
    public static char[] permutaAlfabet(char[] alfabet) {
        List<Character> permutado = new ArrayList<>();
        for (char c : alfabet) {
            permutado.add(c);
        }
        Collections.shuffle(permutado);
        for (int i = 0; i < permutado.size(); i++) {
            alfabet[i] = permutado.get(i);
        }
        return alfabet;
    }

    public static String xifraMonoAlfa(String frase) {
       
        for (int i = 0; i < alfabet.length; i++) {
            char c = alfabet[i];

            if (Character.isLowerCase(c)) {
                for (int j = 0; j < alfabet.length; j++) {
                    if (Character.toLowerCase(alfabet[j]) == c) {
                        alfabet[i] = Character.toLowerCase(alfabet[(j + desplazament) % alfabet.length]);
                    }
                }
            }else if (Character.isUpperCase(c)) {
                for (int j = 0; j < alfabet.length; j++) {
                    if (alfabet[j] == c) {
                        alfabet[i] = alfabet[(j + desplazament) % alfabet.length];
                    }
                }
            }
        }
        return new String(alfabet);
    }

    public static String desxifraMonoAlfa(String frase) {
        for (int i = 0; i < alfabet.length; i++) {
            char c = alfabet[i];

            if (Character.isLowerCase(c)) {
                for (int j = 0; j < alfabet.length; j++) {
                    if (Character.toLowerCase(alfabet[j]) == c) {
                        alfabet[i] = Character.toLowerCase(alfabet[(j - desplazament) % alfabet.length]);
                    }
                }
            }else if (Character.isUpperCase(c)) {
                for (int j = 0; j < alfabet.length; j++) {
                    if (alfabet[j] == c) {
                        alfabet[i] = alfabet[(j - desplazament) % alfabet.length];
                    }
                }
            }
        }
        return new String(alfabet);
    }

}

