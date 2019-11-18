package data;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Data {
    private final static String VERSION = "Beta: 1.1";
    private static String ACLicence = "";

    public Data() {

    }

    public static String getVERSION() {
        return VERSION;
    }


    public static boolean checkLicence(String licence) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] byteArray = licence.getBytes("UTF-8");
        byte[] md5Digest = md.digest(byteArray);

        String hash = DatatypeConverter
                .printHexBinary(md5Digest).toUpperCase();

        //System.out.println(hash);


        String read = new Scanner(new URL("http://jacgri9.dreamhosters.com/licence/licences").openStream(), "UTF-8").useDelimiter("\\A").next();
        String[] licences = read.split("\\r?\\n");

        for (String a : licences) {
            System.out.println(a);
            if (a.equals(hash)) {
                System.out.println("Licence is correct!");
                ACLicence = licence;
                writeLicenceToFile();
                return true;
            }
        }

        return false;
    }

    public static void writeLicenceToFile() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("./licence.txt", "UTF-8");
        writer.println(ACLicence);
        writer.close();

        }
    }




