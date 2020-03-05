package model;

import java.util.Scanner;

public class Menu {

	private PortReservationModel portRun;
	private Scanner input;

	public Menu() {
		input = new Scanner(System.in);
		portRun = new PortReservationModel();
	}

	public void runWelcomeMenu() {
		boolean firstRun=true;
		int selection;
		do {
			System.out.println("\t\t\tWelcome to Port Reservation Networking Program");
			System.out.println("1.Register New Port");
			System.out.println("2.Log out");
			System.out.print("Please choose the options prompted above(1-2): ");
			selection = input.nextInt();
			if (selection == 1) {
				portRun.run(firstRun);
				firstRun=false;
			} else {
				System.out.println("\nGoodbye!!!");
			}
		} while (selection != 2);
	}
}
