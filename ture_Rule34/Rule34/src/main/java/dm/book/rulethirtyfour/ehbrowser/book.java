package dm.book.rulethirtyfour.ehbrowser;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

import java.util.Timer;

import dm.book.rulethirtyfour.R;

/**
 * Created by hl2dm on 2013/9/26.
 */
public class book extends Activity {
    Timer timer = new Timer(true);
    Intent open;
    String seach;
    int over=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle bundle0311 =this.getIntent().getExtras();
        seach = bundle0311.getString("seach");
        setContentView(R.layout.book_layout);
        			FragmentManager DS = getFragmentManager();
			FragmentTransaction DSE = DS.beginTransaction();
			Fragment DFC = DS.findFragmentById(R.id.flbs);
        			if (DFC == null) {

				Viewpage usermune = new Viewpage(1,1,1,1,1,1,1,1,1,1,seach,10);
				DSE.add(R.id.flbs, usermune);
				DSE.addToBackStack(null);
				DSE.commit();
			}



        over=0;
    }

    @Override
    protected void onPause() {
        if(over==0){
            finish();
        }

        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
