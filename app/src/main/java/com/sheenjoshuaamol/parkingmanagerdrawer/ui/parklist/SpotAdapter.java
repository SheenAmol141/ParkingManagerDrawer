package com.sheenjoshuaamol.parkingmanagerdrawer.ui.parklist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.sheenjoshuaamol.parkingmanagerdrawer.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SpotAdapter extends FirestoreRecyclerAdapter<Spot, SpotAdapter.SpotHolder> {

    SimpleDateFormat format = new SimpleDateFormat("MMMM dd yyyy | hh:mm:ss");
    public SpotAdapter(@NonNull FirestoreRecyclerOptions<Spot> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SpotHolder holder, int position, @NonNull Spot model) {
        holder.tvCode.setText(model.getCode());
        holder.tvName.setText(model.getName());
//        holder.tvTime.setText(model.getTime().toString());
    }

    @NonNull
    @Override
    public SpotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spot_item, parent, false);
        return new SpotHolder(v);
    }

    class SpotHolder extends RecyclerView.ViewHolder {
        TextView tvCode, tvName, tvTime;

        public SpotHolder(@NonNull View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvName = itemView.findViewById(R.id.tvName);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
