package com.got.teamnote;

import java.util.HashMap;
import java.util.Map;

import com.got.teamnote.utils.Constants;
import com.got.teamnote.utils.HttpUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 注册界面
 * @author Administrator
 *
 */
public class RegisterActivity extends Activity {
	
	private EditText usernameText;
	private EditText passwordText;
	private EditText emailText;
	private Button doRegisterBtn;
	private static final String registerUrl = Constants.MAC_HOST + "UserAction.register";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.register);
		findeViews();
		doRegisterBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				String username = usernameText.getText().toString();
				String password = passwordText.getText().toString();
				String email = emailText.getText().toString();
				Map<String, String> params = new HashMap<String, String>();
				params.put("username", username);
				params.put("password", password);
				params.put("email", email);
				String json = HttpUtils.post(registerUrl, params);
				Toast.makeText(RegisterActivity.this, json, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void findeViews() {
		usernameText = (EditText)this.findViewById(R.id.registerUsernameEditor);
		passwordText = (EditText)this.findViewById(R.id.registerPswEditor);
		emailText = (EditText)this.findViewById(R.id.registerEmailEditor);
		doRegisterBtn = (Button)this.findViewById(R.id.registerDoBtn);
	}

	
	
}
