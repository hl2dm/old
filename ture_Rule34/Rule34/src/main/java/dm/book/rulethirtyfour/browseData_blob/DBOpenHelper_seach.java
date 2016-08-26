package dm.book.rulethirtyfour.browseData_blob;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelper_seach extends SQLiteOpenHelper {
	private static final String DB_NAME = "sites_seach";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "sitesInfo_seach";
	private static final String COL_id = "id";
	private static final String COL_seach = "seach";
	private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME
			+ " ( " + COL_id + " TEXT NOT NULL, " + COL_seach
			+ " BLOB, PRIMARY KEY (id)); ";

	public DBOpenHelper_seach(Context context) {
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

	public void insertDummyData(List<Site_seach> sites_dummy) {
		SQLiteDatabase db = getWritableDatabase();
		// 將Table內容刪除以免資料重複插入
		db.delete(TABLE_NAME, null, null);
		ArrayList<ContentValues> values = new ArrayList<ContentValues>();
		for (Site_seach site : sites_dummy) {
			ContentValues row = new ContentValues();
			row.put(COL_id, site.getId());
			row.put(COL_seach, site.getSeach());
			values.add(row);
		}

		for (ContentValues row : values) {
			db.insert(TABLE_NAME, null, row);
		}
		db.close();
	}

	public ArrayList<Site_seach> getAllSites() {
		SQLiteDatabase db = getReadableDatabase();
		String[] columns = { COL_id, COL_seach };
		Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,
				null);
		ArrayList<Site_seach> sites = new ArrayList<Site_seach>();
		while (cursor.moveToNext()) {
			String id = cursor.getString(0);
			String seach = cursor.getString(1);
			
			Site_seach site = new Site_seach(id, seach);
			sites.add(site);
		}
		cursor.close();
		db.close();
		return sites;
	}
}
