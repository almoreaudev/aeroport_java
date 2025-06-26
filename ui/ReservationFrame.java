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
    
    ButtonGroup classeGroup;
    ButtonGroup group;
    JComboBox<Object> categoriePassagerComboBox;
    JLabel prixLabel;

    double distance;

    public ReservationFrame(Vol vol) {
        setTitle("Réservation de Vol");
        setSize(400, 500);
        setLocationRelativeTo(null); // Centre la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setLayout(new BorderLayout());

        this.distance = vol.getDistance();

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
        prixLabel = new JLabel("Prix");


        // Menu déroulant
        categoriePassagerComboBox = new JComboBox<>();

        categoriePassagerComboBox.addItem("-- Sélectionner catégorie --");

        for (CodeCategorie code : CodeCategorie.values()) {
            categoriePassagerComboBox.addItem(code);
        }

        // Création des boutons radio
        JRadioButton valise0 = new JRadioButton("Aucune valise");
        valise0.setActionCommand("Aucune valise");

        JRadioButton valise1 = new JRadioButton("1 valise");
        valise1.setActionCommand("1 valise");

        JRadioButton valise2 = new JRadioButton("2 valises");
        valise2.setActionCommand("2 valises");

        JRadioButton valise3 = new JRadioButton("3 valises");
        valise3.setActionCommand("3 valises");


        // Groupe pour que l'utilisateur ne puisse en sélectionner qu'un seul
        group = new ButtonGroup();
        group.add(valise0);
        group.add(valise1);
        group.add(valise2);
        group.add(valise3);

        // Ajout dans le panel
        JPanel valisePanel = new JPanel();
        valisePanel.setBorder(BorderFactory.createTitledBorder("Nombre de valises supplémentaires"));
        valisePanel.add(valise0);
        valisePanel.add(valise1);
        valisePanel.add(valise2);
        valisePanel.add(valise3);


        

        // selon la valeur du buttongroup, on peut modifier le prix
        valise0.addActionListener(e -> {
            this.changerPrix();
        });

        valise1.addActionListener(e -> {
            this.changerPrix();
        });

        valise2.addActionListener(e -> {
            this.changerPrix();
        });

        valise3.addActionListener(e -> {
            this.changerPrix();
        });

        // Création des boutons radio
        JRadioButton economieButton = new JRadioButton("Classe Économie");
        economieButton.setActionCommand("Classe Économie");

        JRadioButton businessButton = new JRadioButton("Classe Business");
        businessButton.setActionCommand("Classe Business");

        JRadioButton premiereButton = new JRadioButton("Classe Premiere");
        premiereButton.setActionCommand("Classe Premiere");


        // Groupe pour forcer un choix unique
        classeGroup = new ButtonGroup();
        classeGroup.add(economieButton);
        classeGroup.add(businessButton);
        classeGroup.add(premiereButton);

        // Panel pour les afficher ensemble
        JPanel classePanel = new JPanel();
        classePanel.setBorder(BorderFactory.createTitledBorder("Choisissez la classe"));
        classePanel.add(economieButton);
        classePanel.add(businessButton);
        classePanel.add(premiereButton);


        // Action des boutons radio pour modifier le prix
        economieButton.addActionListener(e -> {
            this.changerPrix();
        });

        businessButton.addActionListener(e -> {
            this.changerPrix();
        });

        premiereButton.addActionListener(e -> {
            this.changerPrix();
        });


        // On modifivation du comboBox
        // récupérer Categoriepassager cp = new CategoriepassagerDAO().getCategoriePassagerByCode(categoriePassagerComboBox.getSelectedItem().toString());
        categoriePassagerComboBox.addActionListener(e -> {
            this.changerPrix();
        });


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

        mainPanel.add(classePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        mainPanel.add(prixLabel);
        mainPanel.add(validerButton);
        // Ajouter le panel principal à la fenêtre
        add(mainPanel);

        // Afficher la fenêtre
        setVisible(true);
    }

    public void changerPrix(){
        CodeCategorie selectedCode = (CodeCategorie) categoriePassagerComboBox.getSelectedItem();
        if (selectedCode != null) {
            Categoriepassager cp = new CategoriepassagerDAO().getCategoriePassagerByCode(selectedCode.toString());
            
            double prixTotal = this.calculerPrix(cp.getTarif(), 
                    classeGroup.getSelection() != null ? classeGroup.getSelection().getActionCommand() : null,
                    group.getSelection() != null ? group.getSelection().getActionCommand() : null);
            prixLabel.setText("Prix: " + prixTotal + " €");
            System.out.println("Prix mis à jour : " + prixTotal + " €");
            
        } else {
            prixLabel.setText("Prix: 0.0 €");
            System.out.println("Aucune catégorie sélectionnée, prix mis à 0.0 €");
        }
    }

    public double calculerPrix(double prixBase, String classeSelectionnee, String valiseSelectionnee) {
        double prixFinal = prixBase * (distance / 100); // Exemple de calcul basé sur la distance

        // Ajout en fonction de la classe sélectionnée
        if (classeSelectionnee != null) {
            switch (classeSelectionnee) {
                case "Classe Économie":
                    prixFinal += 0; // Aucun supplément
                    break;
                case "Classe Business":
                    prixFinal += 150; // Exemple de supplément
                    break;
                case "Classe Premiere":
                    prixFinal += 300; // Exemple de supplément
                    break;
            }
        }

        // Ajout en fonction du nombre de valises supplémentaires
        if (valiseSelectionnee != null) {
            switch (valiseSelectionnee) {
                case "Aucune valise":
                    prixFinal += 0; // Aucun supplément
                    break;
                case "1 valise":
                    prixFinal += 30; // Exemple de prix par valise
                    break;
                case "2 valises":
                    prixFinal += 60;
                    break;
                case "3 valises":
                    prixFinal += 90;
                    break;
            }
        }

        return prixFinal;
    }

}
