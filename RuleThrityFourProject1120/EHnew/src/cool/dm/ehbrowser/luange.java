package cool.dm.ehbrowser;

import org.jsoup.nodes.Document;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class luange extends Fragment {
	protected static final int REFRESH_DATA = 0x00000001;
	Document doc2;
	String  posttext;
	public luange() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.luang, container, false);
		
		Button k_btn=(Button) view.findViewById(R.id.korean_btn);
		k_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				posttext="language:korea"; 
				post();
			}
		});	
		Button c_btn=(Button) view.findViewById(R.id.chinese_btn);
		c_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				posttext="language:chinese"; 
				post();
			}
		});	
		Button e_btn=(Button) view.findViewById(R.id.english_btn);
		e_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				posttext="language:english"; 
				post();
			}
		});	
		Button r_btn=(Button) view.findViewById(R.id.russian_btn);
		r_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				posttext="language:russian"; 
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
