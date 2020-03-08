import java.net.*;
import java.io.*;
import javax.swing.*;

public class Client {
	
//	private boolean online = true;
	private String userName;
	private JFrame frame;
	private ChatBox box;
	
    private ObjectOutputStream out;
    private ObjectInputStream in;
	
	public static void main(String[] args) throws ClassNotFoundException
    {
		Client c = new Client();
		c.startChat();
    }
	
	private void startChat() throws ClassNotFoundException
	{
        //set user name
        userName = JOptionPane.showInputDialog(null, "Enter user name:");
        
		//GUI
		frame = new JFrame("Chat Messanger - " + userName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		box = new ChatBox();
		
		frame.add(box);
		frame.pack();
		frame.setVisible(true);
		
		//server		
		Socket socket = null;
        String host = "localhost";
        
        
        try{
	        	socket = new Socket(host, 7777);
	            out = new ObjectOutputStream(socket.getOutputStream());
	            out.flush();
	            in = new ObjectInputStream(socket.getInputStream());
	            
	            box.welcome(userName);
	            String myMessage;
	            String inputFromServer = (String)in.readObject();;
	            
	            //while receiving input, print it on the screen and wait for next
		        while(inputFromServer != null)
		        {	
		        	System.out.println("input from server:" + inputFromServer); //TESTING
		            box.showInChat(inputFromServer);
		            
		            //if I sent a message, pass it to server
		        	if (box.newMessage() == true)
		        	{
		        		myMessage = box.getText();
			         	System.out.println("in = " + myMessage); //TESTING
			            out.writeObject(userName + ": " + myMessage);
			            out.flush();
		        	}
		        	
		        	inputFromServer = (String)in.readObject();
		        }
		            	                	
	            
	            System.out.println("done"); //TESTING

	            //close all streams
	            out.close();
	            in.close();
	            socket.close();
        	}
        
        
        catch(UnknownHostException e) {}
        catch(IOException e) {}
    }
}