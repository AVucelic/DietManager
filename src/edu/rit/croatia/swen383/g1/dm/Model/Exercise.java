package Model;

public class Exercise {
    private String type;
    private String name;
    private double calories;
    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    private double minutes;

    public Exercise(String type, String name, double minutes) {
        this.type = type;
        this.name = name;
        this.minutes = minutes;
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
                ", Calories: " + this.getMinutes();
        return exerciseInfo;
    }

    public String formatToCSV() {
        String csv = this.getType() + "," + this.getName() + "," + this.getMinutes();
        return csv;
    }

    public int burningEquation(double weight, double timeInMinutes) {
        int burningEquation = (int) (this.getCalories() * weight * (timeInMinutes / 60) + 0.5);
        return burningEquation;
    }

}
