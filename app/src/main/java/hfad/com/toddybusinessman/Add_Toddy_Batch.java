package hfad.com.toddybusinessman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class Add_Toddy_Batch extends AppCompatActivity {
    TextInputLayout volumeBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__toddy__batch);
        volumeBox = findViewById(R.id.volume);
    }

    public void temp(View view)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("name", "");
        String permit_numbr= preferences.getString("permit", "");
        JSONObject postData = new JSONObject();
        String volume=volumeBox.getEditText().getText().toString();
        try {
            postData.put("name", name);
            postData.put("permit", permit_numbr);
            postData.put("volume",volume);


            sendRequest(postData);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
     public void sendRequest(JSONObject postData)
     {
         ConnectionString obj=new ConnectionString();

         String postUrl = obj.connectioncode+"toddybatch/add";
         RequestQueue requestQueue = Volley.newRequestQueue(this);



         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 goBack();


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
     public void goBack()
     {
         Intent intent=new Intent(this,MainActivity.class);
         startActivity(intent);
     }
}