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
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class inboxdonations extends AppCompatActivity {

    //back button creation
    public androidx.appcompat.widget.AppCompatButton donationsreqbackbutton;
    private TextView dataDisplayTextView;

    private TextView headinbox;

    private TextView plainhaed;
    private TextView datastore;

    private String ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inboxdonations);
        //back button intialization
        headinbox=findViewById(R.id.inboxdonheading);
        plainhaed=findViewById(R.id.nullheading);
        datastore=findViewById(R.id.tostoremb);
        donationsreqbackbutton=findViewById(R.id.donreqbackbtn);
        donationsreqbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotobackpage();
            }
        });

        fetchuserinboxdonations();
    }
    //back button function
    public void gotobackpage(){
        Intent intent=new Intent(this,donatepage.class);
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
    private void fetchuserinboxdonations() {
        //getting the userId from login page
        Intent intent1 = getIntent();
        String userIdp = intent1.getStringExtra("idus");
        boolean a=true;
        if(a)
        {
            String puserIdp = userIdp;
            String url = "http://10.0.2.2/communi/userprofile.php"; // Replace with your PHP script's URL

            RequestQueue requestQueue1 = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        int indexOfJsonEnd = response.indexOf("}");
                        if (indexOfJsonEnd != -1) {
                            String jsonResponse = response.substring(0, indexOfJsonEnd + 1);

                            Log.d("JSON Response", jsonResponse); // Log the cleaned JSON response

                            Gson gson = new Gson();
                            JsonObject jsonObject_one = gson.fromJson(jsonResponse, JsonObject.class);

                             ans = jsonObject_one.get("mobileno").getAsString();

                        }
                        else{
                            plainhaed.setText("Error: JSON format issue");
                        }


                    } catch (Exception e) {
                        plainhaed.setText("Error: " + e.getMessage());
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle any errors here
                    plainhaed.setText("Error: " + error.getMessage());
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    // Send the username and password as POST parameters
                    Map<String, String> data = new HashMap<>();
                    data.put("userId",puserIdp);

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
        String url = "http://10.0.2.2/communi/inboxdonationsdatadisplay.php"; // Replace with your PHP script's URL

        RequestQueue requestQueue1 = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    StringBuilder ordersDetails = new StringBuilder();
                    // Process the JSON response and display orders' details
                    Log.d("JSON Response", response); // Log the cleaned JSON response
                    JSONObject jsonResponse = new JSONObject(response);
                    LinearLayout dataDisplayLinearLayout = findViewById(R.id.dataDisplayLinearLayout);

                    if (jsonResponse.getBoolean("status")) {
                        JSONArray jsonArray = jsonResponse.getJSONArray("data");
                        if (jsonArray.length() > 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject order = jsonArray.getJSONObject(i);
                                String room=order.getString("roomid");
                                String name=order.getString("name");
                                String des = order.getString("description");
                                String datetime=order.getString("datatime");
                                String actions = order.getString("status");

                                // Inflate the new layout for each record
                                View detailView = getLayoutInflater().inflate(R.layout.inbox_donations_page, null);

                                // Find the TextViews, Spinner, and Button in the inflated layout
                                TextView detailnameTextView = detailView.findViewById(R.id.n);
                                TextView detaildesTextView = detailView.findViewById(R.id.s);
                                TextView action = detailView.findViewById(R.id.act);
                                Button updateButton = detailView.findViewById(R.id.updateButton);

                                // Set the data to the TextViews
                                detailnameTextView.setText("Name: " + name);
                                detaildesTextView.setText("Description: " + des);

                                if(actions.equals("Available")) {
                                    //update the text in the button
                                    updateButton.setText("Available");
                                    // Set up the update button click listener
                                    updateButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            // Handle the update logic here
                                            String selectedStatus = "Taken";
                                            // Add your logic to update the data in the database with the selectedStatus
                                            // You may use a separate method to handle the update logic
                                            updateData(room, selectedStatus, datetime,userIdp,ans);
                                        }
                                    });
                                }
                                else
                                {
                                    updateButton.setOnClickListener(null);
                                    updateButton.setText("Taken");
                                    updateButton.setBackgroundColor(Color.rgb(0,2,1));
                                }
                                // Add the inflated layout to your main layout
                                // Assume dataDisplayLinearLayout is the layout container in your main XML
                                dataDisplayLinearLayout.addView(detailView);
                            }
                        }
                        else {
                            // Handle case when no data is found
                            plainhaed.setText("No data found for this user.");
                        }
                    }
                    else{
                        // Handle other status scenarios, if needed
                        plainhaed.setText("Data Extraction Failed");

                    }
                    // Set orders' details to the TextView
                    plainhaed.setText(ordersDetails.toString());
                } catch (Exception e) {
                    plainhaed.setText("Error: Hii" + e.getMessage());
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle any errors here
                dataDisplayTextView.setText("Error: Hello" + error.getMessage());
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
        ;
    }
    private void updateData(String room, String selectedStatus,String timedate,String idofuser,String mbofuser) {
        // Implement your logic to update the data in the database with the selected status
        // You may use Volley or another method to make an update request
        // Display a message or perform any additional actions based on the update result
        String updateUrl = "http://10.0.2.2/communi/inboxdonationupdate.php"; // Replace with your PHP script's update URL
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
                params.put("dttm",timedate);
                params.put("status", selectedStatus);
                params.put("userid", idofuser);
                params.put("userphno", mbofuser);
                return params;
            }
        };

        // Add the update request to the Volley request queue
        requestQueue.add(updateRequest);
    }
}