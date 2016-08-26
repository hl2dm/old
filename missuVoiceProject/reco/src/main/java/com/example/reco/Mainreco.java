package com.example.reco;


import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class Mainreco extends Activity {
    private Button recordButn;
    private Button stopButn;
    int s;
    private MediaRecorder mediaRecorder = null;
    private SoundPool soundPool;
    String as;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reco);
        recordButn = (Button) findViewById(R.id.recordButn);
        stopButn = (Button) findViewById(R.id.stopButn);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);


        //錄音
        recordButn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //設定錄音檔名
                String fileName = "abic.amr";
                try {
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    File myDataPath = new File( SDCardpath.getAbsolutePath() + "/abc" );
                    if( !myDataPath.exists() ) {
                        myDataPath.mkdirs();
                    }
                    File recodeFile = new File(SDCardpath.getAbsolutePath() + "/abc/"+fileName);

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

        //停止錄音
        stopButn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(mediaRecorder != null) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    File SDCardpath = Environment.getExternalStorageDirectory();
                    String fileName = "abic.amr";
                    as=SDCardpath.getAbsolutePath() +"/abc/"+fileName;
                    s = soundPool.load(as, 0);
                }
            }
        });


    }
}