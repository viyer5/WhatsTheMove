package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private EditText priceText;
    private EditText distanceText;
    private EditText durationText;
    private RadioButton anyTypeRadioButton;
    private RadioButton anyFeatureRadioButton;
    private RadioGroup activeRadioGroup;
    private RadioGroup popularRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_filters);

        anyTypeRadioButton = (RadioButton) findViewById(R.id.anyTypeRadioButton);
        anyTypeRadioButton.setChecked(true);

        whatsTheMove = (Button) findViewById(R.id.whatsTheMoveButton);
        cancel = (Button) findViewById(R.id.cancelButton);
        whatsTheMove.setOnClickListener(this);
        cancel.setOnClickListener(this);
        resetFilter = (Button) findViewById(R.id.resetFilter);
        resetFilter.setOnClickListener(this);
        pRangeAnyButton = (RadioButton) findViewById(R.id.RadioButtonPriceRange);

        priceRange = (SeekBar) findViewById(R.id.seekaBarPriceRange);
        priceText = (EditText) findViewById(R.id.priceEditText);
        setSliderTextListeners(priceText, priceRange, pRangeAnyButton);

        distanceRangeAnyButton = (RadioButton) findViewById(R.id.RadioButtonDistanceRange);
        distance = (SeekBar) findViewById(R.id.seekaBarDistance);
        distanceText = (EditText) findViewById(R.id.distanceEditText);
        setSliderTextListeners(distanceText, distance, distanceRangeAnyButton);

        durationRangeAnyButton = (RadioButton) findViewById(R.id.RadioButtonDurationRange);
        duration = (SeekBar)findViewById(R.id.seekaBarDuration);
        durationText = (EditText) findViewById(R.id.durationEditText);
        setSliderTextListeners(durationText, duration, durationRangeAnyButton);

        activeRadioGroup = (RadioGroup) findViewById(R.id.activeRadioGroup);
        popularRadioGroup = (RadioGroup) findViewById(R.id.popularRadioGroup);
        anyFeatureRadioButton = (RadioButton) findViewById(R.id.anyFeatureRadioButton);
        setFeatureButtonBehavior();

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

    private void setSliderTextListeners(final EditText editText, final SeekBar seekBar, final RadioButton anyButton) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressVal = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressVal = progress;
                Log.d("EDIT_TEXT", Integer.toString(priceText.getId()));
                Log.d("EDIT_TEXT", Integer.toString(distanceText.getId()));
                Log.d("EDIT_TEXT", Integer.toString(durationText.getId()));
                Log.d("EDIT_TEXT", Integer.toString(editText.getId()));
                anyButton.setChecked(false);
                // Toast.makeText(getApplicationContext(), "Set a value between 0 to 100$", Toast.LENGTH_SHORT).show();
                editText.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//                String output = "Price Range is Set To: " + progressVal + " $";
//                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int progress = Integer.parseInt(s.toString());
                    if (0 <= progress && progress <= seekBar.getMax()) {
                        seekBar.setProgress(progress);
                        editText.setSelection(s.length());
                    } else {
                        editText.setText(Integer.toString(seekBar.getMax()));
                    }
                } catch (NumberFormatException e) {
                    // Do nothing; the text is empty
                }
            }
        });
    }

    private void setFeatureButtonBehavior() {
        anyFeatureRadioButton.setChecked(true);
        anyFeatureRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    activeRadioGroup.clearCheck();
                    popularRadioGroup.clearCheck();
                }
            }
        });
        activeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    anyFeatureRadioButton.setChecked(false);
                }
            }
        });
        popularRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    anyFeatureRadioButton.setChecked(false);
                }
            }
        });
    }
}
