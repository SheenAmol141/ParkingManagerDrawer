package com.sheenjoshuaamol.parkingmanagerdrawer.ui.parklist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.sheenjoshuaamol.parkingmanagerdrawer.R;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ParkListFragment extends Fragment {
    private ParkListViewModel mViewModel;
    RecyclerView rvParkList;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CollectionReference parkingCollection = db.collection("PARKING");

    SpotAdapter adapter;
    public static ParkListFragment newInstance() {
        return new ParkListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_park_list, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ParkListViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvParkList = getView().findViewById(R.id.rvParkList);

        Query query = parkingCollection.orderBy("code", Query.Direction.ASCENDING);
        adapter = new SpotAdapter(new FirestoreRecyclerOptions.Builder<Spot>().setQuery(query, Spot.class).build());


        rvParkList = view.findViewById(R.id.rvParkList);
        rvParkList.setHasFixedSize(true);
        rvParkList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvParkList.setAdapter(adapter);


        Map<String, Object> mapfree = new HashMap<>();

        mapfree.put("name", "");
        mapfree.put("Occupied", false);
        mapfree.put("plate", "");
        mapfree.put("time", "");

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.updateItem(viewHolder.getAdapterPosition(), mapfree);
            }
        }).attachToRecyclerView(rvParkList);

    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}