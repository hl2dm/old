package cool.dm.ehbrowser;

import cool.dm.ehbrowser.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleAdapter.ViewBinder;

public class seachset extends Fragment {
	protected static final int REFRESH_DATA = 0x00000001;
	int a=0;
	int b=0;
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
	int pagelong;
	String seachText;
	private static String w[]=new String [300];
	private EditText seachname;
	private CheckBox f_doujinshi;
	private CheckBox f_manga;
	private CheckBox f_artistcg;
	private CheckBox f_gamecg;
	private CheckBox f_western;
	private CheckBox f_non_h;
	private CheckBox f_imageset;
	private CheckBox f_cosplay;
	private CheckBox f_asianporn;
	private CheckBox f_misc;
	Button S;
	Document doc2;
	String  linkO[]=new String[200];
	String foundText[]=new String[200];
	public seachset() {

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
			FragmentManager DS = getFragmentManager();
			FragmentTransaction DSE = DS.beginTransaction();
			Fragment DFC = DS.findFragmentById(R.id.flbs);
//			if (DFC == null) {
			
				Viewpage usermune = new Viewpage(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,seachText,pagelong);
				DSE.add(R.id.flbs, usermune);
				DSE.addToBackStack(null);
				DSE.commit();
//			}
			

		}

	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.seach, container, false);
		S=(Button) view.findViewById(R.id.buttonS);
		seachname=(EditText) view.findViewById(R.id.editText1);
		f_doujinshi=(CheckBox)view.findViewById(R.id.doujinshi);
		f_manga=(CheckBox)view.findViewById(R.id.manga);
		f_gamecg=(CheckBox)view.findViewById(R.id.gamecg);
		f_western=(CheckBox)view.findViewById(R.id.westrn);
		f_non_h=(CheckBox)view.findViewById(R.id.non_h);
		f_imageset=(CheckBox)view.findViewById(R.id.imageset);
		f_cosplay=(CheckBox)view.findViewById(R.id.cosplay);
		f_asianporn=(CheckBox)view.findViewById(R.id.asianporn);
		f_misc=(CheckBox)view.findViewById(R.id.misc);
		f_artistcg=(CheckBox)view.findViewById(R.id.artistcg);
		f_doujinshi.setOnCheckedChangeListener(listener);
		f_manga.setOnCheckedChangeListener(listener);
		f_imageset.setOnCheckedChangeListener(listener);
		f_gamecg.setOnCheckedChangeListener(listener);
		f_western.setOnCheckedChangeListener(listener);
		f_non_h.setOnCheckedChangeListener(listener);
		f_cosplay.setOnCheckedChangeListener(listener);
		f_asianporn.setOnCheckedChangeListener(listener);
		f_misc.setOnCheckedChangeListener(listener);
		f_artistcg.setOnCheckedChangeListener(listener);
		S.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				seachText=seachname.getText().toString();
				String msg = "aa";
				Thread t = new Thread(new sendPostRunnable(msg));
				t.start();

			}
		});	
		return view;
	}
	
	CheckBox.OnCheckedChangeListener listener=new CheckBox.OnCheckedChangeListener()
	{
	public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
	{
	if(f_doujinshi.isChecked()==true){
		c1=1;
	}
	if(f_manga.isChecked()==true){
		c2=1;
	}
    if(f_imageset.isChecked()==true){
    	c3=1;
    }
    	if(f_gamecg.isChecked()==true){
    		c4=1;
    	}
    		if(f_western.isChecked()==true){
    			c5=1;
    		}
    			if(f_non_h.isChecked()==true){
    				c6=1;
    			}
    				if(f_cosplay.isChecked()==true){
    					c7=1;
    				}
    					if(f_asianporn.isChecked()==true){
    						c8=1;
    					}
    						if(f_misc.isChecked()==true){
    							c9=1;  							
	}
    						if( f_artistcg.isChecked()==true){
    							c10=1;
    						}
    							 
	}
	};

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
					.data("f_search",seachText)
					.get();
			Elements xs = doc4.select("a[href]");
			Elements xs2 = doc4.select("p");
			for (Element p : xs2) { 
				  String linkText = p.text();	
				  foundText[a]=linkText;
				  a++;
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
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onStart() {
		super.onStart();

	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();

	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	public static Fragment findFragmentById(int framelayout) {
		// TODO Auto-generated method stub
		return null;
	}

}
