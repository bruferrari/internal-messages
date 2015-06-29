package test.messages.model;

import java.io.Serializable;

/**
 * @author bruno ferrari
 * @since 06-28-2015 Msg object model
 */

public class Msg implements Serializable {

	private static final long serialVersionUID = 1L;

	private User sender;
	private User receiver;
	private String msg;
	private Long id;

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
