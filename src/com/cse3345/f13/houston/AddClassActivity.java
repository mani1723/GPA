package com.cse3345.f13.houston;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddClassActivity extends Activity implements View.OnClickListener {

	Button cancel = null;
	Button add = null;
	TextView name = null;
	TextView grade = null;
	TextView hours = null;
	
	EditText _etName = null;
	EditText _etgrade = null;
	EditText _ethours = null;
	
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
		cancel.setOnClickListener(AddClassActivity.this);
		add = (Button) findViewById(R.id.Add);
		add.setOnClickListener(AddClassActivity.this);
		
		name = (TextView) findViewById(R.id.className);
		grade = (TextView) findViewById(R.id.classGrade);
		hours = (TextView) findViewById(R.id.classHours);

		_etName = (EditText)findViewById(R.id.classNameTextField);
		_etgrade = (EditText)findViewById(R.id.classGradeTextField);
		_ethours = (EditText)findViewById(R.id.classHoursTextField);
		
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

		classObj.setName(_etName.getText().toString());
		
		try {
			classObj.setGrade(Integer.parseInt(_etgrade.getText().toString().trim()));
			classObj.setHours(Integer.parseInt(_ethours.getText().toString().trim()));
		} catch (NumberFormatException e) {
			Toast.makeText(AddClassActivity.this, "Exception", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		

		// Add grading scale
		classObj.setScale(scale);

		// Add the classObj to the database
		classSource.open();
		classObj = classSource.addClassToDatabase(classObj);
		classSource.close();
	}
}


	