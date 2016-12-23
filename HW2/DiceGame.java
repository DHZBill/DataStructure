/**Code template 
 * Simulates a Dice Poker game played between the computer and user.
  * <p>
  * This class definition contains a main() method that assumes 
  * that the user enters a name and an integer in the command line, for example: 
  * java PlayDice Wendy 7<br>
  * If no arguments are passed, it will assume the call was with Dave and 5
  * @author   CS230 Staff and Zarin Bhuiyan and Haozheng Du
 */
 
import java.util.Arrays;
public class DiceGame {
 
  private DicePlayer thePlayer = new DicePlayer(); 
  private DicePlayer theComputer = new DicePlayer();
  private int numRounds; 
  private int pwin; //rounds won by player
  private int cwin; //rounds won by the computer
  private int[] diceResults = new int[6]; // initializing an integer Array that holds the multiplicity of dice values for each round


  /** Creates client-specific game settings. 
    * @param playerName The name of the player
    * @param numGames The number of rounds to play
    */  
  public DiceGame(String playerName, int numGames) {
    thePlayer.setName(playerName);
    numRounds = numGames;
  }

  /** Counts how many distinct values appear in the input array
  * and stores each count onto the diceResults array.
  * <strong>PRE-CONDITION:</strong> diceResults[] should have enough length 
  * to accomodate the values found in the input array.
  *
  * @param input   The input array
  * @param diceResults  Holds the multiplicity of values found in input.
  *       Note that this is actually the output parameter
  */
  private void accumulateValues(int[] input, int[] diceResults) {
  

    Arrays.fill(diceResults, 0); //Resets the diceResults array to contain 0 at each index
    int len = input.length;
    for (int i = 0; i < len; i++) {
        if (input[i] == 1) {
            diceResults[0]++;
        } else if (input[i] == 2) {
            diceResults[1]++;
        } else if (input[i] == 3) {
            diceResults[2]++;
        } else if (input[i] == 4) {
            diceResults[3]++;
        } else if (input[i] == 5) {
            diceResults[4]++;
        } else if (input[i] == 6) {
            diceResults[5]++;
        }
    }
 
  }
  
  /** Given an input array storing five dice values, 
   * determines the rank of the set of values. 
   * @param input  The array with the five dice values
   * @return    The rank of the hand; an integer between 0 and 6
   */
  private int getRank (int[] input) {
    accumulateValues(input, this.diceResults); 
    Arrays.sort(diceResults); //Sorting the values in diceResults from smallest to largest
    int rank;
    //Uses the largest value(s) in diceResults to determine the rank.
    if (diceResults[5] == 5){ //Example: If the largest value in diceResults is 5, then rank is 6 or "Five of a Kind"
      rank = 6;
    }
    else if (diceResults[5] == 4){ 
      rank = 5;
    }
    else if (diceResults[5] == 3){
      if (diceResults[4] == 2){
        rank = 4;
    }
      else {
        rank =  3;
      }
    }
    else if (diceResults[5] == 2){
      if (diceResults[4] == 2){
        rank = 2;
      }
      else{
        rank= 1;
      }
    }
    else{
      rank= 0;
    }
    return rank;
  }
  
  /** Plays one round of the game: Starts with the computer's turn, 
    * then the player's turn.
    * @return 0 if computer wins the round, 1 if player wins, 2 if a tie
    */
  private int playOneRound() {
    int[] computerHand = theComputer.getValues(); // gets the values of the Computer's hand
    int[] playerHand = thePlayer.getValues(); // gets the values of the Player's hand
    int computerRank = getRank(computerHand); //gets the Rank of the Computer's hand
    int playerRank = getRank(playerHand); // gets the Rank of the Player's hand
    System.out.println("Computer rolled "+ computerHand[0] + " "
                                         + computerHand[1] + " "
                                         + computerHand[2] + " "
                                         + computerHand[3] + " "
                                         + computerHand[4] + ": " + rankName(computerRank));
    System.out.println(thePlayer.getName() + " rolled " + playerHand[0] + " "
                                                        + playerHand[1] + " "
                                                        + playerHand[2] + " "
                                                        + playerHand[3] + " "
                                                        + playerHand[4] + ": " + rankName(playerRank) + "\n");

    if(computerRank > playerRank){
      return 0;
    }
    else if (computerRank < playerRank){
      return 1;
    }
    return 2;
  }
  
  /** 
   * This method uses the switch to translate the Rank number to the Rank type.
   * @param int rank which is the Rank number
   * @return String rankName which is the Rank type
   */
  public String rankName(int rank){
    String rankName = "";
    switch (rank+1) {
            case 1:  rankName = "Nothing";
                     break;
            case 2:  rankName = "One Pair";
                     break;
            case 3:  rankName = "Two Pairs";
                     break;
            case 4:  rankName = "Three of a Kind";
                     break;
            case 5:  rankName = "Full House";
                     break;
            case 6:  rankName = "Four of a Kind";
                     break;
            case 7:  rankName = "Five of a Kind";
                     break;
  }
    return rankName;
  }
  
  /**  Simulates the playing of numRounds of the Dice Poker game between the
    * Computer and player, and prints the winner at the end.
    */

  public void playDiceGame () {
    // Be nice to the player!
    System.out.println("Good evening, " + thePlayer.getName()+"!");
    System.out.println("Everything's running smoothly. How are you?");
    System.out.print("I'm completely operational and ");
    System.out.println("all my circuits are functioning perfectly.");
    System.out.println("Would you like to play a game of Dice Poker? I play very well.");

    for (int i = 0; i < numRounds; i++){ //Plays a specific number of rounds and counts how many times each player wins a round
      
      System.out.println("***ROUND " + (i+1)); 
      int result = playOneRound();
      if (result == 0){
        cwin++;
      }
      else if (result == 1){
        pwin++;
        
      }
    }
    
    // After all rounds played, determine the final winner of the game and print the results
    if (pwin>cwin) System.out.print("The game was won by "+ thePlayer.getName() + " with a score of " + pwin + " to " + cwin);
    else if (cwin>pwin) System.out.print("The game was won by the Computer with a score of " + cwin + " to " + pwin);
    else System.out.print("The game was tied with a score of " + cwin + " to " + pwin);
    
    System.out.println(" in " + numRounds + " rounds.");
    System.out.println("Thank you for a very enjoyable game!");
  }


/** Start the homework by reading this method. 
 */
  public static void main (String args[]) {
     
    String name = (args.length >  0)? args[0] : "Dave";  // the default
    int numRounds = (args.length >  1)? Integer.parseInt(args[1]) : 5; // the default
    // Create an instance of a new game and play the rounds
    DiceGame game = new DiceGame(name, numRounds);
    game.playDiceGame();
  }

}
