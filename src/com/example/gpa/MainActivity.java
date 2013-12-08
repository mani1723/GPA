package com.example.gpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	Button calculateGPA, viewClasses, addClass ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
	}


	@Override
	public void onClick(View view) {
		Intent i;
		
		switch(view.getId()) {
			case R.id.addClass:
				i = new Intent(this, AddClassActivity.class);
				startActivity(i);
				break;
			case R.id.viewClasses:
				i = new Intent(this, ClassListActivity.class);
				startActivity(i);
				break;
			case R.id.calculate:
				i = new Intent(this, CalculateActivity.class);
				startActivity(i);
				break;		
		}
	}
	
	public void initialize() {
		calculateGPA = (Button) findViewById(R.id.calculate);
		viewClasses = (Button) findViewById(R.id.viewClasses);
		addClass = (Button) findViewById(R.id.addClass);
		
		calculateGPA.setOnClickListener(this);
		viewClasses.setOnClickListener(this);
		addClass.setOnClickListener(this);
	}

}
