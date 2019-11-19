package main;

import gui.AutoCyanForm;
import data.Data;
import display.Display;
import scripts.ACDuster;
import scripts.ACHerblore;
import utils.KeyboardKeys;
import data.LoginPropertiesLoader;
import mouse.ClickHandler;
import scripts.ACFletching;
import scripts.ACMining;
import utils.GetWindowRect;
import utils.Time;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Startup {

    private static Scanner scanner;
    private static Display display;
    private static int clientRectangleTopX;
    private static int clientRectangleTopY;
    private static int clientRectangleBottomX;
    private static int clientRectangleBottomY;
    private static LoginPropertiesLoader loginPropertiesLoader;
    private static ClickHandler clickHandler;
    private static AutoCyanForm gui;
    private static boolean cyanAuthenticated;
    private static ACFletching acFletching;
    private static ACMining acMining;
    private static ACDuster acDuster;
    private static ACHerblore acHerblore;
    private static Data data;
    private static Logger logger;
    private static FileHandler fileHandler;

    /*
    Please note! When starting client, please select a free world manually if using a f2p account!

     */

    private static void initClasses() {
        loginPropertiesLoader = new LoginPropertiesLoader();
        clickHandler = new ClickHandler();
        data = new Data();

    }

    private static void initLogging() throws IOException {
        logger = Logger.getLogger("AutoCyanLog");
        fileHandler = new FileHandler("./autocyan.log");
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    }

    private static void checkIfLicenceExists() throws IOException {
        String fileName = "./licence.txt";
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            gui.getAuthenticationField().setText(line);
        }

        fr.close();
        br.close();

    }

    public static void main(String[] args) {

        try {
            initLogging();
            logger.info("Logging initiated successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //launches gui
        cyanAuthenticated = false;

        gui = new AutoCyanForm();
        gui.setVersionLabel(Data.getVERSION());
        gui.setVisible(true);


        //check licence file exists
        try {
            checkIfLicenceExists();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(!cyanAuthenticated) {
            Time.rest(1000);
        }


        logger.info("Starting AutoCyan - Version: " + Data.getVERSION());
        logger.info("Getting OSRS instance...");
        initClasses();



        getOSRSWindow();
        grabClient();


        Time.rest(2000);
        try {
            login(loginPropertiesLoader.getClientType());
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Time.rest(2000);


    }

    private static void getOSRSWindow() {
        int[] rect;

        try {
            rect = GetWindowRect.getRect(loginPropertiesLoader.getClientName());

            clientRectangleTopX = rect[0];
            clientRectangleTopY = rect[1];
            clientRectangleBottomX = rect[2];
            clientRectangleBottomY = rect[3];

            logger.log(Level.INFO,"The corner locations for the window " + loginPropertiesLoader.getClientName() + " are " + Arrays.toString(rect));

        } catch (GetWindowRect.WindowNotFoundException e) {
            logger.log(Level.SEVERE, "Instance not found! Please start Old School RuneScape Client!", e);
            logger.severe("Client aborting...");
            System.exit(-1);

        } catch (GetWindowRect.GetWindowRectException e) {
            logger.log(Level.SEVERE, "Client aborting...", e);
            System.exit(-1);
        }
    }


    public static void loadScript(int script, int selection) {
        switch (script) {
            case 1:
                logger.log(Level.INFO, "Loaded script: ACMining");
                acMining = new ACMining(display, selection); //passes display to have access to the playscreen data
                break;

            case 2:
                logger.log(Level.INFO, "Loaded script: ACFletching");
                acFletching = new ACFletching(display, selection);
                break;

            case 3:
                logger.log(Level.INFO, "Loaded script: ACDuster");
                acDuster = new ACDuster(display);
                break;

            case 4:
                logger.log(Level.INFO, "Loaded script: ACHerblore");
                acHerblore = new ACHerblore(display);
                break;
        }
    }



    private static void grabClient() {
        logger.info("Latching onto instance...");
        display = new Display(loginPropertiesLoader);
        display.setClientWindowTopLeft(new Point(clientRectangleTopX, clientRectangleTopY));
        display.setClientWindowBottomRight(new Point(clientRectangleBottomX, clientRectangleBottomY));
        display.setupGui();
    }

    private static void login(String clientType) throws AWTException, IOException {

        boolean tf = true;

        logger.info("Attempting to login...");

        if (clientType.equals("desktop")) {

            KeyboardKeys kk = new KeyboardKeys();

            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            String username = loginPropertiesLoader.getUsername();
            String password = loginPropertiesLoader.getPassword();

            while(tf) {

                if (checkLoginScreen(1)) {
                    clickHandler.clickPoint(458, 318, display.getClientWindow());
                    robot.delay(2000); //waits 2 seconds

                } else if (checkLoginScreen(2)) { //checking which login screen, if on correct screen (screen 2) then do type chars

                    logger.info("Typing username and password...");
                    for (char c : username.toCharArray()) {
                        kk.keyPress(c);
                        robot.delay(100);

                    }

                    pressEnter(robot);

                    for (char c : password.toCharArray()) {
                        kk.keyPress(c);
                        robot.delay(100);

                    }

                    pressEnter(robot);
                    robot.delay(10000); //waits 10 seconds for login


                } else if (checkLoginScreen(3)) {
                    clickHandler.clickPoint(393, 380, display.getClientWindow());

                } else if (checkLoginScreen(4)) {
                    logger.info("Credentials are incorrect in autocyan.properties!");
                    System.exit(-1);

                } else {
                    logger.info("Account is logged in.");
                    logger.info("Login completed!\n");
                    tf=false;
                }
            }


        } else if (clientType.equals("mobile")) {

            logger.warning("Mobile client - Please run script when already logged in!");
        }
    }


    private static void pressEnter(Robot robot) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private static boolean checkLoginScreen(int screen) throws IOException {
        BufferedImage loginCapture = display.captureLoginRectangle();
        BufferedImage loginScreen1;
        BufferedImage loginScreen2;
        BufferedImage loginScreen3;
        BufferedImage invalidCredentials;

            loginScreen1 = ImageIO.read(new File("./images/login_screen_1.png"));
            loginScreen2 = ImageIO.read(new File("./images/login_screen_2.png"));
            loginScreen3 = ImageIO.read(new File("./images/login_screen_3.png"));
            invalidCredentials = ImageIO.read((new File("./images/invalid_credentials.png")));

        switch(screen) {
            case 1:
                return compareImages(loginCapture, loginScreen1);

            case 2:
                return compareImages(loginCapture, loginScreen2);

            case 3:
                return compareImages(loginCapture, loginScreen3);

            case 4:
                return compareImages(loginCapture, invalidCredentials);

            default:
                return false;
        }

    }

    private static boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        // The images must be the same size.
        if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
            //System.out.println("Image widths/Heights are different!");
            return false;

        }

        int width  = imgA.getWidth();
        int height = imgA.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    //System.out.println("Images are not the same!");
                    return false;
                }
            }
        }

        return true;
    }


    public static Display getDisplay() {
        return display;
    }

    public static void setCyanAuthenticated(boolean cyanAuthenticated) {
        Startup.cyanAuthenticated = cyanAuthenticated;
    }

    public static ACFletching getAcFletching() {
        return acFletching;
    }

    public static ACMining getAcMining() {
        return acMining;
    }

    public static ACDuster getAcDuster() {
        return acDuster;
    }

    public static ACHerblore getAcHerblore() {
        return acHerblore;
    }

    public static Data getData() {
        return data;
    }

    public static Logger getLogger() {
        return logger;
    }
}
