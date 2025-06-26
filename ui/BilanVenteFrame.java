package ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

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
        dataset.addValue(4.2, "2025", "Janvier");
        dataset.addValue(2.5, "2025", "Février");
        dataset.addValue(3.5, "2025", "Mars");
        dataset.addValue(4.5, "2025", "Avril");

        dataset.addValue(2.2, "2024", "Janvier");
        dataset.addValue(4.2, "2024", "Février");
        dataset.addValue(1.8, "2024", "Mars");
        dataset.addValue(2.7, "2024", "Avril");

        JFreeChart barChart = ChartFactory.createBarChart(
                "Ventes annuelles",
                "Mois",
                "Ventes",
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BilanVenteFrame dashboard = new DashboardExample();
            dashboard.setVisible(true);
        });
    }
}
