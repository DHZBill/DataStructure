/* Point.java 
 * CS230 Assignment 2 task 1 
 * Written by: Haozheng Du 
 * *********Important NOTES***********
 * The instruction on the Assignment page is 
 * inconsistent with the instruction on the javadoc page. 
 * On the assignment page I am asked to "Compute, and report if 
 * ANY of the three points is equidistant to the other two."
 * But on the javadoc, I am asked to "calculate their distances 
 * and report which of points P2 and P3 is closer to P1."
 * Here I follow the instruction on the assignment page.
*/ 
import java.util.Scanner;

public class Point{
  private double xCoord;
  private double yCoord;
  private final double TOLERANCE = 0.01;
  // Constructor with 2 coordinates as arguments.
  public Point(double xCoord, double yCoord){
    this.xCoord = xCoord;
    this.yCoord = yCoord;
  }
  
  // Find the distance between this point to another.
  public double findDistance(Point other){
    double xDistance = this.xCoord - other.xCoord;
    double yDistance = this.yCoord - other.yCoord;
    double distance = Math.sqrt(xDistance*xDistance + yDistance*yDistance);
    return distance;
  }
  
  // Check if the other two points are equidistant to this point.
  public boolean areEquidistant(Point p2, Point p3){
    return (Math.abs(findDistance(p2) - findDistance(p3)) <= TOLERANCE);
  }
  
  // Get the x coordinate.
  public double getX(){
    return this.xCoord; 
  }
  
  // Get the y coordinate.
  public double getY(){
    return this.yCoord;
  }
  
  // Set the x coordinate.
  public void setX(double newX){
    this.xCoord = newX;
  }
  
  // Set the y coordinate.
  public void setY(double newY){
    this.yCoord = newY;
  }
  
  // Print the point in (x, y).
  public String toString(){
    return ("(" + xCoord + ", " + yCoord + ")");
  }
  
  // Read from user input and construct a point.
  public static Point readPoint(Scanner s){
    System.out.println("Enter the X coordinate:");
    double X = s.nextDouble();
    System.out.println("Enter the Y coordinate:");
    double Y = s.nextDouble();
    Point p = new Point(X, Y);
    return p;
  }
  
 /**
  * Take 3 user inputs, create 3 points and check if 
  * ANY of the three points is equidistant to the other two.
  */
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    Point p1 = readPoint(s);
    Point p2 = readPoint(s);
    Point p3 = readPoint(s);
    if (p1.areEquidistant(p2,p3))
      System.out.println("Point " + p1 + " is equidistant to the other two.");
    if (p2.areEquidistant(p1,p3))
      System.out.println("Point " + p2 + " is equidistant to the other two.");
    if (p3.areEquidistant(p1,p2))
      System.out.println("Point " + p3 + " is equidistant to the other two.");
    else if (!p1.areEquidistant(p2,p3) && !p2.areEquidistant(p1,p3)){
      System.out.println("None of the 3 points is equidistant to the other two.");
    }
  }
}