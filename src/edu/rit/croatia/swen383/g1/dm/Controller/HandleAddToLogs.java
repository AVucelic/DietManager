package Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Model.Log;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HandleAddToLogs implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;
    private Controller controller;
    double minutes;
    double weight;
    String selectedExercise;
    Log exerciseLog = new Log();

    public HandleAddToLogs(View view, csvModel model, Controller controller) {
        this.view = view;
        this.model = model;
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {
        String selectedItem = view.getFoodView().getSelectionModel().getSelectedItem();
        selectedExercise = view.getExerciseView().getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            char recordType = 'f';
            if (selectedItem.startsWith("Recipe:")) {
                String[] recipeParts = selectedItem.split("\\n");
                String recipeName = recipeParts[0].substring("Recipe: ".length());

                StringBuilder logLine = new StringBuilder();
                logLine.append(getFormattedDate()).append(", ").append(recordType).append(", ").append(recipeName)
                        .append(", 1.0");

                Map<String, Double> ingredientsMap = new HashMap<>();

                for (int i = 1; i < recipeParts.length; i++) {
                    String ingredientLine = recipeParts[i].trim();
                    if (ingredientLine.startsWith("Ingredient:")) {
                        String[] ingredientParts = ingredientLine.split(", ");
                        String foodName = ingredientParts[0].substring("Ingredient: ".length()).trim();
                        double count = Double.parseDouble(ingredientParts[1].substring("Count: ".length()).trim());

                        ingredientsMap.put(foodName, count);
                    }
                }

                for (Map.Entry<String, Double> entry : ingredientsMap.entrySet()) {
                    logLine.append(", ").append(entry.getKey()).append(", ").append(entry.getValue());
                }

                Log log = new Log(getFormattedDate(), recordType, recipeName, 1.0, ingredientsMap);

                try {
                    model.write("Vendor\\log.csv", log);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                view.getExerciseView().getSelectionModel().clearSelection();
                view.getFoodView().getItems().clear();
                view.getLogsView().getItems().clear();

            } else {
                String[] itemParts = selectedItem.split(",");
                String foodName = itemParts[0].trim().split(":")[1].trim();
                Log log = new Log(getFormattedDate(), recordType, foodName, 1.0);
                try {
                    model.write("Vendor\\log.csv", log);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                view.getExerciseView().getSelectionModel().clearSelection();
                view.getFoodView().getItems().clear();
                view.getLogsView().getItems().clear();

            }
        }
        if (selectedExercise != null && selectedExercise.startsWith("Exercise: ")) {
            handleExerciseSelection();

        }
        controller.loadData();
    }

    private void handleExerciseSelection() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Enter Exercise Details");

        Label caloriesLabel = new Label("Minutes: ");
        TextField caloriesField = new TextField();

        Label dateLabel = new Label("Date: ");
        DatePicker datePicker = new DatePicker();

        Button applyButton = new Button("Apply");
        applyButton.setOnAction(event -> {
            minutes = Double.parseDouble(caloriesField.getText());
            LocalDate selectedDate = datePicker.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
            String formattedDate = selectedDate.format(formatter);
            String exerciseInfo = selectedExercise.substring("Exercise: ".length()).trim();
            String[] exerciseParts = exerciseInfo.split(", ");
            String exerciseName = exerciseParts[0];
            if (exerciseParts.length == 2) {
                String[] caloriesParts = exerciseParts[1].split(":");
                if (caloriesParts.length == 2) {
                    try {
                        double calories = Double.parseDouble(caloriesParts[1].trim());
                        exerciseLog = new Log(formattedDate, 'e', exerciseName, minutes);
                        exerciseLog.setCalories(calories);

                        try {
                            model.write("Vendor\\log.csv", exerciseLog);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing calories as double: " + e.getMessage());
                    }
                } else {
                    System.out.println("Unexpected format for calories: " + exerciseParts[1]);
                }
            }
            view.getExerciseView().getSelectionModel().clearSelection();
            view.getLogsView().getItems().clear();
            popupStage.close();
        });

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, caloriesLabel, caloriesField);
        gridPane.addRow(1, dateLabel, datePicker);
        gridPane.add(applyButton, 1, 2);

        Scene scene = new Scene(gridPane, 300, 150);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    private String getFormattedDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
