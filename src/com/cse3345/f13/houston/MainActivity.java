package com.cse3345.f13.houston;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

	Button calculateGPA, viewClasses, addClass ;
	AlertDialog errorMessage;

	private ArrayList<String> listOfGrades = null;
	//double [] grades = null;

	//private List<Double> grades = new ArrayList<Double>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize();
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
		
		listOfGrades = new ArrayList<String>();
	}

	public void calculate() {
		//double[] grades = null;
		//double[] hours = null;
		double tempGPA = 0;
		double totalGPA = 0;
		double totalHours = 0;
		ClassDataSource classSource = new ClassDataSource(MainActivity.this); 
		classSource.open();
		List<Class> classList = classSource.getAllClasses();
        double[] grades = new double[classList.size()];
        double[] hours = new double[classList.size()];
		for (int i = 0; i < classList.size(); i++) {
			classList.get(i).getId();
			double grade = classList.get(i).getGrade();
			double hour = classList.get(i).getHours();
			
			tempGPA = getLetterGrade(grade);
			totalGPA += tempGPA;
			totalHours += hour;
			grades[i] = tempGPA;
			hours[i] = hour;

		}

		double actualGPA = 0;
		for (int i = 0; i < classList.size(); i++) {
			
			double tempGPA2 = grades[i];
			double tempHoursPercent = hours[i]/totalHours;
			double tempSum = tempGPA2 * tempHoursPercent;
			actualGPA += tempSum;
		}
		
		System.out.println("YOUR GPA IS: " + actualGPA);
		classSource.close();
		
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		twoDForm.setRoundingMode(RoundingMode.UP);

		Toast.makeText(getApplicationContext(), 
				"Your GPA is: " + twoDForm.format(actualGPA),
				Toast.LENGTH_LONG).show();
	}

	public double getLetterGrade(double e){

		int a = 94;
		int a_minus = 90;
		int b_plus = 87;
		int b = 84;
		int b_minus = 80;
		int c_plus = 77;
		int c = 74;
		int c_minus = 70;
		int d_plus = 67;
		int d = 64;
		int d_minus = 60;


		BigDecimal trimmedGrade = new BigDecimal(e);
		trimmedGrade = trimmedGrade.setScale(2, BigDecimal.ROUND_UP);
		e = trimmedGrade.doubleValue();
		if(e >= a){
			return 4.0;
		}
		else if(a_minus > 0 && e >= a_minus){
			return 3.7;
		}
		else if(b_plus > 0 && e >= b_plus){
			return 3.3;
		}
		else if(e >= b){
			return 3.0;
		}
		else if(b_minus > 0 && e >= b_minus){
			return 2.7;
		}
		else if(c_plus > 0 && e >= c_plus){
			return 2.3;
		}
		else if(e >= c){
			return 2.0;
		}
		else if(c_minus > 0 && e >= c_minus){
			return 1.7;
		}
		else if(d_plus > 0 && e >= d_plus){
			return 1.3;
		}
		else if(e >= d){
			return 1.0;
		}
		else if(d_minus > 0 && e >= d_minus){
			return 0.7;
		}
		else return 0.0;
	}

}
