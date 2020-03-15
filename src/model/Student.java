package model;

public class Student {

	private String studentID;
	private String name;
	private int portReserve_1;
	private int portReserve_2;
	private String email;

	public Student(String studentID, String name, int portReserve_1, int portReserve_2, String email) {
		this.studentID = studentID;
		this.name = name;
		this.portReserve_1 = portReserve_1;
		this.portReserve_2 = portReserve_2;
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("%s\t\t%s\t\t%d %d\t\t%s", studentID, name, portReserve_1, portReserve_2, email);
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public int getPort_1() {
		return portReserve_1;
	}

	public int getPort_2() {
		return portReserve_2;
	}

}
