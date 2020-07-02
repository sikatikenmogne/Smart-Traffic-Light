package Objects.TrafficLight.TrafficLightTimeDistribution;

public class TimeDistribution {

    private double north_south;
    private double east_west;
    private double round_time;
    private double min_time;
    private double changing_execution_time;


    public TimeDistribution(double time) {
        round_time = time;
        min_time = 10;
        north_south = round_time / 2;
        east_west = round_time / 2;
        changing_execution_time = 2;
    }

    public void addTimeToEastWestRoute() {
        if (north_south > min_time) {
            east_west++;
            north_south--;
        }
    }

    public void addTimeToNorthSouthRoute() {
        if (east_west > min_time) {
            north_south++;
            east_west--;
        }
    }

    public void setEastWest(int time) {
        if (time > round_time || time < min_time) {
            System.err.println("Set north-south new time failed...");
        } else {
            east_west = time;
            north_south = round_time - east_west;
        }
    }

    public void setNorthSouth(int time) {
        if (time > round_time || time < min_time) {
            System.err.println("Set north-south new time failed...");
        } else {
            north_south = time;
            east_west = round_time - north_south;
        }
    }

    public double getNorthSouth() {
        return north_south;
    }

    public double getEastWest() {
        return east_west;
    }

    public double getMinTime() {
        return min_time;
    }

    public double getChangingExecutionTime() {
        return changing_execution_time;
    }
}