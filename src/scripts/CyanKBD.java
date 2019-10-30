package scripts;

import npc.Npc;
import utils.Output;
import utils.Time;

import java.awt.*;
import java.util.ArrayList;

public class CyanKBD extends Script {

    private static Npc kbd;
    private static Point kbdLocation;
    private static boolean kbdAlive;

    private static Npc guard;
    private static Point guardLocation;
    private static boolean guardExists;

    public CyanKBD() {

    }

    public static void main(String[] args) throws AWTException {
        Script script = new Script(); //scripts must initiate this in all cases
        initNpcs();

        while(true) {
            getGuardLocation();
            dismissGuard();
            getKbdLocation();
            attackKbd();

        }

    }

    private static void initNpcs() {
        kbd = new Npc("KBD", getColourManager());
        Output.print("KBD Colours: " + kbd.getColors());

        guard = new Npc("SPAWN_PK_AFK_GUARD", getColourManager());
        Output.print("Guard Colours: " + guard.getColors());
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

    private static void getGuardLocation() {
        ArrayList<Point> guardLocations = getColourManager().findColour(guard.getColors()[0]);

        if(guardLocations.size() == 0) {
            Output.print("GUARD NOT FOUND!");
            guardExists = false;
        } else {
            Point p = guardLocations.get(0); //look for colour 1 from KBD
            guardLocation = p;
            Output.print("Guard Found at: " + guardLocation.toString());
            guardExists = true;
        }

    }

    public static void dismissGuard() throws AWTException {
        if(guardExists) {
                getClickHandler().clickPoint(guardLocation.x, guardLocation.y, getGui().getPlayScreen());
        }
    }

    private static void attackKbd() throws AWTException {

        if(kbdAlive) {
            getClickHandler().clickPoint(kbdLocation.x, kbdLocation.y, getGui().getPlayScreen());
            Time.rest(10000); //wait 10 seconds for us to kill nps, need to change this to check if its dead
        }
    }

}
