package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class adminlogin extends AppCompatActivity {

    public LinearLayout admintouser;
    public Button adminhome;
    private EditText admindIdfield;
    private EditText adminPASswordfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        admintouser=findViewById(R.id.movetouser);
        adminhome=findViewById(R.id.adminlogin);

        admintouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openuser();
            }
        });

        //getting adminId and password from text field
        admindIdfield=findViewById(R.id.adminidfield);
        adminPASswordfield=findViewById(R.id.adminpasswordfield);

        adminhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminLoginCheck();
            }
        });
    }
    public void openuser(){
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }
    public void openadminhomepage(){
        Intent intent=new Intent(this,adminhomepage.class);
        startActivity(intent);
    }

    public void adminLoginCheck() {

        String username = admindIdfield.getText().toString().trim();
        String password = adminPASswordfield.getText().toString().trim();

        String url = "http://10.0.2.2/communi/adminlogin.php"; // Replace with your PHP script URL

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("res",response);
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Intent intent=new Intent(adminlogin.this,adminhomepage.class);
                                intent.putExtra("idus", username);
                                startActivity(intent);
                            } else {
                                // Login failed, show error message
                                Toast.makeText(adminlogin.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(adminlogin.this, "JSON Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(adminlogin.this, "Volley Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                Log.d("tag1",username);
                Log.d("tag1",password);
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        queue.add(stringRequest);
    }
}