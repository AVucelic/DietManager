package Controller;

import java.io.IOException;

import Model.Food;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HandleAddFood implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;

    public HandleAddFood(View view, csvModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        if (this.view.getTypeField().getText().equals("b")) {
            Food food1 = new Food(this.view.getTypeField().getText(), this.view.getNameField().getText(),
                    Double.parseDouble(this.view.getCaloriesField().getText()),
                    Double.parseDouble(this.view.getFatField().getText()),
                    Double.parseDouble(this.view.getCarbsField().getText()),
                    Double.parseDouble(this.view.getProteinField().getText()));

            String foodInfo = "Food: " + food1.getName() +
                    ", Calories: " + food1.getCalories() +
                    ", Fat: " + food1.getFat() +
                    ", Carbs: " + food1.getCarbs() +
                    ", Protein: " + food1.getProtein();

            this.view.getFoodView().getItems().add(foodInfo);

            String emptyLine = "";
            this.view.getFoodView().getItems().add(emptyLine);

            try {
                this.model.write("src/edu/rit/croatia/swen383/g1/dm/Vendor/foods.csv", food1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
