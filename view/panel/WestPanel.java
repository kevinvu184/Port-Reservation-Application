package view.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.PortFocusListener;
import controller.ReserveListener;
import view.MainFrame;

@SuppressWarnings("serial")
public class WestPanel extends JPanel {
	private JTextField nameTextField = new JTextField(10);
	private JTextField idTextField = new JTextField(10);
	private JTextField emailTextField = new JTextField(10);
	private JTextField portTextField = new JTextField(10);

	private JButton reserveButton = new JButton("RESERVE");

	public WestPanel(MainFrame mf) {
		setLayout(new BorderLayout());

		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new SpringLayout());

		JLabel name = new JLabel("Name:", JLabel.TRAILING);
		bodyPanel.add(name);
		name.setLabelFor(nameTextField);
		bodyPanel.add(nameTextField);

		JLabel ID = new JLabel("ID:", JLabel.TRAILING);
		bodyPanel.add(ID);
		ID.setLabelFor(idTextField);
		bodyPanel.add(idTextField);

		JLabel email = new JLabel("Email:", JLabel.TRAILING);
		bodyPanel.add(email);
		email.setLabelFor(emailTextField);
		bodyPanel.add(emailTextField);

		JLabel port = new JLabel("Port:", JLabel.TRAILING);
		bodyPanel.add(port);
		port.setLabelFor(portTextField);
		bodyPanel.add(portTextField);

		SpringUtilities.makeCompactGrid(bodyPanel, 4, 2, 6, 6, 6, 6);

		JPanel buttonPanel = new JPanel();
		reserveButton.addActionListener(new ReserveListener(mf));
		buttonPanel.add(reserveButton);

		portTextField.addFocusListener(new PortFocusListener(mf));
		JPanel makeReservationPanel = new JPanel();
		makeReservationPanel.setLayout(new BorderLayout());
		makeReservationPanel.setBorder(new CompoundBorder(new EtchedBorder(), new TitledBorder("Make Reservation")));

		makeReservationPanel.add(bodyPanel, BorderLayout.NORTH);
		makeReservationPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Add 2 container to the WestPanel
		add(makeReservationPanel, BorderLayout.CENTER);
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public JTextField getIdTextField() {
		return idTextField;
	}

	public JTextField getEmailTextField() {
		return emailTextField;
	}

	public JTextField getPortTextField() {
		return portTextField;
	}
}
