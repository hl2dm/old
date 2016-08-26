package dm.fast.partykings;
import dm.fast.partykings.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by ART on 2013/7/1.
 */
public class test extends Activity {
    public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.index);

    }
}
