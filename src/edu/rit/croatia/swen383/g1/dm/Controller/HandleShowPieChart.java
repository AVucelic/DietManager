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
                    Map<String, Double> nutrientMap = parseFoodInfo(selectedItem);
                    if (!nutrientMap.isEmpty()) {
                        String foodName = parseFoodName(selectedItem);
                        System.out.println("Displaying pie chart for: " + foodName);
                        showFoodNutrientsPieChart(foodName, nutrientMap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}
