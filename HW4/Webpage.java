/**
 * CS230 Assignment 4 Task 2
 * By Haozheng Du
 * Webpage is a class that gets the content from a given url, 
 * counts the lines of the content, and constructs them into a Webpage Object.
 */
import java.net.URL;
import java.util.Scanner;
import java.io.IOException;

public class Webpage implements Comparable<Webpage>{
 private String URLname;
 private int lineCounter;
 private String pageContent;
 
 // construct the Webpage when only URL is provided. I added this constructor
 // because it is better handling the Webpage construction from a URL.
 public Webpage(String URLname){
   lineCounter = 0;
   String content = "";
   this.URLname = URLname;
   
   // get content from the url
   try {
     URL u = new URL(URLname);
     Scanner urlScan = new Scanner( u.openStream() ); // will throw exception
     while (urlScan.hasNext()) {
       String line = urlScan.nextLine();
       lineCounter++;
       content += line;
     }
   } catch (IOException ex) {
     System.out.println(ex);
   }
   this.pageContent = content; // update content
   this.lineCounter = lineCounter; // update number of lines

 }

 // constructor with given url, number of lines, and content
 public Webpage(String URLname, int lineCounter, String pageContent){
   this.URLname = URLname;
   this.lineCounter = lineCounter;
   this.pageContent = pageContent;
 }
 
 // compare the number of lines to another Webpage
 public int compareTo(Webpage p){
   int cmp = this.lineCounter > p.lineCounter ? +1 : // return 1 if having more lines
             this.lineCounter < p.lineCounter ? -1 : //return -1 if having less lines
             0; // return 0 if having same number of lines
   return cmp;
 }
 
 // get lineCounter
 public int getLineCounter(){
   return lineCounter;
 }
 
 // get pageContent
 public String getPageContent(){
   return pageContent;
 }
 
 // get url;
 public String getURLName(){
   return URLname;
 }
 
 // set pageContent
 public void setPageContent(String content){
   this.pageContent = content;
 }
 
 // set url
 public void setURLName(String name){
   this.URLname = name;
 }
 
 // set lineCounter
 public void setLineCounter(int value){
   this.lineCounter = value;
 }
   
 public String toString(){
   if(this.pageContent.length()<=20)
     return (this.URLname + "  : " + 
             this.lineCounter + "  : " +
             this.pageContent );
   return (this.URLname + "  : " + 
           this.lineCounter + "  : " +
           this.pageContent.substring(0,20) + 
           "...");

 }
 public static void main(String[] args){
   // construct a Webpage with given url and print it out
   String url = "http://www.google.com";
   Webpage webpage = new Webpage(url); 
   System.out.println(webpage);
 }
}