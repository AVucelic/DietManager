package Model;

public abstract class Food {
    public abstract String toString();

    public abstract String formatToCSV();

    public abstract String getName();

    public abstract void addFood(Food s, double count);

}