package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.BasicFood;
import Model.Food;
import Model.Log;
import Model.Recipe;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class Controller implements EventHandler<ActionEvent> {

    private View view;
    private csvModel foodModel;
    private csvModel logsModel;

    public Controller(View view, csvModel model, csvModel logsModel) {
        this.view = view;
        this.foodModel = model;
        this.logsModel = logsModel;
    }

    public void loadData() {
        try {
            ArrayList<Object> list = this.foodModel
                    .read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\foods.csv");
            // System.out.println(list.get(0));
            this.foodModel.setData(list);
            System.out.println(foodModel.getData());
            for (Object object : list) {
                if (object instanceof BasicFood) {
                    Food food = (BasicFood) object;
                    this.view.getFoodView().getItems().add(food.toString());
                    String emptyLine = "";
                    this.view.getFoodView().getItems().add(emptyLine);
                } else if (object instanceof Recipe) {
                    Food recipe = (Recipe) object;
                    this.view.getFoodView().getItems().add(recipe.toString());
                    String emptyLine = "";
                    this.view.getFoodView().getItems().add(emptyLine);
                }
            }
            ArrayList<Object> logList = this.logsModel
                    .read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv");
            for (Object log : logList) {
                if (log instanceof Log) {
                    Log log2 = (Log) log;
                    this.view.getLogsView().getItems().add(log2.toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBasicFoodsAndRecipes(ComboBox<String> comboBox) {
        try {
            ArrayList<Object> list = this.foodModel.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\foods.csv");
            for (Object object : list) {
                if (object instanceof BasicFood) {
                    BasicFood food = (BasicFood) object;
                    // Add basic food name to the ComboBox
                    comboBox.getItems().add(food.getName());
                } else if (object instanceof Recipe) {
                    Recipe recipe = (Recipe) object;
                    // Add recipe name to the ComboBox
                    comboBox.getItems().add(recipe.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent event) {
        loadData();
    }

}