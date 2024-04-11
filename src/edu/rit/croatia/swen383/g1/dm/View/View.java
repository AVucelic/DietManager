package View;

import java.time.LocalDate;

import Controller.Controller;
import Controller.HandleAddFood;
import Controller.HandleAddRecipe;
import Controller.HandleAddToLogs;
import Controller.HandleShowPieChart;
import Model.FileHandler;
import Model.Foods;
import Model.Logs;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

public class View extends Application {

    private ListView<String> foodView;
    private ListView<String> logsView;
    private ListView<String> exerciseView;
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
    private Button addRecipeButton;
    private Button addExerciseButton;
    // private Foods foods = new Foods(new FileHandler());
    // private Controller controller = new Controller(this, new Foods(new
    // FileHandler()), new Logs(new FileHandler()));
    private TextField count1;
    private TextField count2;
    private TextField count3;
    private DatePicker dp;

    // textfield for displaying calories per date
    private TextField caloriesTextField;
    private TextField carbsTextField;
    private TextField fatsTextField;
    private TextField proteinsTextField;
    
    private Label exerciseLabel;
    private Button addExerciseBtn;
    final double buttonWidth = 150;
    public TextField getCaloriesTextField() {
        return caloriesTextField;
    }

    public TextField getCarbsTextField() {
        return carbsTextField;
    }

    public TextField getFatsTextField() {
        return fatsTextField;
    }

    public TextField getProteinsTextField() {
        return proteinsTextField;
    }

    private boolean isUpdatingComboBoxes = false;

    public void handleDateSelection(EventHandler<ActionEvent> event) {
        dp.setOnAction(event);
    }

    public void HandleAddToLogs(EventHandler<ActionEvent> event) {
        logBtn.setOnAction(event);
    }

    public void HandleShowPieChart(EventHandler<MouseEvent> event) {
        foodView.setOnMouseClicked(event);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dp = new DatePicker();
        dp.setPromptText("Select a date for logs");
        addButton = new Button("Add");
        addRecipeButton = new Button("Add");
        addExerciseButton = new Button("Add");
        ingredientComboBox = new ComboBox<>();
        ingredientComboBox2 = new ComboBox<>();
        ingredientComboBox3 = new ComboBox<>();

        caloriesTextField = new TextField();
        caloriesTextField.setEditable(false);
        caloriesTextField.setPromptText("Calories consumed will be shown here");

        carbsTextField = new TextField();
        carbsTextField.setEditable(false);
        carbsTextField.setPromptText("Carbs consumed will be shown here");

        fatsTextField = new TextField();
        fatsTextField.setEditable(false);
        fatsTextField.setPromptText("Fats consumed will be shown here");

        proteinsTextField = new TextField();
        proteinsTextField.setEditable(false);
        proteinsTextField.setPromptText("Protein consumed will be shown here");

        foodView = new ListView<>();
        logsView = new ListView<>();

        foodLabel = new Label("Food");
        logLabel = new Label("Log");

        // Logs logsModel = new Logs(new FileHandler());
        // logsModel.read("src\\\\edu\\\\rit\\\\croatia\\\\swen383\\\\g1\\\\dm\\\\Vendor\\\\log.csv");

        addFoodBtn = new Button("Add Food");
        addFoodBtn.setOnAction(event -> showAddPopup("b"));
        addRecipeBtn = new Button("Add Recipe");
        addRecipeBtn.setOnAction(event -> showAddPopup("r"));
        logBtn = new Button("Add to logs");
        addExerciseBtn = new Button("Add Exercise");
        addExerciseBtn.setOnAction(event -> showAddPopup("e"));
        btnBox = new VBox(10);
        addFoodBtn.setPrefWidth(buttonWidth);
        addFoodBtn.setMinWidth(buttonWidth);
        addFoodBtn.setMaxWidth(buttonWidth);
        logBtn.setPrefWidth(buttonWidth);
        logBtn.setMinWidth(buttonWidth);
        logBtn.setMaxWidth(buttonWidth);
        addExerciseBtn.setPrefWidth(buttonWidth);
        addExerciseBtn.setMinWidth(buttonWidth);
        addExerciseBtn.setMaxWidth(buttonWidth);
        addRecipeBtn.setPrefWidth(buttonWidth);
        addRecipeBtn.setMinWidth(buttonWidth);
        addRecipeBtn.setMaxWidth(buttonWidth);
        btnBox.getChildren().addAll(addFoodBtn, addRecipeBtn, logBtn, addExerciseBtn);

        gPane = new GridPane();
        gPane.add(btnBox, 0, 0);
        gPane.add(foodLabel, 1, 1);
        gPane.add(logLabel, 2, 1);
        gPane.add(foodView, 1, 2);
        gPane.add(logsView, 2, 2);
        gPane.add(dp, 2, 0);

        gPane.add(caloriesTextField, 2, 3);
        gPane.add(carbsTextField, 2, 4);
        gPane.add(fatsTextField, 2, 5);
        gPane.add(proteinsTextField, 2, 6);

        foodView.setPrefHeight(500);
        logsView.setPrefHeight(500);
        exerciseView = new ListView<>();
        exerciseLabel = new Label("Exercise");
        gPane.add(exerciseLabel, 3, 1);
        gPane.add(exerciseView, 3, 2);
        gPane.getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(500),
                new ColumnConstraints(500),
                new ColumnConstraints(500));

        gPane.setPadding(new Insets(10));
        gPane.setHgap(60);

        VBox.setVgrow(foodView, Priority.ALWAYS);
        VBox.setVgrow(logsView, Priority.ALWAYS);

        Scene scene = new Scene(gPane, 1850, 700);
        primaryStage.setTitle("DietManager 1.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ComboBox<String> ingredientComboBox = null;
    private ComboBox<String> ingredientComboBox2 = null;
    private ComboBox<String> ingredientComboBox3 = null;

    public void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void HandleComboBoxListener(EventHandler<ActionEvent> event) {
        ingredientComboBox.setOnAction(event);
        ingredientComboBox2.setOnAction(event);
        ingredientComboBox3.setOnAction(event);
    }

    private void showAddPopup(String type) {
        popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        VBox layout = new VBox(10);
        typeField = new TextField();
        if (type.equals("b")) {

            typeField.setText("b");
            popupStage.setTitle("Add Food");
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
            layout.getChildren().addAll(nameField, caloriesField, fatField, carbsField, proteinField, addButton);
        } else if(type.equals("r")) {
            typeField.setText("r");
            popupStage.setTitle("Add Recipe");
            nameField = new TextField("Name");
            ingredientComboBox.setPromptText("Select Ingredient");
            count1 = new TextField();
            ingredientComboBox2.setPromptText("Select Ingredient");
            count2 = new TextField();
            ingredientComboBox3.setPromptText("Select Ingredient");
            count3 = new TextField();
            System.out.println("Ingredient ComboBox initialized: " + (ingredientComboBox != null));
            layout.getChildren().addAll(
                    nameField,
                    ingredientComboBox, count1, ingredientComboBox2, count2, ingredientComboBox3, count3,
                    addRecipeButton);

        }else{
            typeField.setText("e");
            popupStage.setTitle("Add Exercise");
            nameField = new TextField();
            nameField.setPromptText("Name");
            typeField = new TextField();
            typeField.setPromptText("Type");
            caloriesField = new TextField();
            caloriesField.setPromptText("Calories");
           
            layout.getChildren().addAll(nameField, typeField, caloriesField, addExerciseButton);
        }

        layout.setPadding(new Insets(10));
        layout.setSpacing(10);

        Scene scene = new Scene(layout, 300, 300);
        popupStage.setScene(scene);
        popupStage.show();
    }

    public void HandleAddRecipe(EventHandler<ActionEvent> event) {
        addRecipeButton.setOnAction(event);
    }

    public void HandleAddFood(EventHandler<ActionEvent> event) {
        addButton.setOnAction(event);
    }
    
    public void HandleAddExercise(EventHandler<ActionEvent> event){
        addExerciseButton.setOnAction(event);
    }

    public DatePicker getDp() {
        return dp;
    }

    public void setDp(DatePicker dp) {
        this.dp = dp;
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

    public ListView<String> getExerciseView() {
        return exerciseView;
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
