package test.messages.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.messages.common.MsgSql;
import test.messages.connection.ConnectionFactory;
import test.messages.model.User;

/**
 * @author bruno ferrari
 * @since 06-28-2015
 * Data access object for User object
 */

public class UsersDAO {

	public boolean userIsValid(Long userId) {
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = ConnectionFactory.connect();

			PreparedStatement ppStm = conn
					.prepareStatement(MsgSql.LOGIN_CHECK_ID);

			ppStm.setLong(1, userId);
			ResultSet rSet = ppStm.executeQuery();

			while (rSet.next()) {
				User user = new User();
				user.setId(rSet.getLong("id"));

				users.add(user);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (users.size() != 0)
			return true;
		else
			return false;
	}

}
