package model;

public class Student {

	private String studentID;
	private String name;
	private int portReserve;
	private String email;

	public Student(String studentID, String name, int portReserve, String email) {
		this.studentID = studentID;
		this.name = name;
		this.portReserve = portReserve;
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("%s\t\t%s\t\t%d\t\t%s", studentID, name, portReserve, email);
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public int getPort() {
		return portReserve;
	}

}
