package ui;

import models.Vol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VolListSwing extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public VolListSwing(List<Vol> volList) {
        setTitle("Liste des Vols");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrer la fenêtre

        // Création des colonnes
        String[] columnNames = {"ID Vol", "Date Départ", "Date Arrivée", "Distance (km)", "Statut"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Remplir le tableau
        for (Vol vol : volList) {
            Object[] rowData = {
                    vol.getIdVol(),
                    vol.getDateDepart(),
                    vol.getDateArrivee(),
                    vol.getDistance(),
                    vol.getStatut()
            };
            tableModel.addRow(rowData);
        }

        // Création du tableau
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
    }

    // Méthode principale pour tester l'interface
    public static void main(String[] args) {
        List<Vol> vols = new ArrayList<>();

        // Exemple de données
        Vol vol1 = new Vol();
        vol1.setIdVol(1);
        vol1.setDateDepart(LocalDate.of(2025, 6, 24));
        vol1.setDateArrivee(LocalDate.of(2025, 6, 25));
        vol1.setDistance(1500);
        vol1.setStatut("Prévu");

        Vol vol2 = new Vol();
        vol2.setIdVol(2);
        vol2.setDateDepart(LocalDate.of(2025, 7, 1));
        vol2.setDateArrivee(LocalDate.of(2025, 7, 2));
        vol2.setDistance(2500);
        vol2.setStatut("En vol");

        vols.add(vol1);
        vols.add(vol2);

        SwingUtilities.invokeLater(() -> {
            VolListSwing frame = new VolListSwing(vols);
            frame.setVisible(true);
        });
    }
}
