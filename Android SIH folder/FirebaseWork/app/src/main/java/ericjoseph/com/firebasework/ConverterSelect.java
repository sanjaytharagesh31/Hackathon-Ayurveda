package ericjoseph.com.firebasework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ConverterSelect extends AppCompatActivity {
    ImageView Ayurveda, Unani, Siddha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_select);
        Ayurveda = (ImageView) findViewById(R.id.Ayurselect);
        Unani = (ImageView)findViewById(R.id.unaniselect);
        Siddha = (ImageView)findViewById(R.id.siddhaselect);

        Siddha.setImageResource(R.drawable.siddhaicon);
        Unani.setImageResource(R.drawable.unani);
        Ayurveda.setImageResource(R.drawable.ayurveda);
    }
    public void onClick(View view)
    { if(view.getId() == Ayurveda.getId())
        startActivity(new Intent(this,MainActivity.class));
      else if(view.getId() == Unani.getId())
        startActivity(new Intent(this,UnaniConverter.class));
      else if(view.getId() == Siddha.getId())
        startActivity(new Intent(this,SiddhaConverter.class));
    }
}
