package com.got.teamnote;

import java.util.List;

import com.got.teamnote.db.NoteDBHelper;
import com.got.teamnote.domain.Note;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class NotesList extends ListActivity {
	
	private TextView empty;
	private ProgressDialog progressDialog;
	private NoteDBHelper noteDBHelper;
	
	private static final int ADD_NOTE_MENU_ID = 0;
	
	private Handler hanlder = new Handler() {
		public void handleMessage(android.os.Message msg) {
			List<Note> notes = noteDBHelper.getAllNotes();
			progressDialog.dismiss();
			if (null == notes || notes.size() == 0) {
				empty.setText("还没有被记录的便签,赶快添加便签吧~");
			} else {
				//DOTO 用便签适配器显示便签
				NoteListAdapter adapter = new NoteListAdapter(NotesList.this, notes);
				setListAdapter(adapter);
			}
		};
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ADD_NOTE_MENU_ID, 0, R.string.menu_insert)
			.setShortcut('3', 'a')
			.setIcon(android.R.drawable.ic_menu_add);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		super.onMenuItemSelected(featureId, item);
		switch (item.getItemId()) {
		case ADD_NOTE_MENU_ID:
			startActivity(new Intent(this, NoteEditor.class));
			break;
		}
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.notes_list);

        this.empty = (TextView) findViewById(R.id.empty);

        final ListView listView = getListView();
        listView.setItemsCanFocus(false);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setEmptyView(this.empty);
        
        progressDialog = ProgressDialog.show(this, "progress", "loadding...");
        noteDBHelper = new NoteDBHelper(this);
        
        hanlder.sendEmptyMessage(0);
	}
	
	
}
