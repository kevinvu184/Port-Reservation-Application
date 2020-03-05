package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Persistence {
	public static final int START_PORT = 61000;
	public static final int END_PORT = 61999;
	private BufferedReader br;
	private boolean backUpExist = false;
	private Map<Integer, Student> map = new HashMap<Integer, Student>();

	public Persistence() {
		checkFileExist();
	}

	private void checkFileExist() {
		String rootPath = new File("").getAbsolutePath();
		String fileName = rootPath + "/students.txt";

		File file = new File(fileName);
		try {
			Scanner input = new Scanner(file);
			input.nextLine();
			if(input.hasNextLine()) {
				for (int i = START_PORT; i <= END_PORT; ++i) {
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
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean getBackUpExist() {
		return backUpExist;
	}

	public Map<Integer, Student> getMap() {
		return map;
	}

}
