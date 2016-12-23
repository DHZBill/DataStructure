/**
 * School.java
 * CS230 Assignment 3
 * @author HaoZheng Du, Jee Hyun Kim
 * Last Modified Date: 24 September 2016
 * <p>
 * School is a class which contains name, academics rating, 
 * research rating, publication rating of School.
 * It computes overallRating given the weights
 * and has setter and getter methods for 
 * name, academics, research, pubs, overall rating and rank.
 */

public class School{
	private String name;
	private int academics;
	private int research;
	private int pubs;

	final int MIN = 0;
	final int MAX = 10;

	private int overallRating;
	private int rank;

	//constructor
	// checks if the rating values are within the min and max 
	// and sets it as min/max accordingly if out of range
	public School(String name, int academics, int research, int pubs){
		this.name = name;
		this.academics = academics < MIN? MIN: academics > MAX? MAX: academics;
		this.research = research < MIN? MIN: research > MAX? MAX: research;
		this.pubs = pubs < MIN? MIN: pubs > MAX? MAX: pubs;
	}

	//getter methods
	public String getName(){
		return name;
	}

	public int getRankValue(){
		return rank;
	}

	public int getRateAcademics(){
		return academics;
	}

	public int getRatePubs(){
		return pubs;
	}

	public int getRateResearch(){
		return research;
	}

	public int getOverallRating(){
		return overallRating;
	}

	//setter methods
	public void setRankValue(int newValue){
		rank = newValue;
	}

	public void setRateAcademics(int ac){
		academics = ac;
	}

	public void setRatePubs(int pubs){
		this.pubs = pubs;
	}

	public void setRateResearch(int re){
		research = re;
	}

	public void setName(String newName){
		name = newName;
	}

	// set overall rating based on the weight input for each rating
	public void setOverallRating(int weightAcademics, int weightResearch, int weightPubs){
		overallRating = weightAcademics*academics
						+ weightResearch*research
						+ weightPubs*pubs;
	}


	//calls setOverallRating
	public void computeRating(int weightAcademics, int weightResearch, int weightPubs){
		setOverallRating(weightAcademics, weightResearch, weightPubs);
	}

	public String toString(){
		return("Name: "+ name
				+ "\nAcademics: " + academics
				+ "\nResearch: " + research
				+ "\nPublications: " + pubs
				+ "\nOverall Rating: " + overallRating
				+ "\nCurrent Rank: " + rank);

	}

	public static void main(String [] args){
		School s1 = new School("Wellesley",-3,7,11);
		System.out.println(s1);

		System.out.println("setting overall rating");
		s1.setOverallRating(1,2,3);
		System.out.println(s1);
	}
	
}