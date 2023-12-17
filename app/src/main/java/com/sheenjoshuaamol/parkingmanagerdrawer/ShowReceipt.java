package com.sheenjoshuaamol.parkingmanagerdrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ShowReceipt extends AppCompatActivity {

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

        Date exited = new Date();

        String formatPattern = "EEE MMM dd HH:mm:ss z yyyy";
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);

        Date entered;
        try {
            entered = format.parse(map.get("timeEntered"));














            totalTime.setText("" + entered);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        name.setText(map.get("name"));
        code.setText("Parking Lot: " + " " +  map.get("code"));
        plate.setText("Plate Number: " + " " +  map.get("plate"));
        timeEntered.setText("Time Entered: " + " " +  map.get("timeEntered"));
        timeExited.setText("Time Exited: " + " " + new Date());


    }
}