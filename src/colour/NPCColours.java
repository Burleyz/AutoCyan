package colour;

import java.awt.*;
import java.util.HashMap;

public class NPCColours {

    private HashMap<String, Color[]> npcs; //list of npcs and colours


    public NPCColours() {
        npcs = new HashMap<>();
        initiateNpcs();
    }

    private void initiateNpcs() {
        npcs.put("KBD", new Color[] {new Color(28,28,3), new Color(47,47,36), new Color(46,45,37)});
        npcs.put("SPAWN_PK_AFK_GUARD", new Color[] {new Color(79,75,50), new Color(84,80,56)}); //SpawnPk Random event
    }

    public HashMap<String, Color[]> getNpcs() {
        return npcs;
    }
}
