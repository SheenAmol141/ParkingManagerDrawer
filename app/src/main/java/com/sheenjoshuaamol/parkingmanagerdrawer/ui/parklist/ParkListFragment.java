package com.sheenjoshuaamol.parkingmanagerdrawer.ui.parklist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sheenjoshuaamol.parkingmanagerdrawer.R;

public class ParkListFragment extends Fragment {

    private ParkListViewModel mViewModel;

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

}