package com.dconstructing.android.tasktimer.objects;

import android.text.format.Time;

public class Task {

	public Time startTime;
	public Time endTime;
	public String label;

	public Task(Time startTime) {
		this.startTime = startTime;
	}

	public Task(Time startTime, String label) {
		this.startTime = startTime;
		this.label = label;
	}

	public String toString() {
		return this.label;
	}
}
