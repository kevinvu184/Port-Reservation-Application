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
		System.out.println("\n\t\t\tWelcome to Port Reservation Networking Program");
		System.out.println("1.Login as Network Admin");
		System.out.println("2.Login as Student");
		System.out.print("Please choose the options prompted above(1-2): ");
		int selection = input.nextInt();
		if (selection == 2) {
			runStudentWelcomeMenu();
		} else {
			runNetworkAdminWelcomeMenu();
		}
	}

	private void runNetworkAdminWelcomeMenu() {
		boolean firstRun = preCheckFileAndSetUp();
		int selection;
		do {
			System.out.println("\n\t\t\tWelcome to Port Reservation Networking Program for Student");
			System.out.println("1.See Databases");
			System.out.println("2.Assign port to students");
			System.out.println("3.Log out");
			System.out.print("Please choose the options prompted above(1-3): ");
			selection = input.nextInt();
			if (selection == 1) {
				System.out.println("\n\nStudentID\t\tStudentName\t\tPort\t\tEmail");
				System.out.println(portRun.getDatabase());
				System.out.println("Hit enter to continue: ");
				input.nextLine();
				input.nextLine();
			} else if (selection == 2) {
				portRun.run(firstRun);
			} else {
				System.out.println("\nGoodbye !!!");
			}
		} while (selection != 3);
	}

	private void runStudentWelcomeMenu() {
		boolean firstRun = preCheckFileAndSetUp();
		int selection;
		do {

			System.out.println("\t\t\tWelcome to Port Reservation Networking Program for Student");
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

	private boolean preCheckFileAndSetUp() {
		boolean firstRun = true;
		p = new Persistence();
		if (p.getBackUpExist()) {
			System.out.println("\nYOUR DATA HAS BEEN RECOVERED!!!");
			firstRun = false;
			portRun.injectMap(p.getMap());
		}
		return firstRun;
	}
}
