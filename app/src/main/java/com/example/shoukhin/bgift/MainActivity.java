package com.example.shoukhin.bgift;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.asp.fliptimerviewlibrary.CountDownClock;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Calendar currentTime;
    private final Calendar birthDay = Constants.getTargetCalendar();;
    private CountDownClock timerProgramCountdown;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        setAlarm();
        initCountDown();
        //CheckTime();

        TextView openbtn = findViewById(R.id.openbtn);
        openbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Surprise.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // set the new task and clear flags
                startActivity(i);

            }
        });
    }

    private void initCountDown() {
        timerProgramCountdown.startCountDown(birthDay.getTimeInMillis() - currentTime.getTimeInMillis());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setAlarm() {
        currentTime = Calendar.getInstance();

        if (birthDay.getTimeInMillis() > currentTime.getTimeInMillis()) {

            Intent intent = new Intent(this, Alarm.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                    intent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, birthDay.getTimeInMillis(), pendingIntent);
            }
        }
    }

    private void CheckTime() {
        if (targetTimeCame()) {
            //surprise starts
            Intent i = new Intent(this, Surprise.class);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // set the new task and clear flags
            startActivity(i);
        }
    }

    private boolean targetTimeCame() {
        return currentTime.get(Calendar.MONTH) == birthDay.get(Calendar.MONTH)
                && currentTime.get(Calendar.DAY_OF_MONTH)
                == birthDay.get(Calendar.DAY_OF_MONTH) && currentTime.get(Calendar.HOUR_OF_DAY) >= birthDay.get(Calendar.HOUR_OF_DAY);
                //&& currentTime.get(Calendar.MINUTE) >= birthDay.get(Calendar.MINUTE) && currentTime.get(Calendar.SECOND) >= birthDay.get(Calendar.SECOND);
    }

    private void initialize() {
        currentTime = Calendar.getInstance();

        String text = "" + getResources().getString(R.string.receiver_name) + ",\n\n" + getResources().getString(R.string.letter_text);
        TextView textView = findViewById(R.id.text);
        textView.setText(text);

        timerProgramCountdown = findViewById(R.id.timerProgramCountdown);
    }

}
