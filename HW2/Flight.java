/* Flight.java 
 * CS230 Assignment 2 task 2 
 * Written by: Haozheng Du 
 */ 
import java.util.Scanner;

//reads a couple of flights and determines whether they share a stopover city
public class Flight{
  private String airline;
  private int flNum;
  private String from;
  private String to;
  
  // Flight constructor with 4 inputs
  public Flight(String airline, 
                int flNum, 
                String from, 
                String to){
    this.airline = airline;
    this.flNum = flNum;
    this.from = from;
    this.to = to;
  }
  
  // Get the airline
  public String getAirline(){
    return this.airline;
  }
  
  // Get the Destination
  public String getDestination(){
    return this.to;
  }
  
  // Get the Flight Number
  public int getFlightNumber(){
    return this.flNum;
  }
  
  // Get the Origin
  public String getOrigin(){
    return this.from;
  }
  
  // Set the airline
  public void setAirline(String airline){
    this.airline = airline;
  }
  
  // Set the Destination
  public void setDestination(String toCity){
    this.to = toCity;
  }
  
  // Set the Flight Number
  public void setFlightNum(int flNum){
    this.flNum = flNum;
  }
  
  // Set the Origin
  public void setOrigin(String fromCity){
    this.from = fromCity;
  }
  
  // Print out Flight Information
  public String toString(){
    return("Airline: " + this.airline + "\n" +
           "Flight Number: " + this.flNum + "\n" +
           "Destination : " + this.to + "\n" +
           "Origin: " + this.from + "\n");   
  }
  
  // Read the flight information from user input and create a Flight
  public static Flight readFlight(Scanner s){
    System.out.println("Enter the Airline:");
    String airline = s.next();
    System.out.println("Enter the Flight Number:");
    int flNum = s.nextInt();
    System.out.println("Enter the Destination:");
    String to = s.next();
    System.out.println("Enter the Origin:");
    String from = s.next();
    System.out.println("Flight Information Recorded!");
    Flight flight = new Flight(airline, flNum, from, to);
    return flight;
  }
  
  // Check if 2 input Flights have a stopover city
  public static boolean stopOver(Flight f1, Flight f2){
    if(f1.to.equals(f2.from) || f1.from.equals(f2.to))
      return true;
    else
      return false;
  }
  
  // Read a couple of flights and determines whether they share a stopover city
  public static void main(String[] args){
    int numberOfFlights = 3; // Set the number of input flights 
    Scanner scan = new Scanner(System.in);
    Flight[] flightArray = new Flight[numberOfFlights]; // Generate an Array of Flights
    // Assign input Flights to each elements in the Array
    for(int i = 0; i < numberOfFlights; i++){
      flightArray[i] = readFlight(scan);
    }
    
    // Check if all flights share a stopover city
    for(int i = 0; i < numberOfFlights; i++){
      for(int j = i + 1; j < numberOfFlights; j++){
        if (!(stopOver(flightArray[i], flightArray[j]))){
          System.out.println("They don't share a stopover city.");         
          return;
        }      
      }
    }
    System.out.println("They share a stopover city.");
  }
}