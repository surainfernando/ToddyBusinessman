package hfad.com.toddybusinessman.ui.dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hfad.com.toddybusinessman.Accept_Transfer;
import hfad.com.toddybusinessman.BatchAdapter;
import hfad.com.toddybusinessman.ConnectionString;
import hfad.com.toddybusinessman.Contact;
import hfad.com.toddybusinessman.R;
import hfad.com.toddybusinessman.Sell_Toddy_Screen;
import hfad.com.toddybusinessman.ToddyBatch;
import hfad.com.toddybusinessman.ToddyRequestCard;
import hfad.com.toddybusinessman.ToddyRequestModel;

public class DashboardFragment extends Fragment {
    ArrayList<ToddyRequestModel> contacts;
    Button refreshButton;
    RecyclerView rvContacts;
    ArrayList<ToddyRequestModel> toddySellRequestList;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
      rvContacts = (RecyclerView)root.findViewById(R.id.rvContacts);

        // Initialize contacts
        refreshButton=root.findViewById(R.id.refreshButton);
        toddySellRequestList= new ArrayList<ToddyRequestModel>();

        //contacts = Contact.createContactsList(20);
        addListners();;
        fillRecycler();
        // Create adapter passing in the sample user data
//        BatchAdapter adapter = new BatchAdapter(contacts);
//        // Attach the adapter to the recyclerview to populate items
//        rvContacts.setAdapter(adapter);
//        // Set layout manager to position the items
//        rvContacts.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }



    public void addListners()
    {refreshButton.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
         fillRecycler();


        }
    });

    }




    public void fillRecycler()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String name = preferences.getString("name", "");
        String permit_numbr= preferences.getString("permit", "");
        JSONObject postData = new JSONObject();
        try {
            postData.put("name", name);
            postData.put("permit", permit_numbr);



            sendRequest(postData);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void sendRequest(JSONObject postData)//
    {
        ConnectionString obj=new ConnectionString();

        String postUrl = obj.connectioncode+"toddybatch/getToddySellRequest";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("Success", "sucesssyyyyy7777777777777777777777777777777777");
                setDataToRecycler(response);

                //System.out.println(response);
                //Log.i("MyActivity", response);
                //handleListView(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", "fffffffffffffffffffffffffffffffffffffffffffffffffffl");
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    public void setDataToRecycler(JSONObject response)
   {
        Log.i("MyActivity", "7777777777777777777777777777777777");
        try{
            JSONArray user_wallets = response.getJSONArray("result");
            JSONObject[] batchList=new JSONObject[user_wallets.length()];
            int lengthx=user_wallets.length();
            lengthx=lengthx-1;
            for(int i = lengthx; i>=0;i--){

                JSONObject wallet = user_wallets.getJSONObject(i);
               // batchList[i]=wallet;
                //String x=wallet.getString("creator_name");
                Log.i("iteratiom", "7777777777777777777777777777777777");
                //ToddyRequestModel contact1=new Contact(x,true);
                ToddyRequestModel batch=new ToddyRequestModel( wallet.getInt("request_id"),wallet.getInt("approval_status"), wallet.getInt("volume"), wallet.getInt("batch_id"), wallet.getString("date_created"), wallet.getString("buyer_permit_number"),wallet.getString("seller_permit_number")  , wallet.getString("seller_name") , wallet.getString("buyer_name") ) ;
                toddySellRequestList.add(batch);
            }
            ToddyRequestCard adapter = new ToddyRequestCard( toddySellRequestList);
            adapter.setOnItemClickListener(new ToddyRequestCard.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    ToddyRequestModel a=toddySellRequestList.get(position);

                    int id=a.getRequest_id();
                    String name=a.getSeller_name();
                    String permitNumber=a.getSeller_permit_number();
                    int volume=a.getVolume();
                    String date=a.getDate_created();
                    int batchID=a.getBatch_id();
                    Intent intent=new Intent(getActivity(), Accept_Transfer.class);
                    intent.putExtra("RequestID",id);
                    intent.putExtra("name",name);
                    intent.putExtra("permitNumber",permitNumber);
                    intent.putExtra("volume",volume);
                    intent.putExtra("date",date);
                    intent.putExtra("batchID",batchID);

                    startActivity(intent);
//                    intent.putExtra("volume",a.getVolume());
//                    intent.putExtra("date",a.getDate_created());
//
                }
            });
            // Attach the adapter to the recyclerview to populate items
            rvContacts.setAdapter(adapter);
            // Set layout manager to position the items
            rvContacts.setLayoutManager(new LinearLayoutManager(getContext()));





        }
        catch(Exception e)
        {
            Log.i("errror parse", "7777777777777777777777777777777777"+e);

        }

  }

}