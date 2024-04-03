package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import Factory.FoodFactory;

public class Foods extends csvModel {
    ArrayList<Object> foods;

    public ArrayList<Object> getData() {
        return foods;
    }

    public void setData(ArrayList<Object> data) {
        this.foods = data;
    }

    private FoodFactory foodFactory = new FoodFactory();

    public Foods(FileHandler fh) {
        super(fh);
        foods = new ArrayList<>();
    }

    @Override
    public ArrayList<Object> read(String filepath) throws IOException {
        foods = new ArrayList<>();
        BufferedReader br = new BufferedReader(fh.getReader(filepath));
        String line;
        while ((line = br.readLine()) != null) {

            Food food = foodFactory.createFood(line);
            foods.add(food);
        }
        br.close();
        return foods;
    }

    @Override
    public void write(String filepath, Object item) throws IOException {
        BufferedWriter bw = new BufferedWriter(fh.getWriter(filepath));
        bw.newLine();
        Food food = (Food) item;
        bw.write(food.formatToCSV());
        bw.flush();
        bw.close();
    }

    public void update() throws IOException {

    }

    @Override
    public void update(int index, Object item) throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(Object item) throws IOException {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

}
