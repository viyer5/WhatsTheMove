package edu.illinois.cs465.waterwedoing.whatsthemove;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetFiltersActivity extends Activity implements View.OnClickListener {
    private Button whatsTheMove;
    private Button cancel;
    private Button resetFilter;

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
