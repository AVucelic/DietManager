package Controller;

import java.io.IOException;
import java.util.ArrayList;
import Model.Exercise;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class HandleAddExercise implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;

    public HandleAddExercise(View view, csvModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        String nameOfExercise = this.view.getNameField().getText().trim();
        String caloriesOfExercise = this.view.getCaloriesField().getText().trim();
        double caloriesValue;
        if (nameOfExercise.isEmpty() || caloriesOfExercise.isEmpty()) {
            view.showAlert(Alert.AlertType.WARNING, "Missing Information",
                    "Please fill all the input fields",
                    "Fill all data including exercise name, and calories.");
            return;
        }

        try {
            caloriesValue = Double.parseDouble(caloriesOfExercise);
        } catch (NumberFormatException e) {
            view.showAlert(Alert.AlertType.ERROR, "Invalid Input",
                    "Invalid data for numbers",
                    "Please enter number value");
            return;
        }
        Exercise exercise = new Exercise(this.view.getTypeField().getText(), nameOfExercise, caloriesValue);
        if (!exerciseExists(exercise)) {

            this.view.getExerciseView().getItems().add(exercise.toString());
            try {
                this.model.write("Vendor\\exercise.csv", exercise);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            view.showAlert(Alert.AlertType.ERROR, "Unique Exercise",
                    "Please each exercise should have unique name",
                    "This exercise already exists");
        }
    }

    private boolean exerciseExists(Exercise exercise) {
        ArrayList<Object> exerciseList;
        try {
            exerciseList = this.model
                    .read("Vendor\\exercise.csv");

            for (Object obj : exerciseList) {
                if (obj instanceof Exercise) {
                    Exercise existingExercise = (Exercise) obj;
                    if (existingExercise.getName().equalsIgnoreCase(exercise.getName())) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
