package me.stoliarov.anycipher.cipher;

/**
 * Created by Владислав on 23.09.2015.
 */
public class CezarCipher implements Cipher {
    private final String key;
    private final int intKey;
    private String text;


    public CezarCipher(final String key) {
        this.key = key;
        this.intKey = Integer.parseInt(key);
    }

    public static void main(String[] args) {
        String result = new CezarCipher("3").getEncrypted("Защита Информации");
        System.out.println(result);
        System.out.println(new CezarCipher("3").getDecrypted(result));
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
        String result = "";
        for (char ch : text.toCharArray()) {
            if (ch == ' ') result += ' ';
            else result += rotate(ch, intKey);
        }
        return result;
    }

    private String decrypt() {
        String result = "";
        for (char ch : text.toCharArray()) {
            if (ch == ' ') result += ' ';
            else result += rotate(ch, -intKey);
        }
        return result;
    }

    private char rotate(char c, int key) {
        int i = 0;
        while (i < LENGTH) {
            if (c == ALPHABET.charAt(i)) return ALPHABET.charAt((i + key + LENGTH) % LENGTH);
            i++;
        }
        return c;
    }
}
