package test.messages.util;

import java.util.Scanner;

import test.messages.dao.MsgDAO;
import test.messages.dao.UsersDAO;
import test.messages.internalMsgTest.App;

public class CommonUtils {

	private static final String YES = "y";
	private static final String NO = "n";
	private static final String DELETE = "d";
	App app = new App();
	Scanner scanner = new Scanner(System.in);

	public void logout() {
		System.out.println("logged out!\n");
		app.login();
	}

	public void returnToMenu() {

		System.out
				.println("Do you like to get back to main menu? (y/n) or 'd' if you want to delete msg");
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();

		switch (choice) {
		case YES:
			clearScreen();
			app.userInterface();
			break;

		case NO:
			logout();
			break;

		case DELETE:
			deleteMsg();
			app.userInterface();
			break;

		default:
			break;
		}
	}

	private void deleteMsg() {

		System.out.println("ID: ");
		Long id = scanner.nextLong();

		MsgDAO dao = new MsgDAO();

		if (dao.canDeleteMsg(id) == true)
			dao.deleteMessage(id);
		else
			System.out.println("Message doesn't exists!");

	}

	public void clearScreen() {
		try {
			final String os = System.getProperty("os.name");

			if (os.startsWith("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
