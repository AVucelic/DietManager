package Model;

import java.util.ArrayList;
import java.util.Objects;

public class BasicFood extends Food {
    private String type;
    private String name;
    private double calories;
    private double fat;
    private double carbs;
    private double protein;

    public BasicFood(String type, String name, double calories, double fat, double carbs, double protein) {
        this.type = type;
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public String toString() {
        String foodInfo = "Food: " + this.getName() +
                ", Calories: " + this.getCalories() +
                ", Fat: " + this.getFat() +
                ", Carbs: " + this.getCarbs() +
                ", Protein: " + this.getProtein();
        return foodInfo;
    }

    @Override
    public String formatToCSV() {
        String line = this.getType() + "," + this.getName() + "," + this.getCalories() + "," + this.getFat() + ","
                + this.getCarbs() + "," + this.getProtein();

        return line;
    }

    @Override
    public void addFood(Food s, double count) {
        throw new UnsupportedOperationException("Unimplemented method 'addFood'");
    }

    @Override
    public String objToString() {
        throw new UnsupportedOperationException("Unimplemented method 'objToString'");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BasicFood other = (BasicFood) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public ArrayList<Double> getIngredientCounts() {
        ArrayList<Double> arr = new ArrayList<>();
        arr.add(1.0);
        arr.add(1.0);
        arr.add(1.0);
        return arr;
    }

    @Override
    public ArrayList<String> getIngredientNames() {
        ArrayList<String> arr = new ArrayList<>();
        return arr;
    }

}
