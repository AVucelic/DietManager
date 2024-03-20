package Model;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<String> ingredientNames;
    private ArrayList<Double> ingredientCounts;

    public Recipe(String name) {
        this.name = name;
        this.ingredientNames = new ArrayList<>();
        this.ingredientCounts = new ArrayList<>();
    }

    public void addIngredient(String ingredientName, double count) {
        ingredientNames.add(ingredientName);
        ingredientCounts.add(count);
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getIngredientNames() {
        return ingredientNames;
    }

    public ArrayList<Double> getIngredientCounts() {
        return ingredientCounts;
    }

    public static Recipe parseRecipe(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length < 3 || !parts[0].equals("r")) {
            throw new IllegalArgumentException("Invalid CSV line for recipe: " + csvLine);
        }

        Recipe recipe = new Recipe(parts[1]);
        for (int i = 2; i < parts.length; i += 2) {
            String ingredientName = parts[i];
            double count = Double.parseDouble(parts[i + 1]);
            recipe.addIngredient(ingredientName, count);
        }

        return recipe;
    }
}