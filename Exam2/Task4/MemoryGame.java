/**
 * CS230 Midterm Exam 2 Task 4
 * By Haozheng Du
 * Class MemoryGame is a class that allows clients to play a memory card game.
 * I believe my program implements all major features and works well.
 * An exception might happen if all 10 cards do not match each other in my program.
 * I would check the table when creating it but the deck.getNext() method prevent me
 * from easily implementing this because that method removes the card from the deck. 
 */ 
import java.util.*;

public class MemoryGame{
  private LinkedList<Card> table; // 10 cards on the table is stored in a LinkedList.
  private Deck deck; // The original card deck
  
  /** 
   * Constructs the game with a deck of cards and an empty table.
   */
  public MemoryGame(){
    deck = new Deck(); 
    table = new LinkedList<Card>();
  }
  
  /** 
   * puts 10 cards on the table from the deck.
   */
  public void generateTable(){
    for(int i = 0; i < 10; i++){
      table.add(deck.getNext());
    }
  }
  
  /**
   * Flips a card at given index on the table.
   */
  public void flip(int index){
    Card card = table.get(index);
    card.flip();
  }
  
  /**
   * Prints the cards on the table
   */
  public void printTable(){
    String s = "";
    for (int i = 1; i <= 10; i++){
      // if the card is facing up, shows the content
      if (table.get(i-1).getStatus()) 
        s = s + table.get(i-1) + " ";
      // if facing down, show only the index
      else
        s = s + "[" + i + "] ";
    }
    System.out.println(s);
  }

  /**
   * Play 1 round (choose 2 cards)
   */
  public void playOneRound(){
//    for(int i = 0; i < 10; i++)
//      table.get(i).flip();
    printTable();
    String s = "";
    Scanner scan = new Scanner(System.in); // creates a scanner for user input.
    
    // choosing the first card
    System.out.println("Pick the card number [1-10]. Type 0 to stop the game.");
    int i = scan.nextInt();
    if(i == 0){ // if the input is 0, shutdown the game.
      System.out.println("Play again soon!");
      System.exit(0);
    }
//    System.out.println(i);
    Card card1 = table.get(i-1); // get the first card based on index
//    System.out.println(card1);
    card1.flip(); 
    printTable();
    
    // choosing the second card
    System.out.println("Pick the second card number [1-10]. Type 0 to stop the game.");
    int j = scan.nextInt();
    if(j == 0){
      System.out.println("Play again soon!");
      System.exit(0);
    }
//    System.out.println(i + " " +j);
    Card card2 = table.get(j-1);
//    System.out.println(card2);
    card2.flip();
    printTable();
//    System.out.println(""  + card1 + card2);
    
    // check if two cards match. 
    if(card1.equals(card2)){
      System.out.println("Awesome! You found a match.");
      // if match, take 2 cards from the deck and replace the chosen cards.
      table.set(i-1,deck.getNext());
      table.set(j-1,deck.getNext());
    }
    else{
      System.out.println("Not a match. Try again!");
      // if not match, flip back the chosen cards.
      card1.flip();
      card2.flip();
    }
  }
  
  /**
   * play the game
   */
  public static void main(String[] args){
    System.out.println("Welcome to the memory game.");
    System.out.println("Your goal is to match the cards in the deck in the minimum number of rounds.");
    MemoryGame game = new MemoryGame(); // initializes the game
    game.deck.shuffle(); // shuffles the deck of cards
    game.generateTable(); // put 10 cards on the table
    while(true)
      game.playOneRound(); // play until all cards from the deck are matched.
  }
}