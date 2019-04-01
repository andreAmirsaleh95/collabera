package com.collabera.jump.util;

public class EmployeeValidater {
	
	public static final int MIN_EMP_ID = 0;
	
	public static final int MAX_EMP_ID = 999999;
	
	public static boolean validateEmpId(int empId) {
		if (MIN_EMP_ID > empId || MAX_EMP_ID < empId) return false;
		return true;
	}
	
}
