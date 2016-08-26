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

public class wait extends Activity {
	 Double num2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.infd);
		Bundle bData = this.getIntent().getExtras();
		 Double num1 = bData.getDouble( "secc" );
		 num2 = bData.getDouble( "pag" );

	    Runnable runnable=new Runnable(){
	        public void run(){
				Intent intent = new Intent(wait.this, dm.myhoust.run.ringring.class);
				Bundle bData2 = new Bundle();
				bData2.putDouble( "pag",num2);
				intent.putExtras(bData2);
				wait.this.startActivity(intent);
	          }	
	        };
	        Handler handler=new Handler();
	        handler.postDelayed(runnable,(long) (num1*1000));
	}


}
