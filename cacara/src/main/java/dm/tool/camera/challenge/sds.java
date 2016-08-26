package dm.tool.camera.challenge;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by hl2dm on 2013/8/10.
 */
public class sds extends Activity {
   private byte[] data;
    Bitmap bmp;
    InputStream is_set;
    InputStream is[]=new InputStream[3];
    String st_info="內容";
    String st_title="標題";
    String st_point="分數";
    Bitmap newbmp;
    Uri uri;
    int[] image = {
            R.drawable.sty1_2,
            R.drawable.sty2_2,
            R.drawable.sty3_2,
    };
    ImageView smo;
    int i=0;
    Bundle bundle0311;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.edit_main);
        is[0]= this.getResources().openRawResource(R.drawable.smp);
        is[1]= this.getResources().openRawResource(R.drawable.smp2);
        is[2]= this.getResources().openRawResource(R.drawable.smp3);
        is_set=is[i];
        sbuliden();
        bundle0311 =this.getIntent().getExtras();
smo=(ImageView)findViewById(R.id.esa);

//        try {
////            take();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Button save =(Button)findViewById(R.id.save_btn);
        Button send =(Button)findViewById(R.id.share_btn);
        Button build=(Button)findViewById(R.id.set_btn);
        ImageButton up=(ImageButton)findViewById(R.id.ibtn_up);
        ImageButton down=(ImageButton)findViewById(R.id.ibtn_down);

        up.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

                if(i!=2){

                i=i+1;
                }
                is_set=is[i];
                smo.setImageDrawable(getResources().getDrawable(image[i]));
                try {
                    take();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});

        down.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {


                if(i!=0){
                    i=i-1;
                }
                is_set=is[i];
                smo.setImageDrawable(getResources().getDrawable(image[i]));
                try {
                    take();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});

        save.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                smo.setImageDrawable(getResources().getDrawable(image[0]));
                try {
                    saveBitmap("sdcard",newbmp,"gg");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});
        send.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),newbmp, "123","456"));
                share(uri);
            }});
        build.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                bmp=null;
                data=null;
                sbuliden();
            }});

    }
    public static void saveBitmap(String pFolderPath, Bitmap pBitmap,
                                  String pFileName) throws IOException {

        File pictureFileDir = getDir();

        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {


            return;

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";
        String filename = pictureFileDir.getPath() + File.separator + photoFile;
        File pictureFile = new File(filename);
        FileOutputStream fos = new FileOutputStream(pictureFile);

        String _folderPath = pFolderPath;
        if (_folderPath.lastIndexOf("/") == -1) {
            _folderPath += "/";
        }
        File _file = new File(Environment.getExternalStorageDirectory(),getTimeName()+".jpg");
        OutputStream _outStream  = new FileOutputStream(pictureFile);

        pBitmap.compress(Bitmap.CompressFormat.JPEG, 90, _outStream);

        _outStream.flush();
        _outStream.close();
    }
    public static String getTimeName() {
        Date _date = new Date(System.currentTimeMillis());
        SimpleDateFormat _sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Random _random = new Random();
        return _sdf.format(_date) + _random.nextInt(999);
    }
    private static File getDir() {
        File sdDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(sdDir, "camera_challenge");
    }
    private void sbuliden() {
        final AlertDialog dialog = new AlertDialog.Builder(sds.this).create();
        LayoutInflater inflater = LayoutInflater.from(sds.this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        View view = inflater.inflate(R.layout.photo_set_layout, null); // xml Layout file for imageView
        Button ok = (Button) view.findViewById(R.id.btn_enter);
        Button cancel = (Button) view.findViewById(R.id.btn_cancel);
        final EditText title=(EditText)view.findViewById(R.id.dilog_tile);
        final EditText info=(EditText)view.findViewById(R.id.dilog_info);
        final EditText point=(EditText)view.findViewById(R.id.dilog_point);

        ok.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                st_info=info.getText().toString();
                st_title=title.getText().toString();
                st_point=point.getText().toString();
                try {
                    take();
                    dialog.cancel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        cancel.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                dialog.cancel();
            }

        });
//        img.setImageResource(R.drawable.dust);
        dialog.setView(view);
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void share(Uri uri){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        if(uri!=null){
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/*");
        }
        //自定义选择框的标题
        //startActivity(Intent.createChooser(shareIntent, "邀请好友"));
        //系统默认标题
        startActivity(shareIntent);

    }
    private void take() throws IOException {
        String FF = bundle0311.getString("file");
        Uri uri = Uri.parse("file://" +FF);
        ContentResolver resolver = getContentResolver();
        InputStream  inStream=resolver.openInputStream(uri);

        BitmapFactory.Options options2=new BitmapFactory.Options();
        options2.inJustDecodeBounds = false;
        options2.inSampleSize =2;
        bmp =BitmapFactory.decodeStream(inStream,null,options2);
        inStream.close();
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        float scaleWidth  = (float) 1000 / w;
        float scaleHeight = (float) 1000 / h;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        bmp =Bitmap.createBitmap(bmp, 0, 0, w, h,matrix, false);
        w = bmp.getWidth();
        h = bmp.getHeight();
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        Bitmap btp =BitmapFactory.decodeStream(is_set,null,options);

        newbmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(newbmp);
        cv.drawBitmap(bmp, 0, 0, null);
        cv.drawBitmap( btp ,100,800,null);
//        800
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(20);
        Paint paint2 = new Paint();
        paint2.setColor(Color.YELLOW);
        paint2.setFakeBoldText(true);
        paint2.setTextSize(20);
        Paint paint3 = new Paint();
        paint3.setColor(Color.BLACK);
        paint3.setTextSize(70);

        Paint paint4 = new Paint();
        paint4.setColor(Color.WHITE);
        paint4.setTextSize(15);
        cv.drawText(st_point, 820, 920, paint3);
        cv.drawText(st_info,300, 895, paint);
        //24
        cv.drawText(st_title,310, 860, paint2);
        //530
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        cv.drawText(date,790, 960, paint4);
        ImageView photo=(ImageView)findViewById(R.id.edaa);

        photo.setImageBitmap(newbmp);

    }
}
