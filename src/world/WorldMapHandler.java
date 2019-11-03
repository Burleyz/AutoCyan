package world;

import java.io.File;

public class WorldMapHandler {

    public WorldMapHandler() {
        getWorldMap();
    }

    private void getWorldMap() {
        File tempFile = new File("./world_map.png");
        boolean exists = tempFile.exists();

        if(!exists) {
            System.out.println("Generating world map...");
            WorldMapParser.generateWorldMap();

        }

        System.out.println("World Map file already exists.");
    }
}
