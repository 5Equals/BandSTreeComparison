/**
 * Implementation of ordered array ST and Binary search tree. How they compare to each other in the time execution
 * and search part. The input that will be tested on is N words input from predetermined text.
 */

package se.kth;

public class Main {
    public static void main(String[] args) {
        test("theFilteredText.txt", 100, 1000);
    }

    static void test(String file, int testNum, int words) {
        // Preparation
        String text = Utilities.readText(file);
        long averageSTIn = 0;
        long averageBSTIn = 0;
        long averageSTFs = 0;
        long averageBSTFs = 0;

        // The number of test that will take place and taken the average of them.
        for(int i = 0; i < testNum; i++) {
            averageBSTIn += SearchTest.insertionBSTTest(text, words);
            averageSTIn += SearchTest.insertionSTTest(text, words);
            averageBSTFs += SearchTest.maxFrequentSearchBSTTest(text, words);
            averageSTFs += SearchTest.maxFrequentSearchSTTest(text, words);
        }

        // Show the data of the test in a a specific manner.
        // Insertion test result data.
        System.out.println((words * 100) + " words ("+ testNum +" tests average)");
        System.out.println("ST insertion time: " + (averageSTIn / testNum) + "µs");
        System.out.println("BST insertion time: " + (averageBSTIn / testNum) + "µs");
        // Show which of the two implementation wins the test for the decided number of words.
        if(averageSTIn < averageBSTIn) {
            System.out.println("ST is better at Insertion.");
        } else {
            System.out.println("BST is better at Insertion.");
        }
        // Search test result data.
        System.out.println("ST max frequent search time: " + (averageSTFs / testNum) + "µs");
        System.out.println("BST max frequent search: " + (averageBSTFs / testNum) + "µs");
        if(averageSTFs < averageBSTFs) {
            System.out.println("ST is better at searching for the most frequent word.");
        } else {
            System.out.println("BST is better at searching for the most frequent word.");
        }
    }
}
