package Controller;

import java.io.IOException;
import java.util.Arrays;

import Model.Log;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class HandleRemoveLogs implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;

    public HandleRemoveLogs(View view, csvModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void handle(ActionEvent event) {
        int selectedIndex = view.getLogsView().getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            try {
                String selectedItem = view.getLogsView().getSelectionModel().getSelectedItem();
                System.out.println("Selected Item: " + selectedItem); // Print selected item for debugging
                Log logToRemove = parseLogFromString(selectedItem);
                System.out.println("Log to Remove: " + logToRemove); // Print parsed log for debugging
                model.remove(selectedItem);
                view.getLogsView().getItems().remove(selectedIndex);
                System.out.println("Log removed from the list and CSV file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Log parseLogFromString(String logString) {
        String[] parts = logString.split(":");
        String dateAndInfo = parts[1].trim();
        String[] info = dateAndInfo.split("\\s+");
        String date = info[3] + " " + info[4];
        char recordType = info[info.length - 3].charAt(0);
        double servings = 1.0;
        try {
            servings = Double.parseDouble(info[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String foodName = String.join(" ", Arrays.copyOfRange(info, 5, info.length));
        return new Log(date, recordType, foodName, servings);
    }
}
