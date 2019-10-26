package world;

import utils.Output;

import java.io.File;

public class WorldMapHandler {

    public WorldMapHandler() {
        getWorldMap();
    }

    private void getWorldMap() {
        File tempFile = new File("./world_map.png");
        boolean exists = tempFile.exists();

        if(!exists) {
            Output.print("Generating world map...");
            WorldMapParser.generateWorldMap();

        }

        Output.print("World Map file already exists.");
    }
}
