import java.net.*;
import java.io.*;
import javax.swing.*;

public class Client {
	
	private String userName;
	private JFrame frame;
	private ChatBox box;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private boolean online = true;
    private String myMessage;
	
	public static void main(String[] args) throws ClassNotFoundException
    {
		Client c = new Client();
		c.startChat();
    }
	
	public Client()
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
		socket = null;
        String host = "localhost";
        
        try{
	        	socket = new Socket(host, 7777);
	            out = new ObjectOutputStream(socket.getOutputStream());
	            out.flush();
	            in = new ObjectInputStream(socket.getInputStream());
        }
        catch(IOException e) {}
	}
	
	private void startChat() throws ClassNotFoundException
	{ 
		System.out.println(userName + "is online");
        myMessage = userName + " enterd chat";
        String inputFromServer;
        
        try {
        	 //send welcome message
	         out.writeObject(myMessage);
	         out.flush();

		        //while connected to chat, send and receive input
	            while (online) {
		            
		            //if I sent a message, pass it to server
		            //if (!(myMessage = box.getMessage()).equals(null))
		            if ((myMessage = box.getMessage()) != (null))
			            out.writeObject(userName + ": " + myMessage);
		           
		            else 
		            	out.writeObject("");
		            
		            out.flush();
		            
		            //print messages from server
		            if (!(inputFromServer = (String)in.readObject()).equals(""))
		            {
		            	System.out.println("new message!");
			            box.showInChat(inputFromServer);
		            } 
		            
	            }
		        
		       out.writeObject(userName + " left chat"); //send leaving message
		       out.flush();
		
		        //close all streams
		        out.close();
		        in.close();
		        socket.close();
	       }

        catch(IOException e) { System.out.println("no object to read");}
    }
	
}