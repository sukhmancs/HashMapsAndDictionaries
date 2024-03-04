/**
 * Part A: Discussion of the results including an analysis of the performance.
 * the fastest method is the third method, which uses the SimpleHashSet dictionary and the contains method provided
 * the second fastest method is the second method, which uses the binarySearch method of the Collections class (Collections.binarySearch) to search the ArrayList dictionary
 * the slowest method is the first method, which uses the contains method of ArrayList to find matches
 *
 * the third method is the fastest because the contains method of SimpleHashSet is O(1)
 * the second method is the second fastest because the binarySearch method of the Collections class is O(log n)
 * the first method is the slowest because the contains method of ArrayList is O(n)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Main class for Assignment 3
 * This class contains the main method to run the program
 *
 * @author Sukhmanjeet Singh, Student ID 000838215
 */
public class Assignment3 {
    public static void main(String[] args) {
        // read the book War and Peace by Leo Tolstoy
        printNewLines(2);
        List<String> bookWords = readFile("src\\WarAndPeace.txt");
        printNewLines(1);

        //
        // ╔══════════════════════════════════════╗
        // ║   PART A - Question 1 - Question 4   ║
        // ╚══════════════════════════════════════╝
        //
        // Question 1 - count the words in the book War and Peace by Leo Tolstoy
        int wordCount = countWords(bookWords);
        System.out.println("Word count: " + wordCount);

        // Question 2 - count the unique words in the book War and Peace by Leo Tolstoy
        Set<String> uniqueWords = new HashSet<String>(bookWords);
        System.out.println("Unique word count: " + uniqueWords.size());

        // Question 3 - count the occurrences of words in the book War and Peace by Leo Tolstoy
        Map<String, Integer> wordOccurrences = countAllOccurrences(bookWords);

        // Question 4 - list of 15 most frequent words and count of occurrences
        List<Map.Entry<String, Integer>> sortedWordOccurrences = new ArrayList<Map.Entry<String, Integer>>(wordOccurrences.entrySet());
        Collections.sort(sortedWordOccurrences, (a, b) -> {
            if (a.getValue() == b.getValue()) { // if the counts are the same, sort alphabetically
                return a.getKey().compareTo(b.getKey());
            }
            return b.getValue().compareTo(a.getValue()); // sort by count
        });
        System.out.println("15 most frequent words and count of occurrences:");
        System.out.println("---------------");
        System.out.println("Word    Count");
        for (int i = 0; i < 15; i++) {
            System.out.printf("%-7s %d\n", sortedWordOccurrences.get(i).getKey(), sortedWordOccurrences.get(i).getValue());
        }
        ;
        System.out.println("---------------");

        // Question 5 - list of words that occur exactly 41 times in the file. These words must be listed in alphabetical order
        List<String> wordsWithCount41 = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : wordOccurrences.entrySet()) {
            if (entry.getValue() == 41) {
                wordsWithCount41.add(entry.getKey());
            }
        }
        Collections.sort(wordsWithCount41);
        System.out.println("Words that occur exactly 41 times in the file:");
        for (String word : wordsWithCount41) {
            // print words in a single line with , as separator
            System.out.print(word + ", ");
        }

        // read the dictionary
        printNewLines(2);
        List<String> dictionaryWords = readFile("src\\US.txt");
        printNewLines(1);

        // Question 6 - spell checking the document
        // Method 1 - Use the contains method of ArrayList to find matches
        // spell check the document and report how many words are not found in the provided dictionary
        // analyze the performance of each spell check method
        // calculate time took to run the spell check
        long startTime = System.currentTimeMillis();
        int wordsNotInDictionary = countWordsNotInDictionary(bookWords, dictionaryWords);
        long endTime = System.currentTimeMillis();
        System.out.println("Words not in dictionary: " + wordsNotInDictionary);

        // Method 2 - Use the binarySearch method of the Collections class (Collections.binarySearch) to search the ArrayList dictionary
        // spell check the document and report how many words are not found in the provided dictionary
        // analyze the performance of each spell check method
        // calculate time took to run the spell check
        // sort the dictionary before running binary check
        Collections.sort(dictionaryWords);
        long startTime2 = System.currentTimeMillis();
        int wordsNotInDictionary2 = countWordsNotInDictionary2(bookWords, dictionaryWords);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Words not in dictionary 2: " + wordsNotInDictionary2);

        // Method 3 - Use the SimpleHashSet dictionary and the contains method provided
        // spell check the document and report how many words are not found in the provided dictionary
        SimpleHashSet<String> dictionaryWordSetString = new SimpleHashSet<String>();
        for (String dictionaryWord : dictionaryWords) {
            dictionaryWordSetString.insert(dictionaryWord);
        }
        // analyze the performance of each spell check method
        // calculate time took to run the spell check
        long startTime3 = System.currentTimeMillis();
        int wordsNotInDictionary3 = countWordsNotInDictionary3(bookWords, dictionaryWordSetString);
        long endTime3 = System.currentTimeMillis();
        System.out.println("Words not in dictionary 3: " + wordsNotInDictionary3);

        // Performance Measurement
        // analyze the performance of each spell check method
        // calculate time took to run the spell check
        printNewLines(2);
        System.out.println("Performance of each spell check method:");
        System.out.println("Time took to run spell check 1  (List contains): " + (endTime - startTime) + "ms");
        System.out.println("Time took to run spell check 2  (Binary search): " + (endTime2 - startTime2) + "ms");
        System.out.println("Time took to run spell check 3 (Hashset method): " + (endTime3 - startTime3) + "ms");
        printNewLines(2);

        //
        // ╔══════════════════════════════════════╗
        // ║    PART B - war-peace occurrences    ║
        // ╚══════════════════════════════════════╝
        //
        // match occurrences of the word War with Peace and find the average distance between the closest set of each pair of these words.
        //int averageDistance = matchOccurrences(bookWords, "war", "peace");
        //System.out.println("Average distance: " + averageDistance);
        System.out.println("Part B - war-peace occurrences");
        displayMatchOccurrences(bookWords, "war", "peace");

        // store each word for the words in the novel and for the words in the provided dictionary
        // The words must be stored without concern for case sensitivity (all characters must be converted to lowercase).
        ArrayList<BookWord> bookWordList = new ArrayList<BookWord>();
        for (String bookWord : bookWords) {
            bookWordList.add(new BookWord(bookWord));
        }

        // create two dictionaries one using ArrayList and the other using SimpleHashSet
        // store each dictionary word in the ArrayList of BookWord
        ArrayList<BookWord> dictionaryWordList = new ArrayList<BookWord>();
        for (String dictionaryWord : dictionaryWords) {
            dictionaryWordList.add(new BookWord(dictionaryWord));
        }
        // sort the ArrayList using the Collections.sort method once all dictionary has been stored in the ArrayList
        //Collections.sort(dictionaryWordList);

        // create a SimpleHashSet<BookWord>
        SimpleHashSet<BookWord> dictionaryWordSet = new SimpleHashSet<BookWord>();
        for (BookWord dictionaryWord : dictionaryWordList) {
            dictionaryWordSet.insert(dictionaryWord);
        }

        // confirm that the selected algorithm produces good hashes
        // If you have less than 10% empty buckets in your HashSet on the Dictionary Words your algorithm is acceptable.
        int emptyBuckets = 0;
        for (ArrayList<BookWord> bucket : dictionaryWordSet.buckets) {
            if (bucket.isEmpty()) {
                emptyBuckets++;
            }
        }

        // Print if the algorithm produces good hashes
        // If you have less than 10% empty buckets in your HashSet on the Dictionary Words your algorithm is acceptable.
        // Default we have 10 buckets, so 10% of 10 is 1
        if (emptyBuckets < dictionaryWordSet.numberOfBuckets * 0.1) {
            System.out.println("Good hashes");
        } else {
            System.out.println("Bad hashes");
        }
    }

    /**
     * Print n new lines
     * @param n number of new lines to print
     */
    public static void printNewLines(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    /**
     * Read the book War and Peace by Leo Tolstoy
     * @param fileName name of the file to read
     * @return list of words in the book
     */
    public static List<String> readFile(String fileName) {
        // read the book War and Peace by Leo Tolstoy
        List<String> bookWords = new ArrayList<String>();

        // Check if the file exists
        // If the file does not exist, print an error message and exit the program
        // read each word from the file and add it to the list of words
        // use the following delimiters: .,?!\s"()_,-:;\n to split the words
        // this delimiter means that the words will be split by .,?! and any whitespace character
        // eg. "sample text? with, punctuation." will be split into "sample", "text", "with", "punctuation
        // exclude word with single quote
        try {
            Scanner scanner = new Scanner(new File(fileName));
            System.out.println("Reading file: " + fileName + "...");
            scanner.useDelimiter("\\.|\\?|\\!|\\s|\"|\\(|\\)|\\,|\\_|\\-|\\:|\\;|\\n");
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                // Exclude word with single quote
                if (word.contains("'")) {
                    continue;
                }
                bookWords.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) { // catch file not found exception
            e.printStackTrace();
            System.out.println("File not found: " + fileName);
        }
        return bookWords; // return the list of words
    }

    /**
     * Count the words in the book War and Peace by Leo Tolstoy
     * @param bookWords list of words in the book
     * @return the number of words in the book
     */
    public static int countWords(List<String> bookWords) {
        // count the words in the book War and Peace by Leo Tolstoy
        return bookWords.size();
    }

    /**
     * Count the occurrences of all words in the book War and Peace by Leo Tolstoy
     * @param bookWords list of words in the book
     * @return a map of words and their occurrences
     */
    public static Map<String, Integer> countAllOccurrences(List<String> bookWords) {
        // count the occurrences of all words in the book War and Peace by Leo Tolstoy
        Map<String, Integer> wordOccurrences = new HashMap<String, Integer>();
        for (String bookWord : bookWords) {
            // check if the word is already in the map
            // if it is, increment the count by 1
            if (wordOccurrences.containsKey(bookWord)) {
                wordOccurrences.put(bookWord, wordOccurrences.get(bookWord) + 1);
            } else { // if it is not, add the word to the map with a count of 1
                wordOccurrences.put(bookWord, 1);
            }
        }
        return wordOccurrences; // return the map
    }

    /**
     * Spell check the document and report how many words are not found in the provided dictionary
     * using the contains method of ArrayList to find matches
     * @param bookWords list of words in the book
     * @param dictionaryWords list of words in the dictionary
     * @return the number of words not found in the dictionary
     */
    public static int countWordsNotInDictionary(List<String> bookWords, List<String> dictionaryWords) {
        int count = 0;
        // loop through each word in the book and check if it is in the dictionary
        for (String bookWord : bookWords) {
            if (!dictionaryWords.contains(bookWord)) {
                // if it is not in the dictionary, increment the count by 1
                count++;
            }
        }
        return count;
    }

    /**
     * Spell check the document and report how many words are not found in the provided dictionary
     * using the binarySearch method of the Collections class (Collections.binarySearch) to search the ArrayList dictionary
     * @param bookWords list of words in the book
     * @param dictionaryWords list of words in the dictionary
     * @return the number of words not found in the dictionary
     */
    public static int countWordsNotInDictionary2(List<String> bookWords, List<String> dictionaryWords) {
        // spell check the document and report how many words are not found in the provided dictionary
        int count = 0;
        for (String bookWord : bookWords) {
            // use the binarySearch method of the Collections class to search the ArrayList dictionary
            // lambda expression will be used to compare the words
            // if the word is not found in the dictionary, increment the count by 1
            if (Collections.binarySearch(dictionaryWords, bookWord, (a, b) -> a.compareTo(b)) < 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Spell check the document and report how many words are not found in the provided dictionary
     * using the SimpleHashSet dictionary and the contains method provided
     * @param bookWords list of words in the book
     * @param dictionaryWords list of words in the dictionary
     * @return the number of words not found in the dictionary
     */
    public static int countWordsNotInDictionary3(List<String> bookWords, SimpleHashSet<String> dictionaryWords) {
        // spell check the document and report how many words are not found in the provided dictionary
        int count = 0;
        for (String bookWord : bookWords) {
            // if the word is not found in the dictionary, increment the count by 1
            if (!dictionaryWords.contains(bookWord)) {
                count++;
            }
        }
        return count;
    }

    // match occurrences of the word War with Peace and find the average distance between the closest set of each pair of these words.
    // All matched pairs output to screen with the total distance and the average distance output to 2 decimal places.

    /**
     * Match occurrences of the word 'War' with 'Peace' and find the average distance between the closest set of each pair of these words.
     * @param bookWords list of words in the book
     * @param word1 first word to match
     * @param word2 second word to match
     */
    public static void displayMatchOccurrences(List<String> bookWords, String word1, String word2) {
        // match occurrences of the word War with Peace and find the average distance between the closest set of each pair of these words.
        int count = 0;
        int distance = 0;
        Map<Integer, Integer> wordPairsPositons = new HashMap<Integer, Integer>();
        List<Integer> distances = new ArrayList<Integer>();
        int totalDistance = 0;
        // loop through each word in the book
        // and look for word 'War' in the book
        for (int i = 0; i < bookWords.size(); i++) {
            // if the word is found, look for word 'Peace' in the book
            if (bookWords.get(i).equals(word1)) {
                distance = 0;
                // look for word 'Peace' in the book
                for (int j = i; j < bookWords.size(); j++) {
                    // if the word is found, store word pairs positions
                    if (bookWords.get(j).equals(word2)) {
                        wordPairsPositons.put(i, j);  // store word pairs positions
                        distance = j - i;             // calculate distance between the matched pairs
                        distances.add(distance);      // store distance between the matched pairs
                        totalDistance += distance;    // calculate total distance
                        count++;                      // count for the total number of matched pairs
                        break; // do not look for any other subsequent occurrences of word 'Peace' in the book after the first matching pair
                    }
                }
            }
        }

        // display the shortest distance between war at pos(3) and peace(1) = 2
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : wordPairsPositons.entrySet()) {
            System.out.println("Shortest distance between " + word1 + " at pos(" + entry.getKey() + ") and " + word2 + "(" + entry.getValue() + ") = " + distances.get(i));
            i++;
        }
        System.out.println("The total sum of distances between the matched pairs of war and peace: " + totalDistance);
        System.out.println("The average distance between the closest set of each pair of these words: " + String.format("%.2f", (double)totalDistance / count));
    }
}
