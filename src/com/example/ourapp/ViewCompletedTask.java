package com.example.ourapp;



import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class ViewCompletedTask extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_completed_task);
		 Bundle extras = getIntent().getExtras();
         String id=null;
         if (extras != null) {
             id = extras.get("TASK_ID").toString();
         }
         DatabaseHandler db = new DatabaseHandler(this);
         TaskPOJO task = db.getTask(Integer.parseInt(id));
         ImageView imgView = (ImageView) findViewById(R.id.imgView);
         // Set the Image in ImageView after decoding the String
         imgView.setImageBitmap(BitmapFactory
                 .decodeFile(task.getImage()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_completed_task, menu);
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
}
