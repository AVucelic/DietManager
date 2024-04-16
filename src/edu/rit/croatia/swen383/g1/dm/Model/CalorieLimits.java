package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CalorieLimits extends csvModel {
    private ArrayList<Object> calorieLimits;

    public CalorieLimits(FileHandler fh) {
        super(fh);
    }

    String filePath = "Vendor\\log.csv";

    public ArrayList<Object> getCalorieLimits() {
        return calorieLimits;
    }

    public void setCalorieLimits(ArrayList<Object> calorieLimits) {
        this.calorieLimits = calorieLimits;
    }

    @Override
    public ArrayList<Object> read(String filepath) throws IOException {
        calorieLimits = new ArrayList<>();
        BufferedReader br = new BufferedReader(fh.getReader(filepath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[3].equals("c")) {
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int day = Integer.parseInt(parts[2]);
                double calories = Double.parseDouble(parts[4]);
                CalorieLimit cl = new CalorieLimit(year, month, day, calories);
                calorieLimits.add(cl);
            }
        }
        br.close();
        return calorieLimits;
    }

    @Override
    public void write(String filepath, Object item) throws IOException {
        calorieLimits = new ArrayList<>();
        BufferedWriter bw = new BufferedWriter(fh.getWriter(filepath));
        bw.newLine();
        if (item instanceof CalorieLimit) {
            CalorieLimit calorieLimit = (CalorieLimit) item;
            calorieLimits.add(item);
            String line = String.format("%d,%02d,%02d,c,%.1f", calorieLimit.getYear(), calorieLimit.getMonth(),
                    calorieLimit.getDay(),
                    calorieLimit.getCalories());
            bw.write(line);
        }
        bw.close();
    }

    @Override
    public void update(int index, Object item) throws IOException {
        if (index >= 0 && index < calorieLimits.size()) {
            calorieLimits.set(index, (CalorieLimit) item);
            write(filePath, item);
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public void remove(Object item) throws IOException {
        if (calorieLimits.contains(item)) {
            calorieLimits.remove(item);
            fh.clearFile(filePath);
            for (Object calorieLimit : calorieLimits) {
                write(filePath, calorieLimit);
            }
        } else {
            throw new IllegalArgumentException("Item not found in the list");
        }
    }

    @Override
    public ArrayList<Object> getData() {
        return new ArrayList<>(calorieLimits);
    }

    @Override
    public void setData(ArrayList<Object> data) {
        calorieLimits.clear();
        for (Object obj : data) {
            if (obj instanceof CalorieLimit) {
                calorieLimits.add((CalorieLimit) obj);
            }
        }
    }
}
