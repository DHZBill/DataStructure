import java.util.*;

/**
 * Represents a single card object
 */
public class Card{
  private int number;
  private String suit;
  
  /**
   * Constructor that takes two parameters
   * @param n The face value of the card
   * @param s The suit of the card
   */
  public Card(int n, String s){
    number = n;
    suit = s;
  }
  
  /**
   * Getter for suit
   * @return The suit of the card
   */
  public String getSuit(){return suit;}
  
  /** 
   * Getter for the number
   * @return The face value of the card
   */
  public int getNumber(){return number;};
  
  /**
   * Checks whether this cards is equal to another card
   * @param c The other card
   * @return True if they have the same face value (number)
   */
  public boolean equals(Card c){
    return this.number == c.number;
  }
  
  /** 
   * The string representation of this card
   * @return A nicely formatted representation of this card
   */
  public String toString(){
    return "["  + suit.toUpperCase() + Integer.toString(number) +"]";
  }
}