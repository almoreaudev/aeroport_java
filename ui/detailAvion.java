package ui;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import java.awt.Dimension;

import dao.AvionDAO;
import models.Avion;
import models.Vol;

public class detailAvion extends javax.swing.JFrame {
    
    private Avion avion;
    // Cette classe est une interface
    public detailAvion(Avion avion) {
        this.avion = avion;
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        // Initialisation des composants de l'interface graphique
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Détails de l'Avion");
        setSize(600, 500);
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        // Ajoutez ici les composants nécessaires pour afficher les détails de l'avion
        // Par exemple, des labels pour afficher le type d'avion, la capacité, etc.
        
        // Exemple de label
        JLabel avionLabel = new JLabel("Type d'Avion: " + avion.getTypeAvion());
        add(avionLabel);
        add(Box.createRigidArea(new Dimension(0, 10))); // Espace

        JLabel listeAvionSemainePro = new JLabel("Liste des vols de l'avion pour la semaine prochaine :");
        add(listeAvionSemainePro);

        // Liste des vols pour l'avion
        ArrayList<Vol> vols = new AvionDAO().getVolsByIdAvionForNextWeek(this.avion.getIdAvion());
        // Tableau avec "Départ" (CDG Paris), "Arrivée" (DBX Dubai), "Date de départ", "Catégorie vol"))
        // Un tableau de cette forme :
        String[] volsColumns = {"Départ", "Arrivé", "Date de départ", "Catégorie", "Détail"};
        DefaultTableModel volsModel = new DefaultTableModel(volsColumns, 0);
        JTable volsTable = new JTable(volsModel);
        JScrollPane volsScroll = new JScrollPane(volsTable);

        add(volsScroll);
        //add(volsPanel);
        // Autres composants peuvent être ajoutés ici

        for (Vol vol : vols) {
            Object[] rowData = {
                vol.getAeroportDepart().getCodeAeroport() + " - " + vol.getAeroportDepart().getVille(),
                vol.getAeroportArrive().getCodeAeroport() + " - " + vol.getAeroportArrive().getVille(),
                vol.getDateDepart(),
                vol.getCodeTypeVol(),
                "Voir le détail"
            };
            volsModel.addRow(rowData);
        }

        // Gestion du clic sur "Voir le détail" dans la table des vols
        volsTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = volsTable.rowAtPoint(e.getPoint());
                int column = volsTable.columnAtPoint(e.getPoint());

                if (column == 4) { // colonne "Détail"
                    // new VolDetailFrame(vols.get(row)).setVisible(true);
                    Vol vol = vols.get(row);
                    VolDetailFrame volDetailFrame = new VolDetailFrame(vol);
                    volDetailFrame.setVisible(true);
                }
            }
        });


        // Ajouter un espace
        add(Box.createRigidArea(new Dimension(0, 10))); // Espace

        // Tableaux des contrôles de sécurité et d'entretien
        String[] controlesColonnes = {"Date contrôle", "En retard"};
        DefaultTableModel controlesModel = new DefaultTableModel(controlesColonnes, 0);
        JTable controlesTable = new JTable(controlesModel);
        JScrollPane controlesScroll = new JScrollPane(controlesTable);

        add(new JLabel("Contrôles de sécurité et d'entretien de l'avion :"));
        add(controlesScroll);

        // Ajout des contrôles de sécurité et d'entretien
        Object[] controleData = {
            "Date de contrôle de sécurité : " + avion.getDateControleSecurite(),
            avion.isControleEnRetard() ? "Oui" : "Non"
        };
        controlesModel.addRow(controleData);

        Object[] entretienData = {
            "Date d'entretien : " + avion.getDateEntretien(),
            avion.isEntretienEnRetard() ? "Oui" : "Non"
        };
        controlesModel.addRow(entretienData);




        
    }
}
