package com.example.gpa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	static final String dbName = "GPADB";
	private static final int DATABASE_VERSION = 1;

	/**
	 * @param args
	 */
	
	/*private static final String ASSIGNMENT_TYPES_TABLE_CREATE = 
			"CREATE TABLE " + courseTable + 
			" (" +
			colTypeID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			colTypeCourseID + " INTEGER NOT NULL," +
			colWeight + " INTEGER NOT NULL, " +
			colName + " TEXT NOT NULL" +
			");";*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public DatabaseHandler(Context context) {
		super(context, dbName, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Create Courses Table
		//db.execSQL(COURSE_TABLE_CREATE);
		
		//  Create Assignment Types Table
		//db.execSQL(ASSIGNMENT_TYPES_TABLE_CREATE);
		// TODO Create Grades Table
		//db.execSQL(GRADES_TABLE_CREATE);
				
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHandler.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		//db.execSQL("DROP TABLE IF EXISTS" + coursesTable);
		//db.execSQL("DROP TABLE IF EXISTS" + assignmentTypesTable);
		//db.execSQL("DROP TABLE IF EXISTS" + gradesTable);
		onCreate(db);
		
	}

}
