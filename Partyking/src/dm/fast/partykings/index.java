package dm.fast.partykings;
import dm.fast.partykings.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import simple.ListView3;
import simple.ListViewnews;

public class index extends Activity {

    private Button mSet;
    Calendar mCalendar = Calendar.getInstance();
    Intent add;
    Button btn_adds;
    Button btn_edit;
    Button btn_show;
    Button btn_news;
    private Timer timer;
    private Timer timer2 = new Timer(true);;
    MediaPlayer mp;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index);
        add = new Intent();
        btn_adds=(Button)findViewById(R.id.btn_new_add);
        btn_show=(Button)findViewById(R.id.btn_edit_add);
        btn_news=(Button)findViewById(R.id.btn_news);
        add.setClass(this, sta.class);
//        mp = MediaPlayer.create(index.this, R.raw.ring);
//        Intent navigation = new Intent(Intent.ACTION_VIEW,android.net.Uri.parse("http://maps.google.com/maps?saddr=START_LON,START_LAT&daddr=END_LON,END_LAT"));
//        startActivity(navigation);

        Call();
        btn_adds.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(add);
            }
        });
        btn_show.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                        // TODO Auto-generated method stub
                        Intent intent = new Intent(index.this, ListView3.class);
                        startActivity(intent);


            }
        });
        btn_news.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                Intent intent = new Intent(index.this, ListViewnews.class);
                startActivity(intent);


            }
        });
    }

    private void Call() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(index.this,AlarmAlert.class);
                startService(intent);
            }
        };
        timer = new Timer();
        timer.schedule(task, 3 * 1000);
    }


}