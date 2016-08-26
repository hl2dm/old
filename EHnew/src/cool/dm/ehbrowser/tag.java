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

public class tag extends Fragment {
	protected static final int REFRESH_DATA = 0x00000001;
	Document doc2;
	String  posttext;
	public tag() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.female, container, false);
		
		Button bik_btn=(Button) view.findViewById(R.id.bike_shorts_btn);
		bik_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				posttext="female:bike shorts"; 
				post();
			}
		});	
		Button sw_btn=(Button) view.findViewById(R.id.swimsuit_btn);
		sw_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				posttext="female:swimsuit"; 
				post();
			}
		});	
		Button biki_btn=(Button) view.findViewById(R.id.bikini_btn);
		biki_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				posttext="female:bikini"; 
				post();
			}
		});	
		Button r_btn=(Button) view.findViewById(R.id.rape_btn);
		r_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				posttext="female:rape"; 
				post();
			}
		});		
		Button bb_btn=(Button) view.findViewById(R.id.big_breasts_btn);
		bb_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				posttext="female:big_breasts"; 
				post();
			}
		});		
		return view;
	}
	

	private void post() {
		FragmentManager DS = getFragmentManager();
		FragmentTransaction DSE = DS.beginTransaction();
		Fragment DFC = DS.findFragmentById(R.id.flbs);
		if (DFC == null) {
			Viewpage_lung usermune = new Viewpage_lung(posttext);
			DSE.replace(R.id.flbs, usermune);
			DSE.addToBackStack(null);
			DSE.commit();
		}else{
			Viewpage_lung usermune = new Viewpage_lung(posttext);
			DSE.replace(R.id.flbs, usermune);
			DSE.addToBackStack(null);
			DSE.commit();
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
