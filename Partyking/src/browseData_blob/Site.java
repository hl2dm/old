package browseData_blob;

import android.net.Uri;

public class Site {
	private String id;
    private int day;
    private int mon;
    private int min;
    private int hos;
    private Uri uri;
    private String job;
    private String title;
    private String where;
    private String page;
	public Site() {
	}

	public Site(String id, int day,int mon,int min,int hos,String job,String title,String where,String page) {
		this.id = id;
        this.day=day;
        this.job=job;
        this.mon=mon;
        this.min=min;
        this.hos=hos;
        this.title=title;
        this.where=where;
        this.page=page;

	}
//    public Site(String id, int day,int mon,String job) {
//        this.id = id;
//        this.day=day;
//        this.job=job;
//        this.mon=mon;
//
//    }

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public int getDay() {
		return day;
	}
    public int getMin() {
        return min;
    }
    public int getHos() {
        return hos;
    }
    public int getMon() {
        return mon;
    }
    public String getPage() {
        return page;
    }
    public String getJob() {
        return job;
    }
    public String getWhere() {
        return where;
    }

    public Uri getUri() {
        return uri;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setUri(Uri uri) {
        this.uri =uri;
    }
    public String getTitle() {
        return title;
    }

	public void setMon(int mon) {
		this.mon = mon;
	}
    public void setJob(String job) {
        this.job = job;
    }
    public void setWhere(String where) {
        this.where = where;
    }
    public void setPage(String page) {
        this.page = page;
    }
    public void setDay(int day) {
        this.day = day;
    }

    public void setH(int hos) {
        this.hos = hos;
    }

    public void setM(int min) {
        this.min = min;
    }
}
