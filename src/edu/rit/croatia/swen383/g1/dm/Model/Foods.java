package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class Foods extends csvModel {
    ArrayList<Object> foods;

    public Foods(FileHandler fh) {
        super(fh);
    }

    @Override
    public ArrayList<Object> read(String filepath) throws IOException {
        foods = new ArrayList<>();
        BufferedReader br = new BufferedReader(fh.getReader(filepath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] attributes = line.split(",");
            if (attributes[0] == "b") {

                Food food = new Food(attributes[0], attributes[1], Double.parseDouble(attributes[2]),
                        Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]),
                        Double.parseDouble(attributes[5]));
                foods.add(food);
            }

        }
        br.close();
        return foods;
    }

    @Override
    public void write(String filepath, Food food) throws IOException {
        BufferedWriter bw = new BufferedWriter(fh.getWriter(filepath));
        String line = food.getType() + "," + food.getName() + "," + food.getCalories() + "," + food.getFat() + ","
                + food.getCarbs() + "," + food.getProtein();
        bw.write(line);
        bw.flush();
        bw.close();
    }

    public void update() throws IOException {

    }

}
