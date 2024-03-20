package View;

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

        btnBox = new VBox(10);
        btnBox.getChildren().addAll(addFoodBtn, logBtn);

        gPane = new GridPane();
        gPane.add(btnBox, 0, 0);
        gPane.add(foodLabel, 1, 0);
        gPane.add(logLabel, 2, 0);
        gPane.add(foodView, 1, 1);
        gPane.add(logsView, 2, 1);

        gPane.getColumnConstraints().addAll(new ColumnConstraints(100), new ColumnConstraints(200),
                new ColumnConstraints(200));

        gPane.setPadding(new Insets(10));
        gPane.setHgap(10);

        VBox.setVgrow(foodView, Priority.ALWAYS);
        VBox.setVgrow(logsView, Priority.ALWAYS);

        Scene scene = new Scene(gPane, 600, 400);
        primaryStage.setTitle("DietManager 1.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
