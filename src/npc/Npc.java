package npc;

import objects.NPCLocations;

import java.awt.*;

public class Npc {

    private String name;
    private Color[] colors;
    private Point[] locations;
    private NPCColours npcColours;
    private NPCLocations npcLocations;

    public Npc(String name) {
        this.name = name;
        npcColours = new NPCColours();
        npcLocations = new NPCLocations();
        colors = npcColours.getNpcColours().get(name);
        locations = npcLocations.getNpcLocations().get(name);
    }

    public Color[] getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColors(Color[] colors) {
        this.colors = colors;
    }

    public Point[] getLocations() {
        return locations;
    }

}
