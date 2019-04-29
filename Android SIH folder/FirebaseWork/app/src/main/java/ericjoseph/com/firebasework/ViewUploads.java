package ericjoseph.com.firebasework;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewUploads extends Activity {

    //the listview
    ListView listView;

    //database reference to get uploads data
    DatabaseReference mDatabaseReference,videoDatabaseReference,textDatabaseReference,audioDatabaseReference;
    Button select;
    //list to store uploads data
    List<Upload> pdfuploadList,mp4uploadList,txtuploadList,audiouploadlist;

    RadioButton pdfradio,mp4radio,txtradio,audioradio;
    Upload upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploads);

        //getting the database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("PDF Uploads");
        videoDatabaseReference = FirebaseDatabase.getInstance().getReference("Video Uploads");
        textDatabaseReference = FirebaseDatabase.getInstance().getReference("Text Uploads");
        audioDatabaseReference = FirebaseDatabase.getInstance().getReference("Audio Uploads");

        pdfuploadList = new ArrayList<>();
        mp4uploadList = new ArrayList<>();
        txtuploadList = new ArrayList<>();
        audiouploadlist = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);

        pdfradio = (RadioButton)findViewById(R.id.pdfradio);
        mp4radio = (RadioButton)findViewById(R.id.mp4radio);
        txtradio = (RadioButton)findViewById(R.id.txtradio);
        audioradio = (RadioButton)findViewById(R.id.audioradio);

        select = (Button)findViewById(R.id.button2);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pdfradio.isChecked())
                {   //retrieving upload data from firebase database
                    pdfuploadList.clear();
                    mDatabaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                Upload upload = postSnapshot.getValue(Upload.class);
                                pdfuploadList.add(upload);
                            }

                            String[] uploads = new String[pdfuploadList.size()];

                            for (int i = 0; i < uploads.length; i++) {
                                uploads[i] = pdfuploadList.get(i).getName();
                            }

                            //displaying it to list
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uploads);
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(),"Database Error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if(mp4radio.isChecked())
                {   //retrieving upload data from firebase database
                    mp4uploadList.clear();
                    videoDatabaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                            { Upload upload = postSnapshot.getValue(Upload.class);
                                mp4uploadList.add(upload);
                            }
                            String[] mp4uploads = new String[mp4uploadList.size()];

                            for(int i =0;i< mp4uploads.length;i++)
                                mp4uploads[i]= mp4uploadList.get(i).getName();

                            ArrayAdapter<String> mp4adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,mp4uploads);
                            listView.setAdapter(mp4adapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(),"Database Error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                else if(txtradio.isChecked())
                {   //retrieving upload data from firebase database
                    txtuploadList.clear();
                    textDatabaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                            { Upload upload = postSnapshot.getValue(Upload.class);
                                txtuploadList.add(upload);
                            }
                            String[] txtuploads = new String[txtuploadList.size()];

                            for(int i =0;i< txtuploads.length;i++)
                                txtuploads[i]= txtuploadList.get(i).getName();

                            ArrayAdapter<String> txtadapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,txtuploads);
                            listView.setAdapter(txtadapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(),"Database Error",Toast.LENGTH_SHORT).show();
                        }
                    });

                }

                else if(audioradio.isChecked())
                {   //retrieving upload data from firebase database
                    audiouploadlist.clear();
                    audioDatabaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                            { Upload upload = postSnapshot.getValue(Upload.class);
                                audiouploadlist.add(upload);
                            }
                            String[] audiouploads = new String[audiouploadlist.size()];

                            for(int i =0;i< audiouploads.length;i++)
                                audiouploads[i]= audiouploadlist.get(i).getName();

                            ArrayAdapter<String> audioadapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,audiouploads);
                            listView.setAdapter(audioadapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(),"Database Error",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });




        //adding a clicklistener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the upload
                if(pdfradio.isChecked())
                    upload = pdfuploadList.get(i);

                else if(mp4radio.isChecked())
                    upload = mp4uploadList.get(i);

                else if(txtradio.isChecked())
                    upload = txtuploadList.get(i);

                else if(audioradio.isChecked())
                    upload = audiouploadlist.get(i);


                //Opening the upload file in browser using the upload url
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(upload.getUrl()));
                startActivity(intent);
            }
        });
    }
}