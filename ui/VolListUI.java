package ui;

import dao.VolDAO;
import models.Vol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VolListUI extends JFrame {

    private JTable volTable;
    private DefaultTableModel tableModel;

    public VolListUI() {
        setTitle("Liste des vols");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // centrer la fenêtre

        initComponents();
        loadFlights();
    }

    private void initComponents() {
        // Création du modèle de table
        String[] columnNames = {"ID", "Numéro de vol", "Départ", "Arrivée", "Départ (heure)", "Arrivée (heure)", "Statut", "Avion"};
        tableModel = new DefaultTableModel(columnNames, 0);
        volTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(volTable);

        add(scrollPane, BorderLayout.CENTER);
    }

     private void loadFlights() {
        VolDAO volDAO = new VolDAO();
        List<Vol> vols = volDAO.getAllVols();

        for (Vol vol : vols) {
            Object[] rowData = {
                    vol.getIdVol(),
                    vol.getDateDepart(),
                    vol.getDateArrivee(),
                    vol.getDistance(),
                    vol.getStatut(),
                    vol.getCarburantNecessaire(),
                    vol.getDuree(),
                    vol.getIdAvion(),
                    vol.getCodeTypeVol(),
                    vol.getCodeAeroportDepart(),
                    vol.getCodeAeroportArrive()
            };
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VolListUI().setVisible(true);
        });
    }
}
