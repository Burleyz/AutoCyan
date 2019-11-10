package scripts;

import antiban.AntiBan;
import character.Character;
import colour.ColourManager;
import gui.Gui;
import mouse.ClickHandler;
import objects.RSObject;
import utils.Time;

import java.awt.*;

public class ACFletching {

    //@@@@@@@ SORT ERROR HANDLING

    private static Character player;
    private static ColourManager colourManager;
    private static ClickHandler clickHandler;
    private static Gui gui;
    private static Point bankLocation;
    private static Point bankSlot1;
    private static Point bankExit;
    private static Point selectShortbow;
    private static Point selectLongbow;
    private static Point checkBankPoint;
    private static final int TIME_TO_COMPLETE_INVENT = 50000; //will change this to use checks, but for inital, 50 secongs = 50000 ms
    private static RSObject bank;
    private int fletchingType; //1 for shortbows, 2 for longbows
    private long startTime;
    private int fletchCounter;

    public ACFletching(Gui gui, int fletchingType) {
        this.gui = gui;
        clickHandler = new ClickHandler();
        colourManager = new ColourManager(gui);
        player = new Character(gui);
        startTime = System.currentTimeMillis();
        fletchCounter = 0;

        bank = new RSObject("BANK_CHECK");

        gui.setFletching(true);
        gui.setupGui();

        initPoints();

        while(true) {
            try {
                fletch(fletchingType);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    private void fletch(int type) throws AWTException {

        clickHandler.clickPoint(bankLocation.x + AntiBan.randomValue(1,10), bankLocation.y + AntiBan.randomValue(1,10),gui.getClientWindow());
        Time.rest(AntiBan.randomValue(1000,1950));
        checkPosition();
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(1).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(1).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(1000,1950));
        clickHandler.clickPoint(bankSlot1.x + AntiBan.randomValue(1,10),bankSlot1.y + AntiBan.randomValue(1,10),gui.getClientWindow());
        Time.rest(AntiBan.randomValue(1000,1950));
        clickHandler.clickPoint(bankExit.x + AntiBan.randomValue(1,10),bankExit.y + AntiBan.randomValue(1,10),gui.getClientWindow());
        Time.rest(AntiBan.randomValue(1000,1950));
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(0).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(0).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(1000,1950));
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(1).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(1).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(1000,1950));

        if(type == 1) {
            clickHandler.clickPoint(selectShortbow.x + AntiBan.randomValue(1,10), selectShortbow.y + AntiBan.randomValue(1,10), gui.getClientWindow());
        } else if (type == 2) {
            clickHandler.clickPoint(selectLongbow.x + AntiBan.randomValue(1,10), selectLongbow.y + AntiBan.randomValue(1,10), gui.getClientWindow());
        }
        Time.rest(TIME_TO_COMPLETE_INVENT + AntiBan.randomValue(200,3000));

        fletchCounter = fletchCounter + 27;
        //running time calc
        long currentTime = System.currentTimeMillis();
        float seconds = (currentTime - startTime) / 1000F;

        int p1 = (int)seconds % 60;
        int p2 = (int)seconds / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;


        System.out.println("Time elapsed: " +  p2 + ":" + p3 + ":" + p1 + " Logs Fletched: " + fletchCounter);

        clickHandler.clickPoint(bankLocation.x + AntiBan.randomValue(1,10), bankLocation.y + AntiBan.randomValue(1,10),gui.getClientWindow());
        Time.rest(AntiBan.randomValue(700,1200));

    }

    private void initPoints() {
        bankLocation = new Point(649,315);
        bankSlot1 = new Point(221,296);
        bankExit = new Point(652,215);
        selectShortbow = new Point(195,130);
        selectLongbow = new Point(272,126);
        checkBankPoint = bank.getLocations()[0];
    }

    private void checkPosition() throws AWTException {

        //System.out.println("Location Colour: " + colourManager.getColour(checkBankPoint,gui.getClientWindow()));
        //System.out.println("BANK Colour: " + bank.getColors()[0]);

        if(colourManager.similarColours(colourManager.getColour(checkBankPoint,gui.getClientWindow()), bank.getColors()[0],5)) {

        } else {
            Time.rest(1000);
            clickHandler.clickPoint(bankExit.x,bankExit.y,gui.getClientWindow());
            System.out.println("ERROR - EXITING");
            logout();
            System.exit(-1);

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
