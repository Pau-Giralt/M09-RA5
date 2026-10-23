import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.HexFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    // Charset i límits definits a l'enunciat
    static final String CHARSET = "abcdefABCDEF1234567890!";
    static final int LONGITUD_MAXIMA = 6;

    // Paràmetres de PBKDF2
    static final int PBKDF2_ITERACIONS = 100000;
    static final int PBKDF2_LONGITUD_BITS = 128;

    // Comptador de passwords provats a la força bruta
    int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes("UTF-8"));
        byte[] bytes = md.digest(pw.getBytes("UTF-8"));
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes("UTF-8"),
                PBKDF2_ITERACIONS, PBKDF2_LONGITUD_BITS);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] bytes = factory.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    // Mètode auxiliar: calcula el hash segons l'algorisme indicat
    private String calculaHash(String alg, String pw, String salt) throws Exception {
        if (alg.equals("SHA-512")) {
            return getSHA512AmbSalt(pw, salt);
        } else {
            return getPBKDF2AmbSalt(pw, salt);
        }
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        final AtomicInteger comptador = new AtomicInteger(0);   // npass compartit
        final AtomicReference<String> trobat = new AtomicReference<>(null);
        int nFils = Runtime.getRuntime().availableProcessors();
        Thread[] fils = new Thread[nFils];

        for (int t = 0; t < nFils; t++) {
            final int idFil = t;
            fils[t] = new Thread(() -> {
                char[] cs = CHARSET.toCharArray();
                char[] pw = new char[LONGITUD_MAXIMA];
                long index = 0;  // índex global del candidat dins l'enumeració

                // Un bucle niuat per cada posició de la contrasenya (1..6)
                for (int a = 0; a < cs.length; a++) { pw[0] = cs[a];
                for (int b = 0; b < cs.length; b++) { pw[1] = cs[b];
                for (int c = 0; c < cs.length; c++) { pw[2] = cs[c];
                for (int d = 0; d < cs.length; d++) { pw[3] = cs[d];
                for (int e = 0; e < cs.length; e++) { pw[4] = cs[e];
                for (int f = 0; f < cs.length; f++) { pw[5] = cs[f];
                    if (trobat.get() != null) return;      // ja l'ha trobat un altre fil
                    if (index % nFils == idFil) {          // repartiment entre fils
                        comptador.incrementAndGet();
                        String intent = new String(pw);
                        try {
                            if (calculaHash(alg, intent, salt).equals(hash)) {
                                trobat.set(intent);
                                return;
                            }
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    index++;
                }}}}}}
            });
        }

        for (Thread f : fils) f.start();
        for (Thread f : fils) f.join();

        npass = comptador.get();
        return trobat.get();
    }

    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long segons = millis / 1000;  millis %= 1000;
        long minuts = segons / 60;    segons %= 60;
        long hores  = minuts / 60;    minuts %= 60;
        long dies   = hores / 24;     hores  %= 24;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                dies, hores, minuts, segons, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("== ======\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("-- ----------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("-----\n\n");
        }
    }
}
