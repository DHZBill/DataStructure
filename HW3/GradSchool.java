/**
 * GradSchool.java
 * CS230 Assignment 3
 * @author HaoZheng Du, Jee Hyun Kim
 * Last Modified Date: 24 September 2016
 * <p>
 * GradSchool creates a collection of School object 
 * and has various methods to add schools, compute the ratings, 
 * determine and display the ranking based on the given factor
 */

public class GradSchool{


 private School [] collection; // a collection of schools
 private static int index = 0; // current number of schools in the collection

 public GradSchool(){
  this(100); //creates array of 100 schools if the size is not specified
 }

 public GradSchool(int size){
  collection = new School[size];
 }

 /**
  * updates school information if the school is already in database
  * or add new school to grad school collection if the shcool is not in database
  * comparing school name is not case sensitive
  */
 public void addSchool(String name, int academics, int research, int pubs){
  for(int i = 0; i<index; i++){
   if (collection[i].getName() == name){ //case sensitive
    collection[i].setRateAcademics(academics);
    collection[i].setRateResearch(research);
    collection[i].setRatePubs(pubs);
    //for cases which are added after computing rank and overall rating
    collection[i].setRankValue(0);
    collection[i].setOverallRating(0, 0, 0);
    return;
   }
  }
  //adds new school if school is not in collection
  School s = new School(name, academics, research, pubs);
  collection[index++] = s;
 }

 /**
  * compute the overall ratings for each school in the collection based on given weights
  */
 public void computeRatings(int weightAcademics, int weightResearch, int weightPubs){
  for (int i = 0; i<index; i++){
   collection[i].computeRating(weightAcademics, weightResearch, weightPubs);
  }
 }
 /**
  * display the school names by rank based on a given factor.
  */
 public void displayRankResults(String factor){
  rankSchools(factor); // rank all the schools based on the factor
  System.out.println("\nRanking of schools from highest to lowest using " + factor + " as a factor");
  for (int i = index-1; i>=0; i--){
   System.out.println(collection[i].getName());
  }
 }

 /**
  * Returns the top ranking school as School object
  */
 public School getTop(){
  return collection[index];
 } 

 /**
  * Checks the factor the user want to rank the school by
  * and changes the order of collection according to rank
  * from lowest score to the highest score
  * Also, the rank element of each school is resetted accordingly
  * 
  * factor (String): Overall, Academics, Research, Publications
  */
 public void rankSchools(String factor){

  int [] factorArray = new int[index]; // New Array to store ranked school ratings

  if (factor == "Overall"){
   for (int i = 0; i<index; i++){
    factorArray[i] = collection[i].getOverallRating();
   }
  } if(factor == "Academics"){
   for (int i = 0; i<index; i++){
    factorArray[i] = collection[i].getRateAcademics();
   }
  } if(factor == "Research"){
   for (int i = 0; i<index; i++){
    factorArray[i] = collection[i].getRateResearch();
   }
  } if(factor == "Publications"){
   for (int i = 0; i<index; i++){
    factorArray[i] = collection[i].getRatePubs();
   }
   //didn't add default so that if none of the factors are typed, 
   //the factorArray will have 0s and will not make changes to the collection array
  }
  
  factorArray = Sort.sortArray(factorArray);

  School [] collectioncopy = collection.clone(); // Make a copy of the original collection
  for (int i=0; i<index; i++){
   collection[i].setRankValue(4-factorArray[i]); //setting rank where 1 is top
   collection[i] = collectioncopy[factorArray[i]]; //rearranging collection from lower score to higher scole
  }

 }
 
// print out the collection, displaying school name, various ratings and ranking in order stored in collection
 public String toString(){
  String s = "\nThere are " + index + " schools in the database: \n\n";
  for (int i = 0; i<index; i++){
   s += collection[i];
   s += "\n\n\n";
  }
  return s;
 }

 public static void main(String [] args){
   // if the user gives no input, ask the user to give a weight for each factor.
  if (args.length<3){
   System.out.println("Please provide 3 weights(1-5) for Academics, Research and Publications");
  } else{

   GradSchool gs = new GradSchool(4); // create a collection of 4 schools

   gs.addSchool("Wellesley",5,4,5);
   gs.addSchool("Wellesley",7,4,5); //resetting Wellesley
   gs.addSchool("Olin",6,9,3);
   gs.addSchool("Babson",3,9,2);
   gs.addSchool("MIT",2,3,15);
   
   gs.computeRatings(Integer.parseInt(args[0]), // compute the ratings based on weights
                     Integer.parseInt(args[1]),
                     Integer.parseInt(args[2]));
   System.out.println(gs);
   
   // display rank results for each factor and the overall rating.
   gs.displayRankResults("Academics");
   gs.displayRankResults("Research");
   gs.displayRankResults("Publications");
   gs.displayRankResults("Overall");

   System.out.println(gs);
  }

 }
}