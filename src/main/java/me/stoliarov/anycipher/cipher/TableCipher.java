package me.stoliarov.anycipher.cipher;

import static me.stoliarov.anycipher.cipher.util.CipherHelper.getSloganAlphabet;
import static me.stoliarov.anycipher.cipher.util.CipherHelper.stringToCharArr;

/**
 * Created by Владислав on 24.09.2015.
 */

public class TableCipher implements Cipher {
    private final String key;
    private final String sloganAlphabet;
    private final int charsI;
    private final int charsJ;
    private final char[][] tableChars;
    private String text;

    public TableCipher(final String key) {
        this.key = key.replaceAll("\\s+", "").toUpperCase();
        this.sloganAlphabet = getSloganAlphabet(this.key);
        this.charsI = this.sloganAlphabet.length() / 6;
        this.charsJ = 6;
        this.tableChars = stringToCharArr(sloganAlphabet, true, charsI, charsJ);
    }

    public static void main(String[] args) {
        Cipher cipher = new TableCipher("ШИФРУЮЩАЯ ТАБЛИЦА");
        String result = cipher.getEncrypted("ЗАЩИТА ИНФОРМАЦИИ");
        System.out.println(result);
        System.out.println(cipher.getDecrypted(result));
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
        for (char c : text.toCharArray()) {
            int idxI = 0, idxJ = 0;
            for (int j = 0; j < charsI; j++) {
                for (int k = 0; k < charsJ; k++) {
                    if (c == tableChars[j][k]) {
                        idxI = k + 1;
                        idxJ = j + 1;
                    }
                }
            }
            result.append(idxI + "," + idxJ + ";");
        }
        return result.toString();
    }

    private String decrypt() {
        StringBuilder result = new StringBuilder();
        String[] encryptCoords = text.split(";");
        int idxI, idxJ;
        for (String s : encryptCoords) {
            String[] idx = s.split(",");
            idxJ = Integer.parseInt(idx[0]) - 1;
            idxI = Integer.parseInt(idx[1]) - 1;
            result.append(tableChars[idxI][idxJ]);
        }
        return result.toString();
    }
}
