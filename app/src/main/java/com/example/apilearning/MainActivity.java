package com.example.apilearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.apilearning.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.example.apilearning.apiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    adapterAPI adapter;
    List<modifiedModelApi> list = new ArrayList<>();
    apiInterface AI ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // toolbar has been set as an Action bar:
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setAdapter(list);
        initialization();
        getData();


    }

    public void setAdapter(List<modifiedModelApi> list) {
        adapter = new adapterAPI(list, this);
        binding.recyclarView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclarView.setAdapter(adapter);
    }
    public void initialization(){
        // initialzation of apiClient with its getClient method
        Retrofit retrofit =apiClient.getClient();
        AI =retrofit.create(apiInterface.class);
    }

    public void getData(){
        AI.getpoetry().enqueue(new Callback<responseApi>() {
            @Override
            public void onResponse(Call<responseApi> call, Response<responseApi> response) {
                if(response!=null){
                    try {
                       if (response.body().getStatus().equals("1")){
                            setAdapter(response.body().getData());
                       }
                       else{
                           Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                       }

                    }
                    catch (Exception exp){
                        Log.e( "onResponse: ", exp.getLocalizedMessage() );
                    }
                }
            }

            @Override
            public void onFailure(Call<responseApi> call, Throwable t) {
                Log.e("onFailure: ", t.getLocalizedMessage());
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.toolbarmenu){
           startActivity(new Intent(this,addActivity.class));

        }
        return super.onOptionsItemSelected(item);
    }

}