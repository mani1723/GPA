package com.example.gpa;

import java.math.BigDecimal;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Class implements Parcelable{
	private int id;
	private String name;
	private int hours;
	private int grade;
	private Scale scale;

	private Context ctx;
	
	public Class(){
		name = "";
		hours = -1;
		grade = -1;
	}
	public Class(Parcel source) {
		id = source.readInt();
		name = source.readString();
		hours = source.readInt();
		grade = source.readInt();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	@Override
	public String toString(){
		return this.name+" "+this.grade + " \t ";
	}
	public Scale getScale() {
		return scale;
	}
	public void setScale(Scale scale) {
		this.scale = scale;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeInt(grade);
		dest.writeInt(hours);
	}
	public static final Parcelable.Creator<Class> CREATOR = new Parcelable.Creator<Class>() {

		@Override
		public Class createFromParcel(Parcel source) {
			return new Class(source);
		}

		@Override
		public Class[] newArray(int size) {
			return new Class[size];
		}

	};
	
	public String getCourseCurrentGrade(){
		// TODO Algorithms
		double avg = getGrade();
		if(avg > -1){
			BigDecimal trimmedAvg = new BigDecimal(avg);
			trimmedAvg = trimmedAvg.setScale(2, BigDecimal.ROUND_UP);
			return "Grade: " + trimmedAvg + " % " + scale.getLetterGrade(avg);
		}
		else{
			return "Grade: N/A";
		}
		
		
	}
	
	public String getCourseGrade(){
		// TODO algorithms
		return "";
	}
	public String getCourseTitle(){
		
		return this.getName();
	}
	public Context getContext() {
		return ctx;
	}
	public void setContext(Context ctx) {
		this.ctx = ctx;
	}
}
