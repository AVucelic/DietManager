package Controller;

import java.io.IOException;
import java.util.ArrayList;
import Model.BasicFood;
import Model.Food;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HandleAddFood implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;

    public HandleAddFood(View view, csvModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        Food food1 = new BasicFood(this.view.getTypeField().getText(), this.view.getNameField().getText(),
                Double.parseDouble(this.view.getCaloriesField().getText()),
                Double.parseDouble(this.view.getFatField().getText()),
                Double.parseDouble(this.view.getCarbsField().getText()),
                Double.parseDouble(this.view.getProteinField().getText()));

        if (!foodExists(food1)) {
            String foodInfo = food1.toString();

            this.view.getFoodView().getItems().add(foodInfo);

            String emptyLine = "";
            this.view.getFoodView().getItems().add(emptyLine);

            try {
                this.model.write("Vendor\\foods.csv", food1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("This food already exists.");
            alert.showAndWait();
        }
    }

    private boolean foodExists(Food food) {
        ArrayList<Object> foodList;
        try {
            foodList = this.model
                    .read("Vendor\\foods.csv");

            for (Object obj : foodList) {
                if (obj instanceof Food) {
                    Food existingFood = (Food) obj;
                    if (existingFood.equals(food)) {
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
