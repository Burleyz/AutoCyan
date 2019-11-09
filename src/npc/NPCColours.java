package npc;

import java.awt.*;
import java.util.HashMap;

public class NPCColours {

    private HashMap<String, Color[]> npcColours; //list of npcColours and colours


    public NPCColours() {
        npcColours = new HashMap<>();
        initiateNpcs();
    }

    private void initiateNpcs() {
        npcColours.put("KBD", new Color[] {new Color(28,28,3), new Color(47,47,36), new Color(46,45,37)});
        npcColours.put("SPAWN_PK_AFK_GUARD", new Color[] {new Color(79,75,50), new Color(84,80,56)}); //SpawnPk Random event
    }

    public HashMap<String, Color[]> getNpcColours() {
        return npcColours;
    }
}
