package gui;

import data.Data;
import data.LoginPropertiesLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Gui extends JFrame {

    private Window w;
    private Point clientWindowTopLeft;
    private Point clientWindowBottomRight;
    private Rectangle clientWindow;

    private Point inventoryBottomRight;
    private Point inventoryTopLeft;
    private Rectangle inventory;

    //following rectangles are for checking if past login screens
    private Rectangle loginScreenCheckRectangle; //this will check which stage of login we are at
    private Rectangle playScreen;
    private LoginPropertiesLoader loginPropertiesLoader;
    private int clientHeaderSize;

    //for mining
    private Rectangle rockA;
    private Rectangle rockB;

    private boolean mining;
    private boolean fletching;

    //for fletching
    private Rectangle bank;
    private Rectangle bankSlot1;
    private Rectangle bankExit;
    private Rectangle selectShortbow;
    private Rectangle selectLongbow;


    public Gui(LoginPropertiesLoader loginPropertiesLoader) {
        this.loginPropertiesLoader = loginPropertiesLoader;
        mining = false;
    }


    public void setupGui() {


        setClientHeaderSize();
        setUpInventory();
        //Sets up client window Rectangle
        clientWindow = new Rectangle();
        clientWindow.setFrameFromDiagonal(clientWindowTopLeft, clientWindowBottomRight);

        //inventoryTopLeft = new Point((int)(clientWindowTopLeft.x*2.67),(int)(clientWindowTopLeft.y*3.16)); //figure out how to auto gen invent coords


        System.out.println("InventTopLeft: " + inventoryTopLeft);
        System.out.println("InventBottomRight: " + inventoryBottomRight);
        System.out.println("ClientTopLeft: " + clientWindowTopLeft);
        System.out.println("ClientBottomRight: " + clientWindowBottomRight);





        System.out.println("ClientWidth: " + clientWindow.width);
        System.out.println("ClientHeight: " + clientWindow.height);

        loginScreenCheckRectangle = new Rectangle();
        loginScreenCheckRectangle.setFrameFromDiagonal(new Point(clientWindowTopLeft.x + 300, clientWindowTopLeft.y + 350), new Point(clientWindowBottomRight.x - 300, clientWindowBottomRight.y - 200)); //makes rectangle to check between 3 login screens

        //sets inventory bounds
        inventory = new Rectangle();
        inventory.setFrameFromDiagonal(inventoryTopLeft, inventoryBottomRight);




        //mining
        rockA = new Rectangle();
        rockB = new Rectangle();
        rockA.setFrameFromDiagonal(new Point(323,400), new Point(323+10,400+10));
        rockB.setFrameFromDiagonal(new Point(432,325), new Point(432+10,325+10));

        //fletching
        bank = new Rectangle();
        bankExit = new Rectangle();
        bankSlot1 = new Rectangle();
        selectShortbow = new Rectangle();
        selectLongbow = new Rectangle();

        bank.setFrameFromDiagonal(new Point(649,315),new Point(649+10,315+10));
        bankExit.setFrameFromDiagonal(new Point(652,215),new Point(652+10,215+10));
        bankSlot1.setFrameFromDiagonal(new Point(221,296),new Point(221+10,296+10));
        selectLongbow.setFrameFromDiagonal(new Point(272,126),new Point(272+10,126+10));
        selectShortbow.setFrameFromDiagonal(new Point(195,130),new Point(195+10,130+10));

        generatePlayScreen();


             w = new Window(null)

        {
            @Override
            public void paint(Graphics g)
            {
                final Font font = getFont().deriveFont(48f);
                g.setFont(font);
                g.setColor(Color.RED);

                final String message = "AutoCyan - Version: " + Data.getVERSION();
                g.drawString(message, (int) clientWindow.getX() + 5, (int) clientWindow.getY() + 40);
                g.drawRect(clientWindow.x, clientWindow.y, clientWindow.width, clientWindow.height);
                g.drawRect(playScreen.x,playScreen.y,playScreen.width,playScreen.height);

                g.setColor(Color.BLUE);
                g.drawRect(inventory.x, inventory.y, inventory.width, inventory.height);

                if(mining) {
                    g.setColor(Color.GREEN);
                    g.drawRect(rockA.x + clientWindow.x - 5,rockA.y + clientWindow.y - 5,rockA.width,rockA.height); //-5 so the checkColour doesn't see the green
                    g.drawRect(rockB.x + clientWindow.x - 5,rockB.y + clientWindow.y - 5,rockB.width,rockB.height);
                } else if(fletching) {
                    g.setColor(Color.GREEN);
                    g.drawRect(bank.x + clientWindow.x -5,bank.y + clientWindow.y - 5,bank.width,bank.height);
                    g.drawRect(bankExit.x + clientWindow.x -5,bankExit.y + clientWindow.y - 5,bankExit.width,bankExit.height);
                    g.drawRect(bankSlot1.x + clientWindow.x -5,bankSlot1.y + clientWindow.y - 5,bankSlot1.width,bankSlot1.height);
                    g.drawRect(selectLongbow.x + clientWindow.x -5,selectLongbow.y + clientWindow.y - 5,selectLongbow.width,selectLongbow.height);
                    g.drawRect(selectShortbow.x + clientWindow.x -5,selectShortbow.y + clientWindow.y - 5,selectShortbow.width,selectShortbow.height);
                }





                //g.setColor(Color.BLUE);
                //g.drawRect(loginScreenCheckRectangle.x, loginScreenCheckRectangle.y, loginScreenCheckRectangle.width, loginScreenCheckRectangle.height); //don't draw this when released

                /* BELOW CODE USED FOR GENERATING COMPARISON IMAGES
                BufferedImage capture = null;
                try {
                    capture = new Robot().createScreenCapture(loginScreenCheckRectangle);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                try {
                    ImageIO.write(capture, "png", new File("invalid_credentials.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */

            }

            @Override
            public void update(Graphics g)
            {
                paint(g);
            }
        };

        w.setAlwaysOnTop(true);
        w.setBounds(w.getGraphicsConfiguration().getBounds());
        w.setBackground(new Color(0, true));
        w.setVisible(true);
    }

    private void generatePlayScreen() {
        playScreen = new Rectangle(clientWindow.x,clientWindow.y + clientHeaderSize,clientWindow.width,clientWindow.height - clientHeaderSize); //add 40 to move below top bar of bluestacks
    }

    public Point getMousePos() {
        PointerInfo inf = MouseInfo.getPointerInfo();
        Point p = inf.getLocation();
        System.out.println(p.toString());
        return p;
    }

    public void setClientWindowTopLeft(Point clientWindowTopLeft) {
        this.clientWindowTopLeft = clientWindowTopLeft;
    }


    public void setClientWindowBottomRight(Point clientWindowBottomRight) {
        this.clientWindowBottomRight = clientWindowBottomRight;
    }

    public Rectangle getLoginScreenCheckRectangle() {
        return loginScreenCheckRectangle;
    }

    public BufferedImage captureLoginRectangle() {
        try {
            BufferedImage capture = new Robot().createScreenCapture(loginScreenCheckRectangle);
            return capture;
        } catch (AWTException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Rectangle getClientWindow() {
        return clientWindow;
    }

    //public Rectangle generateInventoryRectangle() {
    //    clientWindow.
    //}


    public Rectangle getPlayScreen() {
        return playScreen;
    }

    private void setClientHeaderSize() {
        if(loginPropertiesLoader.getClientName().equals("OSBuddy Guest - Guest")) {
            clientHeaderSize = 32;
        } else if(loginPropertiesLoader.getClientName().equals("NoxPlayer1")) {
            clientHeaderSize = 34;
        }
    }

    private void setUpInventory() {
        inventoryTopLeft = new Point(clientWindowTopLeft.x + 696, clientWindowTopLeft.y + 254);
        inventoryBottomRight = new Point(clientWindowTopLeft.x + 901, clientWindowTopLeft.y + 536);
    }

    public Point getInventoryTopLeft() {
        return inventoryTopLeft;
    }

    public void setInventoryTopLeft(Point inventoryTopLeft) {
        this.inventoryTopLeft = inventoryTopLeft;
    }

    public Rectangle getInventory() {
        return inventory;
    }

    public void setMining(boolean mining) {
        this.mining = mining;
    }

    public void setFletching(boolean fletching) {
        this.fletching = fletching;
    }
}




