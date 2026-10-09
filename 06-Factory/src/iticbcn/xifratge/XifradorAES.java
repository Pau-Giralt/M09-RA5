package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            return new TextXifrat(xifraAES(msg, clau));
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error de desxifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    public byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] bMsg = msg.getBytes(StandardCharsets.UTF_8);

        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] bClau = sha.digest(clau.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec clauSecreta = new SecretKeySpec(bClau, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, clauSecreta, ivSpec);
        byte[] bXifrat = cipher.doFinal(bMsg);

        byte[] resultat = new byte[MIDA_IV + bXifrat.length];
        System.arraycopy(iv, 0, resultat, 0, MIDA_IV);
        System.arraycopy(bXifrat, 0, resultat, MIDA_IV, bXifrat.length);

        return resultat;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        byte[] bIv = new byte[MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, 0, bIv, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(bIv);

        int midaXifrat = bIvIMsgXifrat.length - MIDA_IV;
        byte[] bXifrat = new byte[midaXifrat];
        System.arraycopy(bIvIMsgXifrat, MIDA_IV, bXifrat, 0, midaXifrat);

        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] bClau = sha.digest(clau.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec clauSecreta = new SecretKeySpec(bClau, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, clauSecreta, ivSpec);
        byte[] bDesxifrat = cipher.doFinal(bXifrat);

        return new String(bDesxifrat, StandardCharsets.UTF_8);
    }
}
