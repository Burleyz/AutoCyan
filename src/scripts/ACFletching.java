package scripts;

import character.Character;
import colour.ColourManager;
import gui.Gui;
import mouse.ClickHandler;
import utils.Time;

import java.awt.*;

public class ACFletching {

    //@@@@@@@ SORT ERROR HANDLING

    private static Character player;
    private static ColourManager colourManager;
    private static ClickHandler clickHandler;
    private static Gui gui;
    private static Point bank;
    private static Point bankSlot1;
    private static Point bankExit;
    private static Point selectShortbow;
    private static Point selectLongbow;
    private static final int TIME_TO_COMPLETE_INVENT = 50000; //will change this to use checks, but for inital, 50 secongs = 50000 ms
    private static final Color BANK_COLOR = new Color(90,77,42);
    private int errorCounter;

    public ACFletching(Gui gui) {
        this.gui = gui;
        clickHandler = new ClickHandler();
        colourManager = new ColourManager(gui);
        player = new Character(gui);

        gui.setFletching(true);
        gui.setupGui();

        initPoints();

        while(true) {

            if(errorCounter > 0) {
                System.exit(-1);
            }

            try {
                checkPosition();
            } catch (AWTException e) {
                e.printStackTrace();
            }

            try {
                fletch();
            } catch (AWTException e) {
                e.printStackTrace();
            }


        }
    }
    private void fletch() throws AWTException {
        clickHandler.clickPoint(bank.x,bank.y,gui.getClientWindow());
        Time.rest(1000);
        clickHandler.clickPoint(bankSlot1.x,bankSlot1.y,gui.getClientWindow());
        Time.rest(1000);
        clickHandler.clickPoint(bankExit.x,bankExit.y,gui.getClientWindow());
        Time.rest(1000);
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(0).x,player.getInventory().getInventSlotPoints().get(0).y);
        Time.rest(1000);
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(1).x,player.getInventory().getInventSlotPoints().get(1).y);
        Time.rest(1000);
        clickHandler.clickPoint(selectShortbow.x,selectShortbow.y,gui.getClientWindow());
        Time.rest(TIME_TO_COMPLETE_INVENT);
        clickHandler.clickPoint(bank.x,bank.y,gui.getClientWindow());
        Time.rest(1000);
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(1).x,player.getInventory().getInventSlotPoints().get(1).y);
    }

    private void initPoints() {
        bank = new Point(649,315);
        bankSlot1 = new Point(221,296);
        bankExit = new Point(652,215);
        selectShortbow = new Point(195,130);
        selectLongbow = new Point(272,126);
    }

    private void checkPosition() throws AWTException {

        System.out.println("Location Colour: " + colourManager.getColour(bank,gui.getClientWindow()));
        System.out.println("BANK Colour: " + BANK_COLOR);

        if(colourManager.similarColours(colourManager.getColour(bank,gui.getClientWindow()),BANK_COLOR,5)) {

        } else {
            errorCounter++;
            System.out.println("ERROR COUNTER = " + errorCounter);
            if(errorCounter > 50) {
            logout();
            System.out.println("Character not in correct position! Exiting!");
            System.exit(-1);
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
