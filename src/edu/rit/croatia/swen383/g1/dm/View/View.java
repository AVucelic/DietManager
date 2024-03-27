package View;

import javax.swing.Action;

import Controller.Controller;
import Controller.HandleAddFood;
import Controller.HandleAddRecipe;
import Controller.HandleAddToLogs;
import Controller.HandleRemoveLogs;
import Model.FileHandler;
import Model.Foods;
import Model.Logs;
import Model.csvModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
    private Button addRecipeBtn;
    private TextField typeField;
    private TextField nameField;
    private TextField caloriesField;
    private TextField fatField;
    private TextField carbsField;
    private TextField proteinField;
    private Stage popupStage;
    private Button addButton;
    private Button removeLogsButton;
    private Button updateLogsButton;
    private Foods foods = new Foods(new FileHandler());
    private Controller controller = new Controller(this, foods, new Logs(new FileHandler()));
    private TextField count1;
    private TextField count2;
    private TextField count3;

    public Foods getFoods() {
        return foods;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        foodView = new ListView<>();
        logsView = new ListView<>();

        foodLabel = new Label("Food");
        logLabel = new Label("Log");

        removeLogsButton = new Button("Remove Logs");
        Logs logsModel = new Logs(new FileHandler());
        logsModel.read("src\\\\edu\\\\rit\\\\croatia\\\\swen383\\\\g1\\\\dm\\\\Vendor\\\\log.csv");

        HandleRemoveLogs handleRemoveLogs = new HandleRemoveLogs(this, logsModel);
        removeLogsButton.setOnAction(handleRemoveLogs);

        updateLogsButton = new Button("Update Logs");

        updateLogsButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });
        addFoodBtn = new Button("Add Food");
        addFoodBtn.setOnAction(event -> showAddFoodPopup("b"));
        addRecipeBtn = new Button("Add Recipe");
        addRecipeBtn.setOnAction(event -> showAddFoodPopup("r"));
        logBtn = new Button("Add to logs");
        logBtn.setOnAction(new HandleAddToLogs(this, new Logs(new FileHandler())));
        btnBox = new VBox(10);
        btnBox.getChildren().addAll(addFoodBtn, addRecipeBtn, logBtn, removeLogsButton, updateLogsButton);

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

        controller.loadData();

        Scene scene = new Scene(gPane, 1100, 700);
        primaryStage.setTitle("DietManager 1.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ComboBox<String> ingredientComboBox = null;
    private ComboBox<String> ingredientComboBox2 = null;
    private ComboBox<String> ingredientComboBox3 = null;

    // ChatGPT help me quickly generate a pop up window.
    private void showAddFoodPopup(String type) {
        popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        VBox layout = new VBox(10);
        addButton = new Button("Add");
        if (type.equals("b")) {
            popupStage.setTitle("Add Food");
            // Create controls for food fields
            nameField = new TextField();
            nameField.setPromptText("Name");
            caloriesField = new TextField();
            caloriesField.setPromptText("Calories");
            fatField = new TextField();
            fatField.setPromptText("Fat");
            carbsField = new TextField();
            carbsField.setPromptText("Carbs");
            proteinField = new TextField();
            proteinField.setPromptText("Protein");
            layout.getChildren().addAll( nameField, caloriesField, fatField, carbsField, proteinField, addButton);
            addButton.setOnAction(new HandleAddFood(this, foods));
        } else {
            popupStage.setTitle("Add Recipe");
            nameField = new TextField("Name");
            // Create ComboBox for selecting ingredients
            ingredientComboBox = new ComboBox<>();
            ingredientComboBox.setPromptText("Select Ingredient");
            controller.loadBasicFoodsAndRecipes(ingredientComboBox);
            count1 = new TextField();
            ingredientComboBox2 = new ComboBox<>();
            ingredientComboBox2.setPromptText("Select Ingredient");
            controller.loadBasicFoodsAndRecipes(ingredientComboBox2);
            count2 = new TextField();
            ingredientComboBox3 = new ComboBox<>();
            ingredientComboBox3.setPromptText("Select Ingredient");
            controller.loadBasicFoodsAndRecipes(ingredientComboBox3);
            count3 = new TextField();

            // Debug statement to check if ComboBox is initialized
            System.out.println("Ingredient ComboBox initialized: " + (ingredientComboBox != null));
            layout.getChildren().addAll(
                    nameField,
                    ingredientComboBox, count1, ingredientComboBox2, count2, ingredientComboBox3, count3, addButton);
            addButton.setOnAction(new HandleAddRecipe(this, foods));

        }

        // Create button to add food

        // Layout for the popup window

        layout.setPadding(new Insets(10));
        layout.setSpacing(10);

        Scene scene = new Scene(layout, 300, 300);
        popupStage.setScene(scene);
        popupStage.show();
    }

    public TextField getCount1() {
        return count1;
    }

    public TextField getCount2() {
        return count2;
    }

    public TextField getCount3() {
        return count3;
    }

    public Button getRemoveLogsButton() {
        return removeLogsButton;
    }

    public void setRemoveLogsButton(Button removeLogsButton) {
        this.removeLogsButton = removeLogsButton;
    }

    public Button getUpdateLogsButton() {
        return updateLogsButton;
    }

    public void setUpdateLogsButton(Button updateLogsButton) {
        this.updateLogsButton = updateLogsButton;
    }

    public TextField getTypeField() {
        return typeField;
    }

    public ComboBox<String> getIngredientComboBox() {
        return ingredientComboBox;
    }

    public ComboBox<String> getIngredientComboBox2() {
        return ingredientComboBox2;
    }

    public ComboBox<String> getIngredientComboBox3() {
        return ingredientComboBox3;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getCaloriesField() {
        return caloriesField;
    }

    public TextField getFatField() {
        return fatField;
    }

    public TextField getCarbsField() {
        return carbsField;
    }

    public TextField getProteinField() {
        return proteinField;
    }

    public Stage getPopupStage() {
        return popupStage;
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

    public void setTypeField(TextField typeField) {
        this.typeField = typeField;
    }

    public void setNameField(TextField nameField) {
        this.nameField = nameField;
    }

    public void setCaloriesField(TextField caloriesField) {
        this.caloriesField = caloriesField;
    }

    public void setFatField(TextField fatField) {
        this.fatField = fatField;
    }

    public void setCarbsField(TextField carbsField) {
        this.carbsField = carbsField;
    }

    public void setProteinField(TextField proteinField) {
        this.proteinField = proteinField;
    }

    public void setPopupStage(Stage popupStage) {
        this.popupStage = popupStage;
    }
}
