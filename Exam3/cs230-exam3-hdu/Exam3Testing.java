/* Testing program for EXAM3 - Implementation of GraphPlus
 * By Haozheng Du
 * 
 * I created 5 more test cases below to test each important methods using 
 * simple graphs and have a complicated graph to test everything. (Basic 
 * methods from AdjMatGraph.java is not tested in my test cases because 
 * they have already been fully tested.
*/
import java.io.*;
public class Exam3Testing {
  
  public static void main(String[] args) throws FileNotFoundException {
    
   // Set up simple graph.
    GraphPlus<String> G = new AdjMatGraphPlus<String>();
    //add some vertices
    G.addVertex("A"); G.addVertex("B"); G.addVertex("C");
    G.addVertex("D"); G.addVertex("E"); G.addVertex("F");
    
    //add some edges
    G.addEdge("A", "B"); G.addEdge("B", "C"); G.addEdge("C", "D");
    G.addEdge("F", "A"); G.addEdge("A", "D");
    
    G.addArc("A", "C"); // adding an arc, makes it directed
    G.removeArc("A", "C"); // removing an arc
    
    //test remove vertex
    G.removeVertex("A");
    
    System.out.println("\n*****************************************************\n");
    System.out.println("************* Sample Testing for Exam 3 *************");
    System.out.println("*****************************************************");
    
    System.out.println("Notation: (expected result: )  [actual result]\n");
    
    System.out.println(G);
    
    System.out.println("\n*** Testing DFS and BFS ***");
    try {
     System.out.print("DFS from B [B,C,D] \t: ");
     System.out.println(G.dfsTraversal("B")); 
    } catch (UnsupportedOperationException e) {
     System.out.println("UNIMPLEMENTED");
    }
    try {
    System.out.print("DFS from F     [F] \t: ");
   System.out.println(G.dfsTraversal("F")); 
    } catch (UnsupportedOperationException e) {
     System.out.println("UNIMPLEMENTED");
    }
    try {
    System.out.print("BFS from B [B,C,D] \t: ");
   System.out.println(G.bfsTraversal("B")); 
    } catch (UnsupportedOperationException e) {
     System.out.println("UNIMPLEMENTED");
    }
    try {
    System.out.print("BFS from F     [F] \t: ");
   System.out.println(G.bfsTraversal("F")); 
    } catch (UnsupportedOperationException e) {
     System.out.println("UNIMPLEMENTED");
    }
    
    System.out.println("\n*** Testing clone() ***");
    try {
    System.out.println("CLONED GRAPH (diamond): ");
   System.out.println(G.clone());
    } catch (UnsupportedOperationException e) {
     System.out.println("UNIMPLEMENTED");
    }
    
    System.out.println("\n************* tests on the CS-COURSES graph **************");
    GraphPlus<String> C = AdjMatGraphPlus.fromTGF("cs-courses.tgf");
    try {
    System.out.println("CS graph in DFS: \n(CS111, CS230, CS232, CS231, CS235, CS251, CS242, CS315, CS240):");
   System.out.println(C.dfsTraversal("CS111")); 
    } catch (UnsupportedOperationException e) {
     System.out.println("UNIMPLEMENTED");
    }
    try {
    System.out.println("\nCS graph in BFS: \n(CS111, CS230, CS240, CS232, CS231, CS235, CS251, CS242, CS315):");
   System.out.println(C.bfsTraversal("CS111")); 
    } catch (UnsupportedOperationException e) {
     System.out.println("UNIMPLEMENTED");
    }
    try {
    System.out.println("\nCS-courses with no pre-requisites : \n(MATH225, CS110, CS114, CS111):");
   System.out.println(C.allSources());
    } catch (UnsupportedOperationException e) {
     System.out.println("UNIMPLEMENTED");
    }
    try {
    System.out.println("\nNo followup courses:      \n(CS232, CS231, CS240, CS251, CS242, CS315):");
   System.out.println(C.allSinks());
    } catch (UnsupportedOperationException e) {
     System.out.println("UNIMPLEMENTED");
    }
    System.out.println("\n--------> ONE WAY to satisfy pre-req:");
    try {
   System.out.println("(MATH225, CS110, CS114, CS215, CS111, CS230, CS232, CS231, CS235, CS240, CS251, CS242, CS315):");
     System.out.println(C.listByPriority());
  } catch (GraphCycleException e) {
   System.out.println("  FAILURE:  Stuck in the major forever!  (You erroneously reported a cycle where there is none.)");
    } catch (UnsupportedOperationException e) {
     System.out.println("UNIMPLEMENTED");
   }
    
    System.out.println("\nSAMPLE TESTING COMPLETE");
    
    /**
     * NEW TESTING CODE 
     */
    System.out.println("\n*****************************************************\n");
    System.out.println("************* NEW TESTS FOR EXAM 3 *************");
    System.out.println("*****************************************************\n");
    // Test Case 1
    System.out.println("TEST CASE1: testing isSink() and allSinks() on a simple graph\n");
    try{
      AdjMatGraphPlus<String> g1 = AdjMatGraphPlus.fromTGF("sinks.TGF");
      System.out.println("Is A a sink?(true)  " + g1.isSink("A"));
      System.out.println("Is B a sink?(false)  " + g1.isSink("B"));
      System.out.println("All sinks in this graph:(A,F)  " + g1.allSinks());
    }catch(FileNotFoundException e){
    System.out.println("No such file");
  }
    // Test Case 2
    System.out.println("*****************************************************\n");
    System.out.println("TEST CASE2: testing isSource() and allSources() on a simple graph\n");
    try{
      AdjMatGraphPlus<String> g2 = AdjMatGraphPlus.fromTGF("sources.TGF");
      System.out.println("Is A a source?(true)  " + g2.isSource("A"));
      System.out.println("Is B a source?(false)  " + g2.isSource("B"));
      System.out.println("All sources in this graph:(A,F)  " + g2.allSources());
    }catch(FileNotFoundException e){
    System.out.println("No such file");
  }
    //Test Case 3
    System.out.println("*****************************************************\n");
    System.out.println("TEST CASE3: testing listByPriority() on a simple graph(DAG)\n");
    try{
      AdjMatGraphPlus<String> g3 = AdjMatGraphPlus.fromTGF("DAG.TGF");
      System.out.println("Expected: A B C D E");
      try{
        System.out.println(g3.listByPriority());
      }catch(GraphCycleException e){
        System.out.println("GraphCycleException");
      }
    }catch(FileNotFoundException e){
    System.out.println("No such file");
  }
    // Test Case 4
    System.out.println("*****************************************************\n");
    System.out.println("TEST CASE4: testing Traversals on a simple graph(tree)\n");
    try{
      AdjMatGraphPlus<String> g4 = AdjMatGraphPlus.fromTGF("tree.TGF");
      System.out.println("Depth-First Traversal: (Expected: D E B F G C A)");
      System.out.println(g4.dfsTraversal("A"));
      System.out.println("Breadth-First Traversal: (Expected: A B C D E F G)");
      System.out.println(g4.bfsTraversal("A"));
    }catch(FileNotFoundException e){
    System.out.println("No such file");
  }
    // Test Case 5
    System.out.println("*****************************************************\n");
    System.out.println("TEST CASE5: SANITY TEST (TEST EVERYTHING");
    try{
      AdjMatGraphPlus<String> g5 = AdjMatGraphPlus.fromTGF("test.TGF");
      System.out.println("Original graph shows below:");
      System.out.println(g5);
      System.out.println("Cloning...");
      AdjMatGraphPlus<String> g6 = (AdjMatGraphPlus)g5.clone();
      System.out.println("Cloned graph shows below:");
      System.out.println(g6);
      System.out.println("\nIs E a sink?(true)  " + g5.isSink("E"));
      System.out.println("Is B a sink?(false)  " + g5.isSink("B"));
      System.out.println("All sinks in this graph:(E F)  " + g5.allSinks());
      System.out.println("\nIs A a source?(true)  " + g5.isSource("A"));
      System.out.println("Is B a source?(false)  " + g5.isSource("B"));
      System.out.println("All sources in this graph:(A F)  " + g5.allSources());
      System.out.println("\nIs F isolated?(true)  " + g5.isIsolated("F"));
      System.out.println("\nCreating a list by priority: (Expected: F A C B D E)");
      System.out.println("Note: F is isolated, doesnt matter.");
      try{
        System.out.println(g5.listByPriority());
      }catch(GraphCycleException e){
        System.out.println("GraphCycleException");
      }
      System.out.println("\nDepth-First Traversal: (Expected: E D B C A)");
      System.out.println(g5.dfsTraversal("A"));
      System.out.println("\nBreadth-First Traversal: (Expected: A B C D E)");
      System.out.println(g5.bfsTraversal("A"));
    }catch(FileNotFoundException e){
    System.out.println("No such file");
  }
 }
}