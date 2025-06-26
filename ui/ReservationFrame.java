package ui;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import models.CodeCategorie;
import models.Vol;
import dao.CategoriepassagerDAO;
import dao.UtilisateurDAO;
import dao.BilletDAO;
import models.BilletAvion;
import models.Categoriepassager;

public class ReservationFrame extends JFrame {
    
    public ReservationFrame(Vol vol) {
        setTitle("Réservation de Vol");
        setSize(400, 500);
        setLocationRelativeTo(null); // Centre la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Marges

        // Création du label
        JLabel nomLabel = new JLabel("Nom :");
        JTextField nomTextField = new JTextField(20); // 20 colonnes de largeur

        JLabel prenomLabel = new JLabel("Prénom :");
        JTextField prenomTextField = new JTextField(20); // 20 colonnes


        // Label "Prix"
        JLabel prixLabel = new JLabel("Prix");


        // Menu déroulant
        JComboBox<Object> categoriePassagerComboBox = new JComboBox<>();

        categoriePassagerComboBox.addItem("-- Sélectionner catégorie --");

        for (CodeCategorie code : CodeCategorie.values()) {
            categoriePassagerComboBox.addItem(code);
        }

        // On modifivation du comboBox
        // récupérer Categoriepassager cp = new CategoriepassagerDAO().getCategoriePassagerByCode(categoriePassagerComboBox.getSelectedItem().toString());
        categoriePassagerComboBox.addActionListener(e -> {
            CodeCategorie selectedCode = (CodeCategorie) categoriePassagerComboBox.getSelectedItem();
            if (selectedCode != null) {
                Categoriepassager cp = new CategoriepassagerDAO().getCategoriePassagerByCode(selectedCode.toString());
                double prix = cp.getTarif() * vol.getDistance() / 100; // Exemple de calcul
                prixLabel.setText("Prix: " + prix + " €");
                
            }
        });

        // Création des boutons radio
        JRadioButton valise1 = new JRadioButton("1 valise");
        JRadioButton valise2 = new JRadioButton("2 valises");
        JRadioButton valise3 = new JRadioButton("3 valises");

        // Groupe pour que l'utilisateur ne puisse en sélectionner qu'un seul
        ButtonGroup group = new ButtonGroup();
        group.add(valise1);
        group.add(valise2);
        group.add(valise3);

        // Ajout dans le panel
        JPanel valisePanel = new JPanel();
        valisePanel.setBorder(BorderFactory.createTitledBorder("Nombre de valises supplémentaires"));
        valisePanel.add(valise1);
        valisePanel.add(valise2);
        valisePanel.add(valise3);

        

        // selon la valeur du buttongroup, on peut modifier le prix
        valise1.addActionListener(e -> {
            CodeCategorie selectedCode = (CodeCategorie) categoriePassagerComboBox.getSelectedItem();
            if (selectedCode != null) {
                Categoriepassager cp = new CategoriepassagerDAO().getCategoriePassagerByCode(selectedCode.toString());
                double prix = cp.getTarif() * vol.getDistance() / 100 + 50; // Exemple de calcul avec supplément pour 1 valise
                prixLabel.setText("Prix: " + prix + " €");
            }
        });

        valise2.addActionListener(e -> {
            CodeCategorie selectedCode = (CodeCategorie) categoriePassagerComboBox.getSelectedItem();
            if (selectedCode != null) {
                Categoriepassager cp = new CategoriepassagerDAO().getCategoriePassagerByCode(selectedCode.toString());
                double prix = cp.getTarif() * vol.getDistance() / 100 + 100; // Exemple de calcul avec supplément pour 2 valises
                prixLabel.setText("Prix: " + prix + " €");
            }
        });

        valise3.addActionListener(e -> {
            CodeCategorie selectedCode = (CodeCategorie) categoriePassagerComboBox.getSelectedItem();
            if (selectedCode != null) {
                Categoriepassager cp = new CategoriepassagerDAO().getCategoriePassagerByCode(selectedCode.toString());
                double prix = cp.getTarif() * vol.getDistance() / 100 + 150; // Exemple de calcul avec supplément pour 3 valises
                prixLabel.setText("Prix: " + prix + " €");
            }
        });


        // Panel pour les boutons à cocher
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        checkboxPanel.setBorder(BorderFactory.createTitledBorder("Options"));

        // Exemple de 3 cases à cocher (tu peux en ajouter d'autres dynamiquement)
        JCheckBox checkBox1 = new JCheckBox("Valise 1");
        JCheckBox checkBox2 = new JCheckBox("O");
        JCheckBox checkBox3 = new JCheckBox("Option 3");

        checkboxPanel.add(checkBox1);
        checkboxPanel.add(checkBox2);
        checkboxPanel.add(checkBox3);

        // Bouton "Valider"
        JButton validerButton = new JButton("Valider");

        // Action du bouton "Valider"
        validerButton.addActionListener(e -> {
            // Créé BilletAvion
            String nom = nomTextField.getText().trim();
            String prenom = prenomTextField.getText().trim();
            CodeCategorie selectedCode = (CodeCategorie) categoriePassagerComboBox.getSelectedItem();
            Categoriepassager cp = new CategoriepassagerDAO().getCategoriePassagerByCode(selectedCode.toString());
            //double prix = cp.getTarif() * vol.getDistance() / 100; // Exemple de calcul
            // Récupérer le prix affiché
            String prixText = prixLabel.getText().replace("Prix: ", "").replace(" €", "");
            double prix = Double.parseDouble(prixText); // Convertir le texte en double
            // Afficher un message de confirmation
            System.out.println("Réservation effectuée pour " + nom + " " + prenom + " dans la catégorie " + selectedCode + " au prix de " + prix + " €");
            

            // Créer un objet BilletAvion
            //(idUtilisateur, idVol, codeCategorie, prenom, nom, prix)
            BilletAvion billet = new BilletAvion();
            String email = utils.LocalUserConfig.getLastUser(); 
            int idUser = new UtilisateurDAO().getIdUtilisateurByEmail(email);
            billet.setIdUtilisateur(idUser); // Remplacer par l'ID de l'utilisateur connecté
            billet.setIdVol(vol.getIdVol());
            billet.setCodeCategorie(selectedCode.toString());
            billet.setPrenom(prenom);
            billet.setNom(nom);
            billet.setPrix(prix);

            // Enregistrer le billet dans la base de données
            boolean success = new BilletDAO().addBillet(billet);
            if (success) {
                JOptionPane.showMessageDialog(this, "Réservation réussie pour " + nom + " " + prenom + " dans la catégorie " + selectedCode + " au prix de " + prix + " €");
                dispose(); // Fermer la fenêtre après la réservation
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la réservation. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        

        mainPanel.add(nomLabel);
        mainPanel.add(nomTextField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace vertical

        mainPanel.add(prenomLabel);
        mainPanel.add(prenomTextField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace vertical

        mainPanel.add(categoriePassagerComboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Ajouter le panel des valises au panel principal
        mainPanel.add(valisePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace vertical

        mainPanel.add(checkboxPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(prixLabel);
        mainPanel.add(validerButton);
        // Ajouter le panel principal à la fenêtre
        add(mainPanel);

        // Afficher la fenêtre
        setVisible(true);
    }
}
