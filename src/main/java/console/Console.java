package console;

import data.Gender;
import data.Job;
import logic.Calculus;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import static data.Params.MESSAGE;


/**
 * @author Victor
 * @since 29 avril 18h09
 */
public class Console {

    	    	

    public void compute() throws IOException {

        System.out.println(MESSAGE);
        Scanner scanner = new Scanner(System.in);

        double rawSpeed;
        double rawHp;
        double rawMp;
        double rawPa;
        double rawMa;


        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println(" Gender pick (with level 1 raw values as a reminder) ");
        Gender selectedGender = this.select(Gender.class, scanner);

        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("JOB AND STATS (low C value is stronger)");

        Job selectedJob = this.select(Job.class, scanner);

        int level = this.enterLevel(scanner);

        rawHp = inputRawValue("Hp", scanner);
        rawMp = inputRawValue("Mp", scanner);

        switch (level) {
            case 1:
                rawSpeed = selectedGender.getRawSpeed();
                rawPa = selectedGender.getRawPa();
                rawMa = selectedGender.getRawMa();
                break;
            default:
                rawSpeed = inputRawValue("Sp", scanner);
                rawPa = inputRawValue("Pa", scanner);
                rawMa = inputRawValue("Ma", scanner);
                break;
        }


        double finalRawHp = Calculus.computeStatTo99(rawHp, level, selectedJob.getHpC());
        double finalRawMp = Calculus.computeStatTo99(rawMp, level, selectedJob.getMpC());
        double finalRawSpeed = Calculus.computeStatTo99(rawSpeed, level, selectedJob.getSpC());
        double finalRawPa = Calculus.computeStatTo99(rawPa, level, selectedJob.getPaC());
        double finalRawMa = Calculus.computeStatTo99(rawMa, level, selectedJob.getMaC());

        System.out.println();
        System.out.println("Here are the final 99 raw values, leveled as a " + selectedGender.name().toLowerCase() + " " + selectedJob.name().toLowerCase() + ", you may now enter them in FFTastic under \"Real Stat\" and press \"Set\"");
        System.out.println("Hp : " + (int) finalRawHp + "\n" +
                "Mp : " + (int) finalRawMp + "\n" +
                "Sp : " + (int) finalRawSpeed + "\n" +
                "Pa : " + (int) finalRawPa + "\n" +
                "Ma : " + (int) finalRawMa + "\n"
        );
        try {
            PrintWriter writer = new PrintWriter(selectedGender.name().toLowerCase() + selectedJob.name().toLowerCase() + level + "to" + 99 + ".txt", "UTF-8");
            writer.println("Hp : " + (int) finalRawHp + "\n" +
                    "Mp : " + (int) finalRawMp + "\n" +
                    "Sp : " + (int) finalRawSpeed + "\n" +
                    "Pa : " + (int) finalRawPa + "\n" +
                    "Ma : " + (int) finalRawMa + "\n");

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private int enterLevel(Scanner scanner) {
        String scanned;
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Please enter character level between 1 and 98");
        System.out.print("\n>>Your input : ");
        scanned = scanner.next();
        int level = 0;
        if (NumberUtils.isDigits(scanned))
            level = Integer.parseInt(scanned);
        while (!(NumberUtils.isDigits(scanned) && level > 0 && level < 99)) {
            System.out.println(">Error, please enter character level between 1 and 98");
            scanned = scanner.next();
            if (NumberUtils.isDigits(scanned))
                level = Integer.parseInt(scanned);
        }
        return level;

    }


    @SuppressWarnings("rawtypes")
	private <T extends Enum<T>> T select(Class<T> clazz, Scanner scanner) {
        T[] enumm = clazz.getEnumConstants();
        for (Enum t : enumm) {
            System.out.print("For " + t.name() + " : press \'" + t.ordinal() + "\' and return");
            System.out.println((t));
        }
        System.out.print("\n>>Your input : ");
        int enummNum = inputChoice(scanner, enumm);
        return enumm[enummNum];
    }

    private <T extends Enum<T>> int inputChoice(Scanner scanner, Enum<T>[] enumm) {
        int choiceNum = 0;
        String scanned = scanner.next();
        if (NumberUtils.isDigits(scanned))
            choiceNum = Integer.parseInt(scanned);
        while (!((NumberUtils.isDigits(scanned)) && (choiceNum < enumm.length))) {
            System.out.println(">Error");
            
            for (Enum t : enumm)
            	
                System.out.println(t);

            System.out.print("\n>>Your input : ");
            scanned = scanner.next();
            if (NumberUtils.isDigits(scanned))
                choiceNum = Integer.parseInt(scanned);

        }
        return choiceNum;
    }

    boolean firstInputRaw = true;

    private double inputRawValue(String stat, Scanner scanner) {
        String scanned;
        String reminder = " as displayed under \"Real Stat\" in FFTastic(no commas)";

        System.out.print(">" + (firstInputRaw ? "Please enter " : "Enter ") + "Raw" + stat + (firstInputRaw ? reminder : "") + " :>");
        scanned = scanner.next();
        while (!NumberUtils.isDigits(scanned)) {
            System.out.println(">Error, please enter Raw " + stat + " again, as displayed under \"Real Stat\" in FFTastic(no commas)");
            scanned = scanner.next();
        }
        firstInputRaw = false;
        return Double.parseDouble(scanned);
    }
}
