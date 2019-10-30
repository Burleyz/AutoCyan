package scripts;
/*

This script will mine ore using OSRS Mobile
 */

import objects.RSObject;
import utils.Output;
import utils.Time;

import java.awt.*;
import java.util.ArrayList;

public class CyanMobileMiner extends Script{

    private static RSObject ironRock;
    private static ArrayList<Point> oreLocations;



    public static void main(String[] args) {
        Script script = new Script(); //scripts must initiate this in all cases
        oreLocations = new ArrayList<>();
        initIronRock();

        while (true) {
            findOres(ironRock);
            printPointArrayList(oreLocations);
            try {
                mineOre();
            } catch (AWTException e) {
                e.printStackTrace();
            }

        }
    }

    private static void initIronRock() {
        ironRock = new RSObject("IRON_ROCK",getColourManager());
        Output.print("Iron ore loaded!");
    }

    private static void findOres(RSObject ore) {
        ArrayList<Point> points = getColourManager().findColour(ore.getColors()[0]); //gets first iron_ore colour

        for (Point p: points) {
            oreLocations.add(p);
        }

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
        getClickHandler().clickPoint((int)p.getX(), (int)p.getY(),getGui().getPlayScreen());
        Output.print("Mining ore at: " + p.toString());
        Time.rest(15000);

    }

}
