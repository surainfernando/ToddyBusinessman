package hfad.com.toddybusinessman;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        Spinner businessBox=(Spinner)findViewById(R.id.businesstype);
        final TextInputLayout treeNumber = findViewById(R.id.treeNumber);

        businessBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        treeNumber.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        treeNumber.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        treeNumber.setVisibility(View.INVISIBLE);
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });
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
        TextInputLayout noOfTreesBox=findViewById(R.id.treeNumber);
        emailBox.setError(null);
        permitBox.setError(null);

         String nameText=nameBox.getEditText().getText().toString().trim();
        String permitText=permitBox.getEditText().getText().toString().trim();

         String businessType=businessBox.getSelectedItem().toString().trim();
        String locationText=locationBox.getEditText().getText().toString().trim();
        String emailText=emailBox.getEditText().getText().toString().trim();
        String pass1Text=pass1Box.getEditText().getText().toString().trim();
        String pass2Text=pass2Box.getEditText().getText().toString().trim();

        Boolean oneisEmpty=false;
        int noOfTrees=0;

        if( nameText == null ||  nameText.trim().isEmpty()) {
            oneisEmpty=true;

        }
        if( permitText == null || permitText.trim().isEmpty()) {
            oneisEmpty=true;

        }
        if( locationText == null ||  locationText.trim().isEmpty()) {
            oneisEmpty=true;

        }
        if( emailText == null ||  emailText.trim().isEmpty()) {
            oneisEmpty=true;

        }
        if( pass1Text == null ||  pass1Text.trim().isEmpty()) {
            oneisEmpty=true;

        }
        if( pass2Text == null ||  pass2Text.trim().isEmpty()) {
            oneisEmpty=true;

        }

        if(businessType.equals(("Toddy Producer")))
        { try{
            String x=noOfTreesBox.getEditText().getText().toString();
           noOfTrees=Integer.valueOf(x);
        }
        catch(Exception e)
        {}

        }
        else
        {
            noOfTrees=0;

        }


        if(oneisEmpty)
        {
            Toast toast=Toast.makeText(getApplicationContext(),"Please Fill All Fields",Toast.LENGTH_SHORT);

            toast.show();
        }
        else
        {
            registerFunction(nameText,permitText,businessType,locationText,emailText,pass1Text,noOfTrees);
        }






    }
    public void registerFunction(String name,String permit,String business,String location,String email,String pass1,int noOfTrees)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("name", name);
            postData.put("permit", permit);
            postData.put("business",business);
            postData.put("location", location);
            postData.put("email", email);
            postData.put("pass1", pass1);
            postData.put("noOfTrees", noOfTrees);
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
              // System.out.println(response);
                handleResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("MyActivity", "New Emaicdfffffffffffffffffffffffffffffffffffffffffffffffffffl");
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public void handleResponse(JSONObject obj)
    {

        try{
            Boolean status=obj.getBoolean("status");
            String error=obj.getString("error");

            if(status)
            {

                Log.i("MyActivity", "New Email");
                setPref();



            }
            else
            {
                if(error.equals("email"))
                {
                    TextInputLayout emailBox=findViewById(R.id.emailText);
                    emailBox.setError("The email which you enterd already exists.");
                }
                else
                {
                    TextInputLayout permitBox=findViewById(R.id.permitText);
                    permitBox.setError("The permit which you enterd already exists.");

                }

            }

        }
        catch(Exception e)
        {

        }


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

    public void clearText()
    {
        TextInputLayout nameBox = findViewById(R.id.nameText);
        TextInputLayout permitBox=findViewById(R.id.permitText);
        Spinner businessBox=(Spinner)findViewById(R.id.businesstype);
        TextInputLayout locationBox=findViewById(R.id.location);
        TextInputLayout emailBox=findViewById(R.id.emailText);
        TextInputLayout pass1Box=findViewById(R.id.passwordText1);
        TextInputLayout pass2Box=findViewById(R.id.passwordText2);
        TextInputLayout noOfTreesBox=findViewById(R.id.treeNumber);

        nameBox.getEditText().setText("");
        permitBox.getEditText().setText("");
        locationBox.getEditText().setText("");
        emailBox.getEditText().setText("");
        pass1Box.getEditText().setText("");
        pass2Box.getEditText().setText("");
        noOfTreesBox.getEditText().setText("");
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);




    }

    public void setPref()
    {

        TextInputLayout nameBox = findViewById(R.id.nameText);
        TextInputLayout permitBox=findViewById(R.id.permitText);
        Spinner businessBox=(Spinner)findViewById(R.id.businesstype);
        TextInputLayout locationBox=findViewById(R.id.location);
        TextInputLayout emailBox=findViewById(R.id.emailText);
        TextInputLayout pass1Box=findViewById(R.id.passwordText1);
        TextInputLayout pass2Box=findViewById(R.id.passwordText2);
        TextInputLayout noOfTreesBox=findViewById(R.id.treeNumber);
        emailBox.setError(null);
        permitBox.setError(null);

        String nameText=nameBox.getEditText().getText().toString().trim();
        String permitText=permitBox.getEditText().getText().toString().trim();

        String businessType=businessBox.getSelectedItem().toString().trim();
        String locationText=locationBox.getEditText().getText().toString().trim();
        String emailText=emailBox.getEditText().getText().toString().trim();
        String pass1Text=pass1Box.getEditText().getText().toString().trim();
        String pass2Text=pass2Box.getEditText().getText().toString().trim();
        int noOfTrees=0;
        if(businessType.equals(("Toddy Producer")))
        { try{
            String x=noOfTreesBox.getEditText().getText().toString();
            noOfTrees=Integer.valueOf(x);
        }
        catch(Exception e)
        {}

        }
        else
        {
            noOfTrees=0;

        }

        Context context=getApplication();


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        try{


            editor.putString("name",nameText);
            editor.putString("permit",permitText);
            editor.putString("business_type",businessType);
            editor.putString("noOfTrees",String.valueOf(noOfTrees));
            editor.putString("location",locationText);
            editor.putString("email",emailText);
            editor.putString("password",pass1Text);
            editor.putString("loggedon","true");
            editor.apply();
            clearText();


            //Intent intent=new Intent(this,MainActivity.class);
            //(intent);




        }
        catch(Exception e)
        {

        }



    }
    public void dispref(View view)
    { SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("name", "");
        String email = preferences.getString("email", "");
        String noOfTrees = preferences.getString("noOfTrees", "");
        String permit=preferences.getString("permit", "");
        String logg=preferences.getString("loggedon", "");


        Log.i("MyActivity", "name= "+name);
        Log.i("MyActivity", "Email= "+email);
        Log.i("MyActivity", "trees=r "+noOfTrees);
       // Log.i("MyActivity", "permir "+noOfTrees);
        Log.i("MyActivity", "loged=r "+logg);


    }
    public void dispref2(View view)
    {
        String x="200";
        int y=0;
        try{
            y=Integer.valueOf(x);
            y=y+2;

        }
        catch(Exception d)
        {

        }
        Log.i("MyActivity", "loged=r "+String.valueOf(y));

    }

}