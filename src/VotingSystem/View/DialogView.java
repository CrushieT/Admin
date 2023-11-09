package VotingSystem.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import jnafilechooser.api.JnaFileChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.ComboBoxModel;

public class DialogView extends JDialog implements ActionListener {

	private JPanel mainPanel = new JPanel();
	
	private String jdbcUrl = "jdbc:mysql://localhost:3306/votingsystem";
    private String username = "root";
    private String password = "wala";
    
   
	
	JButton okButton;
	JButton cancelButton;
	JButton addImageButton;
	JLabel selectedFileLabel;
	
	private static JComboBox<String> optionComboBox;
	private static JComboBox<String> mainComboBox;
	
	private static JTextField idNumField;
	private static JTextField firstNameField;
	private static JTextField lastNameField;
	private static JTextField ageField;
	private static JTextField posField;
	private static JTextField programField;
	
	private static JnaFileChooser jnaCh;
	
	String filePath;
	private String positionValue;
	private CandidatesView candidatesView;

	public DialogView(CandidatesView candidatesView) {
		this.candidatesView = candidatesView;
	    	
		this.setBounds(700, 200, 800, 600);
		this.getContentPane().setLayout(null);
		this.setTitle("Add Candidate");
		this.setResizable(false);
		this.setModal(true);
		
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 784, 561);
		
		JLabel addCandidateLabel = new JLabel("Add New Candidate");
		addCandidateLabel.setFont(new Font("Malgun Gothic Semilight", Font.BOLD, 29));
		addCandidateLabel.setBounds(30, 11, 287, 34);
		mainPanel.add(addCandidateLabel);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setPreferredSize(new Dimension(710, 1000));
		//contentPanel.setBackground(Color.pink);

		
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setBounds(30, 50, 730, 460);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel idNumLabel = new JLabel("ID Number: ");
		idNumLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		idNumLabel.setBounds(125, 11, 117, 34);
		contentPanel.add(idNumLabel);
		
		idNumField = createIntegerTextField(6);
		idNumField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		idNumField.setBounds(252, 12, 232, 37);
		contentPanel.add(idNumField);
		idNumField.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		firstNameLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		firstNameLabel.setBounds(125, 56, 117, 34);
		contentPanel.add(firstNameLabel);
		
		firstNameField = limitTextField(50);
		firstNameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		firstNameField.setColumns(10);
		firstNameField.setBounds(252, 57, 232, 37);
		contentPanel.add(firstNameField);
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		lastNameLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		lastNameLabel.setBounds(125, 105, 117, 34);
		contentPanel.add(lastNameLabel);
		
		lastNameField = limitTextField(50);
		lastNameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lastNameField.setColumns(10);
		lastNameField.setBounds(252, 106, 232, 37);
		contentPanel.add(lastNameField);
		
		JLabel ageLabel = new JLabel("Age: ");
		ageLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		ageLabel.setBounds(125, 150, 117, 34);
		contentPanel.add(ageLabel);
		
		ageField = createIntegerTextField(2);
		ageField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ageField.setColumns(10);
		ageField.setBounds(252, 151, 232, 37);
		contentPanel.add(ageField);
		
		JLabel posLabel = new JLabel("Position: ");
		posLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		posLabel.setBounds(125, 199, 117, 34);
		contentPanel.add(posLabel);
		
		//COMBOBOX 
		JComboBox<String> mainComboBox = createMainComboBox();
		
		JComboBox<String> optionComboBox = createOptionComboBox(mainComboBox);
		contentPanel.add(optionComboBox);
		contentPanel.add(mainComboBox);

    	positionValue = (String) mainComboBox.getSelectedItem();
	    
		/*
		posField = limitTextField(50);
		posField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		posField.setColumns(10);
		posField.setBounds(252, 200, 232, 37);
		contentPanel.add(posField);
		*/
		JLabel programLabel = new JLabel("Program: ");
		programLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		programLabel.setBounds(125, 248, 117, 34);
		contentPanel.add(programLabel);
		
		programField = limitTextField(50);
		programField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		programField.setColumns(10);
		programField.setBounds(252, 249, 232, 37);
		contentPanel.add(programField);
		
		JLabel imageLabel = new JLabel("Photo: ");
		imageLabel.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
		imageLabel.setBounds(125, 293, 117, 34);
		contentPanel.add(imageLabel);
		
		addImageButton = new JButton("Choose File");
		addImageButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		addImageButton.setBounds(251, 301, 105, 23);
		addImageButton.addActionListener(this);
		addImageButton.setFocusable(false);
		contentPanel.add(addImageButton);
		
		selectedFileLabel = new JLabel("Selected File: (No file selected)");
		selectedFileLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		selectedFileLabel.setBounds(377, 302, 324, 23);
		contentPanel.add(selectedFileLabel);
		
		
		
		
		mainPanel.add(scrollPane);
		this.getContentPane().add(mainPanel);
		
		
		//BUTTON NA TO
		
		okButton = new JButton("Add");
		okButton.setBounds(551, 520, 98, 34);
		okButton.setFocusable(false);
		okButton.addActionListener(this);
		mainPanel.add(okButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds(659, 520, 98, 34);
		cancelButton.setFocusable(false);
		cancelButton.addActionListener(this);
		
		mainPanel.add(cancelButton);
		
		this.setVisible(true);
		
		
		
	}
	//Add lang dito para sa mga position
	public JComboBox<String> createMainComboBox() {
	    String[] mainPositions = { "Select", "President", "Vice President", "Secretary", "Treasurer", "Member" };
	    mainComboBox = new JComboBox<>(new DefaultComboBoxModel<>(mainPositions));
	    mainComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	    mainComboBox.setBounds(252, 200, 186, 37);
	    
	    //positionValue = (String) mainComboBox.getSelectedItem();
	    return mainComboBox;
	}

	
	//Add other option here if there are more option per Positions
	private JComboBox<String> createOptionComboBox(JComboBox<String> mainComboBox) {
	    DefaultComboBoxModel<String> optionModel = new DefaultComboBoxModel<>();
	    optionComboBox = new JComboBox<>(optionModel);
	    optionComboBox.setVisible(false);
	    optionComboBox.setBounds(471, 199, 186, 37);

	    mainComboBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String selectedOption = (String) mainComboBox.getSelectedItem();
	            if (selectedOption.equals("Secretary")) {
	            	optionComboBox.setVisible(true);
	            	optionModel.removeAllElements();
	            	optionModel.addElement("Secretary");
	            	optionModel.addElement("Assistant Secretary");
	            	//positionValue = (String) optionComboBox.getSelectedItem();
	            } else {
	            	optionComboBox.setVisible(false);
	            }
	        }
	    });

	    return optionComboBox;
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		
		
		if (e.getSource() == okButton) {
			//insertCandidate(ALLBITS, jdbcUrl, username, ABORT, password, jdbcUrl, null);
			try {
				addCandidate();
				candidatesView.populateTableWithData();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		if (e.getSource() == cancelButton) {
			this.dispose();
		}
		if (e.getSource() == addImageButton) {
			jnaCh = new JnaFileChooser();
	        boolean save = jnaCh.showOpenDialog(this);
	        if (save) {
	        	 File selectedFile = jnaCh.getSelectedFile();
                 if (selectedFile != null) {
                	filePath = selectedFile.getAbsolutePath();
        	 		selectedFileLabel.setText("Selected File: " + filePath);
                 } else {
                     selectedFileLabel.setText("Selected File: (No file selected)");
                 }
	        }
		}
	}
	
	public static JTextField limitTextField(int columns) {
        JTextField limitField = new JTextField(columns);

        limitField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume(); // Consume the event for digit characters
                }
              
		        boolean nameLimitReached = false;
		        if (firstNameField.getText().length() == 25) {
		        	nameLimitReached = true; // Set the flag when 6 characters are reached
		            e.consume();
		        }
		        
		        boolean lastLimitReached = false;
		        if (lastNameField.getText().length() == 25) {
		        	lastLimitReached = true; // Set the flag when 6 characters are reached
		            e.consume();
		        }
		        
		        
		        
		        
		        
		        boolean programLimitReached = false;
		        if (programField.getText().length() == 25) {
		        	programLimitReached = true; // Set the flag when 6 characters are reached
		            e.consume();
		        }
		        
		    }
		
		    public void keyPressed(KeyEvent e) {
		        // Not used
		    }
		
		    public void keyReleased(KeyEvent e) {
		        // Not used
		    }
        });

        return limitField;
    }
	
	public static JTextField createIntegerTextField(int columns) {
        JTextField integerField = new JTextField(columns);

        integerField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Consume the event, preventing non-numeric characters
                }

                if (e.getSource() == ageField && ageField.getText().length() == 2) {
                    e.consume(); // Consume the event for ageField once it reaches 2 characters
                }

                if (e.getSource() == idNumField && idNumField.getText().length() == 6) {
                    e.consume(); // Consume the event for idNumField once it reaches 6 characters
                }
            }


            @Override
            public void keyPressed(KeyEvent e) {
                // Not used
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Not used
            }
        });

        return integerField;
    }
	
	
	
	public void addCandidate() throws FileNotFoundException {
		int idNum = 0, age = 0; 
		String firstName = null, lastName = null, position = null, program = null;
		FileInputStream fis;
		boolean formIncomplete = false;
		if (optionComboBox.isVisible()) {
            positionValue = (String) optionComboBox.getSelectedItem();
			} else {
        	positionValue = (String) mainComboBox.getSelectedItem();
		}
		if (idNumField.getText().isEmpty() || firstNameField.getText().isEmpty() || 
			    lastNameField.getText().isEmpty() || ageField.getText().isEmpty() || 
			    positionValue.equals("Select") || programField.getText().isEmpty() || filePath.isEmpty()) {
				
			    // Handle the case when any of the text fields is empty
				JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Incomplete Form", JOptionPane.WARNING_MESSAGE);
				formIncomplete = true;
		} else {
		    try {
		    	System.out.println("pasok");
		        idNum = Integer.parseInt(idNumField.getText());
		        firstName = firstNameField.getText();
		        lastName = lastNameField.getText();
		        age = Integer.parseInt(ageField.getText());
		        program = programField.getText();
		        fis = new FileInputStream(filePath);
		        
		        
		        

		        
		        //Upload to database
		        insertCandidate(idNum, firstName, lastName , age, positionValue, program, fis);
		        
		        JOptionPane.showMessageDialog(this, "Profile uploaded", "Success", JOptionPane.INFORMATION_MESSAGE);
		    } catch (NumberFormatException ex) {
		        // Handle the case when there is a parsing error (e.g., non-numeric input)
		        System.out.println("Invalid input for ID or Age.");
		    }
		}
		
		if (!formIncomplete) {
			CandidatesView candidatesView = new CandidatesView();
			candidatesView.populateTableWithData();
	        dispose();
	    }
		
		
	}
	public void insertCandidate(int idNumber, String candidateName, String candidateSurname, int candidateAge, 
            String candidatePosition, String candidateProgram, FileInputStream candidateImage) {
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            String query = "INSERT INTO candidatelist (idNumber, candidateName, candidateSurname, candidateAge, candidatePosition, candidateProgram, candidateImage) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, idNumber);
            statement.setString(2, candidateName);
            statement.setString(3, candidateSurname);
            statement.setInt(4, candidateAge);
            statement.setString(5, candidatePosition);
            statement.setString(6, candidateProgram);
            statement.setBinaryStream(7, candidateImage);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}