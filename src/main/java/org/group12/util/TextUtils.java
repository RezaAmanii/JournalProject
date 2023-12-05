package org.group12.util;

import java.util.Arrays;
import java.util.List;

public class TextUtils {

    /**
     * Counts the number of words in the given text.
     *
     * @param text the text to count words in
     * @return the number of words in the text, or 0 if the text is null or empty
     */
    public static int getWordCount(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        return text.split("\\s+").length;
    }

    /**
     * Gets the first n words of the given text.
     *
     * @param text the text to extract words from
     * @param n the number of words to return
     * @return the first n words of the text, or an empty string if the text is null
     */
    public static String getFirstNWords(String text, int n) {
        if (text == null) {
            return "";
        }
        List<String> words = Arrays.asList(text.split("\\s+"));
        return String.join(" ", words.subList(0, Math.min(n, words.size())));
    }
}