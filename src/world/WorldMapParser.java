package world;

import utils.SaveImageFromUrl;

import java.io.IOException;

public class WorldMapParser {

    private static final String IMAGE_URL = "https://cdn.runescape.com/assets/img/external/oldschool/web/osrs_world_map_july18_2019.PNG";
    private static final String DESTINATION_FILE = "./world_map.png";

    public WorldMapParser() {

    }

    public static void generateWorldMap() {
        try {
            SaveImageFromUrl.saveImage(IMAGE_URL, DESTINATION_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
