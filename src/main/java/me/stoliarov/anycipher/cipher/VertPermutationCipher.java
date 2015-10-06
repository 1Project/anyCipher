package me.stoliarov.anycipher.cipher;

import static me.stoliarov.anycipher.cipher.util.CipherHelper.stringToCharArr;

/**
 * Created by Владислав on 23.09.2015.
 */

public class VertPermutationCipher implements Cipher {
    private final String key;
    private String text;
    private int charsI;
    private int charsJ;


    public VertPermutationCipher(final String key) {
        this.key = key;
    }

    public static void main(String[] args) {
        Cipher cipher = new VertPermutationCipher("614253");
        String result = cipher.getEncrypted("Шифр перестановки изменяет порядок следования букв открытого текста");
        System.out.println(result);
        System.out.println(cipher.getDecrypted(result));
    }

    @Override
    public String getEncrypted(String text) {
        this.text = text.replaceAll("\\s+", "").toUpperCase();
        this.charsI = this.text.length() / this.key.length();
        this.charsJ = this.key.length();
        return encrypt();
    }

    @Override
    public String getDecrypted(String text) {
        this.text = text.replaceAll("\\s+", "").toUpperCase();
        this.charsI = this.text.length() / this.key.length();
        this.charsJ = this.key.length();
        return decrypt();
    }

    public String encrypt() {
        StringBuilder result = new StringBuilder();
        char[][] chars = stringToCharArr(text, true, charsI, charsJ);
        for (int j = 0; j < charsJ; j++) {
            for (int i = 0; i < charsI; i++) {
                result.append(chars[i][key.indexOf(String.valueOf(j + 1))]);
            }
            result.append(" ");
        }
        return result.toString();
    }

    public String decrypt() {
        StringBuilder result = new StringBuilder();
        char[][] chars = stringToCharArr(text, false, charsI, charsJ);
        for (int i = 0; i < charsI; i++) {
            for (int j = 0; j < charsJ; j++) {
                result.append(chars[i][Integer.parseInt(String.valueOf(key.charAt(j))) - 1]);
            }
        }
        return result.toString();
    }
}
