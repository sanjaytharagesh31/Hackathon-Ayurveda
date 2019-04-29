package ericjoseph.com.firebasework;

        import android.Manifest;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.net.Uri;
        import android.os.Build;
        import android.os.Bundle;
        import android.provider.Settings;
        import android.support.annotation.NonNull;
        import android.support.annotation.VisibleForTesting;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.OnProgressListener;
        import com.google.firebase.storage.StorageReference;
        import com.google.firebase.storage.UploadTask;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    //this is the pic pdf code used in file chooser
    final static int PICK_PDF_CODE = 2342;
    final static int PICK_VIDEO_CODE = 2099;
    final static int PICK_TEXT_CODE = 2154;
    final static int PICK_AUDIO_CODE = 1998;

    //these are the views
    TextView textViewStatus;
    EditText editTextFilename;
    ProgressBar progressBar;
    RadioButton pdfradio, mp4radio, txtradio, audioradio;
    //the firebase objects for storage and database
    StorageReference mStorageReference, videoStorageRefernce,textStorageReference,audioStorageReference;
    DatabaseReference mDatabaseReference, videoDatabaseRefernce,textDatabaseReference,audioDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //getting firebase objects
        mStorageReference = FirebaseStorage.getInstance().getReference();
        videoStorageRefernce = FirebaseStorage.getInstance().getReference();
        textStorageReference = FirebaseStorage.getInstance().getReference();
        audioStorageReference = FirebaseStorage.getInstance().getReference();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("PDF Uploads");
        videoDatabaseRefernce = FirebaseDatabase.getInstance().getReference("Video Uploads");
        textDatabaseReference = FirebaseDatabase.getInstance().getReference("Text Uploads");
        audioDatabaseReference = FirebaseDatabase.getInstance().getReference("Audio Uploads");

        //getting the views
        textViewStatus = (TextView) findViewById(R.id.textViewStatus);
        editTextFilename = (EditText) findViewById(R.id.editTextFileName);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        pdfradio = (RadioButton) findViewById(R.id.pdfradio);
        mp4radio = (RadioButton) findViewById(R.id.mp4radio);
        txtradio = (RadioButton) findViewById(R.id.txtradio);
        audioradio = (RadioButton) findViewById(R.id.audioradio);

        //attaching listeners to views
        findViewById(R.id.buttonUploadFile).setOnClickListener(this);
        findViewById(R.id.textViewUploads).setOnClickListener(this);
    }

    //this function will get the pdf from the storage
    public void getPDF() {
        //for greater than lolipop versions we need the permissions asked on runtime
        //so if the permission is not available user will go to the screen to allow storage permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }

        //creating an intent for file chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select PDF File"), PICK_PDF_CODE);
    }

    public void getMP4() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission
                (this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Intent mp4intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package: " + getPackageName()));
            startActivity(mp4intent);
            return;
        }
        Intent mp4 = new Intent();
        mp4.setType("video/*");
        mp4.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(mp4, "Select Video File"), PICK_VIDEO_CODE);
    }

    public void getTextFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission
                (this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Intent mp4intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package: " + getPackageName()));
            startActivity(mp4intent);
            return;
        }
        Intent mp4 = new Intent();
        mp4.setType("text/*");
        mp4.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(mp4, "Select Text File"), PICK_TEXT_CODE);
    }

    public void getAudioFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission
                (this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Intent mp4intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package: " + getPackageName()));
            startActivity(mp4intent);
            return;
        }
        Intent mp4 = new Intent();
        mp4.setType("audio/*");
        mp4.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(mp4, "Select Audio File"), PICK_AUDIO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                uploadFile(data.getData(), PICK_PDF_CODE);
            } else {
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PICK_VIDEO_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (data.getData() != null)
                uploadFile(data.getData(), PICK_VIDEO_CODE);
            else
                Toast.makeText(getApplicationContext(), "No File Chosen", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == PICK_TEXT_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (data.getData() != null)
                uploadFile(data.getData(), PICK_TEXT_CODE);
            else
                Toast.makeText(getApplicationContext(), "No File Chosen", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == PICK_AUDIO_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (data.getData() != null)
                uploadFile(data.getData(), PICK_AUDIO_CODE);
            else
                Toast.makeText(getApplicationContext(), "No File Chosen", Toast.LENGTH_SHORT).show();
        }
    }

    //this method is uploading the file
    //the code is same as the previous tutorial
    //so we are not explaining it
    private void uploadFile(Uri data, int r) {
        if (r == PICK_PDF_CODE) {
            progressBar.setVisibility(View.VISIBLE);
            StorageReference sRef = mStorageReference.child("/PDF Uploads/" + editTextFilename.getText().toString() + ".pdf");
            sRef.putFile(data)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            textViewStatus.setText("PDF File Uploaded Successfully");

                            Upload upload = new Upload(editTextFilename.getText().toString(), taskSnapshot.getDownloadUrl().toString());
                            mDatabaseReference.child(mDatabaseReference.push().getKey()).setValue(upload);
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
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            textViewStatus.setText((int) progress + "% Uploading...");
                        }
                    });
        } else if (r == PICK_VIDEO_CODE) {
            progressBar.setVisibility(View.VISIBLE);
            StorageReference mp4ref = videoStorageRefernce.child("/MP4 Uploads/" + editTextFilename.getText().toString() + ".mp4");
            mp4ref.putFile(data)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            textViewStatus.setText("Video File Uploaded Successfully");

                            Upload upload = new Upload(editTextFilename.getText().toString(), taskSnapshot.getDownloadUrl().toString());
                            videoDatabaseRefernce.child(videoDatabaseRefernce.push().getKey()).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests") //Important Code to be added
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            textViewStatus.setText((int) progress + "% Uploading...");
                        }
                    });
        }
        else if (r == PICK_TEXT_CODE) {
            progressBar.setVisibility(View.VISIBLE);
            StorageReference txtref = textStorageReference.child("/Text Uploads/" + editTextFilename.getText().toString() + ".txt");
            txtref.putFile(data)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            textViewStatus.setText("Text File Uploaded Successfully");

                            Upload upload = new Upload(editTextFilename.getText().toString(), taskSnapshot.getDownloadUrl().toString());
                            textDatabaseReference.child(textDatabaseReference.push().getKey()).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests") //Important Code to be added
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            textViewStatus.setText((int) progress + "% Uploading...");
                        }
                    });
        }
        else if (r == PICK_AUDIO_CODE) {
            progressBar.setVisibility(View.VISIBLE);
            StorageReference audioref = textStorageReference.child("/Audio Uploads/" + editTextFilename.getText().toString() + ".txt");
            audioref.putFile(data)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBar.setVisibility(View.GONE);
                            textViewStatus.setText("Audio File Uploaded Successfully");

                            Upload upload = new Upload(editTextFilename.getText().toString(), taskSnapshot.getDownloadUrl().toString());
                            audioDatabaseReference.child(audioDatabaseReference.push().getKey()).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests") //Important Code to be added
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            textViewStatus.setText((int) progress + "% Uploading...");
                        }
                    });
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonUploadFile && pdfradio.isChecked())
            getPDF();

        else if (view.getId() == R.id.buttonUploadFile && mp4radio.isChecked())
            getMP4();

        else if (view.getId() == R.id.buttonUploadFile && txtradio.isChecked())
            getTextFile();

        else if (view.getId() == R.id.buttonUploadFile && audioradio.isChecked())
            getAudioFile();

        if(view.getId() == R.id.textViewUploads)
            startActivity(new Intent(this, ViewUploads.class));
    }
}