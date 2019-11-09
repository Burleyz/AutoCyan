package objects;

import java.awt.*;
import java.util.HashMap;

public class NPCLocations {

    private HashMap<String,Point[]> npcLocations;


    public NPCLocations() {
        npcLocations = new HashMap<>();
        initLocations();
    }

    public void initLocations() {
        npcLocations.put("BANK_CHECK",new Point[] {new Point(634,288)});
    }


    public HashMap<String, Point[]> getNpcLocations() {
        return npcLocations;
    }
}
