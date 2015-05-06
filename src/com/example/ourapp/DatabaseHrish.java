package com.example.ourapp;

import java.util.ArrayList;
import java.util.List;

import com.example.ourapp.User2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHrish extends SQLiteOpenHelper {
	// All Static variables
		// Database Version
		private static final int DATABASE_VERSION = 1;

		// Database Name
		private static final String DATABASE_NAME = "contactsManager";

		// Contacts table name
		private static final String TABLE_CONTACTS = "contacts";

		// Contacts Table Columns names
		private static final String KEY_ID = "id";
		private static final String KEY_NAME = "name";
		private static final String KEY_PH_NO = "phone_number";
		private static final String KEY_EMAIL = "email";
		private static final String KEY_PASSWORD="password";

		public DatabaseHrish(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// Creating Tables
		@Override
		public void onCreate(SQLiteDatabase db) {
			String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "("
					+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
					+ KEY_PH_NO + " TEXT," + KEY_EMAIL + " TEXT,"+ KEY_PASSWORD + " TEXT" + ")";
			db.execSQL(CREATE_CONTACTS_TABLE);
		}

		// Upgrading database
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Drop older table if existed
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

			// Create tables again
			onCreate(db);
		}

		/**
		 * All CRUD(Create, Read, Update, Delete) Operations
		 */

		// Adding new contact
		void addContact(User2 contact) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_NAME, contact.getName()); // Contact Name
			values.put(KEY_PH_NO, contact.getPhone_number()); // Contact Phone
			values.put(KEY_EMAIL, contact.getEmail()); // Contact Email
			values.put(KEY_PASSWORD, contact.getPassword()); // Contact Phone

			// Inserting Row
			db.insert(TABLE_CONTACTS, null, values);
			db.close(); // Closing database connection
		}

		// Getting single contact
		User2 getContact(int id) {
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
					KEY_NAME, KEY_PH_NO, KEY_EMAIL, KEY_PASSWORD }, KEY_ID + "=?",
					new String[] { String.valueOf(id) }, null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();

			User2 contact = new User2(Integer.parseInt(cursor.getString(0)),
					cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));
			// return contact
			return contact;
		}
		User2 getContact(String name) {
			SQLiteDatabase db = this.getReadableDatabase();

			Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
					KEY_NAME, KEY_PH_NO, KEY_EMAIL, KEY_PASSWORD }, KEY_NAME + "=?",
					new String[] { String.valueOf(name) }, null, null, null, null);
			if (cursor != null)
				cursor.moveToFirst();
			if(cursor.getCount()!=0){
			User2 contact = new User2(Integer.parseInt(cursor.getString(0)),
					cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4));
			// return contact
			return contact;}
			else return null;
		}

		// Getting All Contacts
		public List<User2> getAllContacts() {
			List<User2> contactList = new ArrayList<User2>();
			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					User2 contact = new User2();
					contact.setId(Integer.parseInt(cursor.getString(0)));
					contact.setName(cursor.getString(1));
					contact.setPhone_number(cursor.getString(2));
					contact.setEmail(cursor.getString(3));
					contact.setPassword(cursor.getString(4));
					// Adding contact to list
					contactList.add(contact);
				} while (cursor.moveToNext());
			}

			// return contact list
			return contactList;
		}

		// Updating single contact
		public int updateContact(User2 contact) {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(KEY_NAME, contact.getName());
			values.put(KEY_PH_NO, contact.getPhone_number());
			values.put(KEY_EMAIL, contact.getEmail()); // Contact Email
			values.put(KEY_PASSWORD, contact.getPassword()); // Contact Phone

			// updating row
			return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
					new String[] { String.valueOf(contact.getId()) });
		}

		// Deleting single contact
		public void deleteContact(User2 contact) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
					new String[] { String.valueOf(contact.getId()) });
			db.close();
		}

		// Getting contacts Count
		public int getContactsCount() {
			String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery(countQuery, null);
			cursor.close();

			// return count
			return cursor.getCount();
		}
}
