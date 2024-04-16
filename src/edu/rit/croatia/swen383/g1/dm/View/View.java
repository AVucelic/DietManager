package View;

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
import javafx.scene.control.Alert;

public class View extends Application {

    private ListView<String> foodView;
    private ListView<String> logsView;
    private ListView<String> exerciseView;
    private Label foodLabel;
    private Label logLabel;
    private Label calorieLimitLbl;
    private Label weightLbl;
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
    private TextField calorieLimit;
    private TextField weight;
    private Stage popupStage;
    private Button addButton;
    private Button addRecipeButton;
    private Button addExerciseButton;
    private Button addWeightButton;
    private Button addCalorieLimit;
    private Button addWeight;
    private Button addCL;
    private Button removeLogs;

    public Button getRemoveLogs() {
        return removeLogs;
    }

    public void setRemoveLogs(Button removeLogs) {
        this.removeLogs = removeLogs;
    }

    public Button getAddCL() {
        return addCL;
    }

    public void setAddCL(Button addCL) {
        this.addCL = addCL;
    }

    private TextField count1;
    private TextField count2;
    private TextField count3;
    private DatePicker dp;

    private TextField caloriesTextField;
    private TextField carbsTextField;
    private TextField fatsTextField;
    private TextField proteinsTextField;
    private TextField calorieGoalField;
    private TextField caloriesExpendedField;
    private TextField netCaloriesField;
    private TextField calorieDifferenceField;

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

    public TextField getCaloriesExpendedField() {
        return caloriesExpendedField;
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
        addCalorieLimit = new Button("Add Calorie Limit");
        addWeightButton = new Button("Add Weight");
        addWeight = new Button("Add");
        addCL = new Button("Add");
        removeLogs = new Button("Remove logs");
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

        caloriesExpendedField = new TextField();
        caloriesExpendedField.setEditable(false);
        caloriesExpendedField.setPromptText("Calories Expended will be shown here");

        netCaloriesField = new TextField();
        netCaloriesField.setEditable(false);
        netCaloriesField.setPromptText("Net Calories will be shown here");

        calorieGoalField = new TextField();
        calorieGoalField.setEditable(false);
        calorieGoalField.setPromptText("Calorie Goal will be shown here");

        calorieDifferenceField = new TextField();
        calorieDifferenceField.setEditable(false);
        calorieDifferenceField.setPromptText("Difference from Goal will be shown here");

        foodView = new ListView<>();
        logsView = new ListView<>();
        exerciseView = new ListView<>();
        foodLabel = new Label("Food");
        logLabel = new Label("Log");

        addFoodBtn = new Button("Add Food");
        addFoodBtn.setOnAction(event -> showAddPopup("b"));
        addRecipeBtn = new Button("Add Recipe");
        addRecipeBtn.setOnAction(event -> showAddPopup("r"));
        logBtn = new Button("Add to logs");
        addExerciseBtn = new Button("Add Exercise");
        addExerciseBtn.setOnAction(event -> showAddPopup("e"));
        addCalorieLimit.setOnAction(event -> showAddPopup("c"));
        addWeightButton.setOnAction(event -> showAddPopup("w"));

        btnBox = new VBox(10);
        addFoodBtn.setPrefWidth(buttonWidth);
        addFoodBtn.setMinWidth(buttonWidth);
        addFoodBtn.setMaxWidth(buttonWidth);
        logBtn.setPrefWidth(buttonWidth);
        logBtn.setMinWidth(buttonWidth);
        logBtn.setMaxWidth(buttonWidth);
        removeLogs.setPrefWidth(buttonWidth);
        removeLogs.setMinWidth(buttonWidth);
        removeLogs.setMaxWidth(buttonWidth);
        addExerciseBtn.setPrefWidth(buttonWidth);
        addExerciseBtn.setMinWidth(buttonWidth);
        addExerciseBtn.setMaxWidth(buttonWidth);
        addRecipeBtn.setPrefWidth(buttonWidth);
        addRecipeBtn.setMinWidth(buttonWidth);
        addRecipeBtn.setMaxWidth(buttonWidth);
        addWeightButton.setPrefWidth(buttonWidth);
        addWeightButton.setMinWidth(buttonWidth);
        addWeightButton.setMaxWidth(buttonWidth);
        addCalorieLimit.setPrefWidth(buttonWidth);
        addCalorieLimit.setMinWidth(buttonWidth);
        addCalorieLimit.setMaxWidth(buttonWidth);
        btnBox.getChildren().addAll(addFoodBtn, addRecipeBtn, logBtn, addExerciseBtn, addWeightButton, addCalorieLimit,
                removeLogs);

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
        gPane.add(calorieGoalField, 2, 7);
        gPane.add(caloriesExpendedField, 2, 8);
        gPane.add(netCaloriesField, 2, 9);
        gPane.add(calorieDifferenceField, 2, 10);

        foodView.setPrefHeight(500);
        logsView.setPrefHeight(500);
        exerciseView.setPrefHeight(500);
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
        } else if (type.equals("r")) {
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

        } else if (type.equals("e")) {
            typeField.setText("e");
            popupStage.setTitle("Add Exercise");
            nameField = new TextField();
            nameField.setPromptText("Name");
            caloriesField = new TextField();
            caloriesField.setPromptText("Calories");

            layout.getChildren().addAll(nameField, caloriesField, addExerciseButton);
        } else if (type.equals("c")) {
            typeField.setText("r");
            popupStage.setTitle("Add Calorie Limit");
            calorieLimitLbl = new Label("Calorie Limit:");
            calorieLimit = new TextField();
            calorieLimit.setPromptText("Calorie Limit");
            layout.getChildren().addAll(calorieLimitLbl, calorieLimit, addCL);

        } else if (type.equals("w")) {
            typeField.setText("w");
            popupStage.setTitle("Add Weight");
            weightLbl = new Label("Weight:");
            weight = new TextField();
            weight.setPromptText("Weight");
            layout.getChildren().addAll(weightLbl, weight, addWeight);
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

    public void HandleAddExercise(EventHandler<ActionEvent> event) {
        addExerciseButton.setOnAction(event);
    }

    public void HandleAddWeight(EventHandler<ActionEvent> event) {
        addWeight.setOnAction(event);
    }

    public void HandleAddCalorieLimit(EventHandler<ActionEvent> event) {
        addCL.setOnAction(event);
    }

    public void HandleRemoveLogs(EventHandler<ActionEvent> event) {
        removeLogs.setOnAction(event);
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

    public void setExerciseView(ListView<String> exerciseView) {
        this.exerciseView = exerciseView;
    }

    public Button getAddRecipeBtn() {
        return addRecipeBtn;
    }

    public void setAddRecipeBtn(Button addRecipeBtn) {
        this.addRecipeBtn = addRecipeBtn;
    }

    public TextField getCalorieLimit() {
        return calorieLimit;
    }

    public void setCalorieLimit(TextField calorieLimit) {
        this.calorieLimit = calorieLimit;
    }

    public TextField getWeight() {
        return weight;
    }

    public void setWeight(TextField weight) {
        this.weight = weight;
    }

    public Button getAddButton() {
        return addButton;
    }

    public void setAddButton(Button addButton) {
        this.addButton = addButton;
    }

    public Button getAddRecipeButton() {
        return addRecipeButton;
    }

    public void setAddRecipeButton(Button addRecipeButton) {
        this.addRecipeButton = addRecipeButton;
    }

    public Button getAddExerciseButton() {
        return addExerciseButton;
    }

    public void setAddExerciseButton(Button addExerciseButton) {
        this.addExerciseButton = addExerciseButton;
    }

    public Button getAddWeightButton() {
        return addWeightButton;
    }

    public void setAddWeightButton(Button addWeightButton) {
        this.addWeightButton = addWeightButton;
    }

    public Button getAddCalorieLimit() {
        return addCalorieLimit;
    }

    public void setAddCalorieLimit(Button addCalorieLimit) {
        this.addCalorieLimit = addCalorieLimit;
    }

    public void setCount1(TextField count1) {
        this.count1 = count1;
    }

    public void setCount2(TextField count2) {
        this.count2 = count2;
    }

    public void setCount3(TextField count3) {
        this.count3 = count3;
    }

    public void setCaloriesTextField(TextField caloriesTextField) {
        this.caloriesTextField = caloriesTextField;
    }

    public void setCarbsTextField(TextField carbsTextField) {
        this.carbsTextField = carbsTextField;
    }

    public void setFatsTextField(TextField fatsTextField) {
        this.fatsTextField = fatsTextField;
    }

    public void setProteinsTextField(TextField proteinsTextField) {
        this.proteinsTextField = proteinsTextField;
    }

    public TextField getCalorieGoalField() {
        return calorieGoalField;
    }

    public void setCalorieGoalField(TextField calorieGoalField) {
        this.calorieGoalField = calorieGoalField;
    }

    public void setCaloriesExpendedField(TextField caloriesExpendedField) {
        this.caloriesExpendedField = caloriesExpendedField;
    }

    public TextField getNetCaloriesField() {
        return netCaloriesField;
    }

    public void setNetCaloriesField(TextField netCaloriesField) {
        this.netCaloriesField = netCaloriesField;
    }

    public TextField getCalorieDifferenceField() {
        return calorieDifferenceField;
    }

    public void setCalorieDifferenceField(TextField calorieDifferenceField) {
        this.calorieDifferenceField = calorieDifferenceField;
    }

    public Label getExerciseLabel() {
        return exerciseLabel;
    }

    public void setExerciseLabel(Label exerciseLabel) {
        this.exerciseLabel = exerciseLabel;
    }

    public Button getAddExerciseBtn() {
        return addExerciseBtn;
    }

    public void setAddExerciseBtn(Button addExerciseBtn) {
        this.addExerciseBtn = addExerciseBtn;
    }

    public double getButtonWidth() {
        return buttonWidth;
    }

    public boolean isUpdatingComboBoxes() {
        return isUpdatingComboBoxes;
    }

    public void setUpdatingComboBoxes(boolean isUpdatingComboBoxes) {
        this.isUpdatingComboBoxes = isUpdatingComboBoxes;
    }

    public void setIngredientComboBox(ComboBox<String> ingredientComboBox) {
        this.ingredientComboBox = ingredientComboBox;
    }

    public void setIngredientComboBox2(ComboBox<String> ingredientComboBox2) {
        this.ingredientComboBox2 = ingredientComboBox2;
    }

    public void setIngredientComboBox3(ComboBox<String> ingredientComboBox3) {
        this.ingredientComboBox3 = ingredientComboBox3;
    }
}
