package com.p2prototype.b217.moodprototype;import android.app.Activity;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.text.InputFilter;import android.text.Spanned;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.view.inputmethod.InputMethodManager;import android.widget.EditText;import android.widget.NumberPicker;import android.widget.SeekBar;import android.widget.Toast;import java.io.File;import java.io.IOException;import java.text.DateFormat;import java.util.Calendar;import java.util.Date;import java.text.SimpleDateFormat;import java.util.ArrayList;public class MoodLogging extends AppCompatActivity {    SeekBar moodSlider,anxietySlider;    NumberPicker hoursSleptNp,minutesSleptNp,weightKg,weightComma;    EditText notes;    int mood;    int anxiety;    int sleepMinutes;    int sleepHours;    int weightK;    int weightG;    String note;    ArrayList<Medicine> medicineList = new ArrayList<Medicine>();    ArrayList<Event> eventList = new ArrayList<Event>();    ArrayList<VisualObject> visualList = new ArrayList<VisualObject>();    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_mood_logging);        getSupportActionBar().setDisplayHomeAsUpEnabled(true);        moodSlider=(SeekBar)findViewById(R.id.mood_slider);        anxietySlider=(SeekBar)findViewById(R.id.anxiety_slider);        moodSlider.setOnSeekBarChangeListener(moodAnxietyListener);        anxietySlider.setOnSeekBarChangeListener(moodAnxietyListener);        notes=(EditText)findViewById(R.id.notes_edit);        notes.setOnFocusChangeListener(keyboardAwayListener);        hoursSleptNp= (NumberPicker)findViewById(R.id.sleep_picker_1);        minutesSleptNp=(NumberPicker)findViewById(R.id.sleep_picker_2);        weightKg= (NumberPicker)findViewById(R.id.weight_picker_1);        weightComma=(NumberPicker)findViewById(R.id.weight_picker_2);        hoursSleptNp.setMaxValue(23);        hoursSleptNp.setMinValue(0);        minutesSleptNp.setMinValue(0);        minutesSleptNp.setMaxValue(59);        weightKg.setMinValue(0);        weightKg.setMaxValue(200);        weightComma.setMaxValue(9);        weightComma.setMinValue(0);        InputFilter filter = new InputFilter() {            public CharSequence filter(CharSequence source, int start, int end,                                       Spanned dest, int dstart, int dend) {                for (int i = start; i < end; i++) {                    if (source.charAt(i)=='\n') { // Accept only letter & digits ; otherwise just return                        Toast.makeText(MoodLogging.this,"Invalid Input",Toast.LENGTH_SHORT).show();                        return "";                    }                }                return null;            }        };        notes.setFilters(new InputFilter[] { filter });    }    public void hideKeyboard(View view) {        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);    }    public boolean onOptionsItemSelected(MenuItem item) {        switch (item.getItemId()) {            case android.R.id.home:                finish();                return true;        }        return super.onOptionsItemSelected(item);    }    public boolean onCreateOptionsMenu(Menu menu) {        return true;    }    private View.OnFocusChangeListener keyboardAwayListener = new View.OnFocusChangeListener(){        @Override        public void onFocusChange(View v, boolean hasFocus) {            if (!hasFocus) {                hideKeyboard(v);            }        }    };    //Placeholder below (for testing purposes I guess). Get values onClick for Register button in the final version.    private SeekBar.OnSeekBarChangeListener moodAnxietyListener=new SeekBar.OnSeekBarChangeListener() {        @Override        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {        }        @Override        public void onStartTrackingTouch(SeekBar seekBar) {        }        @Override        public void onStopTrackingTouch(SeekBar seekBar) {            switch (seekBar.getId()){                case R.id.mood_slider:                    mood = moodSlider.getProgress();                    //Toast.makeText(MoodLogging.this,Integer.toString(mood),Toast.LENGTH_SHORT).show();                    break;                case R.id.anxiety_slider:                    anxiety = anxietySlider.getProgress();                    //Toast.makeText(MoodLogging.this,Integer.toString(anxiety),Toast.LENGTH_SHORT).show();                    break;            }        }    };    public void SaveData(View view) throws IOException {        mood = moodSlider.getProgress();        anxiety = anxietySlider.getProgress();        sleepHours = hoursSleptNp.getValue();        sleepMinutes = minutesSleptNp.getValue();        weightK = weightKg.getValue();        weightG = weightComma.getValue();        note = notes.getText().toString();        //making a date        Date date = Calendar.getInstance().getTime();        DateFormat formatter = new SimpleDateFormat("dd-MM");        String today = formatter.format(date);        //makes a file            File file = new File(MainActivity.path, "dataFile.txt");        //Puts all data into a String Array            String[] data = {Integer.toString(mood), Integer.toString(anxiety), Integer.toString(sleepHours), Integer.toString(sleepMinutes), Integer.toString(weightK), Integer.toString(weightG), note, today};            MainActivity.Save(file,data);            Toast.makeText(this, "Dine registrering er gemt", Toast.LENGTH_SHORT).show();        finish();    }}