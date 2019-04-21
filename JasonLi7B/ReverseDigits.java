import java.util.Scanner;
/**
 * Jason Li
 * Ms. Krasteva
 * 2019/04/15
 * Solution of problem 2. This is a program which will reverse the digits in a given number.
 */
public class ReverseDigits {
  //method to reverse the digits in an integer
  private static int revDigits(int n)
  {
   if(n < 10)                                                     
     return n;
   return (n%10) * (int)(Math.pow(10 , (int)Math.log10(n))) + revDigits(n/10);
  }
  //main method that runs tests revDigits()
  public static void main (String[] args){
    Scanner in = new Scanner (System.in);
    int n = in.nextInt();
    System.out.println(revDigits(n));
    
  }
  
}
