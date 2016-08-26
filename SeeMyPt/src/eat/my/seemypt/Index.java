package eat.my.seemypt;
import eat.my.seemypt.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;


public class Index extends Activity 

{
	Button GO;
	int t=0;
public void onCreate(Bundle savedInstanceState)
{
	super.onCreate(savedInstanceState);
	  this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.xz);
    Runnable runnable=new Runnable(){
      public void run(){
    	  now();
        }	
      };
    Handler handler=new Handler();
   handler.postDelayed(runnable,3000);  
}
private void now() {
	Intent intent = new Intent(Index.this, eat.my.seemypt.sset.class);
	Index.this.startActivity(intent);

}
}

