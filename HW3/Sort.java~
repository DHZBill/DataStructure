/*
 * subtask 1
 * sortArray finds the highest number and swap the highest number with the last(jth) digit using swap
 * then it finds the highest number from index 0 to index j-1 and swap with the (j-1)th digit
 * then it repeats until it has gone through the entire array
 */

/**
 * Modified by: HaoZheng Du, Jee Hyun Kim
 * Modified date: 24 September 2016
 * sortArray returns array of Integer which is sorted index of arrayA
 */

import java.util.stream.IntStream;

public class Sort {  
  
  /*
  * Sorts the integers in the input array in increasing order
  * and returns array of Integer which shows the new position of original array 
  * if the array is to be sorted in increasing order
  */
  public static int [] sortArray (int[] arrayA) {
    int[] range = IntStream.rangeClosed(0, arrayA.length).toArray();
    int maxNum;    // maximum integer so far
    int maxIndex;  // index of maximum integer
    int i, j;
    for (j = arrayA.length - 1; j > 0; j--) {
      maxIndex = 0;
      maxNum = arrayA[0];
      for (i = 1; i <= j; i++) 
        if (arrayA[i] > maxNum) {
          maxNum = arrayA[i];
          maxIndex = i;
        }
      swap(arrayA, maxIndex, j);
      swap(range, maxIndex, j);
    }
    return range;
  }
  
/** 
  * Exchanges the contents of locations i and j in the input array
  */
  private static void swap (int[] arrayA, int i, int j) {
    int temp = arrayA[i];
    arrayA[i] = arrayA[j];
    arrayA[j] = temp;
  }
}