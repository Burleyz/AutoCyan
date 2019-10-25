package scripts;

import gui.GUI;
import character.Character;
import utils.Time;

public class Script {

    private static Character player;
    private static GUI gui;
    private static Time time;

    public Script() {
        gui = new GUI();
        player = new Character();
        time = new Time();

        player.generateInventPoints(gui);


    }

    public static Character getPlayer() {
        return player;
    }

    public static GUI getGui() {
        return gui;
    }

    public static Time getTime() {
        return time;
    }
}
