package dm.fast.partykings;
import dm.fast.partykings.R;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import browseData_blob.DBOpenHelper;
import browseData_blob.Site;
import simple.gpstake;

public class CallAlarm extends Activity {

    private Button mSet;
    Calendar mCalendar = Calendar.getInstance();
    AnimationDrawable animDrawable;
    private VideoView vvScreen;
    String uri;
    String uri2;
    int i=0;
    private LocationManager lms;
    private String bestProvider = LocationManager.GPS_PROVIDER;
    private boolean getGPSService;
    private static final int SOUND_COUNT = 2;
    private int sneezeId;
    private int sneezeId2;
    private SoundPool soundPool;
    private Timer timer2 = new Timer(true);;
    int num;
    String sw;
    MediaPlayer mp;
    int Day[]=new int[200];
    int Mon[]=new int[200];
    int Min[]=new int[200];
    int Hos[]=new int[200];
    private ArrayList<Site> sites;
    private DBOpenHelper dbHelper;
    private List<Map<String, Object>> mData;
    String a;
    String ID[]=new  String[200];
    String Where[]=new  String[200];
    String Page[]=new  String[200];
    String vale[]= new String[200];
    String title[]= new String[200];
    String all;
    int x;
    int check;
    TextView tv_all;
    ImageView IV_show;
    String title_info;
    String info;
    String info_time;
    String info_where;
    String MON;
    String DAY;
    String latLongStringx;
    String latLongStringy;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sas);
        title_info=getResources().getString(R.string.str_title_info);
        info=getResources().getString(R.string.str_info);
        info_time=getResources().getString(R.string.str_time_info);
        info_where=getResources().getString(R.string.str_where_info);
        DAY=getResources().getString(R.string.str_DAY);
        MON=getResources().getString(R.string.str_MON);
        sw=getResources().getString(R.string.str_sw);
        Button stop=(Button)findViewById(R.id.btn_stop);
        Button maps=(Button)findViewById(R.id.btn_Navigation);
        mp = MediaPlayer.create(CallAlarm.this, R.raw.ring);

        timer2.schedule(new timerTask(), 500, 500);
        mp.start();
        tv_all=(TextView)findViewById(R.id.tv_all);
        IV_show=(ImageView)findViewById(R.id.IV);
        Bundle bundle = getIntent().getExtras();
        if (dbHelper == null) {
            dbHelper = new DBOpenHelper(this);
        }

        Location s=locationServiceInitial();
        double lat = s.getLatitude();
        double lng = s.getLongitude();
        latLongStringx=lat+"";
        latLongStringy=lng+"";
        sites = dbHelper.getAllSites();
        check=sites.size();
        num = bundle.getInt("num");
        num=num+1;
        int a=0;
        int t=sites.size();
        for(int i=1;i<=check;i++){
            Site site = sites.get(a);
            Day[i] = site.getDay();
            Mon[i] = site.getMon();
            vale[i] = site.getJob();
            ID[i]=site.getId();
            title[i] = site.getTitle();
            Hos[i] = site.getHos();
            Min[i]=site.getMin();
            Where[i]=site.getWhere();
            Page[i]=site.getPage();
            a=a+1;
        }

        Bitmap imageBitmap = BitmapFactory.decodeFile(Page[num]);
        IV_show.setImageBitmap(imageBitmap);
        all=  title_info+title[num]+"\n"+
                info+vale[num]+"\n"+
                info_time+Mon[num]+MON+Day[num]+ DAY+"\n"+
                Hos[num]+":"+Min[num]+"\n"+
                info_where+Where[num]+"\n";
        tv_all.setText(all);
        stop.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                timer2.cancel();
                finish();
            }
        });
        maps.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
          Intent navigation = new Intent(Intent.ACTION_VIEW,android.net.Uri.parse("http://maps.google.com/maps?saddr="+latLongStringx+","+latLongStringy+"&daddr="+Where[num]));
          startActivity(navigation);
                timer2.cancel();
            }
        });
//        this.soundPool = new SoundPool(SOUND_COUNT, AudioManager.STREAM_MUSIC,
//                0);
//        this.sneezeId = this.soundPool.load(this, R.raw.ring, 1);
//        this.soundPool.play(this.sneezeId, 1, 1, 0, 0, 1);
    }
    public Location locationServiceInitial() {
        LocationManager status = (LocationManager) (this.getSystemService(Context.LOCATION_SERVICE));

        if (status.isProviderEnabled(LocationManager.GPS_PROVIDER) || status.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            getGPSService=true;
            lms = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            bestProvider = lms.getBestProvider(criteria, true);
            Location location = lms.getLastKnownLocation(bestProvider);

            if(location != null){
                return location;
            }else{
                Toast.makeText(this, "x", Toast.LENGTH_LONG).show();
                return null;
            }
        } else {
            Toast.makeText(this, "o", Toast.LENGTH_LONG).show();

            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            return null;
        }
    }
    public class timerTask extends TimerTask
    {
        public void run()
        {

            mp.start();
            Vibrator myVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);
            myVibrator.vibrate(500);
        }
    };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        finish();
        timer2.cancel();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
        timer2.cancel();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        finish();
        timer2.cancel();
    }

}