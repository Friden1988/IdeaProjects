package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.max;

//import java.io.File;
//import java.util.Scanner;

public class Main {
    private static String originalText = null;
    public static int getSentences(final String word) {
        return word.split("[!?.]")
                    .length;
    }
    public static int getWord(final String word) {
        return word.replaceAll("[!?.]", "")
                   .split(" ")
                   .length;
    }
    public static int getChar(final String word) {
        return word.replaceAll(" ", "")
                   .split("")
                   .length;
    }
    public static int getSyllables(final String word) {
        return max(1, word.toLowerCase()
                .replaceAll("e\\b", "")
                .replaceAll("you", "a")
                .replaceAll("[aeiouy]{2}", "a")
                .replaceAll(" th "," a ")
                .replaceAll(",","")
                .replaceAll(" w "," a ")
                .replaceAll("[0-9]+", "a")
                .replaceAll("[^aeiouy]", "")
                .length());
    }
    public static int getPolysyllables(final String word) {
        Pattern pattern = Pattern.compile(".?[aeiouy].+[aeiouy].+[aeiouy].?");
        int count = 0;
        String[] res = word.toLowerCase()
                .replaceAll("(e\\b)", "")
                .replaceAll("(you)( th )( w )([0-9]+)([aeiouy]{2})", "a")
                .replaceAll("[!,.]","")
                .split(" ");
        for (String re : res) {
            Matcher matcher = pattern.matcher(re);
            if (matcher.lookingAt()) count++;
        }
        return count;
    }
    public static double getTestFKR() {
        double words = getWord(originalText);
        double sentences = getSentences(originalText);
        double syllables = getSyllables(originalText);
        return (0.39 * ( words / sentences )) + ((11.8 * ( syllables / words )) - 15.59);
    }
    public static double getTestSMOG() {
        double sentences = getSentences(originalText);
        double polysyllables = getPolysyllables(originalText);
        return (1.043 * Math.sqrt(polysyllables * (30 / sentences))) + 3.1291;
    }
    public static double getTestCLI() {
        double words = getWord(originalText);
        double sentences = getSentences(originalText);
        double characters = getChar(originalText);
        double s = sentences / words * 100;
        double l = characters / words * 100;
        return (0.0588 * l) - (0.296 * s) - 15.8;
    }
    public static double getTestAri() {
        double words = getWord(originalText);
        double sentences = getSentences(originalText);
        double characters = getChar(originalText);
        return (4.71 * (characters / words)) + (0.5 * (words / sentences)) - 21.43;
    }
    public static double getResult() {
        return (Year.find(getTestAri()) + Year.find(getTestFKR()) + Year.find(getTestSMOG()) + Year.find(getTestCLI())) / 4.0;
    }
    public static void getAll() {
        System.out.printf("\nAutomated Readability Index: %.2f (about %d year olds).", getTestAri(), Year.find(getTestAri()));
        System.out.printf("\nFlesch–Kincaid readability tests: %.2f (about %d year olds).", getTestFKR(), Year.find(getTestFKR()));
        System.out.printf("\nSimple Measure of Gobbledygook: %.2f (about %d year olds).", getTestSMOG(), Year.find(getTestSMOG()));
        System.out.printf("\nColeman–Liau index: %.2f (about %d year olds).", getTestCLI(), Year.find(getTestCLI()));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            originalText = new String(Files.readAllBytes(Paths.get(args[0])));
        } catch (IOException  FileNotFoundException) {
            System.out.println("Файл не найден или ошибка ввода!");
        }
        System.out.println("The text is: \n" + originalText + "\n" +
                            "Words: " + getWord(originalText) + "\n" +
                            "Sentences: " + getSentences(originalText) + "\n" +
                            "Characters: " + getChar(originalText) + "\n" +
                            "Syllables: " + getSyllables(originalText) + "\n" +
                            "Polysyllables: " + getPolysyllables(originalText));
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");

        String test;
        Pattern pattern = Pattern.compile("ARI|FK|SMOG|CL|all");
        Matcher matcher;
        while (true) {
            test = scanner.next();
            matcher = pattern.matcher(test);
            if (matcher.lookingAt()) {
                switch (test) {
                    case ("ARI") : System.out.printf("\nAutomated Readability Index: %.2f (about %d year olds).", getTestAri(), Year.find(getTestAri())); break;
                    case ("FK")  : System.out.printf("\nFlesch–Kincaid readability tests: %.2f (about %d year olds).", getTestFKR(), Year.find(getTestFKR())); break;
                    case ("SMOG"): System.out.printf("\nSimple Measure of Gobbledygook: %.2f (about %d year olds).", getTestSMOG(), Year.find(getTestSMOG())); break;
                    case ("CL") :  System.out.printf("\nColeman–Liau index: %.2f (about %d year olds).", getTestCLI(), Year.find(getTestCLI())); break;
                    case ("all") : getAll(); break;
                    default: break;
                }
                break;
            } else {
                System.out.println("Неверный ввод, повторите!");
            }
        }

        System.out.printf("\n\nThis text should be understood in average by %.2f year olds.", getResult());
    }
}