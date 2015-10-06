package me.stoliarov.anycipher.cipher;

/**
 * Created by Владислав on 23.09.2015.
 */
public interface Cipher {
    public static final String ALPHABET = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЬЫЭЮЯ";
    public static final String ALPHABET_S = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЬЫЭЮЯ ";
    public static final int LENGTH = ALPHABET.length();
    public static final int LENGTH_S = ALPHABET_S.length();

    public String getEncrypted(String text);

    public String getDecrypted(String text);

}
