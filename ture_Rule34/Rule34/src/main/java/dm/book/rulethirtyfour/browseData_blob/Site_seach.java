package dm.book.rulethirtyfour.browseData_blob;

public class Site_seach {
    private String id;
    private String seach;

    public Site_seach() {
    }

    public Site_seach(String id, String seach) {
        this.id = id;
        this.seach = seach;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getSeach() {
        return seach;
    }

    public void setSeach(String seach) {
        this.seach = seach;
    }
}
