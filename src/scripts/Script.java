package scripts;

import character.Character;
import colour.ColourManager;
import gui.GUI_old;
import main.Controller;
import mouse.ClickHandler;
import utils.Time;

public class Script {

    private static Controller controller;

    public Script() {
        controller = new Controller();
    }

    public static Character getPlayer() {
        return controller.getPlayer();
    }

    public static Time getTime() {
        return controller.getTime();
    }

    public static ClickHandler getClickHandler() {
        return controller.getClickHandler();
    }

    public static ColourManager getColourManager() {
        return controller.getColourManager();
    }

    public static GUI_old getGui() {
        return controller.getGuiOld();
    }

}
