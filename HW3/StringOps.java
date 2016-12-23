/**
 * Account.java
 * CS230 Assignment 3
 * @author HaoZheng Du
 * Last Modified Date: 24 September 2016
 * <p>
 * StringOps is a class that check if 2 words are anagrams.
 */
import java.util.Arrays;
public class StringOps{
  public StringOps(){
  }
  //check if 2 words are anagrams
  public static void testAnagrams(String word1, String word2){
    if(word1.length() == word2.length()){ // check if they have the same length
      char[] word1Char= word1.toCharArray(); // convert them into Char Array
      char[] word2Char = word2.toCharArray();
      Arrays.sort(word1Char); // sort both words
      Arrays.sort(word2Char);
      if(Arrays.equals(word1Char, word2Char)){ //check if 2 arrays are the same
        System.out.println(word1 + " and " + word2 + " are anagrams.");
        }
      else
        System.out.println(word1 + " and " + word2 + " are not anagrams.");
    }
    else
      System.out.println(word1 + " and " + word2 + " are not anagrams.");
  }
  
  // remove the specified character from a string
  public static String removeChar(String s, char ch){
    char[] sChar = s.toCharArray(); // convert the String into a char array
    for(int i = 0; i < s.length(); i++){
       if(sChar[i] == ch){ // find the character to remove
         for(int j = i; j<s.length()-1; j++){
            sChar[j] = sChar[j+1]; // replace each character with the later one
         }
       break;
       }
    }
    String newString = new String(sChar); // reconstruct the string
    return newString.substring(0,newString.length()-1); // delete the last character
  }

  public static void main(String[] args){
    testAnagrams("cinema","iceman");
    testAnagrams("hello","world");
    System.out.println(removeChar("Wellesley",'e'));
  }
}