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
    // private Foods foods = new Foods(new FileHandler());
    private Controller controller = new Controller(this, new Foods(new FileHandler()), new Logs(new FileHandler()));
    private TextField count1;
    private TextField count2;
    private TextField count3;
    private DatePicker dp;

    // textfield for displaying calories per date
    private TextField caloriesTextField;
    private TextField carbsTextField;
    private TextField fatsTextField;
    private TextField proteinsTextField;
    private boolean isUpdatingComboBoxes = false;

    // public Foods getFoods() {
    // return foods;
    // }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dp = new DatePicker();
        dp.setPromptText("Select a date for logs");
        dp.setOnAction(event -> {
            LocalDate date = getDp().getValue();
            controller.handleDateSelection(date);

            int[] totals = controller.calculateTotalNutrientForDate(date);

            caloriesTextField.setText("Calories consumed: " + totals[0]);
            carbsTextField.setText("Carbs consumed: " + totals[1]);
            fatsTextField.setText("Fats consumed: " + totals[2]);
            proteinsTextField.setText("Protein consumed: " + totals[3]);
        });

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

        Logs logsModel = new Logs(new FileHandler());
        logsModel.read("src\\\\edu\\\\rit\\\\croatia\\\\swen383\\\\g1\\\\dm\\\\Vendor\\\\log.csv");

        addFoodBtn = new Button("Add Food");
        addFoodBtn.setOnAction(event -> showAddFoodPopup("b"));
        addRecipeBtn = new Button("Add Recipe");
        addRecipeBtn.setOnAction(event -> showAddFoodPopup("r"));
        logBtn = new Button("Add to logs");
        logBtn.setOnAction(new HandleAddToLogs(this, new Logs(new FileHandler()), controller));
        btnBox = new VBox(10);
        btnBox.getChildren().addAll(addFoodBtn, addRecipeBtn, logBtn);

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
        gPane.getColumnConstraints().addAll(new ColumnConstraints(100), new ColumnConstraints(450),
                new ColumnConstraints(450));

        gPane.setPadding(new Insets(10));
        gPane.setHgap(10);

        VBox.setVgrow(foodView, Priority.ALWAYS);
        VBox.setVgrow(logsView, Priority.ALWAYS);

        controller.loadData();
        foodView.setOnMouseClicked(new HandleShowPieChart(this, controller));

        Scene scene = new Scene(gPane, 1100, 700);
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
    
    private void checkComboBox() {
        EventHandler<ActionEvent> comboBoxListener = event -> {
            if (isUpdatingComboBoxes)
                return;

            isUpdatingComboBoxes = true;

            try {
                String selected1 = ingredientComboBox.getSelectionModel().getSelectedItem();
                String selected2 = ingredientComboBox2.getSelectionModel().getSelectedItem();
                String selected3 = ingredientComboBox3.getSelectionModel().getSelectedItem();

                ingredientComboBox.getItems().clear();
                ingredientComboBox2.getItems().clear();
                ingredientComboBox3.getItems().clear();

                controller.loadBasicFoodsAndRecipes(ingredientComboBox);
                controller.loadBasicFoodsAndRecipes(ingredientComboBox2);
                controller.loadBasicFoodsAndRecipes(ingredientComboBox3);

                if (selected1 != null) {
                    ingredientComboBox2.getItems().remove(selected1);
                    ingredientComboBox3.getItems().remove(selected1);
                }
                if (selected2 != null) {
                    ingredientComboBox.getItems().remove(selected2);
                    ingredientComboBox3.getItems().remove(selected2);
                }
                if (selected3 != null) {
                    ingredientComboBox.getItems().remove(selected3);
                    ingredientComboBox2.getItems().remove(selected3);
                }

                ingredientComboBox.getSelectionModel().select(selected1);
                ingredientComboBox2.getSelectionModel().select(selected2);
                ingredientComboBox3.getSelectionModel().select(selected3);
            } finally {
                isUpdatingComboBoxes = false;
            }
        };

        ingredientComboBox.setOnAction(comboBoxListener);
        ingredientComboBox2.setOnAction(comboBoxListener);
        ingredientComboBox3.setOnAction(comboBoxListener);
    }

    private void showAddFoodPopup(String type) {
        popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        VBox layout = new VBox(10);
        addButton = new Button("Add");
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
            addButton.setOnAction(new HandleAddFood(this, new Foods(new FileHandler())));
        } else {
            typeField.setText("r");
            popupStage.setTitle("Add Recipe");
            nameField = new TextField("Name");
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
            checkComboBox();
            System.out.println("Ingredient ComboBox initialized: " + (ingredientComboBox != null));
            layout.getChildren().addAll(
                    nameField,
                    ingredientComboBox, count1, ingredientComboBox2, count2, ingredientComboBox3, count3, addButton);
            addButton.setOnAction(new HandleAddRecipe(this, new Foods(new FileHandler())));

        }

        layout.setPadding(new Insets(10));
        layout.setSpacing(10);

        Scene scene = new Scene(layout, 300, 300);
        popupStage.setScene(scene);
        popupStage.show();
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
