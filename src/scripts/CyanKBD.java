package scripts;

import antiban.SPKRandomHandler;
import npc.Npc;
import utils.Output;
import utils.Time;

import java.awt.*;
import java.util.ArrayList;

public class CyanKBD extends Script {

    private static Npc kbd;
    private static Point kbdLocation;
    private static boolean kbdAlive;
    private static SPKRandomHandler randomHandler;

    public CyanKBD() {

    }

    public static void main(String[] args) throws AWTException {
        Script script = new Script(); //scripts must initiate this in all cases
        randomHandler = new SPKRandomHandler(getClickHandler(),getColourManager()); //write code to dismiss the guard when it appears
        initKbd();

        while(true) {
            getKbdLocation();
            attackKbd();
        }

    }

    private static void initKbd() {
        kbd = new Npc("KBD", getColourManager());
        Output.print("KBD Colours: " + kbd.getColors());
    }

    private static void getKbdLocation() {
        ArrayList<Point> kbdLocations = getColourManager().findColour(kbd.getColors()[1]);

        if(kbdLocations.size() == 0) {
            Output.print("KBD NOT FOUND!");
            Output.print("Waiting for spawn...");
            kbdAlive = false;
            Time.rest(3500); //wait 5 seconds for spawn, we
        } else {
            Point p = kbdLocations.get(0); //look for colour 1 from KBD
            kbdLocation = p;
            Output.print("KBD Found at: " + kbdLocation.toString());
            kbdAlive = true;
        }


    }

    private static void attackKbd() throws AWTException {

        if(kbdAlive) {
            getClickHandler().clickPoint(kbdLocation.x, kbdLocation.y, getGui().getPlayScreen());
            Time.rest(10000); //wait 15 seconds for us to kill nps, need to change this to check if its dead
        }
    }

}
