/*
 * Class for handling a single thread (single client) in chat room
 */

import java.io.*;
import java.net.*;

public class ChatThread extends Thread{
	
	private Socket socket = null;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
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
		String text;
			
		try {
				text = (String) in.readObject(); //Receive input
				while (text != null)
				{
					System.out.println(text); //TESTING
					out.writeObject(text); //send it out to all clients
					out.flush();
					text = (String) in.readObject();
				}
			
			//close all streams
			out.close();
			in.close();
			socket.close();
		}
			
			catch(IOException | ClassNotFoundException e) {}
	}

}