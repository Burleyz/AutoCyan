package character;

import display.Display;

public class Character {

    private Inventory inventory;
    private boolean loggedIn;

    public Character(Display display) {

        inventory = new Inventory();
        inventory.generateInventSlots(display);
        inventory.generateInventSlotPoints();

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
