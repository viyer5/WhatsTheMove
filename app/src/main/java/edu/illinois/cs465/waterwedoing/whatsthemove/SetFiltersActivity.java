package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.util.ArrayList;

public class SetFiltersActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Button whatsTheMove;
    private Button cancel;
    private Button resetFilter;
    private SeekBar price;
    private SeekBar distance;
    private SeekBar duration;
    private RadioButton priceRangeAnyButton;
    private RadioButton distanceRangeAnyButton;
    private RadioButton durationRangeAnyButton;
    private EditText priceText;
    private EditText distanceText;
    private EditText durationText;
    private RadioButton anyTypeRadioButton;
    private RadioButton anyFeatureRadioButton;
    private RadioGroup activeRadioGroup;
    private RadioGroup popularRadioGroup;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_filters);

        //anyTypeRadioButton = (RadioButton) findViewById(R.id.anyTypeRadioButton);
        //anyTypeRadioButton.setChecked(true);

        whatsTheMove = (Button) findViewById(R.id.whatsTheMoveButton);
        cancel = (Button) findViewById(R.id.cancelButton);
        whatsTheMove.setOnClickListener(this);
        cancel.setOnClickListener(this);
        resetFilter = (Button) findViewById(R.id.resetFilter);
        resetFilter.setOnClickListener(this);

        priceRangeAnyButton = (RadioButton) findViewById(R.id.RadioButtonPriceRange);
        price = (SeekBar) findViewById(R.id.seekBarPriceRange);
        priceText = (EditText) findViewById(R.id.priceEditText);
        setSliderAnyButtonListener(priceRangeAnyButton, price);
        setSliderTextListeners(priceText, price, priceRangeAnyButton);
        priceRangeAnyButton.setChecked(true);

        distanceRangeAnyButton = (RadioButton) findViewById(R.id.RadioButtonDistanceRange);
        distance = (SeekBar) findViewById(R.id.seekBarDistance);
        distanceText = (EditText) findViewById(R.id.distanceEditText);
        setSliderAnyButtonListener(distanceRangeAnyButton, distance);
        setSliderTextListeners(distanceText, distance, distanceRangeAnyButton);
        distanceRangeAnyButton.setChecked(true);

        durationRangeAnyButton = (RadioButton) findViewById(R.id.RadioButtonDurationRange);
        duration = (SeekBar)findViewById(R.id.seekBarDuration);
        durationText = (EditText) findViewById(R.id.durationEditText);
        setSliderAnyButtonListener(durationRangeAnyButton, duration);
        setSliderTextListeners(durationText, duration, durationRangeAnyButton);
        durationRangeAnyButton.setChecked(true);

        activeRadioGroup = (RadioGroup) findViewById(R.id.activeRadioGroup);
        popularRadioGroup = (RadioGroup) findViewById(R.id.popularRadioGroup);
        anyFeatureRadioButton = (RadioButton) findViewById(R.id.anyFeatureRadioButton);
        setFeatureButtonBehavior();

        spinner = (Spinner) findViewById(R.id.dropDownSpinner);
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Anything");
        categories.add("Restaurant");
        categories.add("Museum/Attraction");
        categories.add("Event/Activity");

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, categories);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(dataAdapter);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cancelButton){
            // TODO: this should just go back instead of to the landing page
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.whatsTheMoveButton){
            Intent intent = new Intent(this, ScreenSlidePagerActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.resetFilter){
            anyTypeRadioButton.setChecked(true);
            price.setProgress(50);
            priceRangeAnyButton.setChecked(true);
            distance.setProgress(15);
            distanceRangeAnyButton.setChecked(true);
            duration.setProgress(6);
            durationRangeAnyButton.setChecked(true);
            anyFeatureRadioButton.setChecked(true);
        }
    }

    private void setSliderAnyButtonListener(final RadioButton anyButton, final SeekBar seekBar) {
        anyButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // seekBar.setEnabled(!isChecked);
                if (isChecked) {
                    seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.common_google_signin_btn_text_light_disabled), PorterDuff.Mode.MULTIPLY);
                    seekBar.getThumb().setColorFilter(getResources().getColor(R.color.common_google_signin_btn_text_light_disabled), PorterDuff.Mode.MULTIPLY);
                } else {
                    seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
                    seekBar.getThumb().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                }
            }
        });
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
                } else {
                    anyFeatureRadioButton.setChecked(true);
                }
            }
        });
        popularRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId != -1) {
                    anyFeatureRadioButton.setChecked(false);
                } else {
                    anyFeatureRadioButton.setChecked(true);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
