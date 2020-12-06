import java.net.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.lang.Process;
import java.lang.Runtime;

public class Server22 {

	public static void main(String[] args) throws IOException{
		
		  try(ServerSocket ss = new ServerSocket(3807)){
		 	System.out.println("Waiting for the Client...");
		 	
		 	while(true){
		 		Socket socket = ss.accept();
		 		System.out.println("New Connection Established");
		 		new ServerThread2(socket).start();
		 	}
		 } catch(IOException ex){
		 	System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
		 } 
		
	
		
	}
	

}


