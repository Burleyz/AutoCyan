package gui;

import data.Data;
import data.LoginPropertiesLoader;
import utils.Output;

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


    public Gui(LoginPropertiesLoader loginPropertiesLoader) {
        this.loginPropertiesLoader = loginPropertiesLoader;
    }

    private void generateInventoryLocation() {

    }

    public void setupGui() {

        //sets up inventory




        setClientHeaderSize();
        setUpInventory();
        //Sets up client window Rectangle
        clientWindow = new Rectangle();
        clientWindow.setFrameFromDiagonal(clientWindowTopLeft, clientWindowBottomRight);

        //inventoryTopLeft = new Point((int)(clientWindowTopLeft.x*2.67),(int)(clientWindowTopLeft.y*3.16)); //figure out how to auto gen invent coords


        Output.print("InventTopLeft: " + inventoryTopLeft);
        Output.print("InventBottomRight: " + inventoryBottomRight);
        Output.print("ClientTopLeft: " + clientWindowTopLeft);
        Output.print("ClientBottomRight: " + clientWindowBottomRight);





        Output.print("ClientWidth: " + clientWindow.width);
        Output.print("ClientHeight: " + clientWindow.height);

        loginScreenCheckRectangle = new Rectangle();
        loginScreenCheckRectangle.setFrameFromDiagonal(new Point(clientWindowTopLeft.x + 300, clientWindowTopLeft.y + 350), new Point(clientWindowBottomRight.x - 300, clientWindowBottomRight.y - 200)); //makes rectangle to check between 3 login screens

        //sets inventory bounds
        inventory = new Rectangle();
        inventory.setFrameFromDiagonal(inventoryTopLeft, inventoryBottomRight);

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

}




