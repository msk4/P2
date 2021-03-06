package com.p2prototype.b217.moodprototype;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import static com.p2prototype.b217.moodprototype.R.id.time;
import static java.util.Calendar.MINUTE;

public class MainActivity extends AppCompatActivity {

    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/dataFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File dir = new File(path);
        dir.mkdirs();
    }

    ///Save and Load for MainActivity.java:
    public static void Save(File file, String[] data)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file,true);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                    for (int i = 0; i < data.length; i++) {

                        fos.write(data[i].getBytes());
                        if (i < data.length - 1) {
                            fos.write("\n".getBytes());
                        }
                    }
                    fos.write("\n".getBytes());
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }


    public static String[] Load(File file)
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int antal=0;
        try
        {
            while ((test=br.readLine()) != null)
            {
                antal++;
            }
        }
        catch (IOException e) {e.printStackTrace();}

        try
        {
            fis.getChannel().position(0);
        }
        catch (IOException e) {e.printStackTrace();}

        String[] array = new String[antal];

        String line;
        int i = 0;
        try
        {
            while((line=br.readLine())!=null)
            {
                array[i] = line;
                i++;
            }
        }
        catch (IOException e) {e.printStackTrace();}
        return array;
    }

    public void moodButtonPressed(View view)
    {
        Intent openMoodScreen = new Intent(this,MoodLogging.class);
        startActivity(openMoodScreen);
    }
    public void InfoButtonPressed(View view){
        Intent openInfoScreen = new Intent(this,InfoActivity.class);
        startActivity(openInfoScreen);
    }

    public void SettingsButtonPressed(View view) {
        Intent openSettingScreen = new Intent(this,SettingsActivity.class);
        startActivity(openSettingScreen);
    }
    public void HistorikButtonPressed (View view){
        Intent openHistorikScreen = new Intent(this, HistorikActivity.class);
        startActivity(openHistorikScreen);
    }
}
