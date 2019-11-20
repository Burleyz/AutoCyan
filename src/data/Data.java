package data;

import main.Startup;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;

public class Data {
    private final static String VERSION = "beta_1.1";
    private static String ACLicence = "";

    public Data() {

    }

    public static String getVERSION() {
        return VERSION;
    }

    private static void downloadRelease(String version) throws IOException {
        String filename = "AutoCyan_" + version + ".jar";
        URL url = new URL("http://jacgri9.dreamhosters.com/ac/release/" + filename);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("./" + filename);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        Startup.getLogger().info("Latest version downloaded successfully: " + version);
        Startup.getLogger().info("Please launch updated version");
        System.exit(0);
    }

    public static void checkVersion() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(
                "http://jacgri9.dreamhosters.com/ac/version/current_ver.txt").openStream()))) {
            String fullVersion = br.readLine();
            String releaseType = fullVersion.split("_")[0];
            String revision = fullVersion.split("_")[1];
            Startup.getLogger().info("Latest Release: " + releaseType + " Revision: " + revision);

            if(!VERSION.equals(fullVersion)) {
                Startup.getLogger().warning("Code is out of date! Downloading latest version...");
                downloadRelease(fullVersion);
            }

        } catch (IOException e) {
            Startup.getLogger().log(Level.WARNING, "Unable to check version... Code may be out of date!", e);
        }


    }


    public static boolean checkLicence(String licence) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] byteArray = licence.getBytes("UTF-8");
        byte[] md5Digest = md.digest(byteArray);

        String hash = DatatypeConverter
                .printHexBinary(md5Digest).toUpperCase();


        String read = new Scanner(new URL("http://jacgri9.dreamhosters.com/licence/licences").openStream(), "UTF-8").useDelimiter("\\A").next();
        String[] licences = read.split("\\r?\\n");

        for (String a : licences) { ;
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




