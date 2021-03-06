package com.p2prototype.b217.moodprototype;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

public class HistorikActivity extends AppCompatActivity {
ArrayList<RelativeLayout> visual= new ArrayList<>(0);
    ArrayList<LinearLayout> lines=new ArrayList<>(0);
    ArrayList<VisualObject> visualObjects=new ArrayList<>(0);
    LayoutInflater inflateVisual;
    LinearLayout container;
    HorizontalScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historik);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        container = (LinearLayout) findViewById(R.id.visual_container);
        inflateVisual=(LayoutInflater) HistorikActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertTXT();
        for (int i=0; i<visualObjects.size();i++){
            visual.add((RelativeLayout)inflateVisual.inflate(R.layout.visualisation,null,true));
        }
        drawVisual();
        scrollView=(HorizontalScrollView) findViewById(R.id.scroll_visual);
        scrollView.postDelayed(new Runnable() {

            @Override
            public void run() {
                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 10);
    }
    @Override
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
    private void convertTXT(){
        try{
        int moods;
        int anxieties;
        int sleepHour;
        int sleepMinute;
        int weightKs;
        int weightGs;
        String notes;
        String todays;

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataFile.txt", "dataFile.txt");
        String[] entrie = MainActivity.Load(file);
        int k = 0;
        while (k < entrie.length) {
            moods = Integer.parseInt(entrie[0 + k]);
            anxieties = Integer.parseInt(entrie[1 + k]);
            sleepHour = Integer.parseInt(entrie[2 + k]);
            sleepMinute = Integer.parseInt(entrie[3 + k]);
            weightKs = Integer.parseInt(entrie[4 + k]);
            weightGs = Integer.parseInt(entrie[5 + k]);
            notes = entrie[6 + k];
            todays = entrie[7 + k];
            k += 8;
            visualObjects.add(new VisualObject(moods, anxieties, sleepHour, sleepMinute, weightKs, weightGs, notes, todays));
        }} catch(Exception e){
            Toast.makeText(this, "Dine data er blevet hentet", Toast.LENGTH_SHORT).show();
            }



        for (int i=0;i<20;i++){
            List<String> lines = new ArrayList<>();
            try {

            } catch(Exception e){

            }
            Random r=new Random();
            //visualObjects.add(new VisualObject(moods,r.nextInt(101),r.nextInt(24),r.nextInt(60),r.nextInt(200),r.nextInt(10),"bullshit","03/05"));
        }
    }
    private void drawVisual(){
        int j=0;
        for (int i=0;i<visual.size();i++){
            setText(i);
            int mood =visualObjects.get(i).getMood();
            setColours(mood,i);
            if (visualObjects.get(i).getNoteBoolean()){
                ImageView  view =(ImageView)visual.get(i).findViewById(R.id.icon_3);
                view.setImageResource(R.drawable.ic_note);
            }
            setNotesOnClick(i);
            int anx =Double.valueOf((100-visualObjects.get(i).getAnxiety())*2.55).intValue();
            visual.get(i).findViewById(R.id.anxiety_visual).setBackgroundColor(Color.rgb(anx,anx,anx));
            container.addView(visual.get(i));
            if (i!=visual.size()-1&& !visualObjects.get(i).getDate().equals(visualObjects.get(i+1).getDate())){
                lines.add((LinearLayout)inflateVisual.inflate(R.layout.line,null,true));
                container.addView(lines.get(j));
                j++;
            }
        }

    }
    private void setText(int index){
        TextView date= (TextView)visual.get(index).findViewById(R.id.date_text);
        date.setText(visualObjects.get(index).getDate());
        TextView sleep=(TextView)visual.get(index).findViewById(R.id.sleep_text);
        sleep.setText(visualObjects.get(index).getSleep());
        TextView weight=(TextView)visual.get(index).findViewById(R.id.weight_visual);
        weight.setText(visualObjects.get(index).getWeight());
        if (visualObjects.get(index).getMood()<=5){
            date.setTextColor(Color.WHITE);
            sleep.setTextColor(Color.WHITE);
            weight.setTextColor(Color.WHITE);
        } else{
            date.setTextColor(Color.BLACK);
            sleep.setTextColor(Color.BLACK);
            weight.setTextColor(Color.BLACK);
        }
    }
    private void setColours(int mood, int i){
        if(mood>=95){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(255,0,0));
        }else if(mood<95&&mood>=85){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(255,46,1));
        }else if(mood<85&&mood>=75){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(255,97,1));
        }else if(mood<75&&mood>=65){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(255,146,1));
        }else if(mood<65&&mood>=55){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(255,186,1));
        }else if(mood<55&&mood>=45){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(255,255,0));
        }else if(mood<45&&mood>=35){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(1,210,255));
        }else if(mood<35&&mood>=25){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(1,165,255));
        }else if(mood<25&&mood>=15){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(1,123,255));
        }else if(mood<15&&mood>5){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(1,83,255));
        }else if(mood<=5){
            visual.get(i).findViewById(R.id.visual_background).setBackgroundColor(Color.rgb(0,0,255));
        }
    }
    private void setNotesOnClick(final int index){
        if(visualObjects.get(index).getNoteBoolean()){
            visual.get(index).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(HistorikActivity.this)
                            .setTitle("Noter for dagen")
                            .setMessage(visualObjects.get(index).getNote())
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(R.drawable.ic_note)
                            .show();
                }
            });
		}
    }
}
