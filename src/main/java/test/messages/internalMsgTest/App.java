package test.messages.internalMsgTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import test.messages.dao.MsgDAO;
import test.messages.dao.UsersDAO;
import test.messages.model.Msg;
import test.messages.model.User;
import test.messages.util.CommonUtils;

/**
 * Implementation of messages service application using mysql database
 * 
 * @author Bruno Ferrari
 * @since 06-28-2015
 */
public class App {
	private static User receiver;
	private static Long receiverId;
	private static String message;
	private static int menuChoice;
	private static User userLogged;

	private static Scanner scanner = new Scanner(System.in);
	private static CommonUtils utils = new CommonUtils();

	public static void main(String[] args) {
		login();
	}

	public static void sendMsg() {
		CommonUtils c = new CommonUtils();
		System.out.println("select a receiver userID: ");
		receiverId = scanner.nextLong();
		
		receiver = new User();
		receiver.setId(receiverId);

		System.out.println("type your message:");
		scanner = new Scanner(System.in);
		message = scanner.nextLine();

		try {
			Msg msg = new Msg();
			MsgDAO dao = new MsgDAO();

			msg.setSender(userLogged);
			msg.setReceiver(receiver);
			msg.setMsg(message);

			dao.sendMessage(msg);
			System.out.println("message sent.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.returnToMenu();
	}

	public static void userInterface() {
		CommonUtils c = new CommonUtils();
		System.out.println("--------- Messages App --------");

		System.out.println("SELECT OPTIONS ABOVE.");
		System.out.println("1 - SEND MESSAGE");
		System.out.println("2 - INBOX");
		System.out.println("0 - LOGOUT");

		menuChoice = scanner.nextInt();

		switch (menuChoice) {
		case 1:
			sendMsg();
			break;
		case 2:
			inbox();
			break;
		case 0:
			c.logout();
		}
	}

	private static void inbox() {
		
		System.out.println("------ YOUR INBOX ------");

		MsgDAO dao = new MsgDAO();
		try {
			List<Msg> messages = new ArrayList<Msg>();
			messages = dao.receiveMessage(userLogged.getId());
			
			if(messages.size() == 0 ) {
				System.out.println("you have 0 messages. Come back later!");
			} else {
				for (int i = 0; i < messages.size(); i++) {

					Long id = messages.get(i).getId();
					User sender = messages.get(i).getSender();

					String senderFormat;
					if (sender == userLogged) {
						senderFormat = "yourself";
					} else {
						senderFormat = sender.getAlias();
					}

					System.out.println("MsgID: " + id);
					System.out.println("From: " + senderFormat);
					System.out.println("Message: " + messages.get(i).getMsg()
							+ "\n");
				}
			}
			utils.returnToMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void login() {

		System.out.println("------- LOGIN -------\n");
		System.out.println("UserID: ");
		
		Long userId = scanner.nextLong();
		
		checkId(userId);
		
	}
	
	public static void checkId(Long userId) {
		UsersDAO usersDao = new UsersDAO();
		if(usersDao.userIsValid(userId)){
			userLogged = new User();
			userLogged.setId(userId);
			userInterface();
		} else {
			System.out.println("user doesn't exists! Try again...");
			login();
		}
	}
}
