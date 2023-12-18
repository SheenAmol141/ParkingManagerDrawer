package com.sheenjoshuaamol.parkingmanagerdrawer.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sheenjoshuaamol.parkingmanagerdrawer.R;
import com.sheenjoshuaamol.parkingmanagerdrawer.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private String get;

    private FragmentHomeBinding binding;
    private static final String KEY_CODE = "code";
    private static final String KEY_NAME = "name";
    private static final String KEY_PLATE = "plate";
    private static final String KEY_TIME = "time";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "EEE MMM dd HH:mm:ss 'GMT'XXX yyyy",
            Locale.US
    );
    Calendar current = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy | hh:mm:ss");

    EditText etCode, etName, etPlate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        etName = getView().findViewById(R.id.fireinputName);
        etPlate = getView().findViewById(R.id.fireinputPlate);

        //spinner
        Spinner spinner = getView().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.spinnerStrings, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);


        getView().findViewById(R.id.submitLot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAGNEWTAG", "onClick: " + get);
                saveLot(v, get);

            }
        });

    }

    public void saveLot(View v, String spin) {
        String code = spin;
        String name = etName.getText().toString();
        String plate = etPlate.getText().toString();


        Map<String, Object> lot = new HashMap<>();

        lot.put(KEY_CODE, code);
        lot.put(KEY_NAME, name);
        lot.put("Occupied", true);
        lot.put(KEY_PLATE, plate);
        lot.put(KEY_TIME, String.valueOf(dateFormat.format(new Date())));



        Map<String, Object> record = new HashMap<>();


        record.put(KEY_NAME, name);
        record.put("search", name.toLowerCase());
        record.put(KEY_CODE, code);
        record.put(KEY_PLATE, plate);
        record.put(KEY_TIME, String.valueOf(dateFormat.format(new Date())));

        db.collection("PARKING").document(code).set(lot).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(), "Thank You for Submitting", Toast.LENGTH_SHORT).show();

                etName.setText("");
                etPlate.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Process Failed, Please Try Again or call a Representative", Toast.LENGTH_SHORT).show();
            }
        });

        db.collection("RECORDS").add(record);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        get = text;
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
 }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}