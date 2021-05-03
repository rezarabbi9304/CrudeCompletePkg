package com.example.apicrudegetmain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    @GET("users")
    Call<List<UserModel>> getPost(@Query("id") int Id);

    @GET("quotes")
    Call<List<AnimModel>>getAnim();

    @GET("users")
    Call<UserListRespons> getUserList(@Query("page") int id);

    @POST("users")
    Call<UserModel> postUser(@Body UserModel userModel);

    @PUT("users")
    Call<UserModel> putUser(@Query("id") int id, @Body UserModel userModel);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);
}
