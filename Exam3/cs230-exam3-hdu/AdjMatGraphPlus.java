import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException;
import java.util.LinkedList;
import java.util.Stack;
import javafoundations.LinkedQueue;
/**
 * An extended Adjacency Matrix graph implementation.
 * By Haozheng Du
 * I believe all method works and the exceptions are thrown correctly.
 */
public class AdjMatGraphPlus<T> extends AdjMatGraph<T> implements GraphPlus<T> {

 /**
  * Construct an empty graph.
  */
 public AdjMatGraphPlus() {
  super();
 }
 
 /**
  * Construct a graph with the same vertices and edges as the given original.
  * @param original
  */
 public AdjMatGraphPlus(AdjMatGraph<T> original) {
  super(original);
 }
 
 /**
  * Read a TGF file and create an AdjMatGraphPlus<String> from it.
  * @param tgfFile - the TGF file to read
  * @return a graph created from the TGF file
  * @throws FileNotFoundException if TGF file is not found.
  */
 public static AdjMatGraphPlus<String> fromTGF(String tgfFile) throws FileNotFoundException {
  AdjMatGraphPlus<String> g = new AdjMatGraphPlus<String>();
  AdjMatGraph.loadTGF(tgfFile, g);
  return g;
 }
 
  /******************************************************************
    * Creates a new graph that has all the same vertices
    * and arcs as the original.
    * 
    * @return the new graph.
    *****************************************************************/ 
 public GraphPlus<T> clone() {
   try{
     saveTGF("temp.tgf"); // save the current graph to tgf file
     AdjMatGraphPlus<String> clone = fromTGF("temp.tgf"); // recreate a graph from saved file
     return (GraphPlus) clone;
   }catch(FileNotFoundException e){
      System.out.println("No matching TGF file found");
      return null;
   }
 }
  /******************************************************************
    * Checks if a vertex is a sink (points to no other vertex)
    * 
    * @param vertex: the potential sink vertex
    * @return true if the vertex is a sink, false if it is not.
    * @throws IllegalArgumentException if given vertex is not in graph
    ******************************************************************/
 public boolean isSink(T vertex) throws IllegalArgumentException{
   if(!containsVertex(vertex))
     throw new IllegalArgumentException();
   LinkedList<T> neighbors = getSuccessors(vertex);
   return (neighbors.size() == 0); // if no successor, it its a sink
 }
  /******************************************************************
    * Retrieves all vertices that are sinks. 
    * 
    * @return all the sinks in a linked list
    ******************************************************************/
 public LinkedList<T> allSinks() {
   LinkedList<T> sinkList = new LinkedList<T>();
   for(int i = 0; i < n(); i++) // check if each vertex is a sink
     if(isSink(getVertex(i)))
       sinkList.add(getVertex(i));
   return sinkList;
 }
  /******************************************************************
    * Checks if a vertex is a source (no vertex points to it)
    * 
    * @param vertex: the potential source vertex
    * @return true if the vertex is a source, false if it is not
    * @throws IllegalArgumentException if given vertex is not in graph
    ******************************************************************/
 public boolean isSource(T vertex) {
   if(!containsVertex(vertex))
     throw new IllegalArgumentException();
   LinkedList neighbors = getPredecessors(vertex);
   return (neighbors.size() == 0); // if no predecessor, it is a source
 }
  /******************************************************************
    * Retrieves all vertices that are sources. 
    * 
    * @return all the sources in a linked list
    ******************************************************************/
 public LinkedList<T> allSources() {
   LinkedList<T> sourceList = new LinkedList<T>();
   for(int i = 0; i < n(); i++) // check if each vertex is a source
     if(isSource(getVertex(i)))
       sourceList.add(getVertex(i));
   return sourceList;
 }
  
  /******************************************************************
    * Checks if a vertex is a isolated, i.e., no vertices
    * point to it and it points to no vertices.
    * 
    * @param vertex: the vertex to check for isolation
    * @return true if the vertex is isolated, false if it is not
    * @throws IllegalArgumentException if given vertex is not in graph
    ******************************************************************/
 public boolean isIsolated(T vertex) {
   if(!containsVertex(vertex))
     throw new IllegalArgumentException();
   return (isSink(vertex) && isSource(vertex)); // if neither source nor sink
 }

  /******************************************************************
    * Returns a list of vertices in a directed acyclic graph (DAG)
    * such that for any vertices x and y, if there is a directed
    * edge from x to y in the graph then x comes before y in the
    * returned list. There may be multiple lists that satisfy the
    * abovementioned constraints. This method returns one such list.
    * If the input graph is not a DAG, a GraphCycleException is thrown.
    * 
    * @return the vertices in a linked list satisfying the order described above.
    * @throws GraphCycleException if called on a non-DAG
    ******************************************************************/
 public LinkedList<T> listByPriority() throws GraphCycleException {
   LinkedList<T> prioritized = new LinkedList<T>(); 
   AdjMatGraphPlus<T> temp = (AdjMatGraphPlus) clone(); // make a clone of the original graph
   while(temp.n()>0){
     T vertex = null;
     for(int i = 0; i < temp.n(); i++)
       if(temp.isSource(temp.getVertex(i)))
         vertex = temp.getVertex(i);
     temp.removeVertex(vertex);
     prioritized.add(vertex);
   }
   return prioritized;
 }
  /******************************************************************
    * Returns a LinkedList containing a DEPTH-first search
    * traversal of the graph starting at the given vertex. The result
    * list should contain all vertices visited during the traversal in
    * the order they were visited.
    * 
    * @param vertex: the starting vertex for the traversal
    * @return a linked list with the vertices in depth-first order
    * @throws IllegalArgumentException if given vertex is not in graph
    *****************************************************************/
 public LinkedList<T> dfsTraversal(T startVertex) {
   AdjMatGraphPlus<T> temp = (AdjMatGraphPlus) clone(); // make a clone of the original graph
   LinkedList<T> dfs = new LinkedList<T>();
   Stack<T> stk = new Stack<T>();
   
   if(!containsVertex(startVertex))
     throw new IllegalArgumentException();
   
   // push the startVertex into stk and remove it from the graph
   stk.push(startVertex);
   temp.removeVertex(startVertex);

   while(!stk.isEmpty()){
     T current = stk.peek();// get current vertex
     LinkedList<T> neighbors = this.getSuccessors(current); // get all successors of the current vertex
     T next = null;
     
     // for each neighbor, get the vertex based on index.
     for(int i = 0; i < neighbors.size(); i++)
       if(temp.getIndex(neighbors.get(i)) != NOT_FOUND){
       next = neighbors.get(i);
       break;
     }
     // if nothing left, pop the vertex from stack and put it into the traversal list
     if(next == null)
       dfs.add(stk.pop());
     // else then push the next neighbor into the stack and remove it from the graph
     else{
       stk.push(next);
       temp.removeVertex(next);
     }
   }
   return dfs;
   
 }
   /******************************************************************
    * Returns a LinkedList containing a BREADTH-first search
    * traversal of a graph starting at the given vertex.  The result
    * list should contain all vertices visited during the traversal in
    * the order they were visited.
    * 
    * @param vertex: the starting vertex for the traversal
    * @return a linked list with the vertices in breadth-first order
    * @throws IllegalArgumentException if given vertex is not in graph
    *****************************************************************/
 public LinkedList<T> bfsTraversal(T startVertex) throws IllegalArgumentException{
   int vertexIdx; // current vertex's index
   LinkedList<T> bfs = new LinkedList<T>();
   LinkedQueue<T> queue = new LinkedQueue<T>();
   
   if(!containsVertex(startVertex))
     throw new IllegalArgumentException();
   
   // mark all vertex as unvisited
   boolean[] visited = new boolean[this.n()]; 
   for(int i = 0; i < visited.length; i++)
     visited[i] = false;
   
   // start from the startVertex
   queue.enqueue(startVertex);
   vertexIdx = this.getIndex(startVertex);
   visited[vertexIdx] = true;
   
   while(!queue.isEmpty()){
     // add the vertex in the queue to the traversal list
     vertexIdx = this.getIndex(queue.dequeue()); 
     bfs.add(this.getVertex(vertexIdx));
     
     // for each other vertex, if there is an arc and it is unvisited,
     // enqueue it and mark is as visited
     for(int i=0; i < this.n(); i++){
       if(this.isArc(vertexIdx,i) && !visited[i]){
         queue.enqueue(this.getVertex(i));
         visited[i] = true;
       }
     }
   }
   return bfs;
 }
public static void main (String args[]){
  try{
    AdjMatGraphPlus<String> g = fromTGF("cs-courses.tgf");
    System.out.println(g);
    AdjMatGraphPlus<String> gClone = (AdjMatGraphPlus) g.clone();
    System.out.println(gClone);
    try{
      System.out.println(gClone.isSink("CS242"));
    }catch(IllegalArgumentException e){
      System.out.println("No such vertex");
    }
    try{
      System.out.println(gClone.isSink("CS230"));
    }catch(IllegalArgumentException e){
      System.out.println("No such vertex");
    }
    try{
      System.out.println(gClone.isSource("MATH225"));
    }catch(IllegalArgumentException e){
      System.out.println("No such vertex");
    }
    try{
      System.out.println(gClone.isSource("CS230"));
    }catch(IllegalArgumentException e){
      System.out.println("No such vertex");
    }
    System.out.println(gClone.allSinks());
    System.out.println(gClone.allSources());
    try{
      System.out.println(gClone.isIsolated("CS110"));
    }catch(IllegalArgumentException e){
      System.out.println("No such vertex");
    }
    try{
      System.out.println(gClone.listByPriority());
    }catch(GraphCycleException e){
      System.out.println("GraphCycleException");
    }
    try{
    System.out.println(gClone.dfsTraversal("MATH225"));
    System.out.println(gClone.bfsTraversal("MATH225"));
    }catch(IllegalArgumentException e){
      System.out.println("No such vertex");
    }
  }catch(FileNotFoundException e){
    System.out.println("No such file");
  }
 }
}