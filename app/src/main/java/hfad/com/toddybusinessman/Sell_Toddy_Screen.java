package hfad.com.toddybusinessman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sell_Toddy_Screen extends AppCompatActivity {

    int batch_id;
    String datet;
    int volume;
    Spinner buyer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell__toddy__screen);
        Intent intent=getIntent();
       batch_id=intent.getIntExtra("batchID",0);
        volume=intent.getIntExtra("volume",0);
        datet=intent.getStringExtra("date");
        TextView volumeV=(TextView)findViewById(R.id.volumeView);
        TextView batchV=(TextView)findViewById(R.id.IDView);
        TextView dateV=(TextView)findViewById(R.id.dateView);
        buyer=(Spinner)findViewById(R.id.buyername);
        volumeV.setText(String.valueOf(volume));
        batchV.setText(String.valueOf(batch_id));

        try{
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String string1 =datet;

            Date result1 = df1.parse(string1);
            SimpleDateFormat timeStampFormat = new SimpleDateFormat("dd-MM-yyyy");
            String DateStr = timeStampFormat.format(result1);
            dateV.setText(DateStr);

        }
        catch (ParseException e)
        {

        }





    }

    public void makeSellRequest(View view)
    { //batchid,datecreated,buyername,sellername,buyerpermit, sellerpermit
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = preferences.getString("name", "");
        String permit_numbr= preferences.getString("permit", "");
        String x=buyer.getSelectedItem().toString();
        int indexselected=buyer.getSelectedItemPosition();

        Log.i("MyActivity", "New Emaicdfffffffffffffffffffffffffffffffffffffffffffffffffffl");
        Log.i("MyActivity", "name"+x);
        Log.i("MyActivity", "id"+String.valueOf(indexselected));

        String targetCutomerPermitNumber="";
        if(indexselected==0)
        {
            targetCutomerPermitNumber="Po1245567";
        }
        else
        {
            targetCutomerPermitNumber="P1x24456";
        }
        Log.i("customer permit number", "id  "+targetCutomerPermitNumber);



        JSONObject postData = new JSONObject();
        try {
            postData.put("batch_id", batch_id);
            postData.put("date_created", datet);
            postData.put("seller_name",name);
            postData.put("seller_permit_number", permit_numbr);
            postData.put("buyer_name",x);
            postData.put("buyer_permit_number", targetCutomerPermitNumber);
            postData.put("volume", volume);


           // postData.put("email", email);
            sendRequest(postData);
//


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public void sendRequest(JSONObject postData)//
    {
        ConnectionString obj=new ConnectionString();

        String postUrl = obj.connectioncode+"toddybatch/makeToddySellRequest";
        RequestQueue requestQueue = Volley.newRequestQueue(this);



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("MyActivity", "7777777777777777777777777777777777");
                goBack();
                try{
//                    JSONArray user_wallets = response.getJSONArray("result");
//                    JSONObject[] batchList=new JSONObject[user_wallets.length()];
//                    for(int i = 0; i<user_wallets.length();i++){
//
//                        JSONObject wallet = user_wallets.getJSONObject(i);
//                        batchList[i]=wallet;
//                    }
//                    System.out.println(batchList[0]);
//                    System.out.println(batchList[1]);




                }
                catch(Exception e)
                {

                }
                //System.out.println(response);
                //Log.i("MyActivity", response);
                //handleListView(response);


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