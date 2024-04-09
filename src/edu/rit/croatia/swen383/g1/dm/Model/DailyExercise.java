package Model;

public class DailyExercise {
    private String type;
    private String name;
    private double calories;

    public DailyExercise(String type, String name, double calories) {
        this.type = type;
        this.name = name;
        this.calories = calories;
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

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public String toString() {
        String exerciseInfo = "Exercise: " + this.getName() +
                ", Calories: " + this.getCalories();
        return exerciseInfo;
    }

    public String formatToCSV() {
        String csv = this.getType() + "," + this.getName() + "," + this.getCalories();
        return csv;
    }

    public int burningEquation(double weight, double timeInMinutes) {
        int burningEquation = (int) (this.getCalories() * weight * (timeInMinutes / 60) + 0.5);
        return burningEquation;
    }

}
