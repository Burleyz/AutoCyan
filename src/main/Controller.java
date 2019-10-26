package main;

import colour.ColourManager;
import gui.GUI;
import character.Character;
import mouse.ClickHandler;
import utils.Time;
import world.WorldMapHandler;

// "Why use AutoCyan? Because it's a color based bot and the scripts works like humans do,
// looking at the screen and making a decision based on what it sees,
// not like reflection and injection bots which already know what's on the RuneScape client without looking at the information in-game..."

public class Controller {

    private static Character player;
    private static GUI gui;
    private static Time time;
    private static ClickHandler clickHandler;
    private final static String USERNAME = "";
    private final static String PASSWORD = "";
    private static WorldMapHandler worldMapHandler;
    private static ColourManager colourManager;

    public Controller() {

        gui = new GUI();
        player = new Character(gui,USERNAME,PASSWORD);
        time = new Time();
        clickHandler = new ClickHandler();
        worldMapHandler = new WorldMapHandler();
        colourManager = new ColourManager(gui); //pass in gui as it has the Point info

    }

    public Character getPlayer() {
        return player;
    }

    public Time getTime() {
        return time;
    }

    public ClickHandler getClickHandler() {
        return clickHandler;
    }

    public static ColourManager getColourManager() {
        return colourManager;
    }

    public static GUI getGui() {
        return gui;
    }
}
