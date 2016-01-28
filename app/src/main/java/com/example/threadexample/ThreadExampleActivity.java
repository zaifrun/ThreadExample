package com.example.threadexample;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;

public class ThreadExampleActivity extends Activity {

	Handler handler = new Handler() {
		  @Override
		  public void handleMessage(Message msg) {
		      Bundle bundle = msg.getData();
			  String string = bundle.getString("myKey");
			  
			  TextView myTextView = 
                    (TextView)findViewById(R.id.myTextView);
			  myTextView.setText(string);
		     }
		 };

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread_example);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thread_example, menu);
		return true;
	}
	
    public void buttonClick(View view)
    {

    	Runnable runnable = new Runnable() {
	        public void run() {     

            	Message msg = handler.obtainMessage();
    			Bundle bundle = new Bundle();
    			SimpleDateFormat dateformat = 
                    new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
    			String dateString = dateformat.format(new Date());
    			bundle.putString("myKey", dateString);
                 msg.setData(bundle);
                 handler.sendMessage(msg);
	        }
        };
        Thread mythread = new Thread(runnable);
        mythread.start();

    }

}
