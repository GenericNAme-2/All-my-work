import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Font;



/**
 * @author Andrey Starenky
 * March 28, 2019
 * Version 1.0
 * <p>
 * this program demonstrates the Spring Layout, by using different constraints for buttons and text
 * </p>
 * 
 * <pre>
 * Variable Name            Variable Type        Description
 * text                     JLabel     Text for changing constraints of
 * s                        SpringLayout   Instance of the SpringLayout assigned to the JPanel
 * instruction              JLabel     Tells user to input short word
 * in                   TextField    Gets input to use for text
 * a      JButton     Constrains text to top
 * b      JButton     Constrains text to bottom
 * c      JButton     Constrains text to right
 * d      JButton     Constrains text to left
 * enter                   JButton     Gets input and continues
 * x                       int                  Increase/Decrease for x
 * y                        int                  Increase/Decrease for y
 * </pre>
 */
public class SpringLayoutAndrey extends JPanel implements ActionListener
{
JLabel text;
SpringLayout s;
JLabel instruction;
TextField in;
JButton a;
JButton b;
JButton c;
JButton d;
JButton enter;
int x=200,y=130;

 public SpringLayoutAndrey() {
  super();
    
  s=new SpringLayout(); //Sets layout
  setLayout(s);
  
  
  //Input
  instruction = new JLabel("Enter a word"); //Instructions
  in = new TextField(6);
  enter = new JButton("Enter"); //Button to enter and continue
  add(in);
  add(enter);
  add(instruction);
  
  s.putConstraint (SpringLayout.NORTH, instruction, 0, SpringLayout.NORTH, this);
  s.putConstraint (SpringLayout.NORTH, in, 50, SpringLayout.NORTH, this);
  s.putConstraint (SpringLayout.NORTH, enter, 100, SpringLayout.NORTH, this);
       
  //Buttons revealed after input
        a = new JButton ("Move text up");
        b = new JButton ("Move text down");
        c = new JButton ("Move text right");
        d = new JButton ("Move text left");
        a.addActionListener (this);
        b.addActionListener (this);
        c.addActionListener (this);
        d.addActionListener (this);
  
        //setBackground(Color.black); //For debuggin only
        
  enter.addActionListener(this);
        setVisible(true);
    }
 
 public void refresh(){ //Refreshes screen
     repaint();
     revalidate();
   }
 
 //Button Pressed actions
    public void actionPerformed (ActionEvent e)
    { //Changes constraints based on which button pressed
        if (e.getActionCommand ().equals ("Move text up"))
        {
         y-=10;
            s.putConstraint (SpringLayout.WEST, text, x, SpringLayout.WEST, this);
            s.putConstraint (SpringLayout.NORTH, text, y, SpringLayout.NORTH, this);
            
        }
        else if (e.getActionCommand ().equals ("Move text down"))
        {
         y+=10;
            s.putConstraint (SpringLayout.WEST, text, x, SpringLayout.WEST, this);
            s.putConstraint (SpringLayout.NORTH, text, y, SpringLayout.NORTH, this);
            
        }
        else if (e.getActionCommand ().equals ("Move text right"))
        {x+=10;
            s.putConstraint (SpringLayout.WEST, text, x, SpringLayout.WEST, this);
            s.putConstraint (SpringLayout.NORTH, text, y, SpringLayout.NORTH, this);
            
        }
        else if (e.getActionCommand ().equals ("Move text left"))
        {
         x-=10;
            s.putConstraint (SpringLayout.WEST, text, x, SpringLayout.WEST, this);
            s.putConstraint (SpringLayout.NORTH, text, y, SpringLayout.NORTH, this);
            
        }
        else if (e.getActionCommand ().equals ("Enter")) {
        text = new JLabel (in.getText()); //sets text to input
        add (text); //Removes input
        remove(in);
        remove(enter);
        remove(instruction);
        add (a); //adds all buttons 
        add (b);
        add (c);
        add (d);
        //Constraints
        s.putConstraint (SpringLayout.WEST, a, 2, SpringLayout.WEST, this);
        s.putConstraint (SpringLayout.NORTH, a, 20, SpringLayout.NORTH, this);

        s.putConstraint (SpringLayout.WEST, b, 2, SpringLayout.WEST, this);
        s.putConstraint (SpringLayout.NORTH, b, 50, SpringLayout.NORTH, this);

        s.putConstraint (SpringLayout.WEST, c, 2, SpringLayout.WEST, this);
        s.putConstraint (SpringLayout.NORTH, c, 80, SpringLayout.NORTH, this);

        s.putConstraint (SpringLayout.WEST, d, 2, SpringLayout.WEST, this);
        s.putConstraint (SpringLayout.NORTH, d, 110, SpringLayout.NORTH, this);
        
        s.putConstraint (SpringLayout.WEST, text, 200, SpringLayout.WEST, this);
        s.putConstraint (SpringLayout.NORTH, text, 130, SpringLayout.NORTH, this);
        }
        refresh();
    }

    //Main Method
    public static void main (String[] args)
    {
        new SpringLayoutAndrey();
     
     //To run on its own, comment above and uncomment below
     
     /*JFrame f = new JFrame("tester");
     JPanel p = new SpringLayoutAndrey();
     f.add(p);
     f.setSize(1000, 1000);
     f.setVisible(true);*/
    }
}
