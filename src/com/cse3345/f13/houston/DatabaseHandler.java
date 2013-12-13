package com.cse3345.f13.houston;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
        static final String dbName = "GPADB";
        private static final int DATABASE_VERSION = 1;

        static final String classesTable = "Classes";
        static final String classID = "_id";
        static final String className = "name";
        static final String classGrade = "grade";
        static final String classHours = "hours";
        static final String A = "A";
        static final String A_minus = "A_minus";
        static final String B_plus = "B_plus";
        static final String B = "B";
        static final String B_minus = "B_minus";
        static final String C_plus = "C_plus";
        static final String C = "C";
        static final String C_minus = "C_minus";
        static final String D_plus = "D_plus";
        static final String D = "D";
        static final String D_minus = "D_minus";
        
        private static final String CLASS_TABLE_CREATE = 
                        "CREATE TABLE " + classesTable + 
                        " (" +classID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        className + " TEXT NOT NULL," +
                        classGrade + " INTEGER NOT NULL, " +
                        classHours + " INTEGER NOT NULL," +
                        A + " INTEGER NOT NULL," +
                        A_minus + " INTEGER, " +
                        B_plus + " INTEGER, " +
                        B + " INTEGER NOT NULL, " +
                        B_minus + " INTEGER, " +
                        C_plus + " INTEGER, " +
                        C + " INTEGER NOT NULL," +
                        C_minus + " INTEGER, " +
                        D_plus + " INTEGER, " +
                        D + " INTEGER NOT NULL, " +
                        D_minus + " INTEGER " +
                        ");";
        
        public DatabaseHandler(Context context) {
                super(context, dbName, null, DATABASE_VERSION);
        }
        
        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL(CLASS_TABLE_CREATE);
        }
        
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                Log.w(DatabaseHandler.class.getName(),
                                "Upgrading database from version " + oldVersion + " to "
                                                + newVersion + ", which will destroy all old data");
                db.execSQL("DROP TABLE IF EXISTS" + classesTable);
                onCreate(db);
                
        }

}