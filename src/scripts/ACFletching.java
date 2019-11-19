package scripts;

import antiban.AntiBan;
import character.Character;
import colour.ColourManager;
import display.Display;
import main.Startup;
import mouse.ClickHandler;
import objects.RSObject;
import utils.Time;

import java.awt.*;
import java.util.logging.Level;

public class ACFletching {

    private static Character player;
    private static ColourManager colourManager;
    private static ClickHandler clickHandler;
    private static Display display;
    private static Point bankLocation;
    private static Point bankSlot1;
    private static Point bankExit;
    private static Point selectShortbow;
    private static Point selectLongbow;
    private static Point checkBankPoint;
    private static Point boltLocation;
    private static final int TIME_TO_COMPLETE_INVENT_BOWS = 50000; //will change this to use checks, but for inital, 50 secongs = 50000 ms
    private static final int TIME_TO_COMPLETE_BOLTS = 14400;
    private static RSObject bank;
    private int fletchingType; //1 for shortbows, 2 for longbows
    private long startTime;
    private int fletchCounter;
    private boolean running; //added to enable use of stop button

    public ACFletching(Display display, int fletchingType) {
        this.display = display;
        clickHandler = new ClickHandler();
        colourManager = new ColourManager(display);
        player = new Character(display);
        startTime = System.currentTimeMillis();
        fletchCounter = 0;
        running = true;

        bank = new RSObject("BANK_CHECK");

        if(fletchingType == 1 || fletchingType == 2) {
            display.setFletching(true);
            display.setupGui();
        }

        initPoints();

        while(running) {
            try {
                fletch(fletchingType);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    private void printAmountMade(int madePerCycle, String making) {
        fletchCounter = fletchCounter + madePerCycle;
        long currentTime = System.currentTimeMillis();
        float seconds = (currentTime - startTime) / 1000F;

        int p1 = (int)seconds % 60;
        int p2 = (int)seconds / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;

        Startup.getLogger().info("Time elapsed: " +  p2 + ":" + p3 + ":" + p1 + " " + making + " Fletched: " + fletchCounter);
    }


    private void selectBowType(Point bowType) throws AWTException {
        clickHandler.clickPoint(bowType.x + AntiBan.randomValue(1,10), bowType.y + AntiBan.randomValue(1,10), display.getClientWindow());
        Time.rest(TIME_TO_COMPLETE_INVENT_BOWS + AntiBan.randomValue(200,3000));
        clickHandler.clickPoint(bankLocation.x + AntiBan.randomValue(1,10), bankLocation.y + AntiBan.randomValue(1,10), display.getClientWindow());
        Time.rest(AntiBan.randomValue(700,1200));
    }


    private void fletch(int type) throws AWTException {

        if(type == 1) {

            bank();
            makeBows();
            selectBowType(selectShortbow);
            printAmountMade(27,"Shortbows");

        } else if (type == 2) {

            bank();
            makeBows();
            selectBowType(selectLongbow);
            printAmountMade(27,"Longbows");

        } else if (type == 3) {

            addBoltTips();
            printAmountMade(100,"Bolts");

        } else if (type == 4) {
            makeBolts();
            printAmountMade(10,"Bolts");
        }

    }

    private void makeBolts() throws AWTException {
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(0).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(0).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(50,300));
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(4).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(4).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(50,300));
    }

    private void addBoltTips() throws AWTException {
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(0).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(0).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(50,300));
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(1).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(1).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(800,1200));
        clickHandler.clickPoint(boltLocation.x + AntiBan.randomValue(1,10),boltLocation.y + AntiBan.randomValue(1,10), display.getClientWindow());
        Time.rest(TIME_TO_COMPLETE_BOLTS);
    }

    private void makeBows() throws AWTException {
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(0).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(0).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(1000,1950));
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(1).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(1).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(1000,1950));
    }

    private void bank() throws AWTException {
        clickHandler.clickPoint(bankLocation.x + AntiBan.randomValue(1,10), bankLocation.y + AntiBan.randomValue(1,10), display.getClientWindow());
        Time.rest(AntiBan.randomValue(2500,3200));
        checkPosition();
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(1).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(1).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(1000,1950));
        clickHandler.clickPoint(bankSlot1.x + AntiBan.randomValue(1,10),bankSlot1.y + AntiBan.randomValue(1,10), display.getClientWindow());
        Time.rest(AntiBan.randomValue(1000,1950));
        clickHandler.clickPoint(bankExit.x + AntiBan.randomValue(1,10),bankExit.y + AntiBan.randomValue(1,10), display.getClientWindow());
        Time.rest(AntiBan.randomValue(1000,1950));
    }

    private void initPoints() {
        bankLocation = new Point(649,315);
        bankSlot1 = new Point(221,296);
        bankExit = new Point(652,215);
        selectShortbow = new Point(195,130);
        selectLongbow = new Point(272,126);
        checkBankPoint = bank.getLocations()[0];
        boltLocation = new Point(274,126);
    }

    private void checkPosition() throws AWTException {

        if(colourManager.similarColours(colourManager.getColour(checkBankPoint, display.getClientWindow()), bank.getColors()[0],5)) {

        } else {
            Time.rest(1000);
            clickHandler.clickPoint(bankExit.x,bankExit.y, display.getClientWindow());
            Startup.getLogger().log(Level.SEVERE, "Failed bank check, exiting to prevent bans!");
            logout();
            System.exit(-1);

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
