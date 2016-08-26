package dm.book.rulethirtyfour;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import org.apache.http.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hl2dm on 2013/9/21.
 */

public class photo extends Activity {
    Document doc2;
    String msg="a";
    ImageView []PhotoV=new ImageView[20];
    StringBuffer[] GGG = new StringBuffer[200];
    String[] TKG = new String[300];
    URL[] TKs = new URL[200];
    byte[][] data = new byte[100][];
    InputStream[] Is = new InputStream[200];
    Bitmap[] Bp = new Bitmap[80];
    int i =0;
    protected static final int REFRESH_DATA = 0x00000001;


    final Handler cwjHandler = new Handler();

    final Runnable mUpdateResults = new Runnable() {
        public void run() {
            updateUI();
        }
    };


    protected void NetworkOperation() {

        Thread t = new Thread() {
            public void run() {

                cwjHandler.post(mUpdateResults); //高速UI线程可以更新结果了
            }
        };
        t.start();
    }

    private void updateUI() {
        for(int i = 0;i<=13;i++){
        PhotoV[i].setImageBitmap(Bp[i+6]);
        }
    }
    Handler mHandler = new Handler()

    {

        @Override
        public void handleMessage(Message msg)

        {

            switch (msg.what)

            {

                // 顯示網路上�??��?資�?

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
//            secondBar.setVisibility(View.GONE);
//            OK.setText(com);
//            initDB();
//            showSites();

        }

    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        PhotoV[0]=(ImageView)findViewById(R.id.imageView);
        PhotoV[1]=(ImageView)findViewById(R.id.imageView2);
        PhotoV[2]=(ImageView)findViewById(R.id.imageView3);
        PhotoV[3]=(ImageView)findViewById(R.id.imageView4);
        PhotoV[4]=(ImageView)findViewById(R.id.imageView5);
        PhotoV[5]=(ImageView)findViewById(R.id.imageView6);
        PhotoV[6]=(ImageView)findViewById(R.id.imageView7);
        PhotoV[7]=(ImageView)findViewById(R.id.imageView8);
        PhotoV[8]=(ImageView)findViewById(R.id.imageView9);
        PhotoV[9]=(ImageView)findViewById(R.id.imageView10);
        PhotoV[10]=(ImageView)findViewById(R.id.imageView11);
        PhotoV[11]=(ImageView)findViewById(R.id.imageView12);
        PhotoV[12]=(ImageView)findViewById(R.id.imageView13);
        PhotoV[13]=(ImageView)findViewById(R.id.imageView14);


        Thread t = new Thread(new sendPostRunnable(msg));
        t.start();

    }
    String sendPostDataToInternet(String strTxt2) {
        try {
            doc2 = Jsoup.connect("http://rule34.paheal.net/post/list/bleach/1").get();
            System.out.print(doc2);
            Elements xs = doc2.select("img");
            i=0;
            for (Element asds : xs) {
                if(i<30){
                GGG[i] = new StringBuffer();
                GGG[i].append(asds.attr("src"));
                TKG[i] = GGG[i].toString();
                if(i>6){
                    TKs[i] = new URL(TKG[i]);
//                    data[i] = getImage(TKG[i]);
					Is[i] = TKs[i].openStream();
					Bp[i] = BitmapFactory.decodeStream(Is[i]);
					Is[i].close();
                }
                    i = i + 1;

                String s = Integer.toString(i);
                Log.i("cc", s);
                }
            }
//            TKs[0] = new URL(
//                    "https://e85c89b2-a-62cb3a1a-s-sites.googlegroups.com/site/hl2dmpc01/pag/12z.jpg");
//            Is[0] = TKs[0].openStream();
//            Bp[0] = BitmapFactory.decodeStream(Is[0]);
//            Is[0].close();
//            TKs[1] = new URL(
//                    "https://e85c89b2-a-62cb3a1a-s-sites.googlegroups.com/site/hl2dmpc01/pag/12z.jpg");
//            Is[1] = TKs[1].openStream();
//            Bp[1] = BitmapFactory.decodeStream(Is[1]);
//            Is[1].close();
//            // ///////////////////////////////////////////////////////////////////////////////////////////////////
//            Elements xsj = doc2.select("a[href]");
//            for (Element asdz : xsj) {
//                GG[h] = new StringBuffer();
//                GG[h].append(asdz.attr("href"));
//                TKG[h] = GG[h].toString();
//                if (TKG[h].length() > 23) {
//                    String g = TKG[h].substring(22, 24);
//                    if (g.equals("g/")) {
//                        TKD[z] = TKG[h];
//                        z = z + 1;
//                    }
//
//                }
//                h = h + 1;
//            }
//            int s = 0;
//            for (int x = 50; x < 80; x = x + 2) {
//                TKE[s] = TKD[x];
//                s = s + 1;
//
//            }

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
        NetworkOperation();
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

}

