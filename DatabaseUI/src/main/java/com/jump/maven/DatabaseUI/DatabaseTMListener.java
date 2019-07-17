package com.jump.maven.DatabaseUI;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class DatabaseTMListener implements TableModelListener
{

	@Override
	public void tableChanged(TableModelEvent e)
	{
		int col = e.getColumn();
		int row = e.getFirstRow();
//		System.out.println("Cell (" + row + ", " + col + ") changed to "
//				+ ((DefaultTableModel) e.getSource()).getValueAt(row, col));
//		System.out.println("Cell (" + row + ", " + col + ") changed.");
	}

}
