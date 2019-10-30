package character;

import gui.GUI_old;
import utils.Output;

import java.awt.*;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<Rectangle> inventSlots;
    private Robot bot;

    public Inventory() {

        inventSlots = new ArrayList<>();
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void generateInventPoints(GUI_old guiOld) { //disgusting code, need to make this more efficient
        Point inventInnerTopLeft = new Point(guiOld.getInventoryTopLeft()); //gets starting position

        Rectangle invent = guiOld.getInventory(); //get rect as invent outline

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

    public ArrayList<Rectangle> getInventSlots() {
        return inventSlots;
    }
}
