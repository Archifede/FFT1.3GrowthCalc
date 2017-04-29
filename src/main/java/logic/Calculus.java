package logic;

/**
 * @author Victor
 * @since 23 avril 23h18
 */
public class Calculus {



    private static double levelUpBonus(double rawStat, int level, int c) {
        return rawStat / (c + level);
    }

    public static double computeStatTo99(double rawStat, int level, int c) {
        double addedStat = rawStat;
        while (level < 99) {
            addedStat += levelUpBonus(addedStat, level, c);
            ++level;
        }
        return addedStat;
    }



}
