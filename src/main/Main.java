package main;

import clientHandler.ClientHandler;

public class Main {


    private static ClientHandler ch;


    public static void main(String[] args) {
        ch = new ClientHandler();
        ch.launchLogin();




    }

    public static void launchOSRS() {
        if (ch.isOSRSLoaded() == false) {
            //Launch OSRS
        }
    }

}
