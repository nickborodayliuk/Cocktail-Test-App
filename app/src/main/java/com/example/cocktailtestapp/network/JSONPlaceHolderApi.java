package com.example.cocktailtestapp.network;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("/api/json/v1/1/search.php")
    Call<Post> getPostByS(@Query("s") String s);
}
