package com.github.hemanthsridhar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum RandomizerConstants {
    RANDOM_ALPHABETIC("randomAlphabetic"),
    RANDOM_NUMERIC("randomNumeric"),
    RANDOM("random"),
    RANDOM_ALPHANUMERIC("randomAlphanumeric"),
    RANDOM_GRAPH("randomGraph"),
    RANDOM_ASCII("randomAscii");

    private final String randomSupport;

    RandomizerConstants(String randomSupport) {
        this.randomSupport = randomSupport;
    }

    public static RandomizerConstants ifContains(String line) {
        for (RandomizerConstants enumValue : values()) {
            if (isContain(line.toLowerCase(), enumValue.toString().toLowerCase())) {
                return enumValue;
            }
        }
        return null;
    }

    /**
     * \b Matches a word boundary where a word character is [a-zA-Z0-9_].
     */

    private static boolean isContain(String source, String subItem) {
        String pattern = "\\b" + Pattern.quote(subItem) + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

    @Override
    public String toString() {
        return this.randomSupport;
    }
}
