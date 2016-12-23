package javafoundations;

import javafoundations.exceptions.*;

/**
*  LinkedStack.java       Java Foundations
*
*  Represents a linked implementation of a stack.
*/

public class LinkedStack<T> implements Stack<T>
{
   private int count;
   private LinearNode<T> top;

   /**
   *  Creates an empty stack using the default capacity.
   */
   public LinkedStack()
   {
      count = 0;
      top = null;
   }

   /**
   *  Removes the element at the top of this stack and returns a
   *  reference to it. Throws an EmptyCollectionException if the
   *  stack contains no elements.
   * 
   * @return the element at the top of the stack
   */
   public T pop() throws EmptyCollectionException
   {
      if (count == 0)
         throw new EmptyCollectionException ("Pop operation failed. "
            + "The stack is empty.");

      T result = top.getElement();
      top = top.getNext();
      count--;

      return result;
   }

   /**
   *  Returns a string representation of this stack.
   * 
   * @return the elements in the stack
   */
   public String toString()
   {
      String result = "<top of stack>\n";
      LinearNode current = top;

      while (current != null)
      {
         result += current.getElement() + "\n";
         current = current.getNext();
      }

      return result + "<bottom of stack>";
   }
   // push a new item to the linked stack
   public void push (T newItem) {
     // if the linked stack is empty, set newItem as top element
     if(count == 0){
       top = new LinearNode<T>();
       top.setElement(newItem);
       count++;
     }
     // if not empty, create a new node and set newItem as the element
     else{
       LinearNode<T> oldTop = top;
       top = new LinearNode<T>();
       top.setElement(newItem);
       top.setNext(oldTop);
       count++;
     }
   }
   // return the element at the top
   public T peek () throws EmptyCollectionException {
     if(count == 0)
       throw new EmptyCollectionException("Peek operation failed. " + "The stack is empty.");
     T result = top.getElement();
     return result;
   }
   // check if the linked stack is empty
   public boolean isEmpty() {
     return count == 0;
   }
   // return the size of the linked stack
   public int size() {
     return count;
   }
}
