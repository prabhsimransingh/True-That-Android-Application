package com.example.ourapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Welcome extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void createTask(View v) {
		Intent i = new Intent(Welcome.this, CreateTask.class);
		startActivity(i);
	}

	/*public void completeTask(View v) {
		//Then in the new Activity, retrieve those values:
	
		Intent i = new Intent(Welcome.this, CompleteTask.class);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    String title = extras.getString("TaskTitle");
		    String location = extras.getString("TaskLocation");
		    i.putExtra("TaskTitle",title);
			i.putExtra("TaskLocation",location);
		}
		
		startActivity(i);
	}
*/
	public void viewTasks(View v) {
		Intent i = new Intent(Welcome.this, ViewTasks.class);
		startActivity(i);
	}
	public void viewcompletedTasks(View v) {
		Intent i = new Intent(Welcome.this, ListCompletedTasks.class);
		startActivity(i);
	}
	
}
