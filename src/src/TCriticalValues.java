import java.util.ArrayList;
import java.util.List;

public class TCriticalValues {

    private final List<List<Double>> criticalValues = new ArrayList<>();

    public TCriticalValues() {
        List<Double> level0_10 = new ArrayList<>();
        List<Double> level0_05 = new ArrayList<>();
        List<Double> level0_025 = new ArrayList<>();
        List<Double> level0_01 = new ArrayList<>();
        List<Double> level0_005 = new ArrayList<>();

        double[][] values = {
                {1, 6.314, 12.706, 25.452, 63.657, 127.321},
                {2, 2.920, 4.303, 6.205, 9.925, 14.089},
                {3, 2.353, 3.182, 4.177, 5.841, 7.453},
                {4, 2.132, 2.776, 3.495, 4.604, 5.598},
                {5, 2.015, 2.571, 3.163, 4.032, 4.773},
                {6, 1.943, 2.447, 2.968, 3.707, 4.317},
                {7, 1.895, 2.365, 2.841, 3.499, 4.029},
                {8, 1.860, 2.306, 2.752, 3.355, 3.833},
                {9, 1.833, 2.262, 2.685, 3.250, 3.690},
                {10, 1.812, 2.228, 2.634, 3.169, 3.581},
                {11, 1.796, 2.201, 2.593, 3.106, 3.497},
                {12, 1.782, 2.179, 2.560, 3.055, 3.428},
                {13, 1.771, 2.160, 2.533, 3.012, 3.372},
                {14, 1.761, 2.145, 2.510, 2.977, 3.326},
                {15, 1.753, 2.131, 2.490, 2.947, 3.286},
                {16, 1.746, 2.120, 2.473, 2.921, 3.252},
                {17, 1.740, 2.110, 2.458, 2.898, 3.222},
                {18, 1.734, 2.101, 2.445, 2.878, 3.197},
                {19, 1.729, 2.093, 2.433, 2.861, 3.174},
                {20, 1.725, 2.086, 2.423, 2.845, 3.153},
                {21, 1.721, 2.080, 2.414, 2.831, 3.135},
                {22, 1.717, 2.074, 2.405, 2.819, 3.119},
                {23, 1.714, 2.069, 2.398, 2.807, 3.104},
                {24, 1.711, 2.064, 2.391, 2.797, 3.091},
                {25, 1.708, 2.060, 2.385, 2.787, 3.078},
                {26, 1.706, 2.056, 2.379, 2.779, 3.067},
                {27, 1.703, 2.052, 2.374, 2.771, 3.057},
                {28, 1.701, 2.048, 2.369, 2.763, 3.047},
                {29, 1.699, 2.045, 2.364, 2.756, 3.038},
                {30, 1.697, 2.042, 2.360, 2.750, 3.030}
        };

        for (double[] row : values) {
            level0_10.add(row[1]);
            level0_05.add(row[2]);
            level0_025.add(row[3]);
            level0_01.add(row[4]);
            level0_005.add(row[5]);
        }

        criticalValues.add(level0_10);
        criticalValues.add(level0_05);
        criticalValues.add(level0_025);
        criticalValues.add(level0_01);
        criticalValues.add(level0_005);
    }

    public List<List<Double>> getCriticalValues() {
        return criticalValues;
    }

    public List<Double> getLevel0_10() {
        return criticalValues.get(0);
    }

    public List<Double> getLevel0_05() {
        return criticalValues.get(1);
    }

    public List<Double> getLevel0_025() {
        return criticalValues.get(2);
    }

    public List<Double> getLevel0_01() {
        return criticalValues.get(3);
    }

    public List<Double> getLevel0_005() {
        return criticalValues.get(4);
    }
}
