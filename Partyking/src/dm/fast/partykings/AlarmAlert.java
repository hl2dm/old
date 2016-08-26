package dm.fast.partykings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import browseData_blob.DBOpenHelper;
import browseData_blob.Site;

public class AlarmAlert extends Service {
    private final static int NOTIFICATION_ID = 0;
    public final static String TIMER_ACTION = "AlarmAlert";
    private Timer timers;
    private int sec;
    private NotificationManager ntfMgr;
    private ArrayList<Site> sites;
    private DBOpenHelper dbHelper;

    int Day[]=new int[200];
    int Mon[]=new int[200];
    int Min[]=new int[200];
    int Hos[]=new int[200];
    int Day_true;
    int Hos_true;
    int Min_true;
    int Mon_true;
    int check;
    Calendar mCalendar;
    private Timer timer;
    Timer times=new Timer(true);
    String vale[]= new String[200];
    @Override

    public void onCreate() {
        super.onCreate();
    }

    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        if (dbHelper == null) {
            dbHelper = new DBOpenHelper(this);
        }

        times.schedule(new timerTask(),1000, 50000);
//        for(int r=0;r<1;r--){
//            mCalendar = Calendar.getInstance();
//            Mon_true = mCalendar.get(Calendar.MONTH);
//            Mon_true=Mon_true+1;
//            Min_true = mCalendar.get(Calendar.MINUTE);
//            Hos_true = mCalendar.get(Calendar.HOUR_OF_DAY);
//            Day_true = mCalendar.get(Calendar.DAY_OF_MONTH);
//           if(Min[1]==Min_true){
//               if(Hos[1]==Hos_true){
//                   if(Mon[1]==Mon_true){
//                       if(Day[1]==Day_true){
////            if(Day[1]==Day_true&&Hos[1]==Hos_true&&Mon[1]==Mon_true&&Min[1]==Min_true){
//        Intent i = new Intent();
//        i.setClass( AlarmAlert.this, CallAlarm.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(i);
//        sendBroadcast(new Intent(TIMER_ACTION));
//                r=2;
//            }
//                       }
//                   }
//               }
//            }


//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                Intent i = new Intent();
//                i.setClass( AlarmAlert.this, CallAlarm.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//                sendBroadcast(new Intent(TIMER_ACTION));
//            }
//        };
//        timer = new Timer();
//        timer.schedule(task, 3 * 1000);

        ntfMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        showNotification();

        return START_STICKY;
    }
    public class timerTask extends TimerTask
    {
        public void run()
        {     sites = dbHelper.getAllSites();
            check=sites.size();
            for(int i=0;i<check;i++){
                Site site = sites.get(i);
                Day[i] = site.getDay();
                Mon[i] = site.getMon();
                Hos[i] = site.getMin();
                Min[i] = site.getHos();
            }
            mCalendar = Calendar.getInstance();
            Mon_true = mCalendar.get(Calendar.MONTH);
            Mon_true=Mon_true+1;
            Min_true = mCalendar.get(Calendar.MINUTE);
            Hos_true = mCalendar.get(Calendar.HOUR_OF_DAY);
            Day_true = mCalendar.get(Calendar.DAY_OF_MONTH);
            for(int i=0;i<check;i++){
            if(Min[i]==Min_true){
                if(Hos[i]==Hos_true){
                    if(Mon[i]==Mon_true){
                        if(Day[i]==Day_true){
//            if(Day[1]==Day_true&&Hos[1]==Hos_true&&Mon[1]==Mon_true&&Min[1]==Min_true){
                            Bundle bundle = new Bundle();
                            Intent j = new Intent();
                            j.setClass( AlarmAlert.this, CallAlarm.class);
                            bundle.putInt("num", i);
                            j.putExtras(bundle);
                            j.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(j);
                            sendBroadcast(new Intent(TIMER_ACTION));
                        }
                        }
                    }
                }
            }
        }
    };
    private void showNotification() {
        Intent intent = new Intent(this, CallAlarm.class);
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

    public void onDestroy() {
        super.onDestroy();

        timer.cancel();

        ntfMgr.cancelAll();

    }

    @Override

    public IBinder onBind(Intent intent) {
        return null;
    }
}
