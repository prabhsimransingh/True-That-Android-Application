package com.example.ourapp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

/**
 * 
 * @author Pritish Kamath
 *
 */
public class SendMail {
	CompleteTask activity = null;
	
	/**
	 * This method call the Asynchronous search for movie details
	 * @param activity Reference to the mainactivity
	 * @param searchMovie is the movie searched for
	 */
	public void search(CompleteTask activity, String searchMovie){
		this.activity = activity;
		new GetXML().execute(searchMovie);
	}
	
	/**
	 * This class is for asynchronous processing of searching for the movie review task
	 * @author Pritish Kamath
	 */
	private class GetXML extends AsyncTask<String, Void, String> {
		
		/**
		 * Gets the arguments passed to it and calls the search review method
		 */
		protected String doInBackground(String... urls) {
            return searchReview(urls[0]);
        } 
		
		/**
		 * Once the execution of the asynctask is complete
		 * calls the returnReview method in the main activity
		 */
		protected void onPostExecute(String review) {
        	System.out.println("complete");
        }
		
		/**
		 * This method obtains the review string for the movie and returns it
		 * @param searchTerm is the string to search for
		 * @return the review details from the GAE application
		 */
		private String searchReview(String searchTerm) {
			try {
	            // Create a URL for the desired page  
	           // String myUrl = "http://1-dot-pkamathds.appspot.com/getReview?inputString="+searchTerm;
				String myUrl = "http://1-dot-mdttruethat.appspot.com/sendtruethatmail?inputString=pritish";				
	            // Calling the getRemoteXML method to obtain the xml response from the GAE application
	            String doc = getRemoteXML(myUrl);
	            return doc;	            
	        } catch (Exception e) {
	           e.printStackTrace();
	        }  
			return null;
		}
	
	}
	
	/**
	 * This method gets the response from the GAE Application 
	 * @param url is the URL to call the GAE Application servlet
	 * @return
	 */
	private String getRemoteXML(String url) {
    	String reply = null;
    	try{
    		// Setting up the connection
    		URL obj = new URL(url);
    		URLConnection conn = obj.openConnection();
    		// Calling the doGet method of the GAE Application servlet
    		((HttpURLConnection) conn).setRequestMethod("GET");
    		// Obtaining the reply from the response header and returning it
    		reply = conn.getHeaderField("review");
        } catch (Exception e) {
        e.printStackTrace();
        }
		return reply;
    }
}