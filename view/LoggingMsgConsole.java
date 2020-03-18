package view;

public class LoggingMsgConsole {

	public static void printAvailablePortMsg(String msg) {
		System.out.println(msg);
	}

	public static void promptName() {
		System.out.print("\nName: ");
	}

	public static void promptId() {
		System.out.print("ID: ");
	}

	public static void promptEmail() {
		System.out.print("Email: ");
	}

	public static void promptPort() {
		System.out.print("Port: ");
	}

	public static void printErrorMsg() {
		System.out.println(
				"\nERROR:The port you choose has been reserved or not in valid range, please enter another port\n");
	}
}
