package view;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import view.panel.StatusBar;
import view.panel.WestPanel;
import view.panel.DatabasePanel;
import model.Menu;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private final int WIDTH = 850; 
	private final int HEIGHT = 405;
	private final Menu model = new Menu();

	private StatusBar statusBar;
	private WestPanel westPanel;
	private DatabasePanel databasePanel;
	
	private JPanel contentPane = new JPanel();

	public MainFrame() {
		
		
		statusBar = new StatusBar();
		westPanel = new WestPanel(this);
		databasePanel = new DatabasePanel(this);

		setTitle("Port Reservation");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		contentPane.setBorder(BorderFactory.createEtchedBorder());
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		contentPane.add(statusBar, BorderLayout.SOUTH);
		contentPane.add(westPanel, BorderLayout.WEST);
		contentPane.add(databasePanel, BorderLayout.CENTER);
	}
	
	public Menu getModel() {
		return model;
	}

	public JPanel getContentPane() {
		return contentPane;
	}
	
	public WestPanel getWestPanel() {
		return westPanel;
	}
	
	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	public DatabasePanel getDatabasePanel() {
		return databasePanel;
	}
}
