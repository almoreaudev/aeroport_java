package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

public class UserInputApp extends JFrame {

    private static final String CONFIG_FILE = "user.properties";
    private static final String KEY_LAST_USER = "lastUser";

    private JTextField userField;
    private JButton saveButton;

    public UserInputApp() {
        super("User Input");

        // Interface simple
        userField = new JTextField(20);
        saveButton = new JButton("Valider");

        setLayout(new FlowLayout());
        add(new JLabel("Utilisateur :"));
        add(userField);
        add(saveButton);

        // Action sur clic du bouton
        saveButton.addActionListener(e -> saveUser());

        // Action à la fermeture : suppression du fichier
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                deleteConfigFile();
                System.exit(0);
            }
        });

        loadUser(); // Charger si jamais une valeur existe

        setSize(300, 120);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void saveUser() {
        String username = userField.getText().trim();
        if (!username.isEmpty()) {
            Properties props = new Properties();
            props.setProperty(KEY_LAST_USER, username);
            try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
                props.store(fos, "Temporary user config");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Utilisateur sauvegardé !");
        }
    }

    private void loadUser() {
        File configFile = new File(CONFIG_FILE);
        if (configFile.exists()) {
            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream(configFile)) {
                props.load(fis);
                String user = props.getProperty(KEY_LAST_USER, "");
                userField.setText(user);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void deleteConfigFile() {
        File configFile = new File(CONFIG_FILE);
        if (configFile.exists()) {
            configFile.delete();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserInputApp::new);
    }
}