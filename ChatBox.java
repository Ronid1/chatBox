/*
 * GUI for user side chat box
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatBox extends JPanel implements ActionListener{
	
		private JButton cmdSend;
		private JTextField text;
		private JPanel input;
		private JTextArea output;
		private String txt;
		private boolean newMsg = false;
		
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
		}
		
		public void showInChat(String s)
		{
			output.append(s + "\n");
			txt = ""; //clear saved text
		}
		
		public void welcome(String name)
		{
			showInChat(name + " has enterd chat!\n");
		}
		
		public String getText()
		{
			System.out.println("text = " + txt);
			return txt;
		}
		
		public boolean newMessage()
		{
			if (newMsg == true)
			{
				System.out.println("new message TRUE"); //TESTING
				newMsg = false;
				return true;
			}
			
			return false;
		}
		

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//send text by either pressing "send" or Enter key
			if (e.getSource() == cmdSend || e.getSource() == text) 
			{
				newMsg = true; //set flag for new message
				txt = text.getText();
				System.out.println("message sent: " + txt); //TESTING
				text.setText(""); //clear typing area
			}
		}
		
}