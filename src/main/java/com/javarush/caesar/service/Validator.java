package com.javarush.caesar.service;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {

    public boolean isValidKey(int key, char[] alphabet) {
        if(key <= alphabet.length -1 && key >= 0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isFileExists(String filePath) {
        Path path = Path.of(filePath);
        return Files.isRegularFile(path);
    }
}
