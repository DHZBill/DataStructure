/*Data Structures cs230
 * Oct 28,2016
 * Xiaozheng Xu and Haozheng Du
 * Assignment 6, task3
 */

import javafoundations.*;

public class Polynomial{
  private LinkedQueue<Term> q;
  
  public Polynomial(){
    q = new LinkedQueue<Term>();
  }
  
  public String toString(){
    String s = "";
    for (int i = 0; i<q.size(); i++){
      Term t = q.dequeue();
      s+=t.toString()+ " +";
      q.enqueue(t);
    }
    return s.substring(0,s.length()-1);
  }
  
  public LinkedQueue<Term> getQueue(){
    return q;
  }
  public void addTerm(Term t){
    int size = q.size();
    boolean tInq = false;
    Term t1;
    if (size == 0){q.enqueue(t);}
    else{
        for (int i = 0; i<size; i++){
          t1 = q.dequeue();
          if (t.getExp() > t1.getExp()){
            if (!tInq){
              q.enqueue(t);
              tInq = true;
            }
            q.enqueue(t1);
          } else if (t.getExp() == t1.getExp()){
            int coef = t.getCoef() +t1.getCoef();
            if (coef!=0){
              q.enqueue(new Term(coef,t.getExp()));
              tInq = true;
            }
          } else if (t.getExp() < t1.getExp()){
            q.enqueue(t1);
          }
        }
        if (!tInq)
          q.enqueue(t);
    }
  }
  
  public void addPolynomial(Polynomial p){
    LinkedQueue<Term> q1 = p.getQueue();
    LinkedQueue<Term> q3 = new LinkedQueue<Term>();
    while ((!q.isEmpty()) && (!q1.isEmpty())){
      if (q.first().getExp() > q1.first().getExp()){
        q3.enqueue(q.dequeue());
      }else if(q.first().getExp() < q1.first().getExp()){
        q3.enqueue(q1.dequeue());
      }else if(q.first().getExp() == q1.first().getExp()){
        int coef = q.dequeue().getCoef()+q1.first().getCoef();
        if (coef!=0) q3.enqueue(new Term(coef, q1.dequeue().getExp()));
      }
    }
    while (!q.isEmpty()){q3.enqueue(q.dequeue());}
    while (!q1.isEmpty()){q3.enqueue(q1.dequeue());}
    q = q3;
  }
  
  public static void main(String[] args){
    Polynomial p1 = new Polynomial();
    p1.addTerm(new Term(2,3));
    System.out.println(p1);
    p1.addTerm(new Term(1,2));
    p1.addTerm(new Term(1,0));
    p1.addTerm(new Term(5,4));
    p1.addTerm(new Term(2,1));
    System.out.println(p1);
    Polynomial p2 = new Polynomial();
    p2.addTerm(new Term(3,5));
    p2.addTerm(new Term(2,2));
    System.out.println(p2);
    p2.addPolynomial(p1);
    System.out.println(p2);
  }
}