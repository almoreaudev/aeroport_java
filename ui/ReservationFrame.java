package ui;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import models.Vol;

public class ReservationFrame extends JFrame {
    
    public ReservationFrame(Vol vol) {
        setTitle("Nouvelle Fenêtre");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centre la fenêtre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Marges

        // Label "Prix"
        JLabel prixLabel = new JLabel("Prix");
        prixLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(prixLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espace vertical

        // Menu déroulant
        JComboBox<String> comboBox = new JComboBox<>();
        List<String> valeursMenuDeroulant = List.of("Option 1", "Option 2", "Option 3", "Option 4");
        for (String valeur : valeursMenuDeroulant) {
            comboBox.addItem(valeur);
        }
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(comboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Panel pour les boutons à cocher
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        checkboxPanel.setBorder(BorderFactory.createTitledBorder("Options"));

        // Exemple de 3 cases à cocher (tu peux en ajouter d'autres dynamiquement)
        JCheckBox checkBox1 = new JCheckBox("Option 1");
        JCheckBox checkBox2 = new JCheckBox("Option 2");
        JCheckBox checkBox3 = new JCheckBox("Option 3");

        checkboxPanel.add(checkBox1);
        checkboxPanel.add(checkBox2);
        checkboxPanel.add(checkBox3);

        mainPanel.add(checkboxPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Bouton "Valider"
        JButton validerButton = new JButton("Valider");
        validerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(validerButton);

        // Ajouter le panel principal à la fenêtre
        add(mainPanel, BorderLayout.CENTER);

        // Afficher la fenêtre
        setVisible(true);
    }
}
