package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Facade;
import view.MainFrame;
import view.panel.DatabasePanel;
import view.panel.WestPanel;

public class ReserveListener implements ActionListener {

	private Facade model;
	private WestPanel wp;
	private MainFrame mf;
	private DatabasePanel dp;
	private boolean firstRun;

	public ReserveListener(MainFrame mf) {
		model = mf.getModel();
		this.mf = mf;
		firstRun = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		wp = mf.getWestPanel();
		dp = mf.getDatabasePanel();

		try {
			String name = wp.getNameTextField().getText();
			String id = wp.getIdTextField().getText();
			String email = wp.getEmailTextField().getText();
			String port = wp.getPortTextField().getText();

			if (name.isEmpty() || id.isEmpty() || email.isEmpty() || port.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill in value.", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (!model.getPortRun().getFirstRunDatabase()) {
					firstRun = false;
				}

				if (!model.getPortRun().portAlreadySelected(firstRun, port, email, id, name)) {
					if (model.getPortRun().validateMaxPortSelected()) {
						if (model.getPortRun().validateConsistentInfo()) {
							dp.updateDatabase(model);
							firstRun = false;
							JOptionPane.showMessageDialog(null,
									"You have successfully registered your port please check your email.");
						} else {
							JOptionPane.showMessageDialog(null, "Please check your the consistency of your info");

						}
					} else {
						JOptionPane.showMessageDialog(null, "You have already registered your port");
					}
				} else {
					JOptionPane.showMessageDialog(null, "You chosen port is not in valid range or has been reserved",
							"Invalid Input", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Please check your input.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		}
	}
}
