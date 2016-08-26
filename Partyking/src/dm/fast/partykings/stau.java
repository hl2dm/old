package dm.fast.partykings;
import dm.fast.partykings.R;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import browseData_blob.DBOpenHelper;
import browseData_blob.Site;

public class stau extends Activity {
    int e=0;
    int Mon;
    int Day;
    int Min;
    int house;
    int x=0;
    int SQL_c=0;
    String st_Stroke;
    int Mon2;
    int Day2;
    int check;
    Uri uri;
    String search_true;
    String search_set;
    String search_fast;
    String search_cancel;
    String[] str1 = new String[24];
    private int myYear, myMonth, myDay, myHour, myMinute;
    static final int ID_DATEPICKER = 0;
    static final int ID_TIMEPICKER = 1;
    static final int ID_DayPICKER = 2;
    public final static String TIMER_ACTION = "AlarmAlert";
   TextView tv_Day;
   TextView tv_Time;
   TextView tv_Sk;
    TextView tv_Title;
    String vale;
    String Title_vale;
    TextView tv_wh;
    String enter;
    String ID;
    String title;
    String VALE;
    String mon_st;
    String day_st;
    String where_st;
    String page_st;
    String page;
    String where;
   int img;
    int hos;
    int min;
    int day;
    int mon;
    private Timer timer;
    ImageView iv;
String take_id;
    Timer time=new Timer(true);
    private DBOpenHelper dbHelper;
    private ArrayList<Site> sites;
    Calendar mCalendar = Calendar.getInstance();
    Calendar cl = Calendar.getInstance();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        iv = (ImageView)this.findViewById(R.id.imageView);
        search_true=getResources().getString(R.string.str_true_Search);
        search_set=getResources().getString(R.string.str_set_Search);
        search_fast=getResources().getString(R.string.str_fast_Search);
        search_cancel=getResources().getString(R.string.str_cancel_Search);
        mon_st=getResources().getString(R.string.str_MON);
        day_st=getResources().getString(R.string.str_DAY);
        enter=getResources().getString(R.string.str_enter);
        tv_Day =(TextView)findViewById(R.id.Tv_day);
        tv_Title =(TextView)findViewById(R.id.Tv_Title);
        tv_Time =(TextView)findViewById(R.id.Tv_time);
        tv_Sk =(TextView)findViewById(R.id.Tv_Stroke);
        tv_wh =(TextView)findViewById(R.id.Tv_where);
        Button timePickerButton = (Button)findViewById(R.id.btn_day);
        timePickerButton.setOnClickListener(datePickerButtonOnClickListener);
        Button  datePickerButton  = (Button)findViewById(R.id.btn_time);
        datePickerButton.setOnClickListener(timePickerButtonOnClickListener);
        Button  dateStroke  = (Button)findViewById(R.id.btn_Stroke);
        dateStroke.setOnClickListener(dayPickerButtonOnClickListener);
        Button  dateTitle  = (Button)findViewById(R.id.btn_Title);
        dateTitle.setOnClickListener(TitleButtonOnClickListener);
        Button  Send  = (Button)findViewById(R.id.btn_Send);
        Send.setOnClickListener(SendButtonOnClickListener);
        time.schedule(new timerTask(),1000, 3600000);
        Bundle bundle = getIntent().getExtras();
        Button  delete  = (Button)findViewById(R.id.btn_delete);
        delete.setOnClickListener(deleteButtonOnClickListener);
        Button  btn_where  = (Button)findViewById(R.id.btn_where);
        btn_where.setOnClickListener(whereButtonOnClickListener);
        Button  img_btn  = (Button)findViewById(R.id.btn_img);
        img_btn.setOnClickListener(imgButtonOnClickListener);
        ID = bundle.getString("id");
        if(ID==null){
            ID="J";
        }
        title = bundle.getString("title");
        VALE = bundle.getString("vale");
        where_st = bundle.getString("where");
        page_st = bundle.getString("page");
        img = bundle.getInt("img");
        hos = bundle.getInt("hos");
        min = bundle.getInt("min");
        mon = bundle.getInt("mon");
        day = bundle.getInt("day");

        tv_Day.setText(min+":"+hos);
        tv_Time.setText(mon+mon_st+day+day_st);
        tv_Title.setText(title+"");
        tv_Sk.setText(VALE+"");
        tv_wh.setText(where_st+"");

        day=Day;
        mon=Mon;
        Min=min;
        house=hos;
        vale=VALE;
        Title_vale=title;
        page=page_st;
        if(page==null){
            page="xx";
        }
        where=where_st;
        if(page==null){
            iv.setImageResource(img);
        }else{
        Bitmap imageBitmap = BitmapFactory.decodeFile(page);
        iv.setImageBitmap(imageBitmap);
        }

        Mon = mCalendar.get(Calendar.MONTH);
        Mon=Mon+1;
        Day = mCalendar.get(Calendar.DAY_OF_MONTH);
        if (dbHelper == null) {
            dbHelper = new DBOpenHelper(this);
        }
        sites = dbHelper.getAllSites();
        check=sites.size();
if(check>0){
    SQL_c=check;
}

    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        switch(id){
            case ID_DATEPICKER:
                return new DatePickerDialog(this,
                        myDateSetListener,
                        myYear, myMonth, myDay);
            case ID_TIMEPICKER:
                return new TimePickerDialog(this,
                        myTimeSetListener,
                        myHour, myMinute, false);
                default:
                return null;

        }
    }
    private Button.OnClickListener deleteButtonOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(stau.this);
            builder.setTitle(R.string.str_real);
            builder.setIcon(android.R.drawable.ic_dialog_info);
            builder.setPositiveButton(R.string.str_rely,
                    new DialogInterface.OnClickListener()
                    {

                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            new AlertDialog.Builder(stau.this)
                                    .setMessage(R.string.str_rely).create().show();
                            String ikk =ID;
                            dbHelper.deleteDB(ikk);
                            finish();
                        }
                    });
            builder.setNegativeButton(R.string.str_cancel,
                    new DialogInterface.OnClickListener()
                    {

                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                            Toast.makeText(stau.this, R.string.str_cancel,
                                    Toast.LENGTH_LONG).show();

                        }
                    });
            builder.create().show();
        }
    };
//
private Button.OnClickListener imgButtonOnClickListener
        = new Button.OnClickListener(){

    @Override
    public void onClick(View v) {
        Intent intent = new Intent( Intent.ACTION_PICK );
        intent.setType( "image/*" );
        Intent destIntent = Intent.createChooser( intent,search_set );
        startActivityForResult( destIntent, 0 );
    }
};
    private Button.OnClickListener datePickerButtonOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            final Calendar c = Calendar.getInstance();
            myYear = c.get(Calendar.YEAR);
            myMonth = c.get(Calendar.MONTH);
            myDay = c.get(Calendar.DAY_OF_MONTH);
            showDialog(ID_DATEPICKER);

        }
    };
    private Button.OnClickListener whereButtonOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            LayoutInflater inflater = LayoutInflater.from(stau.this);
            final View x3 = inflater.inflate(R.layout.alertdialog_edittext3, null);
            new AlertDialog.Builder(stau.this)
                    .setTitle(enter)
                    .setView(x3)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            EditText editText3 = (EditText) (x3.findViewById(R.id.edittext3));
                            where=editText3.getText().toString();
                            tv_wh.setText(where);
                        }
                    })
                    .show();
        }
    };
    private Button.OnClickListener dayPickerButtonOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            LayoutInflater inflater = LayoutInflater.from(stau.this);
            final View x = inflater.inflate(R.layout.alertdialog_edittext, null);
            new AlertDialog.Builder(stau.this)
                    .setTitle(enter)
                    .setView(x)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            EditText editText = (EditText) (x.findViewById(R.id.edittext));
                            vale=editText.getText().toString();
                            tv_Sk.setText(vale);
                        }
                    })
                    .show();

        }
    };
    private Button.OnClickListener TitleButtonOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            LayoutInflater inflater = LayoutInflater.from(stau.this);
            final View x2 = inflater.inflate(R.layout.alertdialog_edittext2, null);
            new AlertDialog.Builder(stau.this)
                    .setTitle(enter)
                    .setView(x2)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            EditText editText2 = (EditText) (x2.findViewById(R.id.edittext2));
                            Title_vale=editText2.getText().toString();
                            tv_Title.setText(Title_vale);
                        }
                    })
                    .show();

        }
    };
    private Button.OnClickListener timePickerButtonOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            final Calendar c = Calendar.getInstance();
            myHour = c.get(Calendar.HOUR_OF_DAY);
            myMinute = c.get(Calendar.MINUTE);
            showDialog(ID_TIMEPICKER);
        }
    };
    private Button.OnClickListener SendButtonOnClickListener
            = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            initDB();
            finish();
        }
    };



    private void initDB() {
        if (dbHelper == null) {
            dbHelper = new DBOpenHelper(this);
        }

        List<Site> sites_dummy = null;
        try {
            sites_dummy = createDummyData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        x=0;
        dbHelper.updateDummyData(sites_dummy, x);
    }
    private List<Site> createDummyData() throws IOException {
        ArrayList<Site> sites_dummy = new ArrayList<Site>();
        Site[] a1 = new Site[24];
        a1[0] = new Site(ID,Day,Mon,Min,house,vale,Title_vale,where,page);
        sites_dummy.add(a1[0]);
        return sites_dummy;
    }
    private void showSites() {
        int t=sites.size();
            Site site = sites.get(0);
 take_id = site.getId();
        Toast.makeText(this, take_id, Toast.LENGTH_LONG).show();
    }
    public class timerTask extends TimerTask
    {
        public void run()
        {
        }
    };

    private DatePickerDialog.OnDateSetListener myDateSetListener
            = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year,
                              int monthOfYear, int dayOfMonth) {
            // TODO Auto-generated method stub
            Day=dayOfMonth;
            Mon=monthOfYear+1;
            String date ="Month: " + String.valueOf(monthOfYear+1) + "\n"
                    + "Day: " + String.valueOf(dayOfMonth);
            tv_Time.setText(date);
        }
    };
    private TimePickerDialog.OnTimeSetListener myTimeSetListener
            = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // TODO Auto-generated method stub
            house=hourOfDay;
            Min=minute;
            String time = "Hour: " + String.valueOf(hourOfDay) + "\n"
                    + "Minute: " + String.valueOf(minute);
            tv_Day.setText(time);
        }
    };
    private void time(){

    AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);


    Intent intent =new Intent("WAKEUP");

    PendingIntent pi = PendingIntent.getBroadcast(stau.this, 1, intent, 0);

    am.set(AlarmManager.RTC_WAKEUP, cl.getTimeInMillis(), pi);
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == RESULT_OK )
        {
            uri = data.getData();
            if( uri != null )
            {

                iv = (ImageView)this.findViewById(R.id.imageView);
                page=getFilePathFromUri(this, uri);
                Bitmap imageBitmap = BitmapFactory.decodeFile(page);
                iv.setImageURI( uri );

                setTitle( uri.toString() );
            }
            else
            {
                setTitle(search_fast);
            }
        }
        else
        {
            setTitle(search_cancel);
        }
    }
    public static String getFilePathFromUri(Context context, Uri uri) {
        CursorLoader cursorLoader= new CursorLoader(
                context,
                uri,
                new String[]{ "_data" },
                null,
                null,
                null
        );
        String returnStr = "";
        Cursor cursor = cursorLoader.loadInBackground();
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            returnStr = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
        }
        return returnStr;
    }
}
