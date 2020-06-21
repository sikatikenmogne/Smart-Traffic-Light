package Main;

import Database.Database;
import GUI.ConfirmBox;
import GUI.ProgramGUI;
import Objects.Car.Car;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Objects.TrafficLight.TrafficLight;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        StackPane layout = new StackPane();
//        Scene scene = new Scene(layout, 700, 300);



        window = primaryStage;
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        Scene scene = new ProgramGUI(window).getScene();
        scene.getStylesheets().add("file:src/Main/style.css");

        primaryStage.setTitle("Smart Traffic Light");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void closeProgram() {
        boolean answer = ConfirmBox.display("Exit", "Sure you want to exit?");
        if (answer) {
            window.close();
        }
    }

    public static void main(String[] args) {
//        Database database = Database.getInstance();
//        database.test();
        launch(args);
    }
}
