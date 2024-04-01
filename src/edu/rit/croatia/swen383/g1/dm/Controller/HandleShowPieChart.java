package Controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import Model.Food;
import View.View;

import java.util.Map;

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
                    String foodName = selectedItem.split(":")[1].trim();
                    System.out.println("Displaying pie chart for: " + foodName);
                    showFoodNutrientsPieChart(foodName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showFoodNutrientsPieChart(String foodName) {
        Map<String, Double> dietaryInfo = controller.getDietaryInformation(foodName);

        PieChart pieChart = new PieChart();

        dietaryInfo.forEach((nutrient, value) -> {
            PieChart.Data slice = new PieChart.Data(nutrient + ": " + value, value);
            pieChart.getData().add(slice);
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
