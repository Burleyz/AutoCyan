package display;

import data.Data;
import data.LoginPropertiesLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display extends JFrame {

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
    private Rectangle varrockEastRockA;
    private Rectangle varrockEastRockB;
    private Rectangle miningGuildRockA;
    private Rectangle miningGuildRockB;
    private Rectangle miningGuildRockC;
    private String miningLocation;

    private boolean mining;
    private boolean fletching;
    private boolean dusting;

    private int ra; //rectangle addition: the size of the green squares on screen

    //for fletching/unicorn horns
    private Rectangle bank;
    private Rectangle bankSlot1;
    private Rectangle bankExit;
    private Rectangle selectShortbow;
    private Rectangle selectLongbow;



    public Display(LoginPropertiesLoader loginPropertiesLoader) {
        this.loginPropertiesLoader = loginPropertiesLoader;
        ra = 20;
    }


    public void setupGui() {


        setClientHeaderSize();
        setUpInventory();
        //Sets up client window Rectangle
        clientWindow = new Rectangle();
        clientWindow.setFrameFromDiagonal(clientWindowTopLeft, clientWindowBottomRight);

        loginScreenCheckRectangle = new Rectangle();
        loginScreenCheckRectangle.setFrameFromDiagonal(new Point(clientWindowTopLeft.x + 300, clientWindowTopLeft.y + 350), new Point(clientWindowBottomRight.x - 300, clientWindowBottomRight.y - 200)); //makes rectangle to check between 3 login screens

        //sets inventory bounds
        inventory = new Rectangle();
        inventory.setFrameFromDiagonal(inventoryTopLeft, inventoryBottomRight);

        //mining
        varrockEastRockA = new Rectangle();
        varrockEastRockB = new Rectangle();
        varrockEastRockA.setFrameFromDiagonal(new Point(323,400), new Point(323+ra,400+ra));
        varrockEastRockB.setFrameFromDiagonal(new Point(432,325), new Point(432+ra,325+ra));

        miningGuildRockA = new Rectangle();
        miningGuildRockB = new Rectangle();
        miningGuildRockC = new Rectangle();
        miningGuildRockA.setFrameFromDiagonal(new Point(339, 438),new Point(339+ra, 438+ra));
        miningGuildRockB.setFrameFromDiagonal(new Point(430,333),new Point(430+ra,333+ra));
        miningGuildRockC.setFrameFromDiagonal(new Point(620,374),new Point(620+ra,374+ra));

        //fletching
        bank = new Rectangle();
        bankExit = new Rectangle();
        bankSlot1 = new Rectangle();
        selectShortbow = new Rectangle();
        selectLongbow = new Rectangle();

        bank.setFrameFromDiagonal(new Point(649,315),new Point(649+ra,315+ra));
        bankExit.setFrameFromDiagonal(new Point(652,215),new Point(652+ra,215+ra));
        bankSlot1.setFrameFromDiagonal(new Point(221,296),new Point(221+ra,296+ra));
        selectLongbow.setFrameFromDiagonal(new Point(272,126),new Point(272+ra,126+ra));
        selectShortbow.setFrameFromDiagonal(new Point(195,130),new Point(195+ra,130+ra));

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
                    if(miningLocation == "varrock_east") {
                        g.setColor(Color.GREEN);
                        g.drawRect(varrockEastRockA.x + clientWindow.x - 5, varrockEastRockA.y + clientWindow.y - 5, varrockEastRockA.width, varrockEastRockA.height); //-5 so the checkColour doesn't see the green
                        g.drawRect(varrockEastRockB.x + clientWindow.x - 5, varrockEastRockB.y + clientWindow.y - 5, varrockEastRockB.width, varrockEastRockB.height);
                    } else if (miningLocation == "mining_guild") {
                        g.setColor(Color.GREEN);
                        g.drawRect(miningGuildRockA.x + clientWindow.x - 5, miningGuildRockA.y + clientWindow.y - 5, miningGuildRockA.width, miningGuildRockA.height);
                        g.drawRect(miningGuildRockB.x + clientWindow.x - 5, miningGuildRockB.y + clientWindow.y - 5, miningGuildRockB.width, miningGuildRockB.height);
                        g.drawRect(miningGuildRockC.x + clientWindow.x - 5, miningGuildRockC.y + clientWindow.y - 5, miningGuildRockC.width, miningGuildRockC.height);
                    }
                } else if(fletching) {
                    g.setColor(Color.GREEN);
                    g.drawRect(bank.x + clientWindow.x -5,bank.y + clientWindow.y - 5,bank.width,bank.height);
                    g.drawRect(bankExit.x + clientWindow.x -5,bankExit.y + clientWindow.y - 5,bankExit.width,bankExit.height);
                    g.drawRect(bankSlot1.x + clientWindow.x -5,bankSlot1.y + clientWindow.y - 5,bankSlot1.width,bankSlot1.height);
                    g.drawRect(selectLongbow.x + clientWindow.x -5,selectLongbow.y + clientWindow.y - 5,selectLongbow.width,selectLongbow.height);
                    g.drawRect(selectShortbow.x + clientWindow.x -5,selectShortbow.y + clientWindow.y - 5,selectShortbow.width,selectShortbow.height);
                } else if(dusting) {
                    g.setColor(Color.GREEN);
                    g.drawRect(bank.x + clientWindow.x -5,bank.y + clientWindow.y - 5,bank.width,bank.height);
                    g.drawRect(bankExit.x + clientWindow.x -5,bankExit.y + clientWindow.y - 5,bankExit.width,bankExit.height);
                    g.drawRect(bankSlot1.x + clientWindow.x -5,bankSlot1.y + clientWindow.y - 5,bankSlot1.width,bankSlot1.height);
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



    public Rectangle getPlayScreen() {
        return playScreen;
    }

    private void setClientHeaderSize() {
        if(loginPropertiesLoader.getClientName().equals("OSBuddy Guest - Guest")) {
            clientHeaderSize = 32;
        } else if(loginPropertiesLoader.getClientName().equals("NoxPlayer1")) {
            clientHeaderSize = 34;
        } else {
            clientHeaderSize = 32; //for rsps
        }
    }

    private void setUpInventory() { //add different rsps clients here (this will work for all normal osrs sized clients, runex/spk/osbuddy etc set in fixed mode

        if(loginPropertiesLoader.getClientType().equals("mobile")) {
            inventoryTopLeft = new Point(clientWindowTopLeft.x + 696, clientWindowTopLeft.y + 254);
            inventoryBottomRight = new Point(clientWindowTopLeft.x + 901, clientWindowTopLeft.y + 536);

        } else if (loginPropertiesLoader.getClientType().equals("desktop_rsps")) {
            inventoryTopLeft = new Point(clientWindowTopLeft.x + 553, clientWindowTopLeft.y + 232);
            inventoryBottomRight = new Point(clientWindowTopLeft.x + 740, clientWindowTopLeft.y + 492);
        } else if (loginPropertiesLoader.getClientType().equals("desktop")) {
            inventoryTopLeft = new Point(clientWindowTopLeft.x + 553, clientWindowTopLeft.y + 232);
            inventoryBottomRight = new Point(clientWindowTopLeft.x + 740, clientWindowTopLeft.y + 492);
        }
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

    public void setMiningLocation(String miningLocation) {
        this.miningLocation = miningLocation;
    }

    public void setDusting(boolean dusting) {
        this.dusting = dusting;
    }

    public void resetDisplay() { ///FIX THIS TO CLEAR GUI
        mining = false;
        fletching = false;
        dusting = false;
        //setupGui();
        //repaint();
    }
}




