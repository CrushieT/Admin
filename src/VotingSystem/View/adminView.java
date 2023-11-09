package VotingSystem.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

public class adminView extends JFrame implements ActionListener {


	JButton dashboardButton = new JButton();
	JButton votesButton = new JButton();
	JButton votersButton = new JButton();
	JButton positionsButton = new JButton();
	JButton candidatesButton = new JButton();
	
	CardLayout cardLayout;
	
	
	JPanel cardLayoutPanel;
	private JTable table;
	
	
	public adminView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(400, 150, 1280, 800);
		this.setTitle("Admin");
		this.setLayout(null);
		this.setResizable(false);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBounds(0, 50, 250, 710);
		sidePanel.setBackground(new Color(85, 85, 85));
		sidePanel.setLayout(null);
		getContentPane().add(sidePanel);
		
		JLabel adminLabel = new JLabel("ADMIN");
		adminLabel.setForeground(new Color(255, 255, 255));
		adminLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		adminLabel.setBounds(78, 23, 129, 49);
		sidePanel.add(adminLabel);
		
		JPanel reportPanel= sideLabelPanel(0, 84, 250, 44, sidePanel);
		
		JLabel reportsLabel = sideLabel("REPORTS", 10, 11, 133, 22, reportPanel);
		
		JPanel reportListPanel = new JPanel();
		reportListPanel.setBackground(new Color(75, 75, 75));
		reportListPanel.setBounds(0, 126, 250, 112);
		reportListPanel.setLayout(null);
		sidePanel.add(reportListPanel);
		
		dashboardButton = sidePanelButton("Dashboard",0, 0, 250, 55, reportListPanel);
		dashboardButton.addActionListener(this);
		votesButton = sidePanelButton("Votes", 0, 55, 250, 55, reportListPanel);
		
		JPanel managePanel= sideLabelPanel(0, 236, 250, 44, sidePanel);
		
		JLabel manageLabel = sideLabel("MANAGE", 10, 11, 133, 22, managePanel);
		
		
		JPanel manageListPanel = new JPanel();
		manageListPanel.setBackground(new Color(75, 75, 75));
		manageListPanel.setBounds(0, 280, 250, 166);
		manageListPanel.setLayout(null);
		sidePanel.add(manageListPanel);
		
		votersButton = sidePanelButton("Voters", 0, 0, 250, 55, manageListPanel);
		positionsButton = sidePanelButton("Position", 0, 55, 250, 55, manageListPanel);
		candidatesButton = sidePanelButton("Candidates", 0, 110, 250, 55, manageListPanel);
		candidatesButton.addActionListener(this);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 1264, 51);
		topPanel.setBackground(new Color( 51, 102, 153));
		topPanel.setLayout(null);
		getContentPane().add(topPanel);
		

		//CARDLAYOUT PARA MA PALIT PALIT YUN PANEL
		cardLayoutPanel= new JPanel();
		cardLayoutPanel.setBounds(250, 50, 1014, 711);
		cardLayoutPanel.setLayout(new CardLayout(0, 0));
		getContentPane().add(cardLayoutPanel);

        cardLayout = new CardLayout();
        cardLayoutPanel.setLayout(cardLayout);
		
        //DASHBOARD PANEL
		JPanel dashboardPanel = new JPanel();
		dashboardPanel.setBounds(250, 50, 1014, 711);
		dashboardPanel.setBackground(Color.white);
		dashboardPanel.setLayout(null);
		
		JLabel dashboardLabel = new JLabel("Dashboard");
		dashboardLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 30));
		dashboardLabel.setBounds(26, 11, 189, 49);
		dashboardPanel.add(dashboardLabel);
		
		JPanel noPosPanel = new JPanel();
		noPosPanel.setBackground(new Color(0, 64, 128, 200));
		noPosPanel.setBounds(26, 71, 200, 122);
		dashboardPanel.add(noPosPanel);
		
		JPanel noCandPanel = new JPanel();
		noCandPanel.setBackground(new Color(0, 128, 0, 200));
		noCandPanel.setBounds(277, 71, 200, 122);
		dashboardPanel.add(noCandPanel);
		
		JPanel totalVotersPanel = new JPanel();
		totalVotersPanel.setBackground(new Color(255, 128, 64, 200));
		totalVotersPanel.setBounds(528, 71, 200, 122);
		dashboardPanel.add(totalVotersPanel);
		
		
		JPanel votersVotedPanel = new JPanel();
		votersVotedPanel.setBackground(new Color(168, 4, 0, 200));
		votersVotedPanel.setBounds(780, 71, 200, 122);
		dashboardPanel.add(votersVotedPanel);
		
		JLabel votesTallyLabel = new JLabel("Votes Tally");
		votesTallyLabel.setFont(new Font("Leelawadee UI", Font.BOLD, 30));
		votesTallyLabel.setBounds(26, 233, 189, 49);
		dashboardPanel.add(votesTallyLabel);

		JPanel votesTallyPanel = new JPanel();
		votesTallyPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		votesTallyPanel.setLayout(new GridLayout(5, 2, 20, 20));
		
		JScrollPane votesTallyScrollPane = new JScrollPane(votesTallyPanel);
		votesTallyScrollPane.setBounds(26, 293, 964, 391);
		
		//Dito yun logic para makuha yun realtime votes tally
		
		
		
		//Candidates List to
		JPanel candidatesPanel = new CandidatesView();
		
		
		
		

		//cardLayoutPanel.add(dashboardPanel, "dashboard");
		cardLayoutPanel.add(candidatesPanel, "candidates");
		
		cardLayout.show(cardLayoutPanel, "dashboard");

		dashboardPanel.add(votesTallyScrollPane);
		this.setVisible(true);
	}
	
	//BUTTON ITO NA PAMPINDOT
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == dashboardButton) {

			cardLayout.show(cardLayoutPanel, "dashboard");
		}
		if(e.getSource() == candidatesButton) {
			cardLayout.show(cardLayoutPanel, "candidates");
		}
		
	}
	
	
	
	
	//dashboard, votes etc button
	public JButton sidePanelButton(String text, int x, int y, int width, int height, JPanel panel) {
		JButton button = new JButton(text);
        button.setForeground(new Color(255, 255, 255));
        button.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
        button.setBackground(new Color(75, 75, 75));
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
        button.setLayout(null);
        panel.add(button);
        return button;
	}
	
	//reports, manage label
	public JLabel sideLabel(String text, int x, int y, int width, int height, JPanel panel) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
		label.setForeground(new Color(128, 128, 128));
		label.setBounds(x, y, width, height);
		panel.add(label);
		return label;
		
	}
	
	//lalagyanan na panel ng sideLabel
	public JPanel sideLabelPanel(int x, int y, int width, int height, JPanel panel) {
		JPanel labelPanel = new JPanel();
		labelPanel.setBackground(new Color(63, 63, 63));
		labelPanel.setBounds(x, y, width, height);
		labelPanel.setLayout(null);
		panel.add(labelPanel);
		return labelPanel;
	}
}
