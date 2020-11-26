package hfad.com.toddybusinessman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Sell_Toddy_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell__toddy__screen);
        Intent intent=getIntent();
        int batch_id=intent.getIntExtra("batchID",0);
        int volume=intent.getIntExtra("volume",0);
        String datet=intent.getStringExtra("date");
        TextView volumeV=(TextView)findViewById(R.id.volumeView);
        TextView batchV=(TextView)findViewById(R.id.IDView);
        TextView dateV=(TextView)findViewById(R.id.dateView);
        volumeV.setText(String.valueOf(volume));
        batchV.setText(String.valueOf(batch_id));
        dateV.setText(datet);





    }
}