package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.*;

import java.util.List;
import dao.VolDAO;
import models.Vol;

import dao.AvionDAO;
import models.Avion;

public class VueGlobaleFenetre extends JFrame {
    private DefaultTableModel volsModel;
    private JTable volsTable;

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

        ventesButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                BilanVenteFrame frame = new BilanVenteFrame();
                frame.setVisible(true);
            });
        });

        personnelButton.addActionListener(e -> {
            ListePersonnelFrame personnelFrame = new ListePersonnelFrame();
            personnelFrame.setVisible(true);
        });

        clientsButton.addActionListener(e -> {
            ListeUserFrame userFrame = new ListeUserFrame();
            userFrame.setVisible(true);
        });

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
        volsPanel.add(volsLabel);


        String[] volsColumns = {"IdVol", "Départ", "Arrivé", "Date de départ", "Catégorie", "Retard", "Détail"};
        volsModel = new DefaultTableModel(volsColumns, 0);
        volsTable = new JTable(volsModel);
        JScrollPane volsScroll = new JScrollPane(volsTable);

        volsPanel.add(volsScroll);
        mainPanel.add(volsPanel);

        // Section "Liste des avions"
        JPanel avionsPanel = new JPanel();
        avionsPanel.setLayout(new BoxLayout(avionsPanel, BoxLayout.Y_AXIS));

        // Création du menu déroulant pour la catégorie
        String[] categories = {"Tous", "Courts-courriers", "Moyens-courriers", "Longs-courriers"};
        JComboBox<String> categorieComboBox = new JComboBox<>(categories);
        volsPanel.add(new JLabel("Filtrer sur la catégorie : "));
        volsPanel.add(categorieComboBox);

        // Préparation du filtre dynamique
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(volsModel);
        volsTable.setRowSorter(sorter);

        // Ajout du listener pour filtrer
        categorieComboBox.addActionListener(e -> {
            String selectedCategory = (String) categorieComboBox.getSelectedItem();
            if (selectedCategory.equals("Tous")) {
                sorter.setRowFilter(null); // Affiche tout
            } else {
                sorter.setRowFilter(RowFilter.regexFilter(selectedCategory));
            }
        });



        JLabel avionsLabel = new JLabel("Liste des avions :");


        String[] avionsColumns = {"Code avion", "Nom","Entretien", "Contrôle de sécurité", "Détail"};
        DefaultTableModel avionsModel = new DefaultTableModel(avionsColumns, 0);
        JTable avionsTable = new JTable(avionsModel);
        JScrollPane avionsScroll = new JScrollPane(avionsTable);

        avionsPanel.add(avionsLabel);
        avionsPanel.add(avionsScroll);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espace entre les deux tables
        mainPanel.add(avionsPanel);

        add(mainPanel, BorderLayout.CENTER);

        List<Vol> vols = new VolDAO().getAllVols();
        for (Vol vol : vols) {
            Object[] rowData = {
                vol.getIdVol(),
                vol.getAeroportDepart().getCodeAeroport() + " - " + vol.getAeroportDepart().getVille(),
                vol.getAeroportArrive().getCodeAeroport() + " - " + vol.getAeroportArrive().getVille(),
                vol.getDateDepart(),
                vol.getCodeTypeVol().getCategorie(),
                "Oui",
                "Voir le détail"
            };
            volsModel.addRow(rowData);
        }

        List<Avion> avions = new AvionDAO().getAllAvions();
        for (Avion avion : avions) {
            // Vérification du contrôle de sécurité
            
            Object[] rowData = {
                avion.getIdAvion(),
                avion.getTypeAvion(),
                avion.isEntretienEnRetard() ? "Oui" : "Non",
                avion.isControleEnRetard() ? "Oui" : "Non",
                "Voir le détail"
            };
            avionsModel.addRow(rowData);
        }

        // Gestion du clic sur "Voir le détail" dans la table des vols
        volsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = volsTable.rowAtPoint(e.getPoint());
                int column = volsTable.columnAtPoint(e.getPoint());

                if (column == 6) { // colonne "Détail"
                    int idVol = (int) volsTable.getValueAt(row, 0);
                    Vol vol = new VolDAO().getVolById(idVol);
                    VolDetailFrame detailFrame = new VolDetailFrame(vol);
                    detailFrame.setVisible(true);
                }
            }
        });

        // Gestion du clic sur "Voir le détail" dans la table des avions
        avionsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = avionsTable.rowAtPoint(e.getPoint());
                int column = avionsTable.columnAtPoint(e.getPoint());

                if (column == 4) { // colonne "Détail"
                    int idAvion = (int) avionsTable.getValueAt(row, 0);
                    Avion avion = new AvionDAO().getAvionById(idAvion);
                    detailAvion detailFrame = new detailAvion(avion);
                    detailFrame.setVisible(true);
                }
            }
        });
        setVisible(true);
    }
}
