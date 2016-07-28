package com.htq.circleprogressbar;

import com.htq.circleprogressbar.libary.CircleProgressBar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	
	private CircleProgressBar circleProgressBar,circleProgressBar2,circleProgressBar3,circleProgressBar4;
	private int currentProgress=0;
	private int bar4CurrentPro=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		
	}
	private void initView()
	{
		circleProgressBar=(CircleProgressBar) findViewById(R.id.circle_progress_bar);
		circleProgressBar2=(CircleProgressBar) findViewById(R.id.circle_progress_bar_2);
		circleProgressBar3=(CircleProgressBar) findViewById(R.id.circle_progress_bar_3);
		circleProgressBar4=(CircleProgressBar) findViewById(R.id.circle_progress_bar_4);
	}
	private void initData()
	{
		new Thread()
		{
			public void run()
			{
				circleProgressBar4.setMaxProgress(1000);
				
				while(currentProgress<=1000)
				{
			      circleProgressBar.setCurrentProgress(currentProgress);
			      circleProgressBar2.setCurrentProgress(currentProgress);
			      circleProgressBar3.setCurrentProgress(currentProgress);
			      if(bar4CurrentPro<=700)
			    	{
			    	  circleProgressBar4.setCurrentProgress(bar4CurrentPro);
			    	}
			    	
			      bar4CurrentPro+=10;
			      currentProgress+=5;
			      try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			     
				
			}
		}.start();
		
	
	}
}
