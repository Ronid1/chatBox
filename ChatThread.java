/*
 * Class for handling a single thread (single client) in chat room
 */

import java.io.*;
import java.net.*;

public class ChatThread extends Thread{
	
	private Socket socket = null;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String text;
	
	public ChatThread(Socket s)
	{
		socket = s;
		try {
	            out = new ObjectOutputStream(socket.getOutputStream());
	            out.flush();
	            in = new ObjectInputStream(socket.getInputStream());
			}
		
		catch(IOException e) {}
	}
	
	
	public void run()
	{
		
		try {
				System.out.println("new client online");
				
				//while user is connected to chat
				while ((text = (String) in.readObject()) != null)
				{
					if (!text.equals("")) {
					System.out.println(text);}
					out.writeObject(text); //send it out to all clients
					out.flush();
				}
				
			//close all streams
			out.close();
			in.close();
			socket.close();
		}
		
		catch(IOException | ClassNotFoundException e) {}
		
		System.out.println("client is offline");
	}

}