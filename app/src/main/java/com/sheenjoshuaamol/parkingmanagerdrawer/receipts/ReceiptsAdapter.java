package com.sheenjoshuaamol.parkingmanagerdrawer.receipts;

import android.app.ListActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.recyclerview.widget.RecyclerView;

import com.sheenjoshuaamol.parkingmanagerdrawer.R;

import java.util.List;

public class ReceiptsAdapter extends RecyclerView.Adapter<ReceiptsViewHolder> {

    ReceiptsFragment receiptsFragment;
    List<ReceiptsModel> modelList;
    Context context;

    public ReceiptsAdapter(ReceiptsFragment receiptsFragment, List<ReceiptsModel> modelList, Context context) {
        this.receiptsFragment = receiptsFragment;
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReceiptsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the single item
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.receipt_single, parent, false);


        ReceiptsViewHolder viewHolder = new ReceiptsViewHolder(itemView);
        //handle click

        viewHolder.setOnClickListener(new ReceiptsViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String name = modelList.get(position).getName();
                String code = modelList.get(position).getCode();
                String plate = modelList.get(position).getPlate();
                String timeEntered = modelList.get(position).getTimeEntered();
                Toast.makeText(itemView.getContext(), name+"\n"+code+"\n"+plate+"\n"+timeEntered, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptsViewHolder holder, int position) {
    //bind views / set data
        holder.tvName.setText(modelList.get(position).getName());
        holder.tvCode.setText(modelList.get(position).getCode());
        holder.tvPlate.setText(modelList.get(position).getPlate());
        holder.tvTimeEntered.setText(modelList.get(position).getTimeEntered());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
