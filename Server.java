import java.net.*;
import java.io.*;

public class Server {

	    public static void main(String[] args) throws IOException
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
	        
	        while (listening)
	        {
	        	try {
	        		socket = srvSocket.accept();
	        		new ChatThread(socket).start();
	        	}
	        	
	        	catch (IOException e) {}
	        }
	        
	        srvSocket.close();
	    }
}
