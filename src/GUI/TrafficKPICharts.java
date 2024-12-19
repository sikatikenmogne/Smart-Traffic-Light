package GUI;

import javafx.scene.chart.*;

/**
 * This class provides methods to create various types of charts for displaying traffic KPIs.
 */
public class TrafficKPICharts {

    /**
     * Creates a BarChart for Average Waiting Time (AWT).
     *
     * @return a BarChart displaying the average waiting time at different traffic lights.
     */
    public static BarChart<String, Number> createAWTChart(XYChart.Series<String, Number> awtData) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("Traffic Light");
        yAxis.setLabel("Average Waiting Time (seconds)");
        barChart.getData().add(awtData);
        return barChart;
    }

    /**
     * Creates a LineChart for Average Speed.
     *
     * @return a LineChart displaying the average speed of vehicles over time.
     */
    public static LineChart<String, Number> createAverageSpeedChart(XYChart.Series<String, Number> averageSpeedData) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        xAxis.setLabel("Time");
        yAxis.setLabel("Average Speed (km/h)");
        lineChart.getData().add(averageSpeedData);
        return lineChart;
    }

    /**
     * Creates an AreaChart for Traffic Flow Rate.
     *
     * @return an AreaChart illustrating the number of vehicles passing a point per unit time.
     */
    public static AreaChart<String, Number> createTrafficFlowRateChart(XYChart.Series<String, Number> trafficFlowRateData) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        xAxis.setLabel("Time");
        yAxis.setLabel("Traffic Flow Rate (vehicles/hour)");
        areaChart.getData().add(trafficFlowRateData);
        return areaChart;
    }

    /**
     * Creates a LineChart for Travel Time.
     *
     * @return a LineChart showing the total travel time for vehicles from one point to another.
     */
    public static LineChart<String, Number> createTravelTimeChart(XYChart.Series<String, Number> travelTimeData) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        xAxis.setLabel("Route");
        yAxis.setLabel("Travel Time (minutes)");
        lineChart.getData().add(travelTimeData);
        return lineChart;
    }

    /**
     * Creates a BarChart for Queue Length.
     *
     * @return a BarChart representing the length of vehicle queues at different traffic lights.
     */
    public static BarChart<String, Number> createQueueLengthChart(XYChart.Series<String, Number> queueLengthData) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("Traffic Light");
        yAxis.setLabel("Queue Length (vehicles)");
        barChart.getData().add(queueLengthData);
        return barChart;
    }

    /**
     * Creates a ScatterChart for Intersection Delay.
     *
     * @return a ScatterChart showing the delay experienced by vehicles at intersections.
     */
    public static ScatterChart<Number, Number> createIntersectionDelayChart(XYChart.Series<Number, Number> intersectionDelayData) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        xAxis.setLabel("Time");
        yAxis.setLabel("Intersection Delay (seconds)");
        scatterChart.getData().add(intersectionDelayData);
        return scatterChart;
    }

    /**
     * Creates a PieChart for Throughput.
     *
     * @return a PieChart displaying the proportion of vehicles that successfully pass through the traffic network.
     */
    public static PieChart createThroughputChart() {
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Throughput");
        pieChart.getData().addAll(RealisticData.getThroughputData());
        return pieChart;
    }
}