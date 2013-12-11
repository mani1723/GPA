package com.cse3345.f13.houston;

import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class ViewClassesActivity extends Activity implements
View.OnClickListener {

	List<Class> classList;  // Contains the list of courses
	
	ArrayAdapter<Class> adapter;  // An adapter to facilitate placing the information
								// from the course list to the listView
	
	ClassDataSource source; 
	
	Button addClass, back;
	
	ListView list;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.view_class);
		
		initialize();
	}

	public void initialize() {
	
		addClass = (Button) findViewById(R.id.add_class);
		addClass.setOnClickListener(this);
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(this);
		
		list = (ListView) findViewById(R.id.viewClasses);
		
		source = new ClassDataSource(this);
		updateList();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
		ContextMenuInfo menuInfo) {
		
		if (view.getId() == R.id.viewClasses) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		
			menu.setHeaderTitle(classList.get(info.position).getName());
		
			String[] classMenu = getResources().getStringArray(R.array.menu);
		
			for (int i = 0; i < classMenu.length; i++) {
				menu.add(Menu.NONE, i, i, classMenu[i]);
			}
	
		}
	}
	
	@Override
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
	
			case R.id.add_class:
				i = new Intent(this, AddClassActivity.class);
				startActivityForResult(i, R.integer.ADD_CLASS);
				break;
			case R.id.back:
				finish();
				break;
		}
	
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case R.integer.DELETE_CLASS:
				if (resultCode == RESULT_OK) {
					updateList();
			
				}
				break;
			case R.integer.ADD_CLASS:
				if (resultCode == RESULT_OK) {
					updateList();
				}
				break;
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
			.getMenuInfo();
	int menuItemIndex = item.getItemId();
	String[] classMenu = getResources().getStringArray(R.array.menu);
	String menuItemName = classMenu[menuItemIndex];
	int listItemId = classList.get(info.position).getId();
	
	Intent i;
	if (menuItemName.equals("Edit")) {
		// TODO Move to course edit
		System.out.println("Edit course");
	}
	//if (menuItemName.equals("Delete")) {
	//	System.out.println("Delete course");
	//	i = new Intent(this, DeleteClassActivity.class);
	//	i.putExtra("id", listItemId);  //TODO possibly convert to tags instead of extras
	//	startActivityForResult(i, R.integer.COURSE_DELETE);
	//}
	System.out.println(String.format("Selected %s for item %s",
			menuItemName, listItemId));
	return true;
	}
	
	public void updateList() {
	
		source.open();
		classList = source.getAllClasses();
		for (int i = 0; i < classList.size(); ++i){
			classList.get(i).setContext(this);
		}
	
		adapter = new ArrayAdapter<Class>(this,
				android.R.layout.simple_list_item_1, 
				android.R.id.text1,classList);
		list.setAdapter(adapter);
	
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	
		@Override
		public void onItemClick(AdapterView<?> arg0, View v, int pos,
				long id) {
			onListItemClick(v, pos, id);
	
		}
	
			private void onListItemClick(View v, int pos, long id) {
				System.out.println("onListItemClick id=" + id);
		//		Intent i;
		//		i = new Intent(getApplicationContext(),
		//				IndividualCourseActivity.class);
		//		i.putExtra("id", courseList.get(pos).getId());
		//		startActivity(i);
			}
	
		});
	
	registerForContextMenu(list);
	source.close();
	
	}
		@Override
		public void onResume(){
			super.onResume();
			updateList();
		}
	}
	
