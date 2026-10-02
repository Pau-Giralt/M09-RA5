import java.util.Random;

public class Polialfabetic {

    public static final char[] ALFABET = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();
 
  
    public static char[] alfabetPermutat = new char[ALFABET.length];
 

    public static final long clauSecreta = 12345L;
    public static Random random;
            

    

    public static void main(String[] args) {
    String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
                     "Test 02 Taüll, DÍA, año",
                     "Test 03 Peça, Òrrius, Bòvila"};
    String msgsXifrats[] = new String[msgs.length];

    System.out.println("Xifratge:\n--------");
    for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
        System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
    }

    System.out.println("Desxifratge:\n-----------");
    for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        String msg = desxifraPoliAlfa(msgsXifrats[i]);
        System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
    }

}

    public static void initRandom(long clau) {
        random = new Random(clau);
    }


    public static void permutaAlfabet() {

        for (int i = 0; i < ALFABET.length; i++) {
            alfabetPermutat[i] = ALFABET[i];
        }

        for (int i = alfabetPermutat.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = alfabetPermutat[i];
            alfabetPermutat[i] = alfabetPermutat[j];
            alfabetPermutat[j] = temp;
        }
    }
    public static int posicio(char[] alfabet, char c) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

public static String xifraPoliAlfa(String msg) {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            boolean esMajuscula = Character.isUpperCase(c);
            char minuscula = Character.toLowerCase(c);
            permutaAlfabet();
            int pos = posicio(ALFABET, minuscula);
            if (pos != -1) {
                char nova = alfabetPermutat[pos];
                if (esMajuscula) {
                    nova = Character.toUpperCase(nova);
                }
                resultat.append(nova);
            } else {
                resultat.append(c); 
            }
        }
        return resultat.toString();
    }
    public static String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder resultat = new StringBuilder();
        for (int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);
            boolean esMajuscula = Character.isUpperCase(c);
            char minuscula = Character.toLowerCase(c);
            permutaAlfabet();
            int pos = posicio(alfabetPermutat, minuscula);
            if (pos != -1) {
                char nova = ALFABET[pos];
                if (esMajuscula) {
                    nova = Character.toUpperCase(nova);
                }
                resultat.append(nova);
            } else {
                resultat.append(c);
            }
        }
        return resultat.toString();
    }
    


}
