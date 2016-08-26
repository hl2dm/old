package browseData_blob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "sites";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "sitesInfo";
	private static final String COL_id = "id";
    private static final String COL_name = "name";
    private static final String COL_voice = "voice";
	private static final String COL_image = "image";
    String whereClause;
	private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
			+ " ( " + COL_id + " TEXT NOT NULL, " +
            COL_name+" TEXT NOT NULL, " +
            COL_voice+" TEXT NOT NULL, " +
            COL_image
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

	public void insertDummyData(List<Site> sites_dummy) {
		SQLiteDatabase db = getWritableDatabase();
////		// 將Table內容刪除以免資料重複插入
//		db.delete(TABLE_NAME, null, null);
		ArrayList<ContentValues> values = new ArrayList<ContentValues>();
		for (Site site : sites_dummy) {
			ContentValues row = new ContentValues();
			row.put(COL_id, site.getId());
            row.put(COL_name, site.getname());
            row.put(COL_voice, site.getvoice());
			row.put(COL_image, site.getImage());
			values.add(row);
		}

		for (ContentValues row : values) {
			db.insert(TABLE_NAME, null, row);
		}
		db.close();
	}
    public void updateDummyData(List<Site> sites_dummy)
    {
        SQLiteDatabase db = getWritableDatabase();
//		db.delete(TABLE_NAME, null, null);
        ArrayList<ContentValues> values = new ArrayList<ContentValues>();
        for (Site site : sites_dummy) {
            ContentValues row = new ContentValues();
            row.put(COL_id, site.getId());
            row.put(COL_name, site.getname());
            row.put(COL_voice, site.getvoice());
            row.put(COL_image, site.getImage());

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
		String[] columns = { COL_id,COL_name,COL_voice, COL_image};
		Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,
				null);
		ArrayList<Site> sites = new ArrayList<Site>();
		while (cursor.moveToNext()) {
			String id = cursor.getString(0);
            String name = cursor.getString(1);
            String voice = cursor.getString(2);
			byte[] image = cursor.getBlob(3);
			
			Site site = new Site(id,name,voice,image);
			sites.add(site);
            Log.w("what the fuck", "SF");
		}
		cursor.close();
		db.close();
		return sites;
	}
}
