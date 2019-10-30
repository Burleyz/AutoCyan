package colour;

import gui.GUI_old;
import utils.Output;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ColourManager {
    
    private Robot bot;
    private NPCColours npcColours;
    private GUI_old guiOld;
    private RSObjectColours objectColours;

    public ColourManager(GUI_old guiOld) {
        this.guiOld = guiOld;
        npcColours = new NPCColours();
        objectColours = new RSObjectColours();
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Point> findColour(Color color) { //find colour in coordinates and returns ArrayList of matches

        Rectangle capture = guiOld.getPlayScreen();
        BufferedImage bufferedImage = bot.createScreenCapture(capture);
        ArrayList<Point> pointsFound = new ArrayList<>();

        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {

                int c = bufferedImage.getRGB(x, y);

                int red = (c & 0x00ff0000) >> 16;
                int green = (c & 0x0000ff00) >> 8;
                int blue = c & 0x000000ff;

                if (red == color.getRed() && green == color.getGreen() && blue == color.getBlue()) {
                    Output.print("Colour found!");
                    pointsFound.add(new Point(x, y));
                }
            }

        }
        return pointsFound;
    }


    public Color getColour(int x, int y) { //gets colour at x and y coords
        return bot.getPixelColor(x,y);
    }

    public ArrayList<Color> getColours(Point[] points){ //gets colour at x and y coords

        ArrayList<Color> colours = new ArrayList<>();

        for (Point p : points) {
            colours.add(getColour(p.x,p.y));
        }

        return colours;
    }

    
    public static boolean similarColours(Color c1, Color c2, int tolerance) { //returns true if c1 is similar to c2 based on tolerance

        //get RGB values of c1
        int c1R = c1.getRed();
        int c1G = c1.getGreen();
        int c1B = c1.getBlue();

        //get RGB values of c2
        int c2R = c2.getRed();
        int c2G = c2.getGreen();
        int c2B = c2.getBlue();

        Output.print("c1R: " + c1R);
        Output.print("c1G: " + c1G);
        Output.print("c1B: " + c1B);

        Output.print("c2R: " + c2R);
        Output.print("c2G: " + c2G);
        Output.print("c2B: " + c2B);

        //compare with tolerance
        if(Math.abs(c1R - c2R) <= tolerance) {
            if(Math.abs(c1G - c2G) <= tolerance) {
                if(Math.abs(c1B - c2B) <= tolerance) {
                    return true;
                }
            }
        }

        return false;
        
    }

    public NPCColours getNpcColours() {
        return npcColours;
    }

    public RSObjectColours getObjectColours() {
        return objectColours;
    }
}
