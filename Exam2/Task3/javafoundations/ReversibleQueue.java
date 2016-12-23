//********************************************************************
//  ReversibleQueue.java       Java Foundations
//  
//  CS230 Midterm Exam 2 Task 3
//  By Haozheng Du
//  I believe this program implements all features and works well.
//  Represents a reversible linked implementation of a queue.
//********************************************************************

package javafoundations;
import javafoundations.exceptions.*;
import java.util.*;

public class ReversibleQueue<T> extends LinkedQueue<T>{
  
  public ReversibleQueue(){
    super();
  }
  /**
   * Reverses the elements stored in the queue.
   */
  public void reverse(){
    Stack<T> stack = new Stack<T>(); // creates a Stack.
    // dequeue all the elements in the queue, and then push them into the stack.
    while(!isEmpty())
      stack.push(dequeue());
    // pop all the elements in the stack, and then enqueue them into the queue.
    while(!stack.empty())
      enqueue(stack.pop());
  }
  
  /**
   * Testing reversible queue
   */ 
  public static void main(String[] args){
    ReversibleQueue<String> rq = new ReversibleQueue<String>();
    rq.enqueue("Amber");
    rq.enqueue("Bill");
    rq.enqueue("Cindy");
    System.out.println("Original queue: \n" + rq + "\n");
    rq.reverse();
    System.out.println("Reversed queue: \n" + rq);
  }
}