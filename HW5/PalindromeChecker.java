/**
 * Class PalindromeChecker
 * Created by Haozheng Du
 * This class reads an input string from command-line 
 * and checks if it is a palindrome.
 */
import javafoundations.*;

public class PalindromeChecker{
  // checks if a string is a palindrome
  public static boolean checkPalindrome(String s){
    ArrayStack<Character> arrayStack = new ArrayStack<Character>();
    char[] array = s.toCharArray(); // convert string to an array of characters
    for (int i = 0; i < array.length; i++)
      arrayStack.push(array[i]); // push all characters to the arrayStack
    String reverse = ""; // initialize the reverse string
    while (!arrayStack.isEmpty())
      reverse += arrayStack.pop(); //construct the reverse string by poping the arraystack
    return s.equals(reverse);
  }
  public static void main(String[] args){
    if(args.length > 0){ // if there is an input string, check if it is palindrome
      if(checkPalindrome(args[0]))
        System.out.println(args[0] + " is a palindrome");
      else
        System.out.println(args[0] + " is not a palindrome");
    }
    else // if no input string, ask the user to provide it
    System.out.println("Please give an input string in command-line");
  }
}