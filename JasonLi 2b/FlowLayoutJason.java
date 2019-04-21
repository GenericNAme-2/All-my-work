/**
 * Auto Generated Java Class.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * @author Jason Li
 * @version 1.0 Mar 29, 2019
 * This demonstrates the uses of the LayoutManager FlowLayout in java such as changing component spacing and order.
 * It extends JPanel and implements the ActionListener interface.
 * <p>
 * <pre>
 * Variable Name            Variable Type        Description
 * layout                   FlowLayout           The LayoutManager for this object
 * button2                  JButton              The button used to change spacings, its name changes from "Widen spacing"
 *                                               to "Remove spacing" and back
 * </pre>
 */
public class FlowLayoutJason extends JPanel implements ActionListener
{
  FlowLayout layout;
  JButton button2;
  /**
   * This method is the constructor, it builds the JPanel and components and adds them to the JPanel.
   * It also adds ActionListeners to some of the components.
   * <p>
   * <pre>
   * Variable Name            Variable Type        Description
   * button1                  JButton              The button used to reverse the order of the components.
   * </pre>
   */
  public FlowLayoutJason ()
  {
    layout = new FlowLayout (FlowLayout.LEFT);
    setLayout (layout);
    setComponentOrientation (ComponentOrientation.LEFT_TO_RIGHT);
    
    JButton button1 = new JButton ("Reverse");
    button2 = new JButton ("Widen spacing");
    
    add (button1);
    add (button2);
    
    button1.addActionListener (this);
    button2.addActionListener (this);
    
  }
  
  /**
   * @Override
   * This is the override of the actionPerformed method from the ActionListener interface.
   * This method is used here to modify the panel (tell panel to increase/decrease spacing and reverse component order) when the specific button is pressed.
   * @param ae ActionEvent, stores the recently performed ActionEvent object
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand ().equals ("Reverse"))
    {
      changeOrientation ();
      changeAlignment ();
    }
    if (ae.getActionCommand ().equals ("Widen spacing"))
    {
      button2.setText ("Remove spacing");
      layout.setHgap (50);
      layout.setVgap (50);
    }
    else
    {
      if (ae.getActionCommand ().equals ("Remove spacing"))
      {
        button2.setText ("Widen spacing");
        layout.setHgap (0);
        layout.setVgap (0);
      }
    }
    revalidate ();
  }
  
  /**
   * This method puts the components on the opposite of the panel (left or right) using getAlignment() and setAlignment().
   */
  private void changeAlignment ()
  {
    
    if (layout.getAlignment () == FlowLayout.LEFT)
    {
      layout.setAlignment (FlowLayout.RIGHT);
    }
    else
    {
      layout.setAlignment (FlowLayout.LEFT);
    }
  }
  
  /**
   * This method reverses the components' order using setComponentOrientation and getComponentOrientation
   */
  private void changeOrientation ()
  {
    if (getComponentOrientation ().equals (ComponentOrientation.LEFT_TO_RIGHT))
    {
      setComponentOrientation (ComponentOrientation.RIGHT_TO_LEFT);
    }
    else
    {
      setComponentOrientation (ComponentOrientation.LEFT_TO_RIGHT);
    }
  }
  
  
  // main method
} // FlowLayoutJason class
