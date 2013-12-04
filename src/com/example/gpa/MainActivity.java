package com.example.gpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	Button addSemester, viewClasses, viewGPA;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
	}


	@Override
	public void onClick(View v) {
		Intent i;
		
	}
	
	public void initialize() {
		//addSemester = (Button) findViewById(R.id.add_semester);
		
	}

}
