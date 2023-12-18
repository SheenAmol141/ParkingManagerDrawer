package com.sheenjoshuaamol.parkingmanagerdrawer.ui.parklist;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.sheenjoshuaamol.parkingmanagerdrawer.R;

import java.lang.annotation.Documented;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class SpotAdapter extends FirestoreRecyclerAdapter<Spot, SpotAdapter.SpotHolder> {


    SimpleDateFormat format = new SimpleDateFormat("MMMM dd yyyy | hh:mm:ss");
    public SpotAdapter(@NonNull FirestoreRecyclerOptions<Spot> options) {
        super(options);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onBindViewHolder(@NonNull SpotHolder holder, int position, @NonNull Spot model) {

        holder.tvName.setText("Name: " + model.getName());
        holder.tvTime.setText("Time Entered: " + model.getTime());
        if (model.getOccupied()) {
            holder.tvCode.setText(model.getCode() + " - Occupied");
            holder.check.setImageResource(R.drawable.baseline_car_rental_24);
        } else {
            holder.tvCode.setText(model.getCode() + " - Available");
            holder.check.setImageResource(R.drawable.baseline_crop_free_24);
        }

    }

    @NonNull
    @Override
    public SpotHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spot_item, parent, false);
        return new SpotHolder(v);
    }


    public void updateItem(int position, Map<String, Object> map) {
        getSnapshots().getSnapshot(position).getReference().update(map);
    }

    static class SpotHolder extends RecyclerView.ViewHolder {
        TextView tvCode, tvName, tvTime;
        CardView cv;
        ImageView check;

        public SpotHolder(@NonNull View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvName = itemView.findViewById(R.id.tvName);
            tvTime = itemView.findViewById(R.id.tvTime);
            cv = itemView.findViewById(R.id.cv);
            check = itemView.findViewById(R.id.check);
        }

            
    }






}
