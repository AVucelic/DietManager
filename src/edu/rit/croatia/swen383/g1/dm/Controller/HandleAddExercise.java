package Controller;
import java.io.IOException;
import java.util.ArrayList;
import Model.DailyExercise;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HandleAddExercise implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;

    public HandleAddExercise(View view, csvModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        DailyExercise exercise = new DailyExercise(this.view.getTypeField().getText(), this.view.getNameField().getText(),
                Double.parseDouble(this.view.getCaloriesField().getText()));
        if (!exerciseExists(exercise)) {

            this.view.getExerciseView().getItems().add(exercise.toString());


            try {
                this.model.write("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\exercise.csv", exercise);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("This exercise already exists.");
            alert.showAndWait();
        }
    }

    private boolean exerciseExists(DailyExercise exercise) {
        ArrayList<Object> exerciseList;
        try {
            exerciseList = this.model
                    .read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\exercise.csv");

            for (Object obj : exerciseList) {
                if (obj instanceof DailyExercise) {
                    DailyExercise existingExercise = (DailyExercise) obj;
                    if (existingExercise.equals(exercise)) {
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
