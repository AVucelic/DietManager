package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import Factory.FoodFactory;

public class Foods extends csvModel {
    ArrayList<Object> foods;
    private FoodFactory foodFactory = new FoodFactory();

    public Foods(FileHandler fh) {
        super(fh);
    }

    @Override
    public ArrayList<Object> read(String filepath) throws IOException {
        foods = new ArrayList<>();
        BufferedReader br = new BufferedReader(fh.getReader(filepath));
        String line;
        while ((line = br.readLine()) != null) {

            Food food = foodFactory.createFood(line);
            foods.add(food);

            // String[] attributes = line.split(",");
            // if (attributes[0].equals("b")) {
            // BasicFood food = new BasicFood(attributes[0], attributes[1],
            // Double.parseDouble(attributes[2]),
            // Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]),
            // Double.parseDouble(attributes[5]));
            // foods.add(food);
            // } else if (attributes[0].equals("r")) {
            // Recipe recipe = Recipe.parseRecipe(line);
            // foods.add(recipe);
            // }
        }
        br.close();
        return foods;
    }

    @Override
    public void write(String filepath, Object item) throws IOException {
        BufferedWriter bw = new BufferedWriter(fh.getWriter(filepath));
        bw.newLine();
        Food food = (Food) item;
        bw.write(food.toString());
        // if (item instanceof BasicFood) {
        //     String line = food.getType() + "," + food.getName() + "," + food.getCalories() + "," + food.getFat() + ","
        //             + food.getCarbs() + "," + food.getProtein();
        //     bw.write(line);
        // } else if (item instanceof Recipe) {
        //     Recipe recipe = (Recipe) item;
        //     StringBuilder sb = new StringBuilder();
        //     sb.append("r,").append(recipe.getName());
        //     ArrayList<String> ingredientNames = recipe.getIngredientNames();
        //     ArrayList<Double> ingredientCounts = recipe.getIngredientCounts();
        //     for (int i = 0; i < ingredientNames.size(); i++) {
        //         sb.append(",").append(ingredientNames.get(i)).append(",").append(ingredientCounts.get(i));
        //     }
        //     bw.write(sb.toString());
        // }
        bw.flush();
        bw.close();
    }

    public void update() throws IOException {

    }

    @Override
    public void update(int index, Object item) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void remove(int index) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

}
