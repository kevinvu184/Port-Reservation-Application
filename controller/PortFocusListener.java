package controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;

import model.Facade;
import view.MainFrame;

public class PortFocusListener implements FocusListener {

	private Facade model;
	private boolean initialFocus = true;

	public PortFocusListener(MainFrame mf) {
		model = mf.getModel();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		if (initialFocus) {
			JOptionPane.showMessageDialog(null, model.getPortRun().getAvailablePortMsg());
		}
		initialFocus = false;
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		initialFocus = true;
	}

}
