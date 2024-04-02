package Controller;

import javafx.event.EventHandler;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import View.View;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleShowPieChart implements EventHandler<MouseEvent> {
    private View view;
    private Controller controller;

    public HandleShowPieChart(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void handle(MouseEvent event) {
        try {
            if (event.getClickCount() == 2) {
                String selectedItem = view.getFoodView().getSelectionModel().getSelectedItem();
                if (selectedItem != null && !selectedItem.isEmpty()) {
                    if (selectedItem.startsWith("Recipe:")) {
                        handleRecipe(selectedItem);
                    } else {
                        handleBasicFood(selectedItem);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleBasicFood(String selectedItem) {
        Map<String, Double> nutrientMap = parseFoodInfo(selectedItem);
        if (!nutrientMap.isEmpty()) {
            String foodName = parseFoodName(selectedItem);
            showFoodNutrientsPieChart(foodName, nutrientMap);
        }
    }

    private void handleRecipe(String selectedItem) {
        Map<String, Double> ingredientMap = parseRecipeInfo(selectedItem);
        if (!ingredientMap.isEmpty()) {
            String recipeName = parseRecipeName(selectedItem);
            showRecipeIngredientsPieChart(recipeName, ingredientMap);
        }
    }

    private Map<String, Double> parseFoodInfo(String foodInfo) {
        Map<String, Double> nutrientMap = new HashMap<>();

        Pattern pattern = Pattern.compile("(\\w+):\\s*([\\d.]+)");
        Matcher matcher = pattern.matcher(foodInfo);

        while (matcher.find()) {
            String key = matcher.group(1);
            double value = Double.parseDouble(matcher.group(2));
            nutrientMap.put(key, value);
        }

        return nutrientMap;
    }

    private String parseFoodName(String foodInfo) {
        String[] parts = foodInfo.split(",");
        String firstPart = parts[0].trim();
        String[] keyValue = firstPart.split(":");
        if (keyValue.length == 2) {
            return keyValue[1].trim();
        } else {
            return "";
        }
    }

    private Map<String, Double> parseRecipeInfo(String recipeInfo) {
        Map<String, Double> ingredientMap = new HashMap<>();

        // Regular expression to match ingredient lines like "Ingredient: Pizza Slice,
        // Count: 1.0"
        Pattern pattern = Pattern.compile("Ingredient:\\s*(.+?),\\s*Count:\\s*([\\d.]+)");
        Matcher matcher = pattern.matcher(recipeInfo);

        // Iterate through matches
        while (matcher.find()) {
            String ingredientName = matcher.group(1);
            double count = Double.parseDouble(matcher.group(2));
            ingredientMap.put(ingredientName, count);
        }

        return ingredientMap;
    }

    private String parseRecipeName(String recipeInfo) {
        String[] lines = recipeInfo.split("\n");
        if (lines.length > 0) {
            String firstLine = lines[0].trim();
            String[] keyValue = firstLine.split(":");
            if (keyValue.length == 2) {
                return keyValue[1].trim();
            }
        }
        return "";
    }

    private void showFoodNutrientsPieChart(String foodName, Map<String, Double> nutrientMap) {
        PieChart pieChart = new PieChart();

        nutrientMap.forEach((nutrient, value) -> {
            if (!nutrient.equals("Food")) {
                PieChart.Data slice = new PieChart.Data(nutrient + ": " + value, value);
                pieChart.getData().add(slice);
            }
        });

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(foodName + " Nutritional Information");

        VBox vbox = new VBox(pieChart);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void showRecipeIngredientsPieChart(String recipeName, Map<String, Double> ingredientMap) {
        PieChart pieChart = new PieChart();

        ingredientMap.forEach((ingredient, count) -> {
            PieChart.Data slice = new PieChart.Data(ingredient + ": " + count, count);
            pieChart.getData().add(slice);
        });

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(recipeName + " Ingredients");

        VBox vbox = new VBox(pieChart);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);
        stage.show();
    }
}
