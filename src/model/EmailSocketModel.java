package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class EmailSocketModel {
	private static Socket smtpSocket;
	private static PrintWriter out;
	private static BufferedReader in;

	public static void run(Student student) {

		try {
			// Open a socket on port 25 try-catch(UnknownHostException e)
			smtpSocket = new Socket("localhost", 25);

			// Initialize Streams for communication between host and server
			in = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
			out = new PrintWriter(smtpSocket.getOutputStream(), true);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: hostname");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: hostname");
		}

		//
		if (smtpSocket != null && out != null && in != null) {
			try {

				// Get greeting message from the server
				String responseLine;
				while ((responseLine = in.readLine()) != null) {
					System.out.println("\nServer: " + responseLine);
					if (responseLine.indexOf("220") != -1) {
						break;
					}
				}

				// The clients starts communication by first introduce itself to server with
				// HELO command
				out.println("HELO " + InetAddress.getLocalHost().getHostAddress());
				System.out.println("HELO " + InetAddress.getLocalHost().getHostAddress());
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}

				// The client informs receiver server about the sender's email address by using
				// MAIL From
				// command
				out.println("MAIL From: RMITNetworkingProgrammingCourse@rmit.edu.au");
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}

				// The client informs receiver server about the recipient's email address by
				// using RCPT From command
				out.println("RCPT TO: " + student.getEmail());
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}

				// Send DATA command before initializing email body
				out.println("DATA");
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("354") != -1) {
						break;
					}
				}

				// Send email body
				out.println("From: RMITNetworkingProgrammingCourse@rmit.edu.au");
				out.println("To: " + student.getEmail());
				out.println("Subject: Port Resgistration Notification");
				out.println();
				out.println("Hi " + student.getName() + ",");
				out.println("\nCongratulation you have successfully registered your port");
				out.println("Here is your information:\n\n");
				out.println("StudentID\t\tName\t\tPort\t\tEmail");
				out.println(student.toString());
				out.println("\nRegards,");
				out.println("RMIT Network Programming course");
				out.println();
				out.println(".");

				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}

				// Use QUIT command to terminate connection with SMTP server
				out.println("QUIT");

				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("221") != -1) {
						break;
					}
				}

				System.out.println("Email successfully sent!\n");
				System.out.println("\n---------------------\n");
				out.close();
				in.close();
				smtpSocket.close();

			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}