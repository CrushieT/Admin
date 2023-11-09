package VotingSystem;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import VotingSystem.Model.Server;
import VotingSystem.View.CandidatesView;
import VotingSystem.View.LogInView;
import VotingSystem.View.adminView;

public class Main {
	
	// ITO AY SA SERVER 
    public static void main(String[] args) {
        new Server();
    }

   
}
