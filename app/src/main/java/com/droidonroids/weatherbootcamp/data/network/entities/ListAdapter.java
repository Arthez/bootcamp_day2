package com.droidonroids.weatherbootcamp.data.network.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidonroids.weatherbootcamp.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ListAdapter extends ArrayAdapter<WeatherResponse> {

    private LayoutInflater mLayoutInflater;
    private String mPressureHeader;

    public ListAdapter(Context context, List<WeatherResponse> weatherResponses) {
        super(context, R.layout.listview_item_weather, weatherResponses);
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPressureHeader = context.getString(R.string.pressure_text_label);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WeatherViewHolder holder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.listview_item_weather, parent, false);

            holder = new WeatherViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder = (WeatherViewHolder) convertView.getTag();
        WeatherResponse weatherResponse = getItem(position);
        holder.mTemperatureTextView.setText(Double.toString(weatherResponse.getMain().getTemp()));
        holder.mPressureTextView.setText(mPressureHeader + Double.toString(weatherResponse.getMain().getPressure()));

        return convertView;
    }

    public static class WeatherViewHolder {
        @Bind(R.id.weatherIconImageView)
        ImageView mWeatherIconImageView;
        @Bind(R.id.temperatureTextView)
        TextView mTemperatureTextView;
        @Bind(R.id.pressureTextView)
        TextView mPressureTextView;

        WeatherViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
