package br.com.geodrone.exception;


import br.com.geodrone.utils.Message;
import br.com.geodrone.utils.Messenger;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Messenger messenger;

	public BusinessException() {}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BusinessException(Messenger messenger) {
		this.messenger = messenger;
	}
	
	public BusinessException(String message, Messenger messenger) {
		super(message);
		this.messenger = messenger;
	}

	public BusinessException(Throwable cause, Messenger messenger) {
		super(cause);
		this.messenger = messenger;
	}

	public BusinessException(String message, Throwable cause, Messenger messenger) {
		super(message, cause);
		this.messenger = messenger;
	}

	public Messenger getMessenger() {
		return messenger;
	}
	
	public boolean hasMessenger() {
		if (messenger != null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (messenger != null && messenger.hasMessages()) {
			for (Message message : messenger.getMessages()) {
				if (sb.length() > 0) {
					sb.append(" | "); 
				}
				sb.append(message.getMsg());
			}
		} else {
			sb.append(super.toString());
		}
		
		return sb.toString();
	}

}