package com.example.gpa;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ClassDataSource {

		private SQLiteDatabase db;
		private DatabaseHandler dbHandler;
		
		private String[] columns = { DatabaseHandler.classID,
				DatabaseHandler.className, DatabaseHandler.classHours,
				DatabaseHandler.classGrade, DatabaseHandler.A,
				DatabaseHandler.A_minus, DatabaseHandler.B_plus,
				DatabaseHandler.B, DatabaseHandler.B_minus,
				DatabaseHandler.C_plus, DatabaseHandler.C,
				DatabaseHandler.C_minus, DatabaseHandler.D_plus,
				DatabaseHandler.D, DatabaseHandler.D_minus };
		
		public ClassDataSource(Context context) {
			dbHandler = new DatabaseHandler(context);
		}

		public void open() throws SQLException {
			db = dbHandler.getWritableDatabase();
		}

		public void close() {
			dbHandler.close();
		}

		public Class createClass(String name,int hours, int grade, 
				int a, int a_minus, int b_plus, int b,
				int b_minus, int c_plus, int c, int c_minus,
				int d_plus, int d,int d_minus) {
			
			ContentValues values = new ContentValues();
			values.put(DatabaseHandler.className, name);
			values.put(DatabaseHandler.classHours, hours);
			values.put(DatabaseHandler.classGrade, grade);
			values.put(DatabaseHandler.A, a);
			values.put(DatabaseHandler.A_minus, a_minus);
			values.put(DatabaseHandler.B_plus, b_plus);
			values.put(DatabaseHandler.B, b);
			values.put(DatabaseHandler.B_minus, b_minus);
			values.put(DatabaseHandler.C_plus, c_plus);
			values.put(DatabaseHandler.C, c);
			values.put(DatabaseHandler.C_minus, c_minus);
			values.put(DatabaseHandler.D_plus, d_plus);
			values.put(DatabaseHandler.D, d);
			values.put(DatabaseHandler.D_minus, d_minus);

			long insertId = db.insert(DatabaseHandler.classesTable, null,
					values);
			Log.i("class", "class # " + insertId + " added to database.");
			Cursor cursor = db.query(DatabaseHandler.classesTable, columns,
					DatabaseHandler.classID + " = " + insertId, null, null,
					null, null);

			cursor.moveToFirst();
			return cursorToClass(cursor);
		}
		
		public Class addClassToDatabase(Class c){
			ContentValues values = new ContentValues();
			values.put(DatabaseHandler.className, c.getName());
			values.put(DatabaseHandler.classHours, c.getHours());
			values.put(DatabaseHandler.classGrade, c.getGrade());
			values.put(DatabaseHandler.A, c.getScale().getA());
			values.put(DatabaseHandler.A_minus, c.getScale().getA_minus());
			values.put(DatabaseHandler.B_plus, c.getScale().getB_plus());
			values.put(DatabaseHandler.B, c.getScale().getB());
			values.put(DatabaseHandler.B_minus, c.getScale().getB_minus());
			values.put(DatabaseHandler.C_plus, c.getScale().getC_plus());
			values.put(DatabaseHandler.C, c.getScale().getC());
			values.put(DatabaseHandler.C_minus, c.getScale().getC_minus());
			values.put(DatabaseHandler.D_plus, c.getScale().getD_plus());
			values.put(DatabaseHandler.D, c.getScale().getD());
			values.put(DatabaseHandler.D_minus, c.getScale().getD_minus());
			
			long insertId = db.insert(DatabaseHandler.classesTable, null,
					values);
			
			Log.i("Class", "Class # " + insertId + " added to database.");
			Cursor cursor = db.query(DatabaseHandler.classesTable, columns,
					DatabaseHandler.classID + " = " + insertId, null, null,
					null, null);

			cursor.moveToFirst();
			return cursorToClass(cursor);
		}
		
		public void deleteClass(Class classObj) {
			int id = classObj.getId();
			Log.i("Class", "Class # " + id + " is deleted.");
			db.delete(DatabaseHandler.classesTable, DatabaseHandler.classID
					+ " = " + id, null);
		}
		
		public void deleteClass(int id) {
			Log.i("Class", "Class # " + id + " is deleted.");
			db.delete(DatabaseHandler.classesTable, DatabaseHandler.classID
					+ " = " + id, null);
		}

		public List<Class> getAllClasss() {
			List<Class> classes = new ArrayList<Class>();
			Cursor cursor = db.query(DatabaseHandler.classesTable, columns,
					null, null, null, null,
					DatabaseHandler.className);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				classes.add(cursorToClass(cursor));
				cursor.moveToNext();
			}
			cursor.close();
			return classes;

		}

		/**
		 * @param ClassID the id of the Class being returned
		 * @return the Class associated with the id
		 */
		public Class getClassByID(int ClassID){
			Class Class = new Class();
			Cursor cursor = db.query(DatabaseHandler.classesTable, columns,
					DatabaseHandler.classID + "=?", new String[]{ClassID+""}, null, null, null);
			cursor.moveToFirst();
			Class = cursorToClass(cursor);
			
			return Class;
		}
		 
		private Class cursorToClass(Cursor cursor) {
			Class classObj = new Class();
			classObj.setId(cursor.getInt(0));
			classObj.setName(cursor.getString(1));
			classObj.setHours(cursor.getInt(2));
			classObj.setGrade(cursor.getInt(3));
			classObj.getScale().setA(cursor.getInt(4));
			classObj.getScale().setA_minus(cursor.getInt(5));
			classObj.getScale().setB_plus(cursor.getInt(6));
			classObj.getScale().setB(cursor.getInt(7));
			classObj.getScale().setB_minus(cursor.getInt(8));
			classObj.getScale().setC_plus(cursor.getInt(9));
			classObj.getScale().setC(cursor.getInt(10));
			classObj.getScale().setC_minus(cursor.getInt(11));
			classObj.getScale().setD_plus(cursor.getInt(12));
			classObj.getScale().setD(cursor.getInt(13));
			classObj.getScale().setD_minus(cursor.getInt(14));

			return classObj;
		}
		
}
