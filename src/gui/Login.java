package gui;

import database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel loginPanel;
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame frame;

    public Login() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print(authenticate(usernameField.getText(),passwordField.getText()));
                if(authenticate(usernameField.getText(),passwordField.getText())) {

                }

            }
        });
    }

    public void loadPanel() {
        frame = new JFrame("Login");
        frame.setContentPane(new Login().loginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    private boolean authenticate(String username, String password) {
        if(Database.getUSERNAME().equals(username) && Database.getPASSWORD().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
