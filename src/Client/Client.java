package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 4005;
    
    public static void main(String[] args) throws IOException {
    	Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        ServerConnection serverConn = new ServerConnection(socket);
        new Thread(serverConn).start();

//        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//        while (true) {
//            System.out.printf("> ");
//
//            String command = keyboard.readLine();
//            
//            if (command.equals("!quit"))
//                break;
//
//            out.println(command);
//        }

//        socket.close();
//        System.exit(0);
    }
}
