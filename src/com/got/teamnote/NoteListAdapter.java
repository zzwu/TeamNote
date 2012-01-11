package com.got.teamnote;

import java.util.List;

import com.got.teamnote.domain.Note;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoteListAdapter extends BaseAdapter {
	
	private Context context;
	private List<Note> notes;
	
	public NoteListAdapter(Context context, List<Note> notes) {
		this.context = context;
		this.notes = notes;
	}

	public int getCount() {
		return this.notes.size();
	}

	public Object getItem(int location) {
		return this.notes.get(location);
	}

	public long getItemId(int location) {
		return location;
	}

	public View getView(int location, View convertView, ViewGroup parent) {
		return new NoteItemView(context, notes.get(location));
	}
	
	private class NoteItemView extends LinearLayout {
		
		private TextView titleView;
		private TextView contentView;

		public NoteItemView(Context context, Note note) {
			super(context);
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(5, 3, 5, 0);
			
			titleView = new TextView(context);
			titleView.setText(note.getTitle());
			titleView.setTextSize(16f);
			titleView.setTextColor(Color.WHITE);
			this.addView(titleView, params);
			
			contentView = new TextView(context);
			contentView.setText(note.getContent());
			contentView.setTextSize(16f);
			contentView.setTextColor(Color.GRAY);
			this.addView(titleView, params);
			
		}
		
	}

}
