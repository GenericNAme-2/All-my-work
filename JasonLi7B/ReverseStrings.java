import java.util.Scanner;
/**
 * Jason Li
 * Ms. Krasteva
 * 2019/04/15
 * Solution to Problem 1. Uses recursion to reverse characters in a string.
 */
public class ReverseStrings {
  //Method to recursively reverse a string
  private static String revStrings(String s)
  {
        if (s.equals(""))
          return "";
        return revStrings(s.substring(s.indexOf('\n') + 1)) + s.substring(0, s.indexOf('\n') + 1);
}
  //main method to test revStrings
  public static void main (String[] args){
    Scanner sc = new Scanner(System.in);
        String s = "", input;
        do {
            input = sc.nextLine();
            s += input + "\n";
        } while (!input.equals("."));
        System.out.print(revStrings(s));
  }
  
}
