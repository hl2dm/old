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
public class teachactivity3 extends Activity {
    String folodname;
    private DBOpenHelper dbHelper;
    private ArrayList<Site> sites;
    Button START[]=new Button[4];
    Button STOP[]=new Button[4];
    Button PLAY[]=new Button[4];
    TextView TIMER[]=new TextView[4];
    int time_check;
    private MediaRecorder mediaRecorder = null;
    Button next_btn;
    Button back_btn;
    Intent taf;
    private SoundPool soundPool;
    String as;
    int s[]=new int[4];
    private boolean startflag=false;
    private int tsec=0,csec=0,cmin=0;
    int talk1=0;
    int talk2=0;
    int talk3=0;
    int talk4=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.teach_adlog3);
        setRequestedOrientation(1);
        showSites();
        START[0] = (Button) findViewById(R.id.Record_start_btn1);
        STOP[0] = (Button) findViewById(R.id.Record_stop_btn1);
        PLAY[0]= (Button) findViewById(R.id.Record_play_btn1);
        TIMER[0]=(TextView)findViewById(R.id.Record_timer1);

        START[1] = (Button) findViewById(R.id.Record_start_btn2);
        STOP[1] = (Button) findViewById(R.id.Record_stop_btn2);
        PLAY[1]= (Button) findViewById(R.id.Record_play_btn2);
        TIMER[1]=(TextView)findViewById(R.id.Record_timer2);

        START[2] = (Button) findViewById(R.id.Record_start_btn3);
        STOP[2] = (Button) findViewById(R.id.Record_stop_btn3);
        PLAY[2]= (Button) findViewById(R.id.Record_play_btn3);
        TIMER[2]=(TextView)findViewById(R.id.Record_timer3);

        START[3] = (Button) findViewById(R.id.Record_start_btn4);
        STOP[3] = (Button) findViewById(R.id.Record_stop_btn4);
        PLAY[3]= (Button) findViewById(R.id.Record_play_btn4);
        TIMER[3]=(TextView)findViewById(R.id.Record_timer4);

        taf= new Intent();

        next_btn=(Button)findViewById(R.id.teach3_next_btn);
        back_btn=(Button)findViewById(R.id.teach3_back_btn);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);

        STOP[0].setEnabled(false);
        STOP[1].setEnabled(false);
        STOP[2].setEnabled(false);
        STOP[3].setEnabled(false);

        START[0].setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {
                disable();
                tsec = 0;
                csec = 0;
                cmin = 0;
                START[0].setEnabled(false);
                STOP[0].setEnabled(true);
                TIMER[0].setText("00:00");
                time_check=0;
                startflag = true;
                String fileName = "mon.amr";
                try {
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    File myDataPath = new File(SDCardpath.getAbsolutePath() + "/"+folodname);
                    if (!myDataPath.exists()) {
                        myDataPath.mkdirs();
                    }
                    File recodeFile = new File(SDCardpath.getAbsolutePath() + "/"+folodname+"/" + fileName);

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
        START[1].setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {
                tsec = 0;
                csec = 0;
                cmin = 0;
                disable();

                STOP[1].setEnabled(true);
                TIMER[1].setText("00:00");
                startflag = true;
                time_check = 1;
                String fileName = "night.amr";
                try {
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    File myDataPath = new File(SDCardpath.getAbsolutePath() + "/"+folodname);
                    if (!myDataPath.exists()) {
                        myDataPath.mkdirs();
                    }
                    File recodeFile = new File(SDCardpath.getAbsolutePath() + "/"+folodname+"/" + fileName);

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
        START[2].setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {
                tsec = 0;
                csec = 0;
                cmin = 0;
                disable();
                STOP[2].setEnabled(true);
                TIMER[2].setText("00:00");
                time_check = 2;
                startflag = true;
                String fileName = "noon.amr";
                try {
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    File myDataPath = new File(SDCardpath.getAbsolutePath() + "/" + folodname);
                    if (!myDataPath.exists()) {
                        myDataPath.mkdirs();
                    }
                    File recodeFile = new File(SDCardpath.getAbsolutePath() + "/" + folodname + "/" + fileName);

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
        START[3].setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {
                tsec = 0;
                csec = 0;
                cmin = 0;
                disable();
                STOP[3].setEnabled(true);
                TIMER[3].setText("00:00");
                time_check = 3;
                startflag = true;
                String fileName = "wtf.amr";
                try {
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    File myDataPath = new File(SDCardpath.getAbsolutePath() + "/" + folodname);
                    if (!myDataPath.exists()) {
                        myDataPath.mkdirs();
                    }
                    File recodeFile = new File(SDCardpath.getAbsolutePath() + "/" + folodname + "/" + fileName);

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
        STOP[0].setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {
                talk1=1;
                edisable();
                START[0].setEnabled(true);
                STOP[0].setEnabled(false);
                if (mediaRecorder != null) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    String fileName = "mon.amr";
                    as = SDCardpath.getAbsolutePath() + "/" + folodname + "/" + fileName;
                    s[0] = soundPool.load(as, 0);
                }
                startflag = false;
            }

        });
        STOP[1].setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {
                talk1=2;
                edisable();
                STOP[1].setEnabled(false);
                if (mediaRecorder != null) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    String fileName = "night.amr";
                    as = SDCardpath.getAbsolutePath() + "/" + folodname + "/" + fileName;
                    s[1] = soundPool.load(as, 0);
                }
                startflag = false;
            }

        });
        STOP[2].setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                talk1=3;
                edisable();
                STOP[2].setEnabled(false);
                if(mediaRecorder != null) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    String fileName = "noon.amr";
                    as=SDCardpath.getAbsolutePath() +"/"+folodname+"/"+fileName;
                    s[2] = soundPool.load(as, 0);
                }
                startflag=false;
            }

        });

        STOP[3].setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                talk1=4;
                edisable();
                STOP[3].setEnabled(false);
                if(mediaRecorder != null) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    String fileName = "wtf.amr";
                    as=SDCardpath.getAbsolutePath() +"/"+folodname+"/"+fileName;
                    s[3] = soundPool.load(as, 0);
                }
                startflag=false;
            }

        });
        next_btn.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                if(talk1+talk2+talk3+talk4!=4){
                    Toast.makeText(teachactivity3.this, "此項步驟你還沒完成喔", Toast.LENGTH_SHORT).show();
                }else {
                taf.setClass(teachactivity3.this, teachactivityf.class);
                startActivity(taf);
                finish();
                }
            }

        });
        back_btn.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                taf.setClass(teachactivity3.this, teachactivity2.class);
                startActivity(taf);
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
    private Handler handler;

    {
        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {

                    case 1:
                        csec = tsec % 60;
                        cmin = tsec / 60;
                        String s = "";
                        if (cmin < 10) {
                            s = "0" + cmin;
                        } else {
                            s = "" + cmin;
                        }
                        if (csec < 10) {
                            s = s + ":0" + csec;
                        } else {
                            s = s + ":" + csec;
                        }
                        //鬼BUG不得以才這樣寫
                        if (time_check == 0) {
                            TIMER[0].setText(s);
                        } else {
                            //s字串為00:00格式
                            switch (time_check) {
                                case 1:
                                    TIMER[1].setText(s);
                                    break;
                                case 2:

                                    TIMER[2].setText(s);
                                    break;
                                case 3:

                                    TIMER[3].setText(s);
                                    break;
                                default:
                            }

                            break;

                        }
                }
            }
        };
    }
    private void disable(){
        START[0].setEnabled(false);
        START[1].setEnabled(false);
        START[2].setEnabled(false);
        START[3].setEnabled(false);
    }
    private void edisable(){
        START[0].setEnabled(true);
        START[1].setEnabled(true);
        START[2].setEnabled(true);
        START[3].setEnabled(true);
    }
    private void take(){
        Timer timer01 =new Timer();
        //設定Timer(task為執行內容，0代表立刻開始,間格1秒執行一次)
        timer01.schedule(task, 0,1000);




        PLAY[0].setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                soundPool.play(s[0], 1.0F, 1.0F, 0, 0, 1.0F);
            }

        });

        PLAY[1].setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                soundPool.play(s[1], 1.0F, 1.0F, 0, 0, 1.0F);
            }

        });

        PLAY[2].setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                soundPool.play(s[2], 1.0F, 1.0F, 0, 0, 1.0F);
            }

        });

        PLAY[3].setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                soundPool.play(s[3], 1.0F, 1.0F, 0, 0, 1.0F);
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
