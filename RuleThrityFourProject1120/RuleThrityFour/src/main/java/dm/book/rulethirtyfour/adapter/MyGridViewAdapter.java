package dm.book.rulethirtyfour.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

import dm.book.rulethirtyfour.R;
import dm.book.rulethirtyfour.activity.jd;
import dm.book.rulethirtyfour.utility.BitmapUtilities;

public class MyGridViewAdapter extends BaseAdapter {
    URL[] TKs = new URL[200];
    byte[][] data = new byte[100][];
    InputStream[] Is = new InputStream[200];
    Bitmap[] Bp = new Bitmap[80];
    private Context mContext = null;
    private LayoutInflater mLayoutInflater = null;
    private List<String> mList = null;

    private int width = 150;//每个Item的宽度,可以根据实际情况修改
    private int height = 150;//每个Item的高度,可以根据实际情况修改


    public static class MyGridViewHolder{
        public ImageView imageview_thumbnail;
        public TextView textview_test;
    }

    public MyGridViewAdapter(Context context,List<String> list) {
        // TODO Auto-generated constructor stub
        this.mContext = context;
        this.mList = list;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }


    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        MyGridViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new MyGridViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.layout_my_gridview_item, null);
            viewHolder.imageview_thumbnail = (ImageView)convertView.findViewById(R.id.imageview_thumbnail);
            viewHolder.textview_test = (TextView)convertView.findViewById(R.id.textview_test);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (MyGridViewHolder)convertView.getTag();
        }

        String url = mList.get(position);
        //首先我们先通过cancelPotentialLoad方法去判断imageview是否有线程正在为它加载图片资源，
        //如果有现在正在加载，那么判断加载的这个图片资源（url）是否和现在的图片资源一样，不一样则取消之前的线程（之前的下载线程作废）。
        //见下面cancelPotentialLoad方法代码
        if (cancelPotentialLoad(url, viewHolder.imageview_thumbnail)) {
            AsyncLoadImageTask task = new AsyncLoadImageTask(viewHolder.imageview_thumbnail);
            LoadedDrawable loadedDrawable = new LoadedDrawable(task);
            viewHolder.imageview_thumbnail.setImageDrawable(loadedDrawable);
            task.execute(position);
        }
        viewHolder.textview_test.setText((position+1)+"");
        return convertView;
    }



    private Bitmap getBitmapFromUrl(String url){
        Bitmap bitmap = null;
        bitmap = jd.gridviewBitmapCaches.get(url);
        if(bitmap != null){
            System.out.println(url);
            return bitmap;
        }
//		url = url.substring(0, url.lastIndexOf("/"));//处理之前的路径区分，否则路径不对，获取不到图片

        //bitmap = BitmapFactory.decodeFile(url);
        //这里我们不用BitmapFactory.decodeFile(url)这个方法
        //用decodeFileDescriptor()方法来生成bitmap会节省内存
        //查看源码对比一下我们会发现decodeFile()方法最终是以流的方式生成bitmap
        //而decodeFileDescriptor()方法是通过Native方法decodeFileDescriptor生成bitmap的

        try {
            int r=url.length();
            url=url.substring(0,r-2);
            TKs[1] = new URL(url);
            Is[1] = TKs[1].openStream();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 6;//圖片寬高都為原來的二分之一，即圖片為原來的四分之一
            Bp[1] = BitmapFactory.decodeStream(Is[1],null,options);
            Is[1].close();
//			FileInputStream is = new FileInputStream(url);
            bitmap = Bp[1];
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bitmap = BitmapUtilities.getBitmapThumbnail(bitmap,width,height);
        return bitmap;
    }

    //加载图片的异步任务
    private class AsyncLoadImageTask extends AsyncTask<Integer, Void, Bitmap> {
        private String url = null;
        private final WeakReference<ImageView> imageViewReference;

        public AsyncLoadImageTask(ImageView imageview) {
            super();
            // TODO Auto-generated constructor stub
            imageViewReference = new WeakReference<ImageView>(imageview);
        }

        @Override
        protected Bitmap doInBackground(Integer... params) {
            // TODO Auto-generated method stub
            Bitmap bitmap = null;
            this.url = mList.get(params[0]);
            bitmap = getBitmapFromUrl(url);
            jd.gridviewBitmapCaches.put(mList.get(params[0]), bitmap);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap resultBitmap) {
            // TODO Auto-generated method stub
            if(isCancelled()){
                resultBitmap = null;
            }
            if(imageViewReference != null){
                ImageView imageview = imageViewReference.get();
                AsyncLoadImageTask loadImageTask = getAsyncLoadImageTask(imageview);
                // Change bitmap only if this process is still associated with it
                if (this == loadImageTask) {
                    imageview.setImageBitmap(resultBitmap);
                    imageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                }
            }
            super.onPostExecute(resultBitmap);
        }
    }


    private boolean cancelPotentialLoad(String url,ImageView imageview){
        AsyncLoadImageTask loadImageTask = getAsyncLoadImageTask(imageview);

        if (loadImageTask != null) {
            String bitmapUrl = loadImageTask.url;
            if ((bitmapUrl == null) || (!bitmapUrl.equals(url))) {
                loadImageTask.cancel(true);
            } else {
                // 相同的url已经在加载中.
                return false;
            }
        }
        return true;

    }

    //当 loadImageTask.cancel(true)被执行的时候，则AsyncLoadImageTask 就会被取消，
    //当AsyncLoadImageTask 任务执行到onPostExecute的时候，如果这个任务加载到了图片，
    //它也会把这个bitmap设为null了。
    //getAsyncLoadImageTask代码如下：
    private AsyncLoadImageTask getAsyncLoadImageTask(ImageView imageview){
        if (imageview != null) {
            Drawable drawable = imageview.getDrawable();
            if (drawable instanceof LoadedDrawable) {
                LoadedDrawable loadedDrawable = (LoadedDrawable)drawable;
                return loadedDrawable.getLoadImageTask();
            }
        }
        return null;
    }

    //该类功能为：记录imageview加载任务并且为imageview设置默认的drawable
    public static class LoadedDrawable extends ColorDrawable {
        private final WeakReference<AsyncLoadImageTask> loadImageTaskReference;

        public LoadedDrawable(AsyncLoadImageTask loadImageTask) {
            super(Color.TRANSPARENT);
            loadImageTaskReference =
                    new WeakReference<AsyncLoadImageTask>(loadImageTask);
        }

        public AsyncLoadImageTask getLoadImageTask() {
            return loadImageTaskReference.get();
        }

    }
}
