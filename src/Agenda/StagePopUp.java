package Agenda;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.border.*;

public class StagePopUp extends JFrame {
	
	private Schedule schedule;
	private GUI Gui;

	public StagePopUp(Schedule schedule){
		super("New stage");
		setSize(350, 100);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		makeFrame();
		this.schedule = schedule;
	}
	
	private void makeFrame(){
		
		JPanel content = new JPanel(null);
		JLabel nameLabel = new JLabel("Stage Name: ");
		content.add(nameLabel);
		final JTextField name = new JTextField(20);
		content.add(name);
		
		JButton addStage = new JButton("Add stage");
        addStage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		schedule.addStage(name.getText(), 600, 1200, 60);
          	}
        });
		content.add(addStage);
		
		nameLabel.setBounds(10,10,100,20);
		name.setBounds(110,10,200,20);
		addStage.setBounds(110,40,200,20);
		
		setContentPane(content);
		setVisible(true);
		
	}	
}
