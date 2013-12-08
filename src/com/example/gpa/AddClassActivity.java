package com.example.gpa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddClassActivity extends Activity implements View.OnClickListener {

	Button cancel, add;
	EditText name, grade, hours;
	Class classObj;
	Scale scale;
	AlertDialog errorMessage;
	
	private ClassDataSource classSource;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.add_class);
		initialize();
	}
	@Override
	// This is an onClick method that is called when one of the buttons is
	// clicked.
	
	public void onClick(View view) {
		// This is the switch case which picks the button that was pressed by
		// its id.
		switch (view.getId()) {

		case R.id.Cancel:
			finish();
			break;

		case R.id.Add:
			if (validateClass()) {
				storeClassInformation();
				Intent returnIntent = new Intent();
				setResult(RESULT_OK, returnIntent);
				finish();
			}
			break;
		}

	}
	
	private boolean validateClass() {
		String errors = "You have made the following error(s): \n";
		boolean inputError = false;
		// ensure dept abbreviation is not blank

		if (name.getText().toString().length() == 0) {
			errors += "-Class name cannot be left blank.\n";
			inputError = true;
		}

		// ensure classObj number is not blank
		if (grade.getText().toString().length() == 0) {
			errors += "-Grade percentage cannot be left blank.\n";
			inputError = true;
		}
		

		if (inputError == true) {
			errorMessage.setMessage(errors);
			errorMessage.show();
			return false;
		}

		return true;
	}

	public void initialize() {
		cancel = (Button) findViewById(R.id.Cancel);
		add = (Button) findViewById(R.id.Add);
		name = (EditText) findViewById(R.id.className);
		grade = (EditText) findViewById(R.id.classGrade);
		hours = (EditText) findViewById(R.id.classHours);

		classSource = new ClassDataSource(this);

		cancel.setOnClickListener(this);
		add.setOnClickListener(this);

		classObj = new Class();

		scale = new Scale();

		errorMessage = new AlertDialog.Builder(this).create();
		errorMessage.setTitle("Input error!");
		errorMessage.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		
	}
	
	public void storeClassInformation() {

		Class classObj = new Class();

		classObj.setName(name.getText().toString());
		classObj.setGrade(Integer.parseInt(grade.getText().toString().trim()));
		classObj.setHours(Integer.parseInt(hours.getText().toString().trim()));

		// Add grading scale
		classObj.setScale(scale);

		// Add the classObj to the database
		classSource.open();
		classObj = classSource.addClassToDatabase(classObj);
		classSource.close();
	}
}