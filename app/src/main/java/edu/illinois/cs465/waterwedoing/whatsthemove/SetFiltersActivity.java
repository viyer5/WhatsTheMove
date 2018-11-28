package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class SetFiltersActivity extends Activity implements View.OnClickListener {
    private Button whatsTheMove;
    private Button cancel;
    private Button resetFilter;
    private SeekBar priceRange;
    private SeekBar distance;
    private SeekBar duration;
    private RadioButton pRangeAnyButton;
    private RadioButton distanceRangeAnyButton;
    private RadioButton durationRangeAnyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_filters);

        whatsTheMove = (Button) findViewById(R.id.whatsTheMoveButton);
        cancel = (Button) findViewById(R.id.cancelButton);
        whatsTheMove.setOnClickListener(this);
        cancel.setOnClickListener(this);
        resetFilter = (Button) findViewById(R.id.resetFilter);
        resetFilter.setOnClickListener(this);
        pRangeAnyButton = (RadioButton) findViewById(R.id.RadioButtonPriceRange);


        priceRange = (SeekBar) findViewById(R.id.seekaBarPriceRange);
        priceRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressVal = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressVal = progress;
                pRangeAnyButton.setChecked(false);
                Toast.makeText(getApplicationContext(), "Set a value between 0 to 100$", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String output = "Price Range is Set To: " + progressVal + " $";
                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
            }
        });

        distanceRangeAnyButton = (RadioButton) findViewById(R.id.RadioButtonDistanceRange);
        distance = (SeekBar) findViewById(R.id.seekaBarDistance);
        distance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressVal = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressVal = progress;
                distanceRangeAnyButton.setChecked(false);
                Toast.makeText(getApplicationContext(), "Set a value between 0 mi to 30 mi", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String output = "Distance Range is Set To: " + progressVal + " miles";
                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
            }
        });
        durationRangeAnyButton = (RadioButton) findViewById(R.id.RadioButtonDurationRange);
        duration = (SeekBar)findViewById(R.id.seekaBarDuration);
        duration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressVal = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressVal = progress;
                durationRangeAnyButton.setChecked(false);
                Toast.makeText(getApplicationContext(), "Set a value between 0 hour to 12 hours", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String output = "Duration Range is Set To: " + progressVal + " hours";
                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cancelButton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.whatsTheMoveButton){
            Intent intent = new Intent(this, ScreenSlidePagerActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.resetFilter){
            Intent intent = new Intent(this, SetFiltersActivity.class);
            startActivity(intent);
        }
    }
}
