package com.example.apicrudegetmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GetUserApiAdapter extends RecyclerView.Adapter<GetUserApiAdapter.MyViewHolder> {

    private Context mContext;
    private List<UserModel> userListResponsList;

    public GetUserApiAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setUserListResponsList(List<UserModel> userListResponsList) {
        this.userListResponsList = userListResponsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.get_layout_main,null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.userText.setText(userListResponsList.get(position).getFirst_name());



    }

    @Override
    public int getItemCount() {
        return userListResponsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
            private TextView userText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userText = itemView.findViewById(R.id.textViewName);
        }
    }
}
