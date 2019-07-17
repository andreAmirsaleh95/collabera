package com.jump.maven.DatabaseUtils;

import java.sql.SQLException;

public class DatabaseException extends SQLException {

	/**
	 * Default Value.
	 */
	private static final long serialVersionUID = 1L;
		
	public DatabaseException(String msg) {
		super(msg);
	}
}
