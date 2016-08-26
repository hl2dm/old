package dm.newbie.myhoust;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.op);
	    Runnable runnable=new Runnable(){
	        public void run(){
				Intent intent = new Intent(MainActivity.this, dm.newbie.myhoust.indexx.class);
				MainActivity.this.startActivity(intent);
	          }

	        };
	        Handler handler=new Handler();
	        handler.postDelayed(runnable,3000);
	}


}
