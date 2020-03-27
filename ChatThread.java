/*
 * Class for handling a single thread (single client) in chat room
 */

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

public class ChatThread extends Thread{
	
	private Socket socket = null;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String text;
	private int id;
	public Queue<String> messages = new LinkedList<>();
	
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
	
	public Socket getSocket()
	{
		return socket;
	}
	
	public void addToQueue(String s)
	{
		messages.add(s);
	}
	
	public void run()
	{
		
		try {				
				//while user is connected to chat
				while ((text = (String) in.readObject()) != null)
				{
					if (!text.equals(""))
					{
						//print text in server stream
						System.out.println(text);
						
						//add message to all clients output queue
						for (ChatThread client: Server.clients)
							client.addToQueue(text);
					}
					
					//if there is a message in the queue - send it
					if (messages.peek() != null)
						out.writeObject(messages.remove());
					
					//else, send empty message
					else
						out.writeObject("");
					
					out.flush();

				}
				
			//close all streams
			out.close();
			in.close();
			socket.close();
		}
		
		catch(IOException e) {} 
		catch (ClassNotFoundException e) {}
		
		System.out.println("client " + id + " is offline");
	}

}