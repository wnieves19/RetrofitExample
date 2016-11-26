package com.optivon.stocksfetcher;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wilfredonieves on 11/24/16.
 */

public interface StockApiService {
    @GET("Quote/json")
    Call<Stock> getStockInfo(@Query("symbol") String symbol);

}
