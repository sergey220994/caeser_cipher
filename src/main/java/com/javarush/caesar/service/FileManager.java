package com.javarush.caesar.service;

import com.javarush.caesar.exception.CipherException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {

    public String readFile(String filePath) throws CipherException {
        try {
            Path path = Path.of(filePath);
            String file = Files.readString(path, StandardCharsets.UTF_8);
            return  file;


        } catch (IOException e) {
            throw new CipherException("Не удалось прочитать файл" , e);

        }
    }

    public void writeFile(String content, String filePath) throws CipherException {
        Path path = Path.of(filePath);

        try {
            Files.writeString(path, content, StandardOpenOption.CREATE_NEW);
        }

        catch (IOException e){
            throw new CipherException("Не удалось записать файл" , e);
        }

    }
}
