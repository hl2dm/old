package dm.myhoust.run;

import java.io.File;

import dm.newbie.myhoust.MainActivity;
import dm.newbie.myhoust.R;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
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
import android.widget.MediaController;
import android.widget.VideoView;

public class takev extends Activity {
	private static final String TAG = "CameraDemo";
	Camera camera;
	Preview preview;
	private VideoView vvScreen;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.infd);
//		View v = findViewById(R.id.sj);//找到你要设透明背景的layout 的id    
//		v.getBackground().setAlpha(50);//透明度0~255透明度值 ，值越小越透明 
		preview = new Preview(this);
		
		
		((FrameLayout) findViewById(R.id.preview)).addView(preview);
		vvScreen = (VideoView) findViewById(R.id.videoView1);
		String uri2 = "android.resource://" + getPackageName() + File.separator + R.raw.see3more2;  
		vvScreen.setVideoURI(Uri.parse(uri2));
		MediaController mController = new MediaController(this);
		vvScreen.setMediaController(mController);
		mController.setVisibility(View.INVISIBLE); 
		vvScreen.start();
		vvScreen.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
	    {
	     @Override
	     public void onCompletion(MediaPlayer mp)
	     {
	    	 vvScreen.start();
	     }
	    });
	}


}
