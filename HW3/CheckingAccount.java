/**
 * Account.java
 * CS230 Assignment 3
 * @author HaoZheng Du
 * Last Modified Date: 24 September 2016
 * <p>
 * CheckingAccount is a sub class of the Account class
 */
public class CheckingAccount extends Account {
    private int minBalance = 100; // minimum balance for the checking account
    private int overdraftFee = 50; // incur if remaining money is less than minimum balance
  
    // initialize deposit and account number
    public CheckingAccount(int initialDeposit, int accountNumber){
      super(initialDeposit,accountNumber);
    }
    
    public String toString() {
      String s = super.toString();
      s = "Checking account " + accountNumber + "\nminimum balance: " + minBalance + "\n" + s;
      return s;
    }
    // withdraw money
    public void withdraw(int amount){
      if(amount<=balance - minBalance)
        balance-=amount;
      else
        balance = balance - amount - overdraftFee; // overdraft fee incurs if balance drops below minimum
    }
}
