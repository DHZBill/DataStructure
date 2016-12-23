/*Data Structures cs230
 * Oct 28,2016
 * Xiaozheng Xu and Haozheng Du
 * Assignment 6, task3
 */

public class Term{
  private int coef;
  private int exp;
  
  public Term(int coef, int exp){
    this.coef = coef;
    this.exp = exp;
  }
  
  public String toString(){
    if (exp == 0) return Integer.toString(coef);
    return(coef+"x^"+exp);
  }
  
  public int getCoef(){return coef;}
  public int getExp(){return exp;}
  public void setCoef(int coef){this.coef = coef;}
  public void setExp(int exp){this.exp = exp;}
  
}