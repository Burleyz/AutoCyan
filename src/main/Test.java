package main;

import gui.GUI;
import gui.character.Character;
import utils.Time;

import java.awt.*;


public class Test {

    private static Character player;
    private static GUI gui;
    private static Time time;

    public Test() {
    }

    public static void main(String[] args) throws AWTException {

       //mousePoints();

       gui = new GUI();
       player = new Character();
       time = new Time();

       int i = 0;
       while(i < player.getInventSlots().size()) {
            player.dropItemMobile(i);
            time.rest(100);
            i++;
            System.out.println(i-1);
       }

       

    }

    public static void mousePoints() {
        while (true) {
            //Thread.sleep(100);
            System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x +
                    ", " +
                    MouseInfo.getPointerInfo().getLocation().y + ")");
        }
    }
}
