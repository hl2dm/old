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
import dm.fast.partykings.staadd;
import dm.fast.partykings.stau;

public class ListViewnews extends ListActivity {
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
    int img[]= new int[200];
    int x;
    int check;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("title", "月");
//        map.put("info", "日");
//        map.put("infoo","內容");
//        map.put("iifo","抬頭");
//        map.put("num","a");
//        map.put("time","時間");
//        map.put("img", R.drawable.a2);
//        list.add(map);
                map = new HashMap<String, Object>();
        vale[1]="歲末大趴-有最嗨的電音，最火辣的"+"FHM女郎，還有最搶手的大獎，就差一"+"個盛裝打扮的你，別忘了派對主題顏色唷～";
                map.put("info","內容:"+vale[1]);
                map.put("title","日期:"+"2013 01月26 週六");
        Mon[1]=1;
        Day[1]=26;
        title[1]="歲末大趴";
                map.put("time","時間:"+"22:00pm-04:00am"+"\n"+"地點:"+"LAVA 夜店 Club");
        Min[1]=22;
        Hos[1]=00;
       Where[1]="台北市信義區松壽路22號B1";
                map.put("iifo","標題:"+"FHM Forst Bite 酷派搖滾");
                map.put("num","1");
                map.put("img", R.drawable.r1);
        img[1]=R.drawable.rn1;
                list.add(map);

        return list;
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        ListView listView = (ListView) arg0;
        Toast.makeText(
                ListViewnews.this,
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

                convertView = mInflater.inflate(R.layout.newslist, null);
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.iifo = (TextView) convertView.findViewById(R.id.iifo);
                holder.info = (TextView) convertView.findViewById(R.id.info);
                holder.time = (TextView) convertView.findViewById(R.id.infoo);
                holder.editBtn = (Button) convertView.findViewById(R.id.edit_btn_news);
                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
            holder.title.setText((String) mData.get(position).get("info"));
            holder.info.setText((String) mData.get(position).get("title"));
            holder.iifo.setText((String) mData.get(position).get("iifo"));
            holder.time.setText((String) mData.get(position).get("time"));
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
        intent.setClass(ListViewnews.this,staadd.class);


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
        bundle.putInt("img",img[i]);


        intent.putExtras(bundle);


        startActivity(intent);
        finish();
    }

}
