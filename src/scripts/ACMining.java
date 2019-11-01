package scripts;
/*

This script will mine ore using OSRS Mobile
 */

import character.Character;
import colour.ColourManager;
import gui.Gui;
import mouse.ClickHandler;
import objects.RSObject;
import utils.Output;
import utils.Time;

import javax.swing.plaf.synth.SynthTextAreaUI;
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
    private boolean inventFull;



    public ACMining(Gui gui) {
        inventFull = false;
        this.gui = gui;
        clickHandler = new ClickHandler();
        colourManager = new ColourManager(gui);
        player = new Character(gui);
        oreLocations = new ArrayList<>();
        initIronRockAndOre();


        while (true) {
            System.out.println(getColourAtPoint(player.getInventory().getInventSlotPoints().get(27)));
            //clickHandler.clickPoint(player.getInventory().getInventSlotPoints().get(27).x,player.getInventory().getInventSlotPoints().get(27).y);

            checkInventFull();


            /*
            try {
                clickHandler.clickPoint(rockALocation.x,rockALocation.y,gui.getClientWindow());
                Time.rest(2000);
                clickHandler.clickPoint(rockBLocation.x,rockBLocation.y,gui.getClientWindow());
            } catch (AWTException e) {
                e.printStackTrace();
            }
            */


            //Time.rest(5000);
            if(inventFull) {
                try {
                    dropInventory();
                } catch (AWTException e) {
                    e.printStackTrace();
                }

                Time.rest(5000);
            }

        }
    }

    private void initIronRockAndOre() {
        ironRock = new RSObject("IRON_ROCK",colourManager);
        ironOre = new RSObject("IRON_ORE",colourManager);

        rockALocation = new Point(334, 412);
        rockBLocation = new Point(439,325);
    }


    private static void printPointArrayList(ArrayList<Point> arrayList) {
        for(Point p : arrayList) {
            Output.print(p.toString());
        }
    }


    private int checkRockState(Point rockLocation) { //1 for live, 0 for mined

        if(colourManager.similarColours(getColourAtPoint(rockLocation), ironRock.getColors()[0],7)) {
            return 1;
        } else if (colourManager.similarColours(getColourAtPoint(rockLocation), ironRock.getColors()[1],20)) {
            return 0;
        } else {
            return -1; //-1 for error
        }
    }

    public Color getColourAtPoint(Point p) {
        return colourManager.getColour(p.x,p.y);

    }



    private void dropInventory() throws AWTException {
        int counter = 0;
        for(Point point : player.getInventory().getInventSlotPoints()) {
            clickHandler.clickPoint(point.x,point.y);
            Time.rest(100);
        }
    }

    private void checkInventFull() {
        Point lastInventSlot = player.getInventory().getInventSlotPoints().get(27);
        Color lastInventSlotColor = colourManager.getColour(lastInventSlot.x, lastInventSlot.y);
        System.out.print("lastInventColour: " + lastInventSlotColor.toString());

        Color oreColor = ironOre.getColors()[0];
        System.out.print("ore colour: " + oreColor.toString());

        if (lastInventSlotColor.getRGB() == oreColor.getRGB()) {
            inventFull = true;
            Output.print("Colours match!");
        } else {
            inventFull = false;
        }
    }


}
