package com.example.ourapp;

import java.io.File;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CompleteTask extends Activity {

	private static int RESULT_LOAD_IMG = 1;
	String imgDecodableString;
	DatabaseHrish hb = new DatabaseHrish(this);
	ImageView imgFavorite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complete_task);
		imgFavorite = (ImageView) findViewById(R.id.imageView1);
		imgFavorite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				open();
			}
		});

		Button buttonLoadPicture = (Button) findViewById(R.id.buttonLoadPicture);
		buttonLoadPicture.setOnClickListener(new OnClickListener() {
			public void onClick(View viewParam) {
				loadImagefromGallery(viewParam);
			}
		});
	}

	public void open() {
	Intent intent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, 0);
		
	}

	public void loadImagefromGallery(View view) {
		// Create intent to Open Image applications like Gallery, Google Photos
		Intent galleryIntent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		// Start the Intent
		startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		DatabaseHandler db = new DatabaseHandler(this);

		super.onActivityResult(requestCode, resultCode, data);
		try {

			// When an Image is picked
			if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
					&& null != data) {
				// Get the Image from data

				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				// Get the cursor
				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				// Move to first row
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				imgDecodableString = cursor.getString(columnIndex);
				System.out.println("The load image string :: "+imgDecodableString);
				cursor.close();
				System.out.println("here i am");
				Bundle extras = getIntent().getExtras();
				String id = null;
				if (extras != null) {
					id = extras.get("TASK_ID").toString();
				}

				TaskPOJO currTask = db.getTask(Integer.parseInt(id));

				// TaskPOJO task = new TaskPOJO(currTask.getId(),
				// currTask.getName(), imgDecodableString, currTask.getUserid(),
				// currTask.getLocation());
				TaskPOJO task = new TaskPOJO(currTask.getId(),

				currTask.getName(), imgDecodableString, 1,
						currTask.getLocation());
				db.updateTask(task);
				System.out.println("Mailing1");
				SendMail sm = new SendMail();
				sm.search(this, "pritishkamath1310@gmail.com");
				Toast.makeText(this, "Task Completed",
						Toast.LENGTH_LONG).show();
				Intent i = new Intent(CompleteTask.this, Welcome.class);
				startActivity(i);
				
			} else if (requestCode == 0 && resultCode == RESULT_OK
					&& null != data) {
				// prabhsis
				Bitmap bp = (Bitmap) data.getExtras().get("data");
				imgFavorite.setImageBitmap(bp);
				Toast.makeText(this, "Task Completed",
						Toast.LENGTH_LONG).show();
				Intent i = new Intent(CompleteTask.this, Welcome.class);
				startActivity(i);
				
			} else {
				Toast.makeText(this, "You haven't picked Image",
						Toast.LENGTH_LONG).show();
			}

		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
					.show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.menu_complete_task, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		// noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void home(View v) {
		Intent i = new Intent(CompleteTask.this, Welcome.class);
		startActivity(i);
	}
}
