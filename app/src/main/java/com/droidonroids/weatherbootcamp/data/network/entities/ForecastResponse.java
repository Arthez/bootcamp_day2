package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ForecastResponse {
	@SerializedName("list") private List<WeatherResponse> weatherResponses;

	public List<WeatherResponse> getWeatherResponses() {
		return this.weatherResponses;
	}

	public void setWeatherResponses(List<WeatherResponse> weatherResponses) {
		this.weatherResponses = weatherResponses;
	}

	@Override public String toString() {
		return "ForecastResponse{" +
			"weatherResponses=" + weatherResponses +
			'}';
	}
}
