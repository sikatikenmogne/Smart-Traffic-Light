package GUI;

import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.util.Random;

/**
 * This class provides randomized and realistic data for various traffic KPI charts.
 */
public class RealisticData {

    private static final Random random = new Random();

    /**
     * Generates realistic data for Average Waiting Time (AWT).
     *
     * @return a series of data points representing the average waiting time at different traffic lights.
     */
    public static XYChart.Series<String, Number> getAWTData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("AWT");
        series.getData().add(new XYChart.Data<>("Light 1", random.nextInt(50) + 10));
        series.getData().add(new XYChart.Data<>("Light 2", random.nextInt(50) + 10));
        series.getData().add(new XYChart.Data<>("Light 3", random.nextInt(50) + 10));
        return series;
    }

    /**
     * Generates realistic data for Average Speed.
     *
     * @return a series of data points representing the average speed of vehicles over time.
     */
    public static XYChart.Series<String, Number> getAverageSpeedData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Average Speed");
        series.getData().add(new XYChart.Data<>("Time 1", random.nextInt(30) + 40));
        series.getData().add(new XYChart.Data<>("Time 2", random.nextInt(30) + 40));
        series.getData().add(new XYChart.Data<>("Time 3", random.nextInt(30) + 40));
        return series;
    }

    /**
     * Generates realistic data for Traffic Flow Rate.
     *
     * @return a series of data points representing the number of vehicles passing a point per unit time.
     */
    public static XYChart.Series<String, Number> getTrafficFlowRateData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Traffic Flow Rate");
        series.getData().add(new XYChart.Data<>("Time 1", random.nextInt(100) + 150));
        series.getData().add(new XYChart.Data<>("Time 2", random.nextInt(100) + 150));
        series.getData().add(new XYChart.Data<>("Time 3", random.nextInt(100) + 150));
        return series;
    }

    /**
     * Generates realistic data for Travel Time.
     *
     * @return a series of data points representing the total travel time for vehicles from one point to another.
     */
    public static XYChart.Series<String, Number> getTravelTimeData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Travel Time");
        series.getData().add(new XYChart.Data<>("Route 1", random.nextInt(20) + 10));
        series.getData().add(new XYChart.Data<>("Route 2", random.nextInt(20) + 10));
        series.getData().add(new XYChart.Data<>("Route 3", random.nextInt(20) + 10));
        return series;
    }

    /**
     * Generates realistic data for Queue Length.
     *
     * @return a series of data points representing the length of vehicle queues at different traffic lights.
     */
    public static XYChart.Series<String, Number> getQueueLengthData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Queue Length");
        series.getData().add(new XYChart.Data<>("Light 1", random.nextInt(20) + 5));
        series.getData().add(new XYChart.Data<>("Light 2", random.nextInt(20) + 5));
        series.getData().add(new XYChart.Data<>("Light 3", random.nextInt(20) + 5));
        return series;
    }

    /**
     * Generates realistic data for Intersection Delay.
     *
     * @return a series of data points representing the delay experienced by vehicles at intersections.
     */
    public static XYChart.Series<Number, Number> getIntersectionDelayData() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Intersection Delay");
        series.getData().add(new XYChart.Data<>(1, random.nextInt(10) + 1));
        series.getData().add(new XYChart.Data<>(2, random.nextInt(10) + 1));
        series.getData().add(new XYChart.Data<>(3, random.nextInt(10) + 1));
        return series;
    }

    /**
     * Generates realistic data for Throughput.
     *
     * @return an array of PieChart.Data representing the proportion of vehicles that successfully pass through the traffic network.
     */
    public static PieChart.Data[] getThroughputData() {
        int passed = random.nextInt(100);
        int failed = 100 - passed;
        return new PieChart.Data[]{
                new PieChart.Data("Passed", passed),
                new PieChart.Data("Failed", failed)
        };
    }
}