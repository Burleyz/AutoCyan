package main;

import colour.ColourManager;
import data.Data;
import data.KeyboardKeys;
import data.LoginPropertiesLoader;
import gui.Gui;
import mouse.ClickHandler;
import scripts.ACMining;
import utils.GetWindowRect;
import utils.Output;
import utils.Time;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Startup {

    private static Scanner scanner;
    private static Gui gui;
    private static int clientRectangleTopX;
    private static int clientRectangleTopY;
    private static int clientRectangleBottomX;
    private static int clientRectangleBottomY;
    private static LoginPropertiesLoader loginPropertiesLoader;
    private static ClickHandler clickHandler;

    /*
    Please note! When starting client, please select a free world manually if using a f2p account!

     */

    private static void initClasses() {
        loginPropertiesLoader = new LoginPropertiesLoader();
        clickHandler = new ClickHandler();
    }


    public static void main(String[] args) {
        Output.print("Starting AutoCyan - Version: " + Data.getVERSION());
        Output.print("Getting OSRS instance...");
        initClasses();
        getOSRSWindow();
        launchGui();


        Time.rest(5000);
        try {
            login(loginPropertiesLoader.getClientType());
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Time.rest(5000);

        scriptSelector();


    }

    private static void getOSRSWindow() {
        int[] rect;


        try {
            rect = GetWindowRect.getRect(loginPropertiesLoader.getClientName());

            clientRectangleTopX = rect[0];
            clientRectangleTopY = rect[1];
            clientRectangleBottomX = rect[2];
            clientRectangleBottomY = rect[3];


            System.out.printf("The corner locations for the window \"%s\" are %s",
                    loginPropertiesLoader.getClientName(), Arrays.toString(rect));

            Output.print("\n");

        } catch (GetWindowRect.WindowNotFoundException e) {
            e.printStackTrace();
            Output.print("Instance not found! Please start Old School RuneScape Client!");
            Output.print("Client aborting...");
            System.exit(-1);

        } catch (GetWindowRect.GetWindowRectException e) {
            e.printStackTrace();
            Output.print("Client aborting...");
            System.exit(-1);
        }
    }

    private static void scriptSelector() {
        scanner = new Scanner(System.in);
        Output.print("\n");
        Output.print("Which script would you like to run?");
        Output.print("1: ACMining (PowerMining)");
        int answer = scanner.nextInt();
        Time.rest(1000);
        loadScript(answer);


    }

    private static void loadScript(int script) {
        switch (script) {
            case 1:
                Output.print("Loading ACMining...");
                ACMining cm = new ACMining(gui); //passes gui to have access to the playscreen data
                break;
        }
    }



    private static void launchGui() {
        Output.print("Latching onto instance!");
        gui = new Gui(loginPropertiesLoader);
        gui.setClientWindowTopLeft(new Point(clientRectangleTopX, clientRectangleTopY));
        gui.setClientWindowBottomRight(new Point(clientRectangleBottomX, clientRectangleBottomY));
        gui.setupGui();
    }

    private static void login(String clientType) throws AWTException, IOException {

        boolean tf = true;
        Output.print("Attempting to login...");
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
                    clickHandler.clickPoint(458, 318, gui.getClientWindow());
                    robot.delay(2000); //waits 2 seconds

                } else if (checkLoginScreen(2)) { //checking which login screen, if on correct screen (screen 2) then do type chars

                    Output.print("Typing username and password...");
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
                    clickHandler.clickPoint(393, 380, gui.getClientWindow());

                } else if (checkLoginScreen(4)) {
                    Output.print("Credentials are incorrect in autocyan.properties!");
                    System.exit(-1);


                } else {
                    Output.print("Account is logged in.");
                    Output.print("Login completed!\n");
                }
            }


        } else if (clientType.equals("mobile")) {

            Output.print("Mobile client - Please run script when already logged in!");
            /*
            NOT WORKING, CLICK WONT REGISTER - Login manually

            Point p = clickHandler.getCenter(gui.getClientWindow());
            clickHandler.clickPoint(p.x,p.y + 50); //add 50 to move the point slight down as center of client isnt the center of the game
            Time.rest(2000);
            clickHandler.clickPoint(p.x,p.y + 50); //add 50 to move the point slight down as center of client isnt the center of the game
            Output.print("Login completed!\n");
            */

        }
    }

    private static void pressEnter(Robot robot) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private static boolean checkLoginScreen(int screen) throws IOException {
        BufferedImage loginCapture = gui.captureLoginRectangle();
        BufferedImage loginScreen1;
        BufferedImage loginScreen2;
        BufferedImage loginScreen3;
        BufferedImage invalidCredentials;

            loginScreen1 = ImageIO.read(new File("./login_screen_1.png"));
            loginScreen2 = ImageIO.read(new File("./login_screen_2.png"));
            loginScreen3 = ImageIO.read(new File("./login_screen_3.png"));
            invalidCredentials = ImageIO.read((new File("./invalid_credentials.png")));

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
            //Output.print("Image widths/Heights are different!");
            return false;

        }

        int width  = imgA.getWidth();
        int height = imgA.getHeight();

        // Loop over every pixel.
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Compare the pixels for equality.
                if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                    //Output.print("Images are not the same!");
                    return false;
                }
            }
        }

        return true;
    }

    private static void rightClickTest() throws AWTException {
        clickHandler.rightClickPointMobile(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
    }

    public static Gui getGui() {
        return gui;
    }

}