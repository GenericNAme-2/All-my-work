import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


/**
 * @author Jason Ngo
 * @version 1.0 March 29, 2019
 * This demonstrates the uses of the LayoutManager GroupLayout in java such as rearranging components in horizontal/vertical groups. It extends JPanel and implements the ActionListener interface.
 * <p>
 * <pre>
 * Variable Name            Variable Type        Description
 * gLayout                  GroupLayout          The LayoutManager for this object
 * button4                  JButton              Button for changing the horizontal group from sequential to parallel and back. Its name changes as well.
 * button5                  JButton              Button for changing the vertical group from parallel to sequential and back. Its name changes as well.
 * button6                  JButton              Button that will be replaced by a JTextField when you click on it.
 * button7                  JButton              Allows you to turn the JTextField back into a button
 * textField1               JTextField           Textfield that replaces a button when the button is clicked.
 * componentReplaceable     JComponent           Stores the current shown object (between the button and textfield)
 * </pre>
 */
public class GroupLayoutJason extends JPanel implements ActionListener
{
    JComponent componentReplaceable;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JTextField textField1;
    GroupLayout gLayout;

    /**
     * This is the constructo which 
     */
    public GroupLayoutJason ()
    {
      gLayout = new GroupLayout (this);
      setLayout (gLayout);
      gLayout.setAutoCreateGaps (true);

      button4 = new JButton ("Parallel-Horizontal");
      button5 = new JButton ("Sequential-Vertical");
      button6 = new JButton ("Replace Button With Textfield");
      button7 = new JButton ("Replace Text With Button");
      textField1 = new JTextField ("Here is some text");
 
      componentReplaceable = button6;
 
      gLayout.setHorizontalGroup (gLayout.createSequentialGroup ().addComponent (button4)
                               .addComponent (button5).addComponent (componentReplaceable).addComponent (button7));
      gLayout.setVerticalGroup (gLayout.createParallelGroup ().addComponent (button4)
                             .addComponent (button5).addComponent (componentReplaceable).addComponent (button7));
 
      button4.addActionListener (this);
      button5.addActionListener (this);
      button6.addActionListener (this);
      button7.addActionListener (this);
      button7.setVisible (false);
    }


    /**
     * @Override
     * This is the override of the actionPerformed method from the ActionListener interface.
     * This method is used here to modify the panel (change the scheme of the groups (parallel/sequential)) when the specific button is pressed.
     * It also renames the buttons.
     * @param e ActionEvent, stores the recently performed ActionEvent object
     */
    public void actionPerformed (ActionEvent e)
    {
      if (e.getActionCommand ().equals ("Parallel-Horizontal"))
      {
     gLayout.setHorizontalGroup (gLayout.createParallelGroup ().addComponent (button4)
      .addComponent (button5).addComponent (componentReplaceable).addComponent (button7));
     button4.setText ("Sequential-Horizontal");
      }
      else if (e.getActionCommand ().equals ("Sequential-Horizontal"))
      {
     gLayout.setHorizontalGroup (gLayout.createSequentialGroup ().addComponent (button4)
      .addComponent (button5).addComponent (componentReplaceable).addComponent (button7));
     button4.setText ("Parallel-Horizontal");
      }
      else if (e.getActionCommand ().equals ("Sequential-Vertical"))
      {
     gLayout.setVerticalGroup (gLayout.createSequentialGroup ().addComponent (button4)
      .addComponent (button5).addComponent (componentReplaceable).addComponent (button7));
     button5.setText ("Parallel-Vertical");
      }
      else if (e.getActionCommand ().equals ("Parallel-Vertical"))
      {
     gLayout.setVerticalGroup (gLayout.createParallelGroup ().addComponent (button4)
      .addComponent (button5).addComponent (componentReplaceable).addComponent (button7));
     button5.setText ("Sequential-Vertical");
      }
      else if (e.getActionCommand ().equals ("Replace Button With Textfield"))
      {
     gLayout.replace (button6, textField1);
     componentReplaceable = textField1;
     button7.setVisible (true);
      }
      else
      {
     if (e.getActionCommand ().equals ("Replace Text With Button"))
     {
       gLayout.replace (textField1, button6);
       componentReplaceable = button6;
       button7.setVisible (false);
     }
 }
 revalidate ();
    }
      /**
   * This methods creates a new object that launches the frame
   * and the rest of the program.
   */
  public static void main (String[] args)
  {
    new GroupLayoutJason ();
  }

} // GroupLayoutDemo class
