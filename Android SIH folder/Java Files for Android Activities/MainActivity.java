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

public class MainActivity extends Activity {

    public Spinner s,c,u,aut;
    public EditText e1,e2;
    public double a=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] categories = {"WEIGHT UNITS", "LINEAR UNITS", "TIME UNITS"};

        String[] str_weights = {"anu", "Paramanu", "truti", "liksha", "vanshi", "dhwanshi", "trasrenu", "yuka", "maricha", "raja",
                "rajika", "rakta sarshapa", "sarshapa", "tandula", "dhanyamasha", "yava", "ratti", "gunja", "andika"};

        String[] lenghts = {"YAVODARA", "ANGULA", "VITASTI", "ARATNI", "HASTA", "NRIPA HASTA","RAJHASTA", "VYAMA"};

        String[] times = {"KSANA", "LAVA", "NIMESHA", "KASTHA", "KALA", "GHATI", "MUHURTA", "AHORATRA",
                "PAKSA", "MASA", "RTU", "AYANA", "SAMVATSARA", "YUGA", "AHORATRA-DEVAS", "AHORATRA-PITARAS"};

        String[] author = {"CHARAKA", "SUSHRUTA", "SHARANGADHARA", "RRS", "VAIDYAKA PARIBHASA PRADEEPA", "API"};

        final ArrayAdapter<String> weights = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str_weights);
        final ArrayAdapter<String> category = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        final ArrayAdapter<String> linear = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lenghts);
        final ArrayAdapter<String> time = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);
        final ArrayAdapter<String> auth = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, author);

        String[] ISI_length = {"CENTIMETRE (cm)","METRE (m)","INCH (\")"};
        String[] ISI_weight = {"GRAM (g)","MILLIGRAM (mg)","KILOGRAM (kg)"};
        String[] ISI_time = {"SECOND","MINUTE","HOUR","DAYS","MONTH","YEAR"};

        final ArrayAdapter<String> isil = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ISI_length);
        final ArrayAdapter<String> isiw = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ISI_weight);
        final ArrayAdapter<String> isit = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ISI_time);

        aut = (Spinner)findViewById(R.id.aut_spinner);
        s = (Spinner) findViewById(R.id.aunit_spin);
        c = (Spinner) findViewById(R.id.dim_spin);
        u = (Spinner) findViewById(R.id.iunit_spin);

        aut.setAdapter(auth);
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
            }
                else if (c.getSelectedItem().toString().equals("TIME UNITS")) {
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
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e1.getText().length() <= 0)
                    Toast.makeText(getApplicationContext(), "Please Enter Value", Toast.LENGTH_SHORT).show();

                else {
                    //For Conversions
                    if (aut.getSelectedItem().toString().equals("API")) {
                        if (c.getSelectedItem().toString().equals("LINEAR UNITS")) {
                            if (s.getSelectedItem().toString().equals("YAVODARA"))
                                a = (Double.valueOf(e1.getText().toString()) * 0.24);
                            else if (s.getSelectedItem().toString().equals("ANGULA"))
                                a = Double.valueOf(e1.getText().toString()) * 1.95;
                            else if (s.getSelectedItem().toString().equals("VITASTI"))
                                a = Double.valueOf(e1.getText().toString()) * 22.86;
                            else if (s.getSelectedItem().toString().equals("ARATNI"))
                                a = Double.valueOf(e1.getText().toString()) * 41.91;
                            else if (s.getSelectedItem().toString().equals("HASTA"))
                                a = Double.valueOf(e1.getText().toString()) * 45.72;
                            else if (s.getSelectedItem().toString().equals("NRIPA HASTA"))
                                a = Double.valueOf(e1.getText().toString()) * 55.88;
                            else if (s.getSelectedItem().toString().equals("RAJHASTA"))
                                a = Double.valueOf(e1.getText().toString()) * 55.88;
                            else if (s.getSelectedItem().toString().equals("VYAMA"))
                                a = Double.valueOf(e1.getText().toString()) * 182.88;

                            if (u.getSelectedItem().toString().equals("CENTIMETRE (cm)")) {
                                e2.setText(String.format("%.4f", a) + " cm");
                            } else if (u.getSelectedItem().toString().equals("METRE (m)")) {
                                e2.setText(String.format("%.4f", a / 100) + " m");
                            } else if (u.getSelectedItem().toString().equals("INCH (\")")) {
                                e2.setText(String.format("%.4f", a / 2.5) + " inch");
                            }
                        } else if (c.getSelectedItem().toString().equals("WEIGHT UNITS")) {

                            if (s.getSelectedItem().toString().equals("ratti"))
                                a = Double.valueOf(e1.getText().toString()) * 125;
                            else if (s.getSelectedItem().toString().equals("gunja"))
                                a = Double.valueOf(e1.getText().toString()) * 125;
                            else {
                                a=0;
                                Toast.makeText(getApplicationContext(), "Unit not available for API", Toast.LENGTH_SHORT).show();
                            }
                            if (u.getSelectedItem().toString().equals("GRAM (g)")) {
                                e2.setText(String.format("%.4f", a / 1000) + " g");
                            } else if (u.getSelectedItem().toString().equals("MILLIGRAM (mg)")) {
                                e2.setText(String.format("%.4f", a) + " mg");
                            } else if (u.getSelectedItem().toString().equals("KILOGRAM (kg)")) {
                                e2.setText(String.format("%.4f", a / 1000000) + " kg");
                            }
                        } else if (c.getSelectedItem().toString().equals("TIME UNITS")) {
                            if (s.getSelectedItem().toString().equals("KNASA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 0.38);
                            } else if (s.getSelectedItem().toString().equals("LAVA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 0.77);
                            } else if (s.getSelectedItem().toString().equals("NIMESHA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 1.55);
                            } else if (s.getSelectedItem().toString().equals("KASHTA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 4.66);
                            } else if (s.getSelectedItem().toString().equals("KALA")) {
                                a = Double.valueOf(e1.getText().toString()) * 140;
                            } else if (s.getSelectedItem().toString().equals("GHATI")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 60;
                            } else if (s.getSelectedItem().toString().equals("MUHURTA")) {
                                a = Double.valueOf(e1.getText().toString()) * 48 * 60;
                            } else if (s.getSelectedItem().toString().equals("AHORATRA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600;
                            } else if (s.getSelectedItem().toString().equals("PAKSA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 15;
                            } else if (s.getSelectedItem().toString().equals("MASA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30;
                            } else if (s.getSelectedItem().toString().equals("RITU")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 60;
                            } else if (s.getSelectedItem().toString().equals("AYANA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30 * 6;
                            } else if (s.getSelectedItem().toString().equals("SAMVATSARA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30 * 12;
                            } else if (s.getSelectedItem().toString().equals("YUGA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30 * 12 * 5;
                            } else if (s.getSelectedItem().toString().equals("AHORATRA(DEVA'S)")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30 * 12;
                            } else if (s.getSelectedItem().toString().equals("AHORATRA(PITARA'S)")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30;
                            }

                            if (u.getSelectedItem().toString().equals("SECOND")) {
                                e2.setText(String.format("%.4f", a) + " seconds");
                            } else if (u.getSelectedItem().toString().equals("MINUTE")) {
                                e2.setText(String.format("%.4f", a / 60) + " minutes");
                            } else if (u.getSelectedItem().toString().equals("HOUR")) {
                                e2.setText(String.format("%.4f", a / 3600) + " hours");
                            } else if (u.getSelectedItem().toString().equals("DAYS")) {
                                e2.setText(String.format("%.4f", a / (3600 * 24)) + " days");
                            } else if (u.getSelectedItem().toString().equals("MONTH")) {
                                e2.setText(String.format("%.4f", a / (3600 * 24 * 30)) + " months");
                            } else if (u.getSelectedItem().toString().equals("YEAR")) {
                                e2.setText(String.format("%.4f", a / (3600 * 24 * 30 * 12)) + " years");
                            }

                        }
                    } else if (aut.getSelectedItem().toString().equals("SUSHRUTA")) {
                        if (c.getSelectedItem().toString().equals("LINEAR UNITS")) {
                            Toast.makeText(getApplicationContext(), "Unit not available for Sushruta", Toast.LENGTH_SHORT).show();
                        } else if (c.getSelectedItem().toString().equals("WEIGHT UNITS")) {
                            Toast.makeText(getApplicationContext(), "Unit not available for Sushruta", Toast.LENGTH_SHORT).show();
                        } else if (c.getSelectedItem().toString().equals("TIME UNITS")) {
                            if (s.getSelectedItem().toString().equals("KNASA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 0.38);
                            } else if (s.getSelectedItem().toString().equals("LAVA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 0.77);
                            } else if (s.getSelectedItem().toString().equals("NIMESHA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 1.55);
                            } else if (s.getSelectedItem().toString().equals("KASHTA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 4.66);
                            } else if (s.getSelectedItem().toString().equals("KALA")) {
                                a = Double.valueOf(e1.getText().toString()) * 140;
                            } else if (s.getSelectedItem().toString().equals("GHATI")) {
                                Toast.makeText(getApplicationContext(), "Unit not available for Sushruta", Toast.LENGTH_SHORT).show();
                            } else if (s.getSelectedItem().toString().equals("MUHURTA")) {
                                a = Double.valueOf(e1.getText().toString()) * 48 * 60;
                            } else if (s.getSelectedItem().toString().equals("AHORATRA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600;
                            } else if (s.getSelectedItem().toString().equals("PAKSA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 15;
                            } else if (s.getSelectedItem().toString().equals("MASA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30;
                            } else if (s.getSelectedItem().toString().equals("RITU")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 60;
                            } else if (s.getSelectedItem().toString().equals("AYANA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30 * 6;
                            } else if (s.getSelectedItem().toString().equals("SAMVATSARA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30 * 12;
                            } else if (s.getSelectedItem().toString().equals("YUGA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30 * 12 * 5;
                            } else if (s.getSelectedItem().toString().equals("AHORATRA(DEVA'S)")) {
                                Toast.makeText(getApplicationContext(), "Unit not available for Sushruta", Toast.LENGTH_SHORT).show();
                            } else if (s.getSelectedItem().toString().equals("AHORATRA(PITARA'S)")) {
                                Toast.makeText(getApplicationContext(), "Unit not available for Sushruta", Toast.LENGTH_SHORT).show();
                            }

                            if (u.getSelectedItem().toString().equals("SECOND")) {
                                e2.setText(String.format("%.4f", a) + " seconds");
                            } else if (u.getSelectedItem().toString().equals("MINUTE")) {
                                e2.setText(String.format("%.4f", a / 60) + " minutes");
                            } else if (u.getSelectedItem().toString().equals("HOUR")) {
                                e2.setText(String.format("%.4f", a / 3600) + " hours");
                            } else if (u.getSelectedItem().toString().equals("DAYS")) {
                                e2.setText(String.format("%.4f", a / (3600 * 24)) + " days");
                            } else if (u.getSelectedItem().toString().equals("MONTH")) {
                                e2.setText(String.format("%.4f", a / (3600 * 24 * 30)) + " months");
                            } else if (u.getSelectedItem().toString().equals("YEAR")) {
                                e2.setText(String.format("%.4f", a / (3600 * 24 * 30 * 12)) + " years");
                            }

                        }
                    } else if (aut.getSelectedItem().toString().equals("CHARAKA")) {
                        if (c.getSelectedItem().toString().equals("LINEAR UNITS")) {
                            Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                        } else if (c.getSelectedItem().toString().equals("WEIGHT UNITS")) {
                            if (s.getSelectedItem().toString().equals("anu"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("Paramanu"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("truti"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("liksha"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("vanshi"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("dhwanshi"))
                                a = Double.valueOf(e1.getText().toString()) * 0.05425;
                            else if (s.getSelectedItem().toString().equals("trasrenu"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("yuka"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("maricha"))
                                a = Double.valueOf(e1.getText().toString()) * 0.3255;
                            else if (s.getSelectedItem().toString().equals("raja"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("rajika"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("rakta sarshapa"))
                                a = Double.valueOf(e1.getText().toString()) * 1.9531;
                            else if (s.getSelectedItem().toString().equals("sarshapa"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("tandula"))
                                a = Double.valueOf(e1.getText().toString()) * 15.625;
                            else if (s.getSelectedItem().toString().equals("dhanyamasha"))
                                a = Double.valueOf(e1.getText().toString()) * 31.25;
                            else if (s.getSelectedItem().toString().equals("yava"))
                                a = Double.valueOf(e1.getText().toString()) * 62.5;
                            else if (s.getSelectedItem().toString().equals("ratti"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("gunja"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("andika"))
                                a = Double.valueOf(e1.getText().toString()) * 250;

                            if (u.getSelectedItem().toString().equals("GRAM (g)")) {
                                e2.setText(String.format("%.4f", a / 1000) + " g");
                            } else if (u.getSelectedItem().toString().equals("MILLIGRAM (mg)")) {
                                e2.setText(String.format("%.4f", a) + " mg");
                            } else if (u.getSelectedItem().toString().equals("KILOGRAM (kg)")) {
                                e2.setText(String.format("%.4f", a / 1000000) + " kg");
                            }
                        } else if (c.getSelectedItem().toString().equals("TIME UNITS")) {
                            if (s.getSelectedItem().toString().equals("KNASA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 0.38);
                            } else if (s.getSelectedItem().toString().equals("LAVA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 0.77);
                            } else if (s.getSelectedItem().toString().equals("NIMESHA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 1.55);
                            } else if (s.getSelectedItem().toString().equals("KASHTA")) {
                                a = (Double.valueOf(e1.getText().toString()) * 4.66);
                            } else if (s.getSelectedItem().toString().equals("KALA")) {
                                a = Double.valueOf(e1.getText().toString()) * 140;
                            } else if (s.getSelectedItem().toString().equals("GHATI")) {
                                Toast.makeText(getApplicationContext(), "Unit not available for Sushruta", Toast.LENGTH_SHORT).show();
                            } else if (s.getSelectedItem().toString().equals("MUHURTA")) {
                                a = Double.valueOf(e1.getText().toString()) * 48 * 60;
                            } else if (s.getSelectedItem().toString().equals("AHORATRA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600;
                            } else if (s.getSelectedItem().toString().equals("PAKSA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 15;
                            } else if (s.getSelectedItem().toString().equals("MASA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30;
                            } else if (s.getSelectedItem().toString().equals("RITU")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 60;
                            } else if (s.getSelectedItem().toString().equals("AYANA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30 * 6;
                            } else if (s.getSelectedItem().toString().equals("SAMVATSARA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30 * 12;
                            } else if (s.getSelectedItem().toString().equals("YUGA")) {
                                a = Double.valueOf(e1.getText().toString()) * 24 * 3600 * 30 * 12 * 5;
                            } else if (s.getSelectedItem().toString().equals("AHORATRA(DEVA'S)")) {
                                Toast.makeText(getApplicationContext(), "Unit not available for Sushruta", Toast.LENGTH_SHORT).show();
                            } else if (s.getSelectedItem().toString().equals("AHORATRA(PITARA'S)")) {
                                Toast.makeText(getApplicationContext(), "Unit not available for Sushruta", Toast.LENGTH_SHORT).show();
                            }

                            if (u.getSelectedItem().toString().equals("SECOND")) {
                                e2.setText(String.format("%.4f", a) + " seconds");
                            } else if (u.getSelectedItem().toString().equals("MINUTE")) {
                                e2.setText(String.format("%.4f", a / 60) + " minutes");
                            } else if (u.getSelectedItem().toString().equals("HOUR")) {
                                e2.setText(String.format("%.4f", a / 3600) + " hours");
                            } else if (u.getSelectedItem().toString().equals("DAYS")) {
                                e2.setText(String.format("%.4f", a / (3600 * 24)) + " days");
                            } else if (u.getSelectedItem().toString().equals("MONTH")) {
                                e2.setText(String.format("%.4f", a / (3600 * 24 * 30)) + " months");
                            } else if (u.getSelectedItem().toString().equals("YEAR")) {
                                e2.setText(String.format("%.4f", a / (3600 * 24 * 30 * 12)) + " years");
                            }

                        }
                    } else if (aut.getSelectedItem().toString().equals("SHARANGADHARA")) {
                        if (c.getSelectedItem().toString().equals("LINEAR UNITS")) {
                            Toast.makeText(getApplicationContext(), "Unit not available for Sushruta", Toast.LENGTH_SHORT).show();
                        } else if (c.getSelectedItem().toString().equals("WEIGHT UNITS")) {
                            if (s.getSelectedItem().toString().equals("anu"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("Paramanu"))
                                a = Double.valueOf(e1.getText().toString()) * 0.0012;
                            else if (s.getSelectedItem().toString().equals("truti"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("liksha"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("vanshi"))
                                a = Double.valueOf(e1.getText().toString()) * 0.0361;
                            else if (s.getSelectedItem().toString().equals("dhwanshi"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("trasrenu"))
                                a = Double.valueOf(e1.getText().toString()) * 0.0361;
                            else if (s.getSelectedItem().toString().equals("yuka"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("maricha"))
                                a = Double.valueOf(e1.getText().toString()) * 0.217;
                            else if (s.getSelectedItem().toString().equals("raja"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("rajika"))
                                a = Double.valueOf(e1.getText().toString()) * 1.302;
                            else if (s.getSelectedItem().toString().equals("rakta sarshapa"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("sarshapa"))
                                a = Double.valueOf(e1.getText().toString()) * 3.906;
                            else if (s.getSelectedItem().toString().equals("tandula"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("dhanyamasha"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("yava"))
                                a = Double.valueOf(e1.getText().toString()) * 31.25;
                            else if (s.getSelectedItem().toString().equals("ratti"))
                                a = Double.valueOf(e1.getText().toString()) * 125;
                            else if (s.getSelectedItem().toString().equals("gunja"))
                                a = Double.valueOf(e1.getText().toString()) * 125;
                            else if (s.getSelectedItem().toString().equals("andika"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();

                            if (u.getSelectedItem().toString().equals("GRAM (g)")) {
                                e2.setText(String.format("%.4f", a / 1000) + " g");
                            } else if (u.getSelectedItem().toString().equals("MILLIGRAM (mg)")) {
                                e2.setText(String.format("%.4f", a) + " mg");
                            } else if (u.getSelectedItem().toString().equals("KILOGRAM (kg)")) {
                                e2.setText(String.format("%.4f", a / 1000000) + " kg");
                            }
                        } else if (c.getSelectedItem().toString().equals("TIME UNITS")) {
                            Toast.makeText(getApplicationContext(), "Unit not available for Sushruta", Toast.LENGTH_SHORT).show();
                        }
                    } else if (aut.getSelectedItem().toString().equals("RRS")) {
                        if (c.getSelectedItem().toString().equals("LINEAR UNITS")) {
                            Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                        } else if (c.getSelectedItem().toString().equals("WEIGHT UNITS")) {
                            if (s.getSelectedItem().toString().equals("anu"))
                                a = Double.valueOf(e1.getText().toString()) * 0.0004463;
                            else if (s.getSelectedItem().toString().equals("Paramanu"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("truti"))
                                a = Double.valueOf(e1.getText().toString()) * 0.002678;
                            else if (s.getSelectedItem().toString().equals("liksha"))
                                a = Double.valueOf(e1.getText().toString()) * 0.01607;
                            else if (s.getSelectedItem().toString().equals("vanshi"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("dhwanshi"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("trasrenu"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("yuka"))
                                a = Double.valueOf(e1.getText().toString()) * 0.09645;
                            else if (s.getSelectedItem().toString().equals("maricha"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("raja"))
                                a = Double.valueOf(e1.getText().toString()) * 0.5787;
                            else if (s.getSelectedItem().toString().equals("rajika"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("rakta sarshapa"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("sarshapa"))
                                a = Double.valueOf(e1.getText().toString()) * 3.472;
                            else if (s.getSelectedItem().toString().equals("tandula"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("dhanyamasha"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("yava"))
                                a = Double.valueOf(e1.getText().toString()) * 20.833;
                            else if (s.getSelectedItem().toString().equals("ratti"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("gunja"))
                                a = Double.valueOf(e1.getText().toString()) * 125;
                            else if (s.getSelectedItem().toString().equals("andika"))
                                Toast.makeText(getApplicationContext(), "Unit not available for RRS", Toast.LENGTH_SHORT).show();


                            if (u.getSelectedItem().toString().equals("GRAM (g)")) {
                                e2.setText(String.format("%.4f", a / 1000) + " g");
                            } else if (u.getSelectedItem().toString().equals("MILLIGRAM (mg)")) {
                                e2.setText(String.format("%.4f", a) + " mg");
                            } else if (u.getSelectedItem().toString().equals("KILOGRAM (kg)")) {
                                e2.setText(String.format("%.4f", a / 1000000) + " kg");
                            }
                        } else if (c.getSelectedItem().toString().equals("TIME UNITS")) {
                            Toast.makeText(getApplicationContext(), "Unit not Available for RRS", Toast.LENGTH_SHORT).show();
                        }
                    }


                    else if (aut.getSelectedItem().toString().equals("VAIDYAKA PARIBHASA PRADEEPA")) {
                        if (c.getSelectedItem().toString().equals("LINEAR UNITS")) {
                            Toast.makeText(getApplicationContext(), "Unit not available for VAIDYAKA PARIBHASHA PRADEEPA", Toast.LENGTH_SHORT).show();
                        }

                        else if (c.getSelectedItem().toString().equals("WEIGHT UNITS")) {
                            if (s.getSelectedItem().toString().equals("anu"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("Paramanu"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("truti"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("liksha"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("vanshi"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("dhwanshi"))
                                a = Double.valueOf(e1.getText().toString()) * 0.0361;
                            else if (s.getSelectedItem().toString().equals("trasrenu"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("yuka"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("maricha"))
                                a = Double.valueOf(e1.getText().toString()) * 0.217;
                            else if (s.getSelectedItem().toString().equals("raja"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("rajika"))
                                a = Double.valueOf(e1.getText().toString()) * 1.302;
                            else if (s.getSelectedItem().toString().equals("rakta sarshapa"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("sarshapa"))
                                a = Double.valueOf(e1.getText().toString()) * 3.906;
                            else if (s.getSelectedItem().toString().equals("tandula"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("dhanyamasha"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();
                            else if (s.getSelectedItem().toString().equals("yava"))
                                a = Double.valueOf(e1.getText().toString()) * 31.25;
                            else if (s.getSelectedItem().toString().equals("ratti"))
                                a = Double.valueOf(e1.getText().toString()) * 125;
                            else if (s.getSelectedItem().toString().equals("gunja"))
                                a = Double.valueOf(e1.getText().toString()) * 125;
                            else if (s.getSelectedItem().toString().equals("andika"))
                                Toast.makeText(getApplicationContext(), "Unit not available for Charaka", Toast.LENGTH_SHORT).show();

                            if (u.getSelectedItem().toString().equals("GRAM (g)")) {
                                e2.setText(String.format("%.4f", a / 1000) + " g");
                            } else if (u.getSelectedItem().toString().equals("MILLIGRAM (mg)")) {
                                e2.setText(String.format("%.4f", a) + " mg");
                            } else if (u.getSelectedItem().toString().equals("KILOGRAM (kg)")) {
                                e2.setText(String.format("%.4f", a / 1000000) + " kg");
                            }

                        } else if (c.getSelectedItem().toString().equals("TIME UNITS")) {
                            Toast.makeText(getApplicationContext(), "Unit not available for VAIDYAKA PARIBHASHA PRADEEPA", Toast.LENGTH_SHORT).show();
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
