package console;

import data.Gender;
import data.Job;
import data.Params;
import logic.Calculus;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

import static data.Params.MESSAGE;


/**
 * @author Victor
 * @since 29 avril 18h09
 */
public class Console {


    public void compute() {

        System.out.println(MESSAGE);
        Scanner scanner = new Scanner(System.in);
        int level = Params.STARTING_LEVEL;
        double rawSpeed;
        double rawHp;
        double rawMp;
        double rawPa;
        double rawMa;

        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("----- Gender pick and raw values for a level 1 character (Hp and Mp values will be prompted later since they are random at char creation) -----");
        Gender selectedGender = this.select(Gender.class, scanner);

        System.out.println("---------------------------------------------");
        System.out.println("JOB AND STATS (low C value is stronger)");

        Job selectedJob = this.select(Job.class, scanner);


        rawHp = inputRawValue("Hp", scanner);
        rawMp = inputRawValue("Mp", scanner);

        rawSpeed = selectedGender.getRawSpeed();
        rawPa = selectedGender.getRawPa();
        rawMa = selectedGender.getRawMa();

        double finalRawHp = Calculus.computeStatTo99(rawHp, level, selectedJob.getHpC());
        double finalRawMp = Calculus.computeStatTo99(rawMp, level, selectedJob.getMpC());
        double finalRawSpeed = Calculus.computeStatTo99(rawSpeed, level, selectedJob.getSpC());
        double finalRawPa = Calculus.computeStatTo99(rawPa, level, selectedJob.getPaC());
        double finalRawMa = Calculus.computeStatTo99(rawMa, level, selectedJob.getMaC());

        System.out.println();
        System.out.println("Here are the final 99 raw values, leveled as a " + selectedGender.name().toLowerCase() + " " + selectedJob.name().toLowerCase() + ", please enter them in FFTastic under \"Real Stat\" and press \"Set\"");
        System.out.println("Hp : " + (int) finalRawHp + "\n" +
                "Mp : " + (int) finalRawMp + "\n" +
                "Sp : " + (int) finalRawSpeed + "\n" +
                "Pa : " + (int) finalRawPa + "\n" +
                "Ma : " + (int) finalRawMa + "\n"
        );
    }


    private <T extends Enum<T>> T select(Class<T> clazz, Scanner scanner) {
        T[] enumm = clazz.getEnumConstants();
        for (Enum t : enumm) {
            System.out.print("For " +t.name() + " : press \'" + t.ordinal()+"\' and return");
            System.out.println(t);
        }
        System.out.print("\n>Please enter your choice : ");
        int enummNum = inputChoice(scanner, enumm);
        return enumm[enummNum];
    }

    private <T extends Enum<T>> int inputChoice(Scanner scanner, Enum<T>[] enumm) {
        int choiceNum = 0;
        String scanned = scanner.next();
        if (NumberUtils.isNumber(scanned))
            choiceNum = Integer.parseInt(scanned);
        while (!((NumberUtils.isNumber(scanned)) && (choiceNum < enumm.length))) {
            System.out.println(">Error");
            for (Enum t : enumm) {
                System.out.print("For " +t.name() + " : press \'" + t.ordinal()+"\' and return");
                System.out.println(t);
            }

            System.out.print("\n>Please enter choice : ");
            scanned = scanner.next();
            if (NumberUtils.isNumber(scanned))
                choiceNum = Integer.parseInt(scanned);

        }
        return choiceNum;
    }

    private double inputRawValue(String stat, Scanner scanner) {
        String scanned;
        System.out.println(">Please enter Raw" + stat + " as displayed under \"Real Stat\" in FFTastic(no commas)");
        if (stat.equals("Hp"))
            System.out.println(">Example : 516363");
        if (stat.equals("Mp"))
            System.out.println(">Example : 233414");
        System.out.print(">>Your input : ");
        scanned = scanner.next();
        while (!NumberUtils.isNumber(scanned)) {
            System.out.println(">Error, please enter Raw " + stat + " again, as displayed under \"Real Stat\" in FFTastic(no commas)");
            scanned = scanner.next();
        }
        return Double.parseDouble(scanned);
    }
}
