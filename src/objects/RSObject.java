package objects;

import colour.ColourManager;

import java.awt.*;

public class RSObject {

    private String name;
    private Color[] colors;
    private Point location;

    public RSObject(String name, ColourManager cm) {
        this.name = name;
        colors = cm.getObjectColours().getRsObjects().get(name);
    }

    public Color[] getColors() {
        return colors;
    }
}
