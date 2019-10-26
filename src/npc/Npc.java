package npc;

import java.awt.*;

public class Npc {

    private String name;
    private Color[] colors;

    public Npc(String name, Color[] colors) {
        this.name = name;
        this.colors = colors;
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
}
