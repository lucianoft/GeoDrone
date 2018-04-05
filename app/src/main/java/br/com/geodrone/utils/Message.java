package br.com.geodrone.utils;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int INFO = 1;
	public static final int WARN = 2;
	public static final int ERROR = 3;
	public static final int CANCEL = 4;

	private int level;
	private String msg;

	public Message(int level, String msg) {
		this.level = level;
		this.msg = msg;
	}
	
	public int getLevel() {
		return level;
	}

	public String getMsg() {
		return msg;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Message other = (Message) obj;
		if (msg == null) {
			if (other.msg == null) {
				return true;
			}
			return false;
		}
		if (msg.equalsIgnoreCase(other.msg)) {
			return true;
		}
		return false;
	}

}