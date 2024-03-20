package Model;

import java.time.LocalDate;

public class Log {
    private LocalDate date;
    private char recordType;
    private String foodName;
    private Double servings;
    private Double weight;
    private Double calorieLimit;

    public Log(LocalDate date, char recordType, String foodName, Double servings, Double weight, Double calorieLimit) {
        this.date = date;
        this.recordType = recordType;
        this.foodName = foodName;
        this.servings = servings;
        this.weight = weight;
        this.calorieLimit = calorieLimit;

        if (recordType == 'f') {
            this.weight = null;
            this.calorieLimit = null;
        } else if (recordType == 'w') {
            this.foodName = null;
            this.servings = null;
            this.calorieLimit = null;
        } else if (recordType == 'c') {
            this.foodName = null;
            this.servings = null;
            this.weight = null;
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
}
