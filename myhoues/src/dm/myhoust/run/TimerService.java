package dm.myhoust.run;

import java.util.Timer;
import java.util.TimerTask;

import dm.newbie.myhoust.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class TimerService extends Service {
	private final static int NOTIFICATION_ID = 0;
	public final static String TIMER_ACTION = "org.serviceEx.TimerService";
	private Timer timer;
	private int sec;
	private NotificationManager ntfMgr;

	@Override
	// 第一次啟動Service時會呼叫onCreate()
	public void onCreate() {
		super.onCreate();
	}

	@Override
	// 以startService()方式啟動會呼叫onStartCommand()
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		// 取得之前使用者輸入的秒數
		Bundle bundle = intent.getExtras();
		sec = bundle.getInt("sec");

		// 建立Timer，並將使用者輸入的秒數設定成要延遲啟動的時間；
		// 時間一到便會執行TimerTask的run()內容— 傳送指定的Broadcast
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Intent i = new Intent();
				i.setClass( TimerService.this, dm.myhoust.run.ringring.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);  
				sendBroadcast(new Intent(TIMER_ACTION));
			}
		};
		timer = new Timer();
		timer.schedule(task, sec * 1000);

		// 建立NotificationManager並呼叫showNotification()發送通知訊息
		ntfMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		showNotification();

		// 回傳START_STICKY可以保證再次建立新的Service時仍會呼叫onStartCommand()
		return START_STICKY;
	}

	// 詳細說明可參看NotificationEx範例
	private void showNotification() {
		Intent intent = new Intent(this, dm.newbie.myhoust.indexx.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, 0);
//		Notification notification = new Notification.Builder(this)
//				.setTicker(sec + getString(R.string.msg_playAfterSec))
//				.setContentTitle(getString(R.string.msg_preparePlay))
//				.setContentText(getString(R.string.msg_speakerOn))
//				.setSmallIcon(android.R.drawable.ic_menu_info_details)
//				.setAutoCancel(true).setContentIntent(pendingIntent)
//				.getNotification();
//		ntfMgr.notify(NOTIFICATION_ID, notification);
	}

	@Override
	// Service準備結束時會呼叫此方法
	public void onDestroy() {
		super.onDestroy();
		// 停止Timer與其指定的工作排程
		timer.cancel();
		
		// 取消之前在狀態列上顯示的訊息
		ntfMgr.cancelAll();
		
		Toast.makeText(this, R.string.msg_serviceOver, Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	// onBind()將於ServiceBindEx範例說明
	public IBinder onBind(Intent intent) {
		return null;
	}
}
