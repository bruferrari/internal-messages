package test.messages.common;

/**
 * @author bruno ferrari
 * @since 06-28-2015
 * Just store the sql queries
 */

public class MsgSql {
	
	public static final String SEND_MSG = "INSERT INTO messages (sender, receiver, msg) "
			+ "VALUES (?,?,?)";

	public static final String RECEIVE_MSG = "SELECT messages.id,messages.sender,"
			+ "messages.receiver,messages.msg,u.alias "
			+ "FROM messages INNER JOIN users u ON messages.sender = u.id "
			+ "WHERE messages.receiver = ?";
	
	public static final String LOGIN_CHECK_ID = "SELECT id FROM users WHERE id = ?";
	
	public static final String DELETE_MSG = "DELETE FROM messages WHERE id = ?";
	
	public static final String CHECK_IF_EXISTS_MESSAGE = "SELECT id FROM messages WHERE id = ?";

}
