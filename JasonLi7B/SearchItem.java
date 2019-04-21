import java.util.Arrays; 
/**
 * Jason Li
 * Ms. Krasteva
 * 2019/04/15
 * Solution to Problem 3. this uses recursion to see if a given comparable is in an array
 */
public class SearchItem {                           
  // recursive method to see if a given comparable is in an array
  public static boolean searchItem(Comparable [] a, Comparable n){
    if(a.length == 1 && !a[0].equals(n))
      return false;
    if(!a[a.length-1].equals(n))
      return searchItem(Arrays.copyOf(a, a.length-1), n);
    return true;
  }
  //main method to test the searchItem() method
  public static void main(String[] args) { 
    String[] s = {"b", "a", "b"};
    System.out.println(searchItem(s, "a"));
  }
  
}
