package com.sheenjoshuaamol.parkingmanagerdrawer.receipts;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sheenjoshuaamol.parkingmanagerdrawer.R;

public class ReceiptsViewHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvCode, tvPlate, tvTimeEntered;
    View mView;
    public ReceiptsViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;

        //item Click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });
        //item long Click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
                return true;
            }
        });


        //initialize every view object
        tvName = itemView.findViewById(R.id.tvName);
        tvCode = itemView.findViewById(R.id.tvCode);
        tvPlate = itemView.findViewById(R.id.tvPlate);
        tvTimeEntered = itemView.findViewById(R.id.tvTimeEntered);
    }

    private ReceiptsViewHolder.ClickListener mClickListener;
    //interface of click listeners
    public interface  ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ReceiptsViewHolder.ClickListener clickListener) {
    mClickListener = clickListener;
    }
}
