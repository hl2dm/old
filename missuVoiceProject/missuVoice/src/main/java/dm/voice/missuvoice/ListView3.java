package dm.voice.missuvoice;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class ListView3 extends ListActivity {
    private ArrayList<Site> sites;
    private DBOpenHelper dbHelper;
    private List<Map<String, Object>> mData;

    String a;
    String ID[]=new  String[200];
    String NAME[]=new  String[200];
    byte img[][]= new byte[100][];
    int x;
    int check;
    Bitmap BP[]=new Bitmap[10];
    Intent lm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(1);
        lm=new Intent();
        if (dbHelper == null) {
            dbHelper = new DBOpenHelper(this);
        }
        sites = dbHelper.getAllSites();
        check=sites.size();
        if(check>0){
            showSites();
        }
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
    }


    private void showSites() {
        int a=0;
        int t=sites.size();
        for(int i=0;i<check;i++){
            Site site = sites.get(a);
            ID[i]=site.getId();
            img[i] = site.getImage();
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inSampleSize = 3;

            BP[i] = BitmapFactory.decodeByteArray(img[i], 0, img[i].length,opts);

            NAME[i] = site.getname();
            a=a+1;
        }
    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();

        if(check>0){
            for(int w=0;w<check;w++){
                map = new HashMap<String, Object>();
                map.put("name",NAME[w]);
                map.put("img",BP[w]);
                list.add(map);
            }
        }else{
            map = new HashMap<String, Object>();
            map.put("name","沒資料");
            map.put("img",R.drawable.face_icon);
            list.add(map);
        }

        return list;
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        ListView listView = (ListView) arg0;
        Toast.makeText(
                ListView3.this,
                "ID" + arg3 +
                        "name"+ listView.getItemAtPosition(arg2).toString(),
                Toast.LENGTH_LONG).show();
    };



    /**
     * listview嚙踝蕭嚙瘢嚙踝蕭嚙踝蕭嚙編嚙諂出嚙踝蕭飫嚙?
     */
    public void showInfo(int position) {
        new AlertDialog.Builder(this)
                .setTitle((String) mData.get(position).get("name"))
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
                holder.viewBtn = (Button) convertView.findViewById(R.id.edit_btn);
                holder.editBtn = (Button) convertView.findViewById(R.id.view_btn);
                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.img.setImageBitmap((Bitmap) mData.get(position).get("img"));
            holder.title.setText((String) mData.get(position).get("name"));
            holder.viewBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    lm.setClass(ListView3.this,MainActivity.class);
                    Bundle face_int =new Bundle();
                    face_int.putInt("face",position);
                    lm.putExtras(face_int);
                    startActivity(lm);
                }
            });
            holder.editBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(check>0){
                        a=((String) mData.get(position).get("num"));
//
//                    update();
                    }
                }
            });
            return convertView;
        }
    }

//    private void update() {
//        int i = Integer.valueOf(a).intValue();
//
//
//        Intent intent = new Intent();
//        intent.setClass(ListView3.this,stau.class);
//
//
//        Bundle bundle = new Bundle();
//        bundle.putInt("hos", Hos[i]);
//        bundle.putInt("min",Min[i]);
//        bundle.putInt("mon",Mon[i]);
//        bundle.putInt("day",Day[i]);
//        bundle.putString("id",ID[i]);
//        bundle.putString("vale",vale[i]);
//        bundle.putString("title",title[i]);
//        bundle.putString("where",Where[i]);
//        bundle.putString("page",Page[i]);
//
//
//        intent.putExtras(bundle);
//
//
//        startActivity(intent);
//        finish();
//    }



}
