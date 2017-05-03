package data;

import java.util.Arrays;

/**
 * @author Victor
 * @since 29 avril 13h57
 */
@SuppressWarnings("unused")
public enum Gender {

    MALE(new double[]{491520, 524287}, new double[]{229376, 245759}, 98304, 81920, 65536),
    FEMALE(new double[]{458752, 491519}, new double[]{245760, 262143}, 98304, 65536, 81920),
    RAMZALE(new double[]{491520, 524287}, new double[]{245760, 262143}, 98304, 81920, 81920);

    Gender(double[] rawHps, double[] rawMps, double rawSpeed, double rawPa, double rawMa) {
        this.rawHps = rawHps;
        this.rawMps = rawMps;
        this.rawSpeed = rawSpeed;
        this.rawPa = rawPa;
        this.rawMa = rawMa;
    }

    private final double[] rawHps;
    private final double[] rawMps;
    private final double rawSpeed;
    private final double rawPa;
    private final double rawMa;
    private static boolean firstToStringCall = false;

    public double[] getRawHps() {
        return Arrays.copyOf(rawHps,rawHps.length);
    }

    public double[] getRawMps() {
        return Arrays.copyOf(rawMps,rawMps.length);
    }

    public double getRawSpeed() {
        return rawSpeed;
    }

    public double getRawPa() {
        return rawPa;
    }

    public double getRawMa() {
        return rawMa;
    }


    @Override
    public String toString() {

        return " | "+
                "Hp{Min-Max}={" + (int) rawHps[0] + "-" + (int) rawHps[1] +
                "}" + " Mp{Min-Max}={" + (int) rawMps[0] + "-" + (int) rawMps[1] +
                "} Speed=" + (int) rawSpeed +
                " Pa=" + (int) rawPa +
                " Ma=" + (int) rawMa;
    }
}
