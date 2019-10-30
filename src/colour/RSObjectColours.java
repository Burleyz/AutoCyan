package colour;

import java.awt.*;
import java.util.HashMap;

public class RSObjectColours {

    private HashMap<String,Color[]> rsObjects;


    public RSObjectColours() {
        rsObjects = new HashMap<>();
        initRocks();
    }

    public void initRocks() {
        rsObjects.put("IRON_ROCK", new Color[] {new Color(87,53,40),new Color(82,54,38)});
    }

    public HashMap<String, Color[]> getRsObjects() {
        return rsObjects;
    }
}
