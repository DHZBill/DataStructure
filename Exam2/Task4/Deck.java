import java.util.*;

/**
 * CS230 Midterm Exam 2 Task 4
 * By Haozheng Du
 * Represents a collection of cards
 * I believe this program implements all features and works well.
 */
public class Deck{
  // The initial number of cards in the Deck
  private final int NUM_OF_CARDS = 52;
  // The stack of cards in the Deck
  Stack<Card> allCards;
  
  /**
   * Constructor that fills the deck with Card objects
   */
  public Deck(){
    allCards = new Stack<Card>();
    for (int i = 1; i <= 13; i++){
      Card spade = new Card(i,"Spade");
      Card club = new Card(i,"Club");
      Card diamond = new Card(i,"Diamond");
      Card heart = new Card(i, "heart");
      allCards.push(spade);
      allCards.push(club);
      allCards.push(diamond);
      allCards.push(heart);
    }
  }
  
  /**
   * Shuffles the cards within this Deck object.
   * Pop all cards and put them into an ArrayList,
   * push them back to the stack by randomly selecting
   * them.
   */
  public void shuffle(){
    Random rnd = new Random();
    ArrayList<Card> cards = new ArrayList<Card>();
    while(hasNext()){
      cards.add(allCards.pop());
    }
    while(!cards.isEmpty()){
      int n = rnd.nextInt(cards.size());
      allCards.push(cards.remove(n));
    }
  }

  /** 
   * Checks if there are any more cards in the deck
   * @return True if there is at least one more card
   */
  public boolean hasNext(){
    return (!allCards.empty());
  }
  
  /**
   * Retrieves the next card from the deck
   * @return The top card from the deck
   */
  public Card getNext(){
    return allCards.pop();
  }
  
  /** 
   * Gets the original size of the deck of cards
   * @return Number of cards originally in the deck
   */
  public int originalSize(){
    return NUM_OF_CARDS;
  }
  
  public String toString(){
    String s = "";
    for(int i = 0; i < allCards.size(); i ++){
      s += allCards.get(i);
    }
    return s;
  }
  
  public static void main(String[] args){
    Deck d = new Deck();
    System.out.println(d);
    d.shuffle();
    System.out.println(d);
  }
}
