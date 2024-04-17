package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.BasicFood;
import Model.CalorieLimits;
import Model.Exercise;
import Model.Exercises;
import Model.FileHandler;
import Model.Food;
import Model.Log;
import Model.Recipe;
import Model.csvModel;
import View.View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class Controller implements EventHandler<ActionEvent> {
    private boolean isUpdatingComboBoxes = false;
    private View view;
    private csvModel foodModel;
    private csvModel logsModel;
    private csvModel exerciseModel;
    private csvModel calorieLimits;
    private ArrayList<Object> foodList;
    double calorieLimit = 2000.0;

    public Controller(View view, csvModel model, csvModel logsModel, csvModel exerciseModel, csvModel calorieLimits,
            csvModel weights) {
        this.view = view;
        this.foodModel = model;
        this.logsModel = logsModel;
        this.exerciseModel = exerciseModel;
        this.calorieLimits = calorieLimits;
        Platform.runLater(() -> {
            HandleAddToLogs addToLogs = new HandleAddToLogs(view, logsModel, this);
            HandleShowPieChart showpChart = new HandleShowPieChart(view, this);
            HandleAddRecipe handleRecipe = new HandleAddRecipe(view, foodModel);
            HandleAddCalorieLimit handleCalorie = new HandleAddCalorieLimit(view, calorieLimits);
            HandleAddWeight handleWeight = new HandleAddWeight(view, weights);
            HandleRemoveLogs handleRemove = new HandleRemoveLogs(view, logsModel, this);

            EventHandler<ActionEvent> dateHandler = event -> {
                LocalDate date = view.getDp().getValue();
                handleDateSelection(date);
                int[] totals = calculateTotalNutrientForDate(date);
                double caloriesExpended = calculateTotalCaloriesExpended(date);
                double netCalories = totals[0] - caloriesExpended;

                view.getCaloriesTextField().setText("Calories consumed: " + totals[0]);
                view.getCarbsTextField().setText("Carbs consumed: " + totals[1]);
                view.getFatsTextField().setText("Fats consumed: " + totals[2]);
                view.getProteinsTextField().setText("Protein consumed: " + totals[3]);
                view.getCaloriesExpendedField().setText("Calories Expended: " + caloriesExpended);
                view.getNetCaloriesField().setText("Net Calories: " + netCalories);
                double calculation = calorieLimit - Math.abs(netCalories);
                view.getCalorieDifferenceField().setText("Difference from Calorie Goal: " + calculation);
            };
            checkComboBox();
            this.view.HandleAddToLogs(addToLogs);
            this.view.HandleShowPieChart(showpChart);
            this.view.HandleAddFood(new HandleAddFood(view, foodModel));
            this.view.HandleAddRecipe(handleRecipe);
            this.view.HandleAddExercise(new HandleAddExercise(view, exerciseModel));
            this.view.HandleAddCalorieLimit(new HandleAddCalorieLimit(view, calorieLimits));
            this.view.HandleAddWeight(new HandleAddWeight(view, weights));
            this.view.HandleRemoveLogs(handleRemove);
            this.loadBasicFoodsAndRecipes(this.view.getIngredientComboBox());
            this.loadBasicFoodsAndRecipes(this.view.getIngredientComboBox2());
            this.loadBasicFoodsAndRecipes(this.view.getIngredientComboBox3());
            this.view.handleDateSelection(dateHandler);
            this.loadData();

        });

    }

    private void checkComboBox() {
        EventHandler<ActionEvent> comboBoxListener = event -> {
            if (isUpdatingComboBoxes)
                return;

            isUpdatingComboBoxes = true;

            try {
                String selected1 = this.view.getIngredientComboBox().getSelectionModel().getSelectedItem();
                String selected2 = this.view.getIngredientComboBox2().getSelectionModel().getSelectedItem();
                String selected3 = this.view.getIngredientComboBox3().getSelectionModel().getSelectedItem();

                this.view.getIngredientComboBox().getItems().clear();
                this.view.getIngredientComboBox2().getItems().clear();
                this.view.getIngredientComboBox3().getItems().clear();

                this.loadBasicFoodsAndRecipes(this.view.getIngredientComboBox());
                this.loadBasicFoodsAndRecipes(this.view.getIngredientComboBox2());
                this.loadBasicFoodsAndRecipes(this.view.getIngredientComboBox3());

                if (selected1 != null) {
                    this.view.getIngredientComboBox2().getItems().remove(selected1);
                    this.view.getIngredientComboBox3().getItems().remove(selected1);
                }
                if (selected2 != null) {
                    this.view.getIngredientComboBox().getItems().remove(selected2);
                    this.view.getIngredientComboBox3().getItems().remove(selected2);
                }
                if (selected3 != null) {
                    this.view.getIngredientComboBox().getItems().remove(selected3);
                    this.view.getIngredientComboBox2().getItems().remove(selected3);
                }

                this.view.getIngredientComboBox().getSelectionModel().select(selected1);
                this.view.getIngredientComboBox2().getSelectionModel().select(selected2);
                this.view.getIngredientComboBox3().getSelectionModel().select(selected3);
            } finally {
                isUpdatingComboBoxes = false;
            }
        };

        this.view.HandleComboBoxListener(comboBoxListener);
    }

    public void loadData() {

        ArrayList<Object> arr;

        try {
            arr = exerciseModel.read("Vendor\\exercise.csv");
            for (Object object : arr) {
                Exercise dw = (Exercise) object;
                this.view.getExerciseView().getItems().add(dw.toString());

            }
            foodList = this.foodModel
                    .read("Vendor\\foods.csv");
            Map<String, Food> foodMap = new HashMap<>();

            for (Object object : foodList) {
                if (object instanceof Food) {
                    Food food = (Food) object;
                    foodMap.put(food.getName(), food);
                    this.view.getFoodView().getItems().add(food.toString());
                    String emptyLine = "";
                    this.view.getFoodView().getItems().add(emptyLine);
                }
            }

            ArrayList<Object> logList = this.logsModel.read("Vendor\\log.csv");

            for (Object log : logList) {
                if (log instanceof Log) {
                    Log logEntry = (Log) log;
                    String logString = logEntry.toString();

                    if (logEntry.getRecordType() != 'w' && logEntry.getRecordType() != 'c') {
                        Food relatedFood = foodMap.get(logEntry.getFoodName());
                        if (relatedFood != null) {
                            logString = logEntry.getDate() + " - " + relatedFood.toString();
                        }
                    }

                    this.view.getLogsView().getItems().add(logString);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBasicFoodsAndRecipes(ComboBox<String> comboBox) {
        try {
            ArrayList<Object> list = this.foodModel.read("Vendor\\foods.csv");
            for (Object object : list) {
                if (object instanceof BasicFood) {
                    BasicFood food = (BasicFood) object;
                    comboBox.getItems().add(food.getName());
                } else if (object instanceof Recipe) {
                    Recipe recipe = (Recipe) object;
                    comboBox.getItems().add(recipe.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDateSelection(LocalDate date) {
        view.getLogsView().getItems().clear();

        try {
            ArrayList<Object> foodList = this.foodModel
                    .read("Vendor\\foods.csv");
            Map<String, Food> foodMap = new HashMap<>();

            for (Object object : foodList) {
                if (object instanceof Food) {
                    Food food = (Food) object;
                    foodMap.put(food.getName(), food);
                }
            }

            ArrayList<Object> logList = logsModel.read("Vendor\\log.csv");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (Object log : logList) {
                if (log instanceof Log) {
                    Log logEntry = (Log) log;
                    String logString = logEntry.toString();

                    LocalDate logDate = LocalDate.parse(logEntry.getDate(), formatter);
                    if (date == null || logDate.equals(date)) {
                        if (logEntry.getRecordType() != 'w' && logEntry.getRecordType() != 'c') {
                            Food relatedFood = foodMap.get(logEntry.getFoodName());
                            if (relatedFood != null) {
                                logString = logEntry.getDate() + " - " + relatedFood.toString();
                            }
                        }
                        view.getLogsView().getItems().add(logString);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent event) {
        loadData();
    }

    public int[] calculateTotalNutrientForDate(LocalDate selectedDate) {
        // int totalNutrient = 0;
        int[] nutrients = new int[4];
        int totalCarbs = 0;
        int totalCalories = 0;
        int totalProtein = 0;
        int totalFat = 0;
        try {
            ArrayList<Object> logList = logsModel.read("Vendor\\log.csv");
            for (Object object : logList) {
                Log log = (Log) object;
                LocalDate logDate = LocalDate.parse(log.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (logDate.equals(selectedDate)) {
                    for (Object foodObject : foodList) {
                        Food food = (Food) foodObject;
                        if (food.getName().equalsIgnoreCase(log.getFoodName())) {
                            totalCarbs += food.getCarbs() * log.getServings();
                            totalCalories += food.getCalories() * log.getServings();
                            totalProtein += food.getProtein() * log.getServings();
                            totalFat += food.getFat() * log.getServings();
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        nutrients[0] = totalCalories;
        nutrients[1] = totalCarbs;
        nutrients[2] = totalFat;
        nutrients[3] = totalProtein;
        return nutrients;
    }

    public double calculateTotalCaloriesExpended(LocalDate selectedDate) {
        double totalCalories = 0;
        ArrayList<Object> logs = logsModel.getData();
        ArrayList<Object> exercises = exerciseModel.getData();
        double weight = 0;

        for (Object object : logs) {
            Log log = (Log) object;
            if (log.getRecordType() == 'w') {
                weight = log.getWeight();
            } else if (log.getRecordType() == 'c') {
                calorieLimit = log.getCalorieLimit();
            }
        }
        view.getCalorieGoalField().setText("Calories to burn: " + calorieLimit);
        for (Object object : exercises) {
            for (Object object2 : logs) {
                Log log = (Log) object2;
                Exercise de = (Exercise) object;
                if (String.valueOf(de.getType()).equals(String.valueOf(log.getRecordType()))
                        && de.getName().equals(log.getFoodName())
                        && log.getDate().equals(String.valueOf((LocalDate) this.view.getDp().getValue()))) {
                    log.setPersonWeight(weight);
                    totalCalories += log.burningEquation(de.getMinutes());
                }
            }
        }

        return totalCalories;
    }
}