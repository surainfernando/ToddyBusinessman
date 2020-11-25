package hfad.com.toddybusinessman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
        loginStatusCheck();
    }

    public void buttonClick(View view)
    {

        TextInputLayout emailBox = findViewById(R.id.emailText);
        TextInputLayout passswordBox=findViewById(R.id.passwordText);
        passswordBox.setError(null);
        emailBox.setError(null);
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
            volleyPost(postData); ///calls the volley method to make request. Email, password values are passed to volleyPost() method

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
                handleResponse(response);// After the trespons e is recieved handleResponse() method will be called to read json response and take next steps.
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public void handleResponse(JSONObject obj)
    { // If username or password is incorrect error message wil be displayed. Else sharedpreferenses will be set with json data and navigate to home screen.
        TextInputLayout emailBox = findViewById(R.id.emailText);
        String errorMessage="The email or the password which you entered is incorrect";
        try {
            boolean status = obj.getBoolean("status");
            if(status)
            {
                Log.i("MyActivity", "Json objSuccessful Login");
                setPref(obj);

            }
            else
            {
                TextInputLayout passswordBox=findViewById(R.id.passwordText);
                passswordBox.setError("The email or the password which you entered is incorrect");
                //TextInputLayout emailBox=findViewById(R.id.emailText);
                emailBox.setError("The email or the password which you entered is incorrect");



            }
            //  Block of code to try
        }
        catch(Exception e) {
            //  Block of code to handle errors
        }



    }

    public void setPref(JSONObject response)
    {
        Context context=getApplication();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        try{
            String name1=response.getString("name");
            String permit=response.getString("permit");
            String business_type=response.getString("business_type");
            String noOfTrees=response.getString("noOfTrees");
            String location=response.getString("location");
            String email=response.getString("email");
            String password=response.getString("password");

            Log.i("----------name is=0000", name1);

            editor.putString("name",name1);
           editor.putString("permit",permit);
          editor.putString("business_type",business_type);
         editor.putString("noOfTrees",noOfTrees);
            editor.putString("location",location);
            editor.putString("email",email);
           editor.putString("password",password);
           editor.putString("loggedon","true");
            editor.apply();

            TextInputLayout emailBox = findViewById(R.id.emailText);
            TextInputLayout passswordBox=findViewById(R.id.passwordText);
            emailBox.getEditText().setText("");
            passswordBox.getEditText().setText("");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();




        }
        catch(Exception e)
        {

        }



    }

    public void loginStatusCheck()
    {SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String loggedon = preferences.getString("loggedon", "");
        if(loggedon.equals("true"))
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void dispref(View view)
    { SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("name", "");
        String permit = preferences.getString("permit", "");
        String business_type = preferences.getString("business_type", "");
        String noOfTrees = preferences.getString("noOfTrees", "");
        String location = preferences.getString("location", "");
        String email = preferences.getString("email", "");
        String password = preferences.getString("password", "");
        String loggedon = preferences.getString("loggedon", "");








        Log.i("MyActivity", "name= "+name);
        Log.i("MyActivity", "permit= "+permit);
        Log.i("MyActivity", "business= "+business_type);
        Log.i("MyActivity", "trees=r "+noOfTrees);
        Log.i("MyActivity", "Elocation= "+location);
        Log.i("MyActivity", "Email= "+email);
        Log.i("MyActivity", "passw= "+password);
        Log.i("MyActivity", "Email= "+loggedon);

        Log.i("MyActivity", "nam000000000000000000000000000e= ");
        //JSONObject obj=(JSONObject)preferences;
        //Log.i("MyActivity", "name= "+obj);

    }
    public void registerscreen(View view)
    {
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }
}