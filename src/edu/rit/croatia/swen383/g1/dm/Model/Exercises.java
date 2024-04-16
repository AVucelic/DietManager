package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Exercises extends csvModel {
    ArrayList<Object> exercises;

    public Exercises(FileHandler fh) {
        super(fh);
        exercises = new ArrayList<>();
    }

    @Override
    public ArrayList<Object> read(String filepath) throws IOException {
        exercises = new ArrayList<>();
        BufferedReader br = new BufferedReader(fh.getReader(filepath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] attribues = line.split(",");
            Exercise exercise = new Exercise(attribues[0], attribues[1], Double.parseDouble(attribues[2]));
            exercises.add(exercise);
        }
        br.close();
        return exercises;
    }

    @Override
    public void write(String filepath, Object item) throws IOException {
        BufferedWriter bw = new BufferedWriter(fh.getWriter(filepath));
        bw.newLine();
        Exercise de = (Exercise) item;
        exercises.add(de);
        bw.write(de.formatToCSV());
        bw.flush();
        bw.close();
    }

    @Override
    public ArrayList<Object> getData() {
        try {
            return read("Vendor/exercise.csv");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setData(ArrayList<Object> data) {
        this.exercises = data;
    }

    @Override
    public void update(int index, Object item) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(Object item) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

}
