package yello.stoper;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {
    private int seconds=0;
    private int miliseconds=0;
    private boolean running;
    private boolean wasRunning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning= savedInstanceState.getBoolean("wasRunning");

        }

        runTimer2();


    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(wasRunning){
            running = true;
        }
    }

    public void onClickStart(View view){
        running=true;
    }

    public void onClickStop(View view){
        running=false;

    }

    public void onClickReset(View view){
        running = false;
        seconds=0;
        miliseconds=0;
    }

      /* private void runTimer(){
            final TextView timer = (TextView)findViewById(R.id.time_view);
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {

                    int hours = seconds / 3600;
                    int minutes = (seconds % 3600) / 60;
                    int secs = seconds % 60;
                    String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                    timer.setText(time);


                    if (running)  {
                        seconds++;
                    }
                    handler.postDelayed(this,1000);
                }
            });
        }
        */
    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        saveInstanceState.putInt("seconds",seconds);
        saveInstanceState.putBoolean("running", running);
        saveInstanceState.putBoolean("wasRunning", wasRunning);
    }


    private void runTimer2(){
        final TextView milisecondsView =(TextView)findViewById(R.id.miliText);
        final TextView timer = (TextView)findViewById(R.id.time_view);
        final Handler handler2  =  new Handler();

        handler2.post(new Runnable() {
            @Override
            public void run() {

                String ms = String.format("%02d",miliseconds);
                milisecondsView.setText(ms);

                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timer.setText(time);

                if(running){
                    miliseconds++;
                }
                if(miliseconds==60){
                    miliseconds=0;
                    seconds++;
                }
                handler2.postDelayed(this,1);
            }
        });
    }
}
