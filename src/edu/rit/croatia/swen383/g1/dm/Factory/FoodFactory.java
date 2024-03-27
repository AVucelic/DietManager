package Factory;

import Model.BasicFood;
import Model.Food;
import Model.Recipe;

public class FoodFactory {
    public Food createFood(String line) {
        String[] attributes = line.split(",");
        switch (attributes[0]) {
            case "b":
                Food food = new BasicFood(attributes[0], attributes[1], Double.parseDouble(attributes[2]),
                        Double.parseDouble(attributes[3]), Double.parseDouble(attributes[4]),
                        Double.parseDouble(attributes[5]));
                return food;
            case "r":
                Food recipe = Recipe.parseRecipe(line);
                return recipe;
            default:
                return null;
        }
    }
}
