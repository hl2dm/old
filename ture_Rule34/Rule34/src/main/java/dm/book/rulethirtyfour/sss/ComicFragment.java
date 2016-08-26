package dm.book.rulethirtyfour.sss;//package saa.assf.sss;
//
//import cool.dm.ehbrowser.R;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import org.apache.http.ParseException;
//import org.browseData_blob.DBOpenHelper;
//import org.browseData_blob.Site;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.content.res.Resources;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.support.v4.view.PagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.SimpleAdapter;
//import android.widget.SimpleAdapter.ViewBinder;
//
//public class ComicFragment extends Fragment {
//	 String[] TKG = new String[200];
//		URL[] TKs = new URL[200];
//		Bitmap [] Bp = new Bitmap[26];
//		InputStream [] Is=new InputStream[200];
//		String[] TKA = new String[200];
//		String[] TKB = new String[200];
//		String[] TKC = new String[200];
//		String[] onebook = new String[300];
//		private DBOpenHelper dbHelper;
//		private ArrayList<Site> sites;
//		private int index;
//		String uul;
//		String title1;
//		StringBuffer[] GGG = new StringBuffer[200];
//		int ak=0;
//		int akk=0;
//		int g=0;
//		int n=0;
//		int i=1;
//		byte[] data;
//		Document doc;
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		View fragmentView = inflater.inflate(R.layout.comic_fragment,
//				container, false);
//		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//	        .detectDiskReads()
//	        .detectDiskWrites()
//	        .detectNetwork()   // or .detectAll() for all detectable problems
//	        .penaltyLog()
//	        .build());
//		 initDB();
//			showSites(index);
//		 try
//			{
//			 Document doc = Jsoup.connect("http://g.e-hentai.org/?f_apply=Apply+Filter&f_search=language:chinese").get();
//						  Elements asd = doc.select("div.it2");
//						  Log.i("cc","s");
//				  for(Element asds : asd){
//					  GGG[i]=new StringBuffer();
//					  GGG[i].append(asds.attr("abs:href")).append("x").append(asds.text());
//					  TKG[i]= GGG[i].toString();
//					  TKG[i]=TKG[i].replaceAll("xinit~ehgt.org~t","http://ehgt.org/t");
//					  TKG[i]=TKG[i].replaceAll("~ ","");
//					  TKG[i]=TKG[i].replaceAll(" ~","");
//					  if(TKG[i].equals("x")){
//				TKG[i]="https://sites.google.com/site/hl2dmpc01/pag/logo-ticrf.png~l";
//					  }
//					  
//					  ak=0;
//					  g=0;
//						String[] TT = new String[TKG[i].length()];
//						for (int z = 0; z < TKG[i].length()-1; z++)
//						{
//						  TT[z] = TKG[i].substring(z, z+1);
//						}
//				      int AA=TT.length;
//				      AA=AA-1;
//
//						for (int z = 0; z < AA; z++)
//						{
//						ak=ak+1;
//						  if (TT[z].equals("~"))
//						  {akk=akk+1;
//							  TKA[akk]="";
//						    for (int x = 0; x < ak-1; x++)
//						    {
//						    	TKA[akk]=TKA[akk]+TT[g];
//						    	g=g+1;
//						    }
//						    ak=0;
//						    g=g+1;
//						  }
//						  
//						  }
//						  TKs[i] = new URL(TKA[i]);
//						  Is[i] = TKs[i].openStream();
//						  Bp[i] = BitmapFactory.decodeStream(Is[i]);
//						  Is[i].close();
//					  i=i+1;
//					  String s = Integer.toString(i); 
//					  Log.i("cc",s);
//						}
//				  TKs[0] = new URL("https://e85c89b2-a-62cb3a1a-s-sites.googlegroups.com/site/hl2dmpc01/pag/12z.jpg");
//				  Is[0] = TKs[0].openStream();
//				  Bp[0] = BitmapFactory.decodeStream(Is[0]);
//				  Is[0].close();
//				  
//		      }
//		      catch (ParseException e) {
//		  		// TODO Auto-generated catch block
//		  		e.printStackTrace();
//		  	} catch (IOException e) {
//		  		// TODO Auto-generated catch block
//		  		e.printStackTrace();
//		  	}
//		
//		final ArrayList<View> pageViews = new ArrayList<View>();
//		// 準�?ViewPager每�??�面?�layout
//		for (int i = 0; i < Bp.length; i++) {
//			View pageView = inflater.inflate(R.layout.comic_page_item, null);
//			pageViews.add(pageView);
//		}
//		final ViewPager viewPager = (ViewPager) fragmentView
//				.findViewById(R.id.vp_comic);
//		MyPagerAdapter myAdapter = new MyPagerAdapter(pageViews);
//		viewPager.setAdapter(myAdapter);
//
//		Button bt_first = (Button) fragmentView.findViewById(R.id.bt_first);
//		bt_first.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				viewPager.setCurrentItem(0);
//			}
//		});
//
//		Button bt_last = (Button) fragmentView.findViewById(R.id.bt_last);
//		bt_last.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				viewPager.setCurrentItem(pageViews.size() - 1);
//			}
//		});
//		
//		return fragmentView;
//	}
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
//	private List<Site> createDummyData() throws IOException {
//		ArrayList<Site> sites_dummy = new ArrayList<Site>();
//		Resources resources = getResources();
//			Site site1 = new Site("yangmingshan", data);			
//			sites_dummy.add(site1);
//		return sites_dummy;
//	}
//		 public byte[] getImage(String t2) throws Exception{  
//		        URL url = new URL(t2);  
//		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
//		        conn.setConnectTimeout(5 * 1000);  
//		        conn.setRequestMethod("GET");  
//		        InputStream inStream = conn.getInputStream();  
//		        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){  
//		            return readStream(inStream);  
//		        }  
//		        return null;  
//		    }  
//		 public static byte[] readStream(InputStream inStream) throws Exception{  
//		        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
//		        byte[] buffer = new byte[1024];  
//		        int len = 0;  
//		        while( (len=inStream.read(buffer)) != -1){  
//		            outStream.write(buffer, 0, len);  
//		        }  
//		        outStream.close();  
//		        inStream.close();  
//		        return outStream.toByteArray();  
//		    }  
//			private void showSites(int index) {
//				if (sites.size() > 0) {
//					Site site = sites.get(index);
//					byte[] image = site.getImage();
//					Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,image.length);
//				} else {
//				}
//			}
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
// }