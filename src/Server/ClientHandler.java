package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

import GuessingGame.Word;

public class ClientHandler extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Word word;
    private String CurrentWord;

    
    public ClientHandler(Socket socket,Word word) throws IOException {
        this.socket = socket;
        this.word = word;
        this.CurrentWord = word.getWord();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

    }

    public void println(String s) 
    {
        out.println(s);
    }

    public void run() {


        	String playerGuess;
			try {
//		        System.out.printf("CurrentWord:%s\n", CurrentWord);

				while ((playerGuess = in.readLine()) != null)
				{
						System.out.printf("playerGuess1:%s\n", playerGuess);
//						if(playerGuess.equals("!start"))
//		            		println(CurrentWord);
						if(playerGuess.equals("!hint"))
		                    println(word.getHint());
						else if(playerGuess.equals(CurrentWord))
						{	
							//a new word should be selected!
							
		                	println("Correct");
		                	println(CurrentWord);
		                }
						else if(!playerGuess.equals(CurrentWord))
		                	println("Wrong");
						else if(playerGuess.equals("!quit"))
							break;
				}
			}
			catch ( SocketException e1) 
			{
				e1.printStackTrace();
			}
			catch(IOException e2)
			{
				e2.printStackTrace();
			}

//        	String request;
//            while ((request = in.readLine()) != null) 
//            {
//            	if(request.equals("!start"))
//            		println(CurrentWord);
//            	
//            	if(request.equals("!hint"))
//                    println(word.getHint());

//            	else if(request.equals(CurrentWord))
//                	println("Correct answer!");
//                
//                else if(!request.equals(CurrentWord))
//                	println("Wrong answer! try again..");
//            }
//            in.close();
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }

        try {
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        System.err.println("closing socket");
        
    }


}
