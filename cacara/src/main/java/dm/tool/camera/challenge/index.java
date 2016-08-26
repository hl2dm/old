package dm.tool.camera.challenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

/**
 * Created by hl2dm on 2013/11/28.
 */
public class index  extends Activity {
Intent intent;
    Intent intent2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView(R.layout.index);

        intent = new Intent();
        intent2 = new Intent();
        intent.setClass(this, MainActivity.class);
        intent2.setClass(this,setphoto.class);
        ImageButton photoset=(ImageButton)findViewById(R.id.photo_ibtn);
        photoset.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                startActivity(intent2);
            }});
        ImageButton cameraset=(ImageButton)findViewById(R.id.camera_ibtn);
        cameraset.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {

                startActivity(intent);
            }});


    }
}
