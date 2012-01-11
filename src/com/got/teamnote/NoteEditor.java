package com.got.teamnote;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class NoteEditor extends Activity {
	
	private EditText text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_editor);
		findViews();
	}

	private void findViews() {
		text = (EditText)findViewById(R.id.note);
	}

}
