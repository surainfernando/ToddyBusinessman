package hfad.com.toddybusinessman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import  hfad.com.toddybusinessman.ConnectionString;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void buttonClick(View view)
    {

        TextInputLayout emailBox = findViewById(R.id.emailText);
        TextInputLayout passswordBox=findViewById(R.id.passwordText);
        String email=emailBox.getEditText().getText().toString().trim();
        String password=passswordBox.getEditText().getText().toString().trim();
        login(email,password);



    }

    public void login(String email,String password)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email);
            postData.put("password", password);
            volleyPost(postData);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void volleyPost(JSONObject postData){
        ConnectionString obj=new ConnectionString();

        String postUrl = obj.connectioncode+"businessman/login";
        RequestQueue requestQueue = Volley.newRequestQueue(this);



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i("MyActivity", "MyClass.getView() — get item number "+response);
                changetext(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public void changetext(JSONObject obj)
    {
        TextInputLayout emailBox = findViewById(R.id.emailText);
        try {
            boolean status = obj.getBoolean("status");
            if(status)
            {emailBox.getEditText().setText("Pass");}
            else
            {{emailBox.getEditText().setText("Fail");}}
            //  Block of code to try
        }
        catch(Exception e) {
            //  Block of code to handle errors
        }



    }

    public void setPref(View view)
    {
        Context context=getApplication();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name","Harneet");
        editor.apply();
    }

    public void dispref(View view)
    { SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("name", "");
        Log.i("MyActivity", "MyClass.getView() — get item number "+name);

    }
}