package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BDFileReader {

    public List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();

        String absolutePath = new File("").getAbsolutePath();
        String path = absolutePath + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }

        return lines;
    }
}
