package com.javarush.caesar.core;

import com.javarush.caesar.exception.CipherException;

import java.util.HashMap;

public class Cipher {

    private final char[] alphabet ;

    public Cipher(char[] alphabet) {
        this.alphabet = alphabet;
    }

    public String encrypt(String text, int shift) {
        char[] charArray = text.toLowerCase().toCharArray();
        StringBuilder result = new StringBuilder();
        HashMap<Character, Integer> st = new HashMap<>();
        for (int i = 0; i < alphabet.length; i++) {
            st.put(alphabet[i],i );
        }
        

        for (char symbol : charArray){
            if (symbol == '\n' || symbol == '\r'){

                result.append(symbol);
                continue;
            }
            if (!st.containsKey(symbol)){
                continue;
            }

            int index = st.get(symbol);

            int newindex = (index + shift) % alphabet.length;  //поиск зашифрованного символа по индексу
            char ch = alphabet[newindex]; //получили символ по индексу
            result.append(ch);

        }
        return result.toString();


    }

    public String decrypt(String encryptedText, int shift) throws CipherException {
        char[] charArray = encryptedText.toLowerCase().toCharArray();
        StringBuilder result = new StringBuilder();
        String st = String.valueOf(alphabet);
        for (char symbol : charArray){
            if (symbol == '\n' || symbol == '\r'){

                result.append(symbol);
                continue;
            }

            int index = st.indexOf(symbol);
            if(index == -1)
            {
                throw new CipherException("УПС...такого символа нет");
            }

            int newindex = ((index - shift) + alphabet.length )% alphabet.length;
            char ch = alphabet[newindex];
            result.append(ch);

        }
        return result.toString();

    }
}
