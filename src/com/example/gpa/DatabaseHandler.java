package com.example.gpa;

public class DatabaseHandler {
	static final String dbName = "passOrFailDB";
	/**
	 * @param args
	 */
	
	private static final String ASSIGNMENT_TYPES_TABLE_CREATE = 
			"CREATE TABLE " + courseTable + 
			" (" +
			colTypeID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			colTypeCourseID + " INTEGER NOT NULL," +
			colWeight + " INTEGER NOT NULL, " +
			colName + " TEXT NOT NULL" +
			");";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
