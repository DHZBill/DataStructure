/**
 * CS230 Assignment 4 Task 2
 * By Haozheng Du
 * Cyberspace is a sorted collection of Webpages.
 */
import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class Cyberspace{
 private Webpage[] collection; // an array of Webpages
 private static int size; // the maximum number of Webpages it can hold
 private int index; // the current number of Webpages
 public Cyberspace(int size){
  collection = new Webpage[size];
  index = 0;
 }

 // insert a Webpage while maintaining the sorted order
 public void addWebpage(Webpage p){
   Webpage[] copyOfCollection = collection.clone(); // create a copy of the collection
   // if there is no Webpage in the collection, just add it. 
   if (index == 0){
      collection[index] = p;
      index ++;
    }
   // if there already is, insert the new Webpage while the collection is sorted 
   // by number of lines each Webpage holds. 
    else{
      Integer originalIndex = new Integer(index); // a copy of the original index
      // check from the beginning if the new Webpage should be inserted somewhere
      // based on number of lines. Once find the place, insert it and append the 
      // later Webpages.
      for (int i = 0; i < index; i++){
        if(p.compareTo(collection[i])==-1){
          index++; 
          collection[i] = p;
          for (int j = i; j< index; j++){
            collection[j+1] = copyOfCollection[j];}
         break;
        }
    }
      // if the Webpage holds the largest number of lines among the whole collection,
      // append it to the collection
      if (index == originalIndex){
        collection[index] = p;
        index++;
      }
  }
 }
 
 public String toString(){
   String s = "";
   for(int i = 0; i < index; i++)
     s = s + collection[i] +"\n";
   return s;
     
 }
 
 // prompt the user for urls
 public static String[] readKeyboardInputURL () {
   String keyboardIn = "";
   Scanner keyboardScan = new Scanner (System.in); // will not throw
   do
   {
     String line = keyboardScan.nextLine();
     keyboardIn = keyboardIn + line + " ";
   } while (keyboardScan.hasNext());
   String[] url = keyboardIn.split(" ");
   return url; // return an Array of urls
}
 
 // read the urls from a file given the file name
 public static String readFileURL (String inFileName) {
   try {
     Scanner reader = new Scanner(new File(inFileName));
     StringBuilder builder = new StringBuilder(); // Accumulate lines in StringBuilder
     while (reader.hasNext()) { // Continue until we reach end of input file
       builder.append(reader.nextLine());
       builder.append("\n"); // nextLine() omits newline, so add it back
     }
     reader.close(); // Close the file reader
     return builder.toString();
   } catch (IOException ex) {
     System.out.println(ex); // Handle file-not-found by displaying message
     return ""; // Return the empty string if file not found
   }
 }
 
 public static void main(String[] args){
   // test given url in main method
   System.out.println("Test given url in main method:\n");
   Cyberspace cyberspace1 = new Cyberspace(10);
   Webpage p1 = new Webpage("http://www.google.com");
   Webpage p2 = new Webpage("http://www.wellesley.edu");
   Webpage p3 = new Webpage("http://www.bing.com");
   cyberspace1.addWebpage(p1);
   cyberspace1.addWebpage(p2);
   cyberspace1.addWebpage(p3);
   System.out.println(cyberspace1);
   
   // test reading url from user input
   System.out.println("Test keyboardIn:\n");
   Cyberspace cyberspace2 = new Cyberspace(10);
   String[] keyboardURL = readKeyboardInputURL();
   for (int i = 0; i<keyboardURL.length;i++){
     Webpage p = new Webpage(keyboardURL[i]);
     cyberspace2.addWebpage(p);
   }
    System.out.println(cyberspace2);
    
    // test reading url from file
    System.out.println("Test URL from File:\n");
    Cyberspace cyberspace3 = new Cyberspace(10);
    String fileURLs = readFileURL("URL");
    String[] fileURL = fileURLs.split("\n");
    for (int i = 0; i<fileURL.length;i++){
      Webpage p = new Webpage(fileURL[i]);
      cyberspace3.addWebpage(p);
  }
    System.out.println(cyberspace3);
 }
}