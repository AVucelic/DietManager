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
import javafx.scene.control.Alert.AlertType;

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

            if (!recipeExists(recipe)) {
                this.view.getFoodView().getItems().add(recipe.objToString());
                String emptyLine = "";
                this.view.getFoodView().getItems().add(emptyLine);

                try {
                    this.model.write("src/edu/rit/croatia/swen383/g1/dm/Vendor/foods.csv", recipe);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("This recipe already exists.");
                alert.showAndWait();
            }
        }

    }

    private boolean recipeExists(Food recipe) {
        ArrayList<Object> dataList = (ArrayList<Object>) this.view.getFoods().getData();

        for (Object obj : dataList) {
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
