package dm.voice.missuvoice;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import browseData_blob.DBOpenHelper;
import browseData_blob.Site;

/**
 * Created by hl2dm on 2013/12/12.
 */
public class teachactivity2 extends Activity {
    Button teach_reco_Start;
    Button teach_reco_Stop;
    Button teach_reco_Play;
    private MediaRecorder mediaRecorder = null;
    TextView timer;
    Button next_btn;
    Button back_btn;
    Intent intent;
    private SoundPool soundPool;
    String as;
    int s;
    private boolean startflag=false;
    private int tsec=0,csec=0,cmin=0;
    Intent ta3;
    private DBOpenHelper dbHelper;
    private ArrayList<Site> sites;
    String folodname;
    int talk1=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.teach_adlog2);
        setRequestedOrientation(1);
       showSites();
        ta3= new Intent();

       teach_reco_Start = (Button) findViewById(R.id.reco_start_btn);
       teach_reco_Stop = (Button) findViewById(R.id.reco_stop_btn);
        teach_reco_Play= (Button) findViewById(R.id.reco_play_btn);
        next_btn=(Button)findViewById(R.id.teach2_next_btn);
        back_btn=(Button)findViewById(R.id.teach2_back_btn);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
        timer=(TextView)findViewById(R.id.tv_timer);
        teach_reco_Stop.setEnabled(false);
        teach_reco_Start.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                tsec = 0;
                csec = 0;
                cmin = 0;
                teach_reco_Start.setEnabled(false);
                teach_reco_Stop.setEnabled(true);
                timer.setText("00:00");
                startflag=true;
                String fileName = "hello.amr";
                try {
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    File myDataPath = new File( SDCardpath.getAbsolutePath() + "/"+folodname );
                    if( !myDataPath.exists() ) {
                        myDataPath.mkdirs();
                    }
                    File recodeFile = new File(SDCardpath.getAbsolutePath() + "/"+folodname+"/"+fileName);

                    mediaRecorder = new MediaRecorder();

                    //設定音源
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    //設定輸出檔案的格式
                    mediaRecorder
                            .setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
                    //設定編碼格式
                    mediaRecorder
                            .setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    //設定錄音檔位置
                    mediaRecorder
                            .setOutputFile(recodeFile.getAbsolutePath());

                    mediaRecorder.prepare();

                    //開始錄音
                    mediaRecorder.start();



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        teach_reco_Stop.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                talk1=1;
                teach_reco_Start.setEnabled(true);
                teach_reco_Stop.setEnabled(false);
                if(mediaRecorder != null) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    String fileName = "hello.amr";
                    as=SDCardpath.getAbsolutePath() +"/"+folodname+"/"+fileName;
                    s = soundPool.load(as, 0);
                }
                startflag=false;
            }

        });

        next_btn.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                if(talk1!=1){
                    Toast.makeText(teachactivity2.this, "此項步驟你還沒完成喔", Toast.LENGTH_SHORT).show();
                }else {
                ta3.setClass(teachactivity2.this, teachactivity3.class);
                startActivity(ta3);
                finish();
                }
            }

        });
        back_btn.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                ta3.setClass(teachactivity2.this, teachactivity.class);
                startActivity(ta3);
                finish();
            }

        });
        take();
    }
    private TimerTask task = new TimerTask(){

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (startflag){
                //如果startflag是true則每秒tsec+1
                tsec++;
                Message message = new Message();

                //傳送訊息1
                message.what =1;
                handler.sendMessage(message);
            }
        }

    };
    //TimerTask無法直接改變元件因此要透過Handler來當橋樑
    private Handler handler = new Handler(){
        public  void  handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    csec=tsec%60;
                    cmin=tsec/60;
                    String s="";
                    if(cmin <10){
                        s="0"+cmin;
                    }else{
                        s=""+cmin;
                    }
                    if(csec < 10){
                        s=s+":0"+csec;
                    }else{
                        s=s+":"+csec;
                    }

                    //s字串為00:00格式
                    timer.setText(s);
                    break;
            }
        }
    };
    private void take(){
        Timer timer01 =new Timer();
        //設定Timer(task為執行內容，0代表立刻開始,間格1秒執行一次)
        timer01.schedule(task, 0,1000);




        teach_reco_Play.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                soundPool.play(s, 1.0F, 1.0F, 0, 0, 1.0F);
            }

        });

    }
    private void showSites() {
        if (dbHelper == null) {
            dbHelper = new DBOpenHelper(this);
        }
        sites = dbHelper.getAllSites();
        int t=sites.size();
        //1
        for(int a=0;a<1;a++){
            Site site = sites.get(a);
            folodname = site.getname();


        }
    }
}
