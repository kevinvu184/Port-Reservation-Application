package model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Persistence {

	private boolean backUpExist = false;
	private Map<Integer, Student> map = new HashMap<Integer, Student>();

	public Persistence() {
		checkFileExist();
	}

	private void checkFileExist() {
		String rootPath = new File("").getAbsolutePath();
		String fileName = rootPath + "/students.txt";

		File file = new File(fileName);
		Scanner input = null;
		try {
			input = new Scanner(file);
			input.nextLine();
			if (input.hasNextLine()) {
				for (int i = PortReservationModel.START_PORT; i <= PortReservationModel.END_PORT; ++i) {
					map.put(i, null);
				}
			}
			while (input.hasNextLine()) {
				String tmp = input.nextLine();
				StringTokenizer sToken = new StringTokenizer(tmp);

				Student s = new Student(sToken.nextToken(), sToken.nextToken(), Integer.parseInt(sToken.nextToken()),
						sToken.nextToken());

				map.put(s.getPort(), s);

				backUpExist = true;
			}

		} catch (Exception e) {
			System.err.println("ERROR OCCUR WHILE READING FROM DATABASE");
		} finally {
			input.close();
		}
	}

	public boolean getBackUpExist() {
		return backUpExist;
	}

	public Map<Integer, Student> getMap() {
		return map;
	}

}
