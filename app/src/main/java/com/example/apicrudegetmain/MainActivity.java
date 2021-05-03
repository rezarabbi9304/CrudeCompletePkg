package com.example.apicrudegetmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Context mContext;
    private RecyclerView recyclerView;
    private List<UserModel> userModelList;
    private List<AnimModel> animModelList;
     private UserApi userApi;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //doing intent
       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PostActivity.class);
                startActivity(intent);

            }
        });
*/
        recyclerView = findViewById(R.id.recycler);
        userModelList = new ArrayList<>();
        animModelList = new ArrayList<>();

      //  https://animechan.vercel.app/api/quotes
       // https://reqres.in/api/


        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://animechan.vercel.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);

     // getUser();
      getAnim();






    }

    private void getAnim() {
        Call<List<AnimModel>> call = userApi.getAnim();
        call.enqueue(new Callback<List<AnimModel>>() {
            @Override
            public void onResponse(Call<List<AnimModel>> call, Response<List<AnimModel>> response) {

                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "CODE" + response.code(), Toast.LENGTH_LONG).show();
                }
                List<AnimModel> animModels = response.body();
                Log.d("TAG", "onResponse: ........... "+response.body().size());


                for (AnimModel anim:animModels
                     ) {
                    animModelList.add(anim); }

                putDataAdapter(animModelList);

            }

            @Override
            public void onFailure(Call<List<AnimModel>> call, Throwable t) {

            }
        });
    }

    private void getUser() {
        Call<List<UserModel>> call = userApi.getPost(1);

        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "CODE:" + response.code(), Toast.LENGTH_SHORT).show();
                    textView.setText("CODE=" + response.code());
                }
                List<UserModel> userModels = response.body();

                for (UserModel user:userModels) {

                    userModelList.add(user);

                }
                //putDataAdapter(userModelList);
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Message"+ t.getMessage(), Toast.LENGTH_LONG).show();
                textView.setText("ERROR=" + t.getMessage());

            }
        });
    }

    public void putDataAdapter(List<AnimModel> animModelList){
        GetApiAdapter adapter = new GetApiAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAnimModelList(animModelList);
        recyclerView.setAdapter(adapter);
    }
}