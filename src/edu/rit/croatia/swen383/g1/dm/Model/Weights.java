package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Weights extends csvModel {
    private ArrayList<Object> weights;

    public Weights(FileHandler fh) {
        super(fh);
    }

    String filePath = "Vendor\\log.csv";

    public ArrayList<Object> getWeights() {
        return weights;
    }

    public void setWeights(ArrayList<Object> weights) {
        this.weights = weights;
    }

    @Override
    public ArrayList<Object> read(String filepath) throws IOException {
        weights = new ArrayList<>();
        BufferedReader br = new BufferedReader(fh.getReader(filepath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[3].equals("w")) {
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);
                double weight = Double.parseDouble(parts[4]);
                Weight w = new Weight(year, month, day, weight);
                weights.add(w);
            }
        }
        br.close();
        return weights;
    }

    @Override
    public void write(String filepath, Object item) throws IOException {
        weights = new ArrayList<>();
        BufferedWriter bw = new BufferedWriter(fh.getWriter(filepath));
        bw.newLine();
        if (item instanceof Weight) {
            Weight weight = (Weight) item;
            String line = String.format("%d,%02d,%02d,w,%.1f", weight.getYear(), weight.getMonth(), weight.getDay(),
                    weight.getWeight());
            bw.write(line);
            weights.add(weight);
        }
        bw.close();
    }

    @Override
    public void update(int index, Object item) throws IOException {
        if (index >= 0 && index < weights.size()) {
            weights.set(index, (Weight) item);
            write(filePath, item);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public void remove(Object item) throws IOException {
        if (weights.contains(item)) {
            weights.remove(item);
            fh.clearFile(filePath);
            for (Object weight : weights) {
                write(filePath, weight);
            }
        } else {
            throw new IllegalArgumentException("Item not found in the list");
        }
    }

    @Override
    public ArrayList<Object> getData() {
        return new ArrayList<Object>(weights);
    }

    @Override
    public void setData(ArrayList<Object> data) {
        weights.clear();
        for (Object obj : data) {
            if (obj instanceof Weight) {
                weights.add((Weight) obj);
            }
        }
    }
}
