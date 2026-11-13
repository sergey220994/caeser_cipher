package com.javarush.caesar.core;

import com.javarush.caesar.exception.CipherException;

public class BruteForce {

    public String decryptByBruteForce(String encryptedText, char[] alphabet) throws CipherException {

        Cipher cipher = new Cipher(alphabet);

        int bestScore = -1;
        String newText = "";

        for (int i = 1; i <= alphabet.length -1 ; i++) {
            String txt = cipher.decrypt(encryptedText, i);
            int score = 0;
            if (txt.contains(" и ")){
                score++;
            }
            if (txt.contains(" ")){
                score++;
            }
            if (txt.contains(". ") || txt.contains(", ") || txt.contains("? ") || txt.contains("! ")){
                score++;
            }

            if (txt.contains(" не "))
            {
                score++;

            }
            if (txt.contains(" в ")){
                score++;
            }
            if (txt.contains(" на ")){
                score++;
            }

            if (txt.contains(" что ")){
                score++;
            }

            if (txt.contains(" это ")){
                score++;
            }

            if (txt.contains(" как ")){
                score++;
            }
            if (txt.contains("пр")){
                score++;
            }
            if(txt.contains("ст")){
                score++;
            }


            if (score > bestScore){
                bestScore = score;
                newText = txt;
            }

        }
        return newText;
    }
}
