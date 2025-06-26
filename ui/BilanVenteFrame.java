package ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import dao.BilletDAO;
import dao.VolDAO;
import models.Vol;
import models.BilletAvion;

public class BilanVenteFrame extends JFrame {

    public BilanVenteFrame() {
        setTitle("Dashboard TravelJet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // Layout global en grille 2x2
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        // Graphique barres
        ChartPanel barChartPanel = createBarChart();
        topPanel.add(barChartPanel);

        // Graphique camembert
        ChartPanel pieChartPanel = createPieChart();
        pieChartPanel.setSize(new Dimension(300, 300)); // Fixer la taille du graphique
        topPanel.add(pieChartPanel);

        // Tableau des destinations
        JScrollPane tablePanel = createTable();
        bottomPanel.add(tablePanel);

        // Graphique retard
        ChartPanel retardPieChartPanel = createRetardPieChart();
        bottomPanel.add(retardPieChartPanel);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        // Bouton retour
        JButton retourButton = new JButton("Retour");
        retourButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(retourButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private ChartPanel createBarChart() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] mois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};

        for (int i = 0; i < 12; i++) {
            
            // Récupérer tout les vols du mois de janvier 2024
            List<Vol> volsMonth2024 = new VolDAO().getVolsByMonthAndYear(i + 1, 2024);
            // Récupérer le total des prix des billets pour chaque vol
            double prixTotal = 0.0;
            for (Vol vol : volsMonth2024) {
                ArrayList<BilletAvion> billets = new BilletDAO().getBilletsByIdVol(vol.getIdVol());
                for (BilletAvion billet : billets) {
                    prixTotal += billet.getPrix();
                }
            }

            dataset.addValue(prixTotal, "2024", mois[i]);
        }

        for (int i = 0; i < 12; i++) {
            // Récupérer tout les vols du mois de janvier 2025
            List<Vol> volsMonth2025 = new VolDAO().getVolsByMonthAndYear(i + 1, 2025);
            // Récupérer le total des prix des billets pour chaque vol
            double prixTotal = 0.0;
            for (Vol vol : volsMonth2025) {
                ArrayList<BilletAvion> billets = new BilletDAO().getBilletsByIdVol(vol.getIdVol());
                for (BilletAvion billet : billets) {
                    prixTotal += billet.getPrix();
                }
            }

            dataset.addValue(prixTotal, "2025", mois[i]);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Ventes annuelles",
                "Mois",
                "Ventes (en €)",
                dataset
        );

        return new ChartPanel(barChart);
    }

    private ChartPanel createPieChart() {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("Long courrier", 60);
        pieDataset.setValue("Moyen courrier", 30);
        pieDataset.setValue("Court courrier", 10);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Ventes par catégorie",
                pieDataset,
                true,
                true,
                false
        );

        return new ChartPanel(pieChart);
    }

    private JScrollPane createTable() {
        String[] columnNames = {"Destination", "Nombre de vols", "Prix moyen d'un vol"};
        Object[][] data = {
                {"Paris", 200, "300 €"},
                {"?", "?", "?"},
                {"?", "?", "?"}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Statistiques des destinations (2025)"));

        return scrollPane;
    }

    private ChartPanel createRetardPieChart() {
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        pieDataset.setValue("A l'heure", 60);
        pieDataset.setValue("Trafic saturé", 20);
        pieDataset.setValue("Panne", 10);
        pieDataset.setValue("Autre", 10);

        JFreeChart pieChart = ChartFactory.createPieChart(
                "Retard",
                pieDataset,
                true,
                true,
                false
        );

        return new ChartPanel(pieChart);
    }


}
