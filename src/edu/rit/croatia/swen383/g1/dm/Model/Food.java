package Model;

public class Food {
    private String type;
    private String name;
    private double calories;
    private double fat;
    private double carbs;
    private double protein;

    public Food(String type, String name, double calories, double fat, double carbs, double protein) {
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
}
