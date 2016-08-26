package dm.tool.camera.challenge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MainActivity extends Activity implements SurfaceHolder.Callback{
    SurfaceHolder surfaceHolder;
    SurfaceView surfaceView1;
    Button button1;
    ImageView imageView1;
    String s;
    Camera camera;
    Bitmap bmp;
    Context context;
    InputStream is;
    InputStream is2;
    Intent intent;
    private static byte[] data;
    private int mon;
    private static Uri uri;
    boolean isPreview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.activity_main);

        intent = new Intent();
        intent.setClass(this, smple.class);
        Bundle bundle = new Bundle();
        bundle.putInt("check",1 );


        //將Bundle物件assign給intent
        intent.putExtras(bundle);

        button1=(Button)findViewById(R.id.button1);
        //在AndroidManifest.xml中設定或是用下面的setRequestedOrientation(0)設定也可以
        //0代表橫向、1代表縱向
        setRequestedOrientation(0);
        //設為横向顯示。因為攝影頭會自動翻轉90度，所以如果不横向顯示，看到的畫面就是翻轉的。

        surfaceView1=(SurfaceView)findViewById(R.id.surfaceView1);
//        imageView1=(ImageView)findViewById(R.id.imageView1);
        surfaceHolder=surfaceView1.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(this);
        button1.setOnClickListener(new OnClickListener(){

            public void onClick(View v) {

                //自動對焦
                camera.autoFocus(afcb);
//                setRequestedOrientation(1);
            }});

    }
    PictureCallback jpeg =new PictureCallback(){

        public void onPictureTaken(byte[] Data, Camera camera) {
//if(bmp!=null){
//    bmp.recycle();
//}
            data=Data;
            bmp= BitmapFactory.decodeByteArray(data, 0, data.length);
            uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),bmp, "123","456"));
bmp.recycle();
            //byte數组轉換成Bitmap
////            imageView1.setImageBitmap(bmp);
//            Bundle bundle = new Bundle();
//            bundle.putByteArray("height",data);
//
//            intent.putExtras(bundle);
//            intent.putExtra("BitmapImage",newbmp);
            startActivity(intent);

            //拍下圖片顯示在下面的ImageView裡
//            FragmentManager DS2 = getFragmentManager();
//            FragmentTransaction DSE2 = DS2.beginTransaction();
//            Fragment DF2 = DS2.findFragmentById(R.id.photo_fg);
//
//                fegama usermune = new fegama(newbmp);
//                DSE2.add(R.id.photo_fg, usermune);
//                DSE2.addToBackStack(null);
//                DSE2.commit();
//            FileOutputStream fop;
//            try {
//                fop=new FileOutputStream("/sdcard/dd.jpg");
//                //實例化FileOutputStream，參數是生成路徑
//                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fop);
//                //壓缩bitmap寫進outputStream 參數：輸出格式  輸出質量  目標OutputStream
//                //格式可以為jpg,png,jpg不能存儲透明
//                fop.close();
//                System.out.println("拍照成功");
//                //關閉流
//            } catch (FileNotFoundException e) {
//
//                e.printStackTrace();
//                System.out.println("FileNotFoundException");
//
//            } catch (IOException e) {
//
//                e.printStackTrace();
//                System.out.println("IOException");
//            }

            camera.startPreview();
            //需要手動重新startPreview，否則停在拍下的瞬間
        }

    };
    public static byte[] getData(){

        return data;
    }
    public static Uri getUri(){

        return uri;
    }
    public int getMon() {
        return mon;
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        Camera.Parameters myParameters = camera.getParameters();
        Camera.Size myBestSize = getBestPreviewSize(width, height, myParameters);

        if(myBestSize != null){
            myParameters.setPreviewSize(myBestSize.width, myBestSize.height);
            camera.setParameters(myParameters);
            camera.startPreview();
            isPreview = true;
        }
    }
    private Camera.Size getBestPreviewSize(int width, int height, Camera.Parameters parameters){
        Camera.Size bestSize = null;
        List<Camera.Size> sizeList = parameters.getSupportedPreviewSizes();

        bestSize = sizeList.get(0);

        for(int i = 1; i < sizeList.size(); i++){
            if((sizeList.get(i).width * sizeList.get(i).height) >
                    (bestSize.width * bestSize.height)){
                bestSize = sizeList.get(i);
            }
        }

        return bestSize;
    }

    public void surfaceCreated(SurfaceHolder holder) {

        camera=Camera.open();
        try {       camera.setPreviewDisplay(holder);
        } catch (IOException exception) {
            camera.release();
            camera = null;
            // TODO: add more exception handling logic here
        }

    }

    public void surfaceDestroyed(SurfaceHolder holder) {

        System.out.println("surfaceDestroyed");
        camera.stopPreview();
        //關閉預覽
        camera.release();
        //
    }

    //自動對焦監聽式
    AutoFocusCallback afcb= new AutoFocusCallback(){

        public void onAutoFocus(boolean success, Camera camera) {

            if(success){
                //對焦成功才拍照
                camera.takePicture(null, null, jpeg);
            }
        }


    };
}
