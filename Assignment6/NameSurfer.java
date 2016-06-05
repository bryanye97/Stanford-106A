/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */	
	public void init() {
		
		graph = new NameSurferGraph();
		add(graph);
		
	    nameLabel = new JLabel("Name: ");
	    add(nameLabel, SOUTH);
	   
	    nameTextField = new JTextField(30);
	    add(nameTextField, SOUTH);
	    nameTextField.addActionListener(this);
	    
	    graphButton = new JButton("Graph");
	    add(graphButton, SOUTH);
	    
	    clearButton = new JButton("Clear");
	    add(clearButton, SOUTH);
	    
	    addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == graphButton) {
			NameSurferEntry entry = namesDataBase.findEntry(nameTextField.getText());
			graph.addEntry(entry);
			graph.update();
		} else if (e.getSource() == nameTextField) {
			NameSurferEntry entry = namesDataBase.findEntry(nameTextField.getText());
			graph.addEntry(entry);
			graph.update();
		} else if (e.getSource() == clearButton) {
			graph.clear();
			graph.update();
		}
	}
	
	private JLabel nameLabel;
	private JTextField nameTextField;
	private JButton graphButton;
	private JButton clearButton;
	
	private NameSurferDataBase namesDataBase = new NameSurferDataBase("names-data.txt");
	private NameSurferGraph graph;
}
