package Model;

import java.time.LocalDate;

public class CalorieLimit {
    private int year;
    private int month;
    private int day;
    private double calories;

    public CalorieLimit(int year, int month, int day, double calories) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.calories = calories;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String toString() {
        LocalDate date = LocalDate.of(year, month, day);
        return date.toString() + " - Calorie limit" + ": " + calories + " kcal";
    }
}
