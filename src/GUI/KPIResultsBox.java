package GUI;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KPIResultsBox {

    public static void display() {
        Stage stage = new Stage();
        stage.setTitle("Traffic KPI Charts");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(TrafficKPICharts.createAWTChart(), 0, 0);
        gridPane.add(TrafficKPICharts.createAverageSpeedChart(), 1, 0);
        gridPane.add(TrafficKPICharts.createTrafficFlowRateChart(), 0, 1);
        gridPane.add(TrafficKPICharts.createTravelTimeChart(), 1, 1);
        gridPane.add(TrafficKPICharts.createQueueLengthChart(), 0, 2);
        gridPane.add(TrafficKPICharts.createIntersectionDelayChart(), 1, 2);
//        gridPane.add(TrafficKPICharts.createThroughputChart(), 0, 3, 2, 1);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());
        gridPane.add(closeButton, 0, 4, 2, 1);
        GridPane.setHalignment(closeButton, javafx.geometry.HPos.CENTER);

        Scene scene = new Scene(gridPane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }
}