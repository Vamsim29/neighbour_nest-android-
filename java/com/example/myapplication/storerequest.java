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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class storerequest extends AppCompatActivity {

    public androidx.appcompat.widget.AppCompatButton backbutton;
    public Button submitbutton;
    private EditText fieldusername;
    private EditText fieldusermobileno;

    private String URL = "http://10.0.2.2/communi/storerequest.php";

    public TextView heading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storerequest);

        //we need to get which store item user selected and user Id number
        Intent intent1=getIntent();
        String headlast=intent1.getStringExtra("idofstore");
        String UserIdNum=intent1.getStringExtra("useridnumber");

        //setting up the heading by getting type of request they a want
        heading=findViewById(R.id.textheadingfield);
        StringBuilder resultheading=new StringBuilder();
        resultheading.append("Book A ");
        resultheading.append(headlast);

        //now setting the new heading as the heading of the storerequest page
        heading.setText(resultheading);

        //setting up input field's
        fieldusername=findViewById(R.id.storeusername);
        fieldusermobileno=findViewById(R.id.storeusermobileno);

        backbutton=findViewById(R.id.storerequestbackbtn);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotobackpage();
            }
        });
        submitbutton=findViewById(R.id.servicesubmitbtn);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aftersubmitgotomenupage();
            }
        });
    }
    public void gotobackpage(){
        Intent intent=new Intent(this,menubar.class);
        Intent intent1 = getIntent();
        String UserIdNum=intent1.getStringExtra("useridnumber");
        intent.putExtra("idus", UserIdNum);
        startActivity(intent);}
    public void aftersubmitgotomenupage(){
        //we need to get which store item user selected and user Id number
        Intent intent1=getIntent();
        String headlast=intent1.getStringExtra("idofstore");
        String UserIdNum=intent1.getStringExtra("useridnumber");
            // Get the data from EditText fields
            String tx1 = fieldusername.getText().toString();
            String tx2 = fieldusermobileno.getText().toString();
            //Setting up time
            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // Adjust the pattern as needed
        String formattedDate = dateFormat.format(currentDate);

        // Create a JSON object with the data
            JSONObject jsonData = new JSONObject();
            try {
                Log.d("tag0",UserIdNum);
                Log.d("tag1",tx1);
                Log.d("tag1",tx2);
                Log.d("tag1",headlast);
                jsonData.put("field0", UserIdNum);
                jsonData.put("field1", tx1);
                jsonData.put("field2", tx2);
                jsonData.put("field3", headlast);
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
                                        Toast.makeText(storerequest.this, message, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(storerequest.this, menubar.class);
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