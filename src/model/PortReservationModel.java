package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

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

		Scanner input = new Scanner(System.in);
		System.out.print("\n---------------------");
		System.out.print("\nName: ");
		String name = input.nextLine();
		System.out.print("Student id: ");
		String studentID = input.nextLine();
		int portReserve_1;
		int portReserve_2;
		boolean portReserved = true;
		Student student = null;

		do {
			if (getAvailablePort().length() > 0) {

				LoggingMsgConsole.printAvailablePortMsg(String.format("%s%s",
						"NOTE:Current available port is 61000-61999 excluding: ", getAvailablePort()));
			} else {
				LoggingMsgConsole
						.printAvailablePortMsg(String.format("%s", "NOTE:Current available port is 61000-61999"));
			}
			LoggingMsgConsole.promptPort();
			portReserve_1 = Integer.parseInt(input.nextLine());
			if (portReserve_1 >= 61000 && portReserve_1 <= 61999 && map.get(portReserve_1) == null) {

				LoggingMsgConsole.promptPort();
				portReserve_2 = Integer.parseInt(input.nextLine());
				if (portReserve_2 != portReserve_1) {
					// Validation of existence of port in range
					if (map.containsKey(portReserve_2) && map.get(portReserve_2) == null) {
						LoggingMsgConsole.promptEmail();
						String email = input.nextLine();
						student = new Student(studentID, name, portReserve_1, portReserve_2, email);
						map.put(portReserve_1, student);
						map.put(portReserve_2, student);
						portReserved = false;
					} else {
						LoggingMsgConsole.printErrorMsg();
					}
				} else {
					LoggingMsgConsole.printErrorMsg();
				}
			} else {
				LoggingMsgConsole.printErrorMsg();
			}
		} while (portReserved);
		// conduct validation and reprompt

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

	public String getAvailablePort() {
		StringBuffer sBuffer = new StringBuffer("");
		Set<Student> set = new HashSet<Student>();
		Iterator<Entry<Integer, Student>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Student> pair = (Map.Entry<Integer, Student>) it.next();
			if (pair.getValue() != null && !set.contains(pair.getValue())) {
				set.add(pair.getValue());
				sBuffer.append(pair.getKey() + ", " + pair.getValue().getPort_2() + ", ");
			}
		}
		return sBuffer.substring(0, sBuffer.length() == 0 ? 0 : sBuffer.length() - 2);
	}

	public void injectMap(Map<Integer, Student> map) {
		this.map = (HashMap<Integer, Student>) map;
	}

	public StringBuffer getDatabase() {
		StringBuffer sBuffer = new StringBuffer("");
		Set<Student> set = new HashSet<Student>();
		Iterator<Entry<Integer, Student>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Student> pair = (Map.Entry<Integer, Student>) it.next();
			if (pair.getValue() != null && !set.contains(pair.getValue())) {
				set.add(pair.getValue());
				sBuffer.append(pair.getValue().toString() + "\n");
				sBuffer.append("-----------------------------------------------------------------------------------\n");
			}
		}
		return sBuffer;
	}
}
