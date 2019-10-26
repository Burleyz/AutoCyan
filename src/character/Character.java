package character;

import gui.GUI;

public class Character {

    private Inventory inventory;
    private String username;
    private String password;
    private boolean loggedIn;

    public Character(GUI gui, String username, String password) {

        this.username = username;
        this.password = password;
        inventory = new Inventory();
        inventory.generateInventPoints(gui);

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
