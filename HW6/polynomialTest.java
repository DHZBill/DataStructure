/*Data Structures cs230
 * Oct 28,2016
 * Xiaozheng Xu and Haozheng Du
 * Assignment 6, task3
 */
import java.util.*;
public class polynomialTest{
  public static Polynomial constructPolynomial(String input){
    Polynomial p = new Polynomial();
    String[] terms = input.split(" \\+ ");
    for(int i = 0; i < terms.length; i++){
      if(!terms[i].contains("x^")){
        p.addTerm(new Term(Integer.parseInt(terms[i]),0));
      }
      else if (terms[i].contains("x^")){
        String[] data = terms[i].split("x\\^");
        p.addTerm(new Term(Integer.parseInt(data[0]),Integer.parseInt(data[1])));
      }
    } 
    return p;
  }
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter Polynomial P1");
    String s1 = scan.nextLine();
    Polynomial p1 = constructPolynomial(s1);
    System.out.println("Enter Polynomial P2");
    String s2 = scan.nextLine();
    Polynomial p2 = constructPolynomial(s2);
    System.out.println("The result of adding them is: ");
    p1.addPolynomial(p2);
    System.out.print(p1);
  }
  
}