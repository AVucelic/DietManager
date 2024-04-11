package Model;

import java.time.LocalDate;

public class Weight {
    private int year;
    private int month;
    private int day;
    private double weight;

    public Weight(int year, int month, int day, double weight) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.weight = weight;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String toString() {
        LocalDate date = LocalDate.of(year, month, day);
        return date + " - Weight" + ": " + weight + " kg";

    }
}
