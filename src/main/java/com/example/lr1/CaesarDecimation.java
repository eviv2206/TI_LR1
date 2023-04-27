package com.example.lr1;

import java.security.InvalidParameterException;

public class CaesarDecimation {

    private static final String upperAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String lowerAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static final int NUM_OF_LETTERS = 33;

    public static String encrypt(String plainText, String shiftStr) {
        int shift;
        try {
            plainText = Utils.getValidData(plainText);
        } catch (InvalidParameterException e) {
            throw new InvalidParameterException("Передана пустая строка или строка содержащая только недопустимые символы");
        }
        try {
            shift = Utils.getValidKeyForCaesar(shiftStr);
        } catch (InvalidParameterException e){
            throw new InvalidParameterException("Передан пустой ключ или ключ содержащий только недопустимые символы");
        }
        if (areCoprime(33, shift)) {
            StringBuilder output = new StringBuilder();

            for (int i = 0; i < plainText.length(); i++) {

                char c = plainText.charAt(i);

                if (upperAlphabet.contains(String.valueOf(c)) || lowerAlphabet.contains(String.valueOf(c))) {
                    if (Character.isUpperCase(c)) {
                        int numOfLetter = upperAlphabet.indexOf(c) + 1;
                        int cipherNum = (numOfLetter * shift) % NUM_OF_LETTERS;
                        if (cipherNum == 0) {
                            output.append(upperAlphabet.charAt(upperAlphabet.length() - 1));
                        } else {
                            output.append(upperAlphabet.charAt(cipherNum - 1));
                        }
                    } else {
                        int numOfLetter = lowerAlphabet.indexOf(c) + 1;
                        int cipherNum = (numOfLetter * shift) % NUM_OF_LETTERS;
                        if (cipherNum == 0) {
                            output.append(lowerAlphabet.charAt(lowerAlphabet.length() - 1));
                        } else {
                            output.append(lowerAlphabet.charAt(cipherNum - 1));
                        }
                    }
                }
            }
            return output.toString();
        } else {
            throw new InvalidParameterException("Ключ должен быть взаимно простым числом с 33");
        }
    }

    public static String decrypt(String cipherText, String shiftStr) {
        int shift;
        try {
            cipherText = Utils.getValidData(cipherText);
        } catch (InvalidParameterException e) {
            throw new InvalidParameterException("Передана пустая строка или строка содержащая только недопустимые символы");
        }
        try {
            shift = Utils.getValidKeyForCaesar(shiftStr);
        } catch (InvalidParameterException e){
            throw new InvalidParameterException("Передан пустой ключ или ключ содержащий только недопустимые символы");
        }

        int reverseShift = 0;
        for (int i = 0; i < NUM_OF_LETTERS; i++) {
            if ((i * shift) % NUM_OF_LETTERS == 1) {
                reverseShift = i;
                break;
            }
        }
        return encrypt(cipherText, String.valueOf(reverseShift));
    }

    private static boolean areCoprime(int a, int b) {
        int gcd = findGCD(a, b);
        return gcd == 1;
    }

    private static int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return findGCD(b, a % b);
    }
}
