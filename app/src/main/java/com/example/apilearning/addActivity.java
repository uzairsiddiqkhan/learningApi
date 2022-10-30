package com.example.apilearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.apilearning.databinding.ActivityAddBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class addActivity extends AppCompatActivity {
    ActivityAddBinding binding;
    apiInterface AI;
    String poetry = "";
    String poet_name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        mujhe is ki zaroorat nhi hai
//        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               finish();
//            }
//        });

        initialization();


        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poetry = binding.etpoetry.getText().toString().trim();
                poet_name = binding.etpoetry.getText().toString().trim();

                if (poetry.equals("") || poet_name.equals("")) {
                    Toast.makeText(addActivity.this, "Please Enter Acurate Data", Toast.LENGTH_SHORT).show();
                } else {
                    addpoetry(poetry, poet_name);
                    finish();
                }
            }

        });
        updateBtn();
    }


    private void initialization() {
        Retrofit retrofit = apiClient.getClient();
        AI = retrofit.create(apiInterface.class);
    }

    private void addpoetry(String poetry2, String poet_name2) {
        AI.addCall(poetry2, poet_name2).enqueue(new Callback<deleteresponse>() {
            @Override
            public void onResponse(Call<deleteresponse> call, Response<deleteresponse> response) {
                if (response != null) {
                    Toast.makeText(addActivity.this,
                            response.body().getMessage() + " " + response.body().getStatus(),
                            Toast.LENGTH_LONG).show();




                } else {
                    Toast.makeText(addActivity.this,
                            response.body().getMessage() + " " + response.body().getStatus(),
                            Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<deleteresponse> call, Throwable t) {
                Toast.makeText(addActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void  updateBtn(){
        if(getIntent().hasExtra("id")  &&
                getIntent().hasExtra("poetry") &&
                getIntent().hasExtra("poet_name") ){

            String poetry_data =getIntent().getStringExtra("poetry");
            String poet_name =getIntent().getStringExtra("poet_name");
            final String id =getIntent().getStringExtra("id");


            binding.etpoetry.setText(poetry_data);
            binding.etpoetName.setText(poet_name);
            binding.submit.setText("Update");
            binding.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  String newPoetryData =  binding.etpoetry.getText().toString().trim();
                  String newPoetData =binding.etpoetName.getText().toString().trim();

                  updatePoetry(newPoetryData,newPoetData,id);
                    Toast.makeText(addActivity.this, id +" "+newPoetryData, Toast.LENGTH_SHORT).show();



                    finish();
                }
            });

        }
    }
    public  void updatePoetry(String updatePoetry ,String updatePoet ,String id){
        AI.updateCall(updatePoetry,updatePoet ,id ).enqueue(new Callback<deleteresponse>() {
            @Override
            public void onResponse(Call<deleteresponse> call, Response<deleteresponse> response) {
                try {
                    if(response!=null){
                        Toast.makeText(addActivity.this, "Poetry update successfully",
                                Toast.LENGTH_SHORT).show();

                    }

                }catch (Exception e){
                    Toast.makeText(addActivity.this, "exception "+e , Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<deleteresponse> call, Throwable t) {
                Toast.makeText(addActivity.this, "unable to update poetrya",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}