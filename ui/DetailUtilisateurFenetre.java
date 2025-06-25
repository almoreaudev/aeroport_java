package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.BilletDAO;

import java.awt.*;
import java.util.List;

import models.Utilisateur;
import models.BilletAvion;

public class DetailUtilisateurFenetre extends JFrame {

    private JTable volTable;
    private DefaultTableModel tableModel;

    public DetailUtilisateurFenetre(Utilisateur utilisateur) {
        setTitle("Détails de l'utilisateur");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Création d'un panel pour les informations utilisateur
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoPanel.add(new JLabel("Nom : " + utilisateur.getNom()));
        infoPanel.add(new JLabel("Prénom : " + utilisateur.getPrenom()));
        infoPanel.add(new JLabel("Email : " + utilisateur.getEmail()));
        infoPanel.add(new JLabel("Adresse : " + utilisateur.getAdresse()));
        infoPanel.add(new JLabel("Numéro de passeport : " + utilisateur.getNumPasseport()));
        infoPanel.add(new JLabel("Numéro de carte d'identité : " + utilisateur.getNumCarteIdentite()));

        // Création du tableau
        String[] columnNames = {"Nom", "Prénom", "idVol"};
        tableModel = new DefaultTableModel(columnNames, 0);
        volTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(volTable);


        List<BilletAvion> billets = new BilletDAO().getBilletsByIdUtilisateur(utilisateur.getIdUtilisateur());
        // Remplir le tableau avec les vols
        for (BilletAvion vol : billets) {
            Object[] rowData = {utilisateur.getNom(), utilisateur.getPrenom(), vol.getIdVol()};
            tableModel.addRow(rowData);
        }

        // Ajout des composants à la fenêtre
        add(infoPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
