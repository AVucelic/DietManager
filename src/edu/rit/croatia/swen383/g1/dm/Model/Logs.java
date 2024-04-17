package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Logs extends csvModel {
    ArrayList<Object> logs;

    public ArrayList<Object> getData() {
        return logs;
    }

    public void setData(ArrayList<Object> data) {
        this.logs = data;
    }

    String filePath = "Vendor\\log.csv";

    public Logs(FileHandler fh) {
        super(fh);
    }

    @Override
    public ArrayList<Object> read(String filepath) throws IOException {
        logs = new ArrayList<>();
        BufferedReader br = new BufferedReader(fh.getReader(filepath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] attributes = line.split(",");
            Log log;
            char recordType = attributes[3].charAt(0);
            if (recordType == 'w' || recordType == 'c') {
                log = new Log(attributes[0] + "-" + attributes[1] + "-" + attributes[2], recordType,
                        Double.parseDouble(attributes[4]));
            } else {
                log = new Log(attributes[0] + "-" + attributes[1] + "-" + attributes[2], recordType, attributes[4],
                        Double.parseDouble(attributes[5]));
            }
            logs.add(log);
        }
        br.close();
        return logs;
    }

    @Override
    public void write(String filepath, Object item) throws IOException {
        BufferedWriter bw = new BufferedWriter(fh.getWriter(filepath));
        bw.newLine();
        if (item instanceof Log) {
            Log log = (Log) item;
            char recordType = log.getRecordType();
            if (recordType == 'w' || recordType == 'c') {
                String line = log.getDate() + "," + recordType + "," + log.getWeight();
                bw.write(line);
            } else if (recordType == 'r') {
                StringBuilder lineBuilder = new StringBuilder();
                lineBuilder.append(log.getDate()).append(",").append(recordType).append(",")
                        .append(log.getFoodName()).append(",").append(log.getServings());

                for (Map.Entry<String, Double> entry : log.getIngredients().entrySet()) {
                    lineBuilder.append(",").append(entry.getKey()).append(",").append(entry.getValue());
                }

                bw.write(lineBuilder.toString());
            } else {
                String line = log.getDate() + "," + recordType + "," + log.getFoodName() + "," + log.getServings();
                bw.write(line);
            }
            logs.add(log);
        }
        bw.flush();
        bw.close();
    }

    @Override
    public void update(int index, Object item) throws IOException {
        if (logs != null && index >= 0 && index < logs.size()) {
            logs.set(index, item);
            fh.clearFile(filePath);
            for (Object log : logs) {
                write(filePath, log);
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void remove(Object item) throws IOException {
        if (logs != null && !logs.isEmpty()) {
            if (item instanceof Log) {
                Log logToRemove = (Log) item;
                String dateToRemove = logToRemove.getDate();
                String nameToRemove = logToRemove.getFoodName();

                logs.clear();
                BufferedReader br = new BufferedReader(fh.getReader(filePath));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] attributes = line.split(",");
                    String date = attributes[0] + "," + attributes[1] + "," + attributes[2];
                    String name = attributes[4];
                    if (!date.equals(dateToRemove) || !name.equals(nameToRemove)) {
                        logs.add(line);
                    }
                }
                br.close();

                BufferedWriter bw = new BufferedWriter(fh.getWriter(filePath));
                fh.clearFile(filePath);
                int lastIndex = logs.size() - 1;
                for (int i = 0; i < logs.size(); i++) {
                    bw.write(logs.get(i).toString());
                    if (i != lastIndex) {
                        bw.newLine();
                    }
                }
                bw.flush();
                bw.close();
            } else {
                throw new IllegalArgumentException("Invalid item type");
            }
        } else {
            throw new IllegalStateException("Logs list is null or empty");
        }
    }
}
