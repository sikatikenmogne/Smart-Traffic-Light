package Main;

import GUI.ProgramGUI;
import Tools.Constants;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class Main extends Application {

    private static Connector connector;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        connector = new Connector("COM5"); // Replace with your port
        if (connector != null) {
            Label dataLabel = new Label("Waiting for data...");

            new Thread(() -> {
                while (true) {
                    Map<String, Integer> data = connector.readFromPort();
                    if (!data.isEmpty()) {
                        dataLabel.setText(data.toString());
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
        if (connector != null) {
            connector.closeConnection();
        }
    }

    public static Connector getConnector() {
        return connector;
    }
}