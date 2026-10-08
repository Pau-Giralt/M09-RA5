import java.security.*;
import javax.crypto.*;

public class EAS {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";  
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte [16];
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


    public static byte[] xifraAES(String msg, String clau) throws Exception{

    //Obtenir els bytes de l’String

    // Genera IvParameterSpec

    // Genera hash

    // Encrypt.

    // Combinar IV i part xifrada.

    // return iv+msgxifrat
        }

        public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

    // Extreure 1'IV.

    // Extreure la part xifrada.

    // Fer hash de la clau

    // Desxifrar.

    //return String desxifrat
        }

}


