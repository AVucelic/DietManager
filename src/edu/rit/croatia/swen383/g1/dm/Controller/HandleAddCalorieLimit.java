package Controller;

import java.io.IOException;
import java.time.LocalDate;

import Model.CalorieLimit;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class HandleAddCalorieLimit implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;

    public HandleAddCalorieLimit(View view, csvModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        LocalDate currentDate = LocalDate.now();
        double calories;
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();

        String calorieLimitText = this.view.getCalorieLimit().getText().trim();

        if (calorieLimitText.isEmpty()) {
            view.showAlert(Alert.AlertType.WARNING, "Missing Information",
                    "Please fill all the input fields",
                    "Fill all data including exercise name, and calories.");
            return;
        }

        try {
            calories = Double.parseDouble(calorieLimitText);
        } catch (NumberFormatException e) {
            view.showAlert(Alert.AlertType.ERROR, "Invalid Input",
                    "Calorie Limit must be a valid number",
                    "Please enter a valid number for the Calorie Limit.");
            return;
        }

        CalorieLimit cLimit = new CalorieLimit(year, month, day, calories);
        try {
            this.model.write("Vendor\\log.csv", cLimit);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.view.getLogsView().getItems().addAll(cLimit.toString());
    }
}
