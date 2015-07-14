package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;

public class Weather {
	@SerializedName("icon") private String icon;

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override public String toString() {
		return "Weather{" +
			"icon='" + icon + '\'' +
			'}';
	}
}
