package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.BilletDAO;

import java.awt.*;
import java.util.List;
import java.time.format.DateTimeFormatter;

import models.Utilisateur;
import models.BilletAvion;
import dao.VolDAO;
import models.Vol;

public class DetailUtilisateurFenetre extends JFrame {

    private JTable volTable;
    private DefaultTableModel tableModel;

    public DetailUtilisateurFenetre(Utilisateur utilisateur) {
        setTitle("Détails de l'utilisateur");
        setSize(800, 400);
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
        String[] columnNames = {"VolId", "Départ", "Arrivée", "Date de départ", "Nom", "Prénom", "Catégorie Passager", "Prix"};
         // On peut ajouter d'autres colonnes si nécessaire
        tableModel = new DefaultTableModel(columnNames, 0);
        volTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(volTable);


        List<BilletAvion> billets = new BilletDAO().getBilletsByIdUtilisateur(utilisateur.getIdUtilisateur());
        // Remplir le tableau avec les vols
        for (BilletAvion b : billets) {
            int idVol = b.getIdVol();
            System.out.println("Billet ID: " + b.getIdBillet() + ", Vol ID: " + idVol);
            // On peut récupérer le vol associé au billet
            Vol vol = new VolDAO().getVolById(idVol);
            Object[] rowData = {
                vol.getIdVol(),
                vol.getAeroportDepart().getCodeAeroport() + " - " + vol.getAeroportDepart().getVille(),
                vol.getAeroportArrive().getCodeAeroport() + " - " + vol.getAeroportArrive().getVille(),
                vol.getDateDepart(),
                b.getNom(), 
                b.getPrenom(),
                b.getCodeCategorie(),
                b.getPrix()
                
            };
            tableModel.addRow(rowData);
        }

        // Ajout des composants à la fenêtre
        add(infoPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}
