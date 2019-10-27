package antiban;

import colour.ColourManager;
import mouse.ClickHandler;
import npc.Npc;
import utils.Time;

import java.awt.*;

public class SPKRandomHandler {

    private Npc AFKGuard;
    private ClickHandler clickHandler;
    private ColourManager colourManager;

    public SPKRandomHandler(ClickHandler clickHandler, ColourManager colourManager) {
        this.clickHandler = clickHandler;
        this.colourManager = colourManager;
    }

    public void dismissAfkGuard() throws AWTException {
        Point guardLocation = colourManager.findColour(AFKGuard.getColors()[0]).get(0);
        clickHandler.rightClickPoint(AFKGuard.getLocation());
        Time.rest(1000);
        clickHandler.clickPoint((int)AFKGuard.getLocation().getX(), (int)AFKGuard.getLocation().getY() + 20); //20 pixels lower which should be dismiss (add all this to a file that stores 'use','drop' etc
    }


}
