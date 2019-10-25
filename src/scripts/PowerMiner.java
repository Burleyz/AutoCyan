package scripts;

import utils.Time;

import java.awt.*;

public class PowerMiner extends Script {

    public PowerMiner() {

    }

    public static void main(String[] args) throws AWTException {
        Script script = new Script();

        int counter = 0;
        int limit = 28;
        while(counter < limit) {
            Time.rest(1000);
            getPlayer().dropItem(counter);
            counter++;
        }
        
    }

}
