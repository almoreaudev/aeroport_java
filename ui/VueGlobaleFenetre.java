package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class VueGlobaleFenetre extends JFrame {

    public VueGlobaleFenetre() {
        setTitle("Vue Globale");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Barre de navigation
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new FlowLayout());

        JButton ventesButton = new JButton("Bilan des ventes");
        JButton personnelButton = new JButton("Liste du personnel");
        JButton clientsButton = new JButton("Liste des clients");

        navPanel.add(ventesButton);
        navPanel.add(personnelButton);
        navPanel.add(clientsButton);

        add(navPanel, BorderLayout.NORTH);

        // Corps principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Section "Liste des vols"
        JPanel volsPanel = new JPanel();
        volsPanel.setLayout(new BoxLayout(volsPanel, BoxLayout.Y_AXIS));

        JLabel volsLabel = new JLabel("Liste des vols :");
        JTextField filtreField = new JTextField("Filtrer sur la catégorie...", 20);

        volsPanel.add(volsLabel);
        volsPanel.add(filtreField);

        String[] volsColumns = {"Départ", "Arrivé", "Date de départ", "Catégorie", "Retard", "Détail"};
        DefaultTableModel volsModel = new DefaultTableModel(volsColumns, 0);
        JTable volsTable = new JTable(volsModel);
        JScrollPane volsScroll = new JScrollPane(volsTable);

        volsPanel.add(volsScroll);
        mainPanel.add(volsPanel);

        // Section "Liste des avions"
        JPanel avionsPanel = new JPanel();
        avionsPanel.setLayout(new BoxLayout(avionsPanel, BoxLayout.Y_AXIS));

        JLabel avionsLabel = new JLabel("Liste des avions :");

        String[] avionsColumns = {"Code avion", "Nom", "Contrôle de sécurité", "Détail"};
        DefaultTableModel avionsModel = new DefaultTableModel(avionsColumns, 0);
        JTable avionsTable = new JTable(avionsModel);
        JScrollPane avionsScroll = new JScrollPane(avionsTable);

        avionsPanel.add(avionsLabel);
        avionsPanel.add(avionsScroll);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espace entre les deux tables
        mainPanel.add(avionsPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Exemple de données (tu pourras les remplacer par tes DAO)
        volsModel.addRow(new Object[]{"Paris", "Edimbourg", "17/06/2025", "Moyen courrier", "Oui", "Voir le détail"});
        avionsModel.addRow(new Object[]{"AEC", "A380", "En retard", "Voir le détail"});

        // Gestion du clic sur "Voir le détail" dans la table des vols
        volsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = volsTable.rowAtPoint(e.getPoint());
                int column = volsTable.columnAtPoint(e.getPoint());

                if (column == 5) { // colonne "Détail"
                    JOptionPane.showMessageDialog(null, "Détail du vol : " + volsTable.getValueAt(row, 0));
                }
            }
        });

        // Gestion du clic sur "Voir le détail" dans la table des avions
        avionsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = avionsTable.rowAtPoint(e.getPoint());
                int column = avionsTable.columnAtPoint(e.getPoint());

                if (column == 3) { // colonne "Détail"
                    JOptionPane.showMessageDialog(null, "Détail de l'avion : " + avionsTable.getValueAt(row, 0));
                }
            }
        });

        // Exemple d'action sur le champ de filtre (non connecté encore à la table)
        filtreField.addActionListener(e -> {
            String filtre = filtreField.getText();
            JOptionPane.showMessageDialog(null, "Filtrage sur la catégorie : " + filtre);
            // Ici tu pourras appliquer ton vrai filtre
        });

        setVisible(true);
    }
}
