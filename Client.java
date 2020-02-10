import java.net.*;
import java.io.*;
import javax.swing.*;

public class Client {
	
	public static void main(String[] args)
    {
		Client c = new Client();
		c.startChat();
    }
	
	private void startChat()
	{
        //Set a user name
        String userName = JOptionPane.showInputDialog(null, "Enter user name:");
        
		//GUI
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		ChatBox box = new ChatBox(userName);
		
		frame.add(box);
		frame.pack();
		frame.setVisible(true);
		
				
		Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String host = "localhost";
        
        
        try{
	        	socket = new Socket(host, 7777);
	            out = new PrintWriter(socket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            //in = new InputStreamReader(socket.getInputStream());
	            
	            box.welcome();
	            //String s = JOptionPane.showInputDialog(null, "Enter a string to send:");
	            String input = box.getText();
	            
	            //while receiving input, print it on the screen and wait for next
	            while(in != null)
	            {
	                out.println(input); //print in server
	                box.showInChat(""+in.read()); //print in client
	                //s = JOptionPane.showInputDialog(null, "Enter a string to send:");
	            }
	            
	            //close all streams
	            out.close();
	            in.close();
	            socket.close();
        	}
        
        
        catch(UnknownHostException e) {}
        catch(IOException e) {}
    }
}
