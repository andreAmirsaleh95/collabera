package com.jump.maven.DatabaseUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 * Database UI Window for StagBench GUI
 * 
 * @author Andre Amirsaleh
 * @author Walter Salmeron
 * @author Brett Aiken
 * @author Braxton Harris
 */

public class DatabaseUI
{
	public static JFrame frame;
	public static JTextArea statusField;

	public static void buildUI(String database, String username)
	{
		// --------------------JFrame--------------------
		frame = new JFrame();
		frame.setTitle("Stag Bench 8.0 CE");
		frame.setSize(855, 585); // Sets the frame's dimensions (Width x Length)
		frame.setLocation(300, 100); // Sets the frame's on-screen position (X-axis x Y-axis)
		frame.setResizable(false); // Window cannot be resized
		frame.setLayout(new FlowLayout()); // Sets the frame's layout manager
		frame.setVisible(true); // Makes frame visible

		// --------------------JTextFields--------------------
		JTextField queryField = new JTextField(40);
		statusField = new JTextArea(5, 60);
		statusField.setEditable(false);
		statusField.setForeground(Color.red.darker());

		// --------------------JButtons--------------------
		JButton execute = new JButton("Execute");
		execute.setForeground(Color.red);
		execute.setEnabled(false);

		// JButton save = new JButton("Save"); //in development
		// save.setEnabled(false);
		JButton clear = new JButton("Clear");
		JButton logout = new JButton("Log Out");
		JButton exit = new JButton("Exit");

		// --------------------JTable--------------------
		DefaultTableModel dtm = new DefaultTableModel();
		JTable dbTable = new JTable(dtm);

		dbTable.getTableHeader().setReorderingAllowed(false);
		dbTable.setPreferredScrollableViewportSize(new Dimension(750, 300)); // Sets the table's size dimensions (Width
																				// x Length)
		dbTable.setFillsViewportHeight(true); // Sets viewport height dynamically visible

		dbTable.setBackground(Color.gray.darker().darker().darker());
		dbTable.setForeground(Color.white);
		dbTable.getTableHeader().setBackground(Color.gray.darker().darker().darker().darker());
		dbTable.getTableHeader().setForeground(Color.white);

		// --------------------JLabel--------------------
		JLabel commandLabel = new JLabel("SQL Commands:");
		JLabel queryLabel = new JLabel("Query:");
		JLabel statusLabel = new JLabel("Action Output: ");

		JLabel databaseWordInfo = new JLabel("Current Database: ");
		databaseWordInfo.setForeground(Color.green.darker());

		JLabel databaseInfo = new JLabel(database);
		databaseInfo.setForeground(Color.blue.darker());

		// --------------------JComboBox--------------------
		String[] options = { "SELECT", "SELECT * FROM ", "UPDATE", "DELETE FROM ", "INSERT INTO ", "ALTER TABLE ",
				"SHOW TABLES", "DROP TABLE " };
		JComboBox<String> commandList = new JComboBox<String>(options);

		commandList.setSelectedIndex(0);
		commandList.setEditable(false);

		// --------------------Scroll-Pane--------------------
		JScrollPane scroller = new JScrollPane(dbTable); // Creates a scroller for the Database Table
		scroller.setBackground(Color.gray.darker().darker().darker());
		JScrollPane scroller2 = new JScrollPane(statusField); // Creates a scroller for the Database Table
		scroller.setBackground(Color.gray.darker().darker().darker());

		// --------------------JPanels--------------------
		JPanel infoPanel = new JPanel(new FlowLayout()); // Rows x Columns
		JPanel queryPanel = new JPanel(new FlowLayout()); // Rows x Columns
		JPanel bPanel = new JPanel(new FlowLayout()); // Rows x Columns
		JPanel buttonPanel = new JPanel(new GridLayout(1, 1)); // Rows x Columns
		JPanel statusPanel = new JPanel(new FlowLayout());

		// ---------------------JMenuBar------------------
		JMenuBar dbMenuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription("File");
		dbMenuBar.add(fileMenu);

		JMenu historyMenu = new JMenu("History");
		historyMenu.setMnemonic(KeyEvent.VK_H);
		historyMenu.getAccessibleContext().setAccessibleDescription("Query History");
		dbMenuBar.add(historyMenu);

		JMenu tableMenu = new JMenu("Tables");
		tableMenu.setMnemonic(KeyEvent.VK_A);
		tableMenu.getAccessibleContext().setAccessibleDescription("About the Team");
		dbMenuBar.add(tableMenu);

		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_A);
		aboutMenu.getAccessibleContext().setAccessibleDescription("About the Team");
		dbMenuBar.add(aboutMenu);

		// ------------------JMenuitems-----------------
		JMenuItem sqlFileItem = new JMenuItem("Open SQL Query File...", KeyEvent.VK_N);
		sqlFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		sqlFileItem.getAccessibleContext()
				.setAccessibleDescription("Open a .sql file to be executed in the workbench.");

		fileMenu.add(sqlFileItem);

		// --------------------Action-Listener--------------------
		DatabaseActionListener listener = new DatabaseActionListener(databaseInfo, dtm, sqlFileItem, historyMenu,
				tableMenu, commandList, queryField);
		DatabaseDocListener docListener = new DatabaseDocListener(execute, queryField);

		// -------------------TableModelListener----------------

		dtm.addTableModelListener(new DatabaseTMListener());

		// --------------------Button-Action--------------------
		commandList.setActionCommand(DatabaseActionListener.COMMAND);
		execute.setActionCommand(DatabaseActionListener.EXECUTE);
		// save.setActionCommand(DatabaseActionListener.SAVE); //removed until further
		// notice
		clear.setActionCommand(DatabaseActionListener.CLEAR);
		logout.setActionCommand(DatabaseActionListener.LOGOUT);
		exit.setActionCommand(DatabaseActionListener.EXIT);
		sqlFileItem.setActionCommand(DatabaseActionListener.FILE);

		commandList.addActionListener(listener);
		execute.addActionListener(listener);
		// save.addActionListener(listener); //removed until further notice
		clear.addActionListener(listener);
		logout.addActionListener(listener);
		exit.addActionListener(listener);
		sqlFileItem.addActionListener(listener);

		queryField.getDocument().addDocumentListener(docListener);

		// --------------------Adding-Components--------------------

		// InfoPanel
		infoPanel.add(databaseWordInfo);
		infoPanel.add(databaseInfo);
		// infoPanel.add(usernameWordInfo);
		// infoPanel.add(usernameInfo);

		// Query Panel
		queryPanel.add(queryLabel);
		queryPanel.add(queryField);

		// B Panel
		bPanel.add(commandLabel);
		bPanel.add(commandList);

		// Button Panel
		buttonPanel.add(execute);
		// buttonPanel.add(save);
		buttonPanel.add(clear);
		buttonPanel.add(logout);
		buttonPanel.add(exit);

		// Status Panel
		statusPanel.add(statusLabel);
		statusPanel.add(scroller2);

		// Frame
		frame.add(infoPanel);
		frame.add(scroller);
		frame.add(bPanel);
		frame.add(queryPanel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(statusPanel, BorderLayout.SOUTH);

		// -------------------AddMenuBAr----------------
		frame.setJMenuBar(dbMenuBar);

		frame.validate(); // Validates frame and all its subcomponents
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Terminates program when the user exits the window
	}
}