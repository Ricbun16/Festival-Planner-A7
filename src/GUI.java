import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.border.*;

public class GUI extends JFrame{
	
	public static void main(String s[]){
		new GUI();
	}
	
	public GUI(){
		super("Agenda");
		setSize(1000, 1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		makeFrame();
	}
	
	private void makeFrame(){
		
		JPanel content = new JPanel(new BorderLayout());
        Border blackline = BorderFactory.createLineBorder(Color.black);
        
        JPanel westBorder = new JPanel();
        westBorder.setBorder(blackline);
        westBorder.setLayout(new BoxLayout(westBorder, BoxLayout.Y_AXIS));
        westBorder.add(new JLabel(" "));
        JButton artistAndGenre = new JButton("Artist & Genre");
        artistAndGenre.setSize(100, 200);
        artistAndGenre.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		
          	}
        }); 
//      for(final Stage s : stages) {
//          JButton button = new JButton(s.getName());
//          button.addActionListener(new ActionListener() {
//                              public void actionPerformed(ActionEvent e) { 
//                                  makeStage(s);
//                              }
//                         });
//          westBorder.add(button);
//      }
        westBorder.add(artistAndGenre);      
        content.add(westBorder, BorderLayout.WEST);
        
        JPanel eastBorder = new JPanel();
        eastBorder.setBorder(blackline);
        eastBorder.setLayout(new BoxLayout(eastBorder, BoxLayout.Y_AXIS));
        eastBorder.add(new JLabel(" "));
        JButton addStage = new JButton("Add stage");
        addStage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		new StagePopUp();
          	}
        });
        eastBorder.add(addStage);    
        
        JButton addPerformance = new JButton("Add show");
        addPerformance.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		
          	}
        });
        eastBorder.add(addPerformance);
        
        content.add(eastBorder, BorderLayout.EAST);
        
        JPanel northBorder = new JPanel(new FlowLayout());
        JLabel naamLable = new JLabel("Festival Naam - 16-02-2016");
        northBorder.setBorder(blackline);
        northBorder.add(naamLable);
        content.add(northBorder, BorderLayout.NORTH);
        
        JPanel southBorder = new JPanel();
        southBorder.setBorder(blackline);
        southBorder.add(new JLabel(" "));
        content.add(southBorder, BorderLayout.SOUTH);
        
        JPanel center = new JPanel(new BorderLayout());
        JPanel centerNorth = new JPanel(new FlowLayout());
        centerNorth.add(new JLabel("Artist & Genres"));
        centerNorth.setBorder(blackline);
        center.add(centerNorth, BorderLayout.NORTH);
        
        center.add(new JScrollPane(new JTable()), BorderLayout.CENTER);
        
        content.add(center, BorderLayout.CENTER);
        
        setContentPane(content);
        setVisible(true);
	}

}
