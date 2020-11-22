package hfad.com.toddybusinessman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import  hfad.com.toddybusinessman.ConnectionString;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void buttonClick(View view)
    {
        TextInputLayout nameBox = findViewById(R.id.nameText);
        TextInputLayout permitBox=findViewById(R.id.permitText);
        Spinner businessBox=(Spinner)findViewById(R.id.businesstype);
        TextInputLayout locationBox=findViewById(R.id.location);
        TextInputLayout emailBox=findViewById(R.id.emailText);
        TextInputLayout pass1Box=findViewById(R.id.passwordText1);
        TextInputLayout pass2Box=findViewById(R.id.passwordText2);

         String nameText=nameBox.getEditText().getText().toString().trim();
        String permitText=permitBox.getEditText().getText().toString().trim();

         String businessType=businessBox.getSelectedItem().toString().trim();
        String locationText=locationBox.getEditText().getText().toString().trim();
        String emailText=emailBox.getEditText().getText().toString().trim();
        String pass1Text=pass1Box.getEditText().getText().toString().trim();



        Log.i("MyActivity", "name======== "+nameText);
        Log.i("MyActivity", "name======== "+businessType);
        registerFunction(nameText,permitText,businessType,locationText,emailText,pass1Text);











    }
    public void registerFunction(String name,String permit,String business,String location,String email,String pass1)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("name", name);
            postData.put("permit", permit);
            postData.put("business",business);
            postData.put("location", location);
            postData.put("email", email);
            postData.put("pass1", pass1);
            volleyPost(postData);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void volleyPost(JSONObject postData){
        ConnectionString obj=new ConnectionString();

        String postUrl = obj.connectioncode+"businessman/register";
        RequestQueue requestQueue = Volley.newRequestQueue(this);



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
    public void buttonClick22(View view)
    {
        TextInputLayout textInputLayout = findViewById(R.id.permitText);
        String text1=textInputLayout.getEditText().toString();
        final TextInputLayout text2=findViewById(R.id.nameText);
        // text2.getEditText().setText("dfdff");
        // Instantiate the RequestQueue.



        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.8.104:3000";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // text2.getEditText().setText("suceessfill");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("MyActivity", "MyClass.getView() — get item number "+error);


                // text2.getEditText().setText(error);
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);



    }

}