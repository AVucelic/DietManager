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
            return "Weight on " + date + ": " + weight + " kg";
        } else if (recordType == 'c') {
            return "Calorie limit on " + date + ": " + calorieLimit + " kcal";
        } else if (recordType == 'r') {
            return date + " - Recipe: " + foodName + ", " + servings + " servings";
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

}
