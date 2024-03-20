package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class csvModel {
    /**
     * Abstract method to update data.
     */
    public abstract void update();

    /**
     * Reads data from a CSV file.
     * @param fileName the name of the CSV file to read from.
     */
    public ArrayList<?> read(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCsvLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Writes data to a CSV file.
     * @param fileName the name of the CSV file to write to.
     */
    public void write(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            String csvData = getCsvData();
            bw.write(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Abstract method to process a line of CSV data.
     * @param line the line of CSV data to process.
     */
    protected abstract Object processCsvLine(String line);

    /**
     * Abstract method to get CSV data to write.
     * @return a string representing the CSV data to write.
     */
    protected abstract String getCsvData();
}
