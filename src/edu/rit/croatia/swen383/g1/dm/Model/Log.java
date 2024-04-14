package Model;

import java.util.Map;

public class Log {
    private String date;
    private char recordType;
    private String foodName;
    private Double servings;
    private Double weight;
    private Double calorieLimit;
    private Map<String, Double> ingredients;
    private double calories;
    private double personWeight;

    public double getPersonWeight() {
        return personWeight;
    }

    public void setPersonWeight(double personWeight) {
        this.personWeight = personWeight;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public Log(String date, char recordType, String foodName, Double servings, Map<String, Double> ingredients) {
        this.date = date;
        this.recordType = recordType;
        this.foodName = foodName;
        this.servings = servings;
        this.ingredients = ingredients;
    }

    public Log(String date, char recordType, String foodName, Double servings) {
        this.date = date;
        this.recordType = recordType;
        this.foodName = foodName;
        this.servings = servings;
    }

    public Log(String date, char recordType, Double weight) {
        this.date = date;
        this.recordType = recordType;

        if (recordType == 'w') {
            this.weight = weight;
        } else if (recordType == 'c') {
            this.calorieLimit = weight;
        }
    }

    public Log() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public char getRecordType() {
        return recordType;
    }

    public void setRecordType(char recordType) {
        this.recordType = recordType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getServings() {
        return servings;
    }

    public void setServings(Double servings) {
        this.servings = servings;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public double getCalorieLimit() {
        return calorieLimit;
    }

    public void setCalorieLimit(Double calorieLimit) {
        this.calorieLimit = calorieLimit;
    }

    public String toString() {
        if (recordType == 'w') {
            return date + " - Weight" + ": " + weight + " kg";
        } else if (recordType == 'c') {
            return date + " - Calorie limit" + ": " + calorieLimit + " kcal";
        } else if (recordType == 'r') {
            return date + " - Recipe: " + foodName + ", " + servings + " servings";
        } else if (recordType == 'e') {
            return date + " - Exercise: " + foodName + ", " + servings + " minutes";
        } else {
            return date + " - Food: " + foodName + ", " + servings + " servings";
        }

    }

    public Map<String, Double> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Double> ingredients) {
        this.ingredients = ingredients;
    }

    public int burningEquation(double calories) {
        int burningEquation = (int) (calories * getPersonWeight() * (getServings() / 60) + 0.5);
        return burningEquation;
    }

}
