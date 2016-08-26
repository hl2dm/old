package dm.book.rulethirtyfour.showshow;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dm.book.rulethirtyfour.R;


public class show_sd_card extends Activity  implements OnScrollListener {
    /** Called when the activity is first created. */
    private ImageView img;
    private GridView gridview_test = null;
    //SD图片路径
    Bitmap b;
    private String filepath = "/storage/emulated/0/Pictures/RULE34";
    private MyGridViewAdapter_SD adapter = null;
    private List<String> mList = null;
    File imageList;
    int bglong;
    ProgressBar s;
    String where[]=new String[100];
    public static Map<String,Bitmap> gridviewBitmapCaches = new HashMap<String,Bitmap>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showpg);
        s=(ProgressBar)findViewById(R.id.progressBare);
        s.setVisibility(View.GONE);
//        img = (ImageView) findViewById(R.id.imageView);
        gridview_test = (GridView)findViewById(R.id.gridview_test);
        File file = new File(filepath);

        File file2 = new File(filepath);

        File imageList[] = file2.listFiles();
        bglong= imageList.length;
        for(int i=0;i<bglong;i++)
        {
            Log.e("Image: " + i + ": path", imageList[i].getAbsolutePath());
            where[i]=imageList[i].getAbsolutePath();
            //将图片显示到ImageView中
//            img.setImageBitmap(b);
        }
        initData();
        setAdapter();
    }
    private void initData(){
        mList = new ArrayList<String>();
//    	String url = "http://rule34-data-002.paheal.net/_images/3582efbb9f7a004aa900ad404fb4cdde/1195466%20-%20Ai_Sutome%20Loli_Pop%20Misae_Nohara%20Miss_Anderson%20Miss_Katz%20Miss_Polly%20Moeko_Sakurada%20Patty_Milfer%20Penny_Milfer%20Shin_Chan%20nanako_ohara.png";
        //为sd卡下面创建testGridView文件夹，将图片放入其中
        //为了方便测试，我们这里只存入一张图片，将其路径后面添加数字进行区分，到后面要获取图片时候再处理该路径。
        for(int i=0;i<bglong;i++){
            mList.add(where[i]);//区分路径
        }
    }
    private void setAdapter(){

        adapter = new MyGridViewAdapter_SD(this, mList);
        gridview_test.setAdapter(adapter);
        gridview_test.setOnScrollListener(this);
        gridview_test.setNumColumns(2);
        gridview_test.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView parent, View v, final int position, long id)
            {
              take(position);

            }
        });
//        gridview_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                alertDialog.show();
//
//                // DO something
//
//            }
//        });
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // TODO Auto-generated method stub
        //注释：firstVisibleItem为第一个可见的Item的position，从0开始，随着拖动会改变
        //visibleItemCount为当前页面总共可见的Item的项数
        //totalItemCount为当前总共已经出现的Item的项数
        recycleBitmapCaches(0,firstVisibleItem);
        recycleBitmapCaches(firstVisibleItem+visibleItemCount, totalItemCount);

    }

    private void recycleBitmapCaches(int fromPosition,int toPosition){
        Bitmap delBitmap = null;
        for(int del=fromPosition;del<toPosition;del++){
            delBitmap = gridviewBitmapCaches.get(mList.get(del));
            if(delBitmap != null){
                //如果非空则表示有缓存的bitmap，需要清理
                //从缓存中移除该del->bitmap的映射
                gridviewBitmapCaches.remove(mList.get(del));
                delBitmap.recycle();
                delBitmap = null;
            }
        }
    }


    private void take(int s){

        final AlertDialog dialog = new AlertDialog.Builder(show_sd_card.this).create();
        LayoutInflater inflater = LayoutInflater.from(show_sd_card.this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        View view = inflater.inflate(R.layout.showloco, null); // xml Layout file for imageView
        ImageView img = (ImageView) view.findViewById(R.id.tttv);
        Bitmap shos= BitmapFactory.decodeFile(where[s]);

        img.setImageBitmap(shos);


//        img.setImageResource(R.drawable.dust);
        dialog.setView(view);
        dialog.show();
        dialog.getWindow().setAttributes(lp);
//
    }


//    @Override
//    protected void onPause() {
//        super.onPause();
//        finish();
//    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // TODO Auto-generated method stub
    }
}