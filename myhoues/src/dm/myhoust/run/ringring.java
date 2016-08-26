package dm.myhoust.run;

import java.util.Timer;
import java.util.TimerTask;
import dm.newbie.myhoust.MainActivity;
import dm.newbie.myhoust.R;
import android.hardware.Camera;
import android.media.MediaPlayer;
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
import android.widget.ImageView;

public class ringring extends Activity {
	 Timer timer = new Timer(true);
	 MediaPlayer mp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ring);       
		View v = findViewById(R.id.s);//找到你要设透明背景的layout 的id    
		v.getBackground().setAlpha(10);//透明度0~255透明度值 ，值越小越透明  
		View v2 = findViewById(R.id.ss);//找到你要设透明背景的layout 的id    
		v2.getBackground().setAlpha(10);//透明度0~255透明度值 ，值越小越透明 
		View v3 = findViewById(R.id.sss);//找到你要设透明背景的layout 的id    
		v3.getBackground().setAlpha(10);//透明度0~255透明度值 ，值越小越透明 
		 ImageView callman = (ImageView)this.findViewById(R.id.imageView1);	
		 Button btn = (Button)this.findViewById(R.id.s);
		 btn.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					 timer.cancel();
					 mp.stop(); 
					Intent intent = new Intent(ringring.this, dm.myhoust.run.takev.class);
					ringring.this.startActivity(intent);
				}
		 });
//		Bundle bData2 = this.getIntent().getExtras();
//		 Double num1 = bData2.getDouble( "pag" );
//		 if(num1>1){
			 callman.setImageResource(R.drawable.s1);
//		 }  
		 timer.schedule(new timerTask(), 500, 5000);
		 mp = MediaPlayer.create(ringring.this, R.raw.skype);
    	
	}
	  public class timerTask extends TimerTask
	  {
	    public void run()
	    {
	
	    mp.start();    
    	Vibrator myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
    	myVibrator.vibrate(3000);
	    }
	  };
	    protected void onPause() {
	         super.onPause();
			 timer.cancel();
			 mp.stop();
	     }
	    protected void onStop() {
	         super.onStop();
			 timer.cancel();
			 mp.stop();
	     }
	    protected void onDestroy() {
	         super.onDestroy();
			 timer.cancel();
			 mp.stop();
	     }
}
