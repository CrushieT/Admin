package VotingSystem.Controller;

import VotingSystem.Model.CreateBallotModel;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.ConnectionGroupManager;


public class CreateBallotController {
	
	

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    private String jdbcUrl = "jdbc:mysql://localhost:3306/votingsystem";
    private String username = "root";
    private String password = "wala";
	
	//Constructor to wala lang laman 
	public CreateBallotController() {
	}
	
	public void addCandidate(int idNumber,String candidateName,String candidateSurname,  int candidateAge,
			String candidatePosition, String candidateProgram, FileInputStream candidateImage) {
		CreateBallotModel createBallotModel = new CreateBallotModel(idNumber, candidateName, candidateSurname,   candidateAge,
				 													candidatePosition,  candidateProgram,  candidateImage);
		
		insertData(idNumber, candidateName, candidateSurname,   candidateAge, candidatePosition,  candidateProgram,  candidateImage);
	}
	
	//pang insert ng candidate data sa database
	public void insertData(int idNumber,String candidateName,String candidateSurname,  int candidateAge,
			String candidatePosition, String candidateProgram, FileInputStream candidateImage) {
		
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);) {
        	
        	String sql = "INSERT INTO candidatelist (idNumber, candidateName, candidateSurname, candidateAge, candidatePosition, candidateProgram, candidateImage) VALUES (?, ?, ?, ?, ?, ?, ?)";
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setInt(1, idNumber); // Set the idNumber
        	preparedStatement.setString(2, candidateName);
        	preparedStatement.setString(3, candidateSurname);
        	preparedStatement.setInt(4, candidateAge);
        	preparedStatement.setString(5, candidatePosition);
        	preparedStatement.setString(6, candidateProgram);
        	preparedStatement.setBinaryStream(7, candidateImage, candidateImage.available());


            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Data insertion failed.");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//basta server wait lang
	public void serverConnection() {
		try {
            serverSocket = new ServerSocket(9696, 0, InetAddress.getByName("0.0.0.0"));
            System.out.println("Device2 is running and waiting for connection...");
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Could not listen on port: 9494");
            System.exit(1);
        }
	}
	
}
