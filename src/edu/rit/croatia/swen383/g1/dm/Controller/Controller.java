package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.BasicFood;
import Model.Food;
import Model.Log;
import Model.Recipe;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class Controller implements EventHandler<ActionEvent> {

    private View view;
    private csvModel foodModel;
    private csvModel logsModel;

    public Controller(View view, csvModel model, csvModel logsModel) {
        this.view = view;
        this.foodModel = model;
        this.logsModel = logsModel;
    }

    public void loadData() {
        try {
            ArrayList<Object> foodList = this.foodModel
                    .read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\foods.csv");
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

            ArrayList<Object> logList = this.logsModel.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv");

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
            ArrayList<Object> list = this.foodModel.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\foods.csv");
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
                    .read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\foods.csv");
            Map<String, Food> foodMap = new HashMap<>();

            for (Object object : foodList) {
                if (object instanceof Food) {
                    Food food = (Food) object;
                    foodMap.put(food.getName(), food);
                }
            }

            ArrayList<Object> logList = logsModel.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv");
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

    public int calculateTotalCaloriesForDate(LocalDate selectedDate) {
        int totalCalories = 0;
        try {
            ArrayList<Object> logList = logsModel.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv");
            for (Object object : logList) {
                if (object instanceof Log) {
                    Log log = (Log) object;
                    LocalDate logDate = LocalDate.parse(log.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    if (logDate.equals(selectedDate)) {
                        for (Object foodObject : foodModel.getData()) {
                            if (foodObject instanceof Food) {
                                Food food = (Food) foodObject;
                                if (food.getName().equalsIgnoreCase(log.getFoodName())) {
                                    if (food instanceof BasicFood) {
                                        totalCalories += ((BasicFood) food).getCalories() * log.getServings();
                                    } else if (food instanceof Recipe) {
                                        Recipe recipe = (Recipe) food;
                                        totalCalories += calculateRecipeCalories(recipe, log.getServings());
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalCalories;
    }

    private int calculateRecipeCalories(Recipe recipe, double servings) {
        int recipeCalories = 0;
        for (Food ingredient : recipe.getIngredients()) {
            if (ingredient instanceof BasicFood) {
                BasicFood basicFood = (BasicFood) ingredient;
                recipeCalories += basicFood.getCalories() * servings;
            }
        }
        return recipeCalories;
    }

    public int calculateTotalCarbsForDate(LocalDate selectedDate) {
        int totalCarbs = 0;
        try {
            ArrayList<Object> logList = logsModel.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv");
            for (Object object : logList) {
                if (object instanceof Log) {
                    Log log = (Log) object;
                    LocalDate logDate = LocalDate.parse(log.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    if (logDate.equals(selectedDate)) {
                        for (Object foodObject : foodModel.getData()) {
                            if (foodObject instanceof Food) {
                                Food food = (Food) foodObject;
                                if (food.getName().equalsIgnoreCase(log.getFoodName())) {
                                    if (food instanceof BasicFood) {
                                        totalCarbs += ((BasicFood) food).getCarbs() * log.getServings();
                                    } else if (food instanceof Recipe) {
                                        //TODO fix this for the recipe
                                        Recipe recipe = (Recipe) food;
                                        totalCarbs += calculateRecipeCarbs(recipe, log.getServings());
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalCarbs;
    }

    //TODO fix this for the recipe
    private int calculateRecipeCarbs(Recipe recipe, double servings) {
        int recipeCarbs = 0;
        for (Food ingredient : recipe.getIngredients()) {
            if (ingredient instanceof BasicFood) {
                BasicFood basicFood = (BasicFood) ingredient;
                recipeCarbs += basicFood.getCarbs() * servings;
            }
        }
        return recipeCarbs;
    }

    public int calculateTotalFatsForDate(LocalDate selectedDate) {
        int totalFats = 0;
        try {
            ArrayList<Object> logList = logsModel.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv");
            for (Object object : logList) {
                if (object instanceof Log) {
                    Log log = (Log) object;
                    LocalDate logDate = LocalDate.parse(log.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    if (logDate.equals(selectedDate)) {
                        for (Object foodObject : foodModel.getData()) {
                            if (foodObject instanceof Food) {
                                Food food = (Food) foodObject;
                                if (food.getName().equalsIgnoreCase(log.getFoodName())) {
                                    if (food instanceof BasicFood) {
                                        totalFats += ((BasicFood) food).getFat() * log.getServings();
                                    } else if (food instanceof Recipe) {
                                        //TODO fix this for the recipe
                                        Recipe recipe = (Recipe) food;
                                        totalFats += calculateRecipeFats(recipe, log.getServings());
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalFats;
    }

    //TODO fix this for the recipe
    private int calculateRecipeFats(Recipe recipe, double servings) {
        int recipeFats = 0;
        for (Food ingredient : recipe.getIngredients()) {
            if (ingredient instanceof BasicFood) {
                BasicFood basicFood = (BasicFood) ingredient;
                recipeFats += basicFood.getFat() * servings;
            }
        }
        return recipeFats;
    }

    public int calculateTotalProteinForDate(LocalDate selectedDate) {
        int totalProtein = 0;
        try {
            ArrayList<Object> logList = logsModel.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv");
            for (Object object : logList) {
                if (object instanceof Log) {
                    Log log = (Log) object;
                    LocalDate logDate = LocalDate.parse(log.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    if (logDate.equals(selectedDate)) {
                        for (Object foodObject : foodModel.getData()) {
                            if (foodObject instanceof Food) {
                                Food food = (Food) foodObject;
                                if (food.getName().equalsIgnoreCase(log.getFoodName())) {
                                    if (food instanceof BasicFood) {
                                        totalProtein += ((BasicFood) food).getProtein() * log.getServings();
                                    } else if (food instanceof Recipe) {
                                        //TODO fix this for the recipe
                                        Recipe recipe = (Recipe) food;
                                        totalProtein += calculateRecipeProtein(recipe, log.getServings());
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalProtein;
    }

    //TODO fix this for the recipe
    private int calculateRecipeProtein(Recipe recipe, double servings) {
        int recipeProtein = 0;
        for (Food ingredient : recipe.getIngredients()) {
            if (ingredient instanceof BasicFood) {
                BasicFood basicFood = (BasicFood) ingredient;
                recipeProtein += basicFood.getProtein() * servings;
            }
        }
        return recipeProtein;
    }




    public Map<String, Double> getDietaryInformation(String foodName) {
        Map<String, Double> dietaryInfo = new HashMap<>();
        try {
            ArrayList<Object> foodList = foodModel.getData();

            for (Object obj : foodList) {
                Food food = (Food) obj;
                if (food.getName().equalsIgnoreCase(foodName)) {
                    if (food instanceof BasicFood) {
                        BasicFood basicFood = (BasicFood) food;
                        dietaryInfo.put("Calories", basicFood.getCalories());
                        dietaryInfo.put("Fat", basicFood.getFat());
                        dietaryInfo.put("Carbs", basicFood.getCarbs());
                        dietaryInfo.put("Protein", basicFood.getProtein());
                    } else if (food instanceof Recipe) {
                        Recipe recipe = (Recipe) food;
                        double totalCalories = recipe.calculateTotalCalories();
                        dietaryInfo.put("Calories", totalCalories);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dietaryInfo;
    }

}