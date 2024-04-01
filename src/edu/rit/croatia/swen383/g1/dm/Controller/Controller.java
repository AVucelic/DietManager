package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            this.foodModel.setData(list);

            for (Object object : list) {
                Food food = (Food) object;
                this.view.getFoodView().getItems().add(food.toString());
                String emptyLine = "";
                this.view.getFoodView().getItems().add(emptyLine);
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
                    comboBox.getItems().add(food.getName());
                } else if (object instanceof Recipe) {
                    Recipe recipe = (Recipe) object;
                    comboBox.getItems().add(recipe.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDateSelection(LocalDate date) {
        view.getLogsView().getItems().clear();

        try {
            ArrayList<Object> logList = logsModel.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");

            if (date == null) {
                for (Object log : logList) {
                    if (log instanceof Log) {
                        Log logEntry = (Log) log;
                        view.getLogsView().getItems().add(logEntry.toString());
                    }
                }
            } else {
                for (Object log : logList) {
                    if (log instanceof Log) {
                        Log logEntry = (Log) log;
                        LocalDate logDate = LocalDate.parse(logEntry.getDate().replace("-", ","), formatter);
                        if (logDate.equals(date)) {
                            view.getLogsView().getItems().add(logEntry.toString());
                        }
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void handle(ActionEvent event) {
        loadData();
    }

    public int calculateTotalCaloriesForDate(LocalDate selectedDate) {
        int totalCalories = 0;
        try {
            ArrayList<Object> logList = logsModel.read("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv");
            for (Object object : logList) {
                if (object instanceof Log) {
                    Log log = (Log) object;
                    LocalDate logDate = LocalDate.parse(log.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    if (logDate.equals(selectedDate)) {
                        for (Object foodObject : foodModel.getData()) {
                            if (foodObject instanceof Food) {
                                Food food = (Food) foodObject;
                                if (food.getName().equalsIgnoreCase(log.getFoodName())) {
                                    if (food instanceof BasicFood) {
                                        totalCalories += ((BasicFood) food).getCalories() * log.getServings();
                                    } else if (food instanceof Recipe) {
                                        totalCalories += ((Recipe) food).calculateTotalCalories() *
                                                log.getServings();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalCalories;
    }

}