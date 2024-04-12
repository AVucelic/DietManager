package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Log;
import Model.csvModel;
import View.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class HandleRemoveLogs implements EventHandler<ActionEvent> {
    private View view;
    private csvModel model;
    private Controller controller;
    private Alert alert;

    public HandleRemoveLogs(View view, csvModel model, Controller controller) {
        this.view = view;
        this.model = model;
        this.controller = controller;
    }

    @Override
    public void handle(ActionEvent event) {
        String selectedLogString = view.getLogsView().getSelectionModel().getSelectedItem();

        if (selectedLogString != null) {
            try {
                String[] parts = selectedLogString.split(" - ");
                if (parts.length == 2) {
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy,MM,dd");
                    String date = outputFormat.format(inputFormat.parse(parts[0].trim()));

                    String nameAndDetails = parts[1].trim();

                    int indexOfComma = nameAndDetails.indexOf(',');
                    String name = (indexOfComma != -1) ? nameAndDetails.substring(0, indexOfComma).trim()
                            : nameAndDetails.trim();
                    Log selectedLog;
                    if (name.startsWith("Food:")) {
                        name = name.substring("Food:".length()).trim();
                        selectedLog = new Log(date, 'f', name, 0.0);
                        model.remove(selectedLog);
                    } else if (name.startsWith("Recipe:")) {
                        int ingredientIndex = nameAndDetails.indexOf("Ingredient:");
                        name = (ingredientIndex != -1)
                                ? nameAndDetails.substring("Recipe:".length(), ingredientIndex).trim()
                                : nameAndDetails.substring("Recipe:".length()).trim();
                        selectedLog = new Log(date, 'f', name, 0.0);
                        model.remove(selectedLog);
                    } else if (name.startsWith("Exercise:")) {
                        name = name.substring("Exercise:".length()).trim();
                        selectedLog = new Log(date, 'e', name, 0.0);
                        model.remove(selectedLog);
                    } else {
                        view.showAlert(Alert.AlertType.ERROR, "Error",
                                "Cannot remove weight or calorite limit, only exercises and food.",
                                "Error.");
                        return;
                    }

                }

                view.getLogsView().getItems().clear();
                view.getLogsView().getItems().addAll(getLogsAsStringList(model.getData()));

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            controller.loadData();
        }
    }

    private List<String> getLogsAsStringList(List<Object> logs) {
        List<String> logStrings = new ArrayList<>();
        for (Object log : logs) {
            if (log instanceof Log) {
                logStrings.add(log.toString());
            }
        }
        return logStrings;
    }
}
