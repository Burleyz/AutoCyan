package gui.character;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;

public class Character {

    private int healthLevel;
    private int prayerLevel;

    private int currentHealth;
    private int getPrayerLevel;

    private ArrayList<Point> inventSlots;

    public Character() {
        inventSlots = new ArrayList<>();
        generateInventPoints();

    }

    public void generateInventPoints() { //invent slots

        inventSlots.add(new Point(1177,486)); //1
        inventSlots.add(new Point(1218,486)); //2
        inventSlots.add(new Point(1262,486)); //3
        inventSlots.add(new Point(1306,486)); //4

        inventSlots.add(new Point(1177,519)); //5
        inventSlots.add(new Point(1218,519)); //6
        inventSlots.add(new Point(1262,519)); //7
        inventSlots.add(new Point(1306,519)); //8

        inventSlots.add(new Point(1177,555)); //9
        inventSlots.add(new Point(1218,555)); //10
        inventSlots.add(new Point(1262,555)); //11
        inventSlots.add(new Point(1306,555)); //12

        inventSlots.add(new Point(1177,591)); //13
        inventSlots.add(new Point(1218,591)); //14
        inventSlots.add(new Point(1262,591)); //15
        inventSlots.add(new Point(1306,591)); //16

        inventSlots.add(new Point(1177,627)); //17
        inventSlots.add(new Point(1218,627)); //18
        inventSlots.add(new Point(1262,627)); //19
        inventSlots.add(new Point(1306,627)); //20

        inventSlots.add(new Point(1177,663)); //21
        inventSlots.add(new Point(1218,663)); //22
        inventSlots.add(new Point(1262,663)); //23
        inventSlots.add(new Point(1306,663)); //24

        inventSlots.add(new Point(1177,699)); //25
        inventSlots.add(new Point(1218,699)); //26
        inventSlots.add(new Point(1262,699)); //27
        inventSlots.add(new Point(1306,699)); //28

        System.out.println("Inventory positions generated: " + inventSlots.size());

    }

    public void dropItemMobile(int position) throws AWTException {
        click(inventSlots.get(position).x,inventSlots.get(position).y); //position 1 = 0, position 28 = 27
    }

    public static void click(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public ArrayList<Point> getInventSlots() {
        return inventSlots;
    }
}
