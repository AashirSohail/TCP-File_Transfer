

import java.io.*; 
import java.net.*; 


public class server {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	     
		
 
	    ServerSocket welcomeSocket = new ServerSocket(6789);
	    System.out.println("Server Listening at 6789");
	    
	    
	      while(true) { 
	  
	           Socket connectionSocket = welcomeSocket.accept();
	           sock s = new sock(connectionSocket);
	           s.start();


	      }
	      
	}

}

class sock extends Thread {

    Socket ClientSocket;
    
    sock(Socket s){
    	ClientSocket = s;
    }
    
    public void run() {
    	
    	String clientSentence = null; 
	    String capitalizedSentence = ""; 
    	
        BufferedReader inFromClient = null;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        DataOutputStream outToClient = null;
		try {
			outToClient = new DataOutputStream(ClientSocket.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        String filename = null;
		try {
			filename = inFromClient.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        System.out.printf("File name received: %s \n\n", filename);
	  

        do {
        
        try {
			clientSentence = inFromClient.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        // terminate case
        if(clientSentence.startsWith("END")) {
     	   
     	   System.out.printf("END prompt received.\n");
     	   capitalizedSentence = "Closing connection. DONE\n";
     	   System.out.println( capitalizedSentence);
     	   String done = "DONE";
     	   try {
			outToClient.writeBytes(done);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	   System.out.println("Terminated");
     	   break;
     	   
        }
        else {
     	   
     	   try(FileWriter fw = new FileWriter("Server-" + filename, true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println(clientSentence);
				    
				} catch (IOException e) {
				}
     	   
        System.out.println(clientSentence);
        capitalizedSentence = clientSentence.toUpperCase() + '\n'; 

        try {
			outToClient.writeBytes(capitalizedSentence);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        	}
        
        
        } while( true );
        
	
    	
    }
	
	
}

