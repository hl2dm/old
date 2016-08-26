package browseData_blob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "sites";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "sitesInfo";
	private static final String COL_id = "id";
	private static final String COL_mon = "mon";
    private static final String COL_day = "day";
    private static final String COL_job = "job";
    private static final String COL_m = "min";
    private static final String COL_h = "hos";
    private static final String COL_title = "title";
    private static final String COL_where = "well";
    private static final String COL_page = "page";
    String whereClause;
	private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
			+ " ( " + COL_id + " TEXT NOT NULL, "
            + COL_mon+ " TEXT NOT NULL, "
            + COL_day+ " TEXT NOT NULL, "
            + COL_m+ " TEXT NOT NULL, "
            + COL_h+ " TEXT NOT NULL, "
            + COL_job+ " TEXT NOT NULL, "
            + COL_title+ " TEXT NOT NULL, "
            + COL_where+ " TEXT NOT NULL, "
            + COL_page+ " TEXT NOT NULL, "
			+ " BLOB, PRIMARY KEY (id)); ";

	public DBOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public void insertDummyData(List<Site> sites_dummy,int x) {
		SQLiteDatabase db = getWritableDatabase();
//		db.delete(TABLE_NAME, null, null);
		ArrayList<ContentValues> values = new ArrayList<ContentValues>();
		for (Site site : sites_dummy) {
			ContentValues row = new ContentValues();
			row.put(COL_id, site.getId());
			row.put(COL_job, site.getJob());
            row.put(COL_mon, site.getMon());
            row.put(COL_day, site.getDay());
            row.put(COL_m, site.getMin());
            row.put(COL_h, site.getHos());
            row.put(COL_title, site.getTitle());
            row.put(COL_where, site.getWhere());
            row.put(COL_page, site.getPage());
			values.add(row);
		}

		for (ContentValues row : values) {
			db.insert(TABLE_NAME, null, row);
		}
		db.close();
	}
    public void updateDummyData(List<Site> sites_dummy,int x)
    {
        SQLiteDatabase db = getWritableDatabase();
//		db.delete(TABLE_NAME, null, null);
        ArrayList<ContentValues> values = new ArrayList<ContentValues>();
        for (Site site : sites_dummy) {
            ContentValues row = new ContentValues();
            row.put(COL_id, site.getId());
            row.put(COL_job, site.getJob());
            row.put(COL_mon, site.getMon());
            row.put(COL_day, site.getDay());
            row.put(COL_m, site.getMin());
            row.put(COL_h, site.getHos());
            row.put(COL_title, site.getTitle());
            row.put(COL_where, site.getWhere());
            row.put(COL_page, site.getPage());
            whereClause = COL_id + "='" + site.getId() + "'";
            values.add(row);
        }

        for (ContentValues row : values) {
            db.update(TABLE_NAME, row,whereClause, null);
        }
        db.close();

    }
	public ArrayList<Site> getAllSites() {
		SQLiteDatabase db = getReadableDatabase();
		String[] columns = { COL_id,COL_day,COL_mon,COL_m,COL_h,COL_job,COL_title,COL_where,COL_page};
		Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,null,null);
		ArrayList<Site> sites = new ArrayList<Site>();
		while (cursor.moveToNext()) {
			String id = cursor.getString(0);
			int day = cursor.getInt(1);
            int mon = cursor.getInt(2);
            int hos = cursor.getInt(3);
            int min = cursor.getInt(4);
            String job = cursor.getString(5);
            String title = cursor.getString(6);
            String where = cursor.getString(7);
            String page = cursor.getString(8);
            Site site = new Site(id, day,mon,min,hos,job,title,where,page);
			sites.add(site);

		}
		cursor.close();
		db.close();
		return sites;
	}
    public int deleteDB(String id){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COL_id + "='" + id + "'";
        int count = db.delete(TABLE_NAME, whereClause, null);
        db.close();
        return count;
    }
}
