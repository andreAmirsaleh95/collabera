package com.jump.maven.DatabaseUI;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DatabaseDocListener implements DocumentListener
{
	private JButton connect;
	private ArrayList<JTextField> fieldList = new ArrayList<>();
	
	public DatabaseDocListener(JButton connect,ArrayList<JTextField> fieldList)
	{
		this.connect = connect;
		this.fieldList = fieldList;
	}
	public DatabaseDocListener(JButton connect,JTextField textfield)
	{
		this.connect = connect;
		fieldList.add(textfield);
	}
    public void changedUpdate(DocumentEvent e)
    {
    	checkFieldsFull();
    }
 
    public void insertUpdate(DocumentEvent e)
    {
    	checkFieldsFull();
    }
 
    public void removeUpdate(DocumentEvent e)
    {
    	checkFieldsFull();
    }
    
	private void checkFieldsFull()
	{
		for (JTextField field : fieldList) 
		{
			if (field.getText().trim().isEmpty()) 
			{
				connect.setEnabled(false);
				return;
			}
		}
		connect.setEnabled(true);
	}

}
