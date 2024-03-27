package Controller;

import java.io.IOException;

import Model.BasicFood;
import Model.Food;
import Model.Recipe;
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
            Food food1 = new BasicFood(this.view.getTypeField().getText(), this.view.getNameField().getText(),
                    Double.parseDouble(this.view.getCaloriesField().getText()),
                    Double.parseDouble(this.view.getFatField().getText()),
                    Double.parseDouble(this.view.getCarbsField().getText()),
                    Double.parseDouble(this.view.getProteinField().getText()));

            String foodInfo = food1.toString();

            this.view.getFoodView().getItems().add(foodInfo);

            String emptyLine = "";
            this.view.getFoodView().getItems().add(emptyLine);

            this.view.getFoods().getData().add(food1);

            try {
                this.model.write("src/edu/rit/croatia/swen383/g1/dm/Vendor/foods.csv", food1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // else if (this.view.getTypeField().getText().equals("r")) {
        // Recipe recipe = new Recipe("r", this.view.getNameField().getText());
        // recipe.getIngredientNames().add(this.view.getCaloriesField().getText());
        // recipe.getIngredientCounts().add(Double.parseDouble(this.view.getFatField().getText()));
        // recipe.getIngredientNames().add(this.view.getCarbsField().getText());
        // recipe.getIngredientCounts().add(Double.parseDouble(this.view.getProteinField().getText()));

        // this.view.getFoodView().getItems().add(recipe.toString());
        // String emptyLine = "";
        // this.view.getFoodView().getItems().add(emptyLine);

        // try {
        // this.model.write("src/edu/rit/croatia/swen383/g1/dm/Vendor/foods.csv",
        // recipe);
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // }

    }

}
