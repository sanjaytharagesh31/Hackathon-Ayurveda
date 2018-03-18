package ericjoseph.com.firebasework;

import android.app.Activity;
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

public class MainActivity extends Activity {

    public Spinner s,c;
    public EditText e1,e2;
    public double a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] categories = {"WEIGHT UNITS", "LINEAR UNITS", "TIME UNITS"};

        String[] str_weights = {"PARMANU", "DHAVANSHI", "MARICHI", "LAL SARSHAP", "TUNDAL", "DHANYAMASH", "YAVA", "RATTI", "ANDIKA", "MASHAK (MASA)", "SHAAN", "KOL", "TOLA", "KARSHA",
                "MASA", "KARSA(TOLA)", "SUKTI", "PALAM", "PRASTRI", "KUDAVA", "MANIKA", "PRASTHA", "ADHAKA", "DRONA", "SURPA", "DRONI(VAHI)", "KHARI", "TULA", "BHARA"
        };

        String[] lenghts = {"YAVODARA", "ANGULA", "BITAHASTI", "ARATNI", "HASTA", "RAJAHASTA", "VYAMA"};

        String[] times = {"KSANA", "LAVA", "NIMESHA", "KASTHA", "KALA", "GHATI", "MUHURTA", "AHORATRA",
                "PAKSA", "MASA", "RTU", "AYANA", "SAMVATSARA", "YUGA", "AHORATRA-DEVAS", "AHORATRA-PITARAS"};


        final ArrayAdapter<String> weights = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str_weights);
        final ArrayAdapter<String> category = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        final ArrayAdapter<String> linear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lenghts);
        final ArrayAdapter<String> time = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);

        s = (Spinner) findViewById(R.id.spinner2);
        c = (Spinner) findViewById(R.id.spinner);
        c.setAdapter(category);
        s.setAdapter(weights);

        c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (c.getSelectedItem().toString().equals("WEIGHT UNITS"))
                    s.setAdapter(weights);
                else if (c.getSelectedItem().toString().equals("LINEAR UNITS"))
                    s.setAdapter(linear);
                else if (c.getSelectedItem().toString().equals("TIME UNITS"))
                    s.setAdapter(time);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        Button b = (Button) findViewById(R.id.button3);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e1.getText().length() <= 0)
                    Toast.makeText(getApplicationContext(), "Please Enter Value", Toast.LENGTH_SHORT).show();

                else {
                    if (c.getSelectedItem().toString().equals("LINEAR UNITS")) {
                        if (s.getSelectedItem().toString().equals("YAVODARA"))
                            a = (Double.valueOf(e1.getText().toString()) * 0.24);
                        else if (s.getSelectedItem().toString().equals("ANGULA"))
                            a = Double.valueOf(e1.getText().toString()) * 1.95;
                        else if (s.getSelectedItem().toString().equals("BITAHASTI"))
                            a = Double.valueOf(e1.getText().toString()) * 22.86;
                        else if (s.getSelectedItem().toString().equals("ARATNI"))
                            a = Double.valueOf(e1.getText().toString()) * 41.91;
                        else if (s.getSelectedItem().toString().equals("HASTA"))
                            a = Double.valueOf(e1.getText().toString()) * 45.72;
                        else if (s.getSelectedItem().toString().equals("RAJAHASTA"))
                            a = Double.valueOf(e1.getText().toString()) * 55.88;
                        else if (s.getSelectedItem().toString().equals("VYAMA"))
                            a = Double.valueOf(e1.getText().toString()) * 182.88;

                        e2.setText(String.format("%.4f", a) + " cm");
                    } else if (c.getSelectedItem().toString().equals("WEIGHT UNITS")) {
                        if (s.getSelectedItem().toString().equals("PARMANU"))
                            a = Double.valueOf(e1.getText().toString()) * 0.0016;
                        else if (s.getSelectedItem().toString().equals("DHAVANSHI"))
                            a = Double.valueOf(e1.getText().toString()) * 0.5;
                        else if (s.getSelectedItem().toString().equals("MARICHI"))
                            a = Double.valueOf(e1.getText().toString()) * 0.32;
                        else if (s.getSelectedItem().toString().equals("LAL SARSHAP"))
                            a = Double.valueOf(e1.getText().toString()) * 1.95;
                        else if (s.getSelectedItem().toString().equals("TUNDAL"))
                            a = Double.valueOf(e1.getText().toString()) * 15.62;
                        else if (s.getSelectedItem().toString().equals("DHANYAMASH"))
                            a = Double.valueOf(e1.getText().toString()) * 31.25;
                        else if (s.getSelectedItem().toString().equals("YAVA"))
                            a = Double.valueOf(e1.getText().toString()) * 62.5;
                        else if (s.getSelectedItem().toString().equals("RATTI"))
                            a = Double.valueOf(e1.getText().toString()) * 125;
                        else if (s.getSelectedItem().toString().equals("ANDIKA"))
                            a = Double.valueOf(e1.getText().toString()) * 250;
                        else if (s.getSelectedItem().toString().equals("MASHAK (MASA)"))
                            a = Double.valueOf(e1.getText().toString()) * 1000;
                        else if (s.getSelectedItem().toString().equals("SHAAN"))
                            a = Double.valueOf(e1.getText().toString()) * 3000;
                        else if (s.getSelectedItem().toString().equals("KOL"))
                            a = Double.valueOf(e1.getText().toString()) * 6000;
                        else if (s.getSelectedItem().toString().equals("TOLA"))
                            a = Double.valueOf(e1.getText().toString()) * 12000;
                        else if (s.getSelectedItem().toString().equals("KARSHA"))
                            a = Double.valueOf(e1.getText().toString()) * 12000;
                        e2.setText(String.format("%.4f", a) + " mg");


                        if (s.getSelectedItem().toString().equals("MASA")) {
                            a = Double.valueOf(e1.getText().toString()) * 1;
                            e2.setText(String.format("%.4f", a) + " g");
                        } else if (s.getSelectedItem().toString().equals("KARSA(TOLA)")) {
                            a = Double.valueOf(e1.getText().toString()) * 12;
                            e2.setText(String.format("%.4f", a) + " g");
                        } else if (s.getSelectedItem().toString().equals("SUKTI")) {
                            a = Double.valueOf(e1.getText().toString()) * 24;
                            e2.setText(String.format("%.4f", a) + " g");
                        } else if (s.getSelectedItem().toString().equals("PALAM")) {
                            a = Double.valueOf(e1.getText().toString()) * 48;
                            e2.setText(String.format("%.4f", a) + " g");
                        } else if (s.getSelectedItem().toString().equals("PRASRTI")) {
                            a = Double.valueOf(e1.getText().toString()) * 96;
                            e2.setText(String.format("%.4f", a) + " g");
                        } else if (s.getSelectedItem().toString().equals("KUDAVA")) {
                            a = Double.valueOf(e1.getText().toString()) * 192;
                            e2.setText(String.format("%.4f", a) + " g");
                        } else if (s.getSelectedItem().toString().equals("MANIKA")) {
                            a = Double.valueOf(e1.getText().toString()) * 384;
                            e2.setText(String.format("%.4f", a) + " g");
                        } else if (s.getSelectedItem().toString().equals("PRASTHA")) {
                            a = Double.valueOf(e1.getText().toString()) * 768;
                            e2.setText(String.format("%.4f", a) + " g");
                        } else if (s.getSelectedItem().toString().equals("ADHAKA")) {
                            a = Double.valueOf(e1.getText().toString()) * 3.072;
                            e2.setText(String.format("%.4f", a) + " kg");
                        } else if (s.getSelectedItem().toString().equals("DRONA")) {
                            a = Double.valueOf(e1.getText().toString()) * 12.288;
                            e2.setText(String.format("%.4f", a) + " kg");
                        } else if (s.getSelectedItem().toString().equals("SURPA")) {
                            a = Double.valueOf(e1.getText().toString()) * 24.576;
                            e2.setText(String.format("%.4f", a) + " kg");
                        } else if (s.getSelectedItem().toString().equals("DRONI(VAHI)")) {
                            a = Double.valueOf(e1.getText().toString()) * 49.152;
                            e2.setText(String.format("%.4f", a) + " kg");
                        } else if (s.getSelectedItem().toString().equals("KHARI")) {
                            a = Double.valueOf(e1.getText().toString()) * 196.608;
                            e2.setText(String.format("%.4f", a) + " kg");
                        } else if (s.getSelectedItem().toString().equals("TULA")) {
                            a = Double.valueOf(e1.getText().toString()) * 4.8;
                            e2.setText(String.format("%.4f", a) + " kg");
                        } else if (s.getSelectedItem().toString().equals("BHARA")) {
                            a = Double.valueOf(e1.getText().toString()) * 96;
                            e2.setText(String.format("%.4f", a) + " kg");
                        }
                    } else if (c.getSelectedItem().toString().equals("TIME UNITS")) {
                        if (s.getSelectedItem().toString().equals("KNASA")) {
                            a = (Double.valueOf(e1.getText().toString()) * 0.38) / 60;
                            e2.setText(String.format("%.4f", a) + " seconds");
                        } else if (s.getSelectedItem().toString().equals("LAVA")) {
                            a = (Double.valueOf(e1.getText().toString()) * 0.77) / 60;
                            e2.setText(String.format("%.4f", a) + " seconds");
                        } else if (s.getSelectedItem().toString().equals("NIMESHA")) {
                            a = (Double.valueOf(e1.getText().toString()) * 1.55) / 60;
                            e2.setText(String.format("%.4f", a) + " seconds");
                        } else if (s.getSelectedItem().toString().equals("KASHTA")) {
                            a = (Double.valueOf(e1.getText().toString()) * 4.66) / 60;
                            e2.setText(String.format("%.4f", a) + " seconds");
                        } else if (s.getSelectedItem().toString().equals("KALA")) {
                            a = Double.valueOf(e1.getText().toString()) * 2.3;
                            e2.setText(String.format("%.4f", a) + " seconds");
                        } else if (s.getSelectedItem().toString().equals("GHATI")) {
                            a = Double.valueOf(e1.getText().toString()) * 24;
                            e2.setText(String.format("%.4f", a) + " seconds");
                        } else if (s.getSelectedItem().toString().equals("MUHURTA")) {
                            a = Double.valueOf(e1.getText().toString()) * 48;
                            e2.setText(String.format("%.4f", a) + " minutes");
                        } else if (s.getSelectedItem().toString().equals("AHORATRA")) {
                            a = Double.valueOf(e1.getText().toString()) * 24;
                            e2.setText(String.format("%.4f", a) + " hours");
                        } else if (s.getSelectedItem().toString().equals("PAKSA")) {
                            a = Double.valueOf(e1.getText().toString()) * 24;
                            e2.setText(String.format("%.4f", a) + " days");
                        } else if (s.getSelectedItem().toString().equals("MASA")) {
                            a = Double.valueOf(e1.getText().toString()) * 1;
                            e2.setText(String.format("%.4f", a) + " months");
                        } else if (s.getSelectedItem().toString().equals("RTU")) {
                            a = Double.valueOf(e1.getText().toString()) * 2;
                            e2.setText(String.format("%.4f", a) + " months");
                        } else if (s.getSelectedItem().toString().equals("AYANA")) {
                            a = Double.valueOf(e1.getText().toString()) * 6;
                            e2.setText(String.format("%.4f", a) + " months");
                        } else if (s.getSelectedItem().toString().equals("SAMVATSARA")) {
                            a = Double.valueOf(e1.getText().toString()) * 1;
                            e2.setText(String.format("%.4f", a) + " years");
                        } else if (s.getSelectedItem().toString().equals("YUGA")) {
                            a = Double.valueOf(e1.getText().toString()) * 5;
                            e2.setText(String.format("%.4f", a) + " years");
                        } else if (s.getSelectedItem().toString().equals("AHORATRA-DEVAS")) {
                            a = Double.valueOf(e1.getText().toString()) * 1;
                            e2.setText(String.format("%.4f", a) + " years");
                        } else if (s.getSelectedItem().toString().equals("AHORATRA-PITARAS")) {
                            a = Double.valueOf(e1.getText().toString()) * 1;
                            e2.setText(String.format("%.4f", a) + " months");
                        }
                    }
                }
            }
        });
    }
    public void onClick(View view)
    { if(view.getId()== R.id.button4)
        startActivity(new Intent(this,Main2Activity.class));
    }
}
