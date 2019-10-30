package character;

import gui.GUI_old;

public class Character {

    private Inventory inventory;
    private boolean loggedIn;

    public Character(GUI_old guiOld) {

        inventory = new Inventory();
        inventory.generateInventPoints(guiOld);

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
