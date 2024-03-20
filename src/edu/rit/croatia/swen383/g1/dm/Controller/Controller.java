package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.Food;
import Model.Foods;
import Model.Recipe;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller implements EventHandler<ActionEvent> {

    private View view;
    private csvModel model;

    public Controller(View view, Foods model) {
        this.view = view;
        this.model = model;

    }

    // public Controller(View view, Logs model) {
    // this.view = view;
    // this.model = model;
    // }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == view.getLoadData()) {
            try {
                ArrayList<Object> list = this.model.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\foods.csv");
                for (Object object : list) {
                    if (object instanceof Food) {
                        Food food = (Food) object;
                        String foodInfo = "Food: " + food.getName() +
                                ", Calories: " + food.getCalories() +
                                ", Fat: " + food.getFat() +
                                ", Carbs: " + food.getCarbs() +
                                ", Protein: " + food.getProtein();
                        this.view.getFoodView().getItems().add(foodInfo);
                        String emptyLine = "";
                        this.view.getFoodView().getItems().add(emptyLine);
                    } else if (object instanceof Recipe) {
                        Recipe recipe = (Recipe) object;
                        String recipeInfo = "Recipe: " + recipe.getName();
                        for (int i = 0; i < recipe.getIngredientNames().size(); i++) {
                            recipeInfo += "\n Ingredient: " + recipe.getIngredientNames().get(i) +
                                    ", Count: " + recipe.getIngredientCounts().get(i);
                        }
                        this.view.getFoodView().getItems().add(recipeInfo);
                        String emptyLine = "";
                        this.view.getFoodView().getItems().add(emptyLine);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}