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

import controller.PortListener;
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

		JLabel l1 = new JLabel("Name:", JLabel.TRAILING);
		bodyPanel.add(l1);
		l1.setLabelFor(nameTextField);
		bodyPanel.add(nameTextField);

		JLabel l2 = new JLabel("ID:", JLabel.TRAILING);
		bodyPanel.add(l2);
		l2.setLabelFor(idTextField);
		bodyPanel.add(idTextField);

		JLabel l3 = new JLabel("Email:", JLabel.TRAILING);
		bodyPanel.add(l3);
		l3.setLabelFor(emailTextField);
		bodyPanel.add(emailTextField);

		JLabel l4 = new JLabel("Port:", JLabel.TRAILING);
		bodyPanel.add(l4);
		l4.setLabelFor(portTextField);
		bodyPanel.add(portTextField);

		SpringUtilities.makeCompactGrid(bodyPanel, 4, 2, 6, 6, 6, 6);

		JPanel buttonPanel = new JPanel();
		reserveButton.addActionListener(new ReserveListener(mf));
		buttonPanel.add(reserveButton);

		portTextField.addMouseListener(new PortListener(mf));

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
