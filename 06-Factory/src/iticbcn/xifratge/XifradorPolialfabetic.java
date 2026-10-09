package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    public static final char[] ALFABET = "aàáäbcçdeèéëfghiìíïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();

    private char[] alfabetPermutat = new char[ALFABET.length];
    private Random random;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauSecreta;
        try {
            clauSecreta = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(clauSecreta);
        String resultat = xifraPoliAlfa(msg);
        return new TextXifrat(resultat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long clauSecreta;
        try {
            clauSecreta = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        initRandom(clauSecreta);
        String msgXifrat = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
        return desxifraPoliAlfa(msgXifrat);
    }

    public void initRandom(long clau) {
        random = new Random(clau);
    }

    public void permutaAlfabet() {
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

    public int posicio(char[] alfabet, char c) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public String xifraPoliAlfa(String msg) {
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

    public String desxifraPoliAlfa(String msgXifrat) {
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
