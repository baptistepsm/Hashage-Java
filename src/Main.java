import java.io.File;
import java.io.FileNotFoundException;
import java.security.*;
import java.util.HexFormat;
import java.util.Scanner;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Main {

    public static void hashPassword(String passwd) throws DigestException //Cette méthode permet de hasher un mot de passe en SHA-1 avec le mot de passe à hasher entré en paramètre
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] passwdArray = md.digest(passwd.getBytes()); //On hashe le mot de passe en un tableau de bytes
            HexFormat hexa = HexFormat.of();
            System.out.println("Votre mot hashé est : " + hexa.formatHex(passwdArray)); //On transforme le résultat en hexadécimal et on l'affiche
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException)
        {
            throw new DigestException("erreur Digest");
        }
    }

    public static void findCollision(String passwd) throws DigestException //Cette méthode permet de trouver des collisions entre un mot de passe hashé et un mot existant dans le dictionnaire
    {
        boolean found;
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] passwdArray = md.digest(passwd.getBytes()); //On hashe le mot de passe en un tableau de bytes
            int nb_bits;
            Scanner input = new Scanner(System.in);
            System.out.println("Combien de bits voulez vous extraire ? (5/6/7/8/9/10) : ");
            int answer = Integer.parseInt(input.nextLine()); //l'utilisateur ici entre le nombre de bits à extraire afin de trouver des collisions entre le hashé et la liste de mots
            int bits;
            int bits2;
            found = false; //initialisation des variables nécessaires
            int compteur = 1;
            if (answer == 5 || answer == 6 || answer == 7 || answer == 8) //on rentre dans cette condition si l'utilisateur veut entre 5 et 8 bits ce qui correspond à la première case du tableau (1 octet)
            {
                nb_bits = answer;
                bits = (passwdArray[0] % (1 << nb_bits)); //on extrait les premiers bits du mot hashé
                File liste = new File("src/List"); //On intègre la liste de mots dans le programme
                Scanner myReader = new Scanner(liste);
                while (myReader.hasNext() && !found) //on lit la liste tant qu'une collision n'a pas été trouvée
                {
                    String mot = myReader.nextLine();
                    byte[] text_to_byte = md.digest(mot.getBytes()); //on hashe le mot actuel de la liste
                    if ((text_to_byte[0] % (1 << nb_bits)) == bits) //on cherche ici un mot de la liste qui aurait les memes premiers bits que le mot hashé. Si une collision est trouvée, on s'arrête et on renvoie le résultat
                    {
                        found = true;
                        System.out.println("Le mot sur lequel se prosuit la collision est : " + mot + ", " + "Au bout de " + compteur);
                    }
                    compteur += 1;
                }
            }
            else if (answer == 9 || answer == 10){//on rentre dans cette condition si l'utilisateur veut extraire 9 ou 10 bits ce qui correspond à la première et une partie de la seconde case du tableau
                nb_bits = answer;
                bits = (passwdArray[0] % (1 << nb_bits)); //on extrait le premier octet du mot hashé
                bits2 = (passwdArray[1] % (1 << nb_bits-8)); //on extrait la suite du mot hashé
                File liste = new File("src/List"); //On intègre la liste de mots dans le programme
                Scanner myReader = new Scanner(liste);
                while (myReader.hasNext() && !found) //on lit la liste tant qu'une collision n'a pas été trouvée
                {
                    String mot = myReader.nextLine();
                    byte[] text_to_byte = md.digest(mot.getBytes()); //on hashe le mot actuel de la liste
                    if (text_to_byte[0] == bits && (text_to_byte[1] % (1 << nb_bits-8)) == bits2) //on cherche ici un mot de la liste qui aurait les memes premiers bits que le mot hashé. Si une collision est trouvée, on s'arrête et on renvoie le résultat
                    {
                        found = true;
                        String collision = "Le mot sur lequel se produit la collision est : " + mot + ", " + "Au bout de " + compteur;
                        System.out.println(collision);
                    }
                    compteur += 1;
                }
            }

        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException)
        {
            throw new DigestException("erreur Digest");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void hmacGeneration(String message, String key) //Cette méthode permet de générer un HMAC en SHA-1
    {
        try
        {
            Mac hmac = Mac.getInstance("HmacSHA1"); //Utilisation de SHA-1 il suffit simplement de mettre HmacSHA512 pour hasher en SHA-512 (plus sécurisé)
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "SHA-1"); //Utilisation de la clé fournie en paramètre
            hmac.init(keySpec); //génération de la clé
            byte[] result = hmac.doFinal(message.getBytes()); //On obtient ici le résultat du hashage sous forme de tableau de bytes
            //System.out.println(Arrays.toString(result));
            HexFormat hexa = HexFormat.of();
            System.out.println("Le Hmac en hexadecimal sera : " + hexa.formatHex(result)); //on affiche le résultat en hexadécimal

        }
        catch (InvalidKeyException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DigestException{
        hashPassword("MessageHashe"); //Ex1 Q1
        findCollision("poisson"); //EX1
        hmacGeneration("Ceci est mon premier HMAC SHA1", "CleCompliquee"); //EX2
    }
}