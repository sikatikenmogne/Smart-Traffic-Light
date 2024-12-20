package Objects.Conditions;

import Objects.CrossroadInfo.CrossroadInfo;
import Objects.CrossroadInfo.DirectionInfo.DirectionInfo;
import SystemSTL.TrafficComputation.Lane.LaneInfo;
import Tools.ConsoleColors;
import Tools.Constants;
import Tools.Utils;
import javafx.scene.chart.XYChart;

import java.util.*;

/**
 * This class stores all the information about the two intersections that will be used in the simulation.
 */
public class Conditions {

    private CrossroadInfo first_crossroad_info;
    private CrossroadInfo second_crossroad_info;

    private ArrayList<LaneInfo> lanes_info_first_crossroad;
    private ArrayList<LaneInfo> lanes_info_second_crossroad;

    private double algorithm_duration;
    private double initial_duration;
    private double simulation_duration;
    private int phases_count;

    private double initial_awt;
    private double algorithm_awt;

    private Queue<Double> better_distribution;
    private String better_distribution_string;

    /**
     * Constructor.
     * The constructor receives information from the intersection and creates a suitable structure for the calculation algorithm.
     *
     * @param info1 - crossroad 1
     * @param info2 - crossroad 2
     */
    public Conditions(CrossroadInfo info1, CrossroadInfo info2) {
        first_crossroad_info = info1;
        second_crossroad_info = info2;
        this.better_distribution = new ArrayDeque<>();
        better_distribution_string = "";
        createLanesInfo();
    }

    /**
     * Copy constructor.
     *
     * @param conditions - the Conditions object to copy
     */
    public Conditions(Conditions conditions) {
        first_crossroad_info = new CrossroadInfo(conditions.getFirstCrossroadInfo());
        second_crossroad_info = new CrossroadInfo(conditions.getSecondCrossroadInfo());
        createLanesInfo();
    }

    /**
     * This function changes the state of traffic lights. Changes appear in two crossroads.
     */
    public void changeTrafficLightState() {
        first_crossroad_info.getCrossroad().changeTrafficLightStateOnCrossroad();
        second_crossroad_info.getCrossroad().changeTrafficLightStateOnCrossroad();
    }

    /**
     * This function checks if all vehicles have passed these intersections.
     *
     * @return true if all cars have passed, false otherwise
     */
    public boolean isAllCarsPassed() {
        for (LaneInfo lane_info : lanes_info_first_crossroad) {
            if (lane_info.getCarsInLane().size() > 0) {
                return false;
            }
        }
        for (LaneInfo lane_info : lanes_info_second_crossroad) {
            if (lane_info.getCarsInLane().size() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * This function returns true if the east-west direction is currently active.
     *
     * @return true if east-west direction is active, false otherwise
     */
    public boolean isEastWestActive() {
        return first_crossroad_info.getCrossroad().isEastWestActive() && second_crossroad_info.getCrossroad().isEastWestActive();
    }

    /**
     * This function returns true if the north-south direction is currently active.
     *
     * @return true if north-south direction is active, false otherwise
     */
    public boolean isNorthSouthActive() {
        return first_crossroad_info.getCrossroad().isNorthSouthActive() && second_crossroad_info.getCrossroad().isNorthSouthActive();
    }

    /**
     * This function returns the duration of a north-south traffic light.
     *
     * @return the duration of the north-south traffic light
     */
    public double getNorthSouthTimeDistribution() {
        if (first_crossroad_info.getCrossroad().getTimeDistribution().getNorthSouth() !=
                second_crossroad_info.getCrossroad().getTimeDistribution().getNorthSouth()) {
            throw new RuntimeException("Bad time distribution...");
        }
        return first_crossroad_info.getCrossroad().getTimeDistribution().getNorthSouth();
    }

    /**
     * This function returns the duration of an east-west traffic light.
     *
     * @return the duration of the east-west traffic light
     */
    public double getEastWestTimeDistribution() {
        if (first_crossroad_info.getCrossroad().getTimeDistribution().getEastWest() !=
                second_crossroad_info.getCrossroad().getTimeDistribution().getEastWest()) {
            throw new RuntimeException("Bad time distribution...");
        }
        return first_crossroad_info.getCrossroad().getTimeDistribution().getEastWest();
    }

    /**
     * This function returns the change time of the traffic light.
     * Color change is the time when the traffic light needs to switch between the following colors.
     *
     * @return the time of changing
     */
    public double getChangingLightsTime() {
        return first_crossroad_info.getCrossroad().getTimeDistribution().getChangingExecutionTime();
    }

    /**
     * This function adds working time to the east-west direction.
     */
    public void addTimeToEastWestRoute() {
        first_crossroad_info.getCrossroad().addTimeToEastWestRoute();
        second_crossroad_info.getCrossroad().addTimeToEastWestRoute();

        first_crossroad_info.getCrossroad().getTimeDistribution().printTimeDistributionAfterChange();
    }

    /**
     * This function adds working time to the north-south direction.
     */
    public void addTimeToNorthSouthRoute() {
        first_crossroad_info.getCrossroad().addTimeToNorthSouthRoute();
        second_crossroad_info.getCrossroad().addTimeToNorthSouthRoute();

        first_crossroad_info.getCrossroad().getTimeDistribution().printTimeDistributionAfterChange();
    }

    /**
     * This function sets distribution time to each crossroad.
     * When the north-south time changes, the time in the opposite direction (east-west) changes automatically.
     *
     * @param north_south_time - the north-south time to set
     */
    public void setTimeDistribution(double north_south_time) {
        first_crossroad_info.getCrossroad().setTimeDistribution(north_south_time);
        second_crossroad_info.getCrossroad().setTimeDistribution(north_south_time);

        first_crossroad_info.getCrossroad().getTimeDistribution().printTimeDistributionAfterChange();
    }

    /**
     * This function sets the default time distribution on the crossroads.
     */
    public void setDefaultTimeDistribution() {
        first_crossroad_info.getCrossroad().getTimeDistribution().setDefaultDistribution();
        second_crossroad_info.getCrossroad().getTimeDistribution().setDefaultDistribution();

        first_crossroad_info.getCrossroad().getTimeDistribution().printTimeDistributionAfterChange();
    }

    /**
     * This function generates information of crossroads for algorithm calculation.
     */
    private void createLanesInfo() {
        lanes_info_first_crossroad = new ArrayList<>();
        createLanesPerCrossroad(lanes_info_first_crossroad, first_crossroad_info);

        lanes_info_second_crossroad = new ArrayList<>();
        createLanesPerCrossroad(lanes_info_second_crossroad, second_crossroad_info);
    }

    /**
     * This function generates information for the crossroad about its vehicles.
     *
     * @param cars             - list to store lane information
     * @param actual_crossroad - specific crossroad
     */
    private void createLanesPerCrossroad(ArrayList<LaneInfo> cars, CrossroadInfo actual_crossroad) {
        cars.add(createLaneInfo(actual_crossroad.getNorth()));
        cars.add(createLaneInfo(actual_crossroad.getEast()));
        cars.add(createLaneInfo(actual_crossroad.getSouth()));
        cars.add(createLaneInfo(actual_crossroad.getWest()));
    }

    /**
     * This function creates lane information for a specific direction.
     *
     * @param direction_info - the direction information
     * @return the lane information
     */
    private LaneInfo createLaneInfo(DirectionInfo direction_info) {
        return new LaneInfo(direction_info.getCarsCount(), direction_info.getSpeedLimit(), direction_info.getActualSpeed());
    }

    /**
     * Gets the first crossroad information.
     *
     * @return the first crossroad information
     */
    public CrossroadInfo getFirstCrossroadInfo() {
        return first_crossroad_info;
    }

    /**
     * Gets the second crossroad information.
     *
     * @return the second crossroad information
     */
    public CrossroadInfo getSecondCrossroadInfo() {
        return second_crossroad_info;
    }

    /**
     * Gets the lane information for the first crossroad.
     *
     * @return the lane information for the first crossroad
     */
    public ArrayList<LaneInfo> getLanesInfoFirstCrossroad() {
        return lanes_info_first_crossroad;
    }

    /**
     * Gets the lane information for the second crossroad.
     *
     * @return the lane information for the second crossroad
     */
    public ArrayList<LaneInfo> getLanesInfoSecondCrossroad() {
        return lanes_info_second_crossroad;
    }

    /**
     * Gets the lane information for the north direction of the first crossroad.
     *
     * @return the lane information for the north direction of the first crossroad
     */
    public LaneInfo getLaneInfoNorthFirstCrossroad() {
        return lanes_info_first_crossroad.get(Constants.NORTH_DIRECTION);
    }

    /**
     * Gets the lane information for the east direction of the first crossroad.
     *
     * @return the lane information for the east direction of the first crossroad
     */
    public LaneInfo getLaneInfoEastFirstCrossroad() {
        return lanes_info_first_crossroad.get(Constants.EAST_DIRECTION);
    }

    /**
     * Gets the lane information for the south direction of the first crossroad.
     *
     * @return the lane information for the south direction of the first crossroad
     */
    public LaneInfo getLaneInfoSouthFirstCrossroad() {
        return lanes_info_first_crossroad.get(Constants.SOUTH_DIRECTION);
    }

    /**
     * Gets the lane information for the west direction of the first crossroad.
     *
     * @return the lane information for the west direction of the first crossroad
     */
    public LaneInfo getLaneInfoWestFirstCrossroad() {
        return lanes_info_first_crossroad.get(Constants.WEST_DIRECTION);
    }

    /**
     * Gets the lane information for the north direction of the second crossroad.
     *
     * @return the lane information for the north direction of the second crossroad
     */
    public LaneInfo getLaneInfoNorthSecondCrossroad() {
        return lanes_info_second_crossroad.get(Constants.NORTH_DIRECTION);
    }

    /**
     * Gets the lane information for the east direction of the second crossroad.
     *
     * @return the lane information for the east direction of the second crossroad
     */
    public LaneInfo getLaneInfoEastSecondCrossroad() {
        return lanes_info_second_crossroad.get(Constants.EAST_DIRECTION);
    }

    /**
     * Gets the lane information for the south direction of the second crossroad.
     *
     * @return the lane information for the south direction of the second crossroad
     */
    public LaneInfo getLaneInfoSouthSecondCrossroad() {
        return lanes_info_second_crossroad.get(Constants.SOUTH_DIRECTION);
    }

    /**
     * Gets the lane information for the west direction of the second crossroad.
     *
     * @return the lane information for the west direction of the second crossroad
     */
    public LaneInfo getLaneInfoWestSecondCrossroad() {
        return lanes_info_second_crossroad.get(Constants.WEST_DIRECTION);
    }

    /**
     * Gets the count of cars in the east-west direction.
     *
     * @return the count of cars in the east-west direction
     */
    public int getEastWestCarsCount() {
        return calculateCarsCountForDirections(false);
    }

    /**
     * Gets the count of cars in the north-south direction.
     *
     * @return the count of cars in the north-south direction
     */
    public int getNorthSouthCarsCount() {
        return calculateCarsCountForDirections(true);
    }

    /**
     * Calculates the count of cars for the specified directions.
     *
     * @param is_north_south - true if calculating for north-south directions, false for east-west
     * @return the count of cars for the specified directions
     */
    private int calculateCarsCountForDirections(boolean is_north_south) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            if (is_north_south) {
                if (i == Constants.NORTH_DIRECTION || i == Constants.SOUTH_DIRECTION) {
                    count += lanes_info_first_crossroad.get(i).getCarsInLane().size();
                    count += lanes_info_second_crossroad.get(i).getCarsInLane().size();
                }
            } else {
                if (i == Constants.EAST_DIRECTION || i == Constants.WEST_DIRECTION) {
                    count += lanes_info_first_crossroad.get(i).getCarsInLane().size();
                    count += lanes_info_second_crossroad.get(i).getCarsInLane().size();
                }
            }
        }

        return count;
    }

    /**
     * Gets the better distribution queue.
     *
     * @return the better distribution queue
     */
    public Queue<Double> getBetterDistribution() {
        return better_distribution;
    }

    /**
     * Gets the next better distribution time from the queue of better distribution times.
     *
     * @return the next better distribution time
     */
    public double getNextDistribution() {
        return better_distribution.poll();
    }

    /**
     * Sets the better distribution times from a string and adds them to the queue.
     *
     * @param path - string of better distribution times (e.g., ->12:8->10:10->14:6)
     */
    public void setBetterDistribution(String path) {
        better_distribution_string = path;
        algorithm_duration = Utils.calculateDurationFromString(path, first_crossroad_info.getPhaseTime());
        System.out.println(ConsoleColors.CYAN + "Algorithm time of passing all cars: " + Utils.calculateDurationFromString(path, first_crossroad_info.getPhaseTime()) + ConsoleColors.RESET);
        this.better_distribution.clear();
        Utils.addBetterDistributionToQueue(this.better_distribution, path);
    }

    /**
     * Gets the algorithm duration.
     *
     * @return the algorithm duration
     */
    public double getAlgorithmDuration() {
        return algorithm_duration;
    }

    /**
     * Gets the initial duration.
     *
     * @return the initial duration
     */
    public double getInitialDuration() {
        return initial_duration;
    }

    /**
     * Sets the initial duration of the chosen conditions.
     *
     * @param initial_duration - real initial time without smart algorithm
     */
    public void setInitialDuration(double initial_duration) {
        System.out.println(ConsoleColors.CYAN + "Initial time of passing all cars: " + initial_duration + " seconds." + ConsoleColors.RESET);
        this.initial_duration = initial_duration;
    }

    /**
     * Gets the simulation duration.
     *
     * @return the simulation duration
     */
    public double getSimulationDuration() {
        return simulation_duration;
    }

    /**
     * Sets the simulation duration for all vehicles to pass through the intersection and displays its value.
     *
     * @param simulation_duration - the simulation duration
     */
    public void setSimulationDuration(double simulation_duration) {
        System.out.println(ConsoleColors.RED_BOLD + "Simulation working time: " + simulation_duration + " seconds" + ConsoleColors.RESET);
        this.simulation_duration = simulation_duration;
    }

    /**
     * Gets the better distribution string.
     *
     * @return the better distribution string
     */
    public String getBetterDistributionString() {
        return better_distribution_string;
    }

    /**
     * Gets the initial average waiting time (AWT).
     *
     * @return the initial AWT
     */
    public double getInitialAWT() {
        return initial_awt;
    }

    /**
     * Sets the initial average waiting time (AWT) and displays its value.
     *
     * @param initial_awt - the initial AWT
     */
    public void setInitialAWT(double initial_awt) {
        this.initial_awt = initial_awt;
        System.out.println(ConsoleColors.CYAN + "Initial time of AWT: " + initial_awt + " seconds." + ConsoleColors.RESET);
    }

    /**
     * Gets the algorithm average waiting time (AWT).
     *
     * @return the algorithm AWT
     */
    public double getAlgorithmAWT() {
        return algorithm_awt;
    }

    /**
     * Sets the algorithm average waiting time (AWT) and displays its value.
     *
     * @param better_awt - the algorithm AWT
     */
    public void setAlgorithmAWT(double better_awt) {
        this.algorithm_awt = better_awt;
        System.out.println(ConsoleColors.CYAN + "Algorithm time of AWT: " + algorithm_awt + " seconds" + ConsoleColors.RESET);
    }

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
     * Gets the phase time.
     *
     * @return the phase time
     */
    public double getPhaseTime() {
        return first_crossroad_info.getPhaseTime();
    }

    /**
     * Gets the number of phases.
     *
     * @return the number of phases
     */
    public int getPhasesCount() {
        return phases_count;
    }

    /**
     * Sets the actual number of phases to resolve the actual state.
     * Also checks if it is able to update the best time.
     *
     * @param phases_count - the number of phases found
     */
    public void setPhasesCount(int phases_count) {
        this.phases_count = phases_count;
        double best_time = Utils.calculateDurationByPhasesCount(phases_count, first_crossroad_info.getPhaseTime());
        if (best_time < algorithm_duration) {
            algorithm_duration = best_time;
        }
        System.out.println(algorithm_duration);
    }
}