import java.util.Random;

public class Polialfabetic {


            private static final String majuscules = ("AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ");
            private static final char[] alfabet = majuscules.toCharArray();
            private static final long clauSecreta = 123456789L;
            
            static Random random = new Random(clauSecreta);

    static char[] alfabetPermutado;
    private static void permutaAlfabet(){
        alfabetPermutado = alfabet.clone();

        for(int i = 0; i < alfabetPermutado.length; i++){
            int j = random.nextInt(alfabetPermutado.length);
            char temp = alfabetPermutado[i];
            alfabetPermutado[i] = alfabetPermutado[j];
            alfabetPermutado[j] = temp;
            }
        }

    

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

    public static String xifraPoliAlfa(String msg){

    }
    public static String desxifraPoliAlfa(String msgXifrat){
     return "msgXifrat";   
    }



}
