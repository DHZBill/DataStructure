/**
 * CS230 Assignment 8
 * By Haozheng Du
 * This class creates a genealogy tree.
 */ 
import java.util.Iterator;
import javafoundations.*;
import javafoundations.exceptions.*;

public class GenealogyTree<T> implements AncestryTree<T>, Iterable<T>{
  protected BTNode<T> root;
  
  // Constructs a tree with a given root.
  public GenealogyTree(T element){
    this.root = new BTNode<T>(element);
  }
 
  //  Returns the element stored in the root (aka CoTU) of the tree.
  public T getCoTU(){
    return root.getElement();
  }; 
  
  // Finds the node that holds the given element in the tree.
  // Throws exception if target not found.
  public BTNode<T> find (T target) {
    BTNode<T> node = null;
    if (root != null)
      node = root.find(target);
    if (node == null)
      throw new ElementNotFoundException("Find operation failed. "
                                           + "No such element in tree.");
    return node;
  }
  
  //  Returns the member that is the offspring of target.
  //  Returns null as the offspring of the root.
  public T getOffspring(T target){
    BTNode node = find(target);
    return (T) node.getParent().getElement();
  }
  
  //  Returns the member that is the left child of target.
  //  Returns null if the left child does not exist.
  public T getPater(T target){
    BTNode node = find(target);
    return (T) node.getLeft().getElement();
  } // Paternal ancestors are on the left
  
  //  Sets the left child of the tree target to lchild.
  //  It throws an exception if target is not already in the tree
  public void setPater(T target, T lchild){
    BTNode node = find(target);
    BTNode newNode = new BTNode(lchild);
    newNode.setParent(node);
    node.setLeft(newNode);
  }
  
  //  Returns the element that is the right child of target.
  //  Returns null if the right child does not exist
  public T getMater(T target){
    BTNode node = find(target);
    return (T) node.getRight().getElement();
  } // Maternal ancestors are on the right
  
  //  Sets the right child of target to rchild.
  //  It throws an exception if target is not already in the tree
  public void setMater(T target, T rchild){
    BTNode node = find(target);
    BTNode newNode = new BTNode(rchild);
    newNode.setParent(node);
    node.setRight(newNode);
  }
  
  //  Returns true if the tree contains an element that
  //  matches the specified target element and false otherwise.
  public boolean appears (T target){
    BTNode<T> node = null;
    if(root!=null)
      node = root.find(target);
    if(node == null)
      return false;
    return true;
  }
    
  // Returns true if the two members share a grandchild,
  // and false if they are not or if a shared grandchild does not exist
  // Two grandparents that share a grandchild are "inLaws"
  public boolean inLaws(T gp1, T gp2){
    try{
      return getOffspring(getOffspring(gp1)).equals(getOffspring(getOffspring(gp2)));
    }catch(NullPointerException e){
      return false;
    }
  }
    
  //  Returns the number of members in this ancestral tree.
  public int size(){
    int result = 0;
    if(root!=null)
      result = root.count();
    return result;
  }
  
  //  Returns the string representation of the binary tree,
  // one line per level.
  public String toString(){
    LinkedQueue<BTNode<T>> queue = new LinkedQueue<BTNode<T>>();
    String s = "";
    int levelNodes =0; 
    if(root==null) 
      return s;
    queue.enqueue(root);
    // Level-order traverse through the tree, print in a new line for each level
    while(!queue.isEmpty()){
      levelNodes = queue.size();
      while(levelNodes>0){
        BTNode n = (BTNode)queue.dequeue();
        s+= " " + n.getElement();
        if(n.getLeft()!=null) queue.enqueue(n.getLeft());
        if(n.getRight()!=null) queue.enqueue(n.getRight());
        levelNodes--;
      }
      s+="\n";
    }
    return s;
  }
    
  //  Returns an iterator that contains a level-order traversal
  // on the ancestral tree.
  public Iterator<T> byGenerations(){
    LinkedQueue<BTNode<T>> queue = new LinkedQueue<BTNode<T>>();
    ArrayIterator<T> iter = new ArrayIterator<T>();
    
    if (root != null) {
      queue.enqueue(root);
      while (!queue.isEmpty()) {
        BTNode<T> current = queue.dequeue();
        
        iter.add (current.getElement()); //adds the element to iterator.
        
        if (current.getLeft() != null)
          queue.enqueue(current.getLeft());
        if (current.getRight() != null)
          queue.enqueue(current.getRight());
      }
    }
    return iter;
  }
  public Iterator<T> iterator(){
    ArrayIterator<T> iter = new ArrayIterator<T>();
    return iter;
  }
  public static void main(String[] args){
    String e1 = "D";
    String e2 = "E";
    String e3 = "B";
    String e4 = "I";
    String e5 = "A";
    String e6 = "F";
    String e7 = "K";
    String e8 = "G";
    String e9 = "C";
    String e10 = "H";
    String e11 = "J";
    
    GenealogyTree<String> tree = new GenealogyTree<String>(e1);
    
    tree.setMater(e1,e3);
    tree.setPater(e1,e2);
    System.out.println("Current Tree: \n" + tree);
    System.out.println("Tree size: " + tree.size());
    System.out.println("The tree contains I: " + tree.appears(e4));
    System.out.println(tree);
    tree.setMater(e2,e5);
    tree.setPater(e2,e4);
    tree.setPater(e3,e6);
    tree.setMater(e3,e7);
    System.out.println("Current Tree: \n" + tree);
    System.out.println("The tree contains I: " + tree.appears(e4));
    System.out.println("Offspring of A: " + tree.getOffspring(e5));
    System.out.println("I and F are in-laws: " + tree.inLaws(e4,e6));
    System.out.println("I and E are in-laws: " + tree.inLaws(e4,e2));
    System.out.println("Testing byGeneration():");
    Iterator<String> ai = tree.byGenerations();
    while(ai.hasNext())
      System.out.print(ai.next());
  }
}