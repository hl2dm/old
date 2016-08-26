package cool.dm.ehbrowser;

import cool.dm.ehbrowser.R;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.http.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class bookon extends Fragment {
	
	String[] TKG = new String[6000];
	URL[] TKs = new URL[6000];
	Bitmap[] Bp = new Bitmap[20];
	InputStream[] Is = new InputStream[6000];
	String[] TKA = new String[6000];
	String[] TKB = new String[6000];
	String[] TKC = new String[6000];
	String[] TKD = new String[6000];
	String[] TKE = new String[6000];
	String[] onebook = new String[6000];
	String uul;
	String title1;
	StringBuffer[] GGG = new StringBuffer[6000];
	StringBuffer[] GG = new StringBuffer[6000];
	ImageView ss;
	int xc=0;
	int re=0;
	int ak = 0;
	int ab=0;
	int akk = 0;
	int g = 0;
	int n = 0;
	int i = 1;
	int h = 0;
	int x=1;
	int p=0;
	Timer timer = new Timer(true);
	int z = 0;
	int b=0;
	int j=0;
	int c=0;
	int pagint=0;
	String pag;
	String end;
	int over=0;
	Document doc;
	Button up;
	Button down;
	Button sindex;
	Button lastP;
	Button nextP;
	private ProgressBar secondBar = null;
	protected static final int REFRESH_DATA = 0x00000001;
	TextView OK;
	protected static final int REFRESH_DATA2 = 0x00000002;
	InputStream inputStream;
	Bitmap bitmap;
	Handler mHandler2 = new Handler()
	{

	@Override

	public void handleMessage(Message msg2 )

	{

	switch (msg2.what)

	{

	// 顯示網路上抓取的資料

	case REFRESH_DATA2:
	String result2 = null;
	if (msg2.obj instanceof String)
	result2 = (String) msg2.obj;
	 secondBar.setVisibility(View.GONE);
	 ss.setImageBitmap(bitmap);

	if (result2 != null)
		
	break;
	}

	}



	};
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
				break;

			}

		}



	};

	public bookon(String string) {
		this.title1 = string;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.s1,container, false);
	     AdView ssj=(AdView)getActivity().findViewById(R.id.adaView);
	     ssj = new AdView(getActivity(), AdSize.BANNER, "a1511bda3c8906f");
	     ssj.loadAd(new AdRequest());
		View v = fragmentView.findViewById(R.id.up_btn);//找到你要设透明背景的layout 的id    
		v.getBackground().setAlpha(10);//透明度0~255透明度值 ，值越小越透明  
		View v2 = fragmentView.findViewById(R.id.next_btn);//找到你要设透明背景的layout 的id    
		v2.getBackground().setAlpha(10);//透明度0~255透明度值 ，值越小越透明 
		View v3 = fragmentView.findViewById(R.id.index_btn);//找到你要设透明背景的layout 的id    
		v3.getBackground().setAlpha(10);//透明度0~255透明度值 ，值越小越透明 
		View v4 = fragmentView.findViewById(R.id.top_btn);//找到你要设透明背景的layout 的id    
		v4.getBackground().setAlpha(10);//透明度0~255透明度值 ，值越小越透明 
		View v5 = fragmentView.findViewById(R.id.down_btn);//找到你要设透明背景的layout 的id    
		v5.getBackground().setAlpha(10);//透明度0~255透明度值 ，值越小越透明 
	   	 secondBar=(ProgressBar)fragmentView.findViewById(R.id.ppbb1);
	   	secondBar.setVisibility(View.VISIBLE);
		String msg = "aa";
		Thread t = new Thread(new sendPostRunnable(msg));
		t.start();
		String msg2 = null ;
   	    Thread C = new Thread(new sendPostRunnable2(msg2));
   		C.start();

		up=(Button)fragmentView.findViewById(R.id.next_btn);
		down=(Button)fragmentView.findViewById(R.id.up_btn);
		sindex=(Button)fragmentView.findViewById(R.id.index_btn);
		lastP=(Button)fragmentView.findViewById(R.id.top_btn);
		nextP=(Button)fragmentView.findViewById(R.id.down_btn);
		ss= (ImageView)fragmentView.findViewById(R.id.img);
//        ss.setImageResource(R.drawable.s);
		up.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 secondBar.setVisibility(View.VISIBLE);
				if(c+1<ab){
				 c=c+1;
				}
//				if(c==19){
//					String msgd = "aa";
//					 pagint= pagint+19;
//					 title1=title1+"?p="+x;
//					 x=x+1;
//					 if(re==0){
//						 xc=1;
//					Thread t = new Thread(new sendPostRunnable(msgd));
//					t.start();
//					 }
//				}
				String msg2 = null ;
		   	    Thread C = new Thread(new sendPostRunnable2(msg2));
		   		C.start();
			        //得到可用的图片  
		
			}
	
			});
		down.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 secondBar.setVisibility(View.VISIBLE);
				if(c>0)
				{
				c=c-1;
				}
				String msg2 = null ;
		   	    Thread C = new Thread(new sendPostRunnable2(msg2));
		   		C.start();
			        //得到可用的图片  
		
			}
	
			});
		nextP.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				title1=title1.substring(0,42);
				p=p+1;
				title1=title1+"?p="+p;
				c=0;
				String msgd = "aa";
				Thread t = new Thread(new sendPostRunnable(msgd));
				t.start();
			}
	
			});
		lastP.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(p<0){
					p=0;
				}
				
				p=p-1;
				title1=title1+"?p="+p;
				c=0;
				String msgd = "aa";
				Thread t = new Thread(new sendPostRunnable(msgd));
				t.start();

			}
	
			});

		sindex.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				 secondBar.setVisibility(View.VISIBLE);
				c=0;
				String msg2 = null ;
		   	    Thread C = new Thread(new sendPostRunnable2(msg2));
		   		C.start();
			        //得到可用的图片  
		
			}
	
			});
		return fragmentView;
	}

	String sendPostDataToInternet(String strTxt2) {
		try {
			Document doc2 = Jsoup.connect(title1).get();
			// System.out.print(doc2);
			Elements xsj = doc2.select("a[href]");
			h=0;
			z=0;
			for (Element asdz : xsj) {
				GGG[h] = new StringBuffer();
				GGG[h].append(asdz.attr("href"));
				TKG[h] = GGG[h].toString();
				if (TKG[h].length() > 23) {
					String g = TKG[h].substring(22, 24);
					if (g.equals("s/")) {
						TKD[z] = TKG[h];
						z = z + 1;
					}
				}
				h = h + 1;
			}
			for (int c = pagint; c < z; c++) {
				if(over<1){
				Document doc3 = Jsoup.connect(TKD[c]).get();
				Elements xsx = doc3.select("#img");
				for (Element asd : xsx) {
					GG[c] = new StringBuffer();
					GG[c].append(asd.attr("src"));
					TKE[c] = GG[c].toString();
					TKs[c] = new URL(TKE[c]);
					String s = Integer.toString(c);
					Log.i("PG", s);
					ab=c;
				}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EOFException e) {
//	          Toast.makeText(getActivity(),"載入失敗請重新載入", Toast.LENGTH_SHORT).show();    
			e.printStackTrace();
		}catch (IOException e) {


		}

		String strResult = "ss";
		if(xc==1){
		ab=ab-19;
		}
if(ab==19){
	re=0;
}
		return strResult;
	}


	class sendPostRunnable implements Runnable {
		String strTxt = null;

		// 建�?子�?設�?要傳?��?�?
		public sendPostRunnable(String strTxt) {
			this.strTxt = strTxt;
		}

		@Override
		public void run() {

			String result = sendPostDataToInternet(strTxt);

			mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();

		}

	}
	private String sendPostDataToInternet2(String strTxt2)

	{
	/* 建立HTTP Post連線 */
	    try {
	        URL url = new URL(TKE[c]);
	        URLConnection conn = url.openConnection();

	        HttpURLConnection httpConn = (HttpURLConnection)conn;
	        httpConn.setRequestMethod("GET");
	        httpConn.connect();

	        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	          inputStream = httpConn.getInputStream();
if(over==0){
	           bitmap = BitmapFactory.decodeStream(inputStream);
} 
	        }
	    } catch (MalformedURLException e1) {
	        e1.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	return null;
	}

	class sendPostRunnable2 implements Runnable
	{
	String strTxt2 = null;
	// 建構子，設定要傳的字串
	public sendPostRunnable2(String strTxt2)
	{
	this.strTxt2 = strTxt2;
	}





	@Override
	public void run()
	{

	String result2 = sendPostDataToInternet2(strTxt2);


	mHandler2.obtainMessage(REFRESH_DATA2, result2).sendToTarget();

	}
	}
	public void onDetach (){
		over=1;
		 super.onDetach();
	}
	public void onStop(){
		over=1;
		 super.onStop();
	}

}