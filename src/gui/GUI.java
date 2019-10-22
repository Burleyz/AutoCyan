package gui;

import java.awt.*;

public class GUI {

    private Window w;
    private Point topLeft;
    private Point bottomRight;
    private Rectangle rect;
    private final double VERSION = 0.1;
    private Rectangle tile;

    public GUI() {

        topLeft = new Point(502,256);
        bottomRight = new Point(1395,758);

        rect = new Rectangle();
        rect.setFrameFromDiagonal(topLeft,bottomRight);

        w = new Window(null)

        {
            @Override
            public void paint(Graphics g)
            {
                final Font font = getFont().deriveFont(48f);
                g.setFont(font);
                g.setColor(Color.RED);
                g.drawRect(rect.x,rect.y,rect.width,rect.height);

                final String message = "AutoCyan - Version: " + VERSION;
                FontMetrics metrics = g.getFontMetrics();
                g.drawString(message, (int)rect.getX() + 5, (int)rect.getY() + 40);


                //Mining bot

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


}

