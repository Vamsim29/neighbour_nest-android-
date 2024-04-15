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

public class login extends AppCompatActivity {

    private LinearLayout move;
    private EditText usernameEditText;
    private EditText passwordEditText;

    private androidx.appcompat.widget.AppCompatButton transfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       usernameEditText=findViewById(R.id.useridfield);
       passwordEditText=findViewById(R.id.passwordfield);

        move=findViewById(R.id.adminpagebtn);
        transfer=findViewById(R.id.userlogin);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openadminlogin();
            }
        });
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userloginProcess();
            }
        });
    }
    public void openadminlogin(){
        Intent intent=new Intent(this,adminlogin.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }

    public void openhomepage(){
        Intent intent=new Intent(this,menubar.class);
        intent.putExtra("idus", usernameEditText.getText().toString().trim());
        startActivity(intent);
    }
    //loginprocess starts
    public void userloginProcess() {

        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        String url = "http://10.0.2.2/communi/userlogin.php"; // Replace with your PHP script URL

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
                                Intent intent=new Intent(login.this,menubar.class);
                                intent.putExtra("idus", username);
                                startActivity(intent);
                            } else {
                                // Login failed, show error message
                                Toast.makeText(login.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(login.this, "JSON Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login.this, "Volley Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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

//loginprocessends
}