package me.stoliarov.anycipher.cipher;

import static me.stoliarov.anycipher.cipher.util.CipherHelper.getSloganAlphabet;

/**
 * Created by Владислав on 24.09.2015.
 */

public class SloganCipher implements Cipher {
    private final String key;
    private final String sloganAlphabet;
    private String text;


    public SloganCipher(String key) {
        this.key = key.toUpperCase();
        this.sloganAlphabet = getSloganAlphabet(this.key);
    }

    public static void main(String[] args) {
        Cipher cipher = new SloganCipher("БЕЗОПАСНОСТЬ");
        String result = cipher.getEncrypted("ПОЛУЧЕНА ТЕЛЕГРАММА");
        System.out.println(result);
        System.out.println(cipher.getDecrypted(result));
    }

    @Override
    public String getEncrypted(String text) {
        this.text = text.toUpperCase();
        return encrypt();
    }

    @Override
    public String getDecrypted(String text) {
        this.text = text.toUpperCase();
        return decrypt();
    }

    private String encrypt() {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ') result.append(' ');
            else result.append(sloganAlphabet.charAt(ALPHABET.indexOf(c)));
        }
        return result.toString();
    }

    private String decrypt() {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == ' ') result.append(' ');
            else result.append(ALPHABET.charAt(sloganAlphabet.indexOf(c)));
        }
        return result.toString();
    }
}
