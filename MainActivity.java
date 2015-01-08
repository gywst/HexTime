package co.cybrd.hextime;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends Activity {

    private static final String LOGTAG = "MainActivity";

    Date currentTime;

    // ui
    TextView digital_clock;
    TextView hex_textview;

    SimpleDateFormat hours_sdf = new SimpleDateFormat("HH");
    SimpleDateFormat mins_sdf = new SimpleDateFormat("mm");
    SimpleDateFormat secs_sdf = new SimpleDateFormat("ss");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTime = Calendar.getInstance().getTime();
        digital_clock = (TextView) findViewById(R.id.tv_time);
        hex_textview = (TextView) findViewById(R.id.tv_hex);

        Thread thread = new Thread() {

            @Override
            public void run() {
                super.run();
                try {
                    while (!interrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateTime();
                            }
                        });
                    }
                } catch (InterruptedException ex) {

                }
            }
        };

        thread.start();
    }

    private void updateTime() {
        currentTime = Calendar.getInstance().getTime();
        String hours = hours_sdf.format(currentTime.getTime());
        String minutes = mins_sdf.format(currentTime.getTime());
        String seconds = secs_sdf.format(currentTime.getTime());

        String formated_time = hours + " : " + minutes + " : " + seconds;
        // Log.i(LOGTAG, "Time: " + formated_time);

        digital_clock.setText(formated_time);
        digital_clock.setTextColor(Color.WHITE);

        String hex_time = "#" + hours + minutes + seconds;
       // Log.i(LOGTAG, "Hex colour: " + hex_time);

        hex_textview.setText(hex_time);
        hex_textview.setTextColor(Color.WHITE);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout_main);

        // layout.setBackgroundColor(Color.parseColor("#ffffff"));
        layout.setBackgroundColor(Color.parseColor(hex_time));
    }


}
