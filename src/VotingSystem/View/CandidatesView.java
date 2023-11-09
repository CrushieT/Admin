package VotingSystem.View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import VotingSystem.Model.ImageRenderer;

public class CandidatesView extends JPanel implements ActionListener {


	JButton addCandButton;
	DefaultTableModel model;
	private Timer refreshTimer;
	
	public CandidatesView() {
		
		this.setBounds(250, 50, 1014, 711);
		//this.setBackground(Color.pink);
		this.setLayout(null);
		
		
		
		JLabel candidatesLabel = new JLabel("Candidates List");
		candidatesLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 30));
		candidatesLabel.setBounds(26, 11, 226, 49);
		this.add(candidatesLabel);
		
		JPanel mainlistPanel = new JPanel();
		mainlistPanel.setBounds(26, 84, 965, 594);
		mainlistPanel.setLayout(null);
		this.add(mainlistPanel);
		
		addCandButton = new JButton("Add");
		addCandButton.setFont(new Font("Leelawadee UI", Font.BOLD, 13));
		addCandButton.setBounds(10, 11, 89, 37);
		addCandButton.addActionListener(this);
		addCandButton.setFocusable(false);
		mainlistPanel.add(addCandButton);
		
		/*
		// Ito lista to ng candidate
		refreshTimer = new Timer(10000, this);
        refreshTimer.setInitialDelay(0);
        refreshTimer.start();
		*/
		
		model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("FIRST NAME");
        model.addColumn("SURNAME");
        model.addColumn("AGE");
        model.addColumn("POSITION");
        model.addColumn("PROGRAM");
        model.addColumn("IMAGE");

        JTable table = new JTable(model);
        table.setGridColor(table.getBackground());
        
        int rowHeight = 100; // Set the desired row height
        table.setRowHeight(rowHeight);

        // Adjust column width for a specific column (e.g., column 1)
        int columnWidth = 100; // Set the desired column width
        table.getColumnModel().getColumn(1).setPreferredWidth(columnWidth);
        
        populateTableWithData();
        table.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer());
        ImageRenderer.setCustomImageRenderer(table, 6);
        /*
        addCandButton.addActionListener(new ActionListener() {

			
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
                // Simulate adding a student with random data
                int id = model.getRowCount() + 1;
                String name = "Student " + id;
                int age = (int) (Math.random() * 10) + 18;

                model.addRow(new Object[]{id, name, age});
            }
            
        });
        */
		//listPanel.add(table);
		
		JScrollPane listScrollPane = new JScrollPane(table);
		
		listScrollPane.setBounds(10, 155, 945, 428);
		listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		mainlistPanel.add(listScrollPane);
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == addCandButton) {
			 DialogView dialog = new DialogView(CandidatesView.this);
		     dialog.setModal(true);
		     
		}
		/*
		 if (e.getSource() == refreshTimer) {
	            // Perform data retrieval and table population here
	            populateTableWithData();
	        }
	        */
	}
	
	
	public void populateTableWithData() {
	    // Define your database connection details
	    String jdbcUrl = "jdbc:mysql://localhost:3306/votingsystem";
	    String username = "root";
	    String password = "wala";

	    // Clear the existing data in the table
	    model.setRowCount(0);

	    try {
	        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
	        String sql = "SELECT idNumber, candidateName, candidateSurname, candidateAge, candidatePosition, candidateProgram, candidateImage FROM candidatelist";

	        PreparedStatement statement = connection.prepareStatement(sql);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            // Retrieve data from the result set
	            int idNumber = resultSet.getInt("idNumber");
	            String candidateName = resultSet.getString("candidateName");
	            String candidateSurname = resultSet.getString("candidateSurname");
	            int candidateAge = resultSet.getInt("candidateAge");
	            String candidatePosition = resultSet.getString("candidatePosition");
	            String candidateProgram = resultSet.getString("candidateProgram");
	            java.sql.Blob blob = resultSet.getBlob("candidateImage");

	            // Convert the BLOB to an ImageIcon for display in the table
	            ImageIcon imageIcon = null;
	            if (blob != null) {
	                byte[] bytes = blob.getBytes(1, (int) blob.length());
	                imageIcon = new ImageIcon(bytes);
	            }

	            // Add the data to the DefaultTableModel
	            model.addRow(new Object[]{idNumber, candidateName, candidateSurname, candidateAge, candidatePosition, candidateProgram, imageIcon});
	        }

	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
