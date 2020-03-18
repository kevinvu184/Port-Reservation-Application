package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import view.LoggingMsgConsole;

public class PortReservationModel {
	private HashMap<Integer, Student> map = new HashMap<Integer, Student>();
	public static final int START_PORT = 61000;
	public static final int END_PORT = 61999;
	private boolean firstRunDatabase = true;
	private Student recentRegisteredStudent;

	public void setFirstRunDatabase() {
		firstRunDatabase = false;
	}

	public boolean getFirstRunDatabase() {
		return firstRunDatabase;
	}

	public boolean validateConsistentInfo() {
		Iterator<Entry<Integer, Student>> it = map.entrySet().iterator();
		System.out.println(recentRegisteredStudent);
		while (it.hasNext()) {
			Map.Entry<Integer, Student> pair = (Map.Entry<Integer, Student>) it.next();
			if (pair.getValue() != null) {
				if (pair.getValue().getID().equalsIgnoreCase(recentRegisteredStudent.getID())) {
					if (!pair.getValue().getName().equalsIgnoreCase(recentRegisteredStudent.getName())
							|| !pair.getValue().getEmail().equalsIgnoreCase(recentRegisteredStudent.getEmail())) {
						return false;
					}
				}
			}

		}
		EmailSocketModel.run(recentRegisteredStudent);
		map.put(recentRegisteredStudent.getPort(), recentRegisteredStudent);

		try {
			FileWriter file = new FileWriter("students.txt", true);
			PrintWriter writer = new PrintWriter(file);
			writer.println(recentRegisteredStudent.toString());
			writer.close();

		} catch (IOException e) {

		}
		return true;
	}

	public boolean validateMaxPortSelected() {
		int portSelected = 0;
		Iterator<Entry<Integer, Student>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Student> pair = (Map.Entry<Integer, Student>) it.next();
			if (pair.getValue() != null) {
				if (pair.getValue().getID().equalsIgnoreCase(recentRegisteredStudent.getID())) {
					++portSelected;
				}
			}
			if (portSelected == 2) {
				return false;
			}
		}

		return true;
	}

	public boolean portAlreadySelected(boolean firstRun, String portSelect, String emailSelect, String studentID,
			String name) {
		if (firstRun) {
			for (int i = START_PORT; i <= END_PORT; ++i) {
				map.put(i, null);
			}
		}

		int portReserve;
		boolean portReserved = true;
		String email = emailSelect;

		portReserve = Integer.parseInt(portSelect);
		if (map.containsKey(portReserve) && map.get(portReserve) == null) {

			recentRegisteredStudent = new Student(studentID, name, portReserve, email);
			portReserved = false;
		}

		return portReserved;

	}

	public String getAvailablePort() {
		StringBuffer sBuffer = new StringBuffer("");
		Iterator<Entry<Integer, Student>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Student> pair = (Map.Entry<Integer, Student>) it.next();
			if (pair.getValue() != null) {
				sBuffer.append(pair.getKey() + ", ");
			}
		}
		return sBuffer.substring(0, sBuffer.length() == 0 ? 0 : sBuffer.length() - 2);
	}

	public String getAvailablePortMsg() {
		if (getAvailablePort().length() > 0) {
			return "Note: The available port is 61000-61999 excluding: " + getAvailablePort();
		}
		return "Note: The available port is 61000-61999";
	}

	public void injectMap(Map<Integer, Student> map) {
		this.map = (HashMap<Integer, Student>) map;
	}

	public StringBuffer getDatabase() {
		StringBuffer sBuffer = new StringBuffer("");
		Iterator<Entry<Integer, Student>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Student> pair = (Map.Entry<Integer, Student>) it.next();
			if (pair.getValue() != null) {
				sBuffer.append(pair.getValue().toString() + "\n");
				sBuffer.append("-------------------------------------------------------------------------------\n");
			}
		}
		return sBuffer;
	}
}
