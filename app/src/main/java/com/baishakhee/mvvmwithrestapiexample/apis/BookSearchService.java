package com.baishakhee.mvvmwithrestapiexample.apis;

import com.baishakhee.mvvmwithrestapiexample.models.VolumesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookSearchService {
    @GET("/books/v1/volumes")
    Call<VolumesResponse> searchVolumes(
            @Query("q") String query,
            @Query("inauthor") String author
          //  @Query("key") String apiKey
    );
}
