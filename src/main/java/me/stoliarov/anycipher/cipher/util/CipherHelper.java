package me.stoliarov.anycipher.cipher.util;

/**
 * Created by Владислав on 24.09.2015.
 */
public class CipherHelper {
    public static final String ALPHABET = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЬЫЭЮЯ";

    ;
    private CipherHelper() {
    }

    public static String getSloganAlphabet(final String key) {
        String result = new String(removeDuplicates(key));
        for (char c : ALPHABET.toCharArray()) {
            if (result.indexOf(c) == -1) result += c;
        }
        return result.toString();
    }

    public static String removeDuplicates(final String input) {
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String currentChar = input.substring(i, i + 1);
            if (result.indexOf(currentChar) < 0) //if not contained
                result.append(currentChar);
        }
        return (result.toString());
    }

    public static char[][] stringToCharArr(String string, boolean byline, int charsI, int charsJ) {
        char[][] chars = new char[charsI][charsJ];
        if (byline) {
            for (int i = 0; i < charsI; i++) {
                for (int j = 0; j < charsJ; j++) {
                    chars[i][j] = string.charAt((i * charsJ) + j);
                }
            }
        } else {
            for (int j = 0; j < charsJ; j++) {
                for (int i = 0; i < charsI; i++) {
                    chars[i][j] = string.charAt((j * charsI) + i);
                }
            }
        }

        return chars;
    }

    public static int countLineLength(String book) {
        System.out.println(book.indexOf('-'));
        return (book.indexOf('-') + 1);

    }
}
