package model;

import java.util.Scanner;

public class Facade {
	private Persistence p = new Persistence();
	private PortReservationModel portRun;

	private Scanner input;

	public Facade() {
		input = new Scanner(System.in);
		portRun = new PortReservationModel();
	}

	public boolean preCheckFileAndSetUp() {
		boolean firstRun = true;
		p = new Persistence();
		if (p.getBackUpExist()) {
			System.out.println("\nYOUR DATA HAS BEEN RECOVERED!!!");
			firstRun = false;
			portRun.injectMap(p.getMap());
		}
		return firstRun;
	}

	public PortReservationModel getPortRun() {
		return portRun;
	}
}
