package dm.myhoust.run;

import dm.newbie.myhoust.MainActivity;
import dm.newbie.myhoust.R;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class ub extends Activity {
	private static final String TAG = "CameraDemo";
	Camera camera;
	Preview preview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infd);
		preview = new Preview(this);
		((FrameLayout) findViewById(R.id.preview)).addView(preview);
		Bundle bData = this.getIntent().getExtras();
		 Double num1 = bData.getDouble( "secc" );
	    Runnable runnable=new Runnable(){
	        public void run(){
	        	Vibrator myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
	        	myVibrator.vibrate(3000);
//	        	setContentView(R.layout.call);
	          }	
	        };
	        Handler handler=new Handler();
	        handler.postDelayed(runnable,(long) (num1*1000));
	}


}
