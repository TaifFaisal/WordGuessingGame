package Client;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.*;
import java.awt.event.*;

public class ServerConnection extends JFrame implements Runnable {
    private Socket server;
    private BufferedReader in;
    private PrintWriter out;
    private JButton startButton;
    private JButton quitButton;
    private JTextField guess;
    private JTextArea result;
    private JLabel word;
    private JLabel hintLabel;
    private static boolean start = false;
    private static int hint = 0;
    private static int winner = 0;
    
    public ServerConnection(Socket s) throws IOException
    {

        server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        out = new PrintWriter(server.getOutputStream(), true);
        Font font = new Font("Times", Font.BOLD, 18);
        Font font2 = new Font("Times", Font.ITALIC, 14);

        JFrame frame = new JFrame("Word Guessing Game");

        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        
        guess = new JTextField(30);
//        guess.setText("Click start to begin...");
        
        
        result = new JTextArea();
        result.setFont(font);
        result.setForeground(Color.BLUE);
        result.setEditable( false );
        
        word = new JLabel();
        word.setFont(font);
        word.setForeground(Color.BLACK);
        word.setAlignmentX(Component.CENTER_ALIGNMENT);

        hintLabel = new JLabel();
        hintLabel.setFont(font2);
        hintLabel.setForeground(Color.BLACK);
        hintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
//        Dimension size = startButton.getPreferredSize();
//        startButton.setBounds(10, 3, size.width, size.height);
//        quitButton.setBounds(220, 3, size.width, size.height);
       

        
//        frame.add(startButton);
//        frame.add(quitButton);

        Box box = Box.createVerticalBox();
        box.setAlignmentY(CENTER_ALIGNMENT);
        box.setAlignmentX(LEFT_ALIGNMENT);
        frame.add(box, BorderLayout.CENTER);

        box.add(Box.createVerticalGlue());
        box.add(word);
        box.add(hintLabel);
//        box.add(startButton);
//        box.add(quitButton);
        box.add(Box.createVerticalGlue());

        
        frame.add(result, BorderLayout.NORTH);
        frame.add(guess, BorderLayout.SOUTH);
        box.add(Box.createHorizontalGlue());        
        
       
        quitButton.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		System.out.printf("quit\n");
        		out.println("!quit");
        	}
        });
//        if(!start)
//        {
//        	 startButton.addActionListener(new ActionListener()
//             {
//     			public void actionPerformed(ActionEvent e)
//     			{
//     				System.out.printf("start\n");
//     				start = true;
//     			}
//             });
//			System.out.printf("2\n");
//
//        }
//        else
//        {
            guess.setText("Guess the word!!");

             
             guess.addActionListener(new ActionListener()
             {
     		   public void actionPerformed(ActionEvent e)
     		   {
     			   
     	    		String playerGuess = guess.getText();
     	    		if(playerGuess != null)
     	    			if(playerGuess.equals("!hint"))
     		        		hint = 1;
             			else
             				hint = 0;
     	    			//if(word.equals("!start"))
     	    				// start = 1;
     		        	out.println(playerGuess);
     		        	guess.setText("");
     			        	
     		    	if(hint == 1)
     		    	{
     		    		String hintResult;
     		    		try {
//     		    			while ((hintResult = in.readLine()) != null) 
     		    				hintResult = in.readLine();
     		    				String hint_Result = "Hint: " + hintResult;
     		    				hintLabel.setText(hint_Result);
     		    		}
     		    		catch (IOException e1) 
     		    		{
     		    			e1.printStackTrace();
     		    		}
     		    		hint = 0;
     		    	}
     		    	else
     		    	{
     			    	try 
     					{
     						String isWinner;

     						if((isWinner = in.readLine()) != null) 
     							if(isWinner.equals("Correct"))
     							{
     								
     								result.setText("Correct answer!");
     								String ans = "The answer is: " + in.readLine();
     								word.setText(ans);
     								winner++;
     							}
     							else
     							{
     								result.setText("Wrong answer! try again..");
     							}
     					}
     					catch (IOException e1) 
     					{
     						e1.printStackTrace();
     					}
     		    	}
     		   }
             });
             
             if(winner == 2)
             {
             	result.setText("CONGRATULATIONS! YOU ARE A WINNER!");
             }
             
//        }
        
        frame.setSize(300, 250);
        frame.setVisible( true );

    }

    
  
    @Override
    public void run() 
    {
    	
//    	if(check == 1)
//    	{
//    		String word = guess.getText();
//    		if(word != null)
//    			if(word.equals("!hint"))
//	        		hint = 1;
////    			if(word.equals("!start"))
////    				start = 1;
//	        	out.println(word);
//
////	        	guess.setText(" ");
//	        	check = 0;	        	
//    	} 
//    	if(hint == 1)
//    	{
//    		String hintResult;
//    		try {
////    			while ((hintResult = in.readLine()) != null) 
//    				hintResult = in.readLine();
//    				String hint_Result = "Hint: " + hintResult;
//    				hintLabel.setText(hint_Result);
//    		}
//    		catch (IOException e1) 
//    		{
//    			e1.printStackTrace();
//    		}
//    		hint = 0;
//    	}
	
//		try 
//		{
//			String isWinner;
//			if((isWinner = in.readLine()) != null) 
//				if(isWinner.equals("Correct"))
//				{
////					result.setText("Correct");
//				}
//				else
//				{
//					result.setText("Wrong");
//				}
//		}
//		catch (IOException e1) 
//		{
//			e1.printStackTrace();
//		}
	
    }

//	@Override
//	public void actionPerformed(ActionEvent e) 
//	{
//		check = 1;
//		run();
//	}
}

