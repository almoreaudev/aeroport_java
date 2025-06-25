package ui;

import javax.swing.*;

import dao.AvionDAO;
import dao.CategoriepassagerDAO;

import java.awt.*;
import java.time.format.DateTimeFormatter;

import models.Categoriepassager;
import models.Vol;
import utils.DataFormater;
import models.Repas;
import models.A_pour_escale;
import models.Avion;

public class VolDetailFrame extends JFrame {

    public VolDetailFrame(Vol vol) {
        setTitle("Détails du Vol ID: " + vol.getIdVol());
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer seulement cette fenêtre

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des labels

        // Label avec CDG Paris
        JLabel departLabel = new JLabel("Départ: " + vol.getAeroportDepart().getCodeAeroport() + " - " +
                vol.getAeroportDepart().getVille() + ", " + vol.getAeroportDepart().getPays());
        JLabel arriveeLabel = new JLabel("Arrivée: " + vol.getAeroportArrive().getCodeAeroport() + " - " +
                vol.getAeroportArrive().getVille() + ", " + vol.getAeroportArrive().getPays());        
        JLabel dateDepartLabel = new JLabel("Date Départ: " + vol.getDateDepart());
        JLabel dateArriveeLabel = new JLabel("Date Arrivée: " + vol.getDateArrivee() + "\n");
        JLabel distanceLabel = new JLabel("Distance: " + vol.getDistance() + " km");
        JLabel statutLabel = new JLabel("Statut: " + vol.getStatut());
        JLabel carburantLabel = new JLabel("Carburant Nécessaire: " + vol.getCarburantNecessaire() + " L");
        JLabel dureeLabel = new JLabel("Durée: " + vol.getDuree());

        Avion avion = new AvionDAO().getAvionById(vol.getIdAvion());
        JLabel avionLabel = new JLabel("Avion: " + avion.getTypeAvion());
        JLabel typeVolLabel = new JLabel("Type de Vol: " + vol.getCodeTypeVol().getCategorie());

        // Infos aéroport départ
        JLabel aeroportDepartLabel = new JLabel("Aéroport Départ: " + vol.getAeroportDepart().getNom() +
                " (" + vol.getAeroportDepart().getCodeAeroport() + ") - " +
                vol.getAeroportDepart().getVille() + ", " + vol.getAeroportDepart().getPays());

        // Infos aéroport arrivée
        JLabel aeroportArriveLabel = new JLabel("Aéroport Arrivée: " + vol.getAeroportArrive().getNom() +
                " (" + vol.getAeroportArrive().getCodeAeroport() + ") - " +
                vol.getAeroportArrive().getVille() + ", " + vol.getAeroportArrive().getPays());

        Categoriepassager categoriePassager = new CategoriepassagerDAO().getCategoriePassagerByCode("ADULTE");
        double tarif = categoriePassager.getTarif() * vol.getDistance() / 100;
        JLabel prixLabel = new JLabel("Prix Basique: " + tarif + " €");

        // Lister les repas
        // Si il y a des repas associés au vol, les afficher
        JLabel repasLabel = new JLabel();
        if (vol.getRepasList() != null && !vol.getRepasList().isEmpty()) {
            StringBuilder repasList = new StringBuilder("Repas: ");
            for (Repas repas : vol.getRepasList()) {
                repasList.append(repas.getTypeRepas()).append(", ");
            }
            repasLabel = new JLabel(repasList.toString());
        } else {
            repasLabel = new JLabel("Aucun repas associé à ce vol.");
        }

        // Panel principal pour contenir toutes les escales
        JPanel escalesPanel = new JPanel();
        escalesPanel.setLayout(new BoxLayout(escalesPanel, BoxLayout.Y_AXIS)); // Empiler les escales verticalement

        if (vol.getEscalesDetails() != null && !vol.getEscalesDetails().isEmpty()) {
            for (A_pour_escale escale : vol.getEscalesDetails()) {
                // Création d'une "boîte" (un JPanel) pour chaque escale
                JPanel escaleBox = new JPanel();
                escaleBox.setLayout(new BoxLayout(escaleBox, BoxLayout.Y_AXIS));
                escaleBox.setBorder(BorderFactory.createTitledBorder("Escale"));

                // Création des labels pour chaque information
                JLabel codeAeroportLabel = new JLabel("Aéroport : " + escale.getCodeAeroport());
                JLabel arriveeLabelEscale = new JLabel("Arrivée : " + escale.getDateArriveeEscale());
                JLabel departLabelEscale = new JLabel("Départ : " + escale.getDateDepartEscale());

                // Calcul du temps d'attente
                DataFormater df = new DataFormater();
                double tempsAttenteMinutes = df.calculerDifferenceEnMinutes(escale.getDateDepartEscale(), escale.getDateArriveeEscale());
                JLabel attenteLabel = new JLabel("Temps d'attente : " + df.convertirMinutesEnHeure(tempsAttenteMinutes));

                // Ajout des labels dans la boîte de l'escale
                escaleBox.add(codeAeroportLabel);
                escaleBox.add(arriveeLabelEscale);
                escaleBox.add(departLabelEscale);
                escaleBox.add(attenteLabel);

                // Ajout de la boîte dans le panel principal
                escalesPanel.add(escaleBox);
            }
        } else {
            escalesPanel.add(new JLabel("Aucune escale pour ce vol."));
        }

        // Ajouter escalesPanel à la fenêtre ou au conteneur parent


        // Ajout des labels au panneau
        panel.add(departLabel);
        panel.add(arriveeLabel);
        // Ajouer un espace entre les labels
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace
        panel.add(dateDepartLabel);
        panel.add(dateArriveeLabel);
        panel.add(dureeLabel);
        // Ajouter un espace entre les labels
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace

        panel.add(prixLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace

        panel.add(repasLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace

        panel.add(avionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace

        panel.add(statutLabel);
        panel.add(carburantLabel);
        panel.add(avionLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace

        panel.add(escalesPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace

        // Ajouter un bouton de fermeture
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(closeButton);

        // Ajouter bouton réserver
        JButton reserverButton = new JButton("Réserver");
        reserverButton.addActionListener(e -> {
            // Vérification si l'utilisateur est connecté
            String user = utils.LocalUserConfig.getLastUser();
            if (user.length() != 0) {
                // Ouvrir la fenêtre de réservation
                new ReservationFrame(vol).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez vous connecter pour réserver un vol.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(reserverButton);
        add(panel);
    }
}
