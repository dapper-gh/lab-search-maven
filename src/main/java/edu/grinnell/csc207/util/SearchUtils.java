package edu.grinnell.csc207.util;

import java.util.function.Predicate;
import java.util.Comparator;

/**
 * Assorted utilities for searching structures.
 *
 * @author David William Stroud
 * @author Luis Lopez
 * @author Samuel A. Rebelsky (starter code)
 */
public class SearchUtils {
  private static int numExecs = 0;

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static int iterativeBinarySearch(int[] vals, int val) throws Exception {
    int lb = 0;
    int ub = vals.length - 1;

    while (lb <= ub) {
      numExecs++;
      int candidateIndex = (ub + lb) / 2;
      int candidate = vals[candidateIndex];
      if (candidate == val) {
        return candidateIndex;
      } else if (candidate < val) {
        lb = candidateIndex + 1;
      } else if (candidate > val) {
        ub = candidateIndex - 1;
      }
    }
    throw new Exception();
  } // iterativeBinarySearch


  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of T
   * @param val
   *   A T we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static <T> int iterativeBinarySearch(T[] vals, T val, Comparator<T> compare) throws Exception {
    int lb = 0;
    int ub = vals.length - 1;

    while (lb <= ub) {
      numExecs++;
      int candidateIndex = (ub + lb) / 2;
      T candidate = vals[candidateIndex];
      int compared = compare.compare(candidate, val);
      if (compared == 0) {
        return candidateIndex;
      } else if (compared < 0) {
        lb = candidateIndex + 1;
      } else if (compared > 0) {
        ub = candidateIndex - 1;
      }
    }
    throw new Exception();
  } // iterativeBinarySearch

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @param lb
   *   The lower bound of the current search range, inclusive
   * @param ub
   *   The upper bound of the current search range, inclusive
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static int recursiveBinarySearch(int[] vals, int val, int lb, int ub) throws Exception {
    numExecs++;
    if (lb > ub) { // reached end of search
      throw new Exception("Value " + val + " not found");
    } // if

    int candidateIndex = (ub + lb) / 2;
    int candidate = vals[candidateIndex];
    if (candidate < val) {
      return recursiveBinarySearch(vals, val, candidateIndex + 1, ub);
    } else if (candidate > val) {
      return recursiveBinarySearch(vals, val, lb, candidateIndex - 1);
    } else { // candidate == i
      return candidateIndex;
    } // if-else
  } // recursiveBinarySearch

  /**
   * Search for val in a subarray of values, return the index of an 
   * instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param lb
   *   The lower bound of the area of interest (inclusive)
   * @param ub
   *   The upper bound of the area of interest (exclusive)
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i between lb and ub s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  static int rbsHelper(int[] vals, int lb, int ub, int i) throws Exception {
    return 0;   // STUB
  } // rbsHelper

  // +----------------+----------------------------------------------
  // | Public methods |
  // +----------------+

  /**
   * Search values for the first value for which pred holds.
   *
   * @param <T> 
   *   The type of values we're examining
   * @param values
   *   The iterable we're searching
   * @param pred
   *   The predicate used to determine whether or not the value is
   *   acceptable
   * 
   * @return the first mathcing element.
   *
   * @throws Exception
   *   If no matching value is found.
   */
  public static <T> T search(Iterable<T> values, Predicate<? super T> pred) 
      throws Exception {
    for (T value : values) {
      if(pred.test(value)) {
        return value;
      }
    }
    throw new Exception();
  } // search(Iterable<T>, Predicate<? super T>)

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of integers
   * @param val
   *   An integer we're searching for
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  public static int binarySearch(int[] vals, int i) throws Exception {
    numExecs = 0;
    // return recursiveBinarySearch(vals, i, 0, vals.length - 1);
    return iterativeBinarySearch(vals, i);
  } // binarySearch

  /**
   * Search for val in values, return the index of an instance of val.
   *
   * @param values
   *   A sorted array of T
   * @param value
   *   A T we're searching for
   * @param compare
   *   The comparator used to compare T
   * @return
   *   index, an index of val (if one exists)
   * @throws Exception
   *   If there is no i s.t. values[i] == val
   * @pre
   *   values is sorted in increasing order.  That is, values[i] <
   *   values[i+1] for all reasonable i.
   * @post
   *   values[index] == val
   */
  public static <T> int binarySearch(T[] values, T value, Comparator<T> compare) throws Exception {
    return iterativeBinarySearch(values, value, compare);
  } // binarySearch
  

  /**
   * Return how many comparisons were performed by the most recent execution of binarySearch.
   * @return The number of comparisons.
   */
  public static int getNumExecs() {
    return numExecs;
  } // getNumExecs()
} // class SearchUtils
