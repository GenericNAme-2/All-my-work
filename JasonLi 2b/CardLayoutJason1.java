import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * @author Jason Li
 * @version 1.0, Mar. 29, 2019
 * <p>
 * This program demonstrates the 4 layouts: FlowLayout, GridBagLayout, SpringLayout
 * and GroupLayout in various ways over the course of 6 cards.
 * </p>
 */
public class CardLayoutJason1 extends JFrame
{
  /**
   * <p>
   * The constructor method builds all the cards and adds
   * them to the frame. It also creates a tabbed pane which
   * is used to cycle through demos.
   * </p>
   * <pre>
   * Variable Name    Type                 Description
   * sla              SpringLayoutAndrey   Used to Demo the variuos Spring Layouts.
   * gbla             GridBagLayoutAndrey  Used to demo GridBagLayout
   * glt              GroupLayoutJason     Used to demo GroupLayout
   * flj              FlowLayoutJason      Used to demo FlowLayout.
   * panel            JPanel               Used for the main cardlayout content.
   * tabbedPane       JTabbedPane          The tabbed pane used to cycle through demos.
   * allLayouts       JPanel               Used for borderlayout and the collection of all 4 layouts.
   * sLayout          SpringLayoutAndrey   Used to demo SpringLayout in the BorderLayout 
   * </pre>
   */
  public CardLayoutJason1(){
    
    super("CardLayout By Jason Li");
    JPanel panel = new JPanel();
    JTabbedPane tabbedPane = new JTabbedPane();
    
    CardLayout layout = new CardLayout ();
    panel.setLayout(layout);
    
    FlowLayoutJason flj = new FlowLayoutJason();
    panel.add(flj, "FlowLayout");
    tabbedPane.addTab("FlowLayout", flj);
    
    GroupLayoutJason glj = new GroupLayoutJason ();
    panel.add(new GroupLayoutJason (), "GroupLayout");
    tabbedPane.addTab("GroupLayout", glj);
    
    flj = new FlowLayoutJason();
    flj.add(new GroupLayoutJason());
    panel.add(flj,"GroupLayout within FlowLayout");
    tabbedPane.addTab("GroupLayout within FlowLayout",flj);
    
    SpringLayoutAndrey sla = new SpringLayoutAndrey ();
    panel.add(new SpringLayoutAndrey (), "Springlayout");
    tabbedPane.addTab("SpringLayout", sla);
    
    GridBagLayoutAndrey gbla = new GridBagLayoutAndrey ();
    panel.add(new GridBagLayoutAndrey (), "GridBagLayout");
    tabbedPane.addTab("GridBagLayout", gbla);
    
    JPanel allLayouts = new JPanel (new BorderLayout());
    SpringLayoutAndrey sLayout = new SpringLayoutAndrey();
    sLayout.setPreferredSize (new Dimension(350,300));
    allLayouts.add(sLayout, BorderLayout.LINE_START);
    allLayouts.add(new FlowLayoutJason(), BorderLayout.SOUTH);
    allLayouts.add(new GroupLayoutJason(), BorderLayout.EAST);
    allLayouts.add(new JLabel("<HTML>FlowLayout - Jason<BR>SpringLayout - Andrey<BR>GroupLayout - Jason<BR>GridBagLayout - Andrey</HTML>"), BorderLayout.CENTER);
    allLayouts.add(new GridBagLayoutAndrey(), BorderLayout.NORTH);
    panel.add (allLayouts, "All Layouts");
    tabbedPane.addTab("All Layouts", allLayouts); 
    
    getContentPane().add(panel);
    getContentPane().add(tabbedPane);
    setSize (1000, 1000);
    setVisible (true);
  }
  
  /**
   * This methods creates a new object that launches the frame
   * and the rest of the program.
   */
  public static void main (String[] args)
  {
    new CardLayoutJason1 ();
  }
} 
