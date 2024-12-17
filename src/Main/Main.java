package Main;

import GUI.ProgramGUI;
import Tools.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//java --module-path C:\javafx-sdk-13.0.2\lib --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.web -jar STL.jar

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private SerialConnection serialConnection;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        serialConnection = new SerialConnection("COM3"); // Replace with your port
        if (serialConnection.openConnection()) {
            Label dataLabel = new Label("Waiting for data...");

            new Thread(() -> {
                while (true) {
                    String data = serialConnection.readData();
                    if (data != null) {
                        dataLabel.setText(data);
                    }
                }
            }).start();

        } else {
            System.out.println("Failed to open serial port.");
        }

        Scene scene = new ProgramGUI(primaryStage).getScene();
        scene.getStylesheets().add("file:src/Main/style.css");
        primaryStage.setTitle(Constants.window_label);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() {
        serialConnection.closeConnection();
    }

}

