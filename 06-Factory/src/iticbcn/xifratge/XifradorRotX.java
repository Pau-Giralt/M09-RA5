package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class XifradorRotX implements Xifrador {

    private static final char[] MINUSCULES = {
        'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h',
        'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò',
        'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'
    };
    private static final char[] MAJUSCULES = {
        'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H',
        'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò',
        'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplazament = obtenirDesplazament(clau);
        String resultat = xifraRotX(msg, desplazament);
        return new TextXifrat(resultat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplazament = obtenirDesplazament(clau);
        String msgXifrat = new String(xifrat.getBytes(), StandardCharsets.UTF_8);
        return desxifraRotX(msgXifrat, desplazament);
    }

    private int obtenirDesplazament(String clau) throws ClauNoSuportada {
        try {
            int desplazament = Integer.parseInt(clau);
            if (desplazament < 0 || desplazament > 40) {
                throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            }
            return desplazament;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    public String xifraRotX(String frase, int desplazament) {
        char[] xifrada = frase.toCharArray();
        for (int i = 0; i < xifrada.length; i++) {
            char c = xifrada[i];

            if (Character.isLowerCase(c)) {
                for (int j = 0; j < MINUSCULES.length; j++) {
                    if (MINUSCULES[j] == c) {
                        xifrada[i] = MINUSCULES[(j + desplazament) % MINUSCULES.length];
                    }
                }
            } else if (Character.isUpperCase(c)) {
                for (int j = 0; j < MAJUSCULES.length; j++) {
                    if (MAJUSCULES[j] == c) {
                        xifrada[i] = MAJUSCULES[(j + desplazament) % MAJUSCULES.length];
                    }
                }
            }
        }
        return new String(xifrada);
    }

    public String desxifraRotX(String frase, int desplazament) {
        char[] desxifrada = frase.toCharArray();
        for (int i = 0; i < desxifrada.length; i++) {
            char c = desxifrada[i];
            if (Character.isLowerCase(c)) {
                for (int j = 0; j < MINUSCULES.length; j++) {
                    if (MINUSCULES[j] == c) {
                        desxifrada[i] = MINUSCULES[(j - desplazament + MINUSCULES.length) % MINUSCULES.length];
                    }
                }
            } else if (Character.isUpperCase(c)) {
                for (int j = 0; j < MAJUSCULES.length; j++) {
                    if (MAJUSCULES[j] == c) {
                        desxifrada[i] = MAJUSCULES[(j - desplazament + MAJUSCULES.length) % MAJUSCULES.length];
                    }
                }
            }
        }
        return new String(desxifrada);
    }

    public void forsaBrutaRotX(String cadenaXifrada) {
        for (int i = 0; i < MAJUSCULES.length; i++) {
            String resultado = desxifraRotX(cadenaXifrada, i);
            System.out.printf("(%d)->%s%n", i, resultado);
        }
    }
}
