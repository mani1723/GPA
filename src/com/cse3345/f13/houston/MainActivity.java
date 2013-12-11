package com.cse3345.f13.houston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

	Button calculateGPA, viewClasses, addClass ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
		calculate();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
				i = new Intent(this, ViewClassesActivity.class);
				startActivity(i);
				break;
			case R.id.calculate:
				//i = new Intent(this, CalculateActivity.class);
				//startActivity(i);
				calculate();
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
	
	public void calculate() {
		
		
		
	}

}
