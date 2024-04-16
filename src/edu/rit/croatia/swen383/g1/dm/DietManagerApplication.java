import View.View;
import javafx.application.Application;
import javafx.stage.Stage;
import Model.csvModel;
import Model.Foods;
import Model.Logs;
import Model.Weights;
import Controller.Controller;
import Model.FileHandler;
import Model.CalorieLimits;
import Model.Exercises;

public class DietManagerApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View();
        csvModel foodsModel = new Foods(new FileHandler());
        csvModel logsModel = new Logs(new FileHandler());
        csvModel exercisesModel = new Exercises(new FileHandler());
        csvModel calorieLimits = new CalorieLimits(new FileHandler());
        csvModel weightModel = new Weights(new FileHandler());

        Controller controller = new Controller(view, foodsModel, logsModel, exercisesModel, calorieLimits, weightModel);
        view.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
