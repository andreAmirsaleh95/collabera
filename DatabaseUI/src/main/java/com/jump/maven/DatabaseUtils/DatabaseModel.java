package com.jump.maven.DatabaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.jump.maven.DatabaseUI.DatabaseUI;

/**
 * Provides backend functionality for StagBench GUI.
 * 
 * @author Andre Amirsaleh
 * @author Walter Salmeron
 * @author Brett Aiken
 */

public class DatabaseModel {

	public static Connection connection;
	private static Statement statement;
	public static String databaseName;
	public static ArrayList<JMenuItem> historyList = new ArrayList<>();

	public static void connect(String hostname, String database, String username,
			char[] password) throws DatabaseException {
		databaseName = database;

		try {
			if (connection == null) {
				connection = DriverManager.getConnection(hostname, username,
						new String(password));
			} else {
				connection.close();
				connection = DriverManager.getConnection(hostname, username,
						new String(password));
			}

			statement = connection.createStatement();
		} catch (SQLException e) {
			throw new DatabaseException("Could not connect to database.");
		}
	}

	private static boolean executeAlter(String query) throws DatabaseException {
		boolean success;
		try {
			success = statement.execute(query);
			JOptionPane.showMessageDialog(null, "Successfully altered table!");
		} catch (SQLException e) {
			throw new DatabaseException(
					"Could not execute ALTER statement. Check syntax.");
		}
		return success;
	}

	private static boolean executeCreate(String query) throws DatabaseException {
		boolean success;
		try {
			success = statement.execute(query);
			DatabaseUI.statusField.setText(DatabaseUI.statusField.getText()
					+ "\nSuccessfully created table!");
		} catch (SQLException e) {
			throw new DatabaseException(
					"Could not execute CREATE statement. Check syntax.");
		}
		return success;
	}

	private static boolean executeDrop(String query) throws DatabaseException {
		boolean success;
		try {
			success = statement.execute(query);
			DatabaseUI.statusField.setText(DatabaseUI.statusField.getText()
					+ "\nSuccessfully dropped table!");
		} catch (SQLException e) {
			throw new DatabaseException(
					"Could not execute DROP statement. Check syntax.");
		}
		return success;
	}

	private static ResultSet executeSelect(String query)
			throws DatabaseException {
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			throw new DatabaseException(
					"Could not execute SELECT or SHOW statement. Check syntax.");
		}
	}

	private static int executeUpdateDeleteOrInsert(String query)
			throws DatabaseException {
		int rowsAffected = 0;
		try {
			rowsAffected = statement.executeUpdate(query);
			DatabaseUI.statusField.setText(DatabaseUI.statusField.getText() + "\n"
					+ rowsAffected + " row(s) affected!");
		} catch (SQLException e) {
			throw new DatabaseException(
					"Could not execute either UPDATE, DELETE, or INSERT statement."
							+ "Check syntax.");
		}
		return rowsAffected;
	}

	private static boolean executeUse(String query) throws DatabaseException {
		boolean success;
		try {
			success = statement.execute(query);
			JOptionPane.showMessageDialog(null,
					"Successfully moved to new database!");
			DatabaseUI.statusField.setText(DatabaseUI.statusField.getText()
					+ "\nSuccessfully moved to new database!");
			databaseName = query.trim().split(" ")[1];
		} catch (SQLException e) {
			throw new DatabaseException(
					"Could not execute USE statement. Check syntax.");
		}
		return success;
	}

	public static QueryTypes getQueryType(String query)
			throws DatabaseException {
		QueryTypes qt = null;
		String queryFirstWord = query.trim().split(" ")[0];
		if (QueryTypes.SELECT.toString().equalsIgnoreCase(queryFirstWord)) {
			qt = QueryTypes.SELECT;
		} else if (QueryTypes.UPDATE.toString()
				.equalsIgnoreCase(queryFirstWord)) {
			qt = QueryTypes.UPDATE;
		} else if (QueryTypes.DELETE.toString()
				.equalsIgnoreCase(queryFirstWord)) {
			qt = QueryTypes.DELETE;
		} else if (QueryTypes.ALTER.toString().equalsIgnoreCase(queryFirstWord)) {
			qt = QueryTypes.ALTER;
		} else if (QueryTypes.INSERT.toString()
				.equalsIgnoreCase(queryFirstWord)) {
			qt = QueryTypes.INSERT;
		} else if (QueryTypes.USE.toString().equalsIgnoreCase(queryFirstWord)) {
			qt = QueryTypes.USE;
		} else if (QueryTypes.DROP.toString().equalsIgnoreCase(queryFirstWord)) {
			qt = QueryTypes.DROP;
		} else if (QueryTypes.SHOW.toString().equalsIgnoreCase(queryFirstWord)) {
			qt = QueryTypes.SHOW;
		} else if (QueryTypes.CREATE.toString()
				.equalsIgnoreCase(queryFirstWord)) {
			qt = QueryTypes.CREATE;
		} else {
			DatabaseUI.statusField.setText(
					"Not correct input! Possible statements include:" + "\n\tSELECT"
							+ "\tINSERT" + "\tUPDATE" + "\n\tDELETE" + "\tALTER"
							+ "\tDROP" + "\n\tSHOW" + "\tCREATE" + "\tUSE");
			qt = null;

		}
		return qt;
	}

	public static ResultSet submit(String query) throws DatabaseException {
		ResultSet rs = null;
		String[] queries = query.split(";");
		for (String q : queries) {
			rs = submitOneCommand(q);
		}
		return rs;
	}

	private static ResultSet submitOneCommand(String query)
			throws DatabaseException {
		QueryTypes qt = getQueryType(query);
		if (qt == null) {
			return null;
		}
		switch (qt) {
		case SELECT:
		case SHOW:
			return executeSelect(query);
		case INSERT:
		case UPDATE:
		case DELETE:
			executeUpdateDeleteOrInsert(query);
			break;
		case USE:
			executeUse(query);
			break;
		case ALTER:
			executeAlter(query);
			break;
		case CREATE:
			executeCreate(query);
			break;
		case DROP:
			executeDrop(query);
			break;
		}
		return null;
	}

	public static String createShellQuery(String queryType, String tableName,
			String columnNames) {
		String queryShell = "";

		if (queryType.equalsIgnoreCase("insert")) {
			queryShell = " INSERT INTO " + tableName + " (";

			queryShell += columnNames;
			queryShell += ") " + "VALUES " + "(<{emp_id: }>, <{emp_name: }>,"
					+ "<{ssn: }>, <{age: }>, <{email_address: }>, <{phone_num: }>);";
		} else if (queryType.equalsIgnoreCase("delete")) {
			queryShell = "DELETE FROM " + tableName
					+ " WHERE <{where_expression}>;";
		} else if (queryType.equalsIgnoreCase("update")) {
			boolean firstPass = true;
			queryShell = "UPDATE " + tableName + " SET ";
			String primaryKey = "";
			List<String> columns = Arrays.asList(columnNames.trim().split(" "));
			for (String column : columns) {
				if (firstPass) {
					primaryKey = column.trim().split(",")[0];
					firstPass = false;
				}
				if (column.indexOf(",") != -1) {
					column = column.substring(0, column.indexOf(","));
				}
				if (!columns.get(columns.size() - 1).equalsIgnoreCase(column))
					queryShell += column + " = <{" + column + ": }>,";
				else
					queryShell += column + " = <{" + column + ": }>";
			}

			queryShell += " WHERE " + primaryKey + "= <{expr}>;";
		}
		return queryShell;
	}
	
}