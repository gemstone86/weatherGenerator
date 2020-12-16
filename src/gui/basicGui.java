package gui;

import java.awt.*;

import javax.swing.JFrame;

public class basicGui extends Frame{
	Panel panel = new Panel();
	Button btn = new Button("Press");
	
	
	public basicGui(){
		panel.add(btn);	
		
		JFrame frame = new JFrame();
	}
}
