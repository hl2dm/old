package simple;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.widget.Toast;
import dm.fast.partykings.CallAlarm;

public class gpstake extends Activity implements LocationListener  {
    private LocationManager lms;
    private String bestProvider = LocationManager.GPS_PROVIDER;
    private boolean getGPSService;



    //	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		 /**
//		  * 啟用定位模組，使用後記得關閉
//		  * @return Location
//		  */
//		Location s=locationServiceInitial();
//        double lat = s.getLatitude();
//        double lng = s.getLongitude();
//        lat=lat+lng;
//
//	}
    public Location locationServiceInitial() {
		  /* 取得系統定位服務 */
        LocationManager status = (LocationManager) (this.getSystemService(Context.LOCATION_SERVICE));
		   
		  /* 確認是否開啟GPS服務or網路定位服務 */
        if (status.isProviderEnabled(LocationManager.GPS_PROVIDER) || status.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            getGPSService=true;
            lms = (LocationManager) getSystemService(LOCATION_SERVICE); // 取得系統定位服務
            Criteria criteria = new Criteria(); // 資訊提供者選取標準
            bestProvider = lms.getBestProvider(criteria, true);
            Location location = lms.getLastKnownLocation(bestProvider); // 使用GPS定位座標

            if(location != null){
                return location;
            }else{
                Toast.makeText(this, "無法定位座標", Toast.LENGTH_LONG).show();
                return null;
            }
        } else {
            Toast.makeText(this, "請開啟定位服務", Toast.LENGTH_LONG).show();
            // 開啟設定畫面
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            return null;
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (getGPSService) {
            // 服務提供者、更新頻率毫秒、最短距離、地點改變時呼叫物件
            lms.requestLocationUpdates(bestProvider, 1000, 1, (LocationListener) this);
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (getGPSService) {
            lms.removeUpdates((LocationListener) this); // 離開頁面時停止更新
            getGPSService = false;
        }
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

}
