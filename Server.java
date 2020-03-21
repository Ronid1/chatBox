import java.net.*;
import java.util.*;
import java.io.*;

public class Server {
		
		public static Vector<ChatThread> clients = new Vector<>();
		
		public void startSever() throws IOException
		{
	        ServerSocket srvSocket = null;
	        boolean listening = true;	        
	        try {
	            srvSocket = new ServerSocket(7777);
	        }
	        
	        catch(IOException e)
	        {
	            e.printStackTrace();
	        }
	        
	        System.out.println("Server is ready");
	        Socket socket = null;
	        int i = 0;
	        
	        while (listening)
	        {
	        	try {
	        		socket = srvSocket.accept();
	        		ChatThread temp = new ChatThread(socket, i);
	        		temp.start();
	        		clients.add(temp);
	        		
	        		System.out.println("client #" + clients.get(i).getID() + " enterd chat with "+ clients.get(i).getSocket());
	        		i++;
	        	}
	        	
	        	catch (IOException e) {}
	        }
	        
	        srvSocket.close();
		}
		
		//Send a message to all users connected to server
		public static void sendToAll(String message) throws IOException
		{
			for (ChatThread user: clients)
			{
				if (!message.equals(""))
					System.out.println("sending message to " + user.getID());
				user.getOut().writeObject(message);
				user.getOut().flush();
			}
		}
		
		public static void main(String[] args) throws IOException
	    {

			Server server = new Server();
			server.startSever();
	    }
}