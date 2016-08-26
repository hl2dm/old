package dm.voice.missuvoice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by hl2dm on 2013/12/14.
 */
public class index extends Activity {

   Button Btn_START;
    Button Btn_SAMPLE;
    Button Btn_TEACH;
Intent teach_intent;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.index_main);
        setRequestedOrientation(1);
        Btn_SAMPLE=(Button)findViewById(R.id.SE_btn);
        Btn_START=(Button)findViewById(R.id.ST_btn);
        Btn_TEACH=(Button)findViewById(R.id.TH_btn);
        teach_intent = new Intent();


        Btn_TEACH.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                teach_intent.setClass(index.this, teachactivity.class);
                startActivity(teach_intent );
            }
        });

        Btn_START.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                teach_intent.setClass(index.this, ListView3.class);
                startActivity(teach_intent );
            }
        });

        Btn_SAMPLE.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                teach_intent.setClass(index.this, smapleatv.class);
                startActivity(teach_intent);
            }
        });



    }


    }



