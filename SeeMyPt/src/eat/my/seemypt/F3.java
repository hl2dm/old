package eat.my.seemypt;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class F3 extends Fragment {
	String invcheck;
	String invman;
	String singtime;
	String logintime;
	String loginip;
	String btclinet;
	String outputall;
	String torrentA;
	String postA;
	String miage;
	ListView lv1;
	ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	 private SimpleAdapter adapter;
	TextView SG;
	public F3(String string, String string2, String string3, String string4,
			String string5, String string6, String string7, String string8,
			String string9,String string10) {
		this.invcheck= string;
		this.invman= string2;
		this.singtime= string3;
		this.logintime= string4;
		this.loginip= string5;
		this.btclinet= string6;
		this.outputall= string7;
		this.torrentA= string8;
		this.postA= string9;
		this.miage= string10;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.comic_fragment2,
				container, false);
		lv1 = (ListView) fragmentView.findViewById(R.id.listView);
		 final String[] mFoods = new String[] {
			 "邀請資格:"+invcheck,"邀請人:"+invman,"註冊時間:"
						+singtime,"最近登錄:"+logintime,"登錄IP:"+loginip,"登錄設備:"+btclinet+"\n"
						+"總輸出量:"+outputall,"種子評論:"+torrentA,"帖子評論:"+postA,"魔力值:"+miage
			 };
		 for(int i=0; i<9; i++){
		HashMap<String,Object> item = new HashMap<String,Object>();
		 item.put("pic", mPics[i]);
		 item.put( "food", mFoods[i]);
		 list.add( item );
		 }
		 //新增SimpleAdapter
		 adapter = new SimpleAdapter( this.getActivity(), list,R.layout.f,new String[] { "pic","food"},
		 new int[] { R.id.imageView1, R.id.textView1 } );		 
		 //ListActivity設定adapter
		 lv1.setAdapter(adapter);
		 
		 //啟用按鍵過濾功能，這兩行都會進行過濾
		 lv1.setTextFilterEnabled(true);
		 
		return fragmentView;
	}
	private static final int[] mPics=new int[]{
		 R.drawable.p1_01,R.drawable.p1_04,R.drawable.p1_08,R.drawable.p1_11,
		 R.drawable.p1_12,R.drawable.p1_13,R.drawable.p1_15,R.drawable.p1_18,
		 R.drawable.p1_19,R.drawable.p1_02};



	public void onDetach (){
		 super.onDetach();
	}
	public void onStop(){
		 super.onStop();
	}
	
}