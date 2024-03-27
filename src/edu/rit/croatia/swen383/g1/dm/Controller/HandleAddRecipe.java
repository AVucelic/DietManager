package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.Food;
import Model.Recipe;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HandleAddRecipe implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;

    public HandleAddRecipe(View view, csvModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        String name = this.view.getNameField().getText();
        final String foodName = this.view.getIngredientComboBox().getSelectionModel().getSelectedItem();
        final String foodName2 = this.view.getIngredientComboBox2().getSelectionModel().getSelectedItem();
        final String foodName3 = this.view.getIngredientComboBox3().getSelectionModel().getSelectedItem();
        Double count = Double.parseDouble(this.view.getCount1().getText());
        Double count2 = Double.parseDouble(this.view.getCount1().getText());
        Double count3 = Double.parseDouble(this.view.getCount1().getText());

        ArrayList<Object> list = this.view.getFoods().getData();
        // System.out.println(list);
        if (list != null) {

            Food recipe = new Recipe("r", name);

            for (Object object : list) {
                Food food = (Food) object;
                if (food.getName().equals(foodName)) {
                    recipe.addFood(food, count);
                } else if (food.getName().equals(foodName2)) {
                    recipe.addFood(food, count2);
                } else if (food.getName().equals(foodName3)) {
                    recipe.addFood(food, count3);
                }
            }

            this.view.getFoodView().getItems().add(recipe.objToString());
            String emptyLine = "";
            this.view.getFoodView().getItems().add(emptyLine);

            try {
                this.model.write("src/edu/rit/croatia/swen383/g1/dm/Vendor/foods.csv",
                        recipe);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
