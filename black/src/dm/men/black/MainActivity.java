package dm.men.black;
import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends Activity {
	AnimationDrawable animDrawable;
	private VideoView vvScreen;
	String uri;
	String uri2;
	int i=0;
	 private static final int SOUND_COUNT = 2;
	    private int sneezeId;
	    private int sneezeId2;
	    private SoundPool soundPool;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.soundPool = new SoundPool(SOUND_COUNT, AudioManager.STREAM_MUSIC,
	                0);
		 this.sneezeId = this.soundPool.load(this, R.raw.ok, 1);
		 this.sneezeId2 = this.soundPool.load(this, R.raw.st, 1);
//		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
//		imageView.setBackgroundResource(R.drawable.imageset);
		vvScreen = (VideoView) findViewById(R.id.vvScreen);
//		animDrawable = (AnimationDrawable) imageView.getBackground();
		playing();
		
	}

	private void playing() {
		uri = "android.resource://" + getPackageName() + File.separator + R.raw.v_1;  
		uri2 = "android.resource://" + getPackageName() + File.separator + R.raw.v_2;  
		vvScreen.setVideoURI(Uri.parse(uri));
//		vvScreen.setVideoPath(path);
		MediaController mController = new MediaController(this);
		vvScreen.setMediaController(mController);
//		vvScreen.requestFocus();


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

        

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			Toast.makeText(getApplicationContext(), "",
//				     Toast.LENGTH_SHORT).show();
			  this.soundPool.play(this.sneezeId2, 1, 1, 0, 0, 1);
			vvScreen.setVideoPath(uri2);
//			vvScreen.setVideoURI(Uri.parse(uri2));
			return true;
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
//			Toast.makeText(getApplicationContext(), "",
//				     Toast.LENGTH_SHORT).show();
//			vvScreen.setVideoURI(Uri.parse(uri));
			  this.soundPool.play(this.sneezeId, 1, 1, 0, 0, 1);
			vvScreen.setVideoPath(uri);
			return true;
		}
		return super.onTouchEvent(event);
	}
}