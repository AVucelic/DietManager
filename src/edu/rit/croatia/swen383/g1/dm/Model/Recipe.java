package Model;

import java.util.ArrayList;

public class Recipe extends Food {
    private String name;
    private String type;
    private ArrayList<Food> ingredients;
    private ArrayList<String> ingredientNames;

    public ArrayList<String> getIngredientNames() {
        return ingredientNames;
    }

    private ArrayList<Double> ingredientCounts;

    public Recipe(String type, String name) {
        this.type = type;
        this.name = name;
        this.ingredients = new ArrayList<>();
        this.ingredientCounts = new ArrayList<>();
        this.ingredientNames = new ArrayList<>();
    }

    public void addIngredientValues(String ingredient, double count) {
        ingredientNames.add(ingredient);
        ingredientCounts.add(count);
    }

    public void addFood(Food food, double count) {
        ingredients.add(food);
        ingredientCounts.add(count);
    }

    public Food getFood(int index) {
        return ingredients.get(index);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Food> getIngredients() {
        return ingredients;
    }

    public ArrayList<Double> getIngredientCounts() {
        return ingredientCounts;
    }

    public static Recipe parseRecipe(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length < 3 || !parts[0].equals("r")) {
            throw new IllegalArgumentException("Invalid CSV line for recipe: " + csvLine);
        }

        Recipe recipe = new Recipe(parts[0], parts[1]);
        for (int i = 2; i < parts.length; i += 2) {
            String ingredientName = parts[i];
            double count = Double.parseDouble(parts[i + 1]);
            recipe.addIngredientValues(ingredientName, count);
        }

        return recipe;
    }

    public String toString() {
        StringBuilder recipeInfo = new StringBuilder("Recipe: " + name);
        for (int i = 0; i < ingredientNames.size(); i++) {
            recipeInfo.append("\n Ingredient: ").append(ingredientNames.get(i))
                    .append(", Count: ").append(ingredientCounts.get(i));
        }
        return recipeInfo.toString();
    }

    public String objToString() {
        StringBuilder recipeInfo = new StringBuilder("Recipe: " + name);
        for (int i = 0; i < ingredients.size(); i++) {
            recipeInfo.append("\n Ingredient: ").append(ingredients.get(i).getName())
                    .append(", Count: ").append(ingredientCounts.get(i));
        }
        return recipeInfo.toString();

    }

    @Override
    public String formatToCSV() {
        StringBuilder sb = new StringBuilder();
        if (ingredientNames.size() > 0) {
            sb.append("r,").append(this.getName());
            for (int i = 0; i < ingredientNames.size(); i++) {
                sb.append(",").append(ingredientNames.get(i)).append(",").append(ingredientCounts.get(i));
            }
            return sb.toString();

        } else {
            sb.append("r,").append(this.getName());
            for (int i = 0; i < ingredients.size(); i++) {
                sb.append(",").append(ingredients.get(i).getName()).append(",").append(ingredientCounts.get(i));
            }
            return sb.toString();
        }
    }

    @Override
    public double getFat() {
        double totalFat = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            totalFat += ingredients.get(i).getFat() * ingredientCounts.get(i);
        }
        return totalFat;
    }

    @Override
    public double getProtein() {
        double total = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            total += ingredients.get(i).getProtein() * ingredientCounts.get(i);
        }
        return total;
    }

    @Override
    public double getCarbs() {
        double total = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            total += ingredients.get(i).getCarbs() * ingredientCounts.get(i);
        }
        return total;
    }

    @Override
    public double getCalories() {
        double total = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            total += ingredients.get(i).getCalories() * ingredientCounts.get(i);
        }
        return total;
    }

}