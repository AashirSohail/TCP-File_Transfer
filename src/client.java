
import java.io.*; 
import java.net.*; 
import java.util.Scanner;


public class client {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
        String sentence; 
        String modifiedSentence; 

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 

        Socket clientSocket = new Socket("localhost", 6789); 

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
        BufferedReader inFromServer =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
        
        
        // getting file name-sending to server
        System.out.println("Enter file name (test.txt): ");
        String file = inFromUser.readLine(); 
        outToServer.writeBytes(file + '\n');
        
        Scanner scan = new Scanner(new File(file));
		String line;
        StringBuffer stringBuffer = new StringBuffer();
		
		// file end condition
		//while ((line = bufferedReader.readLine()) != null) {
        while(scan.hasNextLine()){	
			//putting in string buffer
        	line = scan.nextLine();
			stringBuffer.append(line);
			stringBuffer.append("\n");
			System.out.printf("\n%s", line);
			
			sentence = line;
			//sentence = inFromUser.readLine();
           	outToServer.writeBytes(sentence + '\n');
			
           	modifiedSentence = inFromServer.readLine();
			System.out.println("\nFROM SERVER: " + modifiedSentence); 
             	
		}
		sentence = "END";
		outToServer.writeBytes(sentence + '\n');
		System.out.println("\nSending END prompt.");
        clientSocket.close(); 
        
        //fileReader.close();
			scan.close();
			System.out.println("\nContents of file:");
			System.out.println(stringBuffer.toString());

	}

}


