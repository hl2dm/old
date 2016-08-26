package dm.book.rulethirtyfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    Timer timer = new Timer(true);
    Intent open;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        open = new Intent();
        open.setClass(this, seach.class);
        timer.schedule(new timerTask(), 3000, 3000);
    }
    public class timerTask extends TimerTask
    {
        public void run()
        {
            startActivity(open);

            timer.cancel();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
