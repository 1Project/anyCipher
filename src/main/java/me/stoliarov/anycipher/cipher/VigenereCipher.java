package me.stoliarov.anycipher.cipher;

/**
 * Created by Владислав on 24.09.2015.
 */

public class VigenereCipher implements Cipher {
    private final String key;
    private String text;
    private int[] keys;

    public VigenereCipher(String key) {
        this.key = key.toUpperCase();

    }

    private void vigenereHelper() {
        this.keys = new int[this.key.length()];
        String[] sKeys = this.key.split("");
        for (int i = 0; i < keys.length; i++) {
            keys[i] = ALPHABET.indexOf(sKeys[i]);
        }

    }
    public static void main(String[] args) {
        Cipher cipher = new VigenereCipher("КОНФИДЕНЦИАЛЬНОКОНФИДЕНЦИАЛЬНО");
        String result = cipher.getEncrypted("ИНФОРМАЦИОННАЯБЕЗОПАСНОСТЬ");
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

    public String encrypt() {
        vigenereHelper();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i)==' ') {
                result.append(' ');
            } else {
                    int num = (ALPHABET.indexOf(text.charAt(i)) + keys[i]) % LENGTH;
                    result.append(ALPHABET.charAt(num));
            }
        }
        return result.toString();
    }

    public String decrypt() {
        {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i)==' ') {
                    result.append(' ');
                } else {
                        int num = ((ALPHABET.indexOf(text.charAt(i)) - keys[i % key.length()] + LENGTH) % LENGTH);
                        result.append(ALPHABET.charAt(num));
                    }
            }
            return result.toString();
        }
    }
}
