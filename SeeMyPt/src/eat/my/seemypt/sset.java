package eat.my.seemypt;
import eat.my.seemypt.R;

import eat.my.seemypt.MainActivity.sendPostRunnable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;


public class sset extends Activity 

{
	ImageButton CHD;
	ImageButton CCF;
	int t=0;
	String set;
public void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.set);
	  CHD=(ImageButton)findViewById(R.id.imageButton1);
	  CCF=(ImageButton)findViewById(R.id.imageButton2);
	  CHD.setOnClickListener(ibLis);
	  CCF.setOnClickListener(ibLis2);
	
}
OnClickListener ibLis=new OnClickListener(){
	   
			@Override
			public void onClick(View v) {

				now();
	 
			}
	 
	    };
	    OnClickListener ibLis2=new OnClickListener(){
	 	   
			@Override
			public void onClick(View v) {

				saw();
	 
			}
	 
	    };
private void now() {

	Intent intent = new Intent(sset.this, eat.my.seemypt.MainActivity.class);
	sset.this.startActivity(intent);

}

private void saw() {

	Intent intent = new Intent(sset.this, eat.my.seemypt.CCF.class);
	sset.this.startActivity(intent);

}
}

