package com.jump.maven.DatabaseUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Login UI Window for StagBench GUI
 * 
 * @author Walter Salmeron
 * @author Brett Aiken
 * @author Braxton Harris
 */

public class LoginUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	public static JFrame frame;

	public static void loginUI()
	{
		// Frame
		frame = new JFrame("Stag Bench (Beta)");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 620);
		frame.setLocation(350, 50);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());

		// --------------------JLabels--------------------
		JLabel hostLabel = new JLabel("              Host:");
		JLabel dbLabel = new JLabel("              Database:");
		JLabel usernameLabel = new JLabel("              Username:");
		JLabel passwordLabel = new JLabel("              Password:");
		JLabel dbOpLabel = new JLabel("DB URL's: ");

		// --------------------JTextFields--------------------
		JTextField hostField = new JTextField(14);
		JTextField dbField = new JTextField(14);
		JTextField usernameField = new JTextField(14);
		JPasswordField passwordField = new JPasswordField(14);

		ArrayList<JTextField> fieldList = new ArrayList<JTextField>();

		fieldList.add(hostField);
		fieldList.add(dbField);
		fieldList.add(usernameField);
		fieldList.add(passwordField);

		// --------------------JButtons--------------------
		JButton connect = new JButton("Connect");
		JButton exit = new JButton("Exit");
		connect.setIcon(new ImageIcon("stagbutton.png"));
		connect.setIconTextGap(0);

		exit.setIcon(new ImageIcon("stagexit.png"));
		exit.setIconTextGap(61);

		connect.setEnabled(false);
		// --------------------JPanels--------------------
		JPanel topPanel = new JPanel();
		JPanel fieldPanel = new JPanel(new GridLayout(2, 2));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

		// --------------------JComboBox--------------------
		String[] dbOptions = { "MySQL", "PostegreSQL", "SQLite", "Oracle" };

		JComboBox<String> dbList = new JComboBox<String>(dbOptions);

		dbList.setSelectedIndex(0);
		dbList.setEditable(false);

		// --------------------Action-Listener--------------------
		DatabaseActionListener listener = new DatabaseActionListener(dbList, hostField, dbField, usernameField,
				passwordField);
		DatabaseDocListener docListener = new DatabaseDocListener(connect, fieldList);

		// --------------------Button-Action--------------------
		connect.setActionCommand(DatabaseActionListener.CONNECT);
		exit.setActionCommand(DatabaseActionListener.EXIT);
		connect.addActionListener(listener);
		exit.addActionListener(listener);

		hostField.getDocument().addDocumentListener(docListener);
		dbField.getDocument().addDocumentListener(docListener);
		usernameField.getDocument().addDocumentListener(docListener);
		passwordField.getDocument().addDocumentListener(docListener);

		// Image(s)
		ImageIcon icon = new ImageIcon("stag.PNG");
		JLabel image = new JLabel(icon);
		image.setIcon(icon);
		image.setVerticalAlignment(SwingConstants.BOTTOM);

		// --------------------Populate-------------------
		topPanel.add(dbOpLabel);
		topPanel.add(dbList);

		fieldPanel.add(hostLabel);
		fieldPanel.add(hostField);
		fieldPanel.add(dbLabel);
		fieldPanel.add(dbField);
		fieldPanel.add(usernameLabel);
		fieldPanel.add(usernameField);
		fieldPanel.add(passwordLabel);
		fieldPanel.add(passwordField);

		// ------------------Populate---------------------
		buttonPanel.add(connect);
		buttonPanel.add(exit);

		frame.add(topPanel, BorderLayout.WEST);
		frame.add(fieldPanel);
		frame.add(image);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		frame.validate();
	}
}