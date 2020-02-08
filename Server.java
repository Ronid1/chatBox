import java.net.*;
import java.io.*;

public class Server {

	 public static void main(String[] args)
	    {
	        ServerSocket srv = null;
	        boolean listening = true;
	        ChatThread chat;
	        
	        try {
		            srv = new ServerSocket(7777);
	
		            System.out.println("Ready");
		            Socket socket = null;
		            
		            while(listening)
		            {
		                socket = srv.accept();
		                chat = new ChatThread(socket);
		                chat.start();
		            }         
	        	}
	        
	        
	        catch(InterruptedIOException e)  {}
	        catch(IOException e) {}
	        
	    }
}
