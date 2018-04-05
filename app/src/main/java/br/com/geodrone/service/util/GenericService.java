package br.com.geodrone.service.util;

import java.util.Locale;

public abstract class GenericService {

	protected boolean isEmpty(String value) {
		if (value == null || "".equals(value)) {
			return true;
		}
		return false;
	}

}