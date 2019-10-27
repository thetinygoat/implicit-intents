package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText msShareEditText;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = findViewById(R.id.web_url);
        mLocationEditText = findViewById(R.id.location);
        msShareEditText = findViewById(R.id.share_text);
    }
    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri Webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, Webpage);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d("Implicit Intent", "cannot handle this intent");
            Log.d("Implicit Intent", url);
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        Uri location = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, location);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else{
            Log.d("Implicit Intent", "cannot handle this intent");
            Log.d("Implicit Intent", loc);
        }
    }

    public void openShare(View view) {
        String text = msShareEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle(R.string.chooser_title).setText(text).startChooser();
    }
}
