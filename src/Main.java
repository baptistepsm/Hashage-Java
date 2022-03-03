import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.security.*;
import java.util.Scanner;

public class Main {

    public static void hashage_mdp(String mdp) throws DigestException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] mdp_tab = md.digest(mdp.getBytes());
            BigInteger no = new BigInteger(1, mdp_tab);
            String hashed = no.toString(16);
            while (hashed.length() < 32) {
                hashed = "0" + hashed;
            }
            System.out.println(hashed);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new DigestException("erreur Digest");
        }
    }

    public static void findCollision(String mdp) throws DigestException {
        boolean trouve;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] mdp_tab = md.digest(mdp.getBytes());
            int nb_bits = 5;
            Scanner input = new Scanner(System.in);
            System.out.println("Combien de bits voulez vous extraire ? (5/6/7/8/9/10) : ");
            int reponse = Integer.parseInt(input.nextLine());
            int bits;
            int bits2;
            trouve = false;
            int compteur = 1;
            if (reponse == 5 || reponse == 6 || reponse == 7 || reponse == 8){
                nb_bits = reponse;
                bits = (mdp_tab[0] % (1 << nb_bits));
                File liste = new File("src/List");
                Scanner myReader = new Scanner(liste);
                while (myReader.hasNext() && !trouve) {
                    String mot = myReader.nextLine();
                    byte[] text_to_byte = md.digest(mot.getBytes());
                    if ((text_to_byte[0] % (1 << nb_bits)) == bits) {
                        trouve = true;
                        String collision = "Le mot sur lequel se prosuit la collision est : " + mot + ", " + "Au bout de " + compteur;
                        System.out.println(collision);
                    }
                    compteur += 1;
                }
            }
            else if (reponse == 9 || reponse == 10){
                nb_bits = reponse;
                bits = (mdp_tab[0] % (1 << nb_bits));
                bits2 = (mdp_tab[1] % (1 << nb_bits-8));
                File liste = new File("src/List");
                Scanner myReader = new Scanner(liste);
                while (myReader.hasNext() && !trouve) {
                    String mot = myReader.nextLine();
                    byte[] text_to_byte = md.digest(mot.getBytes());
                    if (text_to_byte[0] == bits && (text_to_byte[1] % (1 << nb_bits-8)) == bits2) {
                        trouve = true;
                        String collision = "Le mot sur lequel se produit la collision est : " + mot + ", " + "Au bout de " + compteur;
                        System.out.println(collision);
                    }
                    compteur += 1;
                }
            }

        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new DigestException("erreur Digest");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DigestException{
        hashage_mdp("poisson");
        findCollision("poisson");


    }



}




