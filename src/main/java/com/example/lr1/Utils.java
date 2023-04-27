package com.example.lr1;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String getValidData(String text){
        if (text == null){
            throw new InvalidParameterException("Пустая строка как значение");
        }
        String regex = "[^а-яА-ЯёЁ]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        String output = matcher.replaceAll("").toLowerCase();
        if (output.length() == 0){
            throw new InvalidParameterException("Пустая строка как значение");
        } else {
            return output;
        }
    }

    public static Integer getValidKeyForCaesar(String shiftStr){
        if (shiftStr == null){
            throw new InvalidParameterException("Пустая строка как значение");
        }
        shiftStr = shiftStr.replaceAll("\\D+", "");
        if (shiftStr.length() == 0){
            throw new InvalidParameterException("Пустой ключ как значение");
        } else {
            return Integer.parseInt(shiftStr);
        }
    }
}
