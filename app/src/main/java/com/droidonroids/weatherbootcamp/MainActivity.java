package com.droidonroids.weatherbootcamp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;
import com.droidonroids.weatherbootcamp.data.network.entities.ListAdapter;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherApiService;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherRecycleViewAdapter;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;


import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BOOTCAMP";
    private static final String ENDPOINT = "http://api.openweathermap.org/data/2.5";
    private RestAdapter mRestAdapter;
    private WeatherApiService mWeatherApiService;
    private EditText mTownEditText;
    private Button mGetWeatherButton;
    private Callback<ForecastResponse> mWeatherResponseCallback;
    private ListView mWeatherListView;
    private RecyclerView mWeatherRecyclerView;
    private WeatherRecycleViewAdapter mWeatherRecycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initRestAdapter();
        setOnClickListenerToGetWeatherButton();
        setCallback();

        mWeatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void findViews() {
        mTownEditText = (EditText) findViewById(R.id.townEditText);
        mGetWeatherButton = (Button) findViewById(R.id.getWeatherButton);
        //mWeatherListView = (ListView) findViewById(R.id.weatherListView);
        mWeatherRecyclerView = (RecyclerView) findViewById(R.id.weatherRecyclerView);
    }

    private void initRestAdapter() {
        final RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("units", "metric");
            }
        };

        mRestAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog(TAG)).setEndpoint(ENDPOINT).setRequestInterceptor(requestInterceptor).build();
        mWeatherApiService = mRestAdapter.create(WeatherApiService.class);
    }

    private void setOnClickListenerToGetWeatherButton() {
        mGetWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new DownloadingThread().execute();
                String townName = mTownEditText.getEditableText().toString();
                mWeatherApiService.getForecastUsingCallback(townName, mWeatherResponseCallback);
            }
        });
    }

    private void setCallback() {
        mWeatherResponseCallback = new Callback<ForecastResponse>() {
            @Override
            public void success(ForecastResponse forecastResponse, Response response) {
                //populateListView(forecastResponse.getWeatherResponses());
                populateRecycleView( forecastResponse.getWeatherResponses() );
                Log.d(TAG, forecastResponse.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "failure: " + error);
            }
        };
    }

    private void populateListView(List<WeatherResponse> weatherResponseList) {
        ArrayAdapter<WeatherResponse> customAdapter = new ListAdapter(MainActivity.this, weatherResponseList);
        mWeatherListView.setAdapter(customAdapter);
    }

    private void populateRecycleView(List<WeatherResponse> weatherResponseList) {
        if (weatherResponseList != null) {
            mWeatherRecycleViewAdapter = new WeatherRecycleViewAdapter(weatherResponseList, this);
            mWeatherRecyclerView.setAdapter(mWeatherRecycleViewAdapter);
        } else {
            Toast.makeText(this, getString(R.string.invalid_town_name), Toast.LENGTH_SHORT).show();
        }
    }


    private class DownloadingThread extends AsyncTask<Void, Void, WeatherResponse> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected WeatherResponse doInBackground(Void... params) {
            String townName = mTownEditText.getEditableText().toString();
            ForecastResponse forecastResponse = mWeatherApiService.getForecastWithAsync(townName);
            Log.d("ODP", forecastResponse.toString());
            return null;
        }

        @Override
        protected void onPostExecute(WeatherResponse result) {

        }
    }


}
