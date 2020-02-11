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
		private String userName;
		private String txt;
		private boolean inBusy;
		
		public ChatBox(String name)
		{
			userName = name;
			cmdSend = new JButton ("Send");
			cmdSend.addActionListener(this);
			text = new JTextField();
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
			
			inBusy = false;
		}
		
		
		public void showInChat(String s)
		{
			output.append(userName + ": " + s + "\n");
		}
		
		public void welcome()
		{
			output.append(userName + " has enterd chat!\n");
		}
		
		public String getText()
		{
			return txt;
		}


		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource() == cmdSend) {
				//getInput();
				txt = text.getText();
				showInChat(txt);
				text.setText("");
				txt = "";
			}
		}
		
		public synchronized void getInput() {
			
			while(inBusy) {
				try {
					wait();
				}
				catch(InterruptedException s) {}
			}
			
				txt = text.getText();
				showInChat(txt);
				text.setText("");
				txt = "";
				inBusy = false;
				notifyAll();
			
		}
		
	public synchronized void waitForInput()
	{
		while (!inBusy)
		{
			try {
				wait();
			}
			catch(InterruptedException e) {}
		}
		
		inBusy = true;
		notifyAll();
		
		
	}

		
}
