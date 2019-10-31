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

import java.awt.*;
import java.util.ArrayList;

public class ACMining {

    private static RSObject ironRock;
    private static ArrayList<Point> oreLocations;
    private static Character player;
    private static ColourManager colourManager;
    private static ClickHandler clickHandler;
    private static Gui gui;



    public ACMining(Gui gui) {
        this.gui = gui;
        clickHandler = new ClickHandler();
        colourManager = new ColourManager(gui);
        player = new Character(gui);
        oreLocations = new ArrayList<>();
        initIronRock();

        while (true) {
            findOres(ironRock);
            printPointArrayList(oreLocations);

        }
    }

    private static void initIronRock() {
        ironRock = new RSObject("IRON_ROCK",colourManager);
        Output.print("Iron ore loaded!");
    }

    private static void findOres(RSObject ore) {
        oreLocations = colourManager.findColour(ore.getColors()[0]); //gets first iron_ore colour
        Output.print("Finding ores...");
            }

    private static void printPointArrayList(ArrayList<Point> arrayList) {
        for(Point p : arrayList) {
            Output.print(p.toString());
        }
    }

    private static void checkOreUp() {

    }

    private static void mineOre() throws AWTException {
        Point p = oreLocations.get(0);
        clickHandler.clickPoint((int)p.getX(), (int)p.getY(),gui.getPlayScreen());
        Output.print("Mining ore at: " + p.toString());
        Time.rest(15000);

    }

}
