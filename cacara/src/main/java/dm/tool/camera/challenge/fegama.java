package dm.tool.camera.challenge;

/**
 * Created by hl2dm on 2013/8/10.
 */
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class fegama extends Fragment {


    private  String title1;
    private Bitmap bbmp;
    public fegama(Bitmap bs) {
        this.bbmp = bs;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fg,container, false);

ImageView sa=(ImageView)fragmentView.findViewById(R.id.edit_pag);
        sa.setImageBitmap(bbmp);

        return fragmentView;
    }


    public void onDetach (){

        super.onDetach();
    }
    public void onStop(){

        super.onStop();
    }

}