//package dm.book.rulethirtyfour.ehbrowser;
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import org.apache.http.ParseException;
//import org.browseData_blob.DBOpenHelper;
//import org.browseData_blob.Site;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class News extends Fragment {
//	String[] TKG = new String[300];
//	URL[] TKs = new URL[200];
//	Bitmap[] Bp = new Bitmap[17];
//	InputStream[] Is = new InputStream[200];
//	String[] TKA = new String[200];
//	String[] TKB = new String[200];
//	String[] TKC = new String[200];
//	String[] TKD = new String[200];
//	String[] TKE = new String[15];
//	String[] onebook = new String[300];
//	String uul;
//	String title1;
//	StringBuffer[] GGG = new StringBuffer[200];
//	StringBuffer[] GG = new StringBuffer[300];
//	int ak = 0;
//	int akk = 0;
//	int g = 0;
//	int n = 0;
//	int i = 1;
//	int h = 0;
//	int z = 0;
//	int zz = 0;
//	int zzz = 0;
//    int zzzz = 0;
//    int st=1;
//	int pponit=0;
//	int tas;
//	int tass=0;
//	int tas1=0;
//	int tas2=0;
//	int s = 0;
//	int j=0;
//	Document doc2;
//	private DBOpenHelper dbHelper;
//	private ArrayList<Site> sites;
//	private int index;
//	Site[] site=new Site[200];
//	private ProgressBar secondBar = null;
//	protected static final int REFRESH_DATA = 0x00000001;
//	byte[][] data = new byte[100][];
//	byte[][] datat = new byte[100][];
//	byte[] data2;
//	String com;
//	TextView OK;
//    ViewPager viewPager =null;
//	private String t1 = "https://sites.google.com/site/hl2dmpc01/pag/logo-ticrf.png";
//	Handler mHandler = new Handler()
//
//	{
//
//		@Override
//		public void handleMessage(Message msg)
//
//		{
//
//			switch (msg.what)
//
//			{
//
//			// 顯示網路上�??��?資�?
//
//			case REFRESH_DATA:
//				String result = null;
//				if (msg.obj instanceof String)
//					result = (String) msg.obj;
//				if (result != null)
//					re();
//
//				break;
//
//			}
//
//		}
//
//		private void re() {
//			secondBar.setVisibility(View.GONE);
//			OK.setText(com);
//			initDB();
//			showSites();
//            viewPager.setCurrentItem(1);
//		}
//
//	};
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View fragmentView = inflater.inflate(R.layout.comic_fragment,container, false);
//		secondBar = (ProgressBar) fragmentView.findViewById(R.id.pb1);
//		View v = fragmentView.findViewById(R.id.prviewpage);//找到你要设透明背景的layout 的id
//		v.getBackground().setAlpha(0);//透明度0~255透明度值 ，值越小越透明
//		View v2 = fragmentView.findViewById(R.id.nextpage);//找到你要设透明背景的layout 的id
//		v2.getBackground().setAlpha(0);//透明度0~255透明度值 ，值越小越透明
//		OK = (TextView) fragmentView.findViewById(R.id.textView1);
//		String msg = "aa";
//		com=(String) this.getResources().getText(R.string.WEL);
//		Thread t = new Thread(new sendPostRunnable(msg));
//		t.start();
//
//		ArrayList<View> pageViews = new ArrayList<View>();
//		// 準�?ViewPager每�??�面?�layout
//		for (int i = 0; i < Bp.length; i++) {
//			View pageView = inflater.inflate(R.layout.comic_page_item, null);
//			pageViews.add(pageView);
//		}
//		viewPager = (ViewPager) fragmentView
//				.findViewById(R.id.vp_comic);
//		MyPagerAdapter myAdapter = new MyPagerAdapter(pageViews);
//		viewPager.setAdapter(myAdapter);
//
//		Button bt_first = (Button) fragmentView.findViewById(R.id.bt_first);
//		bt_first.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				viewPager.setCurrentItem(1);
//			}
//		});
//
//		Button bt_last = (Button) fragmentView.findViewById(R.id.bt_last);
//		bt_last.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				viewPager.setCurrentItem(16);
//			}
//		});
//		Button bt_set = (Button) fragmentView.findViewById(R.id.bt_set);
//		bt_set.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				FragmentManager DS2 = getFragmentManager();
//				FragmentTransaction DSE2 = DS2.beginTransaction();
//				Fragment DF2 = DS2.findFragmentById(R.id.frameLayout5);
//				if (DF2 == null) {
////					zz = zz - 1;
//					if(zz>14){
//						zz=14;
//					}
//					bookon usermune = new bookon(TKE[zz]);
//					DSE2.add(R.id.frameLayout5, usermune);
//					DSE2.addToBackStack(null);
//					DSE2.commit();
//				}else{
//					bookon usermune = new bookon(TKE[zz]);
//					DSE2.replace(R.id.frameLayout5, usermune);
//					DSE2.addToBackStack(null);
//					DSE2.commit();
//				}
//			}
//		});
//		// Button reflash = (Button) fragmentView.findViewById(R.id.btn_ref);
//		// reflash.setOnClickListener(new OnClickListener() {
//		// public void onClick(View v) {
//		//
//		// i=1;
//		// }
//		// });
//
//		return fragmentView;
//	}
//
//	String sendPostDataToInternet(String strTxt2) {
//		try {
//			doc2 = Jsoup.connect("http://g.e-hentai.org/").get();
//			// System.out.print(doc2);
//            Elements xsj = doc2.select("a[href]");
//
//            for (Element asdz : xsj) {
//                GGG[h] = new StringBuffer();
//                GGG[h].append(asdz.attr("href"));
//                TKG[h] = GGG[h].toString();
//                if (TKG[h].length() > 23) {
//                    String g = TKG[h].substring(22, 24);
//                    if (g.equals("g/")) {
//
//                        TKD[z] = TKG[h];
//
//                        if(z>25){
//                            if(st==1){
//                                st=0;
//                                TKE[zzzz] =TKD[z];
//                                zzzz++;
//                            }else {
//                            if(!TKE[zzzz-1].equals(TKD[z])){
//                            TKE[zzzz] =TKD[z];
//                            zzzz++;
//                            }
//                            }
//                        }
//                        z = z + 1;
//                    }
//                }
//                h = h + 1;
//            }
//			Elements xs = doc2.select("img");
//			i=0;
//			for (Element asds : xs) {
//				GGG[i] = new StringBuffer();
//				GGG[i].append(asds.attr("src"));
//				TKG[i] = GGG[i].toString();
//				onebook[i] = TKG[i].substring(16, 17);
//
//				if (onebook[i].equals("t")) {
//					TKs[i] = new URL(TKG[i]);
//					data[i] = getImage(TKG[i]);
////					Is[i] = TKs[i].openStream();
////					Bp[i] = BitmapFactory.decodeStream(Is[i]);
////					Is[i].close();
//					i = i + 1;
//				}
//
//				String s = Integer.toString(i);
//				Log.i("cc", s);
//			}
//			TKs[0] = new URL(
//					"https://e85c89b2-a-62cb3a1a-s-sites.googlegroups.com/site/hl2dmpc01/pag/12z.jpg");
//			Is[0] = TKs[0].openStream();
//			Bp[0] = BitmapFactory.decodeStream(Is[0]);
//			Is[0].close();
//			TKs[1] = new URL(
//					"https://e85c89b2-a-62cb3a1a-s-sites.googlegroups.com/site/hl2dmpc01/pag/12z.jpg");
//			Is[1] = TKs[1].openStream();
//			Bp[1] = BitmapFactory.decodeStream(Is[1]);
//			Is[1].close();
//			// ///////////////////////////////////////////////////////////////////////////////////////////////////
//
//		}
//
//		catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// secondBar.setVisibility(View.GONE);?��??��??�報??
//		String strResult = "ss";
//		return strResult;
//	}
//
//	class sendPostRunnable implements Runnable {
//		String strTxt = null;
//
//		// 建�?子�?設�?要傳?��?�?
//		public sendPostRunnable(String strTxt) {
//			this.strTxt = strTxt;
//		}
//
//		@Override
//		public void run() {
//
//			String result = sendPostDataToInternet(strTxt);
//
//			mHandler.obtainMessage(REFRESH_DATA, result).sendToTarget();
//
//		}
//
//	}
//	public void onStop(){
//		 super.onStop();
//	}
//	public void onResume(){
//		 super.	onResume();
//	}
//
//	private void initDB() {
//		if (dbHelper == null) {
//			dbHelper = new DBOpenHelper(this.getActivity());
//		}
//
//		List<Site> sites_dummy = null;
//		try {
//			sites_dummy = createDummyData();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		dbHelper.insertDummyData(sites_dummy);
//		sites = dbHelper.getAllSites();
//	}
//
//	private List<Site> createDummyData() throws IOException {
//		ArrayList<Site> sites_dummy = new ArrayList<Site>();
//		Site[] a1 = new Site[16];
//		String[] str1 = new String[16];
//if(j>0){
//	sites_dummy.removeAll(sites_dummy);
//}
//		for (int b = 0; b < i; b++){
//			str1[b]=Integer.toString(b);
//			a1[b] = new Site(str1[b], data[b]);
//		sites_dummy.add(a1[b]);
//		}
//		j=1;
//		return sites_dummy;
//	}
//
//	public byte[] getImage(String t2) throws Exception {
//		URL url = new URL(t2);
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		conn.setConnectTimeout(5 * 1000);
//		conn.setRequestMethod("GET");
//		InputStream inStream = conn.getInputStream();
//		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//			return readStream(inStream);
//		}
//		return null;
//	}
//
//	public static byte[] readStream(InputStream inStream) throws Exception {
//		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//		byte[] buffer = new byte[1024];
//		int len = 0;
//		while ((len = inStream.read(buffer)) != -1) {
//			outStream.write(buffer, 0, len);
//		}
//		outStream.close();
//		inStream.close();
//		return outStream.toByteArray();
//	}
//
//	private void showSites() {
//		int t=sites.size();
//		//1
//			for(int a=1;a<t;a++){
//			Site site = sites.get(a);
//			datat[a] = site.getImage();
//			Bp[a] = BitmapFactory.decodeByteArray(datat[a], 0,datat[a].length);
//
//		}
//	}
//
//	private class MyPagerAdapter extends PagerAdapter {
//		private List<View> pageViews;
//
//		public MyPagerAdapter(List<View> pageViews) {
//			this.pageViews = pageViews;
//		}
//
//		@Override
//		public int getCount() {
//			return pageViews.size();
//		}
//
//		// 依�?position建�?page
//		@Override
//		public Object instantiateItem(ViewGroup container, int position) {
//			ImageView imageView = (ImageView) pageViews.get(position)
//					.findViewById(R.id.iv_comic);
//			imageView.setImageBitmap(Bp[position]);
//			container.addView(pageViews.get(position));
//			zz = position;
//
//			if(pponit==1){
//				tas2=position;
//				pponit=0;
//			}else if(pponit==0){
//            	tas1=position;
//            	pponit=1;
//			}
//			tas=tas1-tas2;
//			if(tas==3|tas==-3){
//				if(tass==0){
//				zzz=2;
//				tass=1;
//				}else if(tass==1){
//					zzz=0;
//					tass=0;
//					}
//			}
//			zz=zz+zzz-2;
//			String s = Integer.toString(zz);
//			Log.i("POSITION", s);
//			return pageViews.get(position);
//		}
//
//		// 依�?position移除page
//		@Override
//		public void destroyItem(ViewGroup container, int position, Object object) {
//			container.removeView(pageViews.get(position));
//		}
//
//		// ?�斷page view?�否?�object(?�instantiateItem()?�傳)
//		@Override
//		public boolean isViewFromObject(View view, Object object) {
//			return view == object;
//		}
//	}
//}