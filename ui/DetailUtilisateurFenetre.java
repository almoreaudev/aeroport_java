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
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal vertical
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Infos utilisateur
        infoPanel.add(new JLabel("Nom : " + utilisateur.getNom()));
        infoPanel.add(new JLabel("Prénom : " + utilisateur.getPrenom()));
        infoPanel.add(new JLabel("Email : " + utilisateur.getEmail()));
        infoPanel.add(new JLabel("Adresse : " + utilisateur.getAdresse()));
        infoPanel.add(new JLabel("Numéro de passeport : " + utilisateur.getNumPasseport()));
        infoPanel.add(new JLabel("Numéro de carte d'identité : " + utilisateur.getNumCarteIdentite()));
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Tableau "A venir"
        String[] columnNames = {"VolId", "Départ", "Arrivée", "Date de départ", "Nom", "Prénom", "Catégorie Passager", "Prix"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable volTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(volTable);

        List<BilletAvion> billets = new BilletDAO().getBilletsByIdUtilisateurAndStatut(utilisateur.getIdUtilisateur(), "A venir");
        for (BilletAvion b : billets) {
            int idVol = b.getIdVol();
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
        infoPanel.add(new JLabel("Billets à venir :"));
        infoPanel.add(scrollPane);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Tableau "Finit"
        String[] columnNamesFinit = {"VolId", "Départ", "Arrivée", "Date de départ", "Nom", "Prénom", "Catégorie Passager", "Prix"};
        DefaultTableModel tableModelFinit = new DefaultTableModel(columnNamesFinit, 0);
        JTable volTableFinit = new JTable(tableModelFinit);
        JScrollPane scrollPaneFinit = new JScrollPane(volTableFinit);

        List<BilletAvion> billetsFinit = new BilletDAO().getBilletsByIdUtilisateurAndStatut(utilisateur.getIdUtilisateur(), "Finit");
        for (BilletAvion b : billetsFinit) {
            int idVol = b.getIdVol();
            Vol vol = new VolDAO().getVolById(idVol);
            Object[] rowDataFinit = {
                vol.getIdVol(),
                vol.getAeroportDepart().getCodeAeroport() + " - " + vol.getAeroportDepart().getVille(),
                vol.getAeroportArrive().getCodeAeroport() + " - " + vol.getAeroportArrive().getVille(),
                vol.getDateDepart(),
                b.getNom(),
                b.getPrenom(),
                b.getCodeCategorie(),
                b.getPrix()
            };
            tableModelFinit.addRow(rowDataFinit);
        }

        JPanel finitPanel = new JPanel(new BorderLayout());
        finitPanel.setBorder(BorderFactory.createTitledBorder("Billets Finit"));
        finitPanel.add(scrollPaneFinit, BorderLayout.CENTER);

        infoPanel.add(finitPanel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Bouton fermer
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());
        infoPanel.add(closeButton);

        // On ajoute le panel principal
        add(infoPanel, BorderLayout.CENTER);

        setVisible(true);
    }

}
