package GUI;

import Main.Connector;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.*;

public class ConnectorData {

    private Connector connector;
    private  Map<String, Integer> connectedData;
    private Deque<Map<String, Integer>> dataStack = new ArrayDeque<>(10);


    public ConnectorData() {
        this.connector = Main.Main.getConnector();
        connector.setDataCallback(this::updateData);
    }

    private void updateData(Map<String, Integer> data){
        if (data == null || data.isEmpty()) {
            return;
        }
        connectedData = data;
        if (dataStack.size() == 10) {
            dataStack.removeFirst();
        }
        dataStack.addLast(data);
    }

    public XYChart.Series<String, Number> getAWTData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("AWT");
        if (dataStack.isEmpty()) {
            return series;
        } else {
            Map<String, Integer> averageData = calculateAverage();
            addDataToSeries(series, "Green", averageData.get("crossroad_1_green"));
            addDataToSeries(series, "Red", averageData.get("crossroad_1_red"));
            addDataToSeries(series, "Yellow", averageData.get("crossroad_1_yellow"));
        }
        return series;
    }

    private Map<String, Integer> calculateAverage() {
        Map<String, Integer> sumData = new HashMap<>();
        int count = dataStack.size();

        for (Map<String, Integer> data : dataStack) {
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                sumData.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        for (Map.Entry<String, Integer> entry : sumData.entrySet()) {
            sumData.put(entry.getKey(), entry.getValue() / count);
        }

        return sumData;
    }

    public XYChart.Series<String, Number> getAverageSpeedData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Average Speed");
        Map<String, Integer> data = connector.readFromPort();
        if (!data.isEmpty()) {
            addDataToSeries(series, "Time 1", data.get("crossroad_1_green"));
            addDataToSeries(series, "Time 2", data.get("crossroad_1_red"));
            addDataToSeries(series, "Time 3", data.get("crossroad_1_yellow"));
        }
        return series;
    }

    public XYChart.Series<String, Number> getTrafficFlowRateData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Traffic Flow Rate");
        Map<String, Integer> data = connector.readFromPort();
        addDataToSeries(series, "Time 1", data.get("crossroad_1_green"));
        addDataToSeries(series, "Time 2", data.get("crossroad_1_red"));
        addDataToSeries(series, "Time 3", data.get("crossroad_1_yellow"));
        return series;
    }

    public XYChart.Series<String, Number> getTravelTimeData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Travel Time");
        Map<String, Integer> data = connector.readFromPort();
        addDataToSeries(series, "Route 1", data.get("crossroad_2_green"));
        addDataToSeries(series, "Route 2", data.get("crossroad_2_red"));
        addDataToSeries(series, "Route 3", data.get("crossroad_2_yellow"));
        return series;
    }

    public XYChart.Series<String, Number> getQueueLengthData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Queue Length");
        Map<String, Integer> data = connector.readFromPort();
        addDataToSeries(series, "Light 1", data.get("crossroad_1_green"));
        addDataToSeries(series, "Light 2", data.get("crossroad_1_red"));
        addDataToSeries(series, "Light 3", data.get("crossroad_1_yellow"));
        return series;
    }

    public XYChart.Series<Number, Number> getIntersectionDelayData() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Intersection Delay");
        Map<String, Integer> data = connector.readFromPort();
        int index = 1;
        addDataToSeries(series, index++, data.get("crossroad_2_green"));
        return series;
    }

    public PieChart.Data[] getThroughputData() {
        Map<String, Integer> data = connector.readFromPort();
        int passed = 0;
        int failed = 0;
        if (data.isEmpty()) {
            return new PieChart.Data[]{
                    new PieChart.Data("Passed", 0),
                    new PieChart.Data("Failed", 0)
            };
        }
        return new PieChart.Data[]{
                new PieChart.Data("Passed", passed),
                new PieChart.Data("Failed", failed)
        };
    }

    private void addDataToSeries(XYChart.Series<String, Number> series, String label, Integer value) {
        if (value != null) {
            series.getData().add(new XYChart.Data<>(label, value));
        }
    }

    private void addDataToSeries(XYChart.Series<Number, Number> series, Number xValue, Integer yValue) {
        if (yValue != null) {
            series.getData().add(new XYChart.Data<>(xValue, yValue));
        }
    }
}