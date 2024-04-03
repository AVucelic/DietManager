import View.View;
import javafx.application.Application;
import javafx.stage.Stage;
import Model.csvModel;
import Model.Foods;
import Model.Logs;
import Controller.Controller;
import Model.FileHandler;

public class DietManagerApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View();
        csvModel foodsModel = new Foods(new FileHandler());
        csvModel logsModel = new Logs(new FileHandler());

        Controller controller = new Controller(view, foodsModel, logsModel);
        view.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}