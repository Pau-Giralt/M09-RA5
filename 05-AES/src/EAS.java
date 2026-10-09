import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EAS {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[16];
    private static final String CLAU = "LaClauSecretaQueVulguis";

   public static void main(String[] args) {
        String msgs[] = { "Lorem ipsum dicet",
                "Hola Andrés cómo está tu cuñado",
                "Àgora illa Òtto" };
 
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
 
            byte[] bXifratas = null;
            String desxifrat = "";
            try {
                bXifratas = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifratas, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }
 
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifratas));
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception {
    
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

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
       
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