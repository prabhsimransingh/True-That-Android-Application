package com.example.ourapp;

import com.example.ourapp.DatabaseHrish;
import com.example.ourapp.User2;
import com.example.ourapp.Welcome;
import com.example.ourapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageView im=null;
	EditText tv1,tv4;
	boolean flag=false;
	DatabaseHrish dbb = new DatabaseHrish(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv1=(EditText)findViewById(R.id.et_uname);
		tv4=(EditText)findViewById(R.id.et_password);
		dbb.addContact(new User2("admin","1234567890","email@email.com","123456789"));
		dbb.addContact(new User2("admin2","1","hsaboo@andrew.cmu.edu","1"));
		dbb.addContact(new User2("admin3","1234567890","hsaboo@andrew.cmu.edu","1234567"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void login(View v) {
		
		String user_name=tv1.getText().toString();
		String password=tv4.getText().toString();
		if(user_name==null||user_name=="")
		{
			show("Please Enter Correct user name.");
		}
		else if(password==null||password==""||password.length()<6)
		{
			show("Please Enter Correct Password.");
		}
		else
		{		
			
			User2 c=dbb.getContact(user_name);
			if(c!=null && c.getName().equalsIgnoreCase(user_name)&& (c.getPassword().equals(password)))
			{
				Intent i = new Intent(MainActivity.this, Welcome.class);
				startActivity(i);
			//i=new Intent(this,Welcome.class);
			//startActivityForResult(i,500);
			 
			dbb.close();
			finish();
			}
			else
				show("Wrong Username or Password .");
			
		}
		
	}
	public void show(String str)
	{
	Toast.makeText(this, str, Toast.LENGTH_LONG).show();	
	}
	
}
