package scripts;

import npc.Npc;
import utils.Output;

import java.awt.*;

public class PowerMiner extends Script {

    private static Npc kbd;

    public PowerMiner() {

    }

    public static void main(String[] args) throws AWTException {
        Script script = new Script(); //scripts must initiate this in all cases

        kbd = new Npc("KBD", getColourManager().getNpcColours().getNpcs().get("KBD"));
        Output.print("KBD Colours: " + kbd.getColors());

        Point KBDlocation = new Point(getColourManager().findColour(kbd.getColors()[1]).get(0));

        System.out.println(KBDlocation); //look for colour 1 from KBD, then print it to screen if found

        getClickHandler().rightClickPoint(KBDlocation.x,KBDlocation.y,getGui().getPlayScreen());

    }

}
