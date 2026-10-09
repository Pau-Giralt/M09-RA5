package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {

    private static final String MAJUSCULES = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private static final char[] ALFABET = MAJUSCULES.toCharArray();
    private char[] clave;

    public XifradorMonoalfabetic() {
        clave = permutaAlfabet(ALFABET);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        comprovaClau(clau);
        String resultat = xifraMonoAlfa(msg);
        return new TextXifrat(resultat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        comprovaClau(clau);
        String msgXifrat = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
        return desxifraMonoAlfa(msgXifrat);
    }

    private void comprovaClau(String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
    }

    public char[] permutaAlfabet(char[] alfabet) {
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

    public char xifraCaracter(char c) {
        boolean esMinus = Character.isLowerCase(c);
        char cUp = Character.toUpperCase(c);
        for (int i = 0; i < ALFABET.length; i++) {
            if (ALFABET[i] == cUp) {
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

    public char desxifraCaracter(char c) {
        boolean esMinus = Character.isLowerCase(c);
        char cUp = Character.toUpperCase(c);
        for (int i = 0; i < clave.length; i++) {
            if (clave[i] == cUp) {
                char res = ALFABET[i];
                if (esMinus) {
                    return Character.toLowerCase(res);
                } else {
                    return res;
                }
            }
        }
        return c; // espacio, coma, número... se devuelve igual
    }

    public String xifraMonoAlfa(String frase) {
        char[] arrayFrase = frase.toCharArray();
        for (int i = 0; i < arrayFrase.length; i++) {
            arrayFrase[i] = xifraCaracter(arrayFrase[i]);
        }
        return new String(arrayFrase);
    }

    public String desxifraMonoAlfa(String frase) {
        char[] arrayFrase = frase.toCharArray();
        for (int i = 0; i < arrayFrase.length; i++) {
            arrayFrase[i] = desxifraCaracter(arrayFrase[i]);
        }
        return new String(arrayFrase);
    }
}
