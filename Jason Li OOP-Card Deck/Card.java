/*
 * Jason Li
 * Ms. Krasteva
 * 2019/04/24
 * Class representation of a card.
 */
public class Card
{
   
String myFace;       
    
String mySuit;   

    
public Card( String theFace, String theSuit )   
   
 {  
     
    myFace = theFace;
     
    mySuit = theSuit;
  
  }
   
 
    public String toString() 
  
    {
        
     return myFace + " of " + mySuit;
    }
} // close class 










