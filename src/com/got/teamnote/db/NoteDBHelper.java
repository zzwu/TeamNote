package com.got.teamnote.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.got.teamnote.domain.Note;
import com.got.teamnote.utils.Constants;

/**
 * note存取数据库辅助工具。
 * @author zzwu
 *
 */
public class NoteDBHelper {
	
	private SQLiteDatabase db;
	private final NoteDBOpenHelper dbOpenHelper;

	private static final String NOTE_TABLE_NAME = "note";
		
	public NoteDBHelper(Context context) {
		this.dbOpenHelper = new NoteDBOpenHelper(context, Constants.DB_NAME, Constants.VERSION);
		this.establishDb();
	}
	
	private void establishDb() {
		if (null == this.db)  db = dbOpenHelper.getWritableDatabase();
	}

	public void cleanUp() {
		if (null != this.db) {
			this.db.close();
			this.db = null;
		}
	}
	
	public long insert(Note note) {
		ContentValues values = new ContentValues();
		values.put("title", note.getTitle());
		values.put("content", note.getContent());
		values.put("status", note.getStatus());
		values.put("operator", note.getOperatorName());
		return this.db.insert(NOTE_TABLE_NAME, null, values);
	}
	
	public int update(Note note) {
		ContentValues values = new ContentValues();
		values.put("title", note.getTitle());
		values.put("content", note.getContent());
		values.put("status", note.getStatus());
		values.put("operator", note.getOperatorName());
		return db.update(NOTE_TABLE_NAME, values, "_id=?", new String[] {note.getId() + ""});
	}
	
	public void deleteNoteById(long id) {
		db.delete(NOTE_TABLE_NAME, "_id=" + id, null);
	}
	
	public Note getNoteById(long id) {
		Cursor c = db.query(NOTE_TABLE_NAME, new String[] {"_id", "title", "content", "createdDate", "lastModified"}, "_id=" + id, null, null, null, null);
		if (c.moveToFirst()) {
			Note n = new Note();
			n.setId(c.getInt(0));
			n.setTitle(c.getString(1));
			n.setContent(c.getString(2));
			n.setStatus(c.getInt(3));
			n.setOperatorName(c.getString(4));
			return n;
		}
		return null;
	}
	
	public List<Note> getAllNotes() {
		Cursor c = db.query(NOTE_TABLE_NAME, new String[] {"_id", "title", "content", "createdDate", "lastModified"}, null, null, null, null, null);
		List<Note> notes = new ArrayList<Note>();
		while (c.moveToNext()) {
			Note n = new Note();
			n.setId(c.getInt(0));
			n.setTitle(c.getString(1));
			n.setContent(c.getString(2));
			n.setStatus(c.getInt(3));
			n.setOperatorName(c.getString(4));
			notes.add(n);
		}
		return notes;
	}
	
	/**
	 * 数据库创建辅助工具。
	 * @author zzwu
	 *
	 */
	class NoteDBOpenHelper extends SQLiteOpenHelper {
		private static final String CREATE_TABLE_SQL = "create table " 
			+ NOTE_TABLE_NAME 
			+ " (_id integer primary key, "
			+ " title TEXT,"
			+ " content TEXT,"
			+ " createdDate INTEGER,"
			+ " lastModified INTEGER);";

		public NoteDBOpenHelper(Context context, String name, int version) {
			super(context, name, null, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_SQL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists " + NOTE_TABLE_NAME);
			this.onCreate(db);
		}
	}

}
