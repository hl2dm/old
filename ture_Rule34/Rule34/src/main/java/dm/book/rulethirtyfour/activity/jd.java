package dm.book.rulethirtyfour.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dm.book.rulethirtyfour.R;
import dm.book.rulethirtyfour.adapter.MyGridViewAdapter;

public class jd extends Activity implements OnScrollListener {
    private static final String TAG = "MainActivity";
    Document doc2;
    Document doc;
    int the=0;
    int ab=2;
    Context context=jd.this;
    String msg="a";
    String seach;
    StringBuffer[] GGG = new StringBuffer[3000];
    StringBuffer[] linG = new StringBuffer[3000];
    String[] TKG = new String[1009];
    URL Dl_rrl;
    InputStream Dl_Is;
    Bitmap Dl_Bp;
    Bitmap[] Bp = new Bitmap[1000];
    int Bp_long=0;
    int df=0;
    ImageView iv;
    int text_long;
    String check;
    String[] link_st = new String[1000];
    String[] link_st2 = new String[1000];
    int img_int=0;
    private TextView textview_show_prompt = null;
    private GridView gridview_test = null;
    AlertDialog alertDialog;
    ProgressBar loading;
    static File paginfo;
    protected static final int REFRESH_DATA = 0x00000001;
    int i;
    TextView cc;
    private List<String> mList = null;
    //用来保存GridView中每个Item的图片，以便释放
    public static Map<String,Bitmap> gridviewBitmapCaches = new HashMap<String,Bitmap>();

    private MyGridViewAdapter adapter = null;
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
                        re();

                    break;

            }

        }

        private void re() {
            if(df==0){
                loading.setVisibility(View.INVISIBLE);
                findViews();
                    initData();
                    setAdapter();
                if(Bp_long<=3){
                    cc.setText("IM,SORRY");
                }
            }
            if(df==1){
                if(ab==2){
                    loading.setVisibility(View.INVISIBLE);
                    take();
                }
            }
//            secondBar.setVisibility(View.GONE);
//            OK.setText(com);
//            initDB();
//            showSites();

        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.showpg);
        Bundle bundle0311 =this.getIntent().getExtras();
        seach = bundle0311.getString("seach");
        loading = (ProgressBar)findViewById(R.id.progressBare);
        loading.setVisibility(View.VISIBLE);
cc=(TextView)findViewById(R.id.textview_show_prompt);
        Thread t = new Thread(new sendPostRunnable(msg));
        t.start();

    }

    private AlertDialog getAlertDialog(String title,String message){
        //產生一個Builder物件
        AlertDialog.Builder builder = new AlertDialog.Builder(jd.this);
        //設定Dialog的標題
        builder.setTitle(title);
        //設定Dialog的內容
        builder.setMessage(message);
        //設定Positive按鈕資料
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //設定Negative按鈕資料
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //按下按鈕時顯示快顯
            }
        });
        //利用Builder物件建立AlertDialog
        return builder.create();
    }
    String sendPostDataToInternet(String strTxt2) {
        try {
            if(df==0){
                seach=seach.replaceAll(" ","_");
                i=0;
                int z=0;
                int j=0;
                int b=0;
                int g=5;
                int d=0;
                for(int s=1;s<1000;s++){
                    g=i+10;
                    doc2 = Jsoup.connect("http://rule34.paheal.net/post/list/"+seach+"/"+s).timeout(50000).get();
                    Elements xs = doc2.select("img");
                    Elements link=doc2.select("a");
                    for (Element asds2 : link) {
                        linG[i]=new StringBuffer();
                        linG[i].append(asds2.attr("href"));
                        link_st[i]=linG[i].toString();

                        text_long=link_st[i].length();
                        if(text_long>8){
                            check=link_st[i].substring(0,7);
                            if(check.equals("/post/v")){
                                if(i>g){
                                    link_st2[d]="http://rule34.paheal.net"+link_st[i];
                                    d++;
                                }
                                i=i+1;
                            }
                        }

                    }
                    b=z+7;
                    for (Element asds : xs) {
                        GGG[z] = new StringBuffer();
                        GGG[z].append(asds.attr("src"));
                        if(z>b){
                            TKG[j] = GGG[z].toString();
                            j=j+1;
                        }
                        z = z + 1;



                    }
                    Bp_long=j;
                }
                String x = Integer.toString(i);
                Log.i("cc", x);
            }
            if(df==1){
                ab=2;
                doc2=null;
                try{

                    doc2 = Jsoup.connect(link_st2[img_int]).timeout(50000).get();
                }catch (OutOfMemoryError E){
                    ab=1;
                    loading.setVisibility(View.INVISIBLE);
//                    Thread t = new Thread(new sendPostRunnable(msg));
//                    t.start();
                }if(ab==2){
                    Elements xs = doc2.select("img");
                    i=0;
                    for (Element asds : xs) {
                        GGG[i] = new StringBuffer();
                        GGG[i].append(asds.attr("src"));
                        TKG[i] = GGG[i].toString();
                        i++;
                    }
                    int out=2;
                    int bfds=10;
                    for(int cx=1;cx<bfds;cx++){
                        try {
                            Dl_rrl = new URL(TKG[7]);
                            Dl_Is = Dl_rrl.openStream();
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = out;//圖片寬高都為原來的二分之一，即圖片為原來的四分之一
                            options.inPreferredConfig = Bitmap.Config.ARGB_4444;    // 默认是Bitmap.Config.ARGB_88884 T# d! A/ ~- F* J" ?- P4 O; P1 G
                            Dl_Bp = BitmapFactory.decodeStream(Dl_Is,null,options);
                            Dl_Is.close();
                            cx=11;
                        } catch (OutOfMemoryError E) {
                            out++;
                            cx=0;
                        }
                    }
//                saveBitmap("gg",Dl_Bp,"ss");

                }
                the=0;
            }

        }

        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        NetworkOperation();
        String strResult = "ss";
        return strResult;
    }
    public byte[] getImage(String t2) throws Exception {
        URL url = new URL(t2);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return readStream(inStream);
        }
        return null;
    }
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
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
    private void findViews(){
        textview_show_prompt = (TextView)findViewById(R.id.textview_show_prompt);
        gridview_test = (GridView)findViewById(R.id.gridview_test);
    }

    private void initData(){
        mList = new ArrayList<String>();
//    	String url = "http://rule34-data-002.paheal.net/_images/3582efbb9f7a004aa900ad404fb4cdde/1195466%20-%20Ai_Sutome%20Loli_Pop%20Misae_Nohara%20Miss_Anderson%20Miss_Katz%20Miss_Polly%20Moeko_Sakurada%20Patty_Milfer%20Penny_Milfer%20Shin_Chan%20nanako_ohara.png";
        //为sd卡下面创建testGridView文件夹，将图片放入其中
        //为了方便测试，我们这里只存入一张图片，将其路径后面添加数字进行区分，到后面要获取图片时候再处理该路径。
        for(int i=0;i<Bp_long;i++){
            mList.add(TKG[i] + "/" + i);//区分路径
        }
    }
    private void take(){
        try {
            saveBitmap("sdcard",Dl_Bp,"gg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final AlertDialog dialog = new AlertDialog.Builder(jd.this).create();
        LayoutInflater inflater = LayoutInflater.from(jd.this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        View view = inflater.inflate(R.layout.test, null); // xml Layout file for imageView
        ImageView img = (ImageView) view.findViewById(R.id.ttv);
        Button save = (Button) view.findViewById(R.id.save_btn);
        Button Share = (Button) view.findViewById(R.id.btn_share);
        Button goback = (Button) view.findViewById(R.id.btn_back);
        img.setImageBitmap(Dl_Bp);
        save.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                checkSDCard();
                try {
                    saveBitmap("sdcard",Dl_Bp,"gg");
                    Uri localUri = Uri.fromFile(paginfo);

                    Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);

                    sendBroadcast(localIntent);
                Toast.makeText(jd.this,"save_OKOK_GO_SEE_YOU_SD_RULE_FILE",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        Share.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {

                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), Dl_Bp, null,null));
                share(uri);
            }

        });
        goback.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                dialog.cancel();
            }

        });
//        img.setImageResource(R.drawable.dust);
        dialog.setView(view);
        dialog.show();
        dialog.getWindow().setAttributes(lp);
//
    }
    public static boolean checkSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }


    public static void saveBitmap(String pFolderPath, Bitmap pBitmap,
                                  String pFileName) throws IOException {

        File pictureFileDir = getDir();

        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {


            return;

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";
        String filename = pictureFileDir.getPath() + File.separator + photoFile;

        File pictureFile = new File(filename);
        paginfo = pictureFile;
        FileOutputStream fos = new FileOutputStream(pictureFile);

        String _folderPath = pFolderPath;
        if (_folderPath.lastIndexOf("/") == -1) {
            _folderPath += "/";
        }
        File _file = new File(Environment.getExternalStorageDirectory(),getTimeName()+".jpg");
        OutputStream _outStream  = new FileOutputStream(pictureFile);

        pBitmap.compress(Bitmap.CompressFormat.JPEG, 90, _outStream);

        _outStream.flush();
        _outStream.close();
    }


    private static File getDir() {
        File sdDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(sdDir, "RULE34");
    }

    // 自動檔名依日期
    public static String getTimeName() {
        Date _date = new Date(System.currentTimeMillis());
        SimpleDateFormat _sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Random _random = new Random();
        return _sdf.format(_date) + _random.nextInt(999);
    }

    private void setAdapter(){

        adapter = new MyGridViewAdapter(this, mList);
        gridview_test.setAdapter(adapter);
        gridview_test.setOnScrollListener(this);
        gridview_test.setNumColumns(2);
        alertDialog = getAlertDialog("這是一個對話框","請選擇......");
        gridview_test.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView parent, View v, final int position, long id)
            {
                if(the==0){
                    the=1;
                    df=1;
                    img_int=position;
                    loading.setVisibility(View.VISIBLE);
                    Thread t = new Thread(new sendPostRunnable(msg));
                    int number = img_int+1;
                    Toast.makeText(jd.this, "you set for:["+number+"]PAG", Toast.LENGTH_LONG).show();
                    t.start();
                }

            }
        });
//        gridview_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                alertDialog.show();
//
//                // DO something
//
//            }
//        });
    }

    //卸载图片的函数
    private void recycleBitmapCaches(int fromPosition,int toPosition){
        Bitmap delBitmap = null;
        for(int del=fromPosition;del<toPosition;del++){
            delBitmap = gridviewBitmapCaches.get(mList.get(del));
            if(delBitmap != null){
                //如果非空则表示有缓存的bitmap，需要清理
                Log.d(TAG, "release position:" + del);
                //从缓存中移除该del->bitmap的映射
                gridviewBitmapCaches.remove(mList.get(del));
                delBitmap.recycle();
                delBitmap = null;
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub
        //注释：firstVisibleItem为第一个可见的Item的position，从0开始，随着拖动会改变
        //visibleItemCount为当前页面总共可见的Item的项数
        //totalItemCount为当前总共已经出现的Item的项数
        recycleBitmapCaches(0,firstVisibleItem);
        recycleBitmapCaches(firstVisibleItem+visibleItemCount, totalItemCount);

    }







//    @Override
//    protected void onPause() {
//        super.onPause();
//        finish();
//    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub
    }

    private void share(Uri uri){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        if(uri!=null){
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/*");
        }
        //自定义选择框的标题
        //startActivity(Intent.createChooser(shareIntent, "邀请好友"));
        //系统默认标题
        startActivity(shareIntent);

}


}
