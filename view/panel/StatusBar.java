package view.panel;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StatusBar extends JPanel {
	private JLabel conectSMTP = new JLabel("Connect to SMTP Server: Yes");

	public StatusBar() {
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new GridLayout(1, 0));

		add(conectSMTP);
	}

	public void updateNumOfPlayer(int numOfPlayer) {
		conectSMTP.setText("Connect to SMTP Server: " + numOfPlayer);
	}
}
