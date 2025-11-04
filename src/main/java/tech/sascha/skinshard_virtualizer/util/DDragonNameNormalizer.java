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
            case "Rek'Sai" -> "RekSai";
            case "Twisted Fate" -> "TwistedFate";
            case "Xin Zhao" -> "XinZhao";
            case "Master Yi" -> "MasterYi";
            case "Cho'Gath" -> "Chogath";
            case "Dr. Mundo" -> "DrMundo";
            case "Jarvan IV" -> "JarvanIV";
            case "Lee Sin" -> "LeeSin";
            case "Kog'Maw" -> "KogMaw";
            case "Kha'Zix" -> "Khazix";
            case "Aurelion Sol" -> "AurelionSol";
            case "Kai'Sa" -> "Kaisa";
            case "Vel'Koz" -> "Velkoz";
            case "Tahm Kench" -> "TahmKench";
            case "Nunu & Willump" -> "Nunu";
            case "Wukong" -> "MonkeyKing";
            default -> championFromSplash;
        };
    }
}
