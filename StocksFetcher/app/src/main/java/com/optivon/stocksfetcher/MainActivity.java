package com.optivon.stocksfetcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    private static final String TAG = "Stocks fetcher";
    private static final String BASE_URL = "http://dev.markitondemand.com/MODApis/Api/v2/";
    private EditText stockName;
    private TextView textView;
    private StockApiService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stockName = (EditText)findViewById(R.id.stock_name);
        textView = (TextView)findViewById(R.id.text);
            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

             service = restAdapter.create(StockApiService.class);

        stockName.addTextChangedListener(this);

        }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(charSequence.length()>2) {
            Call<Stock> stock = service.getStockInfo(stockName.getText().toString());

            stock.enqueue(new Callback<Stock>() {
                @Override
                public void onResponse(Call<Stock> call, Response<Stock> response) {
                    Stock stock = response.body();
                    textView.setText(stock.toString());
                }

                @Override
                public void onFailure(Call<Stock> call, Throwable t) {
                    Log.d(TAG, "onFailure: "+t.getMessage());
                }
            });
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}



