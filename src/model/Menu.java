package model;

import java.util.Scanner;

public class Menu {
	private Persistence p = new Persistence();
	private PortReservationModel portRun;
	private Scanner input;

	public Menu() {
		input = new Scanner(System.in);
		portRun = new PortReservationModel();
	}

	public void runWelcomeMenu() {
		boolean firstRun = true;
		Persistence p = new Persistence();
		if (p.getBackUpExist()) {
			System.out.println("YOUR DATA HAS BEEN RECOVERED!!!");
			firstRun = false;
			portRun.injectMap(p.getMap());
		}
		int selection;
		do {
			System.out.println("\t\t\tWelcome to Port Reservation Networking Program");
			System.out.println("1.Register New Port");
			System.out.println("2.Log out");
			System.out.print("Please choose the options prompted above(1-2): ");
			selection = input.nextInt();
			if (selection == 1) {
				portRun.run(firstRun);
				firstRun = false;
			} else if (selection == 2) {
				System.out.println("\nGoodbye!!!");
			} else {
				System.out.println("\t\tINVALID SELECTION PLEASE CHOOSE AGAIN\n");
			}
		} while (selection != 2);
	}
}
