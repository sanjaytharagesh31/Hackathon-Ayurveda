package ericjoseph.com.firebasework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class StartPage extends AppCompatActivity {

    ImageView Converter,rsc_view,rsc_upload,org_view,org_upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        Converter = (ImageView) findViewById(R.id.Ayurselect);
        rsc_view = (ImageView)findViewById(R.id.siddhaselect);
        rsc_upload = (ImageView)findViewById(R.id.unaniselect);
        org_upload = (ImageView)findViewById(R.id.org_upload);
        org_view = (ImageView)findViewById(R.id.org_view);

        Converter.setImageResource(R.drawable.converter);
        rsc_view.setImageResource(R.drawable.viewupl);
        rsc_upload.setImageResource(R.drawable.uplres);
        org_upload.setImageResource(R.drawable.register);
        org_view.setImageResource(R.drawable.orgicon);
    }
    public void onClick(View view)
    { if(view.getId() == Converter.getId())
          startActivity(new Intent(this,ConverterSelect.class));
        else if(view.getId() == rsc_upload.getId())
          startActivity(new Intent(this,Main2Activity.class));
        else if(view.getId() == rsc_view.getId())
          startActivity(new Intent(this,ViewUploads.class));
        else if(view.getId() == org_upload.getId())
          startActivity(new Intent(this,OrgDataUpload.class));
        else if(view.getId() == org_view.getId())
          startActivity(new Intent(this,ViewOrg.class));
    }
}
