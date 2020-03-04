package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PortReservationModel {

	public static void main(String[] args) {
		HashMap<Integer, Student> map = new HashMap<Integer, Student>();
		for (int i = 61000; i <= 61999; ++i) {
			map.put(i, null);
		}

		// Write ports to database
		try {
			PrintWriter writer = new PrintWriter("available_port.txt", "utf-8");
			writer.println("Available port\n");
			List<Integer> ports = new ArrayList<Integer>(map.keySet());
			Collections.sort(ports);
			for (int i = 0; i < ports.size(); ++i) {
				writer.println(ports.get(i));
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner input = new Scanner(System.in);
		System.out.println("Port Reservation Networking Program");
		System.out.print("\nName: ");
		String name = input.nextLine();
		System.out.print("Student id: ");
		String studentID = input.nextLine();
		int portReserve;
		boolean portReserved = true;
		do {
			System.out.print("Port: ");
			portReserve = Integer.parseInt(input.nextLine());

			if (map.containsKey(portReserve) && map.get(portReserve) == null) {
				portReserved = false;
			} else {
				System.out.println(
						"The port you choose has been reserved or not in valid range, please enter another port");
			}
		} while (portReserved);
		// conduct validation and reprompt
		System.out.print("Email:");
		String email = input.nextLine();
		Student student = new Student(studentID, name, portReserve, email);

		EmailSocketModel.run(student);

		// writing to file
		try {
			PrintWriter writer = new PrintWriter("students.txt");
			writer.println("StudentID\t\tName\t\tPort\t\tEmail");
			writer.println(student.toString());
			writer.close();

		} catch (IOException e) {

		}

	}
}
