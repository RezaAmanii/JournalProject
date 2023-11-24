package org.group12.util;

import java.util.Arrays;
import java.util.List;

public class TextUtils {

    /**
     * Counts the number of words in the given text.
     *
     * @param text the text to count words in
     * @return the number of words in the text
     */
    public static int getWordCount(String text) {
        return text.split("\\s+").length;
    }

    /**
     * Gets the first n words of the given text.
     *
     * @param text the text to extract words from
     * @param n the number of words to return
     * @return the first n words of the text
     */
    public static String getFirstNWords(String text, int n) {
        List<String> words = Arrays.asList(text.split("\\s+"));
        return String.join(" ", words.subList(0, Math.min(n, words.size())));
    }
}