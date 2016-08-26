package dm.book.rulethirtyfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import java.util.Timer;

/**
 * Created by hl2dm on 2013/9/26.
 */
public class set extends Activity {
    Timer timer = new Timer(true);
    Intent open;
    Intent open2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu);
        Bundle bundle0311 =this.getIntent().getExtras();
        String seach = bundle0311.getString("seach");
        ImageButton photo_btn=(ImageButton)findViewById(R.id.imageButton);
        ImageButton book_btn=(ImageButton)findViewById(R.id.imageButton2);
        open = new Intent();
        open2 = new Intent();
        open.setClass(this, dm.book.rulethirtyfour.activity.jd.class);
        open2.setClass(this, dm.book.rulethirtyfour.ehbrowser.book.class);
        Bundle bundle = new Bundle();
        bundle.putString("seach",seach);
        open.putExtras(bundle);
        open2.putExtras(bundle);
        photo_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                startActivity(open);
            }
        });
        book_btn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                startActivity(open2);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
