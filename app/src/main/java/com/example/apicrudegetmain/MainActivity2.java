package com.example.apicrudegetmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private Button nextActivityBtn;
    private RecyclerView recyclerViewUser;
    private List<UserModel> userModelList;
    private UserApi userApi;

    private TextView responText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nextActivityBtn = findViewById(R.id.buttonNext);
        responText = findViewById(R.id.responseText);
        recyclerViewUser = findViewById(R.id.recyclerUser);

        nextActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //intercepter doesn't work , don't know why.
       /* HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://animechan.vercel.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/

        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);

        //getUser solved.
        getUser();

       //postUserData();

        //putUser();
        deletUser();



    }

    private void deletUser() {
        Call<Void> call = userApi.deleteUser(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful())
                {
                    responText.setText("sucks " + response.code());
                }
                responText.setText("CODE: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                responText.setText(t.getMessage());
            }
        });

    }

    private void putUser() {
        UserModel userModel = new UserModel("morphius");
        Call<UserModel> call = userApi.putUser(3,userModel);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(!response.isSuccessful())
                {
                    responText.setText("UNsuccess= " + response.code());
                }
                UserModel userModel1 = response.body();
                String content = "";
                content+= "CODE" + response.code() + "\n";
                content +="First_name" + userModel1.getFirst_name() + "\n";
                content+= "UpdatedTIme" + userModel1.getUpdatedAt();

                responText.setText(content);



            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });

    }

    private void postUserData() {
        UserModel userModel = new UserModel("reza");
        Call<UserModel> call = userApi.postUser(userModel);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(!response.isSuccessful()){
                    responText.setText("CODE-unsuck" + response.code());
                }
                UserModel userModel1 = response.body();
                    String content = "";

                    content += "CODE" + response.code() +"\n";
                    content+= "First_Name" + userModel1.getFirst_name() +"\n";
                    content +="ID:" + userModel1.getId() + "\n";

                    responText.setText(content);

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
            responText.setText(t.getMessage());
            }
        });


    }

    private void getUser() {

        Call<UserListRespons> call = userApi.getUserList(2);
        call.enqueue(new Callback<UserListRespons>() {
            @Override
            public void onResponse(Call<UserListRespons> call, Response<UserListRespons> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG", "onResponse: ......... "+response.body());
                    userModelList = response.body().getData();
                }
                putDataIntoAdapter(userModelList);
            }

            @Override
            public void onFailure(Call<UserListRespons> call, Throwable t) {

            }
        });
    }
    public void putDataIntoAdapter(List<UserModel> userModels){
        GetUserApiAdapter adapter = new GetUserApiAdapter(this);
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
        adapter.setUserListResponsList(userModels);
        recyclerViewUser.setAdapter(adapter);

    }
}