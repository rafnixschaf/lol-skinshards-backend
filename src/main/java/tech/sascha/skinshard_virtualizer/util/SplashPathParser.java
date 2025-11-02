package tech.sascha.skinshard_virtualizer.util;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplashPathParser {

    private static final Pattern PATTERN =
            Pattern.compile("Characters/([^/]+)/Skins/Skin(\\d+)/", Pattern.CASE_INSENSITIVE);

    public static Optional<Result> parse(String splashPath) {
        if (splashPath == null || splashPath.isBlank()) {
            return Optional.empty();
        }
        Matcher m = PATTERN.matcher(splashPath);
        if (m.find()) {
            String champion = m.group(1);
            int skinNum = Integer.parseInt(m.group(2));
            return Optional.of(new Result(champion, skinNum));
        }
        return Optional.empty();
    }

    public record Result(String champion, int skinNumber) {
    }
}