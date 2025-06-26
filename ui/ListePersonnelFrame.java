package ui;

import java.util.ArrayList;

import dao.PersonnelDAO;
import models.Personnel;

public class ListePersonnelFrame extends javax.swing.JFrame {

    public ListePersonnelFrame() {
        initComponents();
    }

    /* 
                // Liste des vols pour l'avion
        ArrayList<Vol> vols = new AvionDAO().getVolsByIdAvionForNextWeek(this.avion.getIdAvion());
        // Tableau avec "Départ" (CDG Paris), "Arrivée" (DBX Dubai), "Date de départ", "Catégorie vol"))
        // Un tableau de cette forme :
        String[] volsColumns = {"Départ", "Arrivé", "Date de départ", "Catégorie", "Détail"};
        DefaultTableModel volsModel = new DefaultTableModel(volsColumns, 0);
        JTable volsTable = new JTable(volsModel);
        JScrollPane volsScroll = new JScrollPane(volsTable);

        add(volsScroll);
        //add(volsPanel);
        // Autres composants peuvent être ajoutés ici

        for (Vol vol : vols) {
            Object[] rowData = {
                vol.getAeroportDepart().getCodeAeroport() + " - " + vol.getAeroportDepart().getVille(),
                vol.getAeroportArrive().getCodeAeroport() + " - " + vol.getAeroportArrive().getVille(),
                vol.getDateDepart(),
                vol.getCodeTypeVol(),
                "Voir le détail"
            };
            volsModel.addRow(rowData);
        }
    */

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Liste du Personnel");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialisation des composants de l'interface graphique
        javax.swing.JPanel mainPanel = new javax.swing.JPanel();
        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.Y_AXIS));
        javax.swing.JLabel titleLabel = new javax.swing.JLabel("Liste du Personnel");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        mainPanel.add(titleLabel);

        // Définir les colonnes du tableau
        String[] columnNames = {"ID", "Nom", "Prénom", "Date naissance", "Type personnel"};
        javax.swing.table.DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(columnNames, 0);
        javax.swing.JTable personnelTable = new javax.swing.JTable(tableModel);
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(personnelTable);
        mainPanel.add(scrollPane);

        // Récupérer les données
        ArrayList<Personnel> personnelsList = new PersonnelDAO().getAllPersonnels();

        // Remplir le tableau
        for (Personnel personnel : personnelsList) {
            Object[] rowData = {
                personnel.getIdPersonnel(),
                personnel.getNom(),
                personnel.getPrenom(),
                personnel.getDateNaissance(),
                personnel.getCodeType().getType()
            };
            tableModel.addRow(rowData);
        }

        // Ajouter le mainPanel à la fenêtre
        getContentPane().add(mainPanel);
    }
 
}
