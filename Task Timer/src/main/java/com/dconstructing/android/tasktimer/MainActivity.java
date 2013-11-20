package com.dconstructing.android.tasktimer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.dconstructing.android.tasktimer.objects.Task;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

	ArrayList<Task> thing = new ArrayList<Task>();
	Task currentTask;
	ArrayAdapter<Task> listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView list = (ListView) findViewById(R.id.task_list);

		if (savedInstanceState == null) {
			listAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, thing);
			list.setAdapter(listAdapter);
		}
	}

	public void startClicked(View view) {
		Time now = new Time(Time.TIMEZONE_UTC);
		now.setToNow();

		if (currentTask != null) {
			int listSize = thing.size();
			Task oldTask = thing.get(listSize);
			oldTask.endTime = now;
			thing.add(listSize, oldTask);
		}

		String taskName = "Nameless";

		EditText editText = (EditText) findViewById(R.id.task_input_name);
		String name = editText.getText().toString();
		if (!name.equals("")) {
			taskName = name;
		}

		Task newTask = new Task(now, taskName);
		thing.add(newTask);
		listAdapter.notifyDataSetChanged();

		editText.setText("");
	}

	public void clearClicked(View view) {
		thing.clear();
		listAdapter.notifyDataSetChanged();
	}

}
