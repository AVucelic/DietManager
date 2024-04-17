package Controller;

import java.io.IOException;
import java.time.LocalDate;

import Model.Weight;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class HandleAddWeight implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;

    public HandleAddWeight(View view, csvModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        LocalDate currentDate = LocalDate.now();
        double weight;
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int day = currentDate.getDayOfMonth();

        String weightText = this.view.getWeight().getText().trim();

        if (weightText.isEmpty()) {
            view.showAlert(Alert.AlertType.WARNING, "Missing Information",
                    "Please fill all the input fields",
                    "Fill all data including exercise name, and calories.");
            return;
        }

        try {
            weight = Double.parseDouble(weightText);
        } catch (NumberFormatException e) {
            view.showAlert(Alert.AlertType.ERROR, "Invalid Input",
                    "Weight must be a valid number",
                    "Please enter a valid number for the Weight.");
            return;
        }

        Weight newWeight = new Weight(year, month, day, weight);
        try {
            this.model.write("Vendor\\log.csv", newWeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.view.getLogsView().getItems().addAll(newWeight.toString());
    }
}
