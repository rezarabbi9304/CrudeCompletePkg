package com.example.apicrudegetmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GetApiAdapter extends RecyclerView.Adapter<GetApiAdapter.MyView>  {



    private Context mContext;
    private List<AnimModel> animModelList;

    public GetApiAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setAnimModelList(List<AnimModel> animModelList) {
        this.animModelList = animModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.get_layout_main,null);
        MyView holder = new MyView(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {

        holder.animName.setText(animModelList.get(position).getAnime());

    }

    @Override
    public int getItemCount() {
        return animModelList.size();
    }

    public static class  MyView extends RecyclerView.ViewHolder{

        private TextView animName;
        public MyView(@NonNull View itemView) {
            super(itemView);

            animName = itemView.findViewById(R.id.textViewName);
        }
    }


    }


