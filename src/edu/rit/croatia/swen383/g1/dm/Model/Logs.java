package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logs extends csvModel {
    ArrayList<Object> logs;

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
            char recordType = attributes[1].charAt(0); 
            if (recordType == 'w' || recordType == 'c') {
                log = new Log(attributes[0], recordType, Double.parseDouble(attributes[2]));
            } else {
                log = new Log(attributes[0], recordType, attributes[2], Double.parseDouble(attributes[3]));
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
            } else {
                String line = log.getDate() + "," + recordType + "," + log.getFoodName() + "," + log.getServings();
                bw.write(line);
            }
        }
        bw.flush();
        bw.close();
    }

    public void update() throws IOException {
        
    }
}
