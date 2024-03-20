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
            if (selectedItem.startsWith("r,")) {
                recordType = 'r';
            }

            if (recordType == 'f') {
                String[] itemParts = selectedItem.split(",");
                String foodName = itemParts[0].trim().split(":")[1].trim();
                Log log = new Log(getFormattedDate(), recordType, foodName, 1.0);
                try {
                    model.write("src\\edu\\rit\\croatia\\swen383\\g1\\dm\\Vendor\\log.csv", log);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                log.setDate(getFormattedDate2());
                view.getLogsView().getItems().add(log.toString());
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
