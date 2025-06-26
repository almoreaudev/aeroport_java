package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.CarteFideliteDAO;
import dao.UtilisateurDAO;
import models.CarteFidelite;
import models.Utilisateur;

public class ListeUserFrame extends JFrame {

    private ArrayList<Utilisateur> userList = new ArrayList<>();
    private DefaultTableModel tableModel;

    private JTextField nomFilterField;

    public ListeUserFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Liste des Clients");
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Panel de filtre
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Nom :"));
        nomFilterField = new JTextField(10);
        filterPanel.add(nomFilterField);

        JButton filterButton = new JButton("Filtrer");
        filterButton.addActionListener(e -> applyFilter());
        filterPanel.add(filterButton);

        JButton resetButton = new JButton("Réinitialiser");
        resetButton.addActionListener(e -> {
            nomFilterField.setText("");
            applyFilter();
        });
        filterPanel.add(resetButton);

        // Ajout d'un bouton "filtrer miles > 6000"
        JButton filterMilesButton = new JButton("Filtrer Miles > 6000");
        filterMilesButton.addActionListener(e -> {
            List<Utilisateur> filteredUsers = userList.stream()
                    .filter(user -> {
                        CarteFidelite cf = new CarteFideliteDAO().getCarteFideliteByIdUtilisateur(user.getIdUtilisateur());
                        return cf != null && cf.getTotalMiles() > 6000;
                    })
                    .collect(Collectors.toList());
            displayUsers(filteredUsers);
        });
        filterPanel.add(filterMilesButton);

        add(filterPanel, BorderLayout.NORTH);

        // Table des clients
        String[] columnNames = {"ID", "Nom", "Prénom", "Email", "Miles", "Détails"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable userTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(userTable);
        tableScroll.setPreferredSize(new Dimension(550, 300));
        add(tableScroll, BorderLayout.CENTER);

        // Récupération des utilisateurs
        userList = new UtilisateurDAO().getAllUtilisateurs();
        displayUsers(userList);

        // Gestion du clic sur "Voir Détails"
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = userTable.rowAtPoint(e.getPoint());
                int column = userTable.columnAtPoint(e.getPoint());

                if (column == 5) { // colonne "Détails"
                    Utilisateur user = userList.get(row);
                    DetailUtilisateurFenetre detailFrame = new DetailUtilisateurFenetre(user);
                    detailFrame.setVisible(true);
                }
            }
        });

        // Bouton Fermer
        JPanel buttonPanel = new JPanel();
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void displayUsers(List<Utilisateur> users) {
        tableModel.setRowCount(0); // Réinitialiser la table

        for (Utilisateur user : users) {
            CarteFidelite cf = new CarteFideliteDAO().getCarteFideliteByIdUtilisateur(user.getIdUtilisateur());
            Object[] rowData = {
                    user.getIdUtilisateur(),
                    user.getNom(),
                    user.getPrenom(),
                    user.getEmail(),
                    (cf != null) ? cf.getTotalMiles() : 0,
                    "Voir Détails"
            };
            tableModel.addRow(rowData);
        }
    }

    private void applyFilter() {
        String nom = nomFilterField.getText().trim().toUpperCase();

        List<Utilisateur> filteredUsers = userList.stream()
                .filter(user -> nom.isEmpty() || user.getNom().toUpperCase().startsWith(nom))
                .collect(Collectors.toList());

        displayUsers(filteredUsers);
    }
}
