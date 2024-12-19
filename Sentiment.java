import java.util.*;
import java.io.*;

public class Sentiment {

    // Method to load words from a given file into a HashSet
    public static HashSet<String> loadWords(String filename) {
        HashSet<String> words = new HashSet<>();
        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                // Skip comment lines (lines starting with ;)
                if (!line.startsWith(";") && !line.isEmpty()) {
                    words.add(line.toLowerCase());
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + filename);
        }
        return words;
    }

    // Method to perform sentiment analysis on a given text file
    public static void analyzeSentiment(String filename, HashSet<String> positiveWords, HashSet<String> negativeWords) {
        int positiveCount = 0;
        int negativeCount = 0;
        int totalCount = 0;

        try {
            Scanner fileScanner = new Scanner(new File(filename));
            while (fileScanner.hasNext()) {
                String word = fileScanner.next().replaceAll("[^a-zA-Z]", "").toLowerCase(); // Remove punctuation
                if (!word.isEmpty()) {
                    totalCount++;
                    if (positiveWords.contains(word)) {
                        positiveCount++;
                    } else if (negativeWords.contains(word)) {
                        negativeCount++;
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + filename);
            return;
        }

        // Calculate percentages
        double positivePercentage = (double) positiveCount / totalCount * 100;
        double negativePercentage = (double) negativeCount / totalCount * 100;

        // Determine sentiment
        String sentiment;
        if (positivePercentage >= negativePercentage + 5) {
            sentiment = "positive";
        } else if (negativePercentage >= positivePercentage + 5) {
            sentiment = "negative";
        } else {
            sentiment = "neutral";
        }

        // Output the analysis
        System.out.println("\nSentiment Report for " + filename + ":");
        System.out.println("There were " + positiveCount + " positive words, " + negativeCount + " negative words and " + totalCount + " total words.");
        System.out.printf("That's %.0f%% positive and %.0f%% negative.  Overall the file's sentiment was %s.\n", positivePercentage, negativePercentage, sentiment);
    }

    // Start method to run sentiment analysis process
    public static void start(Scanner scnr) {
        System.out.println("\nSentiment Analysis.");

        // Load positive and negative words from files
        HashSet<String> positiveWords = loadWords("positive.txt");
        HashSet<String> negativeWords = loadWords("negative.txt");

        System.out.println(positiveWords.size() + " Positive Words Successfully Loaded.");
        System.out.println(negativeWords.size() + " Negative Words Successfully Loaded.");

        // Loop for analyzing multiple files
        boolean continueAnalyzing = true;
        while (continueAnalyzing) {
            System.out.print("\nEnter the name of the text file to perform sentiment analysis: ");
            String filename = scnr.nextLine().trim();
            analyzeSentiment(filename, positiveWords, negativeWords);

            System.out.print("\nWould you like to analyze another file Y/N? ");
            String response = scnr.nextLine().trim().toUpperCase();
            if (response.equals("N")) {
                continueAnalyzing = false;
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        start(scnr); // Start the Sentiment Analysis
        scnr.close();
    }
}
