package scripts;
/*

This script will mine ore using OSRS Mobile
 */

import antiban.AntiBan;
import character.Character;
import colour.ColourManager;
import display.Display;
import main.Startup;
import mouse.ClickHandler;
import objects.RSObject;
import utils.Time;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class ACMining {

    private static RSObject ironRock;
    private static RSObject ironOre;
    private static RSObject unidentifiedMinerals;
    private static ArrayList<Point> oreLocations;
    private static Character player;
    private static ColourManager colourManager;
    private static ClickHandler clickHandler;
    private static Display display;
    private static Point rockALocation;
    private static Point rockBLocation;

    private static Point mgRockALocation;
    private static Point mgRockBLocation;
    private static Point mgRockCLocation;

    private int errorCounter;
    private static String miningLocation;

    private boolean running; //added to enable use of stop button



    public ACMining(Display display, int location) {
        running = true;
        this.display = display;
        clickHandler = new ClickHandler();
        colourManager = new ColourManager(display);
        player = new Character(display);
        oreLocations = new ArrayList<>();
        inits();
        errorCounter = 0;

        if(location == 1) {
            miningLocation = "varrock_east";
        } else if(location == 2) {
            miningLocation = "mining_guild";
        }

        display.setMining(true);
        display.setMiningLocation(miningLocation);
        display.setupGui();



        while (running) {

            if(errorCounter > 150) {
                Startup.getLogger().log(Level.SEVERE,"Too many consecutive errors, logging out!");

                try {
                    logout();
                } catch (AWTException e) {
                    e.printStackTrace();
                    Startup.getLogger().log(Level.SEVERE, "Couldn't logout! Exiting!");
                    Time.rest(5000);
                    System.exit(-1);
                }
                System.exit(-1); //end script
            }

            checkInventFull();

            if(miningLocation == "varrock_east") {

                    if (checkRockState(rockALocation,ironRock) == 1) { //if rockA alive
                        Startup.getLogger().info("Mining rock A... \n");
                        mineRock(rockALocation,ironRock);

                        errorCounter = 0; //reset error counter because if successfully mining, acc must be in correct location.

                    } else if (checkRockState(rockALocation,ironRock) == 0) {

                        if (checkRockState(rockBLocation,ironRock) == 1) {
                            Startup.getLogger().info("Mining rock B...");
                            mineRock(rockBLocation,ironRock);
                            errorCounter = 0;

                        } else if (checkRockState(rockBLocation,ironRock) == 0) {
                            Startup.getLogger().info("Both rocks are down... waiting...");
                            errorCounter = 0;
                        }
                    }
                } else if (miningLocation == "mining_guild") {
                    if (checkRockState(mgRockALocation,ironRock) == 1) { //if rockA alive
                        Startup.getLogger().info("Mining rock A... \n");
                        mineRock(mgRockALocation,ironRock);
                        errorCounter = 0; //reset error counter because if successfully mining, acc must be in correct location.
                    } else if (checkRockState(mgRockALocation,ironRock) == 0) {
                        if (checkRockState(mgRockBLocation,ironRock) == 1) {
                            Startup.getLogger().info("Mining rock B...");
                            mineRock(mgRockBLocation,ironRock);
                            errorCounter = 0;
                        } else if (checkRockState(mgRockBLocation,ironRock) == 0) {
                            if (checkRockState(mgRockCLocation,ironRock) == 1) {
                                Startup.getLogger().info("Mining rock C...");
                                mineRock(mgRockCLocation,ironRock);
                                errorCounter = 0;
                            } else if (checkRockState(mgRockCLocation,ironRock) == 0) {
                                Startup.getLogger().info("All rocks are down... waiting...");
                                errorCounter = 0;
                            }
                        }
                    }
                }



        }
    }

    private void inits() {
        ironRock = new RSObject("IRON_ROCK");
        ironOre = new RSObject("IRON_ORE");
        unidentifiedMinerals = new RSObject("UNIDENTIFIED_MINERALS");

        rockALocation = new Point(323, 400); //fix these locations
        rockBLocation = new Point(432,325); //fix these locations
        mgRockALocation = new Point(339,438);
        mgRockBLocation = new Point(430,333);
        mgRockCLocation = new Point(620,374);
    }



    private int checkRockState(Point rockLocation, RSObject rock) { //1 for live, 0 for mined

        if(colourManager.similarColours(getColourAtPoint(rockLocation), rock.getColors()[0],15)) {
            return 1;
        } else if (colourManager.similarColours(getColourAtPoint(rockLocation), rock.getColors()[1],15)) {
            return 0;
        } else {
            errorCounter++; //if errors are thrown too many times (if character is in the wrong location)
            return -1; //-1 for error
        }
    }


    public Color getColourAtPoint(Point p) {
        try {
            return colourManager.getColour(p, display.getClientWindow());
        } catch (AWTException e) {
            e.printStackTrace();
        }

        return null;
    }



    private void dropInventory() throws AWTException {
        Startup.getLogger().info("Dropping inventory!");
        for(Point point : player.getInventory().getInventSlotPoints().subList(1,28)) { //skips first slot to alloy you to keep Unidentified Minerals
            clickHandler.clickPoint(point.x + AntiBan.randomValue(1,7),point.y + AntiBan.randomValue(2,9));
            Time.rest(100 + AntiBan.randomValue(50,300));
        }
        Time.rest(1000);
    }

    private void mineRock(Point point, RSObject rock) {
        try {
            clickHandler.clickPoint(point.x + AntiBan.randomValue(1,5),point.y + AntiBan.randomValue(1,5), display.getClientWindow());
        } catch (AWTException e) {
            e.printStackTrace();
        }

        //loops if rock is live

        while(checkRockState(point,rock) == 1) {
            System.out.println("mining...");
            Time.rest(500);

        }

        System.out.println("Rock successfully mined!");
    }

    private void checkInventFull() {
        Point lastInventSlot = player.getInventory().getInventSlotPoints().get(27);
        Color lastInventSlotColor = colourManager.getColour(lastInventSlot.x, lastInventSlot.y);

        Color oreColor = ironOre.getColors()[0];
        Color unidentifiedMineralsColor = unidentifiedMinerals.getColors()[0];

        if (colourManager.similarColours(oreColor,lastInventSlotColor,5) || colourManager.similarColours(unidentifiedMineralsColor,lastInventSlotColor,5)) {
            try {
                dropInventory();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    private void logout() throws AWTException {
        Point logoutButton = new Point(927,520);
        Point logoutButtonConfirm = new Point(793,497);
        clickHandler.clickPoint(logoutButton.x,logoutButton.y, display.getClientWindow());
        Time.rest(200);
        clickHandler.clickPoint(logoutButtonConfirm.x, logoutButtonConfirm.y, display.getClientWindow());
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


}
