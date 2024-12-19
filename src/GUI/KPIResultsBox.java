package GUI;

import Database.DatabaseConditions;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KPIResultsBox {

    private static Stage stage;

    public static void display(DatabaseConditions database_conditions) {
        stage = new Stage();
        stage.setTitle("Traffic KPI Charts");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(TrafficKPICharts.createAWTChart(RealisticData.getAWTData()), 0, 0);
        gridPane.add(TrafficKPICharts.createAverageSpeedChart(RealisticData.getAverageSpeedData()), 1, 0);
        gridPane.add(TrafficKPICharts.createTrafficFlowRateChart(RealisticData.getTrafficFlowRateData()), 0, 1);
        gridPane.add(TrafficKPICharts.createTravelTimeChart(RealisticData.getTravelTimeData()), 1, 1);
        gridPane.add(TrafficKPICharts.createQueueLengthChart(RealisticData.getQueueLengthData()), 0, 2);
        gridPane.add(TrafficKPICharts.createIntersectionDelayChart(RealisticData.getIntersectionDelayData()), 1, 2);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());
        gridPane.add(closeButton, 0, 4, 2, 1);
        GridPane.setHalignment(closeButton, javafx.geometry.HPos.CENTER);

        Scene scene = new Scene(gridPane, 800, 800);
        stage.setScene(scene);
        stage.show();
    }


    public static void close() {
        if (stage != null) {
            stage.close();
        }
    }
}