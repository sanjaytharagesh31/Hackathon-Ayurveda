package ericjoseph.com.firebasework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SiddhaConverter extends Activity {

    public Spinner s,c,u;
    public EditText e1,e2;
    public double a=0,x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siddha_converter);

        String[] categories = {"WEIGHT UNITS", "LINEAR UNITS", "TIME UNITS"};

        String[] str_weights = {"ULUNTU", "YAVAM", "KUNRI", "MANCADI", "MASAM", "PANA EDAI", "VARAKAN EDAI", "KAZANCU", "PALAM (PAKKA)", "KACHU/KAICA", "TOLA", "CER", "VICAI", "TUKKU",
                "TULAM"};
        String[] lenghts = {"VIRARKADAI", "CAN", "MUZAM", "PAKAM"};
        String[] times = {"NODI", "NAZIKAI", "MUKURTTAM", "YAMAM", "PAKSAM", "MATAM", "MANDALAM", "KALAM", "AYANAM"};

        String[] ISI_length = {"CENTIMETRE (cm)","METRE (m)","INCH (\")"};
        String[] ISI_weight = {"GRAM (g)","MILLIGRAM (mg)","KILOGRAM (kg)"};
        String[] ISI_time = {"SECOND","MINUTE","HOUR","DAYS","MONTH","YEAR"};

        final ArrayAdapter<String> weights = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str_weights);
        final ArrayAdapter<String> category = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        final ArrayAdapter<String> linear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lenghts);
        final ArrayAdapter<String> time = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);

        final ArrayAdapter<String> isil = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ISI_length);
        final ArrayAdapter<String> isiw = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ISI_weight);
        final ArrayAdapter<String> isit = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ISI_time);

        s = (Spinner) findViewById(R.id.unit_spin);
        c = (Spinner) findViewById(R.id.cat_spin);
        u = (Spinner) findViewById(R.id.isi_spinner);
        c.setAdapter(category);
        s.setAdapter(weights);

        c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (c.getSelectedItem().toString().equals("WEIGHT UNITS")) {
                    s.setAdapter(weights);
                    u.setAdapter(isiw);
                } else if (c.getSelectedItem().toString().equals("LINEAR UNITS")) {
                    s.setAdapter(linear);
                    u.setAdapter(isil);
                } else if (c.getSelectedItem().toString().equals("TIME UNITS")) {
                    s.setAdapter(time);
                    u.setAdapter(isit);
                }
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
                    if (c.getSelectedItem().toString().equals("LINEAR UNITS")) {
                        if (s.getSelectedItem().toString().equals("VIRARKADAI")) {
                            a = (Double.valueOf(e1.getText().toString()) * 1.95);
                            x = (Double.valueOf(e1.getText().toString()) * 0.75);
                        }
                        else if (s.getSelectedItem().toString().equals("CAN")) {
                            a = Double.valueOf(e1.getText().toString()) * 22.86;
                            x = (Double.valueOf(e1.getText().toString()) * 9);
                        }
                        else if (s.getSelectedItem().toString().equals("MUZAM")) {
                            a = Double.valueOf(e1.getText().toString()) * 45.72;
                            x = (Double.valueOf(e1.getText().toString()) * 18);
                        }
                        else if (s.getSelectedItem().toString().equals("PAKAM")) {
                            a = Double.valueOf(e1.getText().toString()) * 182.88;
                            x = (Double.valueOf(e1.getText().toString()) * 72);
                        }

                        if(u.getSelectedItem().toString().equals("CENTIMETRE (cm)")) {
                            e2.setText(String.format("%.4f", a) + " cm");
                        }
                        else if(u.getSelectedItem().toString().equals("METRE (m)")) {
                            e2.setText(String.format("%.4f", a/100) + " m");
                        }
                        else if(u.getSelectedItem().toString().equals("INCH (\")")) {
                            e2.setText(String.format("%.4f", x) + " inch");
                        }

                    }

                    else if (c.getSelectedItem().toString().equals("TIME UNITS")) {
                        if (s.getSelectedItem().toString().equals("NODI")) {
                            a = (Double.valueOf(e1.getText().toString()) * 1);
                        } else if (s.getSelectedItem().toString().equals("NAZIKAI")) {
                            a = (Double.valueOf(e1.getText().toString()) * 24) * 60;
                        } else if (s.getSelectedItem().toString().equals("MUKURTTAM")) {
                            a = (Double.valueOf(e1.getText().toString()) * 90) * 60;
                        } else if (s.getSelectedItem().toString().equals("YAMAM")) {
                            a = (Double.valueOf(e1.getText().toString()) * 3) * 3600;
                        } else if (s.getSelectedItem().toString().equals("PAKSAM")) {
                            a = Double.valueOf(e1.getText().toString()) * 15 * 3600 * 24;
                        } else if (s.getSelectedItem().toString().equals("MATAM")) {
                            a = Double.valueOf(e1.getText().toString()) * 30 * 3600 * 24;
                        } else if (s.getSelectedItem().toString().equals("MANDALAM")) {
                            a = Double.valueOf(e1.getText().toString()) * 45 * 3600 * 24;
                        } else if (s.getSelectedItem().toString().equals("KALAM")) {
                            a = Double.valueOf(e1.getText().toString()) * 60 * 3600 * 24;
                        } else if (s.getSelectedItem().toString().equals("AYANAM")) {
                            a = Double.valueOf(e1.getText().toString()) * 180 * 3600 * 24;
                        }

                        if(u.getSelectedItem().toString().equals("SECOND")) {
                            e2.setText(String.format("%.4f", a) + " seconds");
                        }
                        else if(u.getSelectedItem().toString().equals("MINUTE")) {
                            e2.setText(String.format("%.4f", a/60) + " minutes");
                        }
                        else if(u.getSelectedItem().toString().equals("HOUR")) {
                            e2.setText(String.format("%.4f", a/3600) + " hours");
                        }
                        else if(u.getSelectedItem().toString().equals("DAYS")) {
                            e2.setText(String.format("%.4f", a/(3600*24)) + " days");
                        }
                        else if(u.getSelectedItem().toString().equals("MONTH")) {
                            e2.setText(String.format("%.4f", a/(3600*24*30)) + " months");
                        }
                        else if(u.getSelectedItem().toString().equals("YEAR")) {
                            e2.setText(String.format("%.4f", a/(3600*24*30*12)) + " years");
                        }
                    }

                    else if (c.getSelectedItem().toString().equals("WEIGHT UNITS")) {
                        if (s.getSelectedItem().toString().equals("ULUNTU")) {
                            a = (Double.valueOf(e1.getText().toString()) * 65);
                        } else if (s.getSelectedItem().toString().equals("YAVAM")) {
                            a = (Double.valueOf(e1.getText().toString()) * 130)/4;
                        } else if (s.getSelectedItem().toString().equals("KUNRI")) {
                            a = (Double.valueOf(e1.getText().toString()) * 130);
                        } else if (s.getSelectedItem().toString().equals("MANCADI")) {
                            a = (Double.valueOf(e1.getText().toString()) * 260);
                        } else if (s.getSelectedItem().toString().equals("MASAM")) {
                            a = Double.valueOf(e1.getText().toString()) * 780;
                        } else if (s.getSelectedItem().toString().equals("PANA EDAI")) {
                            a = Double.valueOf(e1.getText().toString()) * 488;
                        } else if (s.getSelectedItem().toString().equals("VARAKAN EDAI")) {
                            a = Double.valueOf(e1.getText().toString()) * 4.16 / 1000;
                        } else if (s.getSelectedItem().toString().equals("KAZANCU")) {
                            a = Double.valueOf(e1.getText().toString()) * 5.12 / 1000;
                        } else if (s.getSelectedItem().toString().equals("PALAM (PAKKA)")) {
                            a = Double.valueOf(e1.getText().toString()) * 41.6 / 1000;
                        } else if (s.getSelectedItem().toString().equals("KACHU/KAICA")) {
                            a = Double.valueOf(e1.getText().toString()) * 10.4 / 1000;
                        } else if (s.getSelectedItem().toString().equals("TOLA")) {
                            a = Double.valueOf(e1.getText().toString()) * 11.7 / 1000;
                        } else if (s.getSelectedItem().toString().equals("CER")) {
                            a = Double.valueOf(e1.getText().toString()) * 280 / 1000;
                        } else if (s.getSelectedItem().toString().equals("VICAI")) {
                            a = Double.valueOf(e1.getText().toString()) * 1.4 / 1000000;
                        } else if (s.getSelectedItem().toString().equals("TUKKU")) {
                            a = Double.valueOf(e1.getText().toString()) * 1.75 / 1000000;
                        } else if (s.getSelectedItem().toString().equals("TULAM")) {
                            a = Double.valueOf(e1.getText().toString()) * 3.5 / 1000000;
                        }

                        if(u.getSelectedItem().toString().equals("GRAM (g)")) {
                            e2.setText(String.format("%.4f", a*1000) + " g");
                        }
                        else if(u.getSelectedItem().toString().equals("MILLIGRAM (mg)")) {
                            e2.setText(String.format("%.4f", a) + " mg");
                        }
                        else if(u.getSelectedItem().toString().equals("KILOGRAM (kg)")) {
                            e2.setText(String.format("%.4f", a*1000000) + " kg");
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
