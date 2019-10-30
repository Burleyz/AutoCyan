package gui;

import utils.Time;

import javax.swing.*;
import java.awt.*;

public class GUI_old extends JFrame {

    private Window w;
    private Point clientWindowTopLeft;
    private Point clientWindowBottomRight;
    private Rectangle clientWindow;

    private Point playScreenTopLeft;
    private Point playScreenBottomRight;
    private Rectangle playScreen;

    private Point inventOuterTopLeft; //although not just the invent, this is what i have named it (see guide image)
    private Point inventOuterBottomRight;
    private Rectangle inventOuter;
    
    private Point inventoryBottomRight;
    private Point inventoryTopLeft;
    private Rectangle inventory;

    private final double VERSION = 0.1;


    public GUI_old() {

        setClientBounds();
        setInventOuterBounds();
        setInventoryBounds();
        setPlayScreenBounds();

        clientWindow = new Rectangle();
        clientWindow.setFrameFromDiagonal(clientWindowTopLeft, clientWindowBottomRight);

        inventOuter = new Rectangle();
        inventOuter.setFrameFromDiagonal(inventOuterTopLeft, inventOuterBottomRight);

        inventory = new Rectangle();
        inventory.setFrameFromDiagonal(inventoryTopLeft, inventoryBottomRight);

        playScreen = new Rectangle();
        playScreen.setFrameFromDiagonal(playScreenTopLeft, playScreenBottomRight);

        w = new Window(null)

        {
            @Override
            public void paint(Graphics g)
            {
                final Font font = getFont().deriveFont(48f);
                g.setFont(font);
                g.setColor(Color.RED);

                g.drawRect(clientWindow.x, clientWindow.y, clientWindow.width, clientWindow.height);
                g.drawRect(inventOuter.x, inventOuter.y, inventOuter.width, inventOuter.height);
                g.drawRect(inventory.x, inventory.y, inventory.width, inventory.height);
                g.drawRect(playScreen.x, playScreen.y, playScreen.width, playScreen.height);

                final String message = "AutoCyan - Data: " + VERSION;
                g.drawString(message, (int) clientWindow.getX() + 5, (int) clientWindow.getY() + 40);

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

    public Point getMousePos() {
        PointerInfo inf = MouseInfo.getPointerInfo();
        Point p = inf.getLocation();
        System.out.println(p.toString());
        return p;
    }

    public void setClientBounds() {
        System.out.println("Getting mouse position in 2 seconds! (Client Top Left)");
        Time.rest(2000);
        clientWindowTopLeft = getMousePos();
        System.out.println("Getting mouse position in 2 seconds! (Client Bottom Right)");
        Time.rest(2000);
        clientWindowBottomRight = getMousePos();
    }

    public void setInventOuterBounds() {
        System.out.println("Getting mouse position in 2 seconds! (Invent Outer Top Left)");
        Time.rest(2000);
        inventOuterTopLeft = getMousePos();
        System.out.println("Getting mouse position in 2 seconds! (Invent Outer Bottom Right)");
        Time.rest(2000);
        inventOuterBottomRight = getMousePos();
    }

    public void setInventoryBounds() {
        System.out.println("Getting mouse position in 2 seconds! (Invent Top Left)");
        Time.rest(2000);
        inventoryTopLeft = getMousePos();
        System.out.println("Getting mouse position in 2 seconds! (Invent Bottom Right)");
        Time.rest(2000);
        inventoryBottomRight = getMousePos();
    }

    public void setPlayScreenBounds() {
        System.out.println("Getting mouse position in 2 seconds! (Play Screen Top Left)");
        Time.rest(2000);
        playScreenTopLeft = getMousePos();
        System.out.println("Getting mouse position in 2 seconds! (Play Screen Bottom Right)");
        Time.rest(2000);
        playScreenBottomRight = getMousePos();
    }

    public Point getInventOuterTopLeft() {
        return inventOuterTopLeft;
    }

    public Point getInventOuterBottomRight() {
        return inventOuterBottomRight;
    }

    public Rectangle getInventory() {
        return inventory;
    }

    public Point getInventoryTopLeft() {
        return inventoryTopLeft;
    }

    public Rectangle getPlayScreen() {
        return playScreen;
    }
}




