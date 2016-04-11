package com.example.threadexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadExampleActivity extends Activity {

	static TextView myTextView; // a reference to the textview we will update.

	//This is the handler - here we specify how to react to any messages.
	static Handler handler = new Handler() {

		  //this code will run in the UI thread
		    @Override
		  public void handleMessage(Message msg) {
			  //get the data from the message
		      Bundle bundle = msg.getData();
			  String string = bundle.getString("myKey");

			 //update our textview
			  myTextView.setText(string);
		     }
		 };

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread_example);
		//get our reference to the textview.
		myTextView = (TextView)findViewById(R.id.myTextView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thread_example, menu);
		return true;
	}
	
    public void buttonClick(View view)
    {
		//this code will run in a background thread.
    	Runnable runnable = new Runnable() {
	        public void run() {     
				//get a new message for us to use from the handler
            	Message msg = handler.obtainMessage();
    			Bundle bundle = new Bundle();
				//put the date in our bundle
    			SimpleDateFormat dateformat = 
                    new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
    			String dateString = dateformat.format(new Date());
				//put the date string into the bundle
    			bundle.putString("myKey", dateString);
				msg.setData(bundle);
				//send the message - this will be sent to the handler
				handler.sendMessage(msg);
	        }
        };
		//we want a new thread
        Thread mythread = new Thread(runnable);
        //lets start the thread (when the button is clicked)
		mythread.start();

    }

}
