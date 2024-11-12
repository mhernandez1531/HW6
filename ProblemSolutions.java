
/******************************************************************
 *
 *   Mariana Hernandez / COMP 272-002 - Fall 2024
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;
import java.util.PriorityQueue;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     *
     * PQ1 Problem Statement:
     * -----------------------
     *
     * You are given an array of integers of boulders where boulders[i] is the
     * weight of the ith boulder.
     *
     * We are playing a game with the boulders. On each turn, we choose the heaviest
     * two boulders and smash them together. Suppose the heaviest two boulders have
     * weights x and y. The result of this smash is:
     *
     *    If x == y, both boulders are destroyed, and
     *    If x != y, the boulder of weight x is destroyed, and the boulder of
     *               weight y has new weight y - x.
     *
     * At the end of the game, there is at most one boulder left.
     *
     * Return the weight of the last remaining boulder. If there are no boulders
     * left, return 0.
     *
     *
     * Example 1:
     *
     * Input: boulders = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
     * value of the last stone.
     *
     * Example 2:
     *
     * Input: boulders = [1]
     * Output: 1
     *
     *
     *
     * RECOMMENDED APPROACH
     *
     * Initializing Priority Queue in reverse order, so that it gives
     * max element at the top. Taking top Elements and performing the
     * given operations in the question as long as 2 or more boulders;
     * returning the 0 if queue is empty else return pq.peek().
     */

  public static int lastBoulder(int[] boulders) {

      //
      // ADD YOUR CODE HERE - DO NOT FORGET TO ADD YOUR NAME / SECTION # ABOVE
      //

      // Create a max-priority queue to store boulders in descending order.
      PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

      // Add each boulder to the priority queue.
      for (int boulder : boulders) {
          pq.add(boulder);
      }

      // Continue smashing the two heaviest boulders until only one or zero remain.
      while (pq.size() > 1) {

          // Retrieve and remove the two heaviest boulders.
          int y = pq.poll(); // heaviest
          int x = pq.poll(); // second heaviest

          // If they are not equal, insert the difference (y - x) back into the queue.
          if (x != y) {
              pq.add(y - x); // The remaining weight of the larger boulder
          }
      }

      // If queue is empty, return 0; otherwise, return the last remaining boulder.
      return pq.isEmpty() ? 0 : pq.peek();
  }


    /**
     * Method showDuplicates
     *
     * This method identifies duplicate strings in an array list. The list
     * is passed as an ArrayList<String> and the method returns an ArrayList<String>
     * containing only unique strings that appear more than once in the input list.
     * This returned array list is returned in sorted ascending order. Note that
     * this method should consider case (strings are case-sensitive).
     *
     * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
     * the method would return an ArrayList<String> containing: "Dog", "Lion"
     *
     * @param  input an ArrayList<String>
     * @return       an ArrayList<String> containing only unique strings that appear
     *               more than once in the input list. They will be in ascending order.
     */

    public static ArrayList<String> showDuplicates(ArrayList<String> input) {

        //
        //  YOUR CODE GOES HERE
        //

        // Creatoing a HashMap to track the frequency of each string
        Map<String, Integer> frequencyMap = new HashMap<>();

        // Loop through each string in the input list
        for (String str : input) {

            // If the string is already in the map, increment its count by 1;
            // otherwise, set its count to 1
            frequencyMap.put(str, frequencyMap.getOrDefault(str, 0) + 1);
        }

        // Create a list to store strings that appear more than once
        ArrayList<String> duplicates = new ArrayList<>();

        // Check each entry in the frequency map
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {

            // If a string's frequency is greater than 1, it means it is a duplicate
            if (entry.getValue() > 1) {

                // Add the duplicate string to the duplicates list
                duplicates.add(entry.getKey());
            }
        }

        // Step 5: Sort the list of duplicates in ascending order (alphabetically)
        Collections.sort(duplicates);

        // Step 6: Return the sorted list of duplicates
        return duplicates;
    }


    /**
     * Finds pairs in the input array that add up to k.
     *
     * @param input   Array of integers
     * @param k       The sum to find pairs for

     * @return an ArrayList<String> containing a list of strings. The ArrayList
     *        of strings needs to be ordered both within a pair, and
     *        between pairs in ascending order. E.g.,
     *
     *         - Ordering within a pair:
     *            A string is a pair in the format "(a, b)", where a and b are
     *            ordered lowest to highest, e.g., if a pair was the numbers
     *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
     *         - Ordering between pairs:
     *            The ordering of strings of pairs should be sorted in lowest to
     *            highest pairs. E.g., if the following two string pairs within
     *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
     *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
     *
     *         Example output:
     *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
     *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
     *
     *  HINT: Considering using any Java Collection Framework ADT that we have used
     *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
     *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
     *  Java Framework documentation in its use.
     */

    public static ArrayList<String> pair(int[] input, int k) {

        //
        //  YOUR CODE GOES HERE
        //

        // Create a set to track elements we've already seen in the array
        Set<Integer> seen = new HashSet<>();

        // Use a set to store unique pairs in string format to avoid duplicates
        Set<String> uniquePairs = new HashSet<>();

        // Looping through each number in the input array
        for (int num : input) {

            // Calculate the complement of the current number needed to reach sum k
            int complement = k - num;

            // Checking if we've seen the complement before
            if (seen.contains(complement)) {

                // If yes, create a sorted pair (smallest number first)
                int a = Math.min(num, complement);
                int b = Math.max(num, complement);

                // Add the formatted pair to uniquePairs to ensure no duplicates
                uniquePairs.add("(" + a + ", " + b + ")");
            }

            // Step 5: Add the current number to the seen set
            seen.add(num);
        }

        // Converting the set of unique pairs to a list so we can sort it
        ArrayList<String> pairs = new ArrayList<>(uniquePairs);

        // Sort the pairs lexicographically so they are in ascending order
        Collections.sort(pairs);

        //  Return the sorted list of pairs
        return pairs;
    }
}