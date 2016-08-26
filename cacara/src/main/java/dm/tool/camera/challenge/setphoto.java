package dm.tool.camera.challenge;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by hl2dm on 2013/12/1.
 */
public class setphoto extends Activity {
    static Uri uri;
   Intent   intent;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.setphoto_lo);
        Intent intent = new Intent( Intent.ACTION_PICK );

        // 過濾檔案格式
        intent.setType( "image/*" );

        // 建立 "檔案選擇器" 的 Intent  (第二個參數: 選擇器的標題)
        Intent destIntent = Intent.createChooser( intent, "選擇檔案" );

        // 切換到檔案選擇器 (它的處理結果, 會觸發 onActivityResult 事件)
        startActivityForResult(destIntent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        // 有選擇檔案
        if ( resultCode == RESULT_OK )
        {
            // 取得檔案的 Uri
           uri = data.getData();
            if( uri != null )
            {
                intent = new Intent();
                intent.setClass(this, smple.class);
                Bundle bundle = new Bundle();
                bundle.putInt("check",0 );


                //將Bundle物件assign給intent
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else
            {
                setTitle("無效的檔案路徑 !!");
            }
        }
        else
        {
          finish();
        }
    }
    public static Uri getUri(){

        return uri;
    }
}
