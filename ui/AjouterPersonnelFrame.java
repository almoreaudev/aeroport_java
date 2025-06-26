package ui;

import java.util.ArrayList;
import javax.swing.*;

import dao.PersonnelDAO;
import models.Personnel;
import models.TypePersonnel;

public class AjouterPersonnelFrame extends JFrame {

    public AjouterPersonnelFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Ajouter Personnel");
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 300);

        JLabel titleLabel = new JLabel("Formulaire d'ajout de personnel", SwingConstants.CENTER);
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));

        JTextField nomField = new JTextField(20);
        JTextField prenomField = new JTextField(20);
        JTextField dateNaissanceField = new JTextField(20);

        ArrayList<TypePersonnel> typePersonnelList = new ArrayList<>();
        typePersonnelList.add(TypePersonnel.PILOT);
        typePersonnelList.add(TypePersonnel.COPIL);
        typePersonnelList.add(TypePersonnel.CHEFCAB);
        typePersonnelList.add(TypePersonnel.HOT);
        typePersonnelList.add(TypePersonnel.STEW);

        JComboBox<String> typePersonnelCombo = new JComboBox<>();
        for (TypePersonnel type : typePersonnelList) {
            typePersonnelCombo.addItem(type.getType());
        }

        JButton ajouterButton = new JButton("Ajouter");

        ajouterButton.addActionListener(e -> {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String dateNaissance = dateNaissanceField.getText();
            int indexType = typePersonnelCombo.getSelectedIndex();
            TypePersonnel typePersonnel = typePersonnelList.get(indexType);

            System.out.println("Personnel ajouté : " + nom + " " + prenom + ", " + dateNaissance + ", " + typePersonnel);

            Personnel p = new Personnel(indexType, nom, prenom, dateNaissance, typePersonnel);
            new PersonnelDAO().addPersonnel(p);
            JOptionPane.showMessageDialog(this, "Personnel ajouté avec succès !");
            dispose(); // ferme la fenêtre après ajout
        });

        // Layout général
        setLayout(new java.awt.BorderLayout(10, 10));
        add(titleLabel, java.awt.BorderLayout.NORTH);

        // Formulaire en GridLayout 4 lignes, 2 colonnes
        JPanel formPanel = new JPanel(new java.awt.GridLayout(4, 2, 10, 10));
        formPanel.add(new JLabel("Nom:"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom:"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Date de Naissance:"));
        formPanel.add(dateNaissanceField);
        formPanel.add(new JLabel("Type de Personnel:"));
        formPanel.add(typePersonnelCombo);

        add(formPanel, java.awt.BorderLayout.CENTER);

        // Bouton en bas
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajouterButton);
        add(buttonPanel, java.awt.BorderLayout.SOUTH);

        setVisible(true);
    }
}
