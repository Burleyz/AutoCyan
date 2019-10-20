package gui;

import javax.swing.*;

public class ClientWrap {
    private JPanel mainPanel;
    private JButton startButton;
    private JButton stopButton;
    private JFrame frame;

    public ClientWrap() {

    }

    public void loadPanel() {
        frame = new JFrame("Wrapper");
        frame.setContentPane(new ClientWrap().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
