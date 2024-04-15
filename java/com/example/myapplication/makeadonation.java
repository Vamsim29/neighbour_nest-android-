package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class makeadonation extends AppCompatActivity {

    public androidx.appcompat.widget.AppCompatButton backbuttontoyourdonations;
    public androidx.appcompat.widget.AppCompatButton buttonsubmit;

    private EditText fieldusername;
    private EditText fielduserdes;
    private TextView headingtext;
    private String URL = "http://10.0.2.2/communi/makedonation.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makeadonation);

        //setting up input field's
        fieldusername=findViewById(R.id.fieldusern);
        fielduserdes=findViewById(R.id.fielduserdescription);
        headingtext=findViewById(R.id.heading);

        backbuttontoyourdonations=findViewById(R.id.backtoyourdonationsbutton);
        backbuttontoyourdonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoyourdonationspage();
            }
        });
        buttonsubmit=findViewById(R.id.makeadonationsubmitbutton);
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aftersubmitgotobackpage();
            }
        });
    }
    //functions to redirect to back page
    public void gotoyourdonationspage(){
        Intent intent=new Intent(this,yourdonations.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
    //function to submit details and go to back page
    public void aftersubmitgotobackpage(){
        //we need to get which store item user selected and user Id number
        Intent intent1=getIntent();
        String UserIdNum=intent1.getStringExtra("idus");
        // Get the data from EditText fields
        String tx1 = fieldusername.getText().toString();
        String tx2 = fielduserdes.getText().toString();

        // Create a JSON object with the data
        JSONObject jsonData = new JSONObject();
        try {
            Log.d("tag0",UserIdNum);
            Log.d("tag1",tx1);
            Log.d("tag1",tx2);
            jsonData.put("field0", UserIdNum);
            jsonData.put("field1", tx1);
            jsonData.put("field2", tx2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Send the JSON data to the PHP script using Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Check if the JSON response contains a "status" key
                        if (response.has("status")) {
                            String status = null;
                            try {
                                status = response.getString("status");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                            if (status.equals("success")) {
                                // Data was updated successfully
                                try {
                                    String message = response.getString("message");
                                    Toast.makeText(makeadonation.this, message, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(makeadonation.this, yourdonations.class);
                                    intent.putExtra("idus",UserIdNum);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            } else {
                                // Data update was not successful
                                try {
                                    String message = response.getString("message");
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        } else {
                            String res="Error Occurred";
                            headingtext.setText(res);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors (if needed)
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}