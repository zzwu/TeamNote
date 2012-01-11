package com.got.teamnote.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.got.teamnote.domain.User;
import com.got.teamnote.utils.Constants;

public class UserDBHelper {
	
	public static final String USER_TABLE_NAME = "user";
	
	private SQLiteDatabase db;
	private final UserDBOpenHelper userDBOpenHelper;
	
	public UserDBHelper(Context context) {
		this.userDBOpenHelper = new UserDBOpenHelper(context, Constants.DB_NAME, Constants.VERSION);
		this.establishDb();
	}
	
	private void establishDb() {
		if (null == this.db)  db = userDBOpenHelper.getWritableDatabase();
	}
	
	public void cleanUp() {
		if (null != this.db) {
			this.db.close();
			this.db = null;
		}
	}
	
	public long insert(User u) {
		ContentValues values = new ContentValues();
		values.put("username",u.getUsername());
		values.put("sid", u.getSid());
		values.put("email", u.getEmail());
		return this.db.insert(USER_TABLE_NAME, null, values);
	}
	
	public User getUser() {
		Cursor c = db.query(USER_TABLE_NAME, new String[] {"username, sid"}, null, null, null, null, null);
		if (c.moveToFirst()) {
			User u = new User();
			u.setUsername(c.getString(0));
			u.setSid(c.getString(1));
			return u;
		}
		return null;
	}
	
	public class UserDBOpenHelper extends SQLiteOpenHelper {
		
		public UserDBOpenHelper(Context context, String name,
			int version) {
			super(context, name, null, version);
		}

		public static final String CREATE_TABLE_SQL = "create table "
				+ USER_TABLE_NAME
				+ " (_id integer primary key,"
				+ "username TEXT,"
				+ "sid TEXT"
				+ "email TEXT)";

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_SQL);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists " + USER_TABLE_NAME);
			this.onCreate(db);
		}
		
	}

}
