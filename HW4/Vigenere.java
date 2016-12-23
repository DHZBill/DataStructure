/**
 * CS230 Assignment 4 Task 1
 * By Haozheng Du
 * Vigenere is a class that can encrypt and decrypt a message 
 * using given password
 */
import javax.swing.*; 
public class Vigenere implements Encryptable{
  private String msg;
  private String password;
  private boolean encrypted;
  // set the character index of "A" as the starting index
  // all shifts below is based on this starting index
  private final int startingCharIndex = "A".charAt(0); 
  
  // construct the Vigenere with input message and password.
  public Vigenere(String msg, String password){
   this.msg = msg;
   this.password = password;
   this.encrypted = false; // originally not encrypted
  }
  
  // encrypt the message
  public void encrypt(){
   if(!isEncrypted()) // check if the message is encrypted. If not, encrypt it
   {
     msg = msg.replaceAll("\\s",""); // remove all spaces
     msg = msg.toUpperCase(); // change all characters to uppercase
     String masked = ""; // initialize the encrypted string
     
     // for each character in the message, determine its shift, 
     // and construct the encrypted string
     for (int index = 0; index < msg.length(); index++){
       int shiftIndex = (index+1)%(password.length());
       if (shiftIndex == 0)
         shiftIndex = password.length();
       int shift = password.charAt(shiftIndex-1) - startingCharIndex;
       int newCharIndex = startingCharIndex + (msg.charAt(index) + shift -startingCharIndex) % 26;
       masked += (char)(newCharIndex);
    }
    msg = masked;
    this.encrypted = true; // message is encrypted
   }
  }
  
  // decrypt the message
  public String decrypt(){
   if(isEncrypted()){ // check if the message is encrypted. If yes, decrypt it
     String unmasked = ""; // initialize the decrypted string
     
     // for each character in the encrypted message, determine its shift,
     // and reconstruct the original message
     for (int index = 0; index < msg.length(); index++){
       int shiftIndex = (index+1)%(password.length());
       if (shiftIndex == 0)
         shiftIndex = password.length();
       int shift = password.charAt(shiftIndex-1) - startingCharIndex;
       int newCharIndex;
       if (msg.charAt(index) - shift % 26 >= startingCharIndex)
         newCharIndex = msg.charAt(index) - shift % 26;
       else
         newCharIndex = msg.charAt(index) - shift % 26 + 26;
       unmasked += (char)(newCharIndex);
   }
    msg = unmasked; 
    return msg; // return decrypted message
   }
   return msg;
  }
  
  // check if the message is encrypted
  public boolean isEncrypted(){
   return this.encrypted;
  }
  public String toString(){
   return msg;
  }
  public static void main(String[] args){
    
    JFrame frame = new JFrame("Input"); // Create dialog box
    // prompt the user to enter the message
    String msg = JOptionPane.showInputDialog(frame, "What is the message?");
    System.out.println("Message: " + msg);
    // prompt the user to enter the password
    String pw = JOptionPane.showInputDialog(frame, "What is the password?");
    System.out.println("Password: " + pw);
    Vigenere vigenere = new Vigenere(msg, pw);
    vigenere.encrypt(); // encrypt message
    JOptionPane.showMessageDialog(frame, vigenere);
    System.out.println("Encrypted: " + vigenere);
    // ask the user if he/she wants the message decrypted
    int input = JOptionPane.showOptionDialog(null, "Do you want it decrypted?", "Select an Option", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
    // if clicking on yes, decrypt it.
    if(input == JOptionPane.OK_OPTION){
      vigenere.decrypt();
      JOptionPane.showMessageDialog(frame, vigenere);
      System.out.println("Decrypted: " + vigenere);
    }
  }
 }