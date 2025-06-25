package utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class DataFormater {

    // Exemple de méthode pour formatter
    public String formatDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

        public long calculerDifferenceEnMinutes(String dateStr1, String dateStr2) {
        // Formatter pour parser le format de tes dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Convertir les chaînes en LocalDateTime
        LocalDateTime dateTime1 = LocalDateTime.parse(dateStr1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(dateStr2, formatter);

        // Calculer la durée entre les deux dates
        Duration duration = Duration.between(dateTime1, dateTime2);

        // Retourner la durée en minutes (valeur absolue si tu veux toujours un résultat positif)
        return Math.abs(duration.toMinutes());
    }

    public int convertirEnMinutes(String dureeStr) {
        if (dureeStr == null || dureeStr.isEmpty()) {
            return 0;
        }

        dureeStr = dureeStr.trim().toLowerCase(); // Nettoyage

        int heures = 0;
        int minutes = 0;

        // Vérifie s'il y a des heures
        if (dureeStr.contains("h")) {
            String[] parts = dureeStr.split("h");
            heures = Integer.parseInt(parts[0].trim());

            // Si des minutes sont précisées après les heures
            if (parts.length > 1 && !parts[1].trim().isEmpty()) {
                minutes = Integer.parseInt(parts[1].trim());
            }
        } else {
            // Si format uniquement en minutes, ex: "30"
            minutes = Integer.parseInt(dureeStr.trim());
        }

        return heures * 60 + minutes;
    }


    public String convertirMinutesEnHeure(double totalMinutes) {
        if (totalMinutes <= 0) {
            return "0h";
        }

        int heures =  (int) (totalMinutes / 60);
        int minutes = (int) (totalMinutes % 60);

        if (minutes == 0) {
            return heures + "h";
        } else {
            return heures + "h" + minutes;
        }
    }


}
