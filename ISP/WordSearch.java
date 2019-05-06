/*

    Jason Li
    Mr.Rosen
    2019/01/16
    Word search: the final version which includes a timed and untimed mode, 3 different themed word searches
    and highscores stored into a file.

    Screens: 
    splashScreen() - Beginning animation
    winScreen() - when the player wins, this takes their name for highscores
    mainMenu() - menu directory
    goodbye() - exits
    search() - word search game
    search(boolean timed) - timed word search
    instructions() - user instructions


    global variables
    name        type                description
    --------------------------------------------------------------------
    c           Console             Console class implementation.
    t           Timer               Timer class implementation.
    w           WordSearch          WordSearch class implementation.
    time        boolean             Whether or not the game is in timed mode.
    setTime     int                 Time limit which the player has set in timed mode.
    theme       int                 Which word search will be picked (random number).
    choice      String              User choice in mainMenu.
    win         boolean             Whether or not the player has won in timed.
    lose        boolean             Whether or not the player has lost in timed.
    found       boolean[]           Whether or not each word has been found in the word search.
    ended       boolean             Whether or not the game has ended.





    */
import java.awt.*;
import hsa.Console;
import hsa.Message;
import java.io.*;

public class WordSearch extends Thread
{
    static Console c;
    static WordSearch w;
    static boolean time = false;
    static int theme;
    int setTime;
    static String choice;
    static boolean ended = false;
    boolean win = true, lose = false;
    static boolean[] found = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    Timer t;
    
    
/*
    WordSearch
    -------------------------------------------------------------------------------
	Global variables used: c

	Local variables:None
    --------------------------------------------------------------------------------
    No input/loop/logic.




    */
    public WordSearch ()
    {
	c = new Console (35, 110, "Word Search");
    }




    /*
    Splash screen with animations.
    -------------------------------------------------------------------------------
	Global variables used: None

	Local variables:
	name        type                description
	--------------------------------------------------------------------
	body        font                Only font to be used in this method.
	lightBlue   Color               A light blue color for background.
	x           int                 Counter in the for loop.
    --------------------------------------------------------------------------------
    For loop is used.




    */


    public void splashScreen ()
    {
	Font body = new Font ("Arial", 1, 50);
	Color lightBlue = new Color (102, 255, 255);
	for (int x = 0 ; x < 2000 ; x++)
	{
	    c.setColor (lightBlue);
	    c.drawLine (0, x, 2000, x);
	}


	for (int x = 400 ; x >= 0 ; x--)
	{
	    c.setColor (lightBlue);
	    c.fillRect (100, 99 - x, 401, 100); //erase
	    c.setColor (Color.white);
	    c.fillRect (100, 100 - x, 400, 100); //white box
	    c.setColor (Color.black);
	    c.drawRect (100, 100 - x, 400, 100); //outline
	    c.drawRect (200, 100 - x, 200, 100);
	    c.drawRect (300, 100 - x, 100, 100);
	    c.setFont (body);
	    c.drawString ("W", 130, 170 - x); //"WORD"
	    c.drawString ("O", 230, 170 - x);
	    c.drawString ("R", 330, 170 - x);
	    c.drawString ("D", 430, 170 - x);
	    try
	    {
		sleep (10);
	    }
	    catch (Exception e)
	    {
	    }
	}

	for (int x = 1000 ; x >= 0 ; x--)
	{
	    c.setColor (lightBlue);
	    c.fillRect (99 - x, 300, 400, 101); //erase
	    c.setColor (Color.white);
	    c.fillRect (100 - x, 300, 600, 100); //white box
	    c.setColor (Color.black);
	    c.drawRect (100 - x, 300, 600, 100); //outline
	    c.drawRect (200 - x, 300, 400, 100);
	    c.drawRect (300 - x, 300, 200, 100);
	    c.drawRect (400 - x, 300, 100, 100);
	    c.setFont (body);
	    c.drawString ("S", 130 - x, 370); //"SEARCH"
	    c.drawString ("E", 230 - x, 370);
	    c.drawString ("A", 330 - x, 370);
	    c.drawString ("R", 430 - x, 370);
	    c.drawString ("C", 530 - x, 370);
	    c.drawString ("H", 630 - x, 370);
	    try
	    {
		sleep (5);
	    }
	    catch (Exception e)
	    {
	    }
	}
	c.setTextBackgroundColor (lightBlue);
	pauseProgram ();
    }


    /*

    Method which runs the timer thread.
    -------------------------------------------------------------------------------
	Global variables used: t,c,w

	Local variables: none
    --------------------------------------------------------------------------------
    Noinput/loop/logic
    */


    public void timer ()
    {
	t = new Timer (c, w);
	t.start ();

    }


    /*
	Timer which runs the timer thread.
	-------------------------------------------------------------------------------
	    Global variables used: t,c,w

	    Local variables: none
       --------------------------------------------------------------------------------
       No input/loop/logic

       */

    public int getTime ()
    {

	return t.getTime ();

    }


    /*
	Game instructions.
	-------------------------------------------------------------------------------
	    Global variables used: None

	    Local variables: none
       --------------------------------------------------------------------------------
       No input/loop/logic

       */
    
    public void instructions ()
    {
	title ();
	c.println ("This is a word search with 15 words and a timed and untimed version.");
	c.println (" The top 10 high scores of the best times will be stored.");
	c.println ("Both levels will be selecting from a variety of word searches each featuring a  different word theme.");
	c.println ("In order to find a word, you must enter the coordinates of the first letter and the last (must be lowercase).");
	c.println ("Also, in timed mode, you will be able to set a time limit for yourself. Once it has been exceeded, you will nolonger be able to enter new coordinates.");
	pauseProgram ();
    }


    
    /*
	Pauses until user presses a key.
	-------------------------------------------------------------------------------
	    Global variables used: None

	    Local variables: none
	    
       --------------------------------------------------------------------------------
       Input used

       */
    public void pauseProgram ()
    {


	c.println ("press any key to continue...");
	c.getChar ();
    }

/*
	When user wins, this method asks for their name (timed) and stores it into a file.
	-------------------------------------------------------------------------------
	    Global variables used: None

	    Local variables: 
	    
	    Name        Type            Description
	    ---------------------------------------------------------------------------
	    endTime     int             Time when the player has won.
	    username    String          Name inputted by the user for their entry to be saved as.
	    pw          PrintWriter     Implementation of PrintWriter class.
	    br          BufferedReader     Implementation of BufferedReader class.
	    header      String          File header.
	    terms       int             Amount of entries currently in the highscores file.
	    termCalculation     int     Variable used to count and calculate terms.
	    position    int             What position would the user's current score be
					amidst the other terms in the file.
	    fileTerm    String[]        Each non-header term of the file.
	    times       int[]           Each time entry inside the file.
	    names       String[]        Each name entry inside the file.
	    sortedTimes int[]           The new times with the current score arranged in the 
					correct spot
	    sortedNames String[]        The new names with the current score arranged in the 
					correct spot
	    x           int             Counter variable inside the while loop.
	    y           int             Counter inside a for loop.
	    i           int             Counter inside a for loop.
       --------------------------------------------------------------------------------
       Input , while loop, if structure, for loop,try/catch, BufferedReader and PrintWriter used
    */


    public void winScreen ()
    {
	if (win && !lose)//determines whether the user actually won
	{
	    title ();
	    int endTime = getTime ();
	    String username;
	    PrintWriter pw;
	    BufferedReader br;
	    if (!time)  //determines if the user won in timed or untimed
	    {
		c.println ("You Won");
		pauseProgram ();
	    }
	    else    //timed
	    {
		String header = "%word-search%", name;
		int terms = 1, termCalculation = 0, position = 0;
		String[] fileTerm = new String [30];
		int[] times = new int [11];
		String[] names = new String [11];
		int[] sortedTimes = new int [11];
		String[] sortedNames = new String [11];
		int x = 0;

		//input username
		c.println ("Your time: " + endTime);
		c.print ("Enter your name:");
		username = c.readLine ();

		try
		{
		    br = new BufferedReader (new FileReader ("HighScores.wordsearch"));
		    while (true)
		    {
			fileTerm [x] = br.readLine ();
			if (fileTerm [x] == null)   ///figures out how many terms are in the highscores file
			    break;
			else
			{
			    termCalculation++;
			}
			x++;

		    }
		    terms = (termCalculation - 1) / 2;
		    if(termCalculation > 21) //if there's a mistake and more than 21 lines are in the file
			terms = 10;
		    if (terms == 0) //if the file is empty
		    {
			sortedNames [0] = username;
			sortedTimes [0] = endTime;

		    }
		    else
		    {
			for (int y = 0 ; y < terms ; y++)
			{
			    names [y] = fileTerm [y * 2 + 1];
			    times [y] = Integer.parseInt (fileTerm [y * 2 + 2]); //seperates all the lines into name and time
			}

			if (endTime < times [terms - 1])    //if the new score will go in between the other entries or at the end
			{
			    for (int y = 0 ; y < terms ; y++)
			    {
				if (endTime < times [y])
				{
				    position = y;
				    sortedNames [position] = username;
				    sortedTimes [position] = endTime;
				    break;
				}
			    }
			    for (int i = 0 ; i < position ; i++)
			    {
				sortedNames [i] = names [i];
				sortedTimes [i] = times [i];
			    }
			    for (int i = position ; i < terms ; i++)
			    {
				sortedNames [i + 1] = names [i];
				sortedTimes [i + 1] = times [i];
			    }
			}
			else if (terms < 10)    //if it's att the end and there's less than 10 entries already in the file
			{
			    position = terms;
			    sortedNames [position] = username;
			    sortedTimes [position] = endTime;
			    for (int i = 0 ; i < position ; i++)
			    {
				sortedNames [i] = names [i];
				sortedTimes [i] = times [i];
			    }
			    for (int i = position ; i < terms ; i++)
			    {
				sortedNames [i + 1] = names [i];
				sortedTimes [i + 1] = times [i];
			    }
			}
			else    //if it won't be fitted into the highscores
			{
			    position = 10;
			    for (int i = 0 ; i < terms ; i++)
			    {
				sortedNames [i] = names [i];
				sortedTimes [i] = times [i];
			    }
			}

		    }
		}
		catch (IOException e)
		{
		}
		try
		{
		    pw = new PrintWriter (new FileWriter ("HighScores.wordsearch"));
		    pw.println (header);
		    for (int i = 0 ; i < terms ; i++)
		    {
			pw.println (sortedNames [i]); //prints the highscores back into the file
			pw.println (sortedTimes [i]);
		    }
		    if (terms == 0)
		    {
			pw.println (sortedNames [0]);
			pw.println (sortedTimes [0]);
		    }
		    pw.close ();
		}
		catch (IOException e)
		{
		}
	    }
	}
    }


     /*
	Determines whether or not the user has won.
	-------------------------------------------------------------------------------
	    Global variables used: found[]

	    Local variables: none
	    
       --------------------------------------------------------------------------------
       For loop and if structure used

       */

    public boolean determineWin ()  
    {
	boolean win = true;
	for (int x = 0 ; x < 15 ; x++)  //scrolls through every term to make sure all elements are true
	{
	    if (found [x] == false)
	    {
		win = false;
	    }
	}
	return win;
    }



    /*
	The actual word search where the user finds words by entering first and last coordinates.
	-------------------------------------------------------------------------------
	    Global variables used: found[], win, lose

	    Local variables: 
	    
	    name            type                description
       -------------------------------------------------------------------------------------
	    x1              String              Inputted x-coordinate of first letter
	    x2              String              Inputted x-coordinate of last letter
	    y1              int                 Inputted y-coordinate of first letter
	    y2              int                 Inputted y-coordinate of last letter
	    y1Str           String              String version of y1 for error trap
	    y2Str           String              String version of y2 for error trap    
	    beginX          int[]               All of the correct first letter x's.
	    endX            int[]               All of the correct last letter x's.
	    beginY          String[]            All of the correct first letter y's.
	    endY            String[]            All of the correct last letter y's.
       --------------------------------------------------------------------------------
       For loop ,if structure, try/catch and  input used

       */
    


    public void search ()  
    {






	String x1 = "a", x2 = "a";
	String y1Str, y2Str = "a";
	int y1 = 0, y2 = 0;
	String[] beginX;
	String[] endX;
	int[] beginY;
	int[] endY;
	if (time && getTime () > setTime)   //if the user has gone over the time limit(the user will remain in the game but if they try to enter
					    //another coordinate, they will be redirected to the lose screen
	{
	    lose = true;
	    title ();
	    c.println ("You LOSE! Time has expired. Go faster or set a higher time limit next time.");
	    found = new boolean[]
	    {
		true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
	    }
	    ;
	    pauseProgram ();
	}
	else
	{
	    if (theme == 1) //theme 1: PC part manufacturers
	    {
		beginX = new String[]
		{
		    "b", "e", "g", "a", "o", "e", "g", "d", "h", "n", "m", "f", "c", "a", "l" //answers for this theme
		}
		;                                                                                                 
		beginY = new int[]
		{
		    8, 3, 2, 4, 7, 13, 1, 8, 10, 1, 3, 7, 6, 7, 8
		}
		;
		endX = new String[]
		{
		    "b", "e", "l", "d", "o", "h", "j", "h", "d", "n", "k", "a", "i", "g", "h"
		}
		;
		endY = new int[]
		{
		    5, 5, 7, 1, 1, 13, 4, 1, 10, 6, 5, 12, 6, 1, 4
		}
		;

		title ();
		c.print (' ', 4);
		c.println ("A B C D E F G H I J K L M N O");
		c.print (' ', 2);
		c.println ("1 D U M S C Z E E G F W F F L R");
		c.print (' ', 2);
		c.println ("2 Q A U S A T A V T J I O P E I");
		c.print (' ', 2);
		c.println ("3 T S H K A X J S G Y U D M N A");
		c.print (' ', 2);
		c.println ("4 A L V G M N U C R A B S F O S");
		c.print (' ', 2);
		c.println ("5 T R A O D O B R A O I A J V R");
		c.print (' ', 2);
		c.println ("6 U E S A M S U N G T C M G O O");
		c.print (' ', 2);
		c.println ("7 S C G K M N H Q B N O K K I C");
		c.print (' ', 2);
		c.println ("8 R A Z Z V P R O T Z V Z O Q G");
		c.print (' ', 2);
		c.println ("9 U X Y I W L G B J V X A U Q E");
		c.print (' ', 1);
		c.println ("10 J R D L E T N I K D Z M H W D");
		c.print (' ', 1);
		c.println ("11 L I K J X I N H X J L J C V M");
		c.print (' ', 1);
		c.println ("12 A O X I I F S X U L K Y N X H");
		c.print (' ', 1);
		c.println ("13 O Z U G D E L L F T W E I A D");
		c.print (' ', 1);
		c.println ("14 L T J I Z S W C K P C Z G T X");
		c.print (' ', 1);
		c.println ("15 B T M D K T M N J R I B N D L");
		c.println ();
		c.print (' ', 37);
		c.println ("Theme: PC Part Manufacturers");
		c.print ("ACER", 15);
		c.print ("AMD", 15);
		c.print ("ASROCK", 15);
		c.print ("ASUS", 15);
		c.println ("CORSAIR");
		c.print ("DELL", 15);
		c.print ("EVGA", 15);
		c.print ("GIGABYTE", 15);
		c.print ("INTEL", 15);
		c.println ("LENOVO", 15);
		c.print ("MSI", 15);
		c.print ("NVIDIA", 15);
		c.print ("SAMSUNG", 15);
		c.print ("SEAGATE", 15);
		c.println ("ZOTAC", 15);
		c.println ();



		if (found [0])                      //sees which ones have already been found
		{
		    c.drawLine (0, 410, 50, 410);       
		    c.drawLine (52, 210, 52, 150);
		}
		if (found [1])
		{
		    c.drawLine (120, 410, 190, 410);
		    c.drawLine (99, 110, 99, 150);
		}
		if (found [2])
		{
		    c.drawLine (240, 410, 290, 410);
		    c.drawLine (131, 90, 211, 190);
		}
		if (found [3])
		{
		    c.drawLine (350, 410, 420, 410);
		    c.drawLine (35, 130, 83, 70);
		}
		if (found [4])
		{
		    c.drawLine (470, 410, 550, 410);
		    c.drawLine (260, 190, 260, 70);
		}
		if (found [5])
		{
		    c.drawLine (0, 430, 50, 430);
		    c.drawLine (99, 310, 147, 310);
		}


		if (found [6])
		{
		    c.drawLine (120, 430, 190, 430);
		    c.drawLine (131, 70, 179, 130);
		}


		if (found [7])
		{
		    c.drawLine (240, 430, 290, 430);
		    c.drawLine (260, 210, 147, 70);
		}


		if (found [8])
		{
		    c.drawLine (350, 430, 420, 430);
		    c.drawLine (147, 250, 83, 250);
		}


		if (found [9])
		{
		    c.drawLine (470, 430, 550, 430);
		    c.drawLine (243, 70, 243, 170);
		}


		if (found [10])
		{
		    c.drawLine (0, 450, 50, 450);
		    c.drawLine (227, 110, 195, 150);
		}


		if (found [11])
		{
		    c.drawLine (120, 450, 190, 450);
		    c.drawLine (115, 190, 35, 290);
		}


		if (found [12])
		{
		    c.drawLine (240, 450, 290, 450);
		    c.drawLine (67, 170, 163, 170);
		}


		if (found [13])
		{
		    c.drawLine (350, 450, 420, 450);
		    c.drawLine (35, 190, 131, 70);
		}


		if (found [14])
		{
		    c.drawLine (470, 450, 550, 450);
		    c.drawLine (211, 210, 147, 130);
		}

	    }
	    else if (theme == 2)    //2nd theme: animals
	    {



		beginX = new String[]
		{
		    "a", "f", "d", "h", "i", "i", "m", "c", "n", "l", "f", "g", "f", "a", "c"
		}
		;                                                                                                 // start and end coordinates for the words
		beginY = new int[]
		{
		    14, 1, 1, 7, 6, 9, 1, 1, 3, 1, 6, 3, 15, 10, 10
		}
		;
		endX = new String[]
		{
		    "d", "i", "b", "c", "o", "g", "m", "i", "n", "g", "a", "e", "m", "a", "g"
		}
		;
		endY = new int[]
		{
		    14, 4, 3, 12, 6, 9, 4, 7, 9, 1, 1, 1, 8, 5, 6
		}
		;

		title ();
		c.print (' ', 4);
		c.println ("A B C D E F G H I J K L M N O");
		c.print (' ', 2);
		c.println ("1 Y C G C G B L A M M A M F G D ");
		c.print (' ', 2);
		c.println ("2 D E A I D I I F B B H B I A Y ");
		c.print (' ', 2);
		c.println ("3 P T K N R N P R U G G R S I V  ");
		c.print (' ', 2);
		c.println ("4 W I S N Y A E T D T I W H N K  ");
		c.print (' ', 2);
		c.println ("5 Y U L V O V F B I J Z L W S A  ");
		c.print (' ', 2);
		c.println ("6 E J G Q K M E F C H I C K E N  ");
		c.print (' ', 2);
		c.println ("7 K J G W F L S C E A N W K C H  ");
		c.print (' ', 2);
		c.println ("8 R U X V A W H Z Z Y N D S T C  ");
		c.print (' ', 2);
		c.println ("9 U Z L H O E G O D V H E M S Z  ");
		c.print (' ', 1);
		c.println ("10 T A W G E D P T U N L Q K X N  ");
		c.print (' ', 1);
		c.println ("11 W Z J T N Y Z A X I B B X H Y  ");
		c.print (' ', 1);
		c.println ("12 F Y A T E I U E T J O U F E L  ");
		c.print (' ', 1);
		c.println ("13 L H Z A X T J P A V L H T L K  ");
		c.print (' ', 1);
		c.println ("14 B E A R Z V E X D V L U N P P  ");
		c.print (' ', 1);
		c.println ("15 D O X W E R B U H Z S V K X F  ");
		c.println ();
		c.print (' ', 37);
		c.println ("Theme: Animals");
		c.print ("BEAR", 15);
		c.print ("BIRD", 15);
		c.print ("CAT", 15);
		c.print ("CHEETA", 15);
		c.println ("CHICKEN");
		c.print ("DOG", 15);
		c.print ("FISH", 15);
		c.print ("GIRAFFE", 15);
		c.print ("INSECTS", 15);
		c.println ("MAMMAL", 15);
		c.print ("MONKEY", 15);
		c.print ("PIG", 15);
		c.print ("REPTILES", 15);
		c.print ("TURKEY", 15);
		c.println ("WHALE", 15);
		c.println ();


		 //checks which words have been found
		if (found [0])          
		{
		    c.drawLine (0, 410, 50, 410);
		    c.drawLine (35, 330, 83, 330);
		}
		if (found [1])
		{
		    c.drawLine (120, 410, 190, 410);
		    c.drawLine (115, 70, 163, 130);
		}
		if (found [2])
		{
		    c.drawLine (240, 410, 290, 410);
		    c.drawLine (83, 70, 51, 110);
		}
		if (found [3])
		{
		    c.drawLine (350, 410, 420, 410);
		    c.drawLine (147, 190, 67, 290);
		}
		if (found [4])
		{
		    c.drawLine (470, 410, 550, 410);
		    c.drawLine (163, 170, 260, 170);
		}
		if (found [5])
		{
		    c.drawLine (0, 430, 50, 430);
		    c.drawLine (163, 230, 131, 230);
		}


		if (found [6])
		{
		    c.drawLine (120, 430, 190, 430);
		    c.drawLine (227, 70, 227, 130);
		}


		if (found [7])
		{
		    c.drawLine (240, 430, 290, 430);
		    c.drawLine (67, 70, 163, 190);
		}


		if (found [8])
		{
		    c.drawLine (350, 430, 420, 430);
		    c.drawLine (243, 110, 243, 230);
		}


		if (found [9])
		{
		    c.drawLine (470, 430, 550, 430);
		    c.drawLine (211, 70, 131, 70);
		}


		if (found [10])
		{
		    c.drawLine (0, 450, 50, 450);
		    c.drawLine (115, 170, 35, 70);
		}


		if (found [11])
		{
		    c.drawLine (120, 450, 190, 450);
		    c.drawLine (131, 110, 99, 70);
		}


		if (found [12])
		{
		    c.drawLine (240, 450, 290, 450);
		    c.drawLine (115, 350, 227, 210);
		}


		if (found [13])
		{
		    c.drawLine (350, 450, 420, 450);
		    c.drawLine (35, 250, 35, 150);
		}


		if (found [14])
		{
		    c.drawLine (470, 450, 550, 450);
		    c.drawLine (67, 250, 131, 170);
		}



	    }
	    else    //3rd theme: ICS vocabulary
	    {

		beginX = new String[]
		{
		    "a", "m", "o", "i", "i", "b", "n", "l", "i", "d", "n", "f", "h", "a", "f"
		}
		;                                                                                                 // start and end coordinates for the words
		beginY = new int[]
		{
		    13, 1, 5, 5, 6, 8, 3, 3, 7, 4, 10, 1, 15, 15, 3
		}
		;
		endX = new String[]
		{
		    "e", "m", "o", "m", "d", "f", "n", "i", "f", "a", "n", "m", "c", "i", "a"
		}
		;
		endY = new int[]
		{
		    9, 7, 8, 9, 6, 8, 1, 3, 4, 7, 5, 8, 10, 15, 8
		}
		;

		title ();
		c.print (' ', 4);
		c.println ("A B C D E F G H I J K L M N O");
		c.print (' ', 2);
		c.println ("1 A D E B A O X A N V Y L B T S");
		c.print (' ', 2);
		c.println ("2 A J Q E T C P X Z Q V R O N A ");
		c.print (' ', 2);
		c.println ("3 V U N P D S B E A V A J O I P ");
		c.print (' ', 2);
		c.println ("4 H Q W L T G G A R L O A L J R ");
		c.print (' ', 2);
		c.println ("5 I R O R C U N K C A O C E D C ");
		c.print (' ', 2);
		c.println ("6 Y O I E L B U O D L T S A O H ");
		c.print (' ', 2);
		c.println ("7 P N K J Z L C I L H A O N H A ");
		c.print (' ', 2);
		c.println ("8 G I N P U T W T P D W S R T R ");
		c.print (' ', 2);
		c.println ("9 H M E D Y Y J B Y F W V S E B ");
		c.print (' ', 1);
		c.println ("10 Y N T A S D T L B W R R U M X ");
		c.print (' ', 1);
		c.println ("11 B E R U P U R R D C E K P W P ");
		c.print (' ', 1);
		c.println ("12 E R K J P K R G D P C J X Z X ");
		c.print (' ', 1);
		c.println ("13 A C J Z B T O R K D S L Q A Q ");
		c.print (' ', 1);
		c.println ("14 B Z U A X K U R A I J L B K X ");
		c.print (' ', 1);
		c.println ("15 R E C U R S I O N Z I E B D D ");
		c.println ();
		c.print (' ', 37);
		c.println ("Theme: ICS");
		c.print ("ARRAY", 15);
		c.print ("BOOLEAN", 15);
		c.print ("CHAR", 15);
		c.print ("CLASS", 15);
		c.println ("DOUBLE");
		c.print ("INPUT", 15);
		c.print ("INT", 15);
		c.print ("JAVA", 15);
		c.print ("LONG", 15);
		c.println ("LOOP");
		c.print ("METHOD", 15);
		c.print ("OPERATOR", 15);
		c.print ("OUTPUT", 15);
		c.print ("RECURSION", 15);
		c.println ("STRING");
		c.println ();



		if (found [0])  //checks which words have been found
		{
		    c.drawLine (0, 410, 50, 410);
		    c.drawLine (35, 310, 103, 230);
		}
		if (found [1])
		{
		    c.drawLine (120, 410, 190, 410);
		    c.drawLine (227, 70, 227, 190);
		}
		if (found [2])
		{
		    c.drawLine (240, 410, 290, 410);
		    c.drawLine (260, 150, 260, 210);
		}
		if (found [3])
		{
		    c.drawLine (350, 410, 420, 410);
		    c.drawLine (163, 150, 227, 230);
		}
		if (found [4])
		{
		    c.drawLine (470, 410, 550, 410);
		    c.drawLine (163, 170, 83, 170);
		}
		if (found [5])
		{
		    c.drawLine (0, 430, 50, 430);
		    c.drawLine (52, 210, 115, 210);
		}


		if (found [6])
		{
		    c.drawLine (120, 430, 190, 430);
		    c.drawLine (243, 110, 243, 70);
		}


		if (found [7])
		{
		    c.drawLine (240, 430, 290, 430);
		    c.drawLine (211, 110, 163, 110);
		}


		if (found [8])
		{
		    c.drawLine (350, 430, 420, 430);
		    c.drawLine (163, 190, 115, 130);
		}


		if (found [9])
		{
		    c.drawLine (470, 430, 550, 430);
		    c.drawLine (83, 130, 35, 190);
		}


		if (found [10])
		{
		    c.drawLine (0, 450, 50, 450);
		    c.drawLine (243, 250, 243, 150);
		}


		if (found [11])
		{
		    c.drawLine (120, 450, 190, 450);
		    c.drawLine (115, 70, 227, 210);
		}


		if (found [12])
		{
		    c.drawLine (240, 450, 290, 450);
		    c.drawLine (147, 350, 67, 250);
		}


		if (found [13])
		{
		    c.drawLine (350, 450, 420, 450);
		    c.drawLine (35, 350, 163, 350);
		}


		if (found [14])
		{
		    c.drawLine (470, 450, 550, 450);
		    c.drawLine (115, 110, 35, 210);
		}

	    }



	    c.println ("Enter coordinate of first letter. Enter E to either x coordinate of first or last letter to exit.");
	    c.println (" *Case sensitive*");
	    c.print ("X: ");
	    x1 = c.readLine ();
	    if (x1.equals ("E"))//if the user wants to exit
	    {
		found = new boolean[]
		{
		    true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
		}
		;
	    }
	

	    else if (!x1.equals ("a") && !x1.equals ("b") && !x1.equals ("c") && !x1.equals ("d") && !x1.equals ("e") && !x1.equals ("f") && !x1.equals ("g") && !x1.equals ("h") && !x1.equals ("i") && !x1.equals ("j") && !x1.equals ("k") && !x1.equals ("l") && !x1.equals ("m") && !x1.equals ("n") && !x1.equals ("o"))
	    {
		new Message ("Error! Not within range of values (a - o)"); //errortrap
		search ();
	    }


	    else
	    {
		c.print ("Y: ");
		y1Str = c.readString ();

		try
		{
		    y1 = Integer.parseInt (y1Str);
		}
		catch (Exception e)
		{
		    new Message ("Error! Not within range of values (1 - 15)");//errortrap
		    search ();
		}

		if (y1 < 1 || y1 > 15)
		{
		    new Message ("Error! Not within range of values (1 - 15)");//errortrap
		    search ();
		}
		c.println ("Enter coordinate of last letter.");
		c.print ("X: ");
		x2 = c.readLine ();
		if (x2.equals ("E"))
		    goodbye ();
		if (!x2.equals ("a") && !x2.equals ("b") && !x2.equals ("c") && !x2.equals ("d") && !x2.equals ("e") && !x2.equals ("f") && !x2.equals ("g") && !x2.equals ("h") && !x2.equals ("i") && !x2.equals ("j") && !x2.equals ("k") && !x2.equals ("l") && !x2.equals ("m") && !x2.equals ("n") && !x2.equals ("o"))
		{
		    new Message ("Error! Not within range of values (a - o)");//errortrap
		    search ();
		}


		c.print ("Y: ");
		y2Str = c.readLine ();
		try
		{
		    y2 = Integer.parseInt (y2Str);
		}
		catch (Exception e)
		{
		    new Message ("Error! Not within range of values (1 - 15)");//errortrap
		    search ();
		}

		if (y2 < 0 || y2 > 15)
		{
		    new Message ("Error! Not within range of values (1 - 15)");//errortrap
		    search ();
		}


		for (int x = 0 ; x < 15 ; x++)//goes through the 15 words to see if the user entered the correct coordinates to any of them
		{
		    if ((beginX [x].equals (x1)) && (beginY [x] == y1) && (endX [x].equals (x2)) && (endY [x] == y2))
		    {
			found [x] = true;
		    }
		}


		if ((determineWin ()) == true) //win
		{
		    winScreen ();
		}


		else
		    search (); 
	    }
	}
    }


    
    /*
	Timed version of search.
	-------------------------------------------------------------------------------
	    Global variables used: setTime

	    Local variables: 
	    name        type        description
       --------------------------------------------------------------------------------
	    setTimeStr  String      String version of setTime for errortrapping.
	    a           boolean     Throwaway for calling search(boolean timed)
       --------------------------------------------------------------------------------
       Input, try/catch used

       */
    public void search (boolean timed)
    {

	String setTimeStr;

	title ();

	c.println ("Set a time limit (s):");
	setTimeStr = c.readLine ();
	try//errortrap for non integers
	{
	    setTime = Integer.parseInt (setTimeStr);
	}
	catch (Exception e)
	{
	    new Message ("Enter a positive integer");
	    boolean a = false;
	    search (a);
	}
	if(setTime < 0){ // errortrap for negatives
	    new Message ("Enter a positive integer");
	    boolean a = false;
	    search (a);
	}
	timer (); //calls the timer to start timing


	search ();
    }


    
    /*
	Displays highscores from the HighScores.wordsearch file.
	-------------------------------------------------------------------------------
	    Global variables used: None

	    Local variables: 
	    name        type                description
       --------------------------------------------------------------------------------
	    br          BufferedReader      Implements BufferedReader class.
	    x           int                 Counter
	    fileTerms   String[]            Non-header terms of the file.
	    pw          PrintWriter         Implements PrintWriter class.
	    header      String              File header.
       --------------------------------------------------------------------------------
       Input, try/catch, if structure used

       */
    
    public void highscores ()
    {
	title ();
	BufferedReader br;
	String fileTerms[] = new String [20];
	int x = 0;
	c.print(' ',35);
	c.println("HIGHSCORES");
	c.println();
	try
	{


	    br = new BufferedReader (new FileReader ("HighScores.wordsearch"));
	    br.readLine ();
	    while (true)
	    {
		fileTerms [x] = br.readLine ();
		if (fileTerms [x].equals (null)) //counts the amount of terms
		    break;
		else
		    x++;

	    }

	}
	

	catch (Exception e)
	{
	}


	if (x == 1)
	{
	    c.print("Currently empty"); //empty file
	}


	else //displays the contents
	{
	    c.print (' ', 10);
	    c.print ("Name", 50);
	    c.println ("Score (s)");
	    for (int i = 0 ; i < x ; i += 2)
	    {
		c.print(' ',10);
		c.print (fileTerms [i]);
		c.print (' ', 50);
		c.println (fileTerms [i + 1]);
	    }
	}

	c.println ();
	c.println ();
	c.println ("Enter 'c' to clear, any other entry to go back to menu");
	String highscoreChoice = c.readLine (); //user's choice of clearing the file 
	PrintWriter pw;
	String header = "%word-search%";
	c.print (highscoreChoice);
	if (highscoreChoice.equals ("c"))//clears the file
	{
	    try
	    {
		pw = new PrintWriter (new FileWriter ("HighScores.wordsearch")); 
		pw.println (header);
		pw.close ();
	    }
	    catch (IOException e)
	    {
	    }
	}
	else
	{
	}


    }

    
    
    
    /*
	Concludes the program and exits.
	-------------------------------------------------------------------------------
	    Global variables used: found[], ended

	    Local variables: none
       --------------------------------------------------------------------------------
       No input/loop/logic

       */

    public void goodbye ()
    {
	found = new boolean[] //found is set to true to stop the timer
	{
	    true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
	}
	;
	title ();
	c.println ("This was made by Jason Li. Thank you for playing.");
	ended = true;
	pauseProgram ();
	c.close ();
    }


    /*
	Draws the title and clears screen.
	-------------------------------------------------------------------------------
	    Global variables used: None

	    Local variables: none
       --------------------------------------------------------------------------------
       No input/loop/logic

       */
    public void title ()
    {
	c.clear ();
	c.print (' ', 35);
	c.println ("Word Search");
	c.println ();
    }


    /*
	Menu 
	-------------------------------------------------------------------------------
	    Global variables used: choice

	    Local variables: none
       --------------------------------------------------------------------------------
      Input used

       */
    public void mainMenu ()
    {
	title ();
	c.println ("Choose");
	c.println ("1) instructions");
	c.println ("2) timed game");
	c.println ("3) untimed game");
	c.println ("4) view highscore");
	c.println ("Anything else to quit");
	choice = c.readLine (); //gets the choice
    }

/*
	Main method
	-------------------------------------------------------------------------------
	    Global variables used: choice, w, time, theme, found[], ended

	    Local variables: none
	    
       --------------------------------------------------------------------------------
      Input used

       */
    public static void main (String[] args)
    {
	w = new WordSearch ();
	w.splashScreen ();

	found = new boolean[] //found is set to all true to stop the timer
	{
	    true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
	}
	;

	while (true)//loops until user decides to exit
	{
	    w.mainMenu ();
	    if (choice.equals ("1")) //instructions path
	    {
		w.instructions ();
	    }
	    else if (choice.equals ("2"))//timed
	    {
		theme = (int) (Math.random () * 3 + 1);
		time = true;
		found = new boolean[] //resets to false so the game may work
		{
		    false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
		}
		;
		w.search (time);
	    }
	    else if (choice.equals ("3"))//untimed
	    {
		theme = (int) (Math.random () * 3 + 1);
		time = false;
		found = new boolean[]//resets to false so the game may work
		{
		    false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
		}
		;
		w.search ();
	    }
	    else if (choice.equals ("4"))//view highscores
	    {
		w.highscores ();
	    }
	    else //exit game
	    {
		w.goodbye ();
	    }
	    if (ended)
	    {
		break;
	    }
	}
    }
}

