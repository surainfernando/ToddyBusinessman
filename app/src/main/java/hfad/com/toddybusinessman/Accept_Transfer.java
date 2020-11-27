package hfad.com.toddybusinessman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Accept_Transfer extends AppCompatActivity {
TextView producenameView;
TextView dateView;
TextView volumeView;
TextView creatorPermit;
int requestid;
int batchid;
String name;
String permitNumber;
int volume;
String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        requestid=intent.getIntExtra("RequestID",0);
//        name=intent.getStringExtra("name");
//        permitNumber=intent.getStringExtra("permitNumber");
        volume=intent.getIntExtra("volume",0);
        date=intent.getStringExtra("date");
        batchid=intent.getIntExtra("batchID",0);



        setContentView(R.layout.activity_accept__transfer);
        producenameView=(TextView)findViewById(R.id.producenameView);
        dateView=(TextView)findViewById(R.id.dateView);
        volumeView=(TextView)findViewById(R.id.volumeView);
        creatorPermit=(TextView)findViewById(R.id.creatorPermit);

        setTextViewValues();

    }
    public void setTextViewValues()
    {
        producenameView.setText(name);
        dateView.setText(date);
        volumeView.setText(String.valueOf("volume"));
        creatorPermit.setText(permitNumber);



    }

    public void acceptTransfer(View view)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
         name = preferences.getString("name", "");
         permitNumber=preferences.getString("permit", "");
        JSONObject postData = new JSONObject();
        try {
            postData.put("requestId", requestid);
            postData.put("batchId", batchid);
            postData.put("ownerName",name);
            postData.put("ownerPermit",permitNumber);
            //volleyPost(postData); ///calls the volley method to make request. Email, password values are passed to volleyPost() method
            apiCallMaketransfer(postData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void apiCallMaketransfer(JSONObject postData)
    {
        ConnectionString obj=new ConnectionString();

        String postUrl = obj.connectioncode+"toddybatch/maketransfer";
        RequestQueue requestQueue = Volley.newRequestQueue(this);



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i("MyActivity", "MyClass.getView() — get item number "+response);
                goBack();
               // handleResponse(response);// After the trespons e is recieved handleResponse() method will be called to read json response and take next steps.
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
    public void goBack()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}















