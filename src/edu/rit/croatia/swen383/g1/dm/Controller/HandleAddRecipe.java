package Controller;

import java.io.IOException;
import java.util.ArrayList;
import Model.Food;
import Model.Recipe;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class HandleAddRecipe implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;
    private ArrayList<Object> foodList;

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

        String countText = this.view.getCount1().getText();
        String count2Text = this.view.getCount2().getText();
        String count3Text = this.view.getCount3().getText();
        Double count, count2, count3;

        if (name.isEmpty() || foodName == null || countText.isEmpty() ||
                foodName2 == null || count2Text.isEmpty() ||
                foodName3 == null || count3Text.isEmpty()) {
            view.showAlert(Alert.AlertType.WARNING, "Empty fields",
                    "Please fill all fields",
                    "Fill all data, including recipe name, ingredients, and their specific quantities.");
            return;
        }

        try {
            count = Double.parseDouble(countText);
            count2 = Double.parseDouble(count2Text);
            count3 = Double.parseDouble(count3Text);
        } catch (NumberFormatException e) {
            view.showAlert(Alert.AlertType.ERROR, "Invalid Input",
                    "Invalid data for numbers",
                    "Please enter number value");
            return;
        }

        try {
            foodList = this.model
                    .read("Vendor\\foods.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (foodList != null) {

            Food recipe = new Recipe("r", name);

            for (Object object : foodList) {
                Food food = (Food) object;
                if (food.getName().equals(foodName)) {
                    recipe.addFood(food, count);
                } else if (food.getName().equals(foodName2)) {
                    recipe.addFood(food, count2);
                } else if (food.getName().equals(foodName3)) {
                    recipe.addFood(food, count3);
                }
            }

            if (!recipeExists(recipe)) {
                this.view.getFoodView().getItems().add(recipe.objToString());
                String emptyLine = "";
                this.view.getFoodView().getItems().add(emptyLine);

                try {
                    this.model.write("Vendor\\foods.csv", recipe);
                    view.showAlert(Alert.AlertType.CONFIRMATION, "Success",
                            null, "Recipe added successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                view.showAlert(Alert.AlertType.ERROR, "Error",
                        null, "This recipe already exists.");
            }
        }

    }

    private boolean recipeExists(Food recipe) {
        for (Object obj : foodList) {
            if (obj instanceof Recipe) {
                Recipe existingRecipe = (Recipe) obj;
                if (existingRecipe.getName().equals(recipe.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
