package edu.grinnell.csc207.experiments;

import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.function.Predicate;

import edu.grinnell.csc207.util.SearchUtils;

/**
 * Assorted experiments for searching structures.
 *
 * @author Luis Lopez
 * @author David William Stroud
 * @author Samuel A. Rebelsky (starter code)
 */
public class SearchExperiments {
  /**
   * Run our experimens.
   *
   * @param args
   *   Command-line arguments. Ignored.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    String[] tmp =
        new String[] { "alpha", "bravo", "charlie", "delta", "echo",
                       "foxtrot", "golf", "hotel", "india",
                       "juliett", "kilo", "lima", "mike",
                       "november", "oscar", "papa", "quebec",
                       "romeo", "sierra", "tango", "uniform",
                       "victor", "whiskey", "xray", "yankee", "zulu" };
    ArrayList<String> strings = new ArrayList<String>(Arrays.asList(tmp));

    try {
      String ex1c = SearchUtils.search(strings, (s) -> s.contains("u"));
      pen.println("The first string of fewer than five letters is " + ex1c);
    } catch (Exception e) {
      pen.println("There are no strings of fewer than five letters.");
    } // try/catch

    try {
      int index = SearchUtils.binarySearch(tmp, "uniform", (a, b) -> a.compareTo(b));
      pen.println("Uniform is at index: " + index);
    } catch (Exception e) {
      pen.println("Could not find uniform.");
    } // try-catch

    int[] large = new int[50000];
    for (int i = 0; i < large.length; i++) {
      large[i] = i * 3;
    }

    int totalNumExecs = 0;
    for (int i = 0; i < large.length; i++) {
      try {
        int _result = SearchUtils.binarySearch(large, i * 3);
      } catch (Exception err) {
        pen.println("Unable to find value in array.");
      }
      totalNumExecs += SearchUtils.getNumExecs();
    }

    pen.println("Average number of comparisons: " + (totalNumExecs / 50000));

    pen.close();
  } // main(String[])
} // class SearchUtils
