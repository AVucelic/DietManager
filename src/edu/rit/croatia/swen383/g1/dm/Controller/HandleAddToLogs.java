package Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Log;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HandleAddToLogs implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;

    public HandleAddToLogs(View view, csvModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        String selectedItem = view.getFoodView().getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            char recordType = 'f';

            if (selectedItem.startsWith("Recipe:")) {
                recordType = 'r';

                String[] recipeParts = selectedItem.split("\\n");
                String recipeName = recipeParts[0].substring("Recipe: ".length());

                StringBuilder logLine = new StringBuilder();
                logLine.append(getFormattedDate()).append(", ").append(recordType).append(", ").append(recipeName)
                        .append(", 1.0");
                for (int i = 1; i < recipeParts.length; i++) {
                    String ingredientLine = recipeParts[i].trim();
                    if (ingredientLine.startsWith("Ingredient:")) {
                        String[] ingredientParts = ingredientLine.split(", ");
                        String foodName = ingredientParts[0].substring("Ingredient: ".length()).trim();
                        double count = Double.parseDouble(ingredientParts[1].substring("Count: ".length()).trim());

                        logLine.append(", ").append(foodName).append(", ").append(count);
                    }
                }
                String log = logLine.toString();
                System.out.println(logLine);
                view.getLogsView().getItems().add(logLine.toString());

                try {
                    model.write("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv", log);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                String[] itemParts = selectedItem.split(",");
                String foodName = itemParts[0].trim().split(":")[1].trim();
                Log log = new Log(getFormattedDate(), recordType, foodName, 1.0);

                view.getLogsView().getItems().add(log.toString());

                try {
                    model.write("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv", log.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getFormattedDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getFormattedDate2() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
