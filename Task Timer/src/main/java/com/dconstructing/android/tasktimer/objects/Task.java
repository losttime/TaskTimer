package com.dconstructing.android.tasktimer.objects;

import android.text.format.Time;

import org.joda.time.Duration;
import org.joda.time.Interval;

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
		String output = this.label + " -";
		if (this.endTime != null) {
			long startMilli = this.startTime.toMillis(false);
			long endMilli = this.endTime.toMillis(false);
			Interval interval = new Interval(startMilli, endMilli);
			Duration duration = interval.toDuration();
			long days = duration.getStandardDays();
			if (days > 0) {
				output += " " + days + "d";
				duration = duration.minus(days*86400000);
			}
			long hours = duration.getStandardHours();
			if (hours > 0) {
				output += " " + hours + "h";
				duration = duration.minus(hours*3600000);
			}
			long minutes = duration.getStandardMinutes();
			if (minutes > 0) {
				output += " " + minutes + "m";
				duration = duration.minus(minutes*60000);
			}
			long seconds = duration.getStandardSeconds();
			if (seconds > 0) {
				output += " " + seconds + "s";
			}
		} else {
			this.startTime.switchTimezone(Time.getCurrentTimezone());
			output += " " + this.startTime.format("%l:%M%p"); // 3:14pm - http://linux.die.net/man/3/strftime
			//output += " " + this.startTime.format("%b %e @ %l:%M%p"); // Jan 3 @ 3:14pm
		}
		return output;
	}
}
