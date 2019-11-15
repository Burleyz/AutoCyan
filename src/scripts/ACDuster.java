package scripts;

import antiban.AntiBan;
import character.Character;
import colour.ColourManager;
import display.Display;
import mouse.ClickHandler;
import objects.RSObject;
import utils.Time;

import java.awt.*;

public class ACDuster {

    //@@@@@@@ SORT ERROR HANDLING

    private static Character player;
    private static ColourManager colourManager;
    private static ClickHandler clickHandler;
    private static Display display;
    private static Point bankLocation;
    private static Point bankSlot1;
    private static Point bankExit;
    private static Point checkBankPoint;
    private static RSObject bank;
    private int dustCounter;
    private long startTime;
    private boolean running; //added to enable use of stop button

    public ACDuster(Display display) {
        running = true;
        this.display = display;
        clickHandler = new ClickHandler();
        colourManager = new ColourManager(display);
        player = new Character(display);
        bank = new RSObject("BANK_CHECK");
        dustCounter = 0;
        startTime = System.currentTimeMillis();

        display.setDusting(true);
        display.setupGui();

        initPoints();

        while(running) {

            try {
                dust();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }

    }



    private void dust() throws AWTException {
        clickHandler.clickPoint(bankLocation.x + AntiBan.randomValue(1,10), bankLocation.y + AntiBan.randomValue(1,10), display.getClientWindow());
        Time.rest(AntiBan.randomValue(1000,1300));
        checkPosition();
        clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(1).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(1).y + AntiBan.randomValue(1,10));
        Time.rest(AntiBan.randomValue(700,1400));
        clickHandler.clickPoint(bankSlot1.x + AntiBan.randomValue(1,10),bankSlot1.y + AntiBan.randomValue(1,10), display.getClientWindow());
        Time.rest(AntiBan.randomValue(890,1300));
        clickHandler.clickPoint(bankExit.x + AntiBan.randomValue(1,10),bankExit.y + AntiBan.randomValue(1,10), display.getClientWindow());
        Time.rest(AntiBan.randomValue(900,1500));


        int counter = 0;
        while(counter < 27) {
            Time.rest(AntiBan.randomValue(100,260));
            clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(26).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(26).y + AntiBan.randomValue(1,10));
            Time.rest(AntiBan.randomValue(100,260));
            clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(27).x + AntiBan.randomValue(1,10),player.getInventory().getInventSlotPoints().get(27).y + AntiBan.randomValue(1,10));
           counter++;
        }

        dustCounter = dustCounter + 27;

        //running time calc
        long currentTime = System.currentTimeMillis();
        float seconds = (currentTime - startTime) / 1000F;

        int p1 = (int)seconds % 60;
        int p2 = (int)seconds / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;


        System.out.println("Time elapsed: " +  p2 + ":" + p3 + ":" + p1 + " Dust Created: " + dustCounter);

        clickHandler.clickPoint(bankLocation.x + AntiBan.randomValue(1,10), bankLocation.y + AntiBan.randomValue(1,10), display.getClientWindow());
        Time.rest(AntiBan.randomValue(700,1200));

    }

    private void initPoints() {
        bankLocation = new Point(649,315);
        bankSlot1 = new Point(221,296);
        bankExit = new Point(652,215);
        checkBankPoint = bank.getLocations()[0];
    }



    private void checkPosition() throws AWTException {

        //System.out.println("Location Colour: " + colourManager.getColour(checkBankPoint,display.getClientWindow()));
        //System.out.println("BANK Colour: " + bank.getColors()[0]);

        if(colourManager.similarColours(colourManager.getColour(checkBankPoint, display.getClientWindow()), bank.getColors()[0],20)) {

        } else {
            Time.rest(1000);
            clickHandler.clickPoint(bankExit.x,bankExit.y, display.getClientWindow());
            System.out.println("ERROR - EXITING");
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
