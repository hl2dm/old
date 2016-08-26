package eat.my.seemypt;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class MainActivity extends Activity {
    ImageButton send;
    EditText name;
    EditText passwd;
    TextView show;
    static int a =0;
    int stop=0;
    String GA;
    String GB;
    private ProgressBar secondBar = null;
    Response res;
    private static String w[]=new String [300];
    private static String A[]=new String [300];
    private static String sessionid = "";
    protected static final int REFRESH_DATA = 0x00000001;
    String over;
    Handler mHandler = new Handler()

    {

        @Override
        public void handleMessage(Message msg)

        {

            switch (msg.what)

            {

                // 顯示網路上??????資??

                case REFRESH_DATA:
                    String result = null;
                    if (msg.obj instanceof String)
                        result = (String) msg.obj;
                    if (result != null)
                        if(stop>=6){
                            aa();
                        }
                    if(stop==0){
                        sa();
                    }
                    break;

            }

        }

        private void aa() {
            Toast.makeText(getApplicationContext(), "登錄錯誤!請確認帳號密碼再登錄，避免IP遭到封鎖",
                    Toast.LENGTH_SHORT).show();

        }

        private void sa() {
            secondBar.setVisibility(View.GONE);
            FragmentManager DS2 = getFragmentManager();
            FragmentTransaction DSE2 = DS2.beginTransaction();
            Fragment DF2 = DS2.findFragmentById(R.id.frameLayout5);
            if (DF2 == null) {
                F3 usermune = new F3(A[0],A[1],A[2],A[3],A[4],A[5],A[6],A[10],A[11],A[12]);
                DSE2.add(R.id.frameLayout5, usermune);
                DSE2.addToBackStack(null);
                DSE2.commit();
            }

        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        secondBar = (ProgressBar)findViewById(R.id.progressBar1);
        secondBar.setVisibility(View.GONE);
        send=(ImageButton)findViewById(R.id.imageButton1);
        name=(EditText)findViewById(R.id.editText1);
        passwd=(EditText)findViewById(R.id.editText2);
        show=(TextView)findViewById(R.id.textView1);
        show.setText("請依CHD規定謹慎使用本程式，確定帳號密碼熟透，再使用否則登錄錯誤太多次，將造成ＩＰ封鎖與其他不良影響請自行負責");
        show.setTextSize(30);
        send.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                secondBar.setVisibility(View.VISIBLE);
                // TODO Auto-generated method stub
                GA=name.getText().toString();
                GB=passwd.getText().toString();
                String msg = null;
                Thread t = new Thread(new sendPostRunnable(msg));
                t.start();

            }

        });

    }
    public class Person implements Serializable {
        private static final long serialVersionUID = -7060210544600464481L;
        private String name;
        private Map age;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Map getAge() {
            return age;
        }
        public void setAge(Map age) {
            this.age = age;
        }

    }
    String sendPostDataToInternet(String strTxt2) {

        try {
            stop=0;
            for(int s=1;s<2;s--){
                res = Jsoup
                        .connect("http://chdbits.org/takelogin.php")
                        .data("username", GA, "password", GB)
                        .method(Method.POST)
                        .timeout(10000)
                        .execute();



                Map<String, String> cookies = res.cookies();
                Document doc = Jsoup.connect("http://chdbits.org").cookies(cookies).get();

                Elements links = doc.select("a[href]");
                for (Element link : links) {
                    String linkHref = link.attr("href");
                    String linkText = link.text();
                    w[a]= linkHref;
                    a=a+1;
                }
                if(a<16){

                    stop=6;
                }
                a=0;

                Document doc2 = Jsoup.connect("http://chdbits.org/"+w[16]).cookies(cookies).get();
                Elements f=doc2.select(".rowfollow");
                for (Element link : f) {
                    String linkText = link.text();
                    A[a]= linkText;
                    a=a+1;
                }
                if(!A[0].equals(null))
                {
                    s=3;
                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String strResult = "ss";
//		send.setText(over);

        return strResult;
    }

    class sendPostRunnable implements Runnable {
        String strTxt = null;

        public sendPostRunnable(String strTxt) {
            this.strTxt = strTxt;
        }

        @Override
        public void run() {

            String result = sendPostDataToInternet(strTxt);

            mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();

        }

    }
    public void onStop(){
        super.onStop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);



        return true;
    }

}
