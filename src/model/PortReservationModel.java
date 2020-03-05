package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PortReservationModel {
	private HashMap<Integer, Student> map = new HashMap<Integer, Student>();
	public static final int START_PORT = 61000;
	public static final int END_PORT = 61999;

	public void run(boolean firstRun) {
		if (firstRun) {
			for (int i = START_PORT; i <= END_PORT; ++i) {
				map.put(i, null);
			}
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
		System.out.print("\n---------------------");
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
		map.put(portReserve, student);
		EmailSocketModel.run(student);

		// writing to student database
		try {
			FileWriter file = new FileWriter("students.txt", true);
			PrintWriter writer = new PrintWriter(file);
			writer.println(student.toString());
			writer.close(); 

		} catch (IOException e) {

		}

	}

	public void injectMap(Map<Integer, Student> map) {
		this.map = (HashMap<Integer, Student>) map;
	}
}
