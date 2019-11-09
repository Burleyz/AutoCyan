package colour;

import gui.Gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ColourManager {
    
    private Robot bot;
    private Gui gui;

    public ColourManager(Gui gui) {
        this.gui = gui;
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Point> findColour(Color color) { //find colour in coordinates and returns ArrayList of matches

        Rectangle capture = gui.getPlayScreen();
        BufferedImage bufferedImage = bot.createScreenCapture(capture);
        ArrayList<Point> pointsFound = new ArrayList<>();

        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {

                int c = bufferedImage.getRGB(x, y);

                int red = (c & 0x00ff0000) >> 16;
                int green = (c & 0x0000ff00) >> 8;
                int blue = c & 0x000000ff;

                if (red == color.getRed() && green == color.getGreen() && blue == color.getBlue()) {
                    System.out.println("Colour found!");
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

        /*
        System.out.println("c1R: " + c1R);
        System.out.println("c1G: " + c1G);
        System.out.println("c1B: " + c1B);

        System.out.println("c2R: " + c2R);
        System.out.println("c2G: " + c2G);
        System.out.println("c2B: " + c2B);
        */

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


    public Color getColour(Point p, Rectangle rect) throws AWTException {
        Robot robot = new Robot();
       return robot.getPixelColor(p.x + rect.x,p.y + rect.y);
    }
}
