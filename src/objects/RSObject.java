package objects;

import java.awt.*;

public class RSObject {

    private String name;
    private Color[] colors;
    private Point[] locations;
    private RSObjectLocations rsObjectLocations;
    private RSObjectColours rsObjectColours;

    public RSObject(String name) {
        this.name = name;
        rsObjectColours = new RSObjectColours();
        rsObjectLocations = new RSObjectLocations();
        colors = rsObjectColours.getRsObjectColours().get(name);
        locations = rsObjectLocations.getRsObjectLocations().get(name);
    }

    public Color[] getColors() {
        return colors;
    }

    public Point[] getLocations() {
        return locations;
    }
}
