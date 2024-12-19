package GUI;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Dashboard {

    private ConnectorData connectorData;
    private XYChart.Series<String, Number> awtSeries;
    private XYChart.Series<String, Number> speedSeries;
    private XYChart.Series<String, Number> flowRateSeries;
    private XYChart.Series<String, Number> travelTimeSeries;
    private XYChart.Series<String, Number> queueLengthSeries;
    private XYChart.Series<Number, Number> delaySeries;
    private PieChart throughputChart;

    public Dashboard(Stage stage) {
        connectorData = new ConnectorData();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        awtSeries = connectorData.getAWTData();
        speedSeries = connectorData.getAverageSpeedData();
        flowRateSeries = connectorData.getTrafficFlowRateData();
        travelTimeSeries = connectorData.getTravelTimeData();
        queueLengthSeries = connectorData.getQueueLengthData();
        delaySeries = connectorData.getIntersectionDelayData();
        throughputChart = new PieChart();
        throughputChart.getData().addAll(connectorData.getThroughputData());

        gridPane.add(TrafficKPICharts.createAWTChart(awtSeries), 0, 0);
        gridPane.add(TrafficKPICharts.createAverageSpeedChart(speedSeries), 1, 0);
        gridPane.add(TrafficKPICharts.createTrafficFlowRateChart(flowRateSeries), 0, 1);
        gridPane.add(TrafficKPICharts.createTravelTimeChart(travelTimeSeries), 1, 1);
        gridPane.add(TrafficKPICharts.createQueueLengthChart(queueLengthSeries), 0, 2);
        gridPane.add(TrafficKPICharts.createIntersectionDelayChart(delaySeries), 1, 2);

        Scene scene = new Scene(gridPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Dashboard");
        stage.show();

        startDataUpdate();
    }

    private void startDataUpdate() {
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
}