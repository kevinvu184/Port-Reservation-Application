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
		boolean validSelection = false;
		do {
			System.out.println("\t\t\tWelcome to Port Reservation Networking Program");
			System.out.println("1.Login as Network Admin");
			System.out.println("2.Login as Student");
			System.out.print("Please choose the options prompted above(1-2): ");
			try {
				int selection = Integer.parseInt(input.nextLine());
				if (selection == 1) {
					validSelection = true;
					runNetworkAdminWelcomeMenu();
				} else if (selection == 2) {
					validSelection = true;
					runStudentWelcomeMenu();
				} else {
					printErrorMsg();
				}
			} catch (Exception e) {
				printErrorMsg();
			}

		} while (!validSelection);
	}

	private void printErrorMsg() {
		System.out.println("\n\nInvalid selection please choose options prompted above\n");
	}

	private void runNetworkAdminWelcomeMenu() {
		boolean firstRun = preCheckFileAndSetUp();
		boolean endProgram = false;
		int selection;
		do {
			System.out.println("\n\t\t\tWelcome to Port Reservation Networking Program for Network Admin");
			System.out.println("1.See Databases");
			System.out.println("2.Assign port to students");
			System.out.println("3.Log out");
			System.out.print("Please choose the options prompted above(1-3): ");
			try {
				selection = Integer.parseInt(input.nextLine());
				if (selection == 1) {
					System.out.println("\n\nStudentID\t\tStudentName\t\tPort\t\tEmail");
					System.out.println(portRun.getDatabase());
					System.out.println("Press any key and/or hit enter to continue: ");
					input.nextLine();
				} else if (selection == 2) {
					portRun.run(firstRun);
				} else if (selection == 3) {
					endProgram = true;
					System.out.println("\nGoodbye !!!");
				}
			} catch (Exception e) {
				printErrorMsg();
			}
		} while (!endProgram);
	}

	private void runStudentWelcomeMenu() {
		boolean endProgram = false;
		boolean firstRun = preCheckFileAndSetUp();
		int selection;
		do {

			System.out.println("\n\t\t\tWelcome to Port Reservation Networking Program for Student");
			System.out.println("1.Register New Port");
			System.out.println("2.Log out");
			System.out.print("Please choose the options prompted above(1-2): ");
			try {
				selection = Integer.parseInt(input.nextLine());
				if (selection == 1) {
					portRun.run(firstRun);
					firstRun = false;
				} else if (selection == 2) {
					endProgram = true;
					System.out.println("\nGoodbye!!!");
				}
			} catch (Exception e) {
				printErrorMsg();
			}
		} while (!endProgram);
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
