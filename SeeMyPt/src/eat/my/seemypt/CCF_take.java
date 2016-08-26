package eat.my.seemypt;
import java.util.ArrayList;
import java.util.HashMap;
import eat.my.seemypt.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class CCF_take extends Fragment {
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
	String well;
	String well2;
	String well3;
	String well4;
	String well5;
	String well6;
	String well7;
	String well8;
	String well9;
	String well10;
	String well11;
	String well12;
	String well13;
	ListView lv1;
	ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
	 private SimpleAdapter adapter;
	TextView SG;
	public CCF_take(String string, String string2, String string3, String string4,
			String string5, String string6, String string7, String string8,
			String string9,String string10,String string11,String string12,String string13,String string14
			,String string15,String string16,String string17,String string18,String string19,String string20
			,String string21,String string22) {
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
		this.well= string11;
		this.well2= string12;
		this.well3= string13;
		this.well4= string14;
		this.well5= string15;
		this.well6= string16;
		this.well7= string17;
		this.well8= string18;
		this.well9= string19;
		this.well11= string20;
		this.well12= string21;
		this.well13= string22;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.comic_fragment2,
				container, false);
		lv1 = (ListView) fragmentView.findViewById(R.id.listView);
		 final String[] mFoods = new String[] {
			 "註冊時間:"+invcheck,"最後登錄:"+invman,"總訪問量:"
						+singtime,"總在現時間:"+logintime,"本周訪問量:"+loginip,"本周在現時間:"+btclinet+"\n"
						+"IP地址:"+outputall,"上傳量:"+torrentA,"下載量:"+postA,"上周上傳量:"+miage,"上周下載量:"
						+well,"積分:"+well2,"分享率:"+well3,"周季分享率:"+well4,"等級:"+well5,"是否冷凍:"+well6,
						"已邀請:"+well10,"種子評論:"+well7,"論壇帖子:"+well8,"下載速度:"+well9,"上傳速度:"+well11,
						"每小時積分"+well12
			 };
		 for(int i=0; i<21; i++){
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
		 R.drawable.p1_19,R.drawable.p1_02, R.drawable.p1_01,R.drawable.p1_04,
		 R.drawable.p1_08,R.drawable.p1_11,R.drawable.p1_02,R.drawable.p1_04,
		 R.drawable.p1_12,R.drawable.p1_13,R.drawable.p1_15,R.drawable.p1_18,
		 R.drawable.p1_19,R.drawable.p1_02};



	public void onDetach (){
		 super.onDetach();
	}
	public void onStop(){
		 super.onStop();
	}
	
}