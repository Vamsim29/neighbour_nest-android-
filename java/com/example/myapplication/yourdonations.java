package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class yourdonations extends AppCompatActivity {

    //back button creation
    public androidx.appcompat.widget.AppCompatButton donationsreqbackbutton;
    public Button btnadddonation;

    private TextView dataDisplayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourdonations);

        //back button intialization
        donationsreqbackbutton=findViewById(R.id.youtdonationspagebackbutton);
        donationsreqbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotodonatepage();
            }
        });

        //add donation button intialization
        btnadddonation=findViewById(R.id.yourdonationsadddonationbutton);
        btnadddonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotomakeadonationpage();
            }
        });

        dataDisplayTextView=findViewById(R.id.yourdonheading);

        // Fetch orders for the specific user
        fetchUserYourDonations();
    }
    //back button function
    public void gotodonatepage(){
        Intent intent=new Intent(this,donatepage.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }
    public void gotomakeadonationpage(){
        Intent intent=new Intent(this,makeadonation.class);
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        intent.putExtra("idus", userIdp);
        startActivity(intent);
    }

    // Helper methods for styling
    private SpannableStringBuilder getBoldText(String text) {
        SpannableStringBuilder boldText = new SpannableStringBuilder(text);
        boldText.setSpan(new StyleSpan(Typeface.BOLD), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return boldText;
    }

    private SpannableStringBuilder getRedText(String text) {
        SpannableStringBuilder redText = new SpannableStringBuilder(text);
        redText.setSpan(new ForegroundColorSpan(Color.RED), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return redText;
    }


    //the data can be displayed by using this function
    private void fetchUserYourDonations() {
        // getting the userId from login page
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        //String userIdp = "t24f";
        String url = "http://10.0.2.2/communi/yourdonationsdatadisplay.php"; // Replace with your PHP script's URL

        // Initialize the Volley request queue
        RequestQueue requestQueue1 = Volley.newRequestQueue(this);

        // Create a StringRequest to make a POST request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    // Process the JSON response and display orders' details
                    Log.d("JSON Response", response); // Log the cleaned JSON response
                    JSONObject jsonResponse = new JSONObject(response);
                    LinearLayout dataDisplayLinearLayout = findViewById(R.id.dataDisplayLinearLayout);

                    if (jsonResponse.getBoolean("status")) {
                        JSONArray jsonArray = jsonResponse.getJSONArray("data");
                        if (jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject order = jsonArray.getJSONObject(i);
                                String des = order.getString("description");
                                String dat = order.getString("datatime");
                                String accroom = order.getString("accbyroomid");
                                String accmob = order.getString("accbymobno");
                                String takebyn= accroom.concat(",").concat(accmob);
                                String actions = order.getString("status");

                                // Inflate the new layout for each record
                                View detailView = getLayoutInflater().inflate(R.layout.your_donations_box, null);

                                // Find the TextViews, Spinner, and Button in the inflated layout
                                TextView detaildesTextView = detailView.findViewById(R.id.mdes);
                                TextView date = detailView.findViewById(R.id.mdt);
                                TextView detailtakenbyTextView = detailView.findViewById(R.id.mtakby);
                                TextView action = detailView.findViewById(R.id.mact);
                                Spinner statusSpinner = detailView.findViewById(R.id.statusSpinner);
                                Button updateButton = detailView.findViewById(R.id.updateButton);

                                // Set the data to the TextViews
                                detaildesTextView.setText("Description: " + des);
                                date.setText("Date: " + dat);
                                detailtakenbyTextView.setText("Taken By: " + takebyn);
                                action.setText("Actions: " + actions);

                                // Set up the Spinner with the dropdown items
                                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                                        R.array.status_array_two, android.R.layout.simple_spinner_item);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                statusSpinner.setAdapter(adapter);

                                // Set up the update button click listener
                                updateButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // Handle the update logic here
                                        String selectedStatus = statusSpinner.getSelectedItem().toString();
                                        // Add your logic to update the data in the database with the selectedStatus
                                        // You may use a separate method to handle the update logic
                                        updateData(userIdp,dat,selectedStatus);
                                    }
                                });

                                // Add the inflated layout to your main layout
                                // Assume dataDisplayLinearLayout is the layout container in your main XML
                                dataDisplayLinearLayout.addView(detailView);
                            }
                        } else {
                            // Handle case when no data is found
                            dataDisplayTextView.setText("No data found for this user.");
                        }
                    } else {
                        // Handle other status scenarios, if needed
                        dataDisplayTextView.setText("No donations");
                    }
                } catch (Exception e) {
                    dataDisplayTextView.setText("Error: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle any errors here
                dataDisplayTextView.setText("Error: " + error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Send the username and password as POST parameters
                Map<String, String> data = new HashMap<>();
                data.put("userId",userIdp);

                return data;
            }
        };

        // Customize the retry policy
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Initialize the Volley request queue and add the request
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    // Method to handle the update logic
    private void updateData(String room,String DateTime ,String selectedStatus) {
        // Implement your logic to update the data in the database with the selected status
        // You may use Volley or another method to make an update request
        // Display a message or perform any additional actions based on the update result
        String updateUrl = "http://10.0.2.2/communi/updateyourdonationsrecord.php"; // Replace with your PHP script's update URL
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create a StringRequest to make a POST request for updating data
        StringRequest updateRequest = new StringRequest(Request.Method.POST, updateUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the update response
                        Log.d("Update Response", response);
                        finish();
                        startActivity(getIntent());
                        // You can display a message or perform other actions based on the update result
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle errors during the update request
                Log.e("Update Error", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Set parameters for the update request
                Map<String, String> params = new HashMap<>();
                params.put("roomid", room);
                params.put("dttm",DateTime);
                params.put("status", selectedStatus);
                return params;
            }
        };

        // Add the update request to the Volley request queue
        requestQueue.add(updateRequest);
    }

}