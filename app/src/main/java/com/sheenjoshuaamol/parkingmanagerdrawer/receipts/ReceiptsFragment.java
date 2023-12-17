package com.sheenjoshuaamol.parkingmanagerdrawer.receipts;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sheenjoshuaamol.parkingmanagerdrawer.R;

import java.util.ArrayList;
import java.util.List;

public class ReceiptsFragment extends Fragment {

    private ReceiptsViewModel mViewModel;
    RecyclerView rvReceipts;
    List<ReceiptsModel> modelList = new ArrayList<>();
    //layout manager for recycler
    RecyclerView.LayoutManager layoutManager;

    //firestore instance
    FirebaseFirestore db;
    ReceiptsAdapter adapter;


    public static ReceiptsFragment newInstance() {
        return new ReceiptsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_receipts, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReceiptsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize recycler object view
        rvReceipts = getActivity().findViewById(R.id.rvReceipts);

        //initialize firestore
        db = FirebaseFirestore.getInstance();

        //set recycler view properties
        rvReceipts.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rvReceipts.setLayoutManager(layoutManager);

        //show data in recycler view
        showData();




        //search bar

    }
    private void showData() {
        db.collection("RECORDS")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Toast.makeText(getContext(), "Records Retrieved", Toast.LENGTH_SHORT).show();

                        for (DocumentSnapshot doc : task.getResult()) {
                            ReceiptsModel model = new ReceiptsModel(doc.getString("name"),
                                    doc.getString("code"),
                                    doc.getString("plate"),
                                    doc.getString("time"));
                            modelList.add(model);
                        }

                        //adapter
                        adapter = new ReceiptsAdapter(ReceiptsFragment.this, modelList, getContext());
                        rvReceipts.setAdapter(adapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Records Retrieve Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
