package ericjoseph.com.firebasework;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class OrgDataUpload extends Activity {
    Button uploadimg, uploadcert, senddetail, orgview;
    EditText name, phone, email, address,cert,image;
    String imgurl,certurl;
    Intent imgselect, certselect;
    Uri imgdata,certdata;

    final static int PICK_IMG_CODE = 2099;
    final static int PICK_PDF_CODE = 1998;
    DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("Organization");
    StorageReference mStorageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_data_upload);
        uploadimg = (Button) findViewById(R.id.uploadimg);
        uploadcert = (Button) findViewById(R.id.uploadcert);
        senddetail = (Button) findViewById(R.id.senddetail);
        orgview = (Button) findViewById(R.id.orgview);

        name = (EditText) findViewById(R.id.orgname);
        phone = (EditText) findViewById(R.id.orgphn);
        email = (EditText) findViewById(R.id.orgmail);
        address = (EditText) findViewById(R.id.orgaddr);
        image = (EditText)findViewById(R.id.imgtext);
        cert = (EditText)findViewById(R.id.certtext);

        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Intent mp4intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package: " + getPackageName()));
                    startActivity(mp4intent);
                    return;
                }
                imgselect = new Intent();
                imgselect.setType("image/jpg");
                imgselect.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(imgselect, "Select Organization Image"), PICK_IMG_CODE);
            }
        });

        uploadcert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Intent mp4intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package: " + getPackageName()));
                    startActivity(mp4intent);
                    return;
                }
                certselect = new Intent();
                certselect.setType("application/pdf");
                certselect.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(certselect, "Select Organization Certificate"), PICK_PDF_CODE);
            }
        });

        senddetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imgdata!=null && certdata!=null) {
                    uploadFile(imgdata, PICK_IMG_CODE);
                    uploadFile(certdata, PICK_PDF_CODE);

                    Organization org = new Organization(imgurl, certurl,name.getText().toString(), email.getText().toString(), address.getText().toString(), Long.valueOf(phone.getText().toString()));
                    mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(org);
                    //audioDatabaseReference.child(audioDatabaseReference.push().getKey()).setValue(upload);
                }
                else
                {  if((imgdata==null)||(imgurl==null))
                        Toast.makeText(getApplicationContext(),"Please select Image for Organizaation",Toast.LENGTH_SHORT).show();
                    if((certdata==null)||(certurl==null))
                        Toast.makeText(getApplicationContext(),"Please select Certificate for Organization",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMG_CODE && resultCode == RESULT_OK && data.getData() != null && data != null)
            imgdata = data.getData();
        else if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data.getData() != null && data != null)
            certdata = data.getData();
    }

    private void uploadFile(Uri data, int r) {
        if (r == PICK_IMG_CODE) {
            if(image.getText().toString().length()!=0) {
                StorageReference sRef = mStorageReference.child("/Organizations/Images/" + image.getText().toString() + ".jpg");
                sRef.putFile(data)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @SuppressWarnings("VisibleForTests")
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getApplicationContext(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                imgurl = taskSnapshot.getDownloadUrl().toString();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @SuppressWarnings("VisibleForTests")
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getApplicationContext(), "Uploading Organization Image", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else
                Toast.makeText(getApplicationContext(),"Please Enter Image Name",Toast.LENGTH_SHORT).show();
        }
        else if (r == PICK_PDF_CODE) {
            if(cert.getText().toString().length()!=0) {
                StorageReference sRef = mStorageReference.child("/Organizations/PDF Certificates/" + cert.getText().toString() + ".pdf");
                sRef.putFile(data)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @SuppressWarnings("VisibleForTests")
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getApplicationContext(), "Certificate Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                certurl = taskSnapshot.getDownloadUrl().toString();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @SuppressWarnings("VisibleForTests")
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getApplicationContext(), "Uploading Organization Certificate", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
            else
                Toast.makeText(getApplicationContext(),"Please Enter Certificate Name",Toast.LENGTH_SHORT).show();
        }
    }
    public void onClick(View view)
    { if(view.getId() == orgview.getId())
        startActivity(new Intent(this,ViewOrg.class));
    }

}
