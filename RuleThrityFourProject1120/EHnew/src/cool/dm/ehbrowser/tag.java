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
