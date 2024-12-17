package GUI;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 * This class provides sample data for various traffic KPI charts.
 */
public class SampleData {

    /**
     * Generates sample data for Average Waiting Time (AWT).
     *
     * @return a series of data points representing the average waiting time at different traffic lights.
     */
    public static XYChart.Series<String, Number> getAWTData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("AWT");
        series.getData().add(new XYChart.Data<>("Light 1", 30));
        series.getData().add(new XYChart.Data<>("Light 2", 45));
        series.getData().add(new XYChart.Data<>("Light 3", 25));
        return series;
    }

    /**
     * Generates sample data for Average Speed.
     *
     * @return a series of data points representing the average speed of vehicles over time.
     */
    public static XYChart.Series<String, Number> getAverageSpeedData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Average Speed");
        series.getData().add(new XYChart.Data<>("Time 1", 60));
        series.getData().add(new XYChart.Data<>("Time 2", 55));
        series.getData().add(new XYChart.Data<>("Time 3", 70));
        return series;
    }

    /**
     * Generates sample data for Traffic Flow Rate.
     *
     * @return a series of data points representing the number of vehicles passing a point per unit time.
     */
    public static XYChart.Series<String, Number> getTrafficFlowRateData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Traffic Flow Rate");
        series.getData().add(new XYChart.Data<>("Time 1", 200));
        series.getData().add(new XYChart.Data<>("Time 2", 180));
        series.getData().add(new XYChart.Data<>("Time 3", 220));
        return series;
    }

    /**
     * Generates sample data for Travel Time.
     *
     * @return a series of data points representing the total travel time for vehicles from one point to another.
     */
    public static XYChart.Series<String, Number> getTravelTimeData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Travel Time");
        series.getData().add(new XYChart.Data<>("Route 1", 15));
        series.getData().add(new XYChart.Data<>("Route 2", 20));
        series.getData().add(new XYChart.Data<>("Route 3", 10));
        return series;
    }

    /**
     * Generates sample data for Queue Length.
     *
     * @return a series of data points representing the length of vehicle queues at different traffic lights.
     */
    public static XYChart.Series<String, Number> getQueueLengthData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Queue Length");
        series.getData().add(new XYChart.Data<>("Light 1", 10));
        series.getData().add(new XYChart.Data<>("Light 2", 15));
        series.getData().add(new XYChart.Data<>("Light 3", 5));
        return series;
    }

    /**
     * Generates sample data for Intersection Delay.
     *
     * @return a series of data points representing the delay experienced by vehicles at intersections.
     */
    public static XYChart.Series<Number, Number> getIntersectionDelayData() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Intersection Delay");
        series.getData().add(new XYChart.Data<>(1, 5));
        series.getData().add(new XYChart.Data<>(2, 10));
        series.getData().add(new XYChart.Data<>(3, 3));
        return series;
    }

    /**
     * Generates sample data for Throughput.
     *
     * @return an array of PieChart.Data representing the proportion of vehicles that successfully pass through the traffic network.
     */
    public static PieChart.Data[] getThroughputData() {
        return new PieChart.Data[]{
                new PieChart.Data("Passed", 70),
                new PieChart.Data("Failed", 30)
        };
    }
}