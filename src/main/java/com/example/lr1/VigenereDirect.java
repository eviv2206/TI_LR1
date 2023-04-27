package com.example.lr1;

import java.security.InvalidParameterException;
public class VigenereDirect {


    private static final String lowerAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static final int NUM_OF_LETTERS = 33;

    public static String encrypt(String text, String key) {
        String plainText;
        try {
            plainText = Utils.getValidData(text);
        } catch (InvalidParameterException e){
            throw new InvalidParameterException("Передана пустая строка или строка содержащая только недопустимые символы");
        }
        try {
            key = Utils.getValidData(key);
        } catch (InvalidParameterException e){
            throw new InvalidParameterException("Передан пустой ключ или ключ содержащий только недопустимые символы");
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (lowerAlphabet.contains(String.valueOf(c))) {
                int numOfLetter = lowerAlphabet.indexOf(c) + 1;
                int cipherNum = (numOfLetter + lowerAlphabet.indexOf(key.charAt(i % key.length()))) % NUM_OF_LETTERS;
                if (cipherNum == 0){
                    output.append(lowerAlphabet.charAt(lowerAlphabet.length() - 1));
                } else {
                    output.append(lowerAlphabet.charAt(cipherNum - 1));
                }
            }
        }
        return output.toString();
    }

    public static String decrypt(String text, String key) {
        String plainText;
        try {
            plainText = Utils.getValidData(text);
        } catch (InvalidParameterException e){
            throw new InvalidParameterException("Передана пустая строка или строка содержащая только недопустимые символы");
        }
        try {
            key = Utils.getValidData(key);
        } catch (InvalidParameterException e){
            throw new InvalidParameterException("Передан пустой ключ или ключ содержащий только недопустимые символы");
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (lowerAlphabet.contains(String.valueOf(c))) {
                int numOfLetter = lowerAlphabet.indexOf(c) + 1;
                int cipherNum = (numOfLetter - lowerAlphabet.indexOf(key.charAt(i % key.length())) + NUM_OF_LETTERS) % NUM_OF_LETTERS;
                if (cipherNum == 0){
                    output.append(lowerAlphabet.charAt(lowerAlphabet.length() - 1));
                } else {
                    output.append(lowerAlphabet.charAt(cipherNum - 1));
                }
            }
        }
        return output.toString();
    }


}
