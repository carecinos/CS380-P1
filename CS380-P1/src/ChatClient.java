import java.net.*;
import java.io.*;


public class ChatClient{
   public static void main(String args[]) throws Exception {
      Socket socket = new Socket("codebank.xyz",38001);
      
      new readServer(socket);
      new sendServer(socket);

      System.out.println("Connected to server.");
      System.out.print("Please enter a username: ");
   }
}

/*
 * This class reads from the server and displays whats read..
 */
class readServer extends Thread{
   Socket socket; 
   readServer(Socket s){
      socket = s;
      start();
   }
   
   public void run(){
      try {
         BufferedReader streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         String line;      
         while((line = streamIn.readLine()) != null) System.out.println(line);
      }
      catch (IOException e) {}
   }
}

/*
 * This class sends the line to the server.
 */
class sendServer extends Thread{
   Socket clientSocket;
   sendServer(Socket socket){
      clientSocket = socket;
      start();
   }

   public void run(){
      try{
         PrintWriter streamOut = new PrintWriter(clientSocket.getOutputStream(),true);
         BufferedReader streamIn = new BufferedReader(new InputStreamReader(System.in));  
         String line;

         while((line = streamIn.readLine()) != null) streamOut.println(line);
      }
      catch (IOException e) {}    
   }
}