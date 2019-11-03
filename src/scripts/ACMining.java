package scripts;
/*

This script will mine ore using OSRS Mobile
 */

import antiban.AntiBan;
import character.Character;
import colour.ColourManager;
import gui.Gui;
import mouse.ClickHandler;
import objects.RSObject;
import utils.Time;

import java.awt.*;
import java.util.ArrayList;

public class ACMining {

    private static RSObject ironRock;
    private static RSObject ironOre;
    private static ArrayList<Point> oreLocations;
    private static Character player;
    private static ColourManager colourManager;
    private static ClickHandler clickHandler;
    private static Gui gui;
    private static Point rockALocation;
    private static Point rockBLocation;
    private int errorCounter;



    public ACMining(Gui gui) {
        this.gui = gui;
        clickHandler = new ClickHandler();
        colourManager = new ColourManager(gui);
        player = new Character(gui);
        oreLocations = new ArrayList<>();
        initIronRockAndOre();
        errorCounter = 0;

        gui.setMining(true);
        gui.setupGui();


        while (true) {

            if(errorCounter > 150) {
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("@ Too many consecutive errors, logging out! @");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                try {
                    logout();
                } catch (AWTException e) {
                    e.printStackTrace();
                    System.out.print("Couldn't logout! Exiting!");
                    Time.rest(5000);
                    System.exit(-1);
                }
                System.exit(-1); //end script
            }

            checkInventFull();

            if(checkRockState(rockALocation) == 1) { //if rockA alive
                System.out.println("Mining rock A... \n");
                mineRock(rockALocation);
                errorCounter = 0; //reset error counter because if successfully mining, acc must be in correct location.
            } else if(checkRockState(rockALocation) == 0) {
                //System.out.println("Rock A down, checking Rock B... \n");
                if(checkRockState(rockBLocation) == 1) {
                    System.out.println("Mining rock B...");
                    mineRock(rockBLocation);
                    errorCounter = 0;
                } else if(checkRockState(rockBLocation) == 0) {
                    System.out.println("Both rocks are down... waiting...");
                    errorCounter = 0;
                }
            }


        }
    }

    private void initIronRockAndOre() {
        ironRock = new RSObject("IRON_ROCK",colourManager);
        ironOre = new RSObject("IRON_ORE",colourManager);

        rockALocation = new Point(323, 400); //fix these locations
        rockBLocation = new Point(432,325); //fix these locations
    }



    private int checkRockState(Point rockLocation) { //1 for live, 0 for mined

        //System.out.println("rockLocation = " + rockLocation.toString());
        //System.out.println("rockLocation colour: " + getColourAtPoint(rockLocation) + "\n");
        //System.out.println("IronRockColour Up: " + ironRock.getColors()[0] + "\n");

        if(colourManager.similarColours(getColourAtPoint(rockLocation), ironRock.getColors()[0],20)) {
            //System.out.println("rockLocation colour: " + getColourAtPoint(rockLocation));
            //System.out.println("IronRockColour Up: " + ironRock.getColors()[0]);
            return 1;
        } else if (colourManager.similarColours(getColourAtPoint(rockLocation), ironRock.getColors()[1],20)) {
            //System.out.print("Rock is down!" + "\n");
            return 0;
        } else {
            errorCounter++; //if errors are thrown too many times (if character is in the wrong location)
            return -1; //-1 for error
        }
    }

    public Color getColourAtPoint(Point p) {
        try {
            return colourManager.getColour(p,gui.getClientWindow());
        } catch (AWTException e) {
            e.printStackTrace();
        }

        return null;
    }



    private void dropInventory() throws AWTException {
        System.out.println("Dropping inventory!");
        for(Point point : player.getInventory().getInventSlotPoints()) {
            clickHandler.clickPoint(point.x + AntiBan.randomValue(1,7),point.y + AntiBan.randomValue(2,9));
            Time.rest(100 + AntiBan.randomValue(50,300));
        }
        Time.rest(1000);
    }

    private void mineRock(Point point) {
        try {
            clickHandler.clickPoint(point.x + AntiBan.randomValue(1,5),point.y + AntiBan.randomValue(1,5),gui.getClientWindow());
        } catch (AWTException e) {
            e.printStackTrace();
        }
        Time.rest(500 + AntiBan.randomValue(1,600));
    }

    private void checkInventFull() {
        System.out.println("Checking if inventory is full!");
        Point lastInventSlot = player.getInventory().getInventSlotPoints().get(27);
        Color lastInventSlotColor = colourManager.getColour(lastInventSlot.x, lastInventSlot.y);

        Color oreColor = ironOre.getColors()[0];

        if (lastInventSlotColor.getRGB() == oreColor.getRGB()) {
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
        clickHandler.clickPoint(logoutButton.x,logoutButton.y,gui.getClientWindow());
        Time.rest(200);
        clickHandler.clickPoint(logoutButtonConfirm.x, logoutButtonConfirm.y,gui.getClientWindow());
    }


}
