package com.example.ourapp;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateTask extends Activity {
	
	private static int taskId = 0;
	private static int userId = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_task);
		
		Button submitButton = (Button)findViewById(R.id.button_createTask_submit);
		final DatabaseHandler data = new DatabaseHandler(this);
		submitButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View viewParam) {
        		Random randomGen=new Random();
        		
        		taskId=randomGen.nextInt(30000);
        		userId=randomGen.nextInt(30000);
        		// Getting the movie searched for
        		TaskPOJO task = new TaskPOJO(++taskId, ((EditText)findViewById(R.id.et_createTask_what)).getText().toString(), 
        				null, ++userId, (""));
        		data.addTask(task);
        		Toast.makeText(CreateTask.this, "Task Created",
						Toast.LENGTH_LONG).show();
        		Intent i = new Intent(CreateTask.this, Welcome.class);
        		i.putExtra("TaskTitle",((EditText)findViewById(R.id.et_createTask_what)).getText().toString());
        		i.putExtra("TaskLocation",(""));
        		startActivity(i);
        	


        		
        	}
        });
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_task, menu);
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

	public void home(View v) {
		Intent i = new Intent(CreateTask.this, Welcome.class);
		startActivity(i);
	}
}
