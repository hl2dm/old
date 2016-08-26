package dm.book.rulethirtyfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dm.book.rulethirtyfour.browseData_blob.DBOpenHelper_seach;
import dm.book.rulethirtyfour.browseData_blob.Site_seach;
import dm.book.rulethirtyfour.showshow.show_sd_card;

/**
 * Created by hl2dm on 2013/9/30.
 */
public class seach extends Activity {
    EditText seach_text;
    Button Seach_btn;
    Intent seach_intent;
    Intent here_intent;
    String string_text;
    ArrayList list2;
    ImageView gohere;
    private SimpleAdapter adapter;
    private ArrayList<Site_seach> sites;
    private DBOpenHelper_seach dbHelper;
    int t;
    int s;
    ListView list_now;
    String seach_text_db[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.page);
        findview();

        if (dbHelper == null) {
            dbHelper = new DBOpenHelper_seach(this);
        }
        sites = dbHelper.getAllSites();

        t=sites.size();
        s=t;
        seach_text_db=new String[1000];
        for(int i=0;i<t;i++){
            Site_seach site = sites.get(i);
            seach_text_db[i]=site.getSeach();
        }
        Start();
        seach_intent = new Intent();
        seach_intent.setClass(this, set.class);
        here_intent = new Intent();
        here_intent.setClass(this, show_sd_card.class);
        gohere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(here_intent);
            }
        });
        Seach_btn.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                string_text=seach_text.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("seach",string_text);
                seach_intent.putExtras(bundle);
                startActivity(seach_intent);
                sites = dbHelper.getAllSites();
                t=sites.size();
                s=t;
                for(int i=0;i<t;i++){
                    Site_seach site = sites.get(i);
                    seach_text_db[i]=site.getSeach();
                }
                initDB();
                Start();
            }

        });
    }

    private void findview() {
        seach_text=(EditText)findViewById(R.id.editText);
        Seach_btn=(Button)findViewById(R.id.seach_btn);
        list_now =(ListView)findViewById(R.id.listView);
        gohere=(ImageView)findViewById(R.id.goheree);
    }
    private void initDB() {
        if (dbHelper == null) {
            dbHelper = new DBOpenHelper_seach(this);
        }

        List<Site_seach> sites_dummy = null;
        try {
            sites_dummy = createDummyData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dbHelper.insertDummyData(sites_dummy);
    }
    private List<Site_seach> createDummyData() throws IOException {
        ArrayList<Site_seach> sites_dummy = new ArrayList<Site_seach>();
        Site_seach[] a1 = new Site_seach[1000];
        for(int i=0;i<t;i++){
            a1[i] = new Site_seach("id"+i,seach_text_db[i]);
            sites_dummy.add(a1[i]);
        }
        a1[t+1] = new Site_seach("id"+t+1,string_text);
        sites_dummy.add(a1[t+1]);

        return sites_dummy;
    }
    private void Start() {
        list2 = new ArrayList<HashMap<String, Object>>();
        for(int i=0; i<t; i++){
            HashMap<String,Object> item = new HashMap<String,Object>();
            item.put("seach", seach_text_db[i]);

            list2.add( item );
        }
        adapter = new SimpleAdapter(
               this,list2,
                R.layout.seachitem,
                new String[] {
                        "seach", },
                new int[] { R.id.seach_ttv,} ){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub

                if(convertView==null){
                    convertView=View.inflate(seach.this, R.layout.seachitem, null);
                }
//                seach_text.setText(seach_text_db[position]);
                return super.getView(position, convertView, parent);

            }

        };
        list_now.setAdapter(adapter);
    }
}
