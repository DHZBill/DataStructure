/* CorrectChange.java 
 * CS230 Assignment 1 task 1 
 * Written by: Haozheng Du 
*/ 
import java.util.Scanner; 

public class CorrectChange{
  /**
   * Makes correct change for a specified amount of money.
   */
  public static void main(String [] args){
    
    Scanner scan = new Scanner(System.in); // Creates a new Scanner
    System.out.print("How much money do you want to make change for?"); //Ask user for input
    double total = scan.nextDouble(); // Get the input value and pass it into total
    
    System.out.println("We can make change for $" + total + " using:");
    
    total = total * 100; // Convert total amount of money into cents
    
    // Computes through different valued bills/coins
    total = makeChangeWithOneDenomination((int)total, "$20 bills", 2000); 
    total = makeChangeWithOneDenomination((int)total, "$10 bills", 1000);
    total = makeChangeWithOneDenomination((int)total, "$5 bills", 500);
    total = makeChangeWithOneDenomination((int)total, "$1 bills", 100);
    total = makeChangeWithOneDenomination((int)total, "quarters", 25);  
    total = makeChangeWithOneDenomination((int)total, "dimes", 10);
    total = makeChangeWithOneDenomination((int)total, "nickels", 5);
    total = makeChangeWithOneDenomination((int)total, "pennies", 1);
    
    
    
    
  }
  
  /**
   *  Computes how many occurrences of the specified denomination ("denomination") 
   *  should be used when making change for the specified amount of cents ("total")
   */
  public static int makeChangeWithOneDenomination(int total, 
                                                  String denominationName, 
                                                  int denomination){
    if(total/denomination != 0){ // Check if at least one the specific bill/coin is needed
      System.out.println(total/denomination +" " + denominationName);
    }
    return (total % denomination); // Return rest of the money    
  }

}
    