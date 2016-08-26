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

public class indexx extends Activity {
int num;
private int sec;
private EditText etTimer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		etTimer = (EditText) findViewById(R.id.editText1);
		Button boy=(Button)this.findViewById(R.id.button1);
		boy.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				num=2;
dr();
			}

			private void dr() {

				sec = Integer.parseInt(etTimer.getText().toString());
				Bundle bundle = new Bundle();
				// 將使用者輸入的秒數儲存到Bundle物件
				bundle.putInt("sec", sec);
				Intent intent = new Intent(indexx.this, dm.myhoust.run.TimerService.class);
				intent.putExtras(bundle);
				// 呼叫此方法以啟動指定的Service
				startService(intent);
				
			}
		});
	}
	public void onDestroy() {
		super.onDestroy();
		// 解除BroadcastReceiver的註冊
	}

}
