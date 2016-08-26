package dm.fast.partykings;
import dm.fast.partykings.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class sss extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, sta.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(i);

    }

}