/**
 * CS230 Assignment 8
 * By Haozheng Du
 * Class MyGenealogy contains my biological heritage.
 */
public class MyGenealogy{
  public static void main(String[] args){
    GenealogyTree<String> myGenealogy = new GenealogyTree<String>("Bill");
    myGenealogy.setPater("Bill", "W");
    myGenealogy.setMater("Bill", "Y");
    myGenealogy.setPater("W", "S");
    myGenealogy.setMater("W", "H");
    myGenealogy.setPater("Y","C");
    myGenealogy.setMater("Y","F");
    myGenealogy.setPater("S", "A");
    myGenealogy.setMater("S", "B");
    myGenealogy.setPater("H", "D");
    myGenealogy.setMater("H", "E");
    myGenealogy.setPater("C", "G");
    myGenealogy.setMater("C", "I");
    myGenealogy.setPater("F", "J");
    myGenealogy.setMater("F", "K");
    System.out.println("My genealogy contains " + myGenealogy.size() + " members:");
    System.out.println(myGenealogy);
    System.out.println("Bill was found: " + myGenealogy.appears("Bill"));
    System.out.println("You appear at the root: " + myGenealogy.getCoTU());
    System.out.println("H was found: " + myGenealogy.appears("H"));
    System.out.println("Your paternal grandfather is: " + myGenealogy.getPater(myGenealogy.getPater("Bill")));
    System.out.println("Your maternal grandmother is: " + myGenealogy.getMater(myGenealogy.getMater("Bill")));
    System.out.println("Your paternal great-grandfather is: " + myGenealogy.getPater(myGenealogy.getPater(myGenealogy.getPater("Bill"))));
    System.out.println("Your Maternal great-grandmother is: " + myGenealogy.getMater(myGenealogy.getMater(myGenealogy.getMater("Bill"))));
    System.out.println("S and C are in-laws: " + myGenealogy.inLaws("S","C"));
    System.out.println("A and H are in-laws: " + myGenealogy.inLaws("A","H"));
  }
}