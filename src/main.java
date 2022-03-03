import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.*;
import java.util.Scanner;

public class main {

    private static String hashage_mdp(String mdp) throws DigestException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] mdp_tab = md.digest(mdp.getBytes());
            BigInteger no = new BigInteger(1, mdp_tab);
            String hashed = no.toString(16);
            while (hashed.length() < 32) {
                hashed = "0" + hashed;
            }
            return hashed;
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new DigestException("erreur Digest");
        }
    }

    private static String findCollision(String mdp) throws DigestException {
        boolean trouve = false;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] mdp_tab = md.digest(mdp.getBytes());
            int bits = (mdp_tab[0] % (1 << 9));
            File liste = new File("src/List");
            Scanner myReader = new Scanner(liste);
            trouve = false;
            int compteur = 1;
            while (myReader.hasNext() && trouve == false) {
                String mot = myReader.nextLine();
                byte[] text_to_byte = md.digest(mot.getBytes());
                if (text_to_byte[0] == bits) {
                    trouve = true;
                    String collision = "Le mot sur lequel se prosuit la collision est : " + mot + ", " + "Au bout de " + compteur;
                    System.out.println(collision);
                }
                compteur += 1;
            }
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new DigestException("erreur Digest");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String args[]) throws DigestException{
        System.out.println(findCollision("Salut"));


    }



}




