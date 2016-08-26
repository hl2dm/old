package dm.voice.missuvoice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
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

import browseData_blob.DBOpenHelper;
import browseData_blob.Site;


public class camera extends Activity implements SurfaceHolder.Callback{
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
    private static byte[] data;
    private int mon;
    private static Uri uri;
    private DBOpenHelper dbHelper;
    Site x =new Site();
    boolean isPreview;
    int t;
Intent Cameraline;
    Bundle photo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.camera_main);
        button1=(Button)findViewById(R.id.button1);
        Cameraline= new Intent();
        Cameraline.setClass(camera.this,teachactivity.class);
        //在AndroidManifest.xml中設定或是用下面的setRequestedOrientation(0)設定也可以
        //0代表橫向、1代表縱向
        setRequestedOrientation(0);


        //設為横向顯示。因為攝影頭會自動翻轉90度，所以如果不横向顯示，看到的畫面就是翻轉的。
        surfaceView1=(SurfaceView)findViewById(R.id.surfaceView1);
        surfaceHolder=surfaceView1.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.addCallback(this);
        button1.setOnClickListener(new OnClickListener(){

            public void onClick(View v) {

                //自動對焦
                camera.autoFocus(afcb);
            }});

    }
    PictureCallback jpeg =new PictureCallback(){

        public void onPictureTaken(byte[] Data, Camera camera) {
            data=Data;
            camera.startPreview();
          startActivity(Cameraline);
            finish();
            //需要手動重新startPreview，否則停在拍下的瞬間
            camera.startPreview();
        }

    };
    public byte[] getData(){

        return data;
    }
    public static Uri getUri(){

        return uri;
    }
    public int getMon() {
        return mon;
    }



    public void surfaceCreated(SurfaceHolder holder) {

        camera=Camera.open();
        try {

                camera.setPreviewDisplay(holder);
            } catch (IOException exception) {
                camera.release();
                camera = null;
                // TODO: add more exception handling logic here
            }
//            Camera.Parameters parameters=camera.getParameters();
//            parameters.setPictureFormat(PixelFormat.JPEG);
//            parameters.setPreviewSize(320, 220);
//            camera.setPreviewDisplay(holder);
//            camera.setParameters(parameters);
//            //設置參數
//
//            //鏡頭的方向和手機相差90度，所以要轉向
//            //camera.setDisplayOrientation(90);
//            //攝影頭畫面顯示在Surface上
//            camera.startPreview();


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

//            if(success){
                //對焦成功才拍照
                camera.takePicture(null, null, jpeg);
            //}
        }


    };



}
