package scripts;

import antiban.AntiBan;
import character.Character;
import colour.ColourManager;
import gui.Gui;
import mouse.ClickHandler;
import utils.Time;

import java.awt.*;

public class ACHerblore {

    //@@@@@@@ SORT ERROR HANDLING

    private static Character player;
    private static ColourManager colourManager;
    private static ClickHandler clickHandler;
    private static Gui gui;
    private static Point bank;
    private static Point bankSlot1;
    private static Point bankExit;
    private static Point checkBankPoint;
    private static final Color BANK_COLOR = new Color(137,120,58);


    public ACHerblore(Gui gui) {
        this.gui = gui;
        clickHandler = new ClickHandler();
        colourManager = new ColourManager(gui);
        player = new Character(gui);

        gui.setDusting(true);
        gui.setupGui();

        initPoints();

        while(true) {



            try {
                clean();
            } catch (AWTException e) {
                e.printStackTrace();
            }


        }
    }

    private void clean() throws AWTException {
        clickHandler.clickPoint(bank.x + AntiBan.randomValue(1,10),bank.y + AntiBan.randomValue(1,10),gui.getClientWindow());
        Time.rest(AntiBan.randomValue(1000,1300));
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(1).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(1).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(700,1400));
        clickHandler.clickPoint(bankSlot1.x + AntiBan.randomValue(1,10),bankSlot1.y + AntiBan.randomValue(1,10),gui.getClientWindow());
        Time.rest(AntiBan.randomValue(890,1300));
        clickHandler.clickPoint(bankExit.x + AntiBan.randomValue(1,10),bankExit.y + AntiBan.randomValue(1,10),gui.getClientWindow());
        Time.rest(AntiBan.randomValue(900,1500));
        checkPosition();

        int counter = 0;
        while(counter < 28) {
            Time.rest(AntiBan.randomValue(100,260));
            clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(counter).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(counter).y + AntiBan.randomValue(1,10));

            counter++;
        }

        checkPosition();
        clickHandler.clickPoint(bank.x + AntiBan.randomValue(1,10),bank.y + AntiBan.randomValue(1,10),gui.getClientWindow());
        Time.rest(AntiBan.randomValue(700,1200));

    }

    private void initPoints() {
        bank = new Point(649,315);
        bankSlot1 = new Point(221,296);
        bankExit = new Point(652,215);
        checkBankPoint = new Point(618,352);
    }

    private void checkPosition() throws AWTException {

        System.out.println("Location Colour: " + colourManager.getColour(checkBankPoint,gui.getClientWindow()));
        System.out.println("BANK Colour: " + BANK_COLOR);

        if(colourManager.similarColours(colourManager.getColour(checkBankPoint,gui.getClientWindow()),BANK_COLOR,20)) {

        } else {
            Time.rest(1000);
            clickHandler.clickPoint(bankExit.x,bankExit.y,gui.getClientWindow());
            System.out.println("ERROR - EXITING");
            //logout();
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