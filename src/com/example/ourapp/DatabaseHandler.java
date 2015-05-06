package com.example.ourapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "truethatapp";

	// Contacts table name
	private static final String TASK_COMPLETE = "taskcomplete";

	// Contacts Table Columns names
	private static final String TASK_ID = "taskId";
	private static final String TASK_NAME = "name";
	private static final String IMAGE = "image";
	private static final String USER_ID = "userId";
	private static final String LOCATION = "location";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TASK_COMPLETE_TABLE = "CREATE TABLE IF NOT EXISTS " + TASK_COMPLETE + "("
				+ TASK_ID + " INTEGER PRIMARY KEY," + TASK_NAME + " TEXT,"
				+ IMAGE + " TEXT," + USER_ID + " TEXT," + LOCATION + " TEXT"+ ")";
		db.execSQL(CREATE_TASK_COMPLETE_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TASK_COMPLETE);
		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addTask(TaskPOJO task) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TASK_ID, task.getId()); // Task Name
		values.put(TASK_NAME, task.getName()); // Task Name
		values.put(IMAGE, task.getImage()); // Task Image
		values.put(USER_ID, task.getUserid()); // User ID
		values.put(LOCATION, task.getLocation()); // Location

		// Inserting Row
		db.insert(TASK_COMPLETE, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	TaskPOJO getTask(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TASK_COMPLETE, new String[] { TASK_ID,
				TASK_NAME, IMAGE, USER_ID }, TASK_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		TaskPOJO pojo = new TaskPOJO(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)), "Pittsburgh");
		// return contact
		return pojo;
	}
	// Updating single contact
	public HashSet getCompletedTasks() {
		String query = "SELECT "+ TASK_NAME +" FROM " + TASK_COMPLETE + " WHERE " + IMAGE + " IS NOT NULL";
	    HashSet list = new HashSet();
	    SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
	    if (cursor.moveToFirst()) {
	        do {
	        	System.out.println("here"+cursor.getString(0));
	            list.add(cursor.getString(0));
	        } while (cursor.moveToNext());
	    }
	    if (cursor != null && !cursor.isClosed()) {
	        cursor.close();
	    }
	    return list;
	}
	
	public int updateTask(TaskPOJO task) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(TASK_NAME, task.getName());
		values.put(IMAGE, task.getImage());
		values.put(USER_ID, task.getUserid());
		values.put(LOCATION, task.getLocation());

		// updating row
		return db.update(TASK_COMPLETE, values, TASK_ID + " = ?",
				new String[] { String.valueOf(task.getId()) });
	}

	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TASK_COMPLETE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	public HashSet getTasks() {
		String query = "SELECT "+ TASK_NAME +" FROM " + TASK_COMPLETE;
	    HashSet list = new HashSet();
	    SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
	    if (cursor.moveToFirst()) {
	        do {
	        	System.out.println("here"+cursor.getString(0));
	            list.add(cursor.getString(0));
	        } while (cursor.moveToNext());
	    }
	    if (cursor != null && !cursor.isClosed()) {
	        cursor.close();
	    }
	    return list;
	}
	TaskPOJO getTaskFromName(String name) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TASK_COMPLETE, new String[] { TASK_ID,
				TASK_NAME, IMAGE, USER_ID, LOCATION }, TASK_NAME + "=?",
				new String[] { String.valueOf(name) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		TaskPOJO pojo = new TaskPOJO(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), 
				Integer.parseInt(cursor.getString(3)), cursor.getString(4));
		// return contact
		return pojo;
	}
}