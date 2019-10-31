package character;

import gui.GUI_old;
import gui.Gui;

public class Character {

    private Inventory inventory;
    private boolean loggedIn;

    public Character(Gui gui) {

        inventory = new Inventory();
        //inventory.generateInventPoints(gui);

    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
