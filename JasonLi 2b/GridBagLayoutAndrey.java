import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Font;



/**
 * @author Andrey Starenky
 * March 28, 2019
 * Version 1.0
 * <p>
 * this program demonstrates the GridBag Layout, by using different formatting,
 * spacing, and weights.
 * </p>
 * 
 * <pre>
 * Variable Name            Variable Type        Description
 * gb						GridBagLayout		 Instance of the GridBagLayout assigned to the JPanel
 * gbc						GridBagConstraints   Stores Constraints for the layout
 * nbsButton				Button				 Button for "No Button Spacing" that removes spacing in between buttons
 * absButton				Button				 Button for "Add Button Spacing" that adds spacing in between buttons
 * sButton					Button				 Button for "Small Buttons" that shrinks size of buttons with weight
 * lButton					Button				 Button for "Large Buttons" that increases size of buttons with weight
 * magicButton				Button				 Button for "Irreversible Magic" that changes colors
 * </pre>
 */
public class GridBagLayoutAndrey extends JPanel implements ActionListener
{
	//Instance Variables
    GridBagLayout gb = new GridBagLayout ();
    GridBagConstraints gbc = new GridBagConstraints ();
    Button nbsButton, absButton, sButton, lButton, magicButton;
    
    public GridBagLayoutAndrey() {
    	setLayout (gb);
    	//Resource used to learn: https://www.javatpoint.com/java-gridbaglayout
        gbc.fill = GridBagConstraints.BOTH;
        gbc.ipadx=140;	//Sets padding
        gbc.ipady=60;
        gbc.gridx = 0;  //Which grid square to use
        gbc.gridy = 0;
        nbsButton = new Button ("No Button Spacing"); //New Button
        nbsButton.addActionListener(this);
        add (nbsButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        absButton = new Button ("Add Button Spacing"); //New Button
        absButton.addActionListener(this);
        add (absButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        sButton = new Button ("Small Buttons"); //New Button
        sButton.addActionListener(this);
        add (sButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        lButton = new Button ("Large Buttons"); //New Button
        lButton.addActionListener(this);
        add (lButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        magicButton = new Button ("Irreversible Magic Button"); //New Button
        magicButton.addActionListener(this);
        add (magicButton, gbc);
    }

    public void refresh(){
	    repaint();
	    revalidate();
	  }
    
    public void actionPerformed (ActionEvent e)
    {//When a button is pressed
        if (e.getActionCommand ().equals ("Irreversible Magic Button"))
        {
            nbsButton.setBackground(Color.RED);   //Changes Colours
            absButton.setBackground(Color.YELLOW);
            sButton.setBackground(Color.CYAN);
            lButton.setBackground(Color.GREEN);
            magicButton.setBackground(Color.ORANGE);
        }
        else if (e.getActionCommand ().equals ("Add Button Spacing"))
        {
        	remove (nbsButton); //Adds spacing to buttons
        	remove (absButton);
        	remove (sButton);
        	remove (lButton);
        	remove (magicButton);
        	
        	gbc.gridwidth=1;
        	gbc.insets=new Insets(12,12,18,18);//Ads spacing
        	
        	gbc.gridx = 0;
            gbc.gridy = 0;
        	add (nbsButton, gbc);
        	gbc.gridx = 1;
            gbc.gridy = 0;
        	add (absButton, gbc);
        	gbc.gridx = 0;
            gbc.gridy = 1;
        	add (sButton, gbc);
        	gbc.gridx = 1;
            gbc.gridy = 1;
        	add (lButton, gbc);
        	gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
        	add (magicButton, gbc);
        }
        else if (e.getActionCommand ().equals ("No Button Spacing"))
        {
        	remove (nbsButton); //Removes spacing from buttons
        	remove (absButton);
        	remove (sButton);
        	remove (lButton);
        	remove (magicButton);
        	
        	gbc.gridwidth=1;
        	gbc.insets=new Insets(0,0,0,0);//Removes spacing
        	
        	gbc.gridx = 0;
            gbc.gridy = 0;
        	add (nbsButton, gbc);
        	gbc.gridx = 1;
            gbc.gridy = 0;
        	add (absButton, gbc);
        	gbc.gridx = 0;
            gbc.gridy = 1;
        	add (sButton, gbc);
        	gbc.gridx = 1;
            gbc.gridy = 1;
        	add (lButton, gbc);
        	gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
        	add (magicButton, gbc);
        	        	
        }
        else if (e.getActionCommand ().equals ("Small Buttons"))
        {
        	remove (nbsButton); //Shrinks buttons
        	remove (absButton);
        	remove (sButton);
        	remove (lButton);
        	remove (magicButton);
        	        	
        	gbc.weightx=0;//Small buttons (has outside padding)
        	gbc.weighty=0;
        	
        	gbc.gridwidth=1;
        	        	
        	gbc.gridx = 0;
            gbc.gridy = 0;
        	add (nbsButton, gbc);
        	gbc.gridx = 1;
            gbc.gridy = 0;
        	add (absButton, gbc);
        	gbc.gridx = 0;
            gbc.gridy = 1;
        	add (sButton, gbc);
        	gbc.gridx = 1;
            gbc.gridy = 1;
        	add (lButton, gbc);
        	gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
        	add (magicButton, gbc);
        	
        }
        else if (e.getActionCommand ().equals ("Large Buttons"))
        {
        	
        	remove (nbsButton); //Grows buttons
        	remove (absButton);
        	remove (sButton);
        	remove (lButton);
        	remove (magicButton);
        	        	
        	gbc.weightx=1;//Grows buttons (no outside padding)
        	gbc.weighty=1;
        	
        	gbc.gridwidth=1;
        	        	
        	gbc.gridx = 0;
            gbc.gridy = 0;
        	add (nbsButton, gbc);
        	gbc.gridx = 1;
            gbc.gridy = 0;
        	add (absButton, gbc);
        	gbc.gridx = 0;
            gbc.gridy = 1;
        	add (sButton, gbc);
        	gbc.gridx = 1;
            gbc.gridy = 1;
        	add (lButton, gbc);
        	gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
        	add (magicButton, gbc);
        	
        }
        refresh();
    }


    public static void main (String[] args)
    {
        GridBagLayoutAndrey s = new GridBagLayoutAndrey();
        
        //To run on its own, comment above and uncomment below
        
    	/*JFrame f = new JFrame("tester");
    	JPanel p = new GridBagLayoutAndrey();
    	f.add(p);
    	f.setSize(700, 500);
    	f.setVisible(true);*/
    }
}
