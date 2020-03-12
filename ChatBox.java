/*
 * GUI for user side chat box
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class ChatBox extends JPanel implements ActionListener{
	
		private JButton cmdSend;
		private JTextField text;
		private JPanel input;
		private JTextArea output;
		private ArrayList<String> messages;
		
		public ChatBox()
		{
			cmdSend = new JButton ("Send");
			cmdSend.addActionListener(this);
			text = new JTextField();
			text.addActionListener(this);
			input = new JPanel(new BorderLayout());
			this.setLayout(new BorderLayout());
			
			//chat window area
			output = new JTextArea(30,50);
			JScrollPane scroll = new JScrollPane(output);
			
			//add button and text field to input panel
			input.add(cmdSend, BorderLayout.EAST);
			input.add(text, BorderLayout.CENTER);
			
			this.add(input, BorderLayout.SOUTH);
			this.add(scroll, BorderLayout.CENTER);
			messages = new ArrayList<String>();
		}
		
		public void showInChat(String s)
		{
			output.append(s + "\n");
		}
		
		
		public String getMessage()
		{
			if (messages.size() > 0)
				return messages.remove(0);
			
			return null;
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{ 
			//send text by either pressing "send" or Enter key
			if (e.getSource() == cmdSend || e.getSource() == text) 
			{
				messages.add(text.getText());
				text.setText(""); //clear typing area
			}
		}
}