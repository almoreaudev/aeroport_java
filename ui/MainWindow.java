package ui;

import dao.VolDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class MainWindow extends JFrame {

    private static final String CONFIG_FILE = "user.properties";

    private JList<String> flightList;
    private JButton connectButton;

    public MainWindow() {
        super("Gestion Aéroport");

        setLayout(new BorderLayout());

        // --- Partie gauche : Liste des vols ---
        DefaultListModel<String> listModel = new DefaultListModel<>();

        VolDAO volDAO = new VolDAO();
        List<String> flights = volDAO.getAllVols().stream()
                .map(vol -> "Vol ID: " + vol.getIdVol() + " - " + vol.getDateDepart() + " à " + vol.getDateArrivee())
                .toList();
        for (String flight : flights) {
            listModel.addElement(flight);
        }

        flightList = new JList<>(listModel);
        add(new JScrollPane(flightList), BorderLayout.WEST);

        // --- Partie droite : Bouton connexion ---
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        connectButton = new JButton("Connexion");
        rightPanel.add(connectButton);
        add(rightPanel, BorderLayout.NORTH);

        // --- Action du bouton connexion ---
        connectButton.addActionListener(e -> openUserWindow());

        // --- Gestion de la fermeture de fenêtre ---
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                deleteConfigFile();
                System.exit(0);
            }
        });

        System.out.println("Bienvenue dans l'application de gestion d'aéroport !");


        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openUserWindow() {
        SwingUtilities.invokeLater(UserInputApp::new);
    }


    private void deleteConfigFile() {
        File configFile = new File(CONFIG_FILE);
        if (configFile.exists()) {
            configFile.delete();
            System.out.println("Fichier de configuration supprimé.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
