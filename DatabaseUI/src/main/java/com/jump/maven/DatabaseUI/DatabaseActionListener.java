package com.jump.maven.DatabaseUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.jump.maven.DatabaseUtils.DatabaseException;
import com.jump.maven.DatabaseUtils.DatabaseModel;

/**
 * ActionListener for StagBench GUI.
 * 
 * @author Andre Amirsaleh
 * @author Walter Salmeron
 * @author Brett Aiken
 * @author Braxton Harris
 */

public class DatabaseActionListener implements ActionListener
{
	// Action commands:
	public static final String CONNECT = "connect";
	public static final String EXECUTE = "execute";
	public static final String SAVE = "save";
	public static final String CLEAR = "clear";
	public static final String LOGOUT = "log out";
	public static final String EXIT = "exit";
	public static final String FILE = "file";
	public static final String COMMAND = "command";
	public static final String MYSQL = "mysql";
	public static final String POSTGRESQL = "postgresql";
	public static final String SQLITE = "sqlite";
	public static final String ORACLE = "oracle";

	// DispayFields:
	private JComboBox<String> dbOption;
	private JTextField hostField;
	private JTextField dbField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private DefaultTableModel dbTableModel;
	private JComboBox<String> commandList;
	private JTextField queryField;
	private JMenu historyMenu;
	private JMenu tableMenu;
	private JLabel databaseName;
	private ResultSet result;
	private JMenuItem sqlFile;

	// Will Populate the fields with what is given by the user
	public DatabaseActionListener(JComboBox<String> dbOption, JTextField hostField, JTextField dbField,
			JTextField usernameField, JPasswordField passwordField)
	{

		this.dbOption = dbOption;
		this.hostField = hostField;
		this.dbField = dbField;
		this.usernameField = usernameField;
		this.passwordField = passwordField;

	}

	public DatabaseActionListener(JLabel databaseName, DefaultTableModel dbTableModel, JMenuItem sqlFile,
			JMenu historyMenu, JMenu tableMenu, JComboBox<String> commandList, JTextField queryField)
	{

		this.commandList = commandList;
		this.dbTableModel = dbTableModel;
		this.queryField = queryField;
		this.historyMenu = historyMenu;
		this.databaseName = databaseName;
		this.tableMenu = tableMenu;
		this.sqlFile = sqlFile;

		tableMenuUpdater();
	}
	
	public DatabaseActionListener()
	{
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		switch (e.getActionCommand())
		{
		case CONNECT:
			connect();
			break;

		case EXECUTE:
			execute(false);
			queryField.setText("");
			break;

		case SAVE:

			break;
		case CLEAR:
			dbTableModel.setColumnCount(0);
			dbTableModel.setRowCount(0);
			queryField.setText("");
			break;

		case LOGOUT:
			logout();
			break;

		case EXIT:
			JOptionPane.showMessageDialog(null, "Thanks for using StagBench!");
			System.exit(0);
			break;

		case COMMAND:
			commandList();
			break;

		case FILE:
			openFileSystem();
			break;
		default:
			queryField.setText(e.getActionCommand());
		}
	}

	public void commandList()
	{
		switch (commandList.getSelectedIndex())
		{

		case 0:
			queryField.setText(queryField.getText() + "SELECT ");
			break;

		case 1:
			queryField.setText(queryField.getText() + "SELECT * FROM ");
			break;

		case 2:
			queryField.setText(queryField.getText() + "UPDATE ");
			break;

		case 3:
			queryField.setText(queryField.getText() + "DELETE FROM ");
			break;

		case 4:
			queryField.setText(queryField.getText() + "INSERT INTO ");
			break;

		case 5:
			queryField.setText(queryField.getText() + "ALTER TABLE ");
			break;
		case 6:
			queryField.setText(queryField.getText() + "SHOW TABLES ");
			break;
		case 7:
			queryField.setText(queryField.getText() + "DROP TABLE ");
			break;

		default:
			queryField.setText(queryField.getText() + "SELECT * FROM ");
			break;
		}
	}

	private void connect()
	{
		String database = null;

		switch ((String) dbOption.getSelectedItem())
		{
		case MYSQL:
			database = "jdbc:mysql://" + hostField.getText() + "/" + dbField.getText();
			break;
		case POSTGRESQL:
			database = "jdbc:postgresql://" + hostField.getText() + "/" + dbField.getText();
			break;
		case SQLITE:
			database = "jdbc:sqlite://" + hostField.getText() + "/" + dbField.getText();
			break;
		case ORACLE:
			database = "jdbc:oracle://" + hostField.getText() + "/" + dbField.getText();
			break;
		default:
			database = "jdbc:mysql://" + hostField.getText() + "/" + dbField.getText();
			break;
		}
		String username = usernameField.getText();
		try
		{
			DatabaseModel.connect(database, dbField.getText(), username, passwordField.getPassword());
			DatabaseUI.buildUI(dbField.getText(), username);
			LoginUI.frame.dispose();
			DatabaseUI.frame.setTitle("Stag Bench (Beta) - " + database);
		}
		catch (SQLException e1)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,
					"<html><font color=\"red\">" + e1.getMessage() + " Try Again. " + "</font></html>");
			e1.printStackTrace();
		}
	}

	private void execute(boolean isFile)
	{
		// DatabaseModel.submit sends a query,
		// determines what type it is,
		// and returns a result if select statement
		try
		{
			result = DatabaseModel.submit(queryField.getText());

			String[] sqlQuery = queryField.getText().trim().split(" ");
			if (sqlQuery[0].equalsIgnoreCase("use"))
			{
				databaseName.setText(sqlQuery[1].split(";")[0]);
				historyMenu.removeAll();

			}
			if (!isFile && DatabaseModel.getQueryType(queryField.getText()) != null)
			{
				JMenuItem queryItem = new JMenuItem(queryField.getText());
				queryItem.setActionCommand(queryField.getText());
				queryItem.addActionListener(new DatabaseMenuListener(dbTableModel));
				historyMenu.add(queryItem, 0);
			}
			
			tableMenuUpdater();

		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			DatabaseUI.statusField.setText(DatabaseUI.statusField.getText() + "\n" + e.getMessage());
			e.printStackTrace();
		}

		dbTableModel.setColumnCount(0);
		dbTableModel.setRowCount(0);

		if (result != null)
		{
			try
			{
				ResultSetMetaData metadata = result.getMetaData();
				ArrayList<String> columnNames = new ArrayList<>();
				int columns = metadata.getColumnCount();
				int rows = 0;
				for (int i = 1; i <= columns; i++)
				{
					dbTableModel.addColumn(metadata.getColumnName(i));
					columnNames.add(metadata.getColumnName(i));
				}
				while (result.next())
				{
					Vector<String> resultList = new Vector<>();
					for (String column : columnNames)
					{
						resultList.add(result.getString(column));
					}
					dbTableModel.addRow(resultList);
					++rows;
				}
				DatabaseUI.statusField.setText(DatabaseUI.statusField.getText() + "\n" + rows + " row(s) returned.");
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				DatabaseUI.statusField.setText(DatabaseUI.statusField.getText() + "\n" + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	private void tableMenuUpdater()
	{
		try
		{
			DatabaseMetaData md = DatabaseModel.connection.getMetaData();

			ResultSet rs = md.getTables(DatabaseModel.databaseName, null, "%", null);
			this.tableMenu.removeAll();
			while (rs.next())
			{
				JMenu temp = new JMenu(rs.getString(3));
				temp.setActionCommand("SELECT * FROM " + temp.getText());
				temp.addActionListener(new DatabaseMenuListener(dbTableModel));
				this.tableMenu.add(temp);
				
				ResultSet columns = md.getColumns(DatabaseModel.databaseName, null, rs.getString(3), null);
				String concatColumns = "";
				ResultSetMetaData metadata = columns.getMetaData();
				while(columns.next())
				{
					if(!columns.isLast())
						concatColumns += columns.getString("COLUMN_NAME") + ", ";
					else
						concatColumns += columns.getString("COLUMN_NAME");
				}
				
				JMenuItem select = new JMenuItem("SELECT");
				select.setActionCommand("SELECT * FROM " + temp.getText());
				select.addActionListener(this);
				JMenuItem insert = new JMenuItem("INSERT");
				insert.setActionCommand(DatabaseModel.createShellQuery("INSERT", temp.getText(), concatColumns));
				insert.addActionListener(this);
				JMenuItem update = new JMenuItem("UPDATE");
				update.setActionCommand(DatabaseModel.createShellQuery("UPDATE", temp.getText(), concatColumns));
				update.addActionListener(this);
				JMenuItem delete = new JMenuItem("DELETE");
				delete.setActionCommand(DatabaseModel.createShellQuery("DELETE", temp.getText(), concatColumns));
				delete.addActionListener(this);
				
				temp.add(select);
				temp.add(insert);
				temp.add(update);
				temp.add(delete);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			DatabaseUI.statusField.setText(DatabaseUI.statusField.getText() + "\n" + e.getMessage());
		}
	}

	public void logout()
	{
		DatabaseUI.frame.dispose();
		LoginUI.loginUI();
	}

	private void openFileSystem()
	{
		JFileChooser choice = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("SQL Files", "sql");
		choice.setFileFilter(filter);

		int option = choice.showOpenDialog(this.sqlFile);
		if (option == JFileChooser.APPROVE_OPTION)
		{
			try (Scanner scan = new Scanner(choice.getSelectedFile()))
			{
				queryField.setText("");
				while (scan.hasNextLine())
				{
					String nextLine = scan.nextLine().trim();
					if (!nextLine.startsWith("#") && !nextLine.isEmpty())
					{
						queryField.setText(queryField.getText() + nextLine);
					}
				}
				execute(true);
				queryField.setText("");
			}
			catch (FileNotFoundException e)
			{
				DatabaseUI.statusField.setText(DatabaseUI.statusField.getText() + "\n" + e.getMessage());
			}

		}
	}
}