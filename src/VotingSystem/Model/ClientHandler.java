package VotingSystem.Model;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
    	System.out.println("Client Handler");
        // Send candidatePosition to the client
		sendPosition(clientSocket);
		sendCandidateData(clientSocket);
		

		// Read the confirmation from the client
		
		
        try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
    }

    private static void testDisplayImage(Candidate candidate) {
        // Create a JFrame
        JFrame frame = new JFrame("Test Display Image");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            // Get the Base64-encoded image from the candidate
            String imageBase64 = candidate.getImageBase64();

            // Decode Base64 string to byte array
            byte[] imageBytes = Base64.getDecoder().decode(imageBase64);

            // Convert byte array to ImageIcon
            ImageIcon imageIcon = new ImageIcon(imageBytes);

            // Create JLabel to display the image
            JLabel imageLabel = new JLabel(imageIcon);
            frame.add(imageLabel);

            // Make the frame visible
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // SEND ALL THE CANDIDATE NAME, SURNAME, POSITION AND IMAGE
    public void sendCandidateData(Socket clienSocket) {
    	 
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			List<Candidate> candidatesList = getCandidateData();
	    	Candidate candidate;
	    	for(int i = 0; i < candidatesList.size(); i++ ) {
	    		candidate = candidatesList.get(i);
	    		out.println(candidate.toDataString());
	    		//testDisplayImage(candidate);
	    		
	    	}
	    	
	    	
	    	
	    	
	    	//clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    private static List<Candidate> getCandidateData() {
        List<Candidate> candidates = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/votingsystem";
        String username = "root";
        String password = "wala";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            // SQL query to retrieve the required data
            String sqlQuery = "SELECT candidateName, candidateSurname, candidatePosition, candidateImage FROM candidatelist";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String name = resultSet.getString("candidateName");
                String surname = resultSet.getString("candidateSurname");
                String position = resultSet.getString("candidatePosition");
                byte[] imageBytes = resultSet.getBytes("candidateImage");

                // Convert the imageBytes to Base64
                String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
                
                // Create a Candidate object and add it to the list
                Candidate candidate = new Candidate(name, surname, position, imageBase64);
                candidates.add(candidate);

                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return candidates;
    }

    
    
 // SEND THE LIST OF ALL THE POSITION TO VOTER CLIENT
    public void sendPosition(Socket clientSocket) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            

            List<String> differentPositions = getDifferentCandidatePositions();
            for (String position : differentPositions) {
                out.println(position);
            }

            out.println("EndOfPositionData"); 
            
            //clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getDifferentCandidatePositions() {
        List<String> distinctPositions = new ArrayList<>();
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/votingsystem";
        String username = "root";
        String password = "wala";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            // SQL query to retrieve distinct candidate positions
            String sqlQuery = "SELECT DISTINCT candidatePosition FROM candidatelist";

            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String position = resultSet.getString("candidatePosition");
                distinctPositions.add(position);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return distinctPositions;
    }

}
