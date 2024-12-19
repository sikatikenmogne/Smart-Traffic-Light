package Objects.TrafficLight.TrafficLightTimeDistribution;

import Tools.ConsoleColors;
import Tools.Constants;

/**
 * This class represents the time distribution for traffic lights at an intersection.
 */
public class TimeDistribution {

    private double north_south;
    private double east_west;
    private double phase_time;
    private double min_time;
    private double changing_execution_time;

    /**
     * Constructor to initialize the time distribution with a given phase time.
     *
     * @param time the total phase time for the traffic light cycle
     */
    public TimeDistribution(double time) {
        phase_time = time;
        min_time = Constants.TRAFFIC_LIGHT_MIN_DISTRIBUTION;
        setDefaultDistribution();
        changing_execution_time = Constants.TRAFFIC_LIGHT_CHANGING_TIME;
    }

    /**
     * Adds one unit of time to the east-west route and subtracts one unit from the north-south route,
     * ensuring that the north-south time does not fall below the minimum time.
     */
    public void addTimeToEastWestRoute() {
        if (north_south > min_time) {
            east_west++;
            north_south--;
        }
    }

    /**
     * Adds one unit of time to the north-south route and subtracts one unit from the east-west route,
     * ensuring that the east-west time does not fall below the minimum time.
     */
    public void addTimeToNorthSouthRoute() {
        if (east_west > min_time) {
            north_south++;
            east_west--;
        }
    }

    /**
     * Sets the time for the east-west route, ensuring it is within valid bounds.
     *
     * @param time the new time for the east-west route
     */
    public void setEastWest(double time) {
        if (time > phase_time || time < min_time) {
            System.err.println("Set north-south new time failed...");
        } else {
            east_west = time;
            north_south = phase_time - east_west;
        }
    }

    /**
     * Sets the time for the north-south route, ensuring it is within valid bounds.
     *
     * @param time the new time for the north-south route
     */
    public void setNorthSouth(double time) {
        if (time > phase_time || time < min_time) {
            System.err.println("Set north-south new time failed...");
        } else {
            north_south = time;
            east_west = phase_time - north_south;
        }
    }

    /**
     * Sets the default time distribution, splitting the phase time equally between east-west and north-south routes.
     */
    public void setDefaultDistribution() {
        east_west = phase_time / 2;
        north_south = phase_time / 2;
    }

    /**
     * Gets the time allocated to the north-south route.
     *
     * @return the north-south time
     */
    public double getNorthSouth() {
        return north_south;
    }

    /**
     * Gets the time allocated to the east-west route.
     *
     * @return the east-west time
     */
    public double getEastWest() {
        return east_west;
    }

    /**
     * Gets the minimum allowable time for any route.
     *
     * @return the minimum time
     */
    public double getMinTime() {
        return min_time;
    }

    /**
     * Gets the maximum allowable time for any route.
     *
     * @return the maximum time
     */
    public double getMaxTime() {
        return phase_time - min_time;
    }

    /**
     * Gets the total phase time for the traffic light cycle.
     *
     * @return the phase time
     */
    public double getPhaseTime() {
        return phase_time;
    }

    /**
     * Sets the total phase time for the traffic light cycle and updates the default distribution.
     *
     * @param time the new phase time
     */
    public void setPhaseTime(double time) {
        phase_time = time;
        setDefaultDistribution();
        min_time = (int) Math.ceil(phase_time / 2 / 2);
    }

    /**
     * Gets the time required to change the traffic light state.
     *
     * @return the changing execution time
     */
    public double getChangingExecutionTime() {
        return changing_execution_time;
    }

    /**
     * Prints the current time distribution to the console.
     */
    public void printTimeDistributionAfterChange() {
        System.out.println(ConsoleColors.GREEN + "north-south: " + north_south + ", east-west: " + east_west + ConsoleColors.RESET);
    }
}