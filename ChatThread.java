/*
 * Class for handling a single thread (single client) in chat room
 */

import java.io.*;
import java.net.*;

public class ChatThread extends Thread{
	
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public ChatThread(Socket s)
	{
		socket = s;
		try {
				out = new PrintWriter(socket.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			}
		
		catch(IOException e) {}
	}
	
	
	public void run()
	{
		String input;
		
		try {
				input = in.readLine();
			
				while (input != null)
				{
					out.println(input);
					input = in.readLine();
				}
				
				//close all streams
				in.close();
				out.close();
				socket.close();
			}
		
		catch(IOException e) {}
	}
}