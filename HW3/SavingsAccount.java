/**
 * SavingsAccount.java
 * CS230 Assignment 3
 * @author HaoZheng Du
 * Last Modified Date: 24 September 2016
 * <p>
 * SavingsAccount is a sub class of the Account class
 */
public class SavingsAccount extends Account {
  private double interestRate = 1.5; // annual interest rate
  // initialize deposit and account number
  public SavingsAccount(int initialDeposit,int accountNumber){
    super(initialDeposit, accountNumber); 
  }
  // withdraw money
  public void withdraw(int amount){
    if(amount>balance){ // warn the user if withdrawing more money than balance
      System.out.println("**Withdrawal DENIED: Amount in Savings is greater than balance.");
    }
    else{
      balance-=amount;
    }
  }
  // add monthly interest
  public void recalculateBalance(){
    balance = Math.round(balance * (interestRate/100/12 + 1));
  }
  
  public String toString() {
    String s = super.toString();
    s = "Saving Account " + accountNumber + "\n" + s + "\nInterest Rate: " + interestRate;
    return s;
  }
}