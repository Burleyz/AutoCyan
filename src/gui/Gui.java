package gui;

import data.Data;
import javafx.scene.shape.Line;
import utils.Output;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gui extends JFrame {

    private Window w;
    private Point clientWindowTopLeft;
    private Point clientWindowBottomRight;
    private Rectangle clientWindow;

    //following rectangles are for checking if past login screens
    private Rectangle loginScreenCheckRectangle; //this will check which stage of login we are at


    public Gui() {

    }

    public void setupGui() {


        //Sets up client window Rectangle
        clientWindow = new Rectangle();
        clientWindow.setFrameFromDiagonal(clientWindowTopLeft, clientWindowBottomRight);

        loginScreenCheckRectangle = new Rectangle();
        loginScreenCheckRectangle.setFrameFromDiagonal(new Point(clientWindowTopLeft.x + 300, clientWindowTopLeft.y + 350), new Point(clientWindowBottomRight.x - 300, clientWindowBottomRight.y - 200)); //makes rectangle to check between 3 login screens

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
}




