/*
 * Jason Li
 * Ms. Krasteva
 * 2019/04/24
 * Runs the deck of cards and tests shuffle.
 */
import java.util.Scanner;

public class DeckCardsClient
{
   

public static void main( String[] args )
  
 {
    Scanner input = new Scanner( System.in );   
 
  
 DeckofCards deck1 = new DeckofCards( );   

 deck1.shuffle();
// deals a shuffled deck
for(int x = 0; x < 52; x++)
  System.out.println(deck1.deal());
   

 } // close main method

 
} // close class 










