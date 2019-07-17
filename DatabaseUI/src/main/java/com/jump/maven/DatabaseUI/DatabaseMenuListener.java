package com.jump.maven.DatabaseUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.jump.maven.DatabaseUtils.DatabaseException;
import com.jump.maven.DatabaseUtils.DatabaseModel;

public class DatabaseMenuListener implements ActionListener
{

	private DefaultTableModel dbTableModel;
	private ResultSet result;

	public DatabaseMenuListener(DefaultTableModel dbtModel)
	{
		dbTableModel = dbtModel;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		execute(e.getActionCommand());
	}

	private void execute(String query)
	{
		// DatabaseModel.submit sends a query,
		// determines what type it is,
		// and returns a result if select statement
		try
		{
			result = DatabaseModel.submit(query);
		}
		catch (DatabaseException e)
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
				}
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				DatabaseUI.statusField.setText(DatabaseUI.statusField.getText() + "\n" + e1.getMessage());
				e1.printStackTrace();
			}
		}
		if (null != result)
		{
			try
			{
				result.close();
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "<html><font color=\"red\">"
						+ "Error occurred while trying to close resource." + "</font></html>");
				e.printStackTrace();
			}
		}
	}

}
