package tn.esprit.autoloc.autolocapi;

public class Utils {

    // Constructeur privé pour empêcher l'instanciation de cette classe utilitaire
    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}