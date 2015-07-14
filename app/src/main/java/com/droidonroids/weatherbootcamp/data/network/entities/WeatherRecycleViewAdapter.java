package com.droidonroids.weatherbootcamp.data.network.entities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidonroids.weatherbootcamp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WeatherRecycleViewAdapter extends RecyclerView.Adapter {

    public static final int ITEM_TYPE_COMPLEX = 0;
    public static final int ITEM_TYPE_DEFAULT = 1;
    private List<WeatherResponse> mWeatherResponseList;
    private LayoutInflater mLayoutInflater;
    private Picasso mPicasso;

    public WeatherRecycleViewAdapter(List<WeatherResponse> weatherResponseList, Context context) {
        mWeatherResponseList = weatherResponseList;
        mPicasso = Picasso.with(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        switch (viewType) {
            case ITEM_TYPE_COMPLEX:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complex_item_weather, parent, false);
                return new RecyclerWeatherComplexItemHolder(view);

            case ITEM_TYPE_DEFAULT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item_weather, parent, false);
                return new RecyclerWeatherDefaultItemHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        WeatherResponse weatherResponse = mWeatherResponseList.get(position);


        switch (itemType) {
            case ITEM_TYPE_COMPLEX:
                RecyclerWeatherComplexItemHolder complexItemHolder = (RecyclerWeatherComplexItemHolder) holder;
                complexItemHolder.mTemperatureTextView.setText(Double.toString(weatherResponse.getMain().getTemp()) + "C");
                complexItemHolder.mPressureTextView.setText("Pressure: " + Double.toString(weatherResponse.getMain().getPressure()));
                complexItemHolder.mMinTempTextView.setText("Min. temp: " + Double.toString(weatherResponse.getMain().getTempMin()));
                complexItemHolder.mMaxTempTextView.setText("Max. temp: " + Double.toString(weatherResponse.getMain().getTempMax()));
                mPicasso.load(URLBuilderForIcon.URLBuilderForIcon(weatherResponse.getWeathers().get(0).getIcon())).into(complexItemHolder.mWeatherIconImageView);
                break;

            case ITEM_TYPE_DEFAULT:
                RecyclerWeatherDefaultItemHolder defaultItemHolder = (RecyclerWeatherDefaultItemHolder) holder;
                defaultItemHolder.mTemperatureTextView.setText( Double.toString(weatherResponse.getMain().getTemp()) + "C");
                defaultItemHolder.mPressureTextView.setText( "Pressure: " + Double.toString(weatherResponse.getMain().getPressure()) );
                mPicasso.load(URLBuilderForIcon.URLBuilderForIcon(weatherResponse.getWeathers().get(0).getIcon())).into(defaultItemHolder.mWeatherIconImageView);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mWeatherResponseList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 5) {
            return ITEM_TYPE_COMPLEX;
        } else {
            return ITEM_TYPE_DEFAULT;
        }
    }


    public static class RecyclerWeatherComplexItemHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.weatherIconImageView)
        ImageView mWeatherIconImageView;
        @Bind(R.id.temperatureTextView)
        TextView mTemperatureTextView;
        @Bind(R.id.pressureTextView)
        TextView mPressureTextView;
        @Bind(R.id.minTempTextView)
        TextView mMinTempTextView;
        @Bind(R.id.maxTempTextView)
        TextView mMaxTempTextView;

        public RecyclerWeatherComplexItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class RecyclerWeatherDefaultItemHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.weatherIconImageView)
        ImageView mWeatherIconImageView;
        @Bind(R.id.temperatureTextView)
        TextView mTemperatureTextView;
        @Bind(R.id.pressureTextView)
        TextView mPressureTextView;

        public RecyclerWeatherDefaultItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
