package test.messages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.messages.common.MsgSql;
import test.messages.connection.ConnectionFactory;
import test.messages.model.Msg;
import test.messages.model.User;

/**
 * @author bruno ferrari
 * @since 06-28-2015 Class for data access object for Msg object
 */

public class MsgDAO {

	public void sendMessage(Msg msg) {
		try {
			Connection conn = ConnectionFactory.connect();

			PreparedStatement ppStm = conn.prepareStatement(MsgSql.SEND_MSG);

			ppStm.setLong(1, msg.getSender().getId());
			ppStm.setLong(2, msg.getReceiver().getId());
			ppStm.setString(3, msg.getMsg());

			ppStm.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Msg> receiveMessage(Long userId) {
		List<Msg> userMessages = new ArrayList<Msg>();
		try {
			Connection conn = ConnectionFactory.connect();

			PreparedStatement ppStm = conn.prepareStatement(MsgSql.RECEIVE_MSG);

			ppStm.setLong(1, userId);
			ResultSet rSet = ppStm.executeQuery();

			while (rSet.next()) {
				Msg msg = new Msg();

				User sender = new User();
				sender.setId(rSet.getLong("sender"));
				sender.setAlias(rSet.getString("alias"));

				User receiver = new User();
				receiver.setId(rSet.getLong("receiver"));

				msg.setId(rSet.getLong("id"));
				msg.setSender(sender);
				msg.setReceiver(receiver);
				msg.setMsg(rSet.getString("msg"));

				userMessages.add(msg);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userMessages;
	}

	public void deleteMessage(Long msgId) {

		try {
			Connection conn = ConnectionFactory.connect();
			PreparedStatement ppStm = conn.prepareStatement(MsgSql.DELETE_MSG);
			ppStm.setLong(1, msgId);

			ppStm.executeUpdate();
			conn.close();

			System.out.println("Message deleted!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean canDeleteMsg(Long msgId) {
		List<Msg> userMessages = new ArrayList<Msg>();

		try {
			Connection conn = ConnectionFactory.connect();

			PreparedStatement ppStm = conn.prepareStatement(MsgSql.CHECK_IF_EXISTS_MESSAGE);

			ppStm.setLong(1, msgId);
			ResultSet rSet = ppStm.executeQuery();
			
			while(rSet.next()) {
				Msg message = new Msg();
				message.setId(rSet.getLong("id"));
				
				userMessages.add(message);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (userMessages.size() != 0)
			return true;
		else
			return false;
	}
}
