package ui;

import dao.CategoriepassagerDAO;
import dao.UtilisateurDAO;
import dao.VolDAO;
import models.Categoriepassager;
import models.Utilisateur;
import models.Vol;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class VolListUI extends JFrame {

    private JTable volTable;
    private DefaultTableModel tableModel;
    private List<Vol> volList = new ArrayList<>();
    private JTextField departFilterField;
    private JTextField arriveeFilterField;

    private JLabel userLabel;
    private String currentUser;

    private JButton loginButton;
    private JButton inscriptionButton;
    private JButton logoutButton;
    private JButton detailUserButton;
    private JButton voirSuperUserDetailsButton;


    public VolListUI() {
        setTitle("Liste des vols");
        setSize(1300, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrer la fenêtre

        initComponents();
        loadFlights();
        checkVisibilityButtons();
    }

    private void initComponents() {
        // ===========================
        // Barre supérieure (Filtres + Connexion/Inscription)
        // ===========================
        JPanel topPanel = new JPanel(new BorderLayout());

        // Panneau de filtre (gauche)
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("Code Aéroport Départ:"));
        departFilterField = new JTextField(5);
        filterPanel.add(departFilterField);

        filterPanel.add(new JLabel("Code Aéroport Arrivée:"));
        arriveeFilterField = new JTextField(5);
        filterPanel.add(arriveeFilterField);

        JButton filterButton = new JButton("Filtrer");
        filterButton.addActionListener(e -> applyFilter());
        filterPanel.add(filterButton);

        topPanel.add(filterPanel, BorderLayout.WEST);

        // Panneau Connexion/Inscription (droite)
        JPanel authPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginButton = new JButton("Connexion");

        loginButton.addActionListener(e -> {
            JTextField emailField = new JTextField();
            JTextField passwordField = new JPasswordField();

            Object[] message = {
                "Email:", emailField,
                "Mot de passe:", passwordField
            };

            int option = JOptionPane.showConfirmDialog(this, message, "Connexion", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String email = emailField.getText().trim();
                String motDePasse = passwordField.getText().trim();

                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                Utilisateur utilisateur = utilisateurDAO.verifierConnexion(email, motDePasse);

                if (utilisateur != null) {
                    currentUser = utilisateur.getNom() + " " + utilisateur.getPrenom();
                    utils.LocalUserConfig.setLastUser(email);
                    utils.LocalUserConfig.setIsSuperUser(utilisateur.isSuperUtilisateur());
                    userLabel.setText("Connecté : " + currentUser);

                    if (utilisateur.isSuperUtilisateur()) {
                        // Ouvre la fenêtre de vue globale si c'est un super utilisateur
                        // Ajoute un bouton pour ouvrir la vue globale
                        voirSuperUserDetailsButton.setVisible(true);
                    } else {
                        voirSuperUserDetailsButton.setVisible(false);
                    }
                    JOptionPane.showMessageDialog(this, "Connexion réussie !");
                } else {
                    JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                checkVisibilityButtons();
            }
        });


        inscriptionButton = new JButton("Inscription");
        inscriptionButton.addActionListener(e -> ouvrirFormulaireInscription());
        
        detailUserButton = new JButton("Détails Utilisateur");
        detailUserButton.addActionListener(e -> {
            String user = utils.LocalUserConfig.getLastUser();
            System.out.println("Current user: " + user);
            if (user.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez vous connecter pour voir les détails de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
                Utilisateur utilisateur = utilisateurDAO.getUtilisateurByEmail(user);
                if (utilisateur != null) {

                    // Ouvre la nouvelle fenêtre
                    new DetailUtilisateurFenetre(utilisateur);

                } else {
                    JOptionPane.showMessageDialog(this, "Utilisateur non trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        authPanel.add(detailUserButton);

        voirSuperUserDetailsButton = new JButton("Données globales");
        voirSuperUserDetailsButton.addActionListener(e -> {
            //lance la fenêtre de vue globale
            new VueGlobaleFenetre().setVisible(true);
        });


        logoutButton = new JButton("Déconnexion");
        logoutButton.addActionListener(e -> {
            currentUser = "";
            utils.LocalUserConfig.setLastUser("");
            userLabel.setText("Non connecté");
            JOptionPane.showMessageDialog(this, "Déconnexion réussie !");
            checkVisibilityButtons();
        });
        authPanel.add(logoutButton);
        authPanel.add(voirSuperUserDetailsButton);
        authPanel.add(loginButton);
        authPanel.add(inscriptionButton);

        currentUser = utils.LocalUserConfig.getLastUser();
        userLabel = new JLabel(currentUser.isEmpty() ? "Non connecté" : "Connecté : " + currentUser);
        authPanel.add(userLabel);

        topPanel.add(authPanel, BorderLayout.EAST);

        // Ajouter la barre supérieure
        add(topPanel, BorderLayout.NORTH);

        // ===========================
        // Tableau des vols
        // ===========================
        String[] columnNames = {"Id", "Depart", "Arrive", "Direct", "Temps de voyage", "Temps de vol", "Escales", "Prix"};
        tableModel = new DefaultTableModel(columnNames, 0);
        volTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(volTable);

        add(scrollPane, BorderLayout.CENTER);

        // ===========================
        // Boutons en bas
        // ===========================

        JButton detailButton = new JButton("Voir Détails");
        detailButton.addActionListener(e -> showFlightDetails());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(detailButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void checkVisibilityButtons() {
        // Si utilisateur est connecté, afficher le bouton de déconnexion et masquer le bouton de connexion et d'inscription
        if (!currentUser.isEmpty()) {
            loginButton.setVisible(false);
            inscriptionButton.setVisible(false);
            logoutButton.setVisible(true);
            detailUserButton.setVisible(true);
            userLabel.setText("Connecté : " + currentUser);
        } else {
            loginButton.setVisible(true);
            inscriptionButton.setVisible(true);
            logoutButton.setVisible(false);
            detailUserButton.setVisible(false);
            voirSuperUserDetailsButton.setVisible(false);
            userLabel.setText("Non connecté");
        }
    }

    private void ouvrirFormulaireInscription() {
        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField adresseField = new JTextField();
        JTextField passeportField = new JTextField();
        JTextField carteIdField = new JTextField();

        Object[] message = {
            "Nom:", nomField,
            "Prénom:", prenomField,
            "Email:", emailField,
            "Mot de passe:", passwordField,
            "Adresse:", adresseField,
            "Numéro de passeport:", passeportField,
            "Numéro de carte d'identité:", carteIdField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Inscription", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String email = emailField.getText().trim();
            String motDePasse = new String(passwordField.getPassword()).trim();
            String adresse = adresseField.getText().trim();
            String numPasseport = passeportField.getText().trim();
            String numCarteIdentite = carteIdField.getText().trim();

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || motDePasse.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Création de l'utilisateur
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setEmail(email);
            utilisateur.setMotDePasse(motDePasse);  // Pense au hashage en vrai projet
            utilisateur.setAdresse(adresse);
            utilisateur.setNumPasseport(numPasseport);
            utilisateur.setNumCarteIdentite(numCarteIdentite);

            UtilisateurDAO userdao = new UtilisateurDAO();
            boolean success = userdao.creerUtilisateur(utilisateur);

            if (success) {
                JOptionPane.showMessageDialog(this, "Inscription réussie !");
                currentUser = utilisateur.getNom() + " " + utilisateur.getPrenom();
                utils.LocalUserConfig.setLastUser(email);
                userLabel.setText("Connecté : " + currentUser);
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'inscription.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            checkVisibilityButtons();
        }
    }

    private void loadFlights() {
        VolDAO volDAO = new VolDAO();
        volList = volDAO.getVolsByStatut("A venir");
        displayFlights(volList);
    }

    private void displayFlights(List<Vol> vols) {
        tableModel.setRowCount(0); // Vider la table

        for (Vol vol : vols) {
            System.out.println(vol.toString() + "\n\n");

            int escaleCount = vol.getEscales().size();
            String escaleText = escaleCount > 0 ? "Oui": "Non";
            String escaleDetails = escaleCount > 0 ? vol.getEscales().stream()
                    .map(aeroport -> aeroport.getCodeAeroport())
                    .collect(Collectors.joining(", ")) : "Aucune";

            // get categorie passager Adulte
            Categoriepassager categoriePassager = new CategoriepassagerDAO().getCategoriePassagerByCode("ADULTE");
            double distance = vol.getDistance();
            double prix = categoriePassager != null ? categoriePassager.getTarif() * distance / 100 : 100.0 * distance / 100; // Valeur par défaut si pas trouvé
            Object[] rowData = {
                    vol.getIdVol(),
                    vol.getAeroportDepart().getCodeAeroport(),
                    vol.getAeroportArrive().getCodeAeroport(),
                    escaleText,
                    vol.getDureeTotalString(),
                    vol.getDuree(),
                    escaleDetails,
                    prix + " €"
            };
            tableModel.addRow(rowData);
        }
    }

    private void applyFilter() {
        String departCode = departFilterField.getText().trim().toUpperCase();
        String arriveeCode = arriveeFilterField.getText().trim().toUpperCase();

        List<Vol> filteredVols = volList.stream()
                .filter(vol -> (departCode.isEmpty() || vol.getAeroportDepart().getCodeAeroport().toUpperCase().startsWith(departCode)))
                .filter(vol -> (arriveeCode.isEmpty() || vol.getAeroportArrive().getCodeAeroport().toUpperCase().startsWith(arriveeCode)))
                .collect(Collectors.toList());

        displayFlights(filteredVols);
    }


    private void showFlightDetails() {
        int selectedRow = volTable.getSelectedRow();
        if (selectedRow != -1) {
            int volId = (int) tableModel.getValueAt(selectedRow, 0);
            Vol selectedVol = volList.stream()
                    .filter(vol -> vol.getIdVol() == volId)
                    .findFirst()
                    .orElse(null);

            if (selectedVol != null) {
                VolDetailFrame detailFrame = new VolDetailFrame(selectedVol);
                detailFrame.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un vol.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VolListUI().setVisible(true));
    }


}
