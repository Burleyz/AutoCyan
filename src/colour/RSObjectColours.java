package colour;

import java.awt.*;
import java.util.HashMap;

public class RSObjectColours {

    private HashMap<String,Color[]> rsObjects;


    public RSObjectColours() {
        rsObjects = new HashMap<>();
        initRocks();
        initOres();
    }

    public void initRocks() {
        rsObjects.put("IRON_ROCK", new Color[] {new Color(109,75,60),new Color(111,105,105)}); //first is when live, 2nd is when mined

    }

    public void initOres() {
        rsObjects.put("IRON_ORE", new Color[] {new Color(3,2,3)}); //change this!
    }

    public HashMap<String, Color[]> getRsObjects() {
        return rsObjects;
    }
}
