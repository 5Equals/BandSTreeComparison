/**
 * Manage the tests for the insertion and search for both implementation.
 */

package se.kth;

import java.util.Scanner;

class SearchTest {
    private SearchTest() { }

    /**
     * The time test for the insertion of words in the Ordered array ST implementation.
     * @param textFileContent the text that the words will be extracted from.
     * @param N the number of words that the test will be take into consideration.
     * @return the time that took for the insertion to finish.
     */
    public static long insertionSTTest(String textFileContent, int N) {
        // Preparations
        String text = textFileContent;
        Scanner scanner = new Scanner(text);
        int minLength = 1;
        long startTime = 0, endTime = 0;
        ST<String, Integer> st = new ST<String, Integer>();

        startTime = System.nanoTime();
        // Get the words.
        for(int i = 0; i < (N * 100) && scanner.hasNext(); i++) {
            String key = scanner.next();
            if (key.length() < minLength) { // Word is at least one character.
                continue;
            }
            if (st.contains(key)) { // The word is already added.
                st.put(key, st.get(key) + 1);
            } else { // Add the word.
                st.put(key, 1);
            }
        }
        endTime = (System.nanoTime() - startTime);
        return endTime / 1000;
    }

    /**
     * The time test for the insertion of words in the Binary search tree  implementation.
     * @param textFileContent the text that the words will be extracted from.
     * @param N the number of words that the test will be take into consideration.
     * @return the time that took for the insertion to finish.
     */
    public static long insertionBSTTest(String textFileContent, int N) {
        // Preparations
        String text = textFileContent;
        Scanner scanner = new Scanner(text);
        int minLength = 1;
        long startTime = 0, endTime = 0;
        BST<String, Integer> bst = new BST<String, Integer>();

        startTime = System.nanoTime();
        // Get the words.
        for(int i = 0; i < (N * 100) && scanner.hasNext(); i++) {
            String key = scanner.next();
            if (key.length() < minLength) { // Word is at least one character.
                continue;
            }
            if (bst.contains(key)) { // The word is already added.
                bst.put(key, bst.get(key) + 1);
            } else { // Add the word.
                bst.put(key, 1);
            }
        }
        endTime = (System.nanoTime() - startTime);
        return endTime / 1000;
    }

    /**
     * The time test for the search of max frequent word in the Ordered array ST implementation.
     * @param textFileContent the text that the words will be extracted from.
     * @param N the number of words that wanted to be inserted.
     * @return the time that took for the search to finish.
     */
    public static long maxFrequentSearchSTTest(String textFileContent, int N) {
        // Preparations
        String text = textFileContent;
        Scanner scanner = new Scanner(text);
        int minLength = 1;
        long startTime = 0, endTime = 0;
        ST<String, Integer> st = new ST<String, Integer>();

        // Get the words.
        for(int i = 0; i < (N * 100) && scanner.hasNext(); i++) {
            String key = scanner.next();
            if (key.length() < minLength) {
                continue;
            }
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
            }
        }

        startTime = System.nanoTime();
        // Get the word that is the most frequent.
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }
        endTime = (System.nanoTime() - startTime);
        return endTime / 1000;
    }

    /**
     * The time test for the search of max frequent word in the Binary search tree implementation.
     * @param textFileContent the text that the words will be extracted from.
     * @param N the number of words that wanted to be inserted.
     * @return the time that took for the search to finish.
     */
    public static long maxFrequentSearchBSTTest(String textFileContent, int N) {
        // Preparations
        String text = textFileContent;
        Scanner scanner = new Scanner(text);
        int minLength = 1;
        long startTime = 0, endTime = 0;
        BST<String, Integer> bst = new BST<String, Integer>();

        // Get the words.
        for(int i = 0; i < (N * 100) && scanner.hasNext(); i++) {
            String key = scanner.next();
            if (key.length() < minLength) {
                continue;
            }
            if (bst.contains(key)) {
                bst.put(key, bst.get(key) + 1);
            } else {
                bst.put(key, 1);
            }
        }

        startTime = System.nanoTime();
        // Get the word that is the most frequent.
        String max = "";
        bst.put(max, 0);
        for (String word : bst.keys()) {
            if (bst.get(word) > bst.get(max)) {
                max = word;
            }
        }
        endTime = (System.nanoTime() - startTime);
        return endTime / 1000;
    }
}
