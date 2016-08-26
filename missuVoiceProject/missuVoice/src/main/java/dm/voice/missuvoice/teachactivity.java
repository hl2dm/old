package dm.voice.missuvoice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import browseData_blob.DBOpenHelper;
import browseData_blob.Site;

/**
 * Created by hl2dm on 2013/12/12.
 */
public class teachactivity extends Activity {
    int a=1;
    static Uri uri;
  TextView timer;
    Intent   intent;
    private boolean startflag=false;
    private int tsec=0,csec=0,cmin=0;
    private DBOpenHelper dbHelper;
    private ArrayList<Site> sites;
    Site[] site=new Site[200];
    Site d=new Site();
    int i=1;
    int j;
    byte[][] datag = new byte[100][];
    byte[][] datat = new byte[100][];
    Bitmap[] Bp = new Bitmap[17];
    Button setphoto;
    Button tnbtn;
    Intent destIntent;
    ImageView isso;
    String rembername;
    EditText taitoname;
    Button cameraBTN;
    Intent ta2;
    Intent cameraline;
    Bitmap tttt;
    camera s=new camera();
    int t;
    int well;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.teach_adlog);
        setRequestedOrientation(1);
        try{
        well=datag[0].length;
        }catch (NullPointerException e){
            well=1;
        }
        rembername=null;
        cameraline= new Intent();
        cameraline.setClass(teachactivity.this,camera.class);
        ta2= new Intent();
        ta2.setClass(teachactivity.this, teachactivity2.class);
        showSites();
        Intent intent = new Intent( Intent.ACTION_PICK );

        // 過濾檔案格式
        intent.setType( "image/*" );

        // 建立 "檔案選擇器" 的 Intent  (第二個參數: 選擇器的標題)
        destIntent = Intent.createChooser( intent, "選擇檔案" );

        // 切換到檔案選擇器 (它的處理結果, 會觸發 onActivityResult 事件)
        tnbtn=(Button)findViewById(R.id.teach_next_btn);
        setphoto=(Button)findViewById(R.id.photo_btn);
        timer = (TextView)findViewById(R.id.tv_timer);
        cameraBTN =(Button)findViewById(R.id.camera_btn);
        isso=(ImageView)findViewById(R.id.ivc);
        taitoname=(EditText)findViewById(R.id.et_taito);
        cameraBTN.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {

                startActivity(cameraline);
                finish();
            }

        });
        setphoto.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                startActivityForResult(destIntent, 0);

            }

        });
        tnbtn.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                rembername=taitoname.getText().toString().trim();
                if(well==1){
                    Toast.makeText(teachactivity.this,"你還沒選擇圖片喔", Toast.LENGTH_SHORT).show();
                }else if(rembername.equals("")) {

                    Toast.makeText(teachactivity.this,"你還沒輸入名稱喔", Toast.LENGTH_SHORT).show();
                }else {
                    initDB();
                    startActivity(ta2);
                    finish();
                }
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        // 有選擇檔案
        if ( resultCode == RESULT_OK )
        {
            // 取得檔案的 Uri
            uri = data.getData();
            if( uri != null )
            {

                InputStream iStream = null;
                try {
                    iStream = getContentResolver().openInputStream(uri);
                    byte[] inputData = getBytes(iStream);
                    datag[0]=inputData;
                    isso.setImageURI(uri);
                    well=0;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            else
            {
                setTitle("無效的檔案路徑 !!");
            }
        }
        else
        {
            finish();
        }
    }
    public static Uri getUri(){

        return uri;
    }
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


    @Override
    protected void onResume() {
        super.onResume();
//        Startdb();
        takecamera();
    }

    private void initDB() {
        if (dbHelper == null) {
            dbHelper = new DBOpenHelper(this);
        }

        List<Site> sites_dummy = null;
        try {
            sites_dummy = createDummyData();
            Log.w("what the fuck","o");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbHelper.insertDummyData(sites_dummy);
        Log.w("what the fuck","k");
        sites = dbHelper.getAllSites();
        Log.w("what the fuck","well");
    }

    private List<Site> createDummyData() throws IOException {
        ArrayList<Site> sites_dummy = new ArrayList<Site>();
        Site a1;
        String str1;
//        if(j>0){
//            sites_dummy.removeAll(sites_dummy);
//        }
        i=t+1;
            str1=Integer.toString(i);
        Log.w("sdgsdg",""+i);
            a1 = new Site(str1,rembername,str1,datag[0]);
            sites_dummy.add(a1);

//        j=1;
        return sites_dummy;
    }
    private void showSites() {
        if (dbHelper == null) {
            dbHelper = new DBOpenHelper(this);
        }
        sites = dbHelper.getAllSites();
        t=sites.size();
        Log.w("what the fuck",t+"");
        //1
    }
    private void takecamera() {
        try{
        datag[0]=s.getData();
    Bitmap  shotface = BitmapFactory.decodeByteArray(datag[0], 0, datag[0].length);
    isso.setImageBitmap(shotface);
            well=0;

        }catch (NullPointerException e){
            Log.w("what the fuck",t+"");
        }



         //1
    }

}
