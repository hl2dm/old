package cool.dm.ehbrowser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.ParseException;
import org.browseData_blob.DBOpenHelper;
import org.browseData_blob.Site;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;

public class Viewpage extends Fragment {
	String[] TKG = new String[300];
	URL[] TKs = new URL[200];
	Bitmap[] Bp = new Bitmap[24];
	InputStream[] Is = new InputStream[200];
	String pagelink[]=new String[27];
	int why=0;
	int i = 1;
	int c1=0;
	int c2=0;
	int c3=0;
	int c4=0;
	int c5=0;
	int c6=0;
	int c7=0;
	int c8=0;
	int c9=0;
	int c10=0;
	int Pvlong;
	int a=0;
	int b=0;
	int c=0;
	int pagl=0;
	String  linkpg[]=new String[100];
	String  linkpa[]=new String[100];
	String foundText[]=new String[200];
	private static String w[]=new String [300];
	private static String A[]=new String [300];
	int linelang;
	int z = 0;
	int zz = 0;
	int zzz = 0;
	int pponit=0;
	int tas;
	int tass=0;
	int tas1=0;
	int tas2=0;
	int s = 0;
	int j=0;
	int pagelong;
	static int end=64;
	static int sta=63;
	static int das=0;
	Document doc2;
	private DBOpenHelper dbHelper;
	private ArrayList<Site> sites;
	private int index;
String seachname;
	Site[] site=new Site[200];
	private ProgressBar secondBar = null;
	protected static final int REFRESH_DATA = 0x00000001;
	byte[][] data = new byte[100][];
	byte[][] datat = new byte[100][];
	byte[] data2;
	String com;
	TextView OK;
	private String t1 = "https://sites.google.com/site/hl2dmpc01/pag/logo-ticrf.png";
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
			secondBar.setVisibility(View.GONE);
			OK.setText(com);
			if(why!=1){
			initDB();
			showSites();
			}

		}

	};

	public Viewpage(int c1, int c2, int c3, int c4, int c5, int c6, int c7,
			int c8, int c9, int c10,String text,int pv) {
		this.c1=c1;
		this.c2=c2;
		this.c3=c3;
		this.c4=c4;
		this.c5=c5;
		this.c6=c6;
		this.c7=c7;
		this.c8=c8;
		this.c9=c9;
		this.c10=c10;
		this.seachname=text;
		this.Pvlong=pv;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.comic_fragment,container, false);	
		secondBar = (ProgressBar) fragmentView.findViewById(R.id.pb1);

		OK = (TextView) fragmentView.findViewById(R.id.textView1);

		String msg = "aa";
		com=(String) this.getResources().getText(R.string.WEL);
		Thread t = new Thread(new sendPostRunnable(msg));
		t.start();
		ArrayList<View> pageViews = new ArrayList<View>();
		// 準�?ViewPager每�??�面?�layout
		for (int i = 0; i < Pvlong; i++) {
			View pageView = inflater.inflate(R.layout.comic_page_item, null);
			pageViews.add(pageView);
		}
		final ViewPager viewPager = (ViewPager) fragmentView
				.findViewById(R.id.vp_comic);
		MyPagerAdapter myAdapter = new MyPagerAdapter(pageViews);
		viewPager.setAdapter(myAdapter);
		viewPager.setCurrentItem(0);
		Button bt_first = (Button) fragmentView.findViewById(R.id.bt_first);
		bt_first.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				viewPager.setCurrentItem(0);
			}
		});

		Button bt_last = (Button) fragmentView.findViewById(R.id.bt_last);
		bt_last.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				viewPager.setCurrentItem(Pvlong);

			}
		});
		Button bt_set = (Button) fragmentView.findViewById(R.id.bt_set);
		bt_set.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				FragmentManager DS2 = getFragmentManager();
				FragmentTransaction DSE2 = DS2.beginTransaction();
				Fragment DF2 = DS2.findFragmentById(R.id.frameLayout5);
				if(zz<0){
                    zz=0;
                }
                if (DF2 == null) {
					bookon usermune = new bookon(linkpa[zz]);
					DSE2.add(R.id.frameLayout5, usermune);
					DSE2.addToBackStack(null);
					DSE2.commit();
				}else{
					bookon usermune = new bookon(linkpa[zz]);
					DSE2.replace(R.id.frameLayout5, usermune);
					DSE2.addToBackStack(null);
					DSE2.commit();
				}
			}
		});
		Button bt_next_page = (Button) fragmentView.findViewById(R.id.nextpage);
		bt_next_page.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(pagelong>21){
				pagl=pagl+1;
				FragmentManager DS = getFragmentManager();
				FragmentTransaction DSE = DS.beginTransaction();
					Viewpage2 usermune = new Viewpage2(pagelink,pagl);
					DSE.replace(R.id.flbs, usermune);
					DSE.addToBackStack(null);
					DSE.commit();
				}
			}
		});
		Button bt_prview_page = (Button) fragmentView.findViewById(R.id.prviewpage);
		bt_prview_page.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(pagl>0){
					pagl=pagl-1;
				FragmentManager DS = getFragmentManager();
				FragmentTransaction DSE = DS.beginTransaction();
					Viewpage2 usermune = new Viewpage2(pagelink,pagl);
					DSE.replace(R.id.flbs, usermune);
					DSE.addToBackStack(null);
					DSE.commit();
					}
			}
		});
		return fragmentView;
	}
	
	String sendPostDataToInternet(String strTxt2) {
		try {
			Document doc4 = Jsoup.connect("http://g.e-hentai.org/")
					.data("f_doujinshi",c1+"")
					.data("f_manga",c2+"")
					.data("f_artistcg",c10+"")
					.data("f_gamecg",c4+"")
					.data("f_western",c5+"")
					.data("f_non-h",c6+"")
					.data("f_imageset",c3+"")
					.data("f_cosplay",c7+"")
					.data("f_asianporn",c8+"")
					.data("f_misc",c9+"")
					.data("f_search",seachname)
					.get();
			Elements xs = doc4.select("a[href]");
			Elements xs2 = doc4.select("p");
			for (Element p : xs2) { 
				  String linkText = p.text();	
				  foundText[a]=linkText;
				  a++;
				  }
			if(foundText[3].equals("No hits found")){
				why=1;
				
			}
			a=0;
			Elements xs3 = doc4.select("div.it2");
			for (Element link : xs3) { 
				  String linkHref = link.attr("src");
				  String linkText = link.text();	
				  w[a]=linkText;
				  a++;
				  }
			pagelong=a;
			a=1;
		      int dac = 0;
			for(int i=1;i<pagelong-1;dac++){
		    	  String sub=w[a].substring(sta, end);  
		    	  if(sub.equals("~")){
                    A[a]=w[a].substring(14, end-1);
                    A[a]="http://ehgt.org/"+A[a];
					data[a] = getImage(A[a]);
//					Is[i] = TKs[i].openStream();
//					Bp[i] = BitmapFactory.decodeStream(Is[i]);
//					Is[i].close();
		    		  end=64;
		    		  sta=63;
			    	  a++;
			    	  i++ ;
		    	  }
		    	  end++;
		    	  sta++;
		      }
//			
//			for (Element link : xs2) { 
//				  String linkText = link.text();
//				  if(b==27){
//				  System.out.println(linkText);
//				  }
//				  b++;
//				  		 }
			pagelong=a;
			a=0;
			for (Element link : xs) { 
				  String linkHref = link.attr("href");
				  int textl=linkHref.length();
				  if(textl>=23){
				  String sg=linkHref.substring(22, 23);
				  if(sg.equals("?")){
					  c=c+1;
					  pagelink[c]=linkHref;
				  }
				  if(textl<=44){
				  if(sg.equals("g")){
					  a=a+1;
					  linkpg[a]=linkHref;
					  
				  }
				  }
				  }
				  		 }
			int xas=1;
			for(int i = 0;i<a/2;i++){
				linkpa[i]=linkpg[xas];
				xas=xas+2;
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
		// secondBar.setVisibility(View.GONE);?��??��??�報??
		String strResult = "ss";
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
//	public void onStop(){
//		 super.onStop();
//	}
//	public void onResume(){
//		 super.	onResume();
//	}

	private void initDB() {
		if (dbHelper == null) {
			dbHelper = new DBOpenHelper(this.getActivity());
		}

		List<Site> sites_dummy = null;
		try {
			sites_dummy = createDummyData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbHelper.insertDummyData(sites_dummy);
		sites = dbHelper.getAllSites();
	}

	private List<Site> createDummyData() throws IOException {
		ArrayList<Site> sites_dummy = new ArrayList<Site>();
		Site[] a1 = new Site[pagelong];
		String[] str1 = new String[pagelong];
if(j>0){
	sites_dummy.removeAll(sites_dummy);
}
		for (int b = 0; b < pagelong; b++){
			str1[b]=Integer.toString(b); 
			a1[b] = new Site(str1[b], data[b]);
		sites_dummy.add(a1[b]);
		}
		j=1;
		return sites_dummy;
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

	private void showSites() {
		int t=sites.size();
			for(int a=1;a<t;a++){
			Site site = sites.get(a);
			datat[a] = site.getImage();
			Bp[a] = BitmapFactory.decodeByteArray(datat[a], 0,datat[a].length);
			
		} 
	}

	private class MyPagerAdapter extends PagerAdapter {
		private List<View> pageViews;

		public MyPagerAdapter(List<View> pageViews) {
			this.pageViews = pageViews;
		}

		@Override
		public int getCount() {
			return pageViews.size();
		}

		// 依�?position建�?page
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			int Pvlongs=Pvlong-2;
			if(Pvlongs>=position){
			ImageView imageView = (ImageView) pageViews.get(position)
					.findViewById(R.id.iv_comic);
			imageView.setImageBitmap(Bp[position]);
			container.addView(pageViews.get(position));
			zz = position;
			if(pponit==1){
				tas2=position;
				pponit=0;
			}else if(pponit==0){
            	tas1=position;
            	pponit=1;
			}
			tas=tas1-tas2;
			if(tas==3|tas==-3){
				if(tass==0){
				zzz=2;
				tass=1;
				}else if(tass==1){
					zzz=0;
					tass=0;
					}
			}
			zz=zz+zzz-1;
			String s = Integer.toString(zz);
			Log.i("POSITION", s);
			}
			return pageViews.get(position);
		}

		// 依�?position移除page
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(pageViews.get(position));
		}

		// ?�斷page view?�否?�object(?�instantiateItem()?�傳)
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}
}