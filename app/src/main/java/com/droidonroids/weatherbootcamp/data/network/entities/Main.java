package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;

public class Main {
	@SerializedName("temp") private Double temp;
	@SerializedName("pressure") private Double pressure;
	@SerializedName("temp_min") private Double tempMin;
	@SerializedName("temp_max") private Double tempMax;

	public Double getTemp() {
		return this.temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getPressure() {
		return this.pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Double getTempMin() {
		return this.tempMin;
	}

	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	public Double getTempMax() {
		return this.tempMax;
	}

	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}

	@Override public String toString() {
		return "Main{" +
			"temp=" + temp +
			", pressure=" + pressure +
			", tempMin=" + tempMin +
			", tempMax=" + tempMax +
			'}';
	}
}
