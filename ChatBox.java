/*
 * GUI for user side chat box
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatBox extends JPanel implements ActionListener{

		private JButton cmdSend;
		private JTextArea text;
		private JPanel input;
		
		private JTextArea chat;
		
		public ChatBox()
		{
			cmdSend = new JButton ("Send");
			cmdSend.addActionListener(this);
			text = new JTextArea();
			
			//chat window area
			chat = new JTextArea();
			JScrollPane scroll = new JScrollPane();
			scroll.add(chat);
			
			//add button and text field to input panel
			input.add(cmdSend, BorderLayout.EAST);
			input.add(text, BorderLayout.CENTER);
			
			this.add(input, BorderLayout.SOUTH);
		}
		
		
		public void showInChat(String s)
		{
			text.append(s);
		}


		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource() == cmdSend)
			{
				String s = text.getText();
				showInChat(s);
			}
					
		}

		
}
