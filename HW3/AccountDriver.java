/**
 * AccountDriver.java
 * CS230 Assignment 3
 * @author HaoZheng Du
 * Last Modified Date: 24 September 2016
 * <p>
 * Driver class for testing.
 */
public class AccountDriver{
  public static void main(String[] args){
    // new checking account with initial deposit 3000 and account number 1.
    CheckingAccount c = new CheckingAccount(3000,1); 
    System.out.println(c + "\n");
    // make a deposit of 1000
    c.deposit(1000);
    System.out.println("After deposit: \n" + c + "\n");
    // withdraw 5000 from checking account
    c.withdraw(5000);
    System.out.println("Withdraw 5000: \n" + c + "\n");
    // new saving account with initial deposit 5000 and number 2
    SavingsAccount s = new SavingsAccount(5000,2);
    System.out.println(s + "\n");
    // add monthly interest
    s.recalculateBalance();
    System.out.println("Recalculating balance " + s + "\n");
    // withdraw 10000 from saving account
    s.withdraw(10000);
    System.out.println(s);
  }  
}