package Tools;

import Objects.Car.Car;

import java.util.Queue;

public class Formulas {
    public static double convertKMpHtoMpS(double speed) {
        return speed * (1000 / 3600);
    }

    public static double convertMpStoKMpH(double speed) {
        return speed * (3600 / 1000);
    }

    public double calculateSpeed(double acceleration, double time) {
        return acceleration * time;
    }

    public double calculateDistance(double speed, double time) {
        return speed * time;
    }


    /**
     * @param N_lane - number of vehicles in the lane
     * @return - start time for lane
     */
    public static double T_start(int N_lane) {
        return 0.5 * (double) N_lane;
    }

    /**
     * @param V_last_vehicle - is the velocity for the last vehicle in the lane’s queue.
     * @param A_last_vehicle - is the acceleration for the last vehicle in the lane’s queue.
     * @param D_last_vehicle - is the vehicle’s distance to the center of the intersection.
     * @return - time to move intersection for the all vehicles in the lane
     */
    public static double T_move(double V_last_vehicle, double A_last_vehicle, double D_last_vehicle) {
        return -(V_last_vehicle / A_last_vehicle)
                + Math.sqrt(Math.pow(V_last_vehicle / A_last_vehicle, 2) + 2 * D_last_vehicle / A_last_vehicle);
    }

    /**
     * Time of opposite lane if there are vehicle that must to turn.
     *
     * @param N_opposite - Is the amount of vehicles from the opposite road that the last vehicle must wait for.
     *                   This is 0 if the vehicle is not going left.
     * @return - time in opposite lane.
     */
    public static double T_opossite(int N_opposite) {
        return 0.1 * (double) N_opposite;
    }

    /**
     * @param t_start    - start time of lane
     * @param t_move     - time of moving
     * @param t_opposite - time of the opposite lane
     * @return - common time of intersection
     */
    public static double T_common(double t_start, double t_move, double t_opposite) {
        return t_start + t_move + t_opposite;
    }

    //average squared waiting time
    public static double ASWT(Queue<Car> cars) {
        return 0;
    }
}