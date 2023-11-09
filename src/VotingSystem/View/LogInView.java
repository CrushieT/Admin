package VotingSystem.View;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.callback.UsernameCallback;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class LogInView extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField adminField;
	private JTextField adminPwField;
	
	JButton logInButton;

	private String username = "admin";
	private String password = "mabini2023";

	/**
	 * Create the frame.
	 */
	public LogInView() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(700, 300, 600, 400);
		this.contentPane = new JPanel();
		this.setTitle("ADMIN");
		this.setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 600, 400);

		JLabel adminPic = new JLabel();
		adminPic.setBounds(200, 11, 150, 150);
		panel.add(adminPic);
		ImageIcon adminIcon= new ImageIcon("src/Logo/admin.jpg");
		Image adminPicImage = adminIcon.getImage();
        Image adminPicImgScale = adminPicImage.getScaledInstance(adminPic.getWidth(), adminPic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon adminPicScaledIcon = new ImageIcon(adminPicImgScale);
        adminPic.setIcon(adminPicScaledIcon);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel adminLabel = new JLabel("Admin");
		adminLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 15));
		adminLabel.setBounds(99, 147, 79, 22);
		panel.add(adminLabel);
		
		JLabel adminPwLabel = new JLabel("Password");
		adminPwLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 15));
		adminPwLabel.setBounds(99, 209, 79, 22);
		panel.add(adminPwLabel);
		
		adminField = new JTextField();
		adminField.setBounds(99, 173, 350, 30);
		panel.add(adminField);
		adminField.setColumns(10);
		
		adminPwField = new JPasswordField();
		adminPwField.setColumns(10);
		adminPwField.setBounds(99, 235, 350, 30);
		
		adminPwField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.equals(adminField.getText()) && password.equals(adminPwField.getText())) {
                    new adminView();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Check your username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                    adminField.setText("");
                    adminPwField.setText("");
                }
            }
        });
		panel.add(adminPwField);
		
		
		logInButton = new JButton("Log In");
		logInButton.setFont(new Font("Leelawadee UI", Font.BOLD, 15));
		logInButton.setBounds(210, 295, 122, 30);
		logInButton.addActionListener(this);
		panel.add(logInButton);
		
		this.setVisible(true);
	}
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == logInButton) {
			if (username.equals(adminField.getText()) && password.equals(adminPwField.getText())) {
				new adminView();
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Check your username or password.", "Login Error", JOptionPane.ERROR_MESSAGE);
				adminField.setText("");
				adminPwField.setText("");
			}
		}
		
		
		
	}

	
	
}
