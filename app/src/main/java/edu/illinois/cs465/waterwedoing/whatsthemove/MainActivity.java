package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

    private Button next;
    private Button slide;
    private Button filters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = (Button) findViewById(R.id.buttonNext);
        slide = (Button) findViewById(R.id.buttonSlide);
        filters = (Button) findViewById(R.id.FiltersPage);
        filters.setOnClickListener(this);
        next.setOnClickListener(this);
        slide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonNext){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.buttonSlide){
            Intent intent = new Intent(this, ScreenSlidePagerActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.FiltersPage){
            Intent intent = new Intent(this, SetFiltersActivity.class);
            startActivity(intent);
        }
    }
}
