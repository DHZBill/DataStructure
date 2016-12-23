/* Intro.java
 * CS230 Lab 1
 * Written by: Jee Hyun Kim and Zarin Bhuiyan
 * Modified by:
 * Modified date: September 7th 2016
 */

import java.util.Random;


public class Dice{
  
    
  public static void main(String [] args){
    
    int rnumber1 = roll();
    int rnumber2 = roll();
    
    if (rnumber1 == rnumber2){
      System.out.println("You rolled two " + rnumber1);
    }
    else{
    System.out.println("You rolled a " + rnumber1 + " and a " + rnumber2);
    }
    
  }
  
  public static int roll(){
    // returns random number between 1 to 6 (including)
    Random rand = new Random();
    
    int randomNumber = rand.nextInt(6) + 1;
    
    return randomNumber;
  }
}