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
	private int id;
	
	public ChatThread(Socket s, int i)
	{
		socket = s;
		id = i;
		try {
	            out = new ObjectOutputStream(socket.getOutputStream());
	            out.flush();
	            in = new ObjectInputStream(socket.getInputStream());
			}
		
		catch(IOException e) {}
	}
	
	public int getID()
	{
		return id;
	}
	
	public ObjectOutputStream getOut()
	{
		return out;
	}
	
	
	public void run()
	{
		
		try {				
				//while user is connected to chat
				while ((text = (String) in.readObject()) != null)
				{
					if (!text.equals("")) {
					System.out.println(text);}
					
					//send message out to all clients
					Server.sentToAll(text);
				
				}
			//close all streams
			out.close();
			in.close();
			socket.close();
		}
		
		catch(IOException | ClassNotFoundException e) {}
		
		System.out.println("client " + id + " is offline");
	}

}