package View;

import Controller.Controller;
import Model.FileHandler;
import Model.Foods;
import Model.Logs;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application {

    private ListView<String> foodView;
    private ListView<String> logsView;
    private Label foodLabel;
    private Label logLabel;
    private Button addFoodBtn;
    private Button logBtn;
    private Button loadData;
    private VBox btnBox;
    private GridPane gPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        foodView = new ListView<>();
        logsView = new ListView<>();

        foodLabel = new Label("Food");
        logLabel = new Label("Log");

        addFoodBtn = new Button("Add Food");
        logBtn = new Button("Add to logs");
        loadData = new Button("Load data");
        loadData.setOnAction(new Controller(this, new Foods(new FileHandler()), new Logs(new FileHandler())));
        btnBox = new VBox(10);
        btnBox.getChildren().addAll(addFoodBtn, logBtn, loadData);

        gPane = new GridPane();
        gPane.add(btnBox, 0, 0);
        gPane.add(foodLabel, 1, 0);
        gPane.add(logLabel, 2, 0);
        gPane.add(foodView, 1, 1);
        gPane.add(logsView, 2, 1);

        foodView.setPrefHeight(500);
        logsView.setPrefHeight(500);
        gPane.getColumnConstraints().addAll(new ColumnConstraints(100), new ColumnConstraints(450),
                new ColumnConstraints(450));

        gPane.setPadding(new Insets(10));
        gPane.setHgap(10);

        VBox.setVgrow(foodView, Priority.ALWAYS);
        VBox.setVgrow(logsView, Priority.ALWAYS);

        Scene scene = new Scene(gPane, 1100, 700);
        primaryStage.setTitle("DietManager 1.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Button getAddFoodBtn() {
        return addFoodBtn;
    }

    public void setAddFoodBtn(Button addFoodBtn) {
        this.addFoodBtn = addFoodBtn;
    }

    public Button getLogBtn() {
        return logBtn;
    }

    public void setLogBtn(Button logBtn) {
        this.logBtn = logBtn;
    }

    public Button getLoadData() {
        return loadData;
    }

    public void setLoadData(Button loadData) {
        this.loadData = loadData;
    }

    public ListView<String> getFoodView() {
        return foodView;
    }

    public void setFoodView(ListView<String> foodView) {
        this.foodView = foodView;
    }

    public ListView<String> getLogsView() {
        return logsView;
    }

    public void setLogsView(ListView<String> logsView) {
        this.logsView = logsView;
    }

    public Label getFoodLabel() {
        return foodLabel;
    }

    public void setFoodLabel(Label foodLabel) {
        this.foodLabel = foodLabel;
    }

    public Label getLogLabel() {
        return logLabel;
    }

    public void setLogLabel(Label logLabel) {
        this.logLabel = logLabel;
    }

    public VBox getBtnBox() {
        return btnBox;
    }

    public void setBtnBox(VBox btnBox) {
        this.btnBox = btnBox;
    }

    public GridPane getgPane() {
        return gPane;
    }

    public void setgPane(GridPane gPane) {
        this.gPane = gPane;
    }
}
