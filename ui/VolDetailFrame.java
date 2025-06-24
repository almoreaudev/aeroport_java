package ui;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

import models.Vol;

public class VolDetailFrame extends JFrame {

    public VolDetailFrame(Vol vol) {
        setTitle("Détails du Vol ID: " + vol.getIdVol());
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer seulement cette fenêtre

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Format pour les dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Création des labels
        JLabel idLabel = new JLabel("ID Vol: " + vol.getIdVol());
        JLabel dateDepartLabel = new JLabel("Date Départ: " + vol.getDateDepart().format(formatter));
        JLabel dateArriveeLabel = new JLabel("Date Arrivée: " + vol.getDateArrivee().format(formatter));
        JLabel distanceLabel = new JLabel("Distance: " + vol.getDistance() + " km");
        JLabel statutLabel = new JLabel("Statut: " + vol.getStatut());
        JLabel carburantLabel = new JLabel("Carburant Nécessaire: " + vol.getCarburantNecessaire() + " L");
        JLabel dureeLabel = new JLabel("Durée: " + vol.getDuree());
        JLabel avionLabel = new JLabel("ID Avion: " + vol.getIdAvion());
        JLabel typeVolLabel = new JLabel("Type de Vol: " + vol.getCodeTypeVol());

        // Infos aéroport départ
        JLabel aeroportDepartLabel = new JLabel("Aéroport Départ: " + vol.getAeroportDepart().getNom() +
                " (" + vol.getAeroportDepart().getCodeAeroport() + ") - " +
                vol.getAeroportDepart().getVille() + ", " + vol.getAeroportDepart().getPays());

        // Infos aéroport arrivée
        JLabel aeroportArriveLabel = new JLabel("Aéroport Arrivée: " + vol.getAeroportArrive().getNom() +
                " (" + vol.getAeroportArrive().getCodeAeroport() + ") - " +
                vol.getAeroportArrive().getVille() + ", " + vol.getAeroportArrive().getPays());

        // Ajout des labels au panneau
        panel.add(idLabel);
        panel.add(dateDepartLabel);
        panel.add(dateArriveeLabel);
        panel.add(distanceLabel);
        panel.add(statutLabel);
        panel.add(carburantLabel);
        panel.add(dureeLabel);
        panel.add(avionLabel);
        panel.add(typeVolLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace
        panel.add(aeroportDepartLabel);
        panel.add(aeroportArriveLabel);

        // Ajouter un bouton de fermeture
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> dispose());

        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(closeButton);

        add(panel);
    }
}
