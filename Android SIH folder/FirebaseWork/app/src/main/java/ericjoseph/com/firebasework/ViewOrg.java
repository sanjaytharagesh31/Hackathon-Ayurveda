package ericjoseph.com.firebasework;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ViewOrg extends Activity {

    Bitmap bmp;

    //database reference to get uploads data
    DatabaseReference orgDatabaseReference = FirebaseDatabase.getInstance().getReference("Organization");

    Button btnnext,btnprev,loadvalues;
    ImageView img;
    EditText name,phone,address,mail;
    //list to store uploads data
    List<Organization> orgList;
    int i=0;

    URL cert,image;
    Organization org;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_org);

        //getting the database reference
        orgList = new ArrayList<>();

        loadvalues = (Button)findViewById(R.id.loadvalues);
        btnprev = (Button)findViewById(R.id.btnprev);
        btnnext = (Button)findViewById(R.id.btnnext);

        img = (ImageView)findViewById(R.id.orgimg);

        name = (EditText)findViewById(R.id.orgname);
        phone = (EditText)findViewById(R.id.orgphn);
        address = (EditText)findViewById(R.id.orgaddr);
        mail = (EditText)findViewById(R.id.orgmail);

        loadvalues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orgList.clear();

                orgDatabaseReference = FirebaseDatabase.getInstance().getReference("Organization");
                orgDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            Organization org = postSnapshot.getValue(Organization.class);
                            orgList.add(org);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Database Error",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orgList.size()<=0)
                    Toast.makeText(getApplicationContext(),"Plese Press Load Data Button",Toast.LENGTH_SHORT).show();
                else
                {
                if (i < 0)
                    Toast.makeText(getApplicationContext(), "You have reeached the beginning", Toast.LENGTH_SHORT).show();
                else {
                    org = orgList.get(i);
                    name.setText(org.orgname);
                  //  phone.setText((int) org.phnum);
                    mail.setText(org.orgemail);
                    //address.setText(org.Address);

                    try {
                        cert = new URL(org.orgcert);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    try {
                        image = new URL(org.orgimage);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    try {
                        bmp = BitmapFactory.decodeStream(image.openConnection().getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    img.setImageBitmap(bmp);
                    i--;
                }
            }
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orgList.size()<=0)
                    Toast.makeText(getApplicationContext(),"Plese Press Load Data Button",Toast.LENGTH_SHORT).show();
                else {
                    if (i > orgList.size())
                        Toast.makeText(getApplicationContext(), "You have reeached the beginning", Toast.LENGTH_SHORT).show();
                    else {
                        org = orgList.get(i);
                        name.setText(org.orgname);
                       // phone.setText((int) org.phnum);
                        mail.setText(org.orgemail);
                        //address.setText(org.Address);

                        try {
                            cert = new URL(org.orgcert);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        try {
                            image = new URL(org.orgimage);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                        try {
                            bmp = BitmapFactory.decodeStream(image.openConnection().getInputStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        img.setImageBitmap(bmp);
                        i++;
                    }
                }
            }
        });
    }
}