package com.got.teamnote;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.got.teamnote.utils.Constants;
import com.got.teamnote.utils.HttpUtils;

public class TeamNoteLogin extends Activity {

	private String TAG = "TeamNoteLogin";
	private EditText usernameEdit;
	private EditText passwordEdit;
	private Button loginBtn;
	private Button registerBtn;
	
	private static final String LOGIN_URL = Constants.MAC_HOST + "UserAction.login?";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        findViews();
        
        loginBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(TAG, "TeamNoteLogin loginBtn click.");
				String user = usernameEdit.getText().toString();
				String password = passwordEdit.getText().toString();
				Map<String, String> params =  new HashMap<String, String>();
				params.put("user", user);
				params.put("password", password);
				String json = HttpUtils.post(LOGIN_URL, params);
				Toast.makeText(TeamNoteLogin.this, json, Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(TeamNoteLogin.this, NotesList.class);
				TeamNoteLogin.this.startActivity(intent);
			}
		});
        
        registerBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i(TAG, "TeamNoteLogin registerBtn click.");
				Intent intent = new Intent(TeamNoteLogin.this, RegisterActivity.class);
				TeamNoteLogin.this.startActivity(intent);
			}
		});
        
    }

	private void findViews() {
		usernameEdit = (EditText)this.findViewById(R.id.usernameEdit);
		passwordEdit = (EditText)this.findViewById(R.id.passwordEdit);
		loginBtn = (Button)this.findViewById(R.id.loginBtn);
		registerBtn = (Button)this.findViewById(R.id.loginRegisterBtn);
	}
    
}