package dm.book.rulethirtyfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hl2dm on 2013/9/30.
 */
public class seach extends Activity {
    EditText seach_text;
    Button Seach_btn;
    Intent seach_intent;
    String string_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.page);
        findview();

        seach_intent = new Intent();
        seach_intent.setClass(this, set.class);


        Seach_btn.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {
                string_text=seach_text.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("seach",string_text);
                seach_intent.putExtras(bundle);
                startActivity(seach_intent);
            }

        });
    }

    private void findview() {
        seach_text=(EditText)findViewById(R.id.editText);
        Seach_btn=(Button)findViewById(R.id.seach_btn);
    }
}
