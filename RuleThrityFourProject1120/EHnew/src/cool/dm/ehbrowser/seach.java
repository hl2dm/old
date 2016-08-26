package cool.dm.ehbrowser;

import android.content.Intent;
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

public class seach extends Fragment {
	Button Lbtn;
	Button Tbtn;
	Button Sbtn;

	public seach() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.set1, container, false);
//		FragmentManager DS2 = getFragmentManager();
//		FragmentTransaction DSE2 = DS2.beginTransaction();
//		Fragment DF2 = DS2.findFragmentById(R.id.flbs);
//		if (DF2 != null) {
//		DSE2.remove(DF2);
//		DSE2.addToBackStack(null);
//		DSE2.commit();
//	}
//	FragmentManager DS = getFragmentManager();
//	FragmentTransaction DG = DS.beginTransaction();
//	Fragment DG2 = DS.findFragmentById(R.id.frameLayout5);
//	if (DG2 != null) {
//		DG.remove(DG2);
//		DG.addToBackStack(null);
//		DG.commit();
//	}
		Sbtn = (Button) view.findViewById(R.id.seachbtn);
		Tbtn = (Button) view.findViewById(R.id.TAGbtn);
		Lbtn = (Button) view.findViewById(R.id.LUGbtn);
		Lbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager DS2 = getFragmentManager();
				FragmentTransaction DSE2 = DS2.beginTransaction();
				Fragment DF2 = DS2.findFragmentById(R.id.flbs);
				if (DF2 == null) {
					luange usermune = new luange();
					DSE2.add(R.id.flbs, usermune);
					DSE2.addToBackStack(null);
					DSE2.commit();
				}else{
					luange usermune = new luange();
					DSE2.replace(R.id.flbs, usermune);
					DSE2.addToBackStack(null);
					DSE2.commit();
				}

			}
		});
		Tbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager DS2 = getFragmentManager();
				FragmentTransaction DSE2 = DS2.beginTransaction();
				Fragment DF2 = DS2.findFragmentById(R.id.flbs);
				if (DF2 == null) {
					tag usermune = new tag();
					DSE2.add(R.id.flbs, usermune);
					DSE2.addToBackStack(null);
					DSE2.commit();
				}else{
					tag usermune = new tag();
					DSE2.replace(R.id.flbs, usermune);
					DSE2.addToBackStack(null);
					DSE2.commit();
				}

			}
		});
		Sbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				FragmentManager DS2 = getFragmentManager();
//				FragmentTransaction DSE2 = DS2.beginTransaction();
//				Fragment DF2 = DS2.findFragmentById(R.id.flbs);
//				if (DF2 == null) {
//					seachset usermune = new seachset();
//					DSE2.add(R.id.flbs, usermune);
//					DSE2.addToBackStack(null);
//					DSE2.commit();
//				}else{
//					seachset usermune = new seachset();
//					DSE2.replace(R.id.flbs, usermune);
//					DSE2.addToBackStack(null);
//					DSE2.commit();
//				}
                Intent intent = new Intent(getActivity(), cool.dm.ehbrowser.seachs.class);
                seach.this.startActivity(intent);
			}
		});

		return view;
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
