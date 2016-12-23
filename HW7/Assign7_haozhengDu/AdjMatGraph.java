import java.util.*;
import java.io.*;
public class AdjMatGraph<T> implements Graph<T>{
 private final int NOT_FOUND = -1;
 private final int DEFAULT_CAPACITY = 1;
 private int n;
 private int m;
 private boolean[][] arcs;
 private T[] vertices;
 
 // create empty graph
 public AdjMatGraph(){
  n = 0;
  this.arcs = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
  this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
 }
 // read graph
 public AdjMatGraph(String fileName){
  n = 0;
  this.arcs = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
  this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
  try{
   Scanner s = new Scanner(new File(fileName));
   T current = (T)s.next();
   while(!current.equals("#")){
    current = (T)s.next();
    if (n == vertices.length - 1){
     expandVerticesCapacity();
     expandArcsCapacity();
    }
    vertices[n+1] = current;
    n++;
    current = (T)s.next();
   }
   while(s.hasNext()){
    int i = s.nextInt();
    int j = s.nextInt();
    arcs[i][j] = true;
   }
  }
  catch (IOException e){
   System.out.println(e);
  }
 }
 
 // check if the graph is empty
 public boolean isEmpty(){
   return n==0;
 }
 
 // returns the number of vertices
 public int n(){
   return n;
 }
 
 // returns the number of arcs
 public int m(){
   m = 0;
   if (!isEmpty()){
     for(int i = 1; i <= n; i++){
       for(int j = 1; j <= n; j++){
         if (arcs[i][j] = true)
           m++;
       }
     }
   }
   return m;
 }
 
 // return the index of the vertex
 public int getIndex (T vertex){
   int i = 0;
   for(int j = 1; j <= n; j++){
     if(vertex.equals(vertices[j]))
       i = j;
   }
   return i;
 }
 
 // add a new vertex to the graph
 public void addVertex(T vertex){
   if(getIndex(vertex)==0){
     if(n == vertices.length - 1){
       expandVerticesCapacity();
       expandArcsCapacity();
     }
     vertices[n+1] = vertex;
     n++;
   }
 }
 
 // when removing vertex, remove the column and row
 private void removeRowAndColumn (int index) {
    for (int i=1; i<=n; i++){ 
      for (int j=index; j<=n; j++){
       arcs[i][j]=arcs[i][j+1];
      } 
    }
    for (int z=index; z<=n; z++){ 
      for (int y=1; y<=n; y++){
       arcs[z][y]=arcs[z+1][y];
      } 
    }
  }
 
 // removes the vertex
 public void removeVertex(T vertex){
   int index = getIndex(vertex); 
    if (index!=0) { 
      removeRowAndColumn(index);
      for(int i=index; i<=n; i++){ 
          vertices[i]=vertices[i+1];
      }
      n--;
    }
  }
 
 // add an arc
 public void addArc(T v0, T v1){
   if(getIndex(v0) != 0 && getIndex(v1) != 0)
     arcs[getIndex(v0)][getIndex(v1)] = true;
 }
 
 //add an edge
 public void addEdge (T v0, T v1) {
    addArc(v0, v1);
    addArc(v0, v1);   
  }
 
 // check if theres an arc 
 public boolean isArc (T v0, T v1) {
    int index0=getIndex(v0); 
    int index1=getIndex(v1);
    if (index0==0 || index1==0)
      return false;
    return (arcs[index0][index1]);
  }
 // check if there is an edge
 public boolean isEdge(T v0, T v1){
   return (isArc(v0, v1) && isArc(v1, v0));
 }
 // remove an arc
 public void removeArc(T v0, T v1){
   if (isArc(v0,v1))
     arcs[getIndex(v0)][getIndex(v1)] = false;
 }
 // remove an edge
 public void removeEdge(T v0, T v1){
   if(isEdge(v0, v1)){
     removeArc(v0,v1);
     removeArc(v1,v0);
   }
 }
 // check if the graph is undirected
 public boolean isUndirected(){
    for (int i = 1; i <= n; i++){
      for (int j = 1; j <= n; j++){
        if (i != j){
          if (arcs[i][j] != arcs[j][i])
            return false;
        }
      }
    }
    return true;
  }

 public String toString() {
    String s = "";
    s += "Number of vertices: " + n + "\n";
    s += "Number of arcs: " + m() + "\n";
    s += "Arcs" + "\n" + "---------" + "\n";
    s += "i" + " ";
    for (int z = 1; z<=n; z++) {
     s += vertices[z] + " "; 
    }
    s += "\n";
    for (int i = 1; i<=n; i++) {
      s += vertices[i] + " " ;
      for (int j = 1; j<=n; j++){
        if (arcs[i][j]==true)
          s += "1" + " ";
        else 
          s += "-" + " ";
      }
      s += "\n";
    }
    return s;
  }

 public void saveTGF(String tgf_file) {
    try {   // Set up the output file stream. 
      PrintWriter writer = new PrintWriter(new FileWriter(tgf_file));
      String s= "";
      for (int i = 1; i<=n; i++) {
        s += i + " " + vertices[i] + "\n"; //Add vertex indices and vertices, separated by a space
      }
      s +="#\n"; //Separate vertices and arcs by the pound (#) sign
      for (int i = 1; i<=n; i++) {
        for (int j = 1; j<=n; j++){
          if (arcs[i][j]==true) //If arc exists, add the indices in 2D arc array
            s += i + " " + j + "\n";
        }
      }
      writer.println(s);
      writer.close();
    }
    catch (IOException e) {
      System.out.println (e);
    }  
  }
 private void expandVerticesCapacity() {
    T[] temp = (T[])new Object[vertices.length*2];
    for (int i=1; i <= n; i++){ //Again, elements in vertices array inserted at index 1
     temp[i]=vertices[i];
    }
    vertices = temp;
  }
  
  /**
   * Private helper method. Doubles the size of the arcs array by creating a temp array twice as large
   * and copying the arcs array into the new temp array
   */
 private void expandArcsCapacity() {
   boolean [][]temp = new boolean[arcs.length*2][arcs.length*2];
   for (int i=0; i < arcs.length; i++){
     for (int j=0; j< arcs.length; j++) {
       temp[i][j]=arcs[i][j];
     }
   }
   arcs = temp;
 }
    /** Retrieve from a graph the vertices adjacent to vertex v.
     Assume that the vertex is in the graph */
  public LinkedList<T> getSuccessors(T vertex) {
    int index=getIndex(vertex);
    LinkedList<T> successors = new LinkedList<T>();
    for (int i=1; i<=n; i++){
      if (arcs[index][i])
        successors.add(vertices[i]);
    }
    return successors;
  }

  /** Retrieve from a graph the vertices x preceding vertex v (x->v)
    and returns them onto a linked list */
  public LinkedList<T> getPredecessors(T vertex) {
    int index=getIndex(vertex);
    LinkedList<T> predecessors = new LinkedList<T>();
    for (int i=1; i<=n; i++){
      if (arcs[i][index])
        predecessors.add(vertices[i]);
    }
    return predecessors;  
  }
 public static void main (String args[]) {

    AdjMatGraph<String> graphFromTGF = new AdjMatGraph<String>("Sample.tgf");
    System.out.println("Sample.tgf\n" + graphFromTGF);
    System.out.println("isArc A <--> C? (TRUE): " + graphFromTGF.isEdge("A", "C"));
    System.out.println("isArc A -> C? (TRUE): " + graphFromTGF.isArc("A", "C"));
    System.out.println("isEdge A <-> C? (TRUE): " + graphFromTGF.isEdge("A", "C"));
    System.out.println("isEdge  D <--> E? (FALSE): " + graphFromTGF.isEdge("D", "E"));
    System.out.println("isArc D -> E? (TRUE): " + graphFromTGF.isArc("D", "E"));
    System.out.println("isArc E -> D? (FALSE): " + graphFromTGF.isArc("E", "D"));
    
    System.out.println("Removing vertex A. Adding edge B<-->D");
    graphFromTGF.removeVertex("A");
    graphFromTGF.addEdge("B", "D");
    System.out.println(graphFromTGF);
    
    System.out.println("The predecessors of B are: " + graphFromTGF.getPredecessors("B"));
    System.out.println("The successors of B are: " + graphFromTGF.getSuccessors("B"));
    System.out.println("The predecessors of E are: " + graphFromTGF.getPredecessors("E"));
    System.out.println("The successors of E are (E has no successors): " + graphFromTGF.getSuccessors("E"));
    
    System.out.println("Adding A (at the end of vertices). Adding B (should be ignored).");
    graphFromTGF.addVertex("A");
    graphFromTGF.addVertex("B");
    System.out.println("Saving the graph into BCDEA.tgf");
    graphFromTGF.saveTGF("BCDEA.tgf");
    
    System.out.println("The graph is isUndirected (FALSE): " + graphFromTGF.isUndirected());
    System.out.println("Adding E->D to make the graph undirected.");
    graphFromTGF.addArc("E", "D");
    System.out.println("isUndirected? (TRUE): " + graphFromTGF.isUndirected());
    
    System.out.println("The index of E is: " + graphFromTGF.getIndex("E") +"\n"); 
    
    //Test constructor with no parameters
    System.out.println("Construct a graph from an empty graph");
    AdjMatGraph<String> myGraph = new AdjMatGraph<String>();
    System.out.println("myGraph is empty (TRUE): " + myGraph.isEmpty());
    System.out.println("Adding vertices L, M, N, O, P, Q");
    myGraph.addVertex("L");
    myGraph.addVertex("M");
    myGraph.addVertex("N");
    myGraph.addVertex("O");
    myGraph.addVertex("P");
    myGraph.addVertex("Q");
    System.out.println(myGraph);
    System.out.println("Adding edges M<-->N, M<-->P. Adding arc L->O.");
    myGraph.addEdge("M", "N");
    myGraph.addEdge("M", "P");
    myGraph.addArc("L", "O");
    System.out.println(myGraph);
    System.out.println("The 0th position in the vertices array is always null: " + Arrays.toString(myGraph.vertices));
    myGraph.saveTGF("LMNOPQ.tgf");
  }
 
}
 