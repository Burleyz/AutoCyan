package objects;

import java.awt.*;
import java.util.HashMap;

public class RSObjectColours {

    private HashMap<String,Color[]> rsObjectColours;


    public RSObjectColours() {
        rsObjectColours = new HashMap<>();
        initRocks();
        initOres();
        initObjects();
    }

    private void initRocks() {
        rsObjectColours.put("IRON_ROCK", new Color[] {new Color(109,75,60),new Color(111,105,105)}); //first is when live, 2nd is when mined
        rsObjectColours.put("UNIDENTIFIED_MINERALS", new Color[] {new Color(117,91,8)});

    }

    private void initObjects() {
        rsObjectColours.put("BANK_CHECK",new Color[] {new Color(73,64,52)});
    }

    private void initOres() {
        rsObjectColours.put("IRON_ORE", new Color[] {new Color(3,2,3)}); //change this!
    }

    public HashMap<String, Color[]> getRsObjectColours() {
        return rsObjectColours;
    }
}
