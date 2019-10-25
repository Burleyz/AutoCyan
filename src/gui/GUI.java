package gui;

import utils.Time;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private Window w;
    private Point topLeft;
    private Point bottomRight;
    private Point inventTopLeft;
    private Point inventBottomRight;
    private Point inventInnerBottomRight;
    private Point inventInnerTopLeft;
    private Rectangle screenRect;
    private Rectangle inventOuterRect;
    private Rectangle inventInnerRect;
    private final double VERSION = 0.1;
    private Rectangle tile;


    public GUI() {

        //topLeft = new Point(502,256);
        //bottomRight = new Point(1395,758);

        setScreenBounds();
        setInventOuterBounds();
        setInventInnerBounds();

        screenRect = new Rectangle();
        screenRect.setFrameFromDiagonal(topLeft, bottomRight);

        inventOuterRect = new Rectangle();
        inventOuterRect.setFrameFromDiagonal(inventTopLeft, inventBottomRight);

        inventInnerRect = new Rectangle();
        inventInnerRect.setFrameFromDiagonal(inventInnerTopLeft,inventInnerBottomRight);

        w = new Window(null)

        {
            @Override
            public void paint(Graphics g)
            {
                final Font font = getFont().deriveFont(48f);
                g.setFont(font);
                g.setColor(Color.RED);

                g.drawRect(screenRect.x, screenRect.y, screenRect.width, screenRect.height);
                g.drawRect(inventOuterRect.x, inventOuterRect.y, inventOuterRect.width, inventOuterRect.height);
                g.drawRect(inventInnerRect.x, inventInnerRect.y, inventInnerRect.width, inventInnerRect.height);

                final String message = "AutoCyan - Version: " + VERSION;
                g.drawString(message, (int) screenRect.getX() + 5, (int) screenRect.getY() + 40);

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

    public void setScreenBounds() {
        System.out.println("Getting mouse position in 2 seconds! (TopLeft)");
        Time.rest(2000);
        topLeft = getMousePos();
        System.out.println("Getting mouse position in 2 seconds! (BottomRight)");
        Time.rest(2000);
        bottomRight = getMousePos();
    }

    public void setInventOuterBounds() {
        System.out.println("Getting mouse position in 2 seconds! (Invent Outer Top Left)");
        Time.rest(2000);
        inventTopLeft = getMousePos();
        System.out.println("Getting mouse position in 2 seconds! (Invent Outer Bottom Right)");
        Time.rest(2000);
        inventBottomRight = getMousePos();
    }

    public void setInventInnerBounds() {
        System.out.println("Getting mouse position in 2 seconds! (Invent Inner Top Left)");
        Time.rest(2000);
        inventInnerTopLeft = getMousePos();
        System.out.println("Getting mouse position in 2 seconds! (Invent Inner Bottom Right)");
        Time.rest(2000);
        inventInnerBottomRight = getMousePos();
    }

    public Point getInventTopLeft() {
        return inventTopLeft;
    }

    public Point getInventBottomRight() {
        return inventBottomRight;
    }

    public Rectangle getInventInnerRect() {
        return inventInnerRect;
    }

    public Point getInventInnerTopLeft() {
        return inventInnerTopLeft;
    }
}




