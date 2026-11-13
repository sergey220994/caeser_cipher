package com.javarush.caesar;

import com.javarush.caesar.core.BruteForce;
import com.javarush.caesar.core.Cipher;
import com.javarush.caesar.exception.CipherException;
import com.javarush.caesar.service.FileManager;
import com.javarush.caesar.service.Validator;

import java.util.Scanner;

public class MainApp {

    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    private final Scanner scanner = new Scanner(System.in);
    private final FileManager fileManager = new FileManager();
    private final Validator validator = new Validator();
    private final Cipher cipher = new Cipher(ALPHABET);
    private final BruteForce bruteForce = new BruteForce();

    public static void main(String[] args) {
        new MainApp().run();
    }

    private void run() {
        System.out.println("Шифр Цезаря: шифрование и расшифровка файлов");
        while (true) {
            printMenu();
            String choice = ask("Выберите действие: ");
            try {
                switch (choice) {
                    case "1":
                        encryptFile();
                        break;
                    case "2":
                        decryptFile();
                        break;
                    case "3":
                        bruteForceFile();
                        break;
                    case "0":
                    case "exit":
                        System.out.println("До свидания!");
                        return;
                    default:
                        System.out.println("Нет такого пункта: " + choice);
                }
            } catch (CipherException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Зашифровать файл");
        System.out.println("2. Расшифровать файл с ключом");
        System.out.println("3. Взломать перебором ключей");
        System.out.println("0. Выход (или exit)");
    }

    private void encryptFile() throws CipherException {
        String input = ask("Укажите исходный файл: ");
        String key = ask("Укажите ключ для шифрования \uD83D\uDD11 : ");

        if (!validator.isValidKey(Integer.parseInt(key), ALPHABET)){
            throw new CipherException("Указан неправильный ключ \u26A0 ");
        }

        if (!validator.isFileExists(input)){
            throw new CipherException("Файл не существует \u274C ");
        }

        String newinput = fileManager.readFile(input);

        String txt = cipher.encrypt(newinput, Integer.parseInt(key));

        String output = ask("Укажите путь для сохранения файла: ");
        fileManager.writeFile(txt, output);


        System.out.println("Файл успешно зашифрован \u2705 ");

    }

    private void decryptFile() throws CipherException {

        String input = ask("Укажите исходный файл для расшифровки: ");
        String key = ask("Укажите ключ шифрования \uD83D\uDD11 : ");

        if (!validator.isValidKey(Integer.parseInt(key), ALPHABET)){
            throw new CipherException("Указан неправильный ключ . \u26A0 ");
        }

        if (!validator.isFileExists(input)){
            throw new CipherException("файл не существует \u274C");
        }

        String newinput = fileManager.readFile(input);

        String txt = cipher.decrypt(newinput, Integer.parseInt(key));

        String output = ask("укажите путь для сохранения файла: ");
        fileManager.writeFile(txt, output);


        System.out.println("Файл успешно расшифрован \u2705 ");

    }

    private void bruteForceFile() throws CipherException {
        String sayFile = ask("Укажите путь к зашифрованному файлу: ");
        String sayFinishFile = ask("Укажите путь для сохранения расшифрованного файла: ");
        String writeTxt = bruteForce.decryptByBruteForce(fileManager.readFile(sayFile), ALPHABET );

        fileManager.writeFile(writeTxt, sayFinishFile);
        System.out.println("Готово \u2705");


    }

    private String ask(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
