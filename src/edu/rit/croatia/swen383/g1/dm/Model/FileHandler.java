package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public BufferedReader getReader(String filePath) throws IOException {
        return new BufferedReader(new FileReader(filePath));
    }

    public BufferedWriter getWriter(String filePath) throws IOException {
        return new BufferedWriter(new FileWriter(filePath, true));
    }

    public void clearFile(String filePath) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
