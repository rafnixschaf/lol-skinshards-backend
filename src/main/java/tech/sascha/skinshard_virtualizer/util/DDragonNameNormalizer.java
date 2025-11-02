package tech.sascha.skinshard_virtualizer.util;

public class DDragonNameNormalizer {

    public static String normalize(String championFromSplash) {
        if (championFromSplash == null) return null;

        return switch (championFromSplash) {
            case "KhaZix" -> "Khazix";
            case "Velkoz" -> "Velkoz";
            case "ChoGath" -> "Chogath";
            case "KaiSa" -> "Kaisa";
            case "LeBlanc" -> "Leblanc";
            default -> championFromSplash;
        };
    }
}
