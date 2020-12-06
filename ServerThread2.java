import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ServerThread2 extends Thread{
	private Socket socket;
	
	public ServerThread2(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			
			BufferedReader in;
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			String str;
			String l = null;
			while(true) {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				str = in.readLine();
				switch (str) {
				case "1": 
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
					LocalDateTime now = LocalDateTime.now();
					out = new PrintWriter(socket.getOutputStream(), true);
					out.println(dtf.format(now));
					out.flush();
					continue;
				case "2":
					out = new PrintWriter(socket.getOutputStream(), true);
					out.println(handleCMD("uptime"));	
					out.flush();
					continue;
				case "3":
					out = new PrintWriter(socket.getOutputStream(), true);
					out.println(handleCMD("cat /proc/meminfo"));
					out.flush();
					continue;
				case "4":
					out = new PrintWriter(socket.getOutputStream(), true);
					out.println(handleCMD("netstat"));
					out.flush();
					continue;
				case "5":
						out = new PrintWriter(socket.getOutputStream(), true);
						out.println(handleCMD("who"));
						out.flush();
					continue;
				case "6":
					out = new PrintWriter(socket.getOutputStream(), true);
					out.println(handleCMD("ps -aux"));
					out.flush();
					continue;
					
				case "End": 
					out.println("Thanks that's all");
					in.close();
					out.close();
					break;
				default:
					out = new PrintWriter(socket.getOutputStream(), true);
					out.println("Invalid Entry");
					out.flush();	
				}
				break;
				}
			
		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
		}
	}
	
	public static String handleCMD(String x) throws IOException {
		String s = "";
		String l; 
		Process p = Runtime.getRuntime().exec(x);
		BufferedReader stdInput = new BufferedReader(new 
                InputStreamReader(p.getInputStream()));

  
           while((l = stdInput.readLine()) != null) {
        	   s ="\n" + l + s;
           }
      return s;
 
	}

}
