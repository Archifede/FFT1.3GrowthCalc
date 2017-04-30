package data;

/**
 * @author Victor
 * @since 23 avril 23h38
 */
@SuppressWarnings("unused")
public enum Job {
    MIME(8, 8, 90, 40, 40),
    THIEF(10, 16, 80, 45, 55),
    MONK(8, 13, 90, 38, 50),
    ORACLE(8, 9, 90, 40, 40),
    KNIGHT(8, 15, 100, 35, 40),
    WIZARD(12, 8, 100, 50, 35);

    private final int hpC;
    private final int mpC;
    private final int spC;
    private final int paC;
    private final int maC;


    Job(int hpC, int mpC, int spC, int paC, int maC) {
        this.hpC = hpC;
        this.mpC = mpC;
        this.spC = spC;
        this.paC = paC;
        this.maC = maC;
    }


    public int getHpC() {
        return hpC;
    }

    public int getMpC() {
        return mpC;
    }

    public int getSpC() {
        return spC;
    }

    public int getPaC() {
        return paC;
    }

    public int getMaC() {
        return maC;
    }

    @Override
    public String toString() {
        return " | Growths : " +
                "hpC=" + hpC +
                ", mpC=" + mpC +
                ", spC=" + spC +
                ", paC=" + paC +
                ", maC=" + maC
                ;
    }

}
