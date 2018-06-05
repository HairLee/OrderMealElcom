package com.elcom.com.quickbloxex.model.api;

import com.elcom.com.quickbloxex.model.data.Articles;
import com.elcom.com.quickbloxex.model.data.Sources;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author ihsan on 11/29/17.
 */

public interface Api {
    @GET("sources")
    Observable<Sources> sources();

    @GET("top-headlines")
    Observable<Articles> topHeadlines(@Query("sources") String sources);


}
