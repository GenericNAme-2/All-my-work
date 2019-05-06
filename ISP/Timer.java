/*  
   Name: Jason Li
   Teacher: Mr. Rosen
   Date: 2019/01/16
   This class is a timer thread which runs alongside word search.
   
   
   Global variables
	name        type            description
   -----------------------------------------------------------------
	ws          WordSearch      Implements WordSearch class
	c           Console         Console class
	s           int             Elapsed seconds
	timerFont   font            Font used for outputting the seconds
*/
import java.awt.*;
import hsa.Console;
import java.lang.*;     // to access Thread class

public class Timer extends Thread
{
    private Console c;
    WordSearch ws;
    int s = 0;
    Font timerFont = new Font ("Arial", 1, 15);
    
    
    /*
    determines whether or not the user has won
    -----------------------------------------------------
	Global Variables: found[]
	
	Local Variables 
	name        type        description
    ------------------------------------------------------
	win         boolean     whether or nother user wins
    ------------------------------------------------------
    For loop and if structure
    
    */
    
    private boolean determineWin ()  //blackbox determines if user has won
    {
	boolean win = true;
	for (int x = 0 ; x < 15 ; x++)
	{
	    if (ws.found [x] == false) 
	    {
		win = false;
	    }
	}
	return win;
    }
    
    
    /*
    animates the timer
    -----------------------------------------------------
	Global Variables: s
	
	Local Variables 
	name        type        description
    ------------------------------------------------------
	lightBlue   Color       Light blue colour for the background
	sStr        String      String version of s for printing
    ------------------------------------------------------
    While loop, for loop and if structure
    
    */
    
    
    public void timer ()
    {
	
	String sStr;
	Color lightBlue = new Color (102, 255, 255);
	while(!determineWin())
	{
	    try
	    {
		Thread.sleep (1000);
	    }
	    catch (Exception e)
	    {
	    }
	    s++;
	    for(int x = 0; x < 100; x ++){
		c.setColor (lightBlue);
		c.drawLine(350,50 + x,500,50 + x);//erase
	    }
	    c.setColor(Color.black);
	    sStr = " " + s;
	    c.setFont(timerFont);
	    c.drawString (sStr + 's', 400, 110);//writes the time
	}
	if(determineWin()){
	    for(int x = 0; x < 100; x ++){
		c.setColor (lightBlue);
		c.drawLine(350,50 + x,500,50 + x);//erase
	    }
	}

    }

    /*
    Returns s
    -----------------------------------------------------
	Global Variables: s
	
	Local Variables :none
    ------------------------------------------------------
    return
    
    */
    public int getTime(){
	return s;
    }

    /*
    Method for the Console and WordSearch for this thread to run on
    -----------------------------------------------------
	Global Variables: c, ws
	
	Local Variables :
	name    type        description
    ---------------------------------------------------------
	con     Console     Console Class
	word    WordSearch  WordSearch class
	
    ------------------------------------------------------
    return
    
    */
    
    public Timer (Console con, WordSearch word)
    {
	c = con;
	ws = word;
    }

    /*
    Runs the thread
    -----------------------------------------------------
	Global Variables: None
	
	Local Variables :none
    ------------------------------------------------------
    return
    
    */
    public void run ()
    {
	timer ();
    }
}

