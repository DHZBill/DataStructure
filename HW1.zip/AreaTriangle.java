/* AreaTriangle.java
 * CS230 Assignment 1 task 3
 * Written by: Xiaozheng Xu, Haozheng Du
*/
import java.util.Scanner;
public class AreaTriangle{
    /**
     * This script asks the user for inputs of 3 side lengths of a triangle, 
     * determine whether the triangle is isoceles and computes its area
     */
  public static void main(String [] args){
    Scanner scan = new Scanner(System.in); // Create a new Scanner
    
    // Ask the user for input
    System.out.println("Please enter the length of the first side of a triangle:");
    Double side1 = scan.nextDouble();
    System.out.println("Please enter the length of the second side of a triangle:");
    Double side2 = scan.nextDouble();
    System.out.println("Please enter the length of the third side of a triangle:");
    Double side3 = scan.nextDouble();
    
    scan.close(); // Close the Scanner
    
    if (isTriangle(side1, side2, side3)){ //Check if the input lengths can form a triangle
      isIsoceles(side1,side2,side3); // If yes, check if the triangle is isoceles
      System.out.println("The area of the triangle is "+calculateArea(side1,side2,side3)); // Calculate the area of the triangle and print it out
    }
  }

  /**
   * Check if the input length can form a triangle
   */
  public static boolean isTriangle(double side1, double side2, double side3){
    if((side1 + side2 <= side3) || (side2 + side3 <= side1) || (side1 + side3 <= side2)){
      System.out.println("Invalid input");
      return false;
    }
    else {
      return true;
    }
  }
  
  /**
   *  Check if the triangle is isoceles
   */
  public static void isIsoceles(double side1, double side2, double side3){
    if ((side1==side2)||(side2==side3)||(side1==side3)){
      System.out.println("Triangle is isoceles.");
    }
    else {
      System.out.println("Triangle is NOT isoceles.");
    }
  }
  
  /**
   * Calculate the area of the triangle using Heron's Formula
   */
  public static double calculateArea(double side1, double side2, double side3){
    double p = (side1 + side2 + side3)/2;
    double s = Math.sqrt(p*(p-side1)*(p-side2)*(p-side3));
    return s;
  }
}