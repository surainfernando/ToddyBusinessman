package hfad.com.toddybusinessman.ui.notifications;

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

import hfad.com.toddybusinessman.Login;
import hfad.com.toddybusinessman.R;

public class NotificationsFragment extends Fragment {
    Button button;
    TextView nameBox;
    TextView permitBox;
    TextView businessBox;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

         button =root.findViewById(R.id.button6);

         nameBox=(TextView)root.findViewById(R.id.nameBox);
         permitBox=(TextView)root.findViewById(R.id.permitBox);
         businessBox=(TextView)root.findViewById(R.id.businessBox);
         setTestListner();
         textSetter();

        return root;
    }
    public void textSetter()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String business_type = preferences.getString("business_type", "");
        String name = preferences.getString("name", "");
        String permit = preferences.getString("permit", "");
        Log.i("Btype", business_type);
        business_type=business_type.trim();
        businessBox.setText(business_type);
        permitBox.setText(permit);
        nameBox.setText(name);

    }

    public void setTestListner()
    {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name","");
                editor.putString("permit","");
                editor.putString("business_type","");
                editor.putString("noOfTrees","");
                editor.putString("location","");
                editor.putString("email","");
                editor.putString("password","");
                editor.putString("loggedon","false");
                editor.apply();
                showpreferences();
                Intent intent=new Intent(getActivity(), Login.class);
                startActivity(intent);




            }
        });

    }

    public void showpreferences()
    {
        // this function logs shared preferences data
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
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
        // Code here executes on main thread after user presses button

    }
}