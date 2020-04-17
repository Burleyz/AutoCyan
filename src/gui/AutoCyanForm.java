package gui;

/**
 *
 * @author Cyan
 */


import main.Startup;

import javax.swing.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jack Griffiths
 */
public class AutoCyanForm extends javax.swing.JFrame {

    private boolean authenticated;
    private Thread scriptThread;
    private boolean scriptLive;
    private Logger logger;
    private String scriptRunning;

    /**
     * Creates new form AutoCyanForm
     */
    public AutoCyanForm() {
        initComponents();
        scriptLive = false;
        scriptRunning = "none";
        logger = Startup.getLogger();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        autoCyanTitle = new javax.swing.JLabel();
        version = new javax.swing.JLabel();
        runButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        miningCheckBox = new javax.swing.JCheckBox();
        fletchingCheckBox = new javax.swing.JCheckBox();
        herbloreCheckBox = new javax.swing.JCheckBox();
        dustCheckBox = new javax.swing.JCheckBox();
        scriptComboBox = new javax.swing.JComboBox<>();
        authenticateButton = new javax.swing.JButton();
        authenticationField = new javax.swing.JTextField();
        authenticatedLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        autoCyanTitle.setText("AutoCyan: ");

        version.setText("Version");

        runButton.setText("Run");
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("Pause");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        miningCheckBox.setText("ACMining");
        miningCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miningCheckBoxActionPerformed(evt);
            }
        });

        fletchingCheckBox.setText("ACFletching");
        fletchingCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fletchingCheckBoxActionPerformed(evt);
            }
        });

        herbloreCheckBox.setText("ACHerblore");
        herbloreCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                herbloreCheckBoxActionPerformed(evt);
            }
        });

        dustCheckBox.setText("ACDuster");
        dustCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dustCheckBoxActionPerformed(evt);
            }
        });

        scriptComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a script!" }));
        scriptComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scriptComboBoxActionPerformed(evt);
            }
        });

        authenticateButton.setText("Authenticate");
        authenticateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authenticateButtonActionPerformed(evt);
            }
        });

        authenticationField.setText("Licence Key");

        authenticatedLabel.setText("No authentication");

        statusLabel.setText("Sleeping...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(runButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pauseButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(stopButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(statusLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                                .addComponent(authenticatedLabel)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(autoCyanTitle)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(version))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(miningCheckBox)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(fletchingCheckBox)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(herbloreCheckBox)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(dustCheckBox))
                                                        .addComponent(scriptComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(authenticateButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(authenticationField)))
                                                .addGap(0, 80, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(autoCyanTitle)
                                                        .addComponent(version))
                                                .addGap(14, 14, 14)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(authenticateButton)
                                                        .addComponent(authenticationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(miningCheckBox)
                                                        .addComponent(fletchingCheckBox)
                                                        .addComponent(herbloreCheckBox)
                                                        .addComponent(dustCheckBox))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(scriptComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(runButton)
                                                        .addComponent(pauseButton)
                                                        .addComponent(stopButton)
                                                        .addComponent(statusLabel)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(authenticatedLabel)))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void initThread(int script, int selection) {
        scriptThread = new Thread() {
            public void run() {
               logger.info("Thread Running");
                scriptLive = true;
                Startup.loadScript(script, selection);
            }
        };
    }

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {

        //Startup.updateRuntimeInTable();

        if(scriptLive) {
            scriptThread.resume();
            logger.info("Resuming script!");
            statusLabel.setText("Running...");
        } else {
            if (authenticated) {

                if (miningCheckBox.isSelected()) {
                    switch (scriptComboBox.getSelectedIndex()) {
                        case 0:
                            initThread(1, 1);
                            scriptRunning = "mining_varrock";
                            break;

                        case 1:
                            initThread(1, 2);
                            scriptRunning = "mining_guild";
                            break;
                    }

                } else if (fletchingCheckBox.isSelected()) {
                    switch (scriptComboBox.getSelectedIndex()) {
                        case 0:
                            initThread(2, 1);
                            scriptRunning = "fletching_shortbows";
                            break;

                        case 1:
                            initThread(2, 2);
                            scriptRunning = "fletching_longbows";
                            break;

                        case 2:
                            initThread(2, 3);
                            scriptRunning = "fletching_bolt_tips";
                            break;

                        case 3:
                            initThread(2, 4);
                            scriptRunning = "fletching_bolts_unf";
                            break;
                    }
                } else if (dustCheckBox.isSelected()) {
                    initThread(3, 0); //selection isnt needed here
                    scriptRunning = "dusting";
                } else if (herbloreCheckBox.isSelected()) {
                    initThread(4, 0);//selection isnt needed here
                    scriptRunning = "herblore";
                } else {
                    logger.warning("No script selected");
                }

                scriptThread.start();
                statusLabel.setText("Running...");
            }
        }

    }

    private void miningCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        scriptComboBox.removeAllItems();
        scriptComboBox.addItem("Varrock East"); //0
        scriptComboBox.addItem("Mining Guild"); //1
        fletchingCheckBox.setSelected(false);
        herbloreCheckBox.setSelected(false);
        dustCheckBox.setSelected(false);


    }

    private void fletchingCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        scriptComboBox.removeAllItems();
        scriptComboBox.addItem("Shortbows"); //0
        scriptComboBox.addItem("Longbows"); //1
        scriptComboBox.addItem("Bolt Tips"); //2
        scriptComboBox.addItem("Unfinished Bolts"); //3
        miningCheckBox.setSelected(false);
        herbloreCheckBox.setSelected(false);
        dustCheckBox.setSelected(false);
    }

    private void herbloreCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        scriptComboBox.removeAllItems();
        scriptComboBox.addItem("Herb Cleaning"); //0
        fletchingCheckBox.setSelected(false);
        miningCheckBox.setSelected(false);
        dustCheckBox.setSelected(false);
    }

    private void scriptComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

    }

    private void dustCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        scriptComboBox.removeAllItems();
        scriptComboBox.addItem("Dust"); //0
        fletchingCheckBox.setSelected(false);
        herbloreCheckBox.setSelected(false);
        miningCheckBox.setSelected(false);
    }

    private void authenticateButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        try {
            if(Startup.getData().checkLicence(authenticationField.getText())) {
                authenticated = true;
                logger.info("Licence key (" + authenticationField.getText() + ") accepted - Authenticated!");
                authenticatedLabel.setText("Authenticated");
                Startup.setCyanAuthenticated(true);

            } else {
                authenticated = false;
                logger.warning("Licence key (" + authenticationField.getText() + ") is not valid!");
                authenticatedLabel.setText("No authentication");
            }
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE,"NoSuchAlgorithmException", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE,"IOException", e);
        }
    }

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        scriptRunning = "none";
        scriptLive = false;
        scriptThread.stop();
        Startup.getDisplay().resetDisplay(); //doesn't work
        logger.info("Script stopped.");
        statusLabel.setText("Sleeping...");
        scriptThread = null;

    }

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        scriptThread.suspend();
        logger.info("Script paused.");
        statusLabel.setText("Paused.");
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AutoCyanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutoCyanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutoCyanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutoCyanForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AutoCyanForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton authenticateButton;
    private javax.swing.JLabel authenticatedLabel;
    private javax.swing.JTextField authenticationField;
    private javax.swing.JLabel autoCyanTitle;
    private javax.swing.JCheckBox dustCheckBox;
    private javax.swing.JCheckBox fletchingCheckBox;
    private javax.swing.JCheckBox herbloreCheckBox;
    private javax.swing.JCheckBox miningCheckBox;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton runButton;
    private javax.swing.JComboBox<String> scriptComboBox;
    private javax.swing.JButton stopButton;
    private javax.swing.JLabel version;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration


    public void setVersionLabel(String versionString) {
        this.version.setText(versionString);
    }

    public JTextField getAuthenticationField() {
        return authenticationField;
    }

    public String getScriptRunning() {
        return scriptRunning;
    }
}
