package character;

import gui.GUI;
import utils.Output;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;

public class Character {

       private ArrayList<Rectangle> inventSlots;

    public Character() {

        inventSlots = new ArrayList<>();

    }

    public void generateInventPoints(GUI gui) { //disgusting code, need to make this more efficient
        Point inventInnerTopLeft = new Point(gui.getInventInnerTopLeft()); //gets starting position

        Rectangle invent = gui.getInventInnerRect(); //get rect as invent outline

        float width = (float)invent.width;
        float height = (float)invent.height;
        float boxWidth = (float)invent.width/4;
        float boxHeight = (float)invent.height/7;

        Output.print("Invent width: " + width);
        Output.print("Invent height: " + height);
        Output.print("InventSlot width: " + boxWidth);
        Output.print("InventSlot height: " + boxHeight);

        int inventSlotCounter = 0;
        int numInventSlots = 28;

        Point newTopLeft = new Point(); //keeps track of next invent slot location

        while(inventSlotCounter < numInventSlots) { //in Rectangles x,y represent top left corner

            if (inventSlotCounter == 0) { //0
                Rectangle inventSlot = new Rectangle(inventInnerTopLeft.x, inventInnerTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventInnerTopLeft.x + (int) boxWidth, inventInnerTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 4) { //1,2,3
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter == 4) { //4
                Rectangle inventSlot = new Rectangle(inventInnerTopLeft.x, inventInnerTopLeft.y + (int) boxHeight, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventInnerTopLeft.x + (int) boxWidth, inventInnerTopLeft.y + (int) boxHeight); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 8) { //5,6,7
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter == 8) {
                Rectangle inventSlot = new Rectangle(inventInnerTopLeft.x, inventInnerTopLeft.y + ((int) boxHeight) * 2, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventInnerTopLeft.x + (int) boxWidth, inventInnerTopLeft.y + ((int) boxHeight) * 2); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 12) {
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter == 12) {
                Rectangle inventSlot = new Rectangle(inventInnerTopLeft.x, inventInnerTopLeft.y + ((int) boxHeight) * 3, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventInnerTopLeft.x + (int) boxWidth, inventInnerTopLeft.y + ((int) boxHeight) * 3); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 16) {
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter == 16) {
                Rectangle inventSlot = new Rectangle(inventInnerTopLeft.x, inventInnerTopLeft.y + ((int) boxHeight) * 4, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventInnerTopLeft.x + (int) boxWidth, inventInnerTopLeft.y + ((int) boxHeight) * 4); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 20) {
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter == 20) {
                Rectangle inventSlot = new Rectangle(inventInnerTopLeft.x, inventInnerTopLeft.y + ((int) boxHeight) * 5, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventInnerTopLeft.x + (int) boxWidth, inventInnerTopLeft.y + ((int) boxHeight) * 5); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 24) {
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if(inventSlotCounter == 24) {
                Rectangle inventSlot = new Rectangle(inventInnerTopLeft.x, inventInnerTopLeft.y + ((int) boxHeight) * 6, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventInnerTopLeft.x + (int) boxWidth, inventInnerTopLeft.y + ((int) boxHeight) * 6); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 28) {
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots.add(inventSlot);
                Output.print("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;
            }
        }

        Output.print("Inventory positions generated: " + inventSlots.size());
    }


    private Point getCenter(Rectangle rect) { //calcs center of rectangle THIS OR DROP ISNT WORKING, FIX THIS
        return new Point((rect.x + (rect.width/2)),rect.y + (rect.height/2));
    }

    public void dropItem(int position) {
        Point center = getCenter(inventSlots.get(position)); //gets center of rectangle
        Output.print("Top Left: " + inventSlots.get(position).toString());
        Output.print("Clicked -- x: " + center.x + " y: " + center.y);

        try {
            click(center.x, center.y); //position 1 = 0, position 28 = 27

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void click(int x, int y) throws AWTException{
        Robot bot = new Robot();
        bot.mouseMove(x, y);
        bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

}
