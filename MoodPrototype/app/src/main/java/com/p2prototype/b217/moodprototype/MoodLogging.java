package com.p2prototype.b217.moodprototype;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Toast;

public class MoodLogging extends AppCompatActivity {
    SeekBar moodSlider,anxietySlider;
    NumberPicker hoursSleptNp,minutesSleptNp,weightKg,weightComma;
    EditText notes;
    int mood;
    int anxiety;
    int sleepMinutes;
    int sleepHours;
    float weight;
    String note;
    Event[] eventList = new Event[0];
    Medicine[] medicineList = new Medicine[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_logging);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        moodSlider=(SeekBar)findViewById(R.id.mood_slider);
        anxietySlider=(SeekBar)findViewById(R.id.anxiety_slider);
        moodSlider.setOnSeekBarChangeListener(moodAnxietyListener);
        anxietySlider.setOnSeekBarChangeListener(moodAnxietyListener);
        notes=(EditText)findViewById(R.id.notes_edit);
        notes.setOnFocusChangeListener(keyboardAwayListener);
        hoursSleptNp= (NumberPicker)findViewById(R.id.sleep_picker_1);
        minutesSleptNp=(NumberPicker)findViewById(R.id.sleep_picker_2);
        weightKg= (NumberPicker)findViewById(R.id.weight_picker_1);
        weightComma=(NumberPicker)findViewById(R.id.weight_picker_2);
        hoursSleptNp.setMaxValue(23);
        hoursSleptNp.setMinValue(0);
        minutesSleptNp.setMinValue(0);
        minutesSleptNp.setMaxValue(59);
        weightKg.setMinValue(0);
        weightKg.setMaxValue(200);
        weightComma.setMaxValue(9);
        weightComma.setMinValue(0);
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    private View.OnFocusChangeListener keyboardAwayListener = new View.OnFocusChangeListener(){
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        }
    };
    //Placeholder below (for testing purposes I guess). Get values onClick for Register button in the final version.
    private SeekBar.OnSeekBarChangeListener moodAnxietyListener=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            switch (seekBar.getId()){
                case R.id.mood_slider:
                    mood = moodSlider.getProgress();
                    Toast.makeText(MoodLogging.this,Integer.toString(mood),Toast.LENGTH_SHORT).show();
                    break;
                case R.id.anxiety_slider:
                    anxiety = anxietySlider.getProgress();
                    Toast.makeText(MoodLogging.this,Integer.toString(anxiety),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}