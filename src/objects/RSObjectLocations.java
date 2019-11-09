package objects;

import java.awt.*;
import java.util.HashMap;

public class RSObjectLocations {

    private HashMap<String,Point[]> rsObjectLocations;


    public RSObjectLocations() {
        rsObjectLocations = new HashMap<>();
        initLocations();
    }

    public void initLocations() {
        rsObjectLocations.put("BANK_CHECK",new Point[] {new Point(634,288)});
    }


    public HashMap<String, Point[]> getRsObjectLocations() {
        return rsObjectLocations;
    }
}
