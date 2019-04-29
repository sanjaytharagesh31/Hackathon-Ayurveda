package ericjoseph.com.firebasework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UnaniConverter extends AppCompatActivity {

    public Spinner s,c,u;
    public EditText e1,e2;
    public double a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unani_converter);

        String[] categories = {"UNANI"};

        String[] str_unani = {"GRAIN/CHAWAL","JO","RATTI","SURKHA","DANGA","MASHA","MISKALA","TOLA","RUPYA","AAQYA","RATALA","SERA"};

        String[] isiunits = {"MILLIGRAMS (mg)","GRAMS (g)","KILOGRAMS (kg)","MILLILITRE (ml) FOR TOLA"};


        final ArrayAdapter<String> unani = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str_unani);
        final ArrayAdapter<String> category = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        final ArrayAdapter<String> units = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, isiunits);

        s = (Spinner) findViewById(R.id.unit_spin);
        c = (Spinner) findViewById(R.id.cat_spin);
        u = (Spinner) findViewById(R.id.spinner3);

        c.setAdapter(category);
        s.setAdapter(unani);
        u.setAdapter(units);

        c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (c.getSelectedItem().toString().equals("UNANI"))
                    s.setAdapter(unani);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        Button b = (Button) findViewById(R.id.button3);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.e2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e1.getText().length() <= 0)
                    Toast.makeText(getApplicationContext(), "Please Enter Value", Toast.LENGTH_SHORT).show();

                else {
                    if (c.getSelectedItem().toString().equals("UNANI")) {
                        if (s.getSelectedItem().toString().equals("GRAIN/CHAWAL"))
                            a = Double.valueOf(e1.getText().toString()) * 15.58;
                        else if (s.getSelectedItem().toString().equals("JO"))
                            a = Double.valueOf(e1.getText().toString()) * 63.32;
                        else if (s.getSelectedItem().toString().equals("RATTI"))
                            a = Double.valueOf(e1.getText().toString()) * 125;
                        else if (s.getSelectedItem().toString().equals("SURKHA"))
                            a = Double.valueOf(e1.getText().toString()) * 125;
                        else if (s.getSelectedItem().toString().equals("DANGA"))
                            a = Double.valueOf(e1.getText().toString()) * 500;
                        else if (s.getSelectedItem().toString().equals("MASHA"))
                            a = Double.valueOf(e1.getText().toString()) * 970;
                        else if (s.getSelectedItem().toString().equals("MISKALA"))
                            a = Double.valueOf(e1.getText().toString()) * 4500;
                        else if (s.getSelectedItem().toString().equals("TOLA"))
                            a = Double.valueOf(e1.getText().toString()) * 11664;
                        else if (s.getSelectedItem().toString().equals("RUPYA"))
                            a = Double.valueOf(e1.getText().toString()) * 57326;
                        else if (s.getSelectedItem().toString().equals("AAQYA"))
                            a = Double.valueOf(e1.getText().toString()) * 350000;
                        else if (s.getSelectedItem().toString().equals("RATALA"))
                            a = Double.valueOf(e1.getText().toString()) * 468500;
                        else if (s.getSelectedItem().toString().equals("SERA"))
                            a = Double.valueOf(e1.getText().toString()) * 937000;

                        if(u.getSelectedItem().toString().equals("MILLIGRAMS (mg)"))
                            e2.setText(String.format("%.4f", a) + " mg");
                        else if(u.getSelectedItem().toString().equals("GRAMS (g)"))
                          {   a=a/1000;
                              e2.setText(String.format("%.4f", a) + " g");
                          }
                        else if(u.getSelectedItem().toString().equals("KILOGRAMS (kg)"))
                        {   a=a/(1000*1000);
                            e2.setText(String.format("%.4f", a) + "kg");
                        }
                        else
                        {  if(u.getSelectedItem().toString().equals("MILLILITRE (ml) FOR TOLA") && s.getSelectedItem().toString().equals("TOLA"))
                           {  a = Double.valueOf(e1.getText().toString()) * 12;
                              e2.setText(String.format("%.4f", a) + "ml");
                           }
                           else
                              Toast.makeText(getApplicationContext(),"Please Select TOLA for Liquid Unit",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
    public void onClick(View view)
    { if(view.getId()== R.id.button4)
        startActivity(new Intent(this,Main2Activity.class));
    else if(view.getId()==R.id.orgbtn)
        startActivity(new Intent(this,OrgDataUpload.class));
    }
}
