package simple;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import browseData_blob.DBOpenHelper;
import browseData_blob.Site;
import dm.fast.partykings.R;
import dm.fast.partykings.stau;

public class news extends ListActivity {
    private ArrayList<Site> sites;
    private DBOpenHelper dbHelper;
    private List<Map<String, Object>> mData;
    int Day[]=new int[200];
    int Mon[]=new int[200];
    int Min[]=new int[200];
    int Hos[]=new int[200];
    String a;
    String ID[]=new  String[200];
    String Where[]=new  String[200];
    String Page[]=new  String[200];
    String vale[]= new String[200];
    String title[]= new String[200];
    int x;
    int check;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        if (dbHelper == null) {
//            dbHelper = new DBOpenHelper(this);
//        }
//        sites = dbHelper.getAllSites();
//        check=sites.size();
//        if(check>0){
//            showSites();
//        }
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
    }


    private void showSites() {
        int a=0;
        int t=sites.size();
        for(int i=1;i<=check;i++){
            Site site = sites.get(a);
            Day[i] = site.getDay();
            Mon[i] = site.getMon();
            vale[i] = site.getJob();
            ID[i]=site.getId();
            title[i] = site.getTitle();
            Hos[i] = site.getHos();
            Min[i]=site.getMin();
            Where[i]=site.getWhere();
            Page[i]=site.getPage();
            a=a+1;
        }
    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "月");
        map.put("info", "日");
        map.put("infoo","內容");
        map.put("iifo","抬頭");
        map.put("num","a");
        map.put("time","時間");
        map.put("img", R.drawable.a2);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("info","內容:"+"東區暢飲店MIRROR最新優惠暢飲女生185暢飲另有學生壽星特優惠價"+"\n"+"地點:"+"Mirror Lounge");
        map.put("title","日期:"+"2013-04-08~ 2013-12-30");
        map.put("time","時間:"+"19:00:00至05:00:00");
        map.put("iifo","標題:"+"最新優惠暢飲女生185暢飲另有學生壽星特優惠價");
        map.put("img", R.drawable.p1);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("info","內容:"+"東區暢飲夜店 緋聞lounge bar （緋沸洋洋）持非本國籍身份證（護照） 平日十點前可享無限暢飲"+"\n"+"地點:"+"緋聞Lounge & Pub");
        map.put("title","日期:"+"2013-03-25~ 2013-08-31");
        map.put("time","時間:"+"19:00:00至03:00:00");
        map.put("iifo","標題:"+"持非本國籍身份證（護照） 平日十點前可享無限暢飲");
        map.put("img", R.drawable.p4);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("info","內容:"+"Zunk CLUB 就甘心!!讓你（妳）省省荷包 壽星生日前後七天免費入場!! 並可攜五位女性同時免費入場唷!! 快快分享給朋友吧!!! 這麼好的優惠 還不趕快分享給朋友知道？那就太不夠朋友了？"+"\n"+"地點:"+"Club Zunk Taipei");
        map.put("title","日期:"+"2012-07-04~ 2013-07-31");
        map.put("time","時間:"+"19:00:00至05:00:00");
        map.put("iifo","標題:"+"【Zunk Club】生日獨享優惠");
        map.put("img", R.drawable.p5);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("info","內容:"+"當日女性穿著低胸服是(鎖骨15公分)入場 可享290元無限暢飲優惠價"+"\n"+"地點:"+"85Lounge");
        map.put("title","日期:"+"2013-06-30~ 2013-12-30");
        map.put("time","時間:"+"19:00:00至05:00:00");
        map.put("iifo","標題:"+"每日特惠活動!假日事業線290元無限暢飲");
        map.put("img", R.drawable.p12);
        list.add(map);


        return list;
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        ListView listView = (ListView) arg0;
        Toast.makeText(
                news.this,
                "ID" + arg3 +
                        "name"+ listView.getItemAtPosition(arg2).toString(),
                Toast.LENGTH_LONG).show();
    };



    /**
     * listview嚙踝蕭嚙瘢嚙踝蕭嚙踝蕭嚙編嚙諂出嚙踝蕭飫嚙?
     */
    public void showInfo(int position) {
        new AlertDialog.Builder(this)
                .setTitle((String) mData.get(position).get("iifo"))
                .setMessage((String) mData.get(position).get("info"))
                .setPositiveButton("送出",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();	}

    public final class ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView info;
        public TextView time;
        public TextView iifo;
        public Button viewBtn;
        public Button editBtn;
    }


    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {

                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.listview3, null);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.iifo = (TextView) convertView.findViewById(R.id.iifo);
                holder.info = (TextView) convertView.findViewById(R.id.info);
                holder.time = (TextView) convertView.findViewById(R.id.infoo);
                holder.viewBtn = (Button) convertView.findViewById(R.id.view_btn);
                holder.editBtn = (Button) convertView.findViewById(R.id.edit_btn);
                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
            holder.title.setText((String) mData.get(position).get("info"));
            holder.info.setText((String) mData.get(position).get("title"));
            holder.iifo.setText((String) mData.get(position).get("iifo"));
            holder.time.setText((String) mData.get(position).get("time"));
            holder.viewBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    showInfo(position);
                }
            });
            holder.editBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    a=((String) mData.get(position).get("num"));

                    update();
                }
            });
            return convertView;
        }
    }

    private void update() {
        int i = Integer.valueOf(a).intValue();


        Intent intent = new Intent();
        intent.setClass(news.this,stau.class);


        Bundle bundle = new Bundle();
        bundle.putInt("hos", Hos[i]);
        bundle.putInt("min",Min[i]);
        bundle.putInt("mon",Mon[i]);
        bundle.putInt("day",Day[i]);
        bundle.putString("id",ID[i]);
        bundle.putString("vale",vale[i]);
        bundle.putString("title",title[i]);
        bundle.putString("where",Where[i]);
        bundle.putString("page",Page[i]);


        intent.putExtras(bundle);


        startActivity(intent);
        finish();
    }

}
