package character;

import antiban.AntiBan;
import gui.GUI;
import utils.Time;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;

public class Character {

       private ArrayList<Point> inventSlots;
       private ArrayList<Rectangle> inventSlots1; //will replace invent slots

    public Character() {
        inventSlots = new ArrayList<>();
        inventSlots1 = new ArrayList<>();
        //generateInventPoints(); moved to Script.java

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

    public void generateInventPoints(GUI gui) { //disgusting code, need to make this more efficient
        Point inventTopLeft = new Point(gui.getInventTopLeft()); //gets starting position

        Rectangle invent = gui.getInventInnerRect(); //get rect as invent outline
        
        float width = (float)invent.width;
        float height = (float)invent.height;
        float boxWidth = (float)invent.width/4;
        float boxHeight = (float)invent.height/7;

        System.out.println("Invent width: " + width);
        System.out.println("Invent height: " + height);
        System.out.println("InventSlot width: " + boxWidth);
        System.out.println("InventSlot height: " + boxHeight);

        int inventSlotCounter = 0;
        int numInventSlots = 28;

        Point newTopLeft = new Point(); //keeps track of next invent slot location

        while(inventSlotCounter < numInventSlots) { //in Rectangles x,y represent top left corner

            if (inventSlotCounter == 0) { //0
                Rectangle inventSlot = new Rectangle(inventTopLeft.x, inventTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventTopLeft.x + (int) boxWidth, inventTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 4) { //1,2,3
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter == 4) { //4
                Rectangle inventSlot = new Rectangle(inventTopLeft.x, inventTopLeft.y + (int) boxHeight, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventTopLeft.x + (int) boxWidth, inventTopLeft.y + (int) boxHeight); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 8) { //5,6,7
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter == 8) {
                Rectangle inventSlot = new Rectangle(inventTopLeft.x, inventTopLeft.y + ((int) boxHeight) * 2, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventTopLeft.x + (int) boxWidth, inventTopLeft.y + ((int) boxHeight) * 2); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 12) {
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter == 12) {
                Rectangle inventSlot = new Rectangle(inventTopLeft.x, inventTopLeft.y + ((int) boxHeight) * 3, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventTopLeft.x + (int) boxWidth, inventTopLeft.y + ((int) boxHeight) * 3); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 16) {
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter == 16) {
                Rectangle inventSlot = new Rectangle(inventTopLeft.x, inventTopLeft.y + ((int) boxHeight) * 4, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventTopLeft.x + (int) boxWidth, inventTopLeft.y + ((int) boxHeight) * 4); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 20) {
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter == 20) {
                Rectangle inventSlot = new Rectangle(inventTopLeft.x, inventTopLeft.y + ((int) boxHeight) * 5, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventTopLeft.x + (int) boxWidth, inventTopLeft.y + ((int) boxHeight) * 5); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 24) {
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;

            } else if(inventSlotCounter == 24) {
                Rectangle inventSlot = new Rectangle(inventTopLeft.x, inventTopLeft.y + ((int) boxHeight) * 6, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(inventTopLeft.x + (int) boxWidth, inventTopLeft.y + ((int) boxHeight) * 6); //updates new location for next slot
                inventSlotCounter++;

            } else if (inventSlotCounter < 28) {
                Rectangle inventSlot = new Rectangle(newTopLeft.x, newTopLeft.y, (int) boxWidth, (int) boxHeight);
                inventSlots1.add(inventSlot);
                System.out.println("Invent slot: " + inventSlotCounter + ", rectangle created. " + inventSlot.x + " " + inventSlot.y + " " + inventSlot.width + " " + inventSlot.height);
                newTopLeft = new Point(newTopLeft.x + (int) boxWidth, newTopLeft.y); //updates new location for next slot
                inventSlotCounter++;
            }
        }

        System.out.println("Inventory positions generated: " + inventSlots1.size());
    }

    public void dropItem(int position) throws AWTException {
        click(inventSlots.get(position).x + AntiBan.randomValue(7,10), inventSlots.get(position).y + AntiBan.randomValue(7,10)); //position 1 = 0, position 28 = 27
    }

    public void dropInvent() throws AWTException {
        for (Point p : inventSlots) {
            click(p.x + AntiBan.randomValue(7,10), p.y + AntiBan.randomValue(7,10));
            Time.rest(120 + AntiBan.randomValue(10,37));
        }
    }


    public void dropItem(int[] positions) throws AWTException {
        for (int p : positions) {
            click(inventSlots.get(p).x + AntiBan.randomValue(7,10), inventSlots.get(p).y + AntiBan.randomValue(7,10));
            Time.rest(120 + AntiBan.randomValue(10,37)); //add random mills between these two values to stop clicks being perfect
        }

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
