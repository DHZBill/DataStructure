/**
 * Account.java
 * CS230 Assignment 3
 * @author HaoZheng Du
 * Last Modified Date: 24 September 2016
 * <p>
 * Account is an Abstract class that represents a generic bank account
 */
public abstract class Account {

    protected int accountNumber; // account number
    protected double balance; // balance
    
    // initialize deposit and account number
    public Account(int initialDeposit, int accountNumber){
        this.balance = initialDeposit;
        this.accountNumber = accountNumber;
    }
    
    // make a deposit to the account
    public void deposit(int amount){
        this.balance += amount;
    }
    
    public String toString() {
        return "Account Balance: $" + balance;
    }
    // abstract method withdraw
    public abstract void withdraw(int amount);
}