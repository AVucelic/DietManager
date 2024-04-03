package Model;

import java.util.ArrayList;

public abstract class Food {
    public abstract String toString();

    public abstract String formatToCSV();

    public abstract String getName();

    public abstract void addFood(Food s, double count);

    public abstract String objToString();

    public abstract ArrayList<Double> getIngredientCounts();

    public abstract ArrayList<String> getIngredientNames();

    public abstract double getFat();

    public abstract double getProtein();

    public abstract double getCarbs();

    public abstract double getCalories();

}