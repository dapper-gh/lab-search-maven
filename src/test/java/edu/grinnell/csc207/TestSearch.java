package edu.grinnell.csc207;

import java.util.Arrays;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.util.SearchUtils;

/**
 * Tests of our search methods.
 *
 * @author Luis Lopez
 * @author David William Stroud
 * @author Samuel A. Rebelsky
 */
public class TestSearch {
  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * A string version of a call to binary search.
   *
   * @param values
   *   The array.
   * @param val
   *   The value we're searching for.
   *
   * @return
   *   The string "binarySearch(values, val)"
   */
  String bsCall(Integer[] values, int val) {
    return String.format("binarySearch(%s, %d)", Arrays.toString(values), val);
  } // bsCall

  /**
   * Assert that a search for a particular value finds the value at that
   * index.
   *
   * @param expected
   *   The expected index.
   * @param values
   *   The array we're searching.
   * @param val
   *   The value we're searching for.
   */
  void assertBinarySearchFinds(int expected, Integer[] values, int val) 
      throws Exception {
    assertEquals(expected, SearchUtils.binarySearch(values, val, (a, b) -> a - b),
        () -> bsCall(values, val));
  } // assertBinarySearchFinds(int, int[], int)

  /**
   * Assert that a search for a particular value fails (hopefully, because 
   * the value * is not in the array).
   *
   * @param values
   *   The array we're searching.
   * @param val
   *   The value we're searching for.
   */
  void assertBinarySearchFails(Integer[] values, int val) throws Exception {
    assertThrows(Exception.class,
        () -> SearchUtils.binarySearch(values, val, (a, b) -> a - b),
        () -> bsCall(values, val));
  } // assertBinarySearchFails()

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Searching the empty array should fail.
   */
  @Test
  void testBinarySearchEmpty() throws Exception {
    assertBinarySearchFails(new Integer[] { }, 0);
  } // testSearchEmpty()

  /**
   * Searching in a one-element array.
   */
  @Test
  void testBinarySearchOne() throws Exception {
    assertBinarySearchFinds(0, new Integer[] { 5 }, 5);
    assertBinarySearchFails(new Integer[] { 5 }, 0);
    assertBinarySearchFails(new Integer[] { 5 }, 10);
  } // testBinarySearchOne()

  /**
   * Searching in a two-element array.
   */
  @Test
  void testBinarySearchTwo() throws Exception {
    assertBinarySearchFinds(0, new Integer[] { 7, 11 }, 7);
    assertBinarySearchFinds(1, new Integer[] { 7, 11 }, 11);
    assertBinarySearchFails(new Integer[] { 7, 11 }, 0);
    assertBinarySearchFails(new Integer[] { 7, 11 }, 10);
    assertBinarySearchFails(new Integer[] { 7, 11 }, 20);
  } // testBinarySearchTwo()

  @Test
  void testBinarySearchDuplicates() throws Exception {
    Integer[] dup = new Integer[] {0, 1, 1, 2, 3, 4};
    int dupIndex = SearchUtils.binarySearch(dup, 1, (a, b) -> a - b);
    assertTrue(
      dupIndex == 1 || dupIndex == 2,
      "binarySearch should find one of many duplicate values (index=1 or index=2), found index " + dupIndex
    );
  } // testBinarySearchDuplicates()

  @Test
  void testBinarySearchOdd() throws Exception {
    assertBinarySearchFinds(2, new Integer[] {0, 1, 2}, 2);
    assertBinarySearchFinds(2, new Integer[] {0, 2, 4, 6, 8, 10, 12}, 4);
    assertBinarySearchFails(new Integer[] {-1, 4, 6}, 0);
  } // testBinarySearchOdd()

  @Test
  void testBinarySearchEven() throws Exception {
    assertBinarySearchFinds(2, new Integer[] {0, 1, 2, 3}, 2);
    assertBinarySearchFinds(2, new Integer[] {0, 2, 4, 6, 8, 10, 12, 14}, 4);
    assertBinarySearchFails(new Integer[] {-1, 4, 6, 8}, 0);
  } // testBinarySearchEven()

  @Test
  void samTest() throws Exception {
    //   For each s from 1 to 32
    // Create an array of size s, containing the values 0, 2, 4, ... 2*(s-1)
    // For all i from 0 to s-1, inclusive
    for (int i = 1; i <= 32; i ++) {
      Integer[] current = new Integer[i];

      for (int j = 0; j < current.length; j++) {
        current[j] = 2 * j;
      } // for

      for (int h = 0; h < current.length; h++ ) {
        assertBinarySearchFinds(h, current, 2*h);
        assertBinarySearchFails(current, 2*h+1);
      } // for
      assertBinarySearchFails(current, -1);
    } // for
  } // samTest()
} // class TestSearch
