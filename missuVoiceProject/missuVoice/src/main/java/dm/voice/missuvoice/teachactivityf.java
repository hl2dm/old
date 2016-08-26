package dm.voice.missuvoice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import browseData_blob.DBOpenHelper;
import browseData_blob.Site;

/**
 * Created by hl2dm on 2013/12/12.
 */
public class teachactivityf extends Activity implements TextToSpeech.OnInitListener {
    private DBOpenHelper dbHelper;
    private ArrayList<Site> sites;
    private static final int MY_SPEECH_TO_TEXT = 0;
    private static final int MY_TTS_DATA_CHECK = 1;
    private TextView tvText;
    private TextToSpeech tts;
    private int alertId;
    private int alertId2;
    private SoundPool soundPool;
    int pd=0;
    int s[]=new int[5];
    String as[]=new String[5];
    String folodname;
    File SDCardpath = Environment.getExternalStorageDirectory();
    Bitmap shotface;
    ImageView face_img;
    byte[][] datat = new byte[100][];
    Button finnow;
    Intent endline;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.teach_adlogf);
        setRequestedOrientation(1);
        endline=new Intent();
        finnow=(Button)findViewById(R.id.teach_final_btn);
        finnow.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                endline.setClass(teachactivityf.this, ListView3.class);
                startActivity(endline);
                finish();
            }

        });
        face_img=(ImageView)findViewById(R.id.face);
        showSites();
        tts = new TextToSpeech(this, this);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
        as[3] = SDCardpath.getAbsolutePath() + "/" + folodname + "/" + "night.amr";
        as[4] = SDCardpath.getAbsolutePath() + "/" + folodname + "/" + "wtf.amr";
        as[0] = SDCardpath.getAbsolutePath() + "/" + folodname + "/" + "hello.amr";
        as[2] = SDCardpath.getAbsolutePath() + "/" + folodname + "/" + "noon.amr";
        as[1] = SDCardpath.getAbsolutePath() + "/" + folodname + "/" + "moo.amr";
        s[0] = soundPool.load(as[0], 0);
        s[1] = soundPool.load(as[1], 0);
        s[2] = soundPool.load(as[2], 0);
        s[3] = soundPool.load(as[3], 0);
        s[4] = soundPool.load(as[4], 0);
//        talk=(Button)findViewById(R.id.face);
//        talk.setOnClickListener(new Button.OnClickListener(){
//
//            @Override
//
//            public void onClick(View v) {
//
//            }
//
//        });
    }
    public void onSpeakClick(View view) {
        // 會提醒user可以開始語音輸入
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        // 語音辨識依據web search語句
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        // 設定提示文字以提醒user開始語音輸入
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.title_speak));
        startActivityForResult(intent, MY_SPEECH_TO_TEXT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == MY_SPEECH_TO_TEXT) {
                // 辨識結果會有多個相近的文字，並依照符合程度高低排序，第一個符合程度通常是最高
                List<String> list = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String text = list.get(0);

pd=0;
                textToSpeech(text);
                if(text.equals("你好")){
                    soundPool.play(s[0], 1.0F, 1.0F, 0, 0, 1.0F);
                    pd=1;
                }else if(text.equals("早安")){
                    soundPool.play(s[1], 1.0F, 1.0F, 0, 0, 1.0F);
                    pd=1;
                }else if(text.equals("午安")){
                    soundPool.play(s[2], 1.0F, 1.0F, 0, 0, 1.0F);
                    pd=1;
                }else if(text.equals("晚安")){
                    soundPool.play(s[3], 1.0F, 1.0F, 0, 0, 1.0F);
                    pd=1;
               }
                if(pd==0){
                    soundPool.play(s[4], 1.0F, 1.0F, 0, 0, 1.0F);
                }


            }
        }
    }

    private void textToSpeech(String text) {
        // 雖然大部份Android手機都支援TTS，但最好還是檢查一下是否有安裝TTS檔案
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_TTS_DATA_CHECK);

        int status = tts.isLanguageAvailable(Locale.getDefault());
        if (status == TextToSpeech.LANG_NOT_SUPPORTED) {
            Toast.makeText(this, "",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // 可以指定聲音串流類型，方便user設定(例如：大小聲)
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                String.valueOf(AudioManager.STREAM_MUSIC));

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, params);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.shutdown();
        }
        super.onDestroy();
    }
    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub

    }

    private void showSites() {
        if (dbHelper == null) {
            dbHelper = new DBOpenHelper(this);
        }
        sites = dbHelper.getAllSites();
        int t=sites.size()-1;
//        //1
//        for(int a=t;a==t;a++){
            Site site = sites.get(t);
            folodname = site.getname();
            datat[t] = site.getImage();
            shotface = BitmapFactory.decodeByteArray(datat[t], 0, datat[t].length);
            face_img.setImageBitmap(shotface);
//        }
    }
}
