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

    public static char xifraCaracter(char c) {
        c = Character.toUpperCase(c);
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == c) {
                return clave[i];
            }
        }
        return clave[0];  //por si no encuentra ninguna
    }
        public static char desxifraCaracter(char c) {
        c = Character.toUpperCase(c);
        for (int i = 0; i < clave.length; i++) {
            if (clave[i] == c) {
                return alfabet[i];
            }
        }
        return clave[0];  //por si no encuentra ninguna
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
