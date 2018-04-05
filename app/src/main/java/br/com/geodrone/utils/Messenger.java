package br.com.geodrone.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Messenger implements Serializable {

	private static final long serialVersionUID = 1L;

	private int level = Message.INFO;
	private List<Message> messages;

	public Messenger() {
	}

	public Messenger(Exception exception) {
		addException(exception);
	}

	public Messenger(Exception exception, Messenger messenger) {
		addMessenger(exception, messenger);
	}

	public void addInfo(String msg) {
		addInfo(msg, false);
	}

	public void addFirstInfo(String msg) {
		addInfo(msg, true);
	}

	/**
	 *
	 * @param msg
	 * @param firstMessage
	 */
	private void addInfo(String msg, boolean firstMessage) {
		Message message = new Message(Message.INFO, msg);
		addMessage(message, firstMessage);
	}
	
	/**
	 * Adiciona as mensagens de <b>messenger</b> como warn.
	 *
	 * @param messenger
	 */
	public void addWarnMessenger(Messenger messenger) {
		if (messenger != null && messenger.hasMessages()) {
			for (Message message : messenger.getMessages()) {
				addWarn(message.getMsg());
			}
		}
	}

	public void addWarn(String msg) {
		addWarn(msg, false);
	}
	
	private void addWarn(String msg, boolean firstMessage) {
		if (level < Message.WARN) {
			level = Message.WARN;
		}
		Message message = new Message(Message.WARN, msg);
		addMessage(message, firstMessage);
	}
	
	public void addError(String msg) {
		addError(msg, false);
	}
	
	public void addFirstError(String msg) {
		addError(msg, true);
	}
	
	/**
	 *
	 * @param msg
	 * @param firstMessage
	 */
	private void addError(String msg, boolean firstMessage) {
		if (level < Message.ERROR) {
			level = Message.ERROR;
		}
		Message message = new Message(Message.ERROR, msg);
		addMessage(message, firstMessage);
	}
	
	public void addCancel(String msg) {
		addCancel(msg, false);
	}
	
	/**
	 *
	 * @param msg
	 * @param firstMessage
	 */
	private void addCancel(String msg, boolean firstMessage) {
		if (level < Message.CANCEL) {
			level = Message.CANCEL;
		}
		Message message = new Message(Message.CANCEL, msg);
		addMessage(message, firstMessage);
	}
	
	public boolean isInfo() {
		if (level == Message.INFO) {
			return true;
		}
		return false;
	}

	public boolean isWarn() {
		if (level == Message.WARN) {
			return true;
		}
		return false;
	}

	public boolean isError() {
		if (level == Message.ERROR) {
			return true;
		}
		return false;
	}
	
	public boolean isCancel() {
		if (level == Message.CANCEL) {
			return true;
		}
		return false;
	}

	public boolean hasMessages() {
		if (messages != null && !messages.isEmpty()) {
			return true;
		}
		return false;
	}

	public List<Message> getMessages() {
		return messages;
	}

	/**
	 *
	 * @param message
	 * @param firstMessage
	 */
	private void addMessage(Message message, boolean firstMessage) {
		if (messages == null) {
			messages = new ArrayList<Message>();
		}
		if (!messages.contains(message)) {
			if (firstMessage) {
				messages.add(0, message);
			} else {
				messages.add(message);
			}
		}
	}

	/**
	 * 
	 * @param messenger
	 */
	public void addMessenger(Messenger messenger) {
		addMessenger(messenger, false);
	}

	/**
	 * 
	 * @param messenger
	 */
	public void addFirstMessenger(Messenger messenger) {
		addMessenger(messenger, true);
	}

	/**
	 * 
	 * @param messenger
	 * @param addError
	 * @param addWarn
	 * @param addInfo
	 * @param addCancel
	 */
	public void addMessenger(Messenger messenger, boolean addError, boolean addWarn, boolean addInfo, boolean addCancel) {
		_addMessenger(messenger, false, addError, addWarn, addInfo, addCancel);
	}
	
	/**
	 * 
	 * @param messenger
	 * @param addError
	 * @param addWarn
	 * @param addInfo
	 */
	public void addMessenger(Messenger messenger, boolean addError, boolean addWarn, boolean addInfo) {
		_addMessenger(messenger, false, addError, addWarn, addInfo, false);
	}

	/**
	 * 
	 * @param messenger
	 * @param firstMessager
	 */
	private void addMessenger(Messenger messenger, boolean firstMessager) {
		_addMessenger(
				messenger,
				firstMessager,
				true, // addError
				true, // addWarn,
				true, // addInfo
				true);// addCancel
	}
	
	/**
	 * 
	 * @param messenger
	 * @param firstMessager
	 * @param addError
	 * @param addWarn
	 * @param addInfo
	 * @param addCancel
	 */
	@SuppressWarnings("unused")
	private void addMessenger(Messenger messenger, boolean firstMessager, boolean addError, boolean addWarn, boolean addInfo, boolean addCancel) {
		_addMessenger(messenger, firstMessager, addError, addWarn, addInfo, addCancel);
	}
	
	/**
	 * 
	 * @param messenger
	 * @param firstMessager
	 * @param addError
	 * @param addWarn
	 * @param addInfo
	 */
	private void _addMessenger(Messenger messenger, boolean firstMessager, boolean addError, boolean addWarn, boolean addInfo, boolean addCancel) {
		if (messenger != null && messenger.hasMessages()) {
			for (Message message : messenger.getMessages()) {
				if (message.isError() && addError) {
					addError(message.getMsg(), firstMessager);
				} else if (message.isWarn() && addWarn) {
					addWarn(message.getMsg(), firstMessager);
				} else if (message.isInfo() && addInfo) {
					addInfo(message.getMsg(), firstMessager);
				} else if (message.isInfo() && addCancel) {
					addCancel(message.getMsg(), firstMessager);
				}
			}
		}
	}

	public void addMessenger(Exception exception, Messenger messenger) {
		if (messenger != null && messenger.hasMessages()) {
			addMessenger(messenger);
		} else {
			addException(exception);
		}
	}

	public void addException(Throwable ex) {
		String error = ex.toString();
		if (error != null) {
			addError(error);
		}
	}

	/**
	 * 
	 * @return - Retorna  a primeira mensagem de erro.
	 */
	public String getFirstMessageError() {
		if (hasMessages() && isError()) {
			for (Message message : messages) {
				if (message.isError()) {
					return message.getMsg();
				}
			}
		}

		return null;
	}

	/**
	 * 
	 * @return - Retorna  a primeira mensagem de warn.
	 */
	public String getFirstMessageWarn() {
		if (hasMessages()) {
			for (Message message : messages) {
				if (message.isWarn()) {
					return message.getMsg();
				}
			}
		}

		return null;
	}

	/**
	 * 
	 * @return - Retorna  a primeira mensagem de info.
	 */
	public String getFirstMessageInfo() {
		if (hasMessages()) {
			for (Message message : messages) {
				if (message.isInfo()) {
					return message.getMsg();
				}
			}
		}

		return null;
	}
	
	/**
	 * 
	 * @return - Retorna  a primeira mensagem de cancel.
	 */
	public String getFirstMessageCancel() {
		if (hasMessages()) {
			for (Message message : messages) {
				if (message.isCancel()) {
					return message.getMsg();
				}
			}
		}
		
		return null;
	}

}