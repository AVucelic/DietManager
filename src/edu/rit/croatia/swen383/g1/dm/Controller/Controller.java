package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Model.Food;
import Model.Foods;
import Model.Log;
import Model.Recipe;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Controller implements EventHandler<ActionEvent> {

    private View view;
    private csvModel foodModel;
    private csvModel logsModel;

    public Controller(View view, csvModel model, csvModel logsModel) {
        this.view = view;
        this.foodModel = model;
        this.logsModel = logsModel;
    }

   

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == view.getLoadData()) {
            try {
                ArrayList<Object> list = this.foodModel
                        .read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\foods.csv");
                for (Object object : list) {
                    if (object instanceof Food) {
                        Food food = (Food) object;
                        this.view.getFoodView().getItems().add(food.toString());
                        String emptyLine = "";
                        this.view.getFoodView().getItems().add(emptyLine);
                    } else if (object instanceof Recipe) {
                        Recipe recipe = (Recipe) object;
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
    }

}