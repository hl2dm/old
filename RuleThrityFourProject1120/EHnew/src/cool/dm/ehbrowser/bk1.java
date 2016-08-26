package cool.dm.ehbrowser;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class bk1 extends Fragment {

	private EditText txtMessage;
	private TextView tv;
	ListView mainListView;
	Integer[] planets = new Integer[] {};
	private Button sendBtn;
	StringBuffer sff2 = new StringBuffer();
	StringBuffer sff = new StringBuffer();
	String myString = null;
	ArrayAdapter<Bitmap> listAdapter;
	private ImageView mImageView;
	private URL mURL = null;
	private String SX;
	private int i = 1;
	private Bitmap mBitmap = null;
	private InputStream mInputStream = null;
	protected static final int REFRESH_DATA2 = 0x00000002;
	private String uriAPI = "http://g.e-hentai.org/";
	String[] TKG = new String[200];
	URL[] TKs = new URL[200];
	Bitmap[] Bp = new Bitmap[200];
	InputStream[] Is = new InputStream[200];
	String[] TKA = new String[200];
	String[] TKB = new String[200];
	String[] TKC = new String[200];
	String[] TKD = new String[200];
	String[] TKE = new String[200];
	String[] onebook = new String[300];
	String uul;
	String title1;
	StringBuffer[] GGG = new StringBuffer[200];
	StringBuffer[] GG = new StringBuffer[200];
	int ak = 0;
	int akk = 0;
	int g = 0;
	int x = 0;
	int n = 0;
	int h = 0;
	int z = 0;
	Document doc;
	private ProgressBar secondBar = null;
	private ListView mainListView2;
	protected static final int REFRESH_DATA = 0x00000001;
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

	};

	public bk1(String title) {

		this.title1 = title;

	}

	public bk1() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.bk1, container, false);
		mainListView2 = (ListView) view.findViewById(R.id.listView2);
		secondBar = (ProgressBar) view.findViewById(R.id.progressBar1);
		String msg = "aa";
		Thread t = new Thread(new sendPostRunnable(msg));
		t.start();
		mainListView2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position > 1) {
					int xs = position;
					uul = TKE[xs];

					see();
				}

			}

			private void see() {
				FragmentManager DS2 = getFragmentManager();
				FragmentTransaction DSE2 = DS2.beginTransaction();
				Fragment DF2 = DS2.findFragmentById(R.id.frameLayout3);
				if (DF2 == null) {
					bookon usermune = new bookon(uul);
					DSE2.add(R.id.frameLayout3, usermune);
					DSE2.addToBackStack(null);
					DSE2.commit();
				}

			}

		});
		return view;
	}

	String sendPostDataToInternet(String strTxt2) {
		try {
			doc = Jsoup
					.connect(
							"http://g.e-hentai.org/?f_apply=Apply+Filter&f_search=language:chinese")
					.get();
			Elements asd = doc.select("div.it2");
			Log.i("cc", "s");

			for (Element asds : asd) {
				GGG[i] = new StringBuffer();
				GGG[i].append(asds.attr("abs:href")).append("x")
						.append(asds.text());
				TKG[i] = GGG[i].toString();
				TKG[i] = TKG[i].replaceAll("xinit~ehgt.org~t",
						"http://ehgt.org/t");
				TKG[i] = TKG[i].replaceAll("~ ", "");
				TKG[i] = TKG[i].replaceAll(" ~", "");
				if (TKG[i].equals("x")) {
					TKG[i] = "https://sites.google.com/site/hl2dmpc01/pag/logo-ticrf.png~l";
				}

				ak = 0;
				g = 0;
				String[] TT = new String[TKG[i].length()];
				for (int z = 0; z < TKG[i].length() - 1; z++) {
					TT[z] = TKG[i].substring(z, z + 1);
				}
				int AA = TT.length;
				AA = AA - 1;

				for (int z = 0; z < AA; z++) {
					ak = ak + 1;
					if (TT[z].equals("~")) {
						akk = akk + 1;
						TKA[akk] = "";
						for (int x = 0; x < ak - 1; x++) {
							TKA[akk] = TKA[akk] + TT[g];
							g = g + 1;
						}
						ak = 0;
						g = g + 1;
					}

				}
				TKs[i] = new URL(TKA[i]);
				Is[i] = TKs[i].openStream();
				Bp[i] = BitmapFactory.decodeStream(Is[i]);
				Is[i].close();
				i = i + 1;
				String s = Integer.toString(i);
				Log.i("cc", s);
			}
			Elements xsj = doc.select("a[href]");
			for (Element asdz : xsj) {
				GG[h] = new StringBuffer();
				GG[h].append(asdz.attr("href"));
				TKG[h] = GG[h].toString();
				if (TKG[h].length() > 23) {
					String g = TKG[h].substring(22, 24);
					if (g.equals("g/")) {
						TKD[z] = TKG[h];
						z = z + 1;
					}

				}
				h = h + 1;
			}
			int s = 0;
			for (int x = 0; x < 49; x = x + 2) {
				TKE[s] = TKD[x];
				s = s + 1;

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	private void re() {

		List<HashMap<String, Object>> mListData = getListData();

		SimpleAdapter adapter = new SimpleAdapter(getActivity(), mListData,
				R.layout.list_item, new String[] { "ing" },
				new int[] { R.id.image });
		adapter.setViewBinder(new ViewBinder() {

			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				if (view instanceof ImageView && data instanceof Bitmap) {
					ImageView iv = (ImageView) view;

					iv.setImageBitmap((Bitmap) data);
					return true;
				} else
					return false;
			}
		});

		mainListView2.setAdapter(adapter);

		secondBar.setVisibility(View.GONE);

	}

	public List<HashMap<String, Object>> getListData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = null;
		for (int i = 0; i < 20; i++) {
			map = new HashMap<String, Object>();
			map.put("ing", Bp[i]);
			list.add(map);
		}
		return list;
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
