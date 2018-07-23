package com.example.bbrothers.cinesky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        String title = (String) intent.getSerializableExtra("title");
        String overview = (String) intent.getSerializableExtra("overview");
        String duration = (String) intent.getSerializableExtra("duration");
        String release_year = (String) intent.getSerializableExtra("release_year");
        ArrayList<String> backdrops_url = intent.getStringArrayListExtra("backdrops_url");

        TextView tvTitle = (TextView) findViewById(R.id.mvTitle);
        tvTitle.setText(title);

        TextView tvYear = (TextView) findViewById(R.id.mvYear);
        tvYear.setText(release_year);

        TextView tvDuration = (TextView) findViewById(R.id.mvDuration);
        tvDuration.setText(duration);

        LinearLayout hsLinear = (LinearLayout) findViewById(R.id.hsLinear);

        if(backdrops_url.size() > 1){
            for(int i = 0; i<backdrops_url.size(); i++){
                ImageView imgShow = new ImageView(this);
                imgShow.setLayoutParams(
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                loadImages(backdrops_url.get(i), imgShow);
                hsLinear.addView(imgShow);
            }
        }else{
            ImageView imgShow = new ImageView(this);
            imgShow.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

            loadImages(R.drawable.unloaded_image, imgShow);
            hsLinear.addView(imgShow);
        }


        TextView tvDesc = (TextView) findViewById(R.id.mvDesc);
        tvDesc.setText(overview);

    }

    public void loadImages(String url, ImageView iv){
        Picasso.get().load(url).resize(720, 400).centerCrop().into(iv);
    }

    public void loadImages(int i, ImageView iv){
        Picasso.get().load(i).resize(720, 400).centerCrop().into(iv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            this.finish();

        return super.onOptionsItemSelected(item);
    }

}
