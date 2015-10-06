package me.stoliarov.anycipher.cipher;

/**
 * Created by Владислав on 24.09.2015.
 */

public class ModGammaCypher implements Cipher {
    private final static int[] k = pseudoGenerator(10);
    private final String key;
    private String text;

    public ModGammaCypher(String key) {
        this.key = key;
    }

    private static int[] pseudoGenerator(int k1) {
        int[] k = new int[50];
        k[0] = k1;
        for (int i = 1; i < k.length; i++) {
            k[i] = (7 * k[i - 1] + 1) % 30;
        }
        return k;
    }

    public static void main(String[] args) {
        Cipher cipher = new ModGammaCypher("10");
        System.out.println(cipher.getEncrypted("Криптография"));
        System.out.println(cipher.getDecrypted("Криптография"));
    }

    @Override
    public String getEncrypted(String text) {
        this.text = text.replaceAll("\\s+", "").toUpperCase();
        return encrypt();
    }

    @Override
    public String getDecrypted(String text) {
        this.text = text.replaceAll("\\s+", "").toUpperCase();
        return decrypt();
    }

    private String encrypt() {
        StringBuilder result = new StringBuilder();
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int idx = (ALPHABET.indexOf(chars[i]) + k[i]) % LENGTH;
            result.append(ALPHABET.charAt(idx));
        }
        return result.toString();
    }

    private String decrypt() {
        StringBuilder result = new StringBuilder();
        char[] chars = encrypt().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int idx = (ALPHABET.indexOf(chars[i]) - k[i]) % LENGTH;
            if (idx < 0) idx += LENGTH;
            result.append(ALPHABET.charAt(idx));
        }
        return result.toString();
    }
}
