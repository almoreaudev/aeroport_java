package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class LocalUserConfig {
    
    private static final String CONFIG_FILE = "user.properties";
    private static final String KEY_LAST_USER = "lastUser";

    // Lire l'utilisateur enregistré
    public static String getLastUser() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
            return props.getProperty(KEY_LAST_USER, "");
        } catch (IOException e) {
            // Si fichier absent, retourner valeur par défaut
            return "";
        }
    }

    // Enregistrer l'utilisateur
    public static void setLastUser(String username) {
        Properties props = new Properties();
        try {
            // Charger l'existant s'il y a lieu
            FileInputStream fis = new FileInputStream(CONFIG_FILE);
            props.load(fis);
            fis.close();
        } catch (IOException e) {
            // Le fichier n'existe pas encore, on continue
        }

        props.setProperty(KEY_LAST_USER, username);

        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            props.store(fos, "User configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // set isSuperUser
    public static void setIsSuperUser(boolean isSuperUser) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
        } catch (IOException e) {
            // Si le fichier n'existe pas, on continue
        }

        props.setProperty("isSuperUser", String.valueOf(isSuperUser));

        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            props.store(fos, "User configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
