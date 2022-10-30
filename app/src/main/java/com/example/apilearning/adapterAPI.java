package com.example.apilearning;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class adapterAPI extends RecyclerView.Adapter<adapterAPI.viewHolder> {
    List<modifiedModelApi> list;
    Context context;
    apiInterface AI;

    public adapterAPI(List<modifiedModelApi> list, Context context) {
        this.list = list;
        this.context = context;
        Retrofit retrofit = apiClient.getClient();
        AI = retrofit.create(apiInterface.class);
    }

    @NonNull
    @Override
    public adapterAPI.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poetrysample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterAPI.viewHolder holder, int position) {
        modifiedModelApi model = list.get(position);

        // can also be do like below:
//        holder.poetry.setText(list.get(position).getPoetry());

        holder.poetry.setText(model.getPoetry_data());
        holder.poet_name.setText(model.getPoet_name());
        holder.date_time.setText(model.getDate_time());

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.getItemId();
                delete(model.getId()+ "", position);
                list.remove(position);
                notifyDataSetChanged();

            }
        });
        holder.updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,addActivity.class);
                intent.putExtra("id",(model.getId()+""));
                intent.putExtra("poetry",model.getPoetry_data());
                intent.putExtra("poet_name",model.getPoet_name());
                Toast.makeText(context, model.getId()+"", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView poetry, poet_name, date_time;
        AppCompatButton updatebtn, deletebtn;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            poetry = itemView.findViewById(R.id.tvPoetry);
            poet_name = itemView.findViewById(R.id.tvPoetName);
            date_time = itemView.findViewById(R.id.date_time);
            updatebtn = itemView.findViewById(R.id.updateButton);
            deletebtn = itemView.findViewById(R.id.deleteButton);


        }
    }
    private  void delete(String  id,int  pose ){
        AI.deletecall(id).enqueue(new Callback<deleteresponse>() {
            @Override
            public void onResponse(Call<deleteresponse> call, Response<deleteresponse> response) {

                    try {
                        if(response !=null ){
                            Toast.makeText(context,response.body().getMessage()+"deleted",
                                    Toast.LENGTH_LONG).show();
//                                 if(response.body().getStatus().equals("1")){
//                                list.remove(pose);
//                                notifyDataSetChanged();
//                            }
                    }


                    }catch (Exception e){
                        Toast.makeText(context, "Exception new lk " +e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }

            }

            @Override
            public void onFailure(Call<deleteresponse> call, Throwable t) {
                Toast.makeText(context, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
