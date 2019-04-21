import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;  
/**
 * This class 
 * @author Jason Li
 * @version 1 01.28.15
 * <p>
 * <pre>
 * Variable Name            Variable Type        Description
 * TestFrame                JFrame               The frame where everything is displayed.
 * </pre>
 */ 
public class LayoutsTestJason2 extends JFrame{
  
  /**
 * This is the constructor.
 */
  public LayoutsTestJason2(){
   super("Layout ");
    setLayout(new FlowLayout());
    JPanel flj = new FlowLayoutJason();
    JPanel glj = new GroupLayoutJason();
    JMenuBar mb=new JMenuBar();  
    JMenuItem flowMenuItem= new JMenuItem("Flow Layout");
    JMenuItem closeMenuItem = new JMenuItem("Close");
    JMenuItem groupMenuItem = new JMenuItem("Group Layout");
    
    mb.add(flowMenuItem);
    mb.add(groupMenuItem);
      mb.add(closeMenuItem);
    setJMenuBar(mb);
    JPanel frame = new JPanel();
    
    flowMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        frame.remove(glj);
        frame.add(flj);
        setContentPane(frame);
        setSize(1080,1920);
        frame.setVisible(true);
      }

    });

    groupMenuItem.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        frame.remove(flj);
        frame.add(glj);
        setSize(1080,1920);
        frame.setVisible(true);
        setContentPane(frame);
      }

    });
    
    closeMenuItem.addActionListener(new ActionListener() {
    
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }

    });

    
    pack();
    
    setSize(400,400);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
  
    
     /** Description of main(String [] args)
       * This method calls the JFrameTest constructor to
       * create the application.
      * 
      * @param args [ ]  String array that allows command line
      * parameters to be used when executing the program.
      */ 
  public static void main(String[] args) { 
    new LayoutsTestJason2();
  }
  
  /* ADD YOUR CODE HERE */
  
}
