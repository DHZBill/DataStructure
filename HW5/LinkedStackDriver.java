/**
 * Class LinkedStackDriver
 * Created by Haozheng Du
 * This is a testing class for class LinkedStack.
 */
import javafoundations.*;

public class LinkedStackDriver{
  public static void main(String[] args){
    LinkedStack<String> linkedStack = new LinkedStack<String>();
    System.out.println("Empty? " + linkedStack.isEmpty()+ "\n");
    System.out.println("Add a element");
    linkedStack.push("Element1");
    System.out.println(linkedStack+"\n");
    System.out.println("Add 2 more elements");
    linkedStack.push("Element2");
    linkedStack.push("Element3");
    System.out.println(linkedStack+"\n");
    System.out.println("Peeking:\n" + linkedStack.peek());
    System.out.println("Size: " + linkedStack.size());
  }
}