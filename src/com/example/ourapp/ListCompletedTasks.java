package com.example.ourapp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ListCompletedTasks extends Activity {
	LinearLayout linear;
	DatabaseHandler db = new DatabaseHandler(this);
	private static int tagN = 1;
	private ArrayList<Button> myArray = new ArrayList<Button>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_completed_tasks);
		test();

	}

	@Override
	protected void onResume() {
		super.onResume();
		for (Button b : myArray) {
			b.setBackgroundResource(R.drawable.buttonshape);
		}
	}

	private void test() {
		HashSet taskList = new HashSet();
		taskList = db.getCompletedTasks();
		System.out.println("view" + taskList);
		linear = (LinearLayout) findViewById(R.id.linear);
		Iterator itr = taskList.iterator();
		// System.out.println("null check :: "+itr.next());
		while (itr.hasNext()) {
			Button btnWord = new Button(this);
			btnWord.setTag(tagN++);
			btnWord.setId(tagN);
			btnWord.setText((String) itr.next());
			btnWord.setOnClickListener(btnClicked);
			linear.addView(btnWord);
			myArray.add((Button) findViewById(tagN));
		}
		System.out.println("1");
	}

	/*
	 * private void test() {
	 * 
	 * //DatabaseHandler db = new DatabaseHandler(this); ArrayList taskList =
	 * new ArrayList(); //taskList = db.getTasks(); taskList.add("word1");
	 * taskList.add("word2"); taskList.add("word3"); taskList.add("word4");
	 * linear = (LinearLayout) findViewById(R.id.linear); for (int i = 0; i <
	 * taskList.size(); i++) { Button btnWord = new Button(this);
	 * btnWord.setTag(i); btnWord.setText("Button::"+i);
	 * btnWord.setOnClickListener(btnClicked); linear.addView(btnWord); } }
	 */

	OnClickListener btnClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			System.out.println("2");
			Object tag = v.getTag();
			String value = (String) ((Button) v).getText();
			System.out.println("Value is :: " + value);
			TaskPOJO currTask = db.getTaskFromName(value);
			System.out.println("3");
			Intent i = new Intent(getApplicationContext(),
					ViewCompletedTask.class);
			i.putExtra("TASK_ID", currTask.getId());
			System.out.println("4");
			startActivity(i);

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	/*
	 * public void home(View v) { Intent i = new Intent(ViewTasks.this,
	 * Welcome.class); startActivity(i); }
	 */
}
