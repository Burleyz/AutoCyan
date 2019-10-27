package npc;

import colour.ColourManager;

import java.awt.*;

public class Npc {

    private String name;
    private Color[] colors;
    private Point location;

    public Npc(String name, ColourManager cm) {
        this.name = name;
        colors = cm.getNpcColours().getNpcs().get(name);
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

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
