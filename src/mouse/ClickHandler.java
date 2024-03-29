package mouse;

import antiban.AntiBan;
import character.Inventory;

import java.awt.*;
import java.awt.event.InputEvent;

public class ClickHandler {

    private Robot bot;

    public ClickHandler() {
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public Point getCenter(Rectangle rect) { //calcs center of rectangle THIS OR DROP ISNT WORKING, FIX THIS
        return new Point((rect.x + (rect.width/2)),rect.y + (rect.height/2));
    }

    public void clickInventSlot(int position, Inventory inventory) {
        Point center = getCenter(inventory.getInventSlots().get(position)); //gets center of rectangle
        System.out.println("Top Left: " + inventory.getInventSlots().get(position).toString());
        System.out.println("Clicked -- x: " + center.x + " y: " + center.y);

        try {
            clickPoint(center.x + AntiBan.randomValue(-3,3), center.y + AntiBan.randomValue(-3,3)); //position 1 = 0, position 28 = 27

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void clickPoint(int x, int y) throws AWTException{
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }


    public void rightClickPoint(int x, int y, Rectangle rectangle) throws AWTException {
        bot.mouseMove(x + rectangle.x, y + rectangle.y);
        bot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void rightClickPointMobile(int x, int y) throws AWTException {
        System.out.println("Right clicking!");
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.delay(1000);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void clickPoint(int x, int y, Rectangle rectangle) throws AWTException{ //used to make sure it clicks inside playscreen not full screen
        bot.mouseMove(x + rectangle.x, y + rectangle.y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }



}
