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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.sheenjoshuaamol.parkingmanagerdrawer.R;
import com.sheenjoshuaamol.parkingmanagerdrawer.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    ArrayList<String> stringListRemove = new ArrayList<>();


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


        //query all documents that have occupied set to true
        FirebaseFirestore db = FirebaseFirestore.getInstance();



        String[] stockArr = new String[stringListRemove.size()];
        stockArr = stringListRemove.toArray(stockArr);
        Log.d("TAGARRAY", "onViewCreated: " + stringListRemove.size() + Arrays.toString(stockArr));


        loadNewAdapter();

       //submit button
        getView().findViewById(R.id.submitLot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAGNEWTAG", "onClick: " + get);
                saveLot(v, get);
                loadNewAdapter();
            }
        });

    }

//    private static addItemToRemove(String id) {
//        String[] arr = {};
//        Log.d("TAGREMOVE", Arrays.toString(stringArray));
//        return arr;
//    }

    private void loadNewAdapter(){
        CollectionReference parkingRef = db.collection("PARKING");
        Task<QuerySnapshot> occupiedQuery = parkingRef
                .whereEqualTo("Occupied", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String documentId = document.getId();
                                stringListRemove.add(documentId);
                                // Add documentId to your String[]
                                // ...
                            }
                            String[] stockArr = new String[stringListRemove.size()];
                            stockArr = stringListRemove.toArray(stockArr);
                            Log.d("TAGNEWARRAY", "onComplete: "+ Arrays.toString(stockArr));
                            //load Spinner
                            loadSpinner(stockArr);
                        } else {
                            Log.w("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    private void loadSpinner(String[] removestring) {
        //spinner
        String[] removestringthis = removestring;



        String[] arraystring = getResources().getStringArray(R.array.spinnerStrings);;


        //remove items from removestringthis in arraystring
        int j = 0;
        for (int i = 0; i < arraystring.length; i++) {
            if (!Arrays.asList(removestringthis).contains(arraystring[i])) {
                arraystring[j++] = arraystring[i]; // Shift remaining elements
            }
        }
        arraystring = Arrays.copyOf(arraystring, j); // Trim the array to new size






        Spinner spinner = getView().findViewById(R.id.spinner);
//        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), arr, android.R.layout.simple_spinner_item);



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arraystring);


        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
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
 }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}