package com.javarush.caesar.core;

import com.javarush.caesar.exception.CipherException;

import java.util.HashMap;

public class Cipher {

    private final char[] alphabet ;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int shift) {
        // TODO сдвинуть каждый символ алфавита на shift позиций вперед по кругу
        //  символ, которого нет в алфавите, пропускаем
        //  позицию символа ищем быстрее, чем перебором всего массива
        char[] charArray = text.toCharArray();
        StringBuilder result = new StringBuilder();
         String st = String.valueOf(alphabet);
//        HashMap<String, Integer> st = new HashMap<>();

        for (char symbol : charArray){
            int index = -1;
            index = st.indexOf(symbol);

            if(index == -1){
                continue;
            }

            int newindex = (index + shift) % alphabet.length;  //индекс зашифрованного символа по индексу
            char ch = alphabet[newindex]; //получили символ по индексу
            result.append(ch);

        }
        return result.toString();


    }

    public String decrypt(String encryptedText, int shift) throws CipherException {
        // TODO сдвинуть каждый символ на shift позиций назад по кругу
        //  символ, которого нет в алфавите, - ошибка: бросаем CipherException
        throw new UnsupportedOperationException("Метод decrypt пока не написан");
    }
}
