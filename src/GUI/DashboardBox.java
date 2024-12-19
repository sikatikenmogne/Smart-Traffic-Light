package GUI;

import Main.Connector;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Map;

public class DashboardBox {

    private Connector connector;

    private static Stage stage;

    public DashboardBox() {
        this.connector = Main.Main.getConnector();
        connector.setDataCallback(this::updateUI);
    }

    public static void display() {
        stage = new Stage();
        stage.setTitle("Dashboard");

        ConnectorData connectorData = new ConnectorData();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        XYChart.Series<String, Number> awtSeries = connectorData.getAWTData();
        XYChart.Series<String, Number> speedSeries = RealisticData.getAverageSpeedData();
        XYChart.Series<String, Number> flowRateSeries = RealisticData.getTrafficFlowRateData();
        XYChart.Series<String, Number> travelTimeSeries = RealisticData.getTravelTimeData();
        XYChart.Series<String, Number> queueLengthSeries = SampleData.getQueueLengthData();
        XYChart.Series<Number, Number> delaySeries = SampleData.getIntersectionDelayData();
        PieChart throughputChart = new PieChart();
        throughputChart.getData().addAll(SampleData.getThroughputData());

        gridPane.add(TrafficKPICharts.createAWTChart(awtSeries), 0, 0);
        gridPane.add(TrafficKPICharts.createAverageSpeedChart(speedSeries), 1, 0);
        gridPane.add(TrafficKPICharts.createTrafficFlowRateChart(flowRateSeries), 0, 1);
        gridPane.add(TrafficKPICharts.createTravelTimeChart(travelTimeSeries), 1, 1);
        gridPane.add(TrafficKPICharts.createQueueLengthChart(queueLengthSeries), 0, 2);
        gridPane.add(TrafficKPICharts.createIntersectionDelayChart(delaySeries), 1, 2);


        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());
        gridPane.add(closeButton, 0, 4, 2, 1);
        GridPane.setHalignment(closeButton, javafx.geometry.HPos.CENTER);

        Scene scene = new Scene(gridPane, 800, 600);
        stage.setScene(scene);
        stage.show();

        startDataUpdate(connectorData, awtSeries, speedSeries, flowRateSeries, travelTimeSeries, queueLengthSeries, delaySeries, throughputChart);
    }

    private static void startDataUpdate(ConnectorData connectorData, XYChart.Series<String, Number> awtSeries, XYChart.Series<String, Number> speedSeries, XYChart.Series<String, Number> flowRateSeries, XYChart.Series<String, Number> travelTimeSeries, XYChart.Series<String, Number> queueLengthSeries, XYChart.Series<Number, Number> delaySeries, PieChart throughputChart) {
        new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    awtSeries.getData().setAll(connectorData.getAWTData().getData());
                    speedSeries.getData().setAll(connectorData.getAverageSpeedData().getData());
                    flowRateSeries.getData().setAll(connectorData.getTrafficFlowRateData().getData());
                    travelTimeSeries.getData().setAll(connectorData.getTravelTimeData().getData());
                    queueLengthSeries.getData().setAll(connectorData.getQueueLengthData().getData());
                    delaySeries.getData().setAll(connectorData.getIntersectionDelayData().getData());
                    throughputChart.getData().setAll(connectorData.getThroughputData());
                });
                try {
                    Thread.sleep(1000); // Update every second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void close() {
        if (stage != null) {
            stage.close();
        }
    }


    private void updateUI(Map<String, Integer> data) {
        Platform.runLater(() -> {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Updated Data");
            addDataToSeries(series, "Light 1", data.get("crossroad_1_green"));
            addDataToSeries(series, "Light 2", data.get("crossroad_1_red"));
            addDataToSeries(series, "Light 3", data.get("crossroad_1_yellow"));
            // Add series to your chart or update existing chart
        });
    }

    private void addDataToSeries(XYChart.Series<String, Number> series, String label, Integer value) {
        if (value != null) {
            series.getData().add(new XYChart.Data<>(label, value));
        }
    }

}
