package ui;

import dao.VolDAO;
import models.Vol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class VolListUI extends JFrame {

    private JTable volTable;
    private DefaultTableModel tableModel;
    private List<Vol> volList = new ArrayList<>(); // Liste pour retrouver les objets Vol

    public VolListUI() {
        setTitle("Liste des vols");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrer la fenêtre

        initComponents();
        loadFlights();
    }

    private void initComponents() {
        // Création du modèle de table
        String[] columnNames = {"ID", "Lieu de départ", "Code Aéroport Départ", "Lieu d'arrivée", "Code Aéroport Arrivée"};
        tableModel = new DefaultTableModel(columnNames, 0);
        volTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(volTable);

        add(scrollPane, BorderLayout.CENTER);

        // Bouton Hello World
        JButton selectionButton = new JButton("Hello World");
        selectionButton.addActionListener(e -> JOptionPane.showMessageDialog(VolListUI.this, "Hello World !"));

        // Bouton Voir Détails
        JButton detailButton = new JButton("Voir Détails");
        detailButton.addActionListener(e -> showFlightDetails());

        // Panneau bas pour les boutons
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(selectionButton);
        bottomPanel.add(detailButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadFlights() {
        VolDAO volDAO = new VolDAO();
        volList = volDAO.getAllVols(); // Remplir la liste des vols

        for (Vol vol : volList) {
            Object[] rowData = {
                    vol.getIdVol(),
                    vol.getAeroportDepart().getNom(),
                    vol.getAeroportDepart().getCodeAeroport(),
                    vol.getAeroportArrive().getNom(),
                    vol.getAeroportArrive().getCodeAeroport()
            };
            tableModel.addRow(rowData);
        }
    }

    private void showFlightDetails() {
        int selectedRow = volTable.getSelectedRow();
        if (selectedRow != -1) {
            int volId = (int) tableModel.getValueAt(selectedRow, 0);
            Vol selectedVol = volList.stream()
                    .filter(vol -> vol.getIdVol() == volId)
                    .findFirst()
                    .orElse(null);

            if (selectedVol != null) {
                VolDetailFrame detailFrame = new VolDetailFrame(selectedVol);
                detailFrame.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un vol.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VolListUI().setVisible(true));
    }
}
