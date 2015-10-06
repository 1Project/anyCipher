package me.stoliarov.anycipher.cipher;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Владислав on 24.09.2015.
 */

public class BookCipher implements Cipher {
    private static final String BOOK =
            " Задача надежной защи-\nты информации от не-\nсанкционированного дос-\nтупа " +
                    "является одной из\nдревнейших и нерешен-\nных до настоящего вре-\nмени " +
                    "проблем. Способы и\nметоды скрытия секрет-\nных сообщений известны с " +
                    "давних времен, причем\n данная сфера человеческой деятельности полу-\nчила " +
                    "название \"стегано-\nграфия\"." +
                    "\rЭто слово происходит\nот греческих слов\nsteganos (секрет, тайна) и\n graphy " +
                    "(запись) и, таким\nобразом, означает бук-\nвально \"тайнопись\", хотя\nметоды " +
                    "стеганографии\nпоявились, вероятно,\nраньше, чем появилась сама письменность" +
                    " (пер-\nвоначально использова-\nлись условные знаки и\nобозначения).\"";
    private static final String BOOKUP = BOOK.toUpperCase();
    private final String key;
    private String text;
    private char[][][] sbook = new char[100][100][100];


    public BookCipher(String key) {
        this.key = key;
    }

    public static void main(String[] args) {
        BookCipher cipher = new BookCipher("");
        String result = cipher.getEncrypted("СИСТЕМЫ БЕЗОПАСНОСТИ");
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
        this.text = text;

        return decrypt();
    }

    private String encrypt() {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            List<String> occurences = (bookHelper(allOccurences(c)));
            result.append(occurences.get(new Random().nextInt(occurences.size())));
            result.append(" ");
        }
        return result.toString();
    }

    public ArrayList<Integer> allOccurences(char c) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int index = BOOKUP.indexOf(c);
        while (index >= 0) {
            arrayList.add(index);
            index = BOOKUP.indexOf(c, index + 1);
        }
        return arrayList;
    }
    private void initSBook() {
        char[] chars = BOOKUP.toCharArray();
        int page = 0, line = 0, idx = 0;
        for (int i = 0; i < chars.length; i++) {
            char tmp = chars[i];
            if (tmp == '\n') {
                sbook[page][line][idx] = tmp;
                idx = 0;
                line++;
            } else if (tmp == '\r') {
                sbook[page][line][idx] = tmp;
                idx = 0;
                line = 0;
                page++;
            } else {
                sbook[page][line][idx] = tmp;
                idx++;
            }
        }
    }
    private List<String> bookHelper(ArrayList<Integer> array) {
        List<String> arrayList = new ArrayList<>();
        StringBuilder string = new StringBuilder();
        char[] chars = BOOKUP.toCharArray();
        int page = 0, line = 0, idx = 0;
        for (int i = 0; i < chars.length; i++) {
            char tmp = chars[i];
            if (array.contains(i)) {
                string.append(String.format("%02d", page + 1));
                string.append(String.format("%02d", line + 1));
                string.append(String.format("%02d", idx + 1));
                arrayList.add(string.toString());
                string = new StringBuilder();
            }
            if (tmp == '\n') {
                sbook[page][line][idx] = tmp;
                idx = 0;
                line++;
            } else if (tmp == '\r') {
                sbook[page][line][idx] = tmp;
                idx = 0;
                line = 0;
                page++;
            } else {
                sbook[page][line][idx] = tmp;
                idx++;
            }
        }
        return arrayList;
    }

    private String decrypt() {
        initSBook();
        String[] arr = text.split(" ");
        StringBuilder result = new StringBuilder();
        for (String s : arr) {
            int idx = Integer.parseInt(s.substring(0, 2));
            int line = Integer.parseInt(s.substring(2, 4));
            int page = Integer.parseInt(s.substring(4, 6));
            result.append(sbook[idx - 1][line - 1][page - 1]);
        }
        return result.toString();
    }

}
