package com.sheenjoshuaamol.parkingmanagerdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ShowReceipt extends AppCompatActivity {



    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_receipt);

        //get intent extra data
        HashMap<String, String> map = (HashMap<String, String>) getIntent()
                .getSerializableExtra("map");

        //Initialize views
        TextView name, code, plate, timeEntered, timeExited, totalTime, totalPrice;
        name = findViewById(R.id.tvName);
        code = findViewById(R.id.tvCode);
        plate = findViewById(R.id.tvPlate);
        timeEntered = findViewById(R.id.tvTimeEntered);
        timeExited = findViewById(R.id.tvTimeExited);
        totalTime = findViewById(R.id.tvTotalTime);
        totalPrice = findViewById(R.id.tvTotalPrice);


        String formatPattern = "EEE MMM dd HH:mm:ss z yyyy";
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);

        Date entered;
        try {
            entered = format.parse(map.get("timeEntered"));

            Long totalhours = printDifference(entered, new Date());

            totalTime.setText("Total Hours: " + totalhours );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        name.setText(map.get("name"));
        code.setText("Parking Lot: " + " " +  map.get("code"));
        plate.setText("Plate Number: " + " " +  map.get("plate"));
        timeEntered.setText("Time Entered: " + " " +  map.get("timeEntered"));
        timeExited.setText("Time Exited: " + " " + new Date());

        String price;

        Long c;

        if (printDifference(entered, new Date()) >= 2){
            int pay;
            c = printDifference(entered, new Date());
                c = c - 3;
             pay = 30;
                while (c > 0) {
                    c = c - 1;
                     pay = pay + 5;
                }
                price = "₱" + pay;
        } else {
            price = "₱30";
        }

        totalPrice.setText("Total Payment: "+ price+".00");


        Button payed = findViewById(R.id.payed);
        payed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> record = new HashMap<>();


                record.put("Name", map.get("name"));
                record.put("Parking Spot", map.get("code"));
                record.put("Plate Number", map.get("plate"));
                record.put("Time Entered", map.get("timeEntered"));
                record.put("Time Exited", format.format(new Date()));
                record.put("Total Hours", calc(entered) + " Hours");
                record.put("Total Payment",  price+".00");
                db.collection("RECEIPTS PAYED").add(record).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(ShowReceipt.this, "Receipt Uploaded! Thank you!", Toast.LENGTH_SHORT).show();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 500);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ShowReceipt.this, "Receipt Upload Failed", Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });



    }
    public int calc(Date entered) {
        if (printDifference(entered, new Date()) >= 2){
            int pay;
            Long c = printDifference(entered, new Date());
            c = c - 3;
            pay = 30;
            while (c > 0) {
                c = c - 1;
                pay = pay + 5;
            }
            return pay;
        } else {
            return 30;
        }
    }
    //1 minute = 60 seconds
//1 hour = 60 x 60 = 3600
//1 day = 3600 x 24 = 86400
    public long printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        //long elapsedDays = different / daysInMilli;
        //different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d hours, %d minutes, %d seconds%n",
                elapsedHours, elapsedMinutes, elapsedSeconds);
        return elapsedHours;
    }
}