package nl.klev.eleasebackend.utilities;

import nl.klev.eleasebackend.models.Account;
import nl.klev.eleasebackend.repositories.AccountRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class WriteToFile {

    public static void toFile(Account account) {

        int number = account.getClass().getFields().length;
        String data = account.toString();
        String separator = data + System.lineSeparator();
        File file = new File("output.txt");
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = number; i > 0; i--) {
                bufferedWriter.write(separator);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}